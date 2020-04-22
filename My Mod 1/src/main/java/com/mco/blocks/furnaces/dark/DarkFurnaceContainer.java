package com.mco.blocks.furnaces.dark;

import library.blocks.LibFurnaceTileEntity;
import library.gui.LibFurnaceContainer;
import library.inventory.LibSlotFuel;
import library.inventory.LibSlotIngredient;
import library.inventory.LibSlotOutput;
import net.minecraft.entity.player.InventoryPlayer;

public class DarkFurnaceContainer extends LibFurnaceContainer 
{
	public DarkFurnaceContainer(InventoryPlayer playerInventory, LibFurnaceTileEntity furnaceInventory) 
	{
		super(playerInventory, furnaceInventory);
		this.setGuiTitle("Darkened Fuser");
		this.setGuiTitleColor(0x6f3d9a);
		
		this.addSlotToContainer(new LibSlotIngredient(furnaceInventory, 0, 49, 17));
		this.addSlotToContainer(new LibSlotIngredient(furnaceInventory, 1, 80, 17));
		this.addSlotToContainer(new LibSlotIngredient(furnaceInventory, 2, 111, 17));
	//	this.addSlotToContainer(new LibSlotIngredient(furnaceInventory, 3, 111, 55));
		
		this.addSlotToContainer(new LibSlotFuel(furnaceInventory, 4, 8, 63));
	//	this.addSlotToContainer(new LibSlotFuel(furnaceInventory, 5, 151, 36));
		
		this.addSlotToContainer(new LibSlotOutput(furnaceInventory, 6, 79, 60));
		
		this.setArrowPosition(54, 34);
		this.setArrowOverlayPosition(54, 201);
		this.setArrowSize(67, 21);
		this.setArrowDirection(AnimateDirection.DOWN);
				
		this.setBurnPosition(157, 23);
		this.setBurnOverlayPosition(194, 23);
		this.setBurnSize(9, 40);
		this.setBurnDirection(AnimateDirection.UP);
				
		this.addPlayerInventory(playerInventory);
	}
}
