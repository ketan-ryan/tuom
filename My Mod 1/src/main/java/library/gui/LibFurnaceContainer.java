package library.gui;

import library.LibRegistry;
import library.blocks.LibFurnaceTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LibFurnaceContainer extends Container {

    private final LibFurnaceTileEntity tileFurnace;
    private int cookTime;
    private int totalCookTime;
    private int furnaceBurnTime;
    private int currentItemBurnTime;

    private String guiTitle = "Furnace";
    private int guiTitleColor = 4210752;

    public InventoryCrafting craftMatrix;

    /**
     * Stuff migrated from GUI
     */

    private ResourceLocation textureGUI;



    /**
     * For burn progress and smelt progress, you are copy and pasting a section of the GUI texture onto the screen
     */

    //position on texture
    private int burnPositionTextureX = 176;
    private int burnPositionTextureY = 12;

    //position to render
    private int burnPositionScreenX = 56;
    private int burnPositionScreenY = 36;

    private int burnWidth = 14;
    private int burnHeight = 14;

    //position on texture
    private int smeltPositionTextureX = 176;
    private int smeltPositionTextureY = 14;

    //position to render
    private int smeltPositionScreenX = 79;
    private int smeltPositionScreenY = 34;

    private int smeltWidth = 24;
    private int smeltHeight = 16;

    public enum AnimateDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private AnimateDirection animateDirectionProgress = AnimateDirection.RIGHT;
    private AnimateDirection animateDirectionBurn = AnimateDirection.UP;

    public void setBurnDirection(AnimateDirection animateDirectionBurn) {
        this.animateDirectionBurn = animateDirectionBurn;
    }

    public void setArrowDirection(AnimateDirection animateDirectionArrow) {
        this.animateDirectionProgress = animateDirectionArrow;
    }

    public AnimateDirection getAnimateDirectionProgress() {
        return animateDirectionProgress;
    }

    public AnimateDirection getAnimateDirectionBurn() {
        return animateDirectionBurn;
    }

    public String getGuiTitle() {
        return guiTitle;
    }

    /**
     * Sets the GUI title that you see at the top of the GUI
     *
     * @param guiTitle
     */
    public void setGuiTitle(String guiTitle) {
        this.guiTitle = guiTitle;
    }

    public int getGuiTitleColor() {
        return guiTitleColor;
    }

    /**
     * Sets the color of the furnace title and inventory title
     *
     * @param guiTitleColor
     */
    public void setGuiTitleColor(int guiTitleColor) {
        this.guiTitleColor = guiTitleColor;
    }

    /**
     * Set the position of the burn animation on the GUI
     *
     * @param x
     * @param y
     */
    public void setBurnPosition(int x, int y) {
        this.burnPositionScreenX = x;
        this.burnPositionScreenY = y;
    }

    /**
     * Set the position of the burn animation on the texture file
     *
     * @param x
     * @param y
     */
    public void setBurnOverlayPosition(int x, int y) {
        this.burnPositionTextureX = x;
        this.burnPositionTextureY = y;
    }

    /**
     * Set the position of the arrow progress animation on the GUI
     *
     * @param x
     * @param y
     */
    public void setArrowPosition(int x, int y) {
        this.smeltPositionScreenX = x;
        this.smeltPositionScreenY = y;
    }

    /**
     * Set the position of the arrow progress animation on the texture file
     *
     * @param x
     * @param y
     */
    public void setArrowOverlayPosition(int x, int y) {
        this.smeltPositionTextureX = x - 2;
        this.smeltPositionTextureY = y;
    }

    public int getBurnPositionTextureX() {
        return burnPositionTextureX;
    }

    public int getBurnPositionTextureY() {
        //- getBurnHeight() to fix their position being bottom y, not top y
        return burnPositionTextureY/* - getBurnHeight()*/;
    }

    public int getBurnPositionScreenX() {
        return burnPositionScreenX;
    }

    public int getBurnPositionScreenY() {
        return burnPositionScreenY;
    }

    public void setArrowSize(int width, int height) {
        this.smeltWidth = width;
        this.smeltHeight = height;
    }

    public void setBurnSize(int width, int height) {
        this.burnWidth = width;
        this.burnHeight = height;
    }

    public int getBurnWidth() {
        return burnWidth;
    }

    public void setBurnWidth(int burnWidth) {
        this.burnWidth = burnWidth;
    }

    public int getBurnHeight() {
        return burnHeight;
    }

    public void setBurnHeight(int burnHeight) {
        this.burnHeight = burnHeight;
    }

    public int getSmeltPositionTextureX() {
        return smeltPositionTextureX;
    }

    public int getSmeltPositionTextureY() {
        return smeltPositionTextureY;
    }

    public int getSmeltPositionScreenX() {
        return smeltPositionScreenX;
    }

    public int getSmeltPositionScreenY() {
        return smeltPositionScreenY;
    }

    public int getSmeltWidth() {
        return smeltWidth;
    }

    public void setSmeltWidth(int smeltWidth) {
        this.smeltWidth = smeltWidth;
    }

    public int getSmeltHeight() {
        return smeltHeight;
    }

    public void setSmeltHeight(int smeltHeight) {
        this.smeltHeight = smeltHeight;
    }

    public LibFurnaceContainer(InventoryPlayer playerInventory, LibFurnaceTileEntity furnaceInventory)
    {
        this.tileFurnace = furnaceInventory;

        craftMatrix = new InventoryCrafting(this, furnaceInventory.getSlotCountIngredients(), 1);


    }

    public void addPlayerInventory(InventoryPlayer playerInventory) {
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    public ResourceLocation getTextureGUI() {
        //return textureGUI;
        return new ResourceLocation(LibRegistry.getModid() + ":textures/" + LibGUIHandler.getTexture(this.getClass()));
    }

    public void setTextureGUI(ResourceLocation textureGUI) {
        this.textureGUI = textureGUI;
    }

    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileFurnace);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.cookTime != this.tileFurnace.getField(2))
            {
                icontainerlistener.sendWindowProperty(this, 2, this.tileFurnace.getField(2));
            }

            if (this.furnaceBurnTime != this.tileFurnace.getField(0))
            {
                icontainerlistener.sendWindowProperty(this, 0, this.tileFurnace.getField(0));
            }

            if (this.currentItemBurnTime != this.tileFurnace.getField(1))
            {
                icontainerlistener.sendWindowProperty(this, 1, this.tileFurnace.getField(1));
            }

            if (this.totalCookTime != this.tileFurnace.getField(3))
            {
                icontainerlistener.sendWindowProperty(this, 3, this.tileFurnace.getField(3));
            }
        }

        this.cookTime = this.tileFurnace.getField(2);
        this.furnaceBurnTime = this.tileFurnace.getField(0);
        this.currentItemBurnTime = this.tileFurnace.getField(1);
        this.totalCookTime = this.tileFurnace.getField(3);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileFurnace.setField(id, data);
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileFurnace.isUsableByPlayer(playerIn);
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        //old values
        //0 = in
        //1 = fuel
        //2 = out

        //TODO: fix for new dynamic slot allocation system
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        int playerInvSize = 9*4;

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            //is out
            if (index >= tileFurnace.getOutRangeMin() && index <= tileFurnace.getOutRangeMax())
            {
                if (!this.mergeItemStack(itemstack1, tileFurnace.getOutRangeMax() + 1, tileFurnace.getOutRangeMax() + playerInvSize - 1, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }//is not fuel and not in (assumed player inventory)
            else if (index > tileFurnace.getOutRangeMax())
            {
                //is fuel
                if (tileFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, this.tileFurnace.getFuelRangeMin(), this.tileFurnace.getFuelRangeMax() + 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                    //auto merge to ingredients since we cant validate it due to multi ingredient system
                } else {
                    if (!this.mergeItemStack(itemstack1, 0, this.tileFurnace.getIngredientRangeMax() + 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            }
            else if (!this.mergeItemStack(itemstack1, tileFurnace.getOutRangeMax() + 1, tileFurnace.getOutRangeMax() + playerInvSize - 1, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}
