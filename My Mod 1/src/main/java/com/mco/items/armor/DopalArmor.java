package com.mco.items.armor;

import com.mco.TUOM;
import library.items.LibItemArmor;
import net.minecraft.inventory.EntityEquipmentSlot;

public class DopalArmor extends LibItemArmor
{
	public DopalArmor(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(materialIn, equipmentSlotIn);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	protected String getArmorWrapTexture() 
	{
		return "dopal_armor";
	}

}