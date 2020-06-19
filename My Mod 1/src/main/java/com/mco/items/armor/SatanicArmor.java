package com.mco.items.armor;

import com.mco.main.TUOMItems;

import library.items.LibItemArmor;
import library.util.Actions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class SatanicArmor extends LibItemArmor
{
	public SatanicArmor(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(materialIn, equipmentSlotIn);
	}

	@Override
	protected String getArmorWrapTexture() {
		return "satanic_armor";
	}
	
	public static boolean isWearingFullSet(EntityPlayer player, Item helmet, Item chestplate, Item leggings, Item boots) 
	{
		return player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == helmet
				&& player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == chestplate
				&& player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == leggings
				&& player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == boots;
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) 
	{
		if(!player.isSpectator()) 
		{
			NonNullList<ItemStack> armor = player.inventory.armorInventory;
			int armorPieces = 0;
			
			for(ItemStack itemArmor: armor) 
			{
				if(itemArmor != null && itemArmor.getItem() instanceof SatanicArmor) 
				{
					armorPieces += 1;
				}
			}	

			Item item = stack.getItem();		
			if(item != null) 
			{
				if(item == TUOMItems.SATANIC_CHESTPLATE)
					Actions.addPotionEffect(player, MobEffects.RESISTANCE, 2, 2, false);
				if(item == TUOMItems.SATANIC_LEGGINGS)
					Actions.addPotionEffect(player, MobEffects.JUMP_BOOST, 2, 2, false);
				if(item == TUOMItems.SATANIC_BOOTS)
					Actions.addPotionEffect(player, MobEffects.SPEED, 2, 2, false);
			}
			
			if(armorPieces >= 4) 
			{
				Actions.addPotionEffect(player, MobEffects.WEAKNESS, 2, 2, false);
			}
		}	
	}

}