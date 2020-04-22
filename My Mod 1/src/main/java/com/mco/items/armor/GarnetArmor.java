package com.mco.items.armor;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemArmor;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class GarnetArmor extends LibItemArmor
{
	public GarnetArmor(String registryName, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(registryName, materialIn, equipmentSlotIn);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	protected String getArmorWrapTexture() 
	{
		return "garnet_armor";
	}
	
	@Override
	public void initRecipe() 
	{
		LibRegistry.addShapedRecipe(TUOMItems.garnet_helmet, 1,
				
				"ttt",
				"t t",
				"   ",
				
				't', TUOMItems.item_garnet
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.garnet_chestplate, 1, 
				
				"t t",
				"ttt",
				"ttt",
				
				't', TUOMItems.item_garnet
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.garnet_leggings, 1, 
				
				"ttt",
				"t t",
				"t t",
				
				't', TUOMItems.item_garnet
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.garnet_boots, 1, 
				
				"t t",
				"t t",
				"   ",
				
				't', TUOMItems.item_garnet
				
		);
	}
	
}
