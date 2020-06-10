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
	public GarnetArmor(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(materialIn, equipmentSlotIn);
	}

	@Override
	protected String getArmorWrapTexture() 
	{
		return "garnet_armor";
	}
	
}
