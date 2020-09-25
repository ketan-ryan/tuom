package com.mco.blocks.furnaces.dark;

import library.blocks.LibFurnaceTileEntity;

import com.mco.main.TUOMItems;

import net.minecraft.item.ItemStack;

import net.minecraft.util.EnumParticleTypes;

public class DarkFurnaceTileEntity extends LibFurnaceTileEntity 
{

	 public DarkFurnaceTileEntity() 
	 {
		  this.initializeSlots(3, 1, 1);
		
		  this.setCookingParticle(EnumParticleTypes.PORTAL, 100, 1);
		
		  this.setCookTimePerItem(100);
		
		  this.addFueledRecipe(new ItemStack(TUOMItems.FOPAL_GRENADE, 1), TUOMItems.ITEM_GARNET, 100, TUOMItems.DARK_OPAL, 
		    TUOMItems.LIGHT_OPAL, TUOMItems.FIRE_OPAL, TUOMItems.ITEM_TOPAZ);
	 }
}