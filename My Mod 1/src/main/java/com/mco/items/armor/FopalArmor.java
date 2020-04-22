package com.mco.items.armor;

import com.mco.TUOM;
import library.LibRegistry;
import library.items.LibItemArmor;
import library.util.Actions;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FopalArmor extends LibItemArmor
{
	public FopalArmor(String registryName, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(registryName, materialIn, equipmentSlotIn);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	protected String getArmorWrapTexture() 
	{
		return "fopal_armor";
	}
	
	@Override
	public void initRecipe() 
	{
		LibRegistry.addShapedRecipe(TUOMItems.fopal_helmet, 1, 
				
				"ttt",
				"t t",
				"   ",
				
				't', TUOMItems.fire_opal
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.fopal_chestplate, 1, 
				
				"t t",
				"ttt",
				"ttt",
				
				't', TUOMItems.fire_opal
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.fopal_leggings, 1, 
				
				"ttt",
				"t t",
				"t t",
				
				't', TUOMItems.fire_opal
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.fopal_boots, 1, 
				
				"t t",
				"t t",
				"   ",
				
				't', TUOMItems.fire_opal
				
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
		if(this.isWearingFullSet(player, TUOMItems.fopal_helmet, TUOMItems.fopal_chestplate, TUOMItems.fopal_leggings, TUOMItems.fopal_boots) == true) 
		{
			Actions.addPotionEffect(player, MobEffects.FIRE_RESISTANCE, 200, 2, false);
		}
	}
	
	
}
