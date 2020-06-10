package com.mco.items.armor;

import library.LibRegistry;
import library.items.LibItemArmor;
import library.util.Actions;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
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
	
	 public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) 
	 {
	  if (itemStack.getItem() == TUOMItems.SATANIC_CHESTPLATE) 
	  {
			Actions.addPotionEffect(player, MobEffects.RESISTANCE, 200, 2, false);
	  }
	  if (itemStack.getItem() == TUOMItems.SATANIC_LEGGINGS)
	  {
			Actions.addPotionEffect(player, MobEffects.JUMP_BOOST, 200, 2, false);
	  }
	  if (itemStack.getItem() == TUOMItems.SATANIC_BOOTS) 
	  {
			Actions.addPotionEffect(player, MobEffects.SPEED, 200, 2, false);
	  }

	if (this.isWearingFullSet(player, TUOMItems.SATANIC_HELMET, TUOMItems.SATANIC_CHESTPLATE, TUOMItems.SATANIC_LEGGINGS, 
			TUOMItems.SATANIC_BOOTS)) {
		Actions.addPotionEffect(player, MobEffects.WEAKNESS, 200, 2, false);
	  }
	}

}