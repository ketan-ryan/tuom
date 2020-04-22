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
	public SatanicArmor(String registryName, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(registryName, materialIn, equipmentSlotIn);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	protected String getArmorWrapTexture() 
	{
		return "satanic_armor";
	}
	
	
	@Override
	public void initRecipe() 
	{
		LibRegistry.addShapedRecipe(TUOMItems.satanic_helmet, 1, 
				
				"ttt",
				"t t",
				"   ",
				
				't', TUOMItems.item_satanic
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.satanic_chestplate, 1, 
				
				"t t",
				"ttt",
				"ttt",
				
				't', TUOMItems.item_satanic
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.satanic_leggings, 1, 
				
				"ttt",
				"t t",
				"t t",
				
				't', TUOMItems.item_satanic
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.satanic_boots, 1, 
				
				"t t",
				"t t",
				"   ",
				
				't', TUOMItems.item_satanic
				
		);
	}

	public static boolean isWearingFullSet(EntityPlayer player, Item helmet, Item chestplate, Item leggings, Item boots) 
	{
		return player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == helmet
				&& player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == chestplate
				&& player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == leggings
				&& player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == boots
				&& player.getHeldItemOffhand().getItem() == TUOMItems.dark_staff;
	}
	
	 public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) 
	 {
	  if (itemStack.getItem() == TUOMItems.satanic_chestplate) 
	  {
			Actions.addPotionEffect(player, MobEffects.RESISTANCE, 200, 2, false);
	  }
	  if (itemStack.getItem() == TUOMItems.satanic_leggings)
	  {
			Actions.addPotionEffect(player, MobEffects.JUMP_BOOST, 200, 2, false);
	  }
	  if (itemStack.getItem() == TUOMItems.satanic_boots) 
	  {
			Actions.addPotionEffect(player, MobEffects.SPEED, 200, 2, false);
	  }

	if (this.isWearingFullSet(player, TUOMItems.satanic_helmet, TUOMItems.satanic_chestplate, TUOMItems.satanic_leggings, 
			TUOMItems.satanic_boots)) {
		Actions.addPotionEffect(player, MobEffects.WEAKNESS, 200, 2, false);
	  }
	}
}