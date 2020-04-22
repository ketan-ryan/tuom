package library.blocks;

import com.google.common.collect.Lists;
import library.common.FuelTiedRecipe;
import library.common.LibIngredient;
import library.inventory.IFuelUser;
import library.util.Actions;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * Features:
 * - multiple fuel input support
 * - multiple output support
 */

/**
 * RECIPE NEEDS
 *
 * shapeless ingredient list
 * a specific fuel
 * an output
 *
 * strict recipe matching, cannot be any extra stuff in slots
 * pause active fuel amount when not crafting
 *
 * only support counts of 1 for recipes, since built in systems ignore count
 */
public class LibFurnaceTileEntity extends TileEntityLockable implements ITickable, ISidedInventory, IFuelUser {

    private int slotCountIngredients = 1;
    private int slotCountFuel = 1;
    private int slotCountResult = 1;

    private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {2, 1};
    private static final int[] SLOTS_SIDES = new int[] {1};
    /** The ItemStacks that hold the items currently being used in the furnace */
    private NonNullList<ItemStack> furnaceItemStacks;
    /** The number of ticks that the furnace will keep burning */
    private int furnaceBurnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private String furnaceCustomName;
    private int cookTimePerItem = 200;

    private List<FuelTiedRecipe> registry = new ArrayList<>();

    private ParticleInfo particleForCooking = null;

    private boolean isCookingClient = false;

    private class ParticleInfo {
        private EnumParticleTypes particle;
        private int amount;
        private int size;

        public ParticleInfo(EnumParticleTypes particle, int amount, int size) {
            this.particle = particle;
            this.amount = amount;
            this.size = size;
        }

        public EnumParticleTypes getParticle() {
            return particle;
        }

        public void setParticle(EnumParticleTypes particle) {
            this.particle = particle;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    public LibFurnaceTileEntity() {
        this.slotCountIngredients = 1;
        this.slotCountFuel = 1;
        this.slotCountResult = 1;
        initInventorySlots();
    }

    public void setCookingParticle(EnumParticleTypes particle, int quantity, int size) {
        particleForCooking = new ParticleInfo(particle, quantity, size);
    }

    public ParticleInfo getParticleForCooking() {
        return particleForCooking;
    }



    /**
     * Add a shapeless recipe type smelting recipe for furnace
     *
     * @param output
     * @param fuel Block, Item, or ItemStack
     * @param fuelBurnTime How long in ticks the fuel keeps the furnace cooking for
     * @param ingredients Block, Item, or ItemStack
     */
    public void addFueledRecipe(ItemStack output, Object fuel, int fuelBurnTime, Object... ingredients) {
        NonNullList<Ingredient> listIngredients = NonNullList.create();
        for (Object obj : ingredients) {
            ItemStack entry = null;
            if (obj instanceof ItemStack) {
                entry = (ItemStack)obj;
            } else if (obj instanceof Item) {
                entry = new ItemStack((Item)obj);
            } else if (obj instanceof Block) {
                entry = new ItemStack((Block)obj);
            }
            listIngredients.add(new LibIngredient(entry));
        }
        ShapedRecipes recipe = new ShapedRecipes(output.getUnlocalizedName(), getSlotCountIngredients(), 1, listIngredients, output);

        ItemStack entry = null;
        if (fuel instanceof ItemStack) {
            entry = (ItemStack)fuel;
        } else if (fuel instanceof Item) {
            entry = new ItemStack((Item)fuel);
        } else if (fuel instanceof Block) {
            entry = new ItemStack((Block)fuel);
        }
        FuelTiedRecipe fuelTiedRecipe = new FuelTiedRecipe(recipe, entry, fuelBurnTime);

        registry.add(fuelTiedRecipe);
    }

    /**
     * Copied from ShapelessRecipes, had to since I dont have container from this context and didnt need it anyways
     *
     * it does not factor in count for the itemstacks in recipes
     *
     * @param recipe
     * @return
     */
    public boolean matches(FuelTiedRecipe recipe)
    {
        List<Ingredient> list = Lists.newArrayList(recipe.getRecipe().getIngredients());

        for (int j = 0; j < getSlotCountIngredients(); ++j)
        {
            ItemStack itemstack = getSlotsIngredients().get(j);

            if (!itemstack.isEmpty())
            {
                boolean flag = false;

                for (Ingredient ingredient : list)
                {
                    if (ingredient.apply(itemstack))
                    {
                        flag = true;
                        list.remove(ingredient);
                        break;
                    }
                }

                if (!flag)
                {
                    return false;
                }
            }
        }

        return list.isEmpty();
    }

    public FuelTiedRecipe findRecipeToUse(boolean matchFuelSource)
    {
        for (FuelTiedRecipe irecipe : registry)
        {
            if (matches(irecipe))
            {
                if (matchFuelSource) {
                    boolean foundFuel = false;
                    NonNullList<ItemStack> slots = getSlotsFuel();
                    for (ItemStack stack : slots) {
                        if (!stack.isEmpty() && irecipe.getFuel().getItem() == stack.getItem()) {
                            foundFuel = true;
                            break;
                        }
                    }
                    if (foundFuel) {
                        return irecipe;
                    }
                } else {
                    return irecipe;
                }
            }
        }

        return null;
    }

    /**
     * Set how many slots there are for each type, and then initialize the inventory slots for them
     * You can use as many slots as you can fit on your gui
     *
     * @param slotCountIngredients
     * @param slotCountFuel
     * @param slotCountResult
     */
    public void initializeSlots(int slotCountIngredients, int slotCountFuel, int slotCountResult) {
        this.slotCountIngredients = slotCountIngredients;
        this.slotCountFuel = slotCountFuel;
        this.slotCountResult = slotCountResult;
        initInventorySlots();
    }

    public void initInventorySlots() {
        furnaceItemStacks = NonNullList.withSize(slotCountIngredients+slotCountFuel+slotCountResult, ItemStack.EMPTY);
    }

    public int getSlotCountIngredients() {
        return slotCountIngredients;
    }

    public void setSlotCountIngredients(int slotCountIngredients) {
        this.slotCountIngredients = slotCountIngredients;
    }

    public int getSlotCountFuel() {
        return slotCountFuel;
    }

    public void setSlotCountFuel(int slotCountFuel) {
        this.slotCountFuel = slotCountFuel;
    }

    public int getSlotCountResult() {
        return slotCountResult;
    }

    public void setSlotCountResult(int slotCountResult) {
        this.slotCountResult = slotCountResult;
    }

    /**
     * Set the amount of time in ticks for an item to get cooked
     *
     * @param time Any range allowed
     */
    public void setCookTimePerItem(int time) {
        this.cookTimePerItem = time;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public int getIngredientRangeMin() {
        return 0;
    }

    public int getIngredientRangeMax() {
        return this.slotCountIngredients - 1;
    }

    public int getFuelRangeMin() {
        return this.slotCountIngredients;
    }

    public int getFuelRangeMax() {
        return this.slotCountIngredients+this.slotCountFuel - 1;
    }

    public int getOutRangeMin() {
        return this.slotCountIngredients+this.slotCountFuel;
    }

    public int getOutRangeMax() {
        return this.slotCountIngredients+this.slotCountFuel+this.slotCountResult - 1;
    }

    public NonNullList<ItemStack> getSlotsIngredients() {
        NonNullList<ItemStack> slots = NonNullList.withSize(slotCountIngredients, ItemStack.EMPTY);
        for (int i = 0; i < slotCountIngredients; i++) {
            slots.set(i, furnaceItemStacks.get(i));
        }
        return slots;
    }

    public NonNullList<ItemStack> getSlotsFuel() {
        NonNullList<ItemStack> slots = NonNullList.withSize(slotCountFuel, ItemStack.EMPTY);
        int offset = slotCountIngredients;
        for (int i = offset; i < offset+slotCountFuel; i++) {
            slots.set(i-offset, furnaceItemStacks.get(i));
        }
        return slots;
    }

    public NonNullList<ItemStack> getSlotsOutput() {
        NonNullList<ItemStack> slots = NonNullList.withSize(slotCountResult, ItemStack.EMPTY);
        int offset = slotCountIngredients+slotCountFuel;
        for (int i = offset; i < offset+slotCountResult; i++) {
            slots.set(i-offset, furnaceItemStacks.get(i));
        }
        return slots;
    }

    /**
     * Should be able to assume entries are valid fuel sources, container prevents adding invalid ones
     * @return
     */
    public ItemStack getNextFuelStack() {
        NonNullList<ItemStack> slots = getSlotsFuel();
        for (ItemStack stack : slots) {
            if (!stack.isEmpty()) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public int getNextFuelSlot() {
        NonNullList<ItemStack> slots = getSlotsFuel();
        int offset = slotCountIngredients;
        for (int i = 0; i < slotCountFuel; i++) {
            ItemStack stack = slots.get(i);
            if (!stack.isEmpty()) {
                return offset+i;
            }
        }
        return -1;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.furnaceItemStacks.size();
    }

    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.furnaceItemStacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the stack in the given slot.
     */
    public ItemStack getStackInSlot(int index)
    {
        return this.furnaceItemStacks.get(index);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.furnaceItemStacks, index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.furnaceItemStacks, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        ItemStack itemstack = this.furnaceItemStacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.furnaceItemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index < slotCountIngredients && !flag)
        {
            this.totalCookTime = this.getCookTime();
            this.cookTime = 0;
            this.markDirty();
        }
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.hasCustomName() ? this.furnaceCustomName : "container.furnace";
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return this.furnaceCustomName != null && !this.furnaceCustomName.isEmpty();
    }

    public void setCustomInventoryName(String p_145951_1_)
    {
        this.furnaceCustomName = p_145951_1_;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.furnaceItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.furnaceItemStacks);
        this.furnaceBurnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.getNextFuelStack());

        if (compound.hasKey("CustomName", 8))
        {
            this.furnaceCustomName = compound.getString("CustomName");
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.furnaceBurnTime);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.furnaceItemStacks);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.furnaceCustomName);
        }

        return compound;
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }

    public boolean isCooking() {
        FuelTiedRecipe recipeWithoutFuel = this.findRecipeToUse(false);

        //pause active fuel using if no recipe to smelt
        return this.isBurning() && recipeWithoutFuel != null;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;

        //for full match including checking if we have fuel for it too
        FuelTiedRecipe recipe = this.findRecipeToUse(true);

        //some parts of code dont need valid fuel item due to furnace burn time being a thing
        FuelTiedRecipe recipeWithoutFuel = this.findRecipeToUse(false);

        //pause active fuel using if no recipe to smelt
        if (this.isBurning() && recipeWithoutFuel != null)
        {
            --this.furnaceBurnTime;
        }

        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("isCooking", isCooking());
        FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().sendPacketToAllPlayersInDimension(new SPacketUpdateTileEntity(getPos(), 0, tag), world.provider.getDimension());

        if (!this.world.isRemote)
        {
            //not validated with fuelrecipe yet
            ItemStack fuel = this.getNextFuelStack();
            int fuelSlot = this.getNextFuelSlot();

            //if we are able to keep crafting
            if (this.isBurning() || !fuel.isEmpty() && recipeWithoutFuel != null)
            {
                //try consume another item for burn time
                if (!this.isBurning() && this.canSmelt(true))
                {
                    this.furnaceBurnTime = recipe.getFuelBurnTime();//getItemBurnTime(fuel);
                    this.currentItemBurnTime = this.furnaceBurnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        //consume 1 stackcount
                        if (!fuel.isEmpty())
                        {
                            Item item = fuel.getItem();
                            fuel.shrink(1);

                            //remove the fuel item if now empty
                            if (fuel.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(fuel);
                                this.furnaceItemStacks.set(fuelSlot, item1);
                            }
                        }
                    }
                }

                //continue cooking
                if (this.isBurning() && this.canSmelt(false))
                {
                    ++this.cookTime;

                    //cooking complete
                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime();
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
            }
        }

        if (flag1)
        {
            this.markDirty();
        }

        if (world.isRemote) {
            tickClient();
        }


    }

    @SideOnly(Side.CLIENT)
    public void tickClient() {
        if (isCookingClient && particleForCooking != null) {
            Actions.spawnParticleAtPosition(world, getPos(), particleForCooking.getParticle(), particleForCooking.getAmount(), particleForCooking.getSize());
        }
    }

    public int getCookTime()
    {
        return cookTimePerItem;
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt(boolean matchFuelSource)
    {
        FuelTiedRecipe recipe = this.findRecipeToUse(matchFuelSource);

        if (recipe != null) {
            ItemStack newResult = recipe.getRecipe().getRecipeOutput().copy();
            NonNullList<ItemStack> listOutput = getSlotsOutput();
            for (ItemStack output : listOutput) {

                if (output.isEmpty()) {
                    return true;
                } else if (!output.isItemEqual(newResult)) {
                    continue;
                } else if (output.getCount() + newResult.getCount() <= this.getInventoryStackLimit() && output.getCount() + newResult.getCount() <= output.getMaxStackSize())  // Forge fix: make furnace respect stack sizes in furnace recipes
                {
                    return true;
                } else {
                    if (output.getCount() + newResult.getCount() <= newResult.getMaxStackSize()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * - Assume item to smelt is the first found in the ingredients list
     * - First check for output with same item and stack space, otherwise find empty slot
     */
    public void smeltItem()
    {
        if (this.canSmelt(false))
        {
            FuelTiedRecipe recipe = this.findRecipeToUse(false);
            ItemStack newResult = recipe.getRecipe().getRecipeOutput().copy();

            NonNullList<ItemStack> listOutput = getSlotsOutput();
            for (int i = 0; i < slotCountResult; i++) {
                ItemStack result = listOutput.get(i);
                //enough space is verified in canSmelt
                if (result.getItem() == newResult.getItem()) {
                    //grows by size of result in smelting list
                    result.grow(newResult.getCount());
                    shinkItemsFromIngredients();
                    return;
                } else if (result.isEmpty()) {
                    this.furnaceItemStacks.set(slotCountIngredients+slotCountFuel+i, newResult.copy());
                    shinkItemsFromIngredients();
                    return;
                }
            }
        }
    }

    public void shinkItemsFromIngredients() {
        for (int i = 0; i < slotCountIngredients; i++) {
            furnaceItemStacks.get(i).shrink(1);
        }
    }

    /**
     *
     * Not really used atm, maybe when i fix shift clicking
     *
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    @Override
    public int getItemBurnTime(ItemStack stack)
    {
        return 0;
    }

    @Override
    public boolean isItemFuel(ItemStack stack)
    {
        //return getItemBurnTime(stack) > 0;
        for (FuelTiedRecipe irecipe : registry)
        {
            if (irecipe.getFuel().getItem() == stack.getItem()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    public void openInventory(EntityPlayer player)
    {
    }

    public void closeInventory(EntityPlayer player)
    {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        if (index < slotCountIngredients) {
            return true;
        } else if (index >= slotCountIngredients+slotCountFuel) {
            return false;
        } else if (index >= slotCountIngredients && index < slotCountIngredients+slotCountFuel/*isItemFuel(stack)*/) {
            return true;
        } else {
            return false;
        }
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        if (side == EnumFacing.DOWN)
        {
            return SLOTS_BOTTOM;
        }
        else
        {
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
            {
                return false;
            }
        }

        return true;
    }

    public String getGuiID()
    {
        return "minecraft:furnace";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerFurnace(playerInventory, this);
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.furnaceBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.furnaceBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }

    public int getFieldCount()
    {
        return 4;
    }

    public void clear()
    {
        this.furnaceItemStacks.clear();
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        isCookingClient = pkt.getNbtCompound().getBoolean("isCooking");
    }
}
