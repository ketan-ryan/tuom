package com.mco.blocks.furnaces.opal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityOpalFuser extends TileEntity implements IInventory, ITickable
{

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
	
	private String customName;
	
	private int burnTime;
	private int cookTime;
	private int totalCookTime;
	
	
	@Override
	public String getName() 
	{
		//If has custom name, sets to custom name
		return this.hasCustomName() ? this.customName : "container.opal_fuser";
	}

	@Override
	public boolean hasCustomName() 
	{
		return customName != null && !customName.isEmpty();
	}

	public void setCustomName(String name)
	{
		this.customName = name;
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}
	
	@Override
	public int getSizeInventory() 
	{
		return inventory.size();
	}
	
	@Override
	public boolean isEmpty() 
	{
		for(ItemStack stack : inventory)
		{
			if(!stack.isEmpty())
				return false;
		}
		
		return true;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) 
	{
		return (ItemStack)inventory.get(index);
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		return ItemStackHelper.getAndSplit(inventory, index, count);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
		return ItemStackHelper.getAndRemove(inventory, index);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		ItemStack itemstack = (ItemStack)inventory.get(index);
		
		//Makes sure it's not empty and both itemstacks (input and existing) are equivalent and checks for equivalent metadata
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		inventory.set(index, stack);	
		
		if(stack.getCount() > this.getInventoryStackLimit())
			stack.setAnimationsToGo(this.getInventoryStackLimit());
		
		
		if(index == 0 && index+1 == 1 && !flag)
		{
			//Create new itemstack for other input
			ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
			//Set cooktime to total cooktime for both stacks
			this.totalCookTime = this.getCookTime(stack, stack1);
			//Reset cooktime as it has finished
			this.cookTime = 0;
			//Ensures the chunk containing the tile entity is saved to disk later - the game won't think it hasn't changed and skip it.
			this.markDirty();
		}
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		
		//Get the inventory
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		//Load all itemstacks back into inventory
		ItemStackHelper.loadAllItems(compound, inventory);
		//Get times from NBT
		burnTime = compound.getInteger("BurnTime");
		cookTime = compound.getInteger("CookTime");
		totalCookTime = compound.getInteger("TotalCookTime");
		//Set current burntime to burntime of item in slot 2
	//	currentBurnTime = getItemBurnTime((ItemStack)inventory.get(2));
		
		if(compound.hasKey("CustomName"))
			this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		compound.setInteger("BurnTime", (short)this.burnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		//Save all itemstacks in inventory to NBT
		ItemStackHelper.saveAllItems(compound, inventory);
		
		if(hasCustomName())
			compound.setString("CustomName", this.customName);
		
		return compound;
	}
	
	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}
	
	public boolean isBurning()
	{
		return this.burnTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory)
	{
		return inventory.getField(0) > 0;
	}
	
	public void update()
	{
		isBurning();
	}
	
	public int getCookTime(ItemStack input1, ItemStack input2)
	{
		//Different cooktimes based on recipe
		return 200;
	}
	
/*	private boolean canSmelt()
	{
		//Make sure input slots aren't empty
		if(((ItemStack)inventory.get(0)).isEmpty() || ((ItemStack)inventory.get(1)).isEmpty())
			return false;
		else
		{
			ItemStack result = OpalFuserRecipes.getInstance().getOpalResult((ItemStack)inventory.get(0), (ItemStack)inventory.get(1));
			//Can't smelt if there's no smelting recipe
			if(result.isEmpty())
				return false;
			else
			{
				ItemStack output = (ItemStack)inventory.get(3);
				if(output.isEmpty())
					return true;
				if(!output.isItemEqual(result))
					return false;
				int res = output.getCount() + result.getCount();
				return res <= getInventoryStackLimit() && res <= output.getMaxStackSize();
			}
		}
	}
*/
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
