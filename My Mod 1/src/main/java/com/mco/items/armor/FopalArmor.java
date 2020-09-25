package com.mco.items.armor;

import com.mco.TUOM;

import library.items.LibItemArmor;
import library.util.Actions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class FopalArmor extends LibItemArmor
{
	public FopalArmor(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(materialIn, equipmentSlotIn);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	protected String getArmorWrapTexture() 
	{
		return "fopal_armor";
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{ 
		if(!player.isSpectator()) 
		{
			NonNullList<ItemStack> armor = player.inventory.armorInventory;
			int armorPieces = 0;
			
			for(ItemStack itemArmor: armor)
			{
				if(itemArmor != null && itemArmor.getItem() instanceof FopalArmor)
				{
					armorPieces += 1;
				}
			}	

			if(armorPieces >= 4)
			{
				Actions.addPotionEffect(player, MobEffects.FIRE_RESISTANCE, 2, 2, false);
			}
		}	
	}
	
	
}
