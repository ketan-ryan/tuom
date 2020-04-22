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
		
		
		
		  this.addFueledRecipe(new ItemStack(TUOMItems.projectile_grenade, 1), TUOMItems.item_garnet, 100, TUOMItems.dark_opal, 
		
		    TUOMItems.light_opal, TUOMItems.fire_opal, TUOMItems.item_topaz);
	
	 }

}