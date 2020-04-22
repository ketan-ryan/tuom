package com.mco.items.armor;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemArmor;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class LopalArmor extends LibItemArmor
{
	public LopalArmor(String registryName, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(registryName, materialIn, equipmentSlotIn);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	protected String getArmorWrapTexture() 
	{
		return "lopal_armor";
	}
	
	
	@Override
	public void initRecipe() 
	{
		LibRegistry.addShapedRecipe(TUOMItems.lopal_helmet, 1,
				
				"ttt",
				"t t",
				"   ",
				
				't', TUOMItems.light_opal
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.lopal_chestplate, 1, 
				
				"t t",
				"ttt",
				"ttt",
				
				't', TUOMItems.light_opal
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.lopal_leggings, 1, 
				
				"ttt",
				"t t",
				"t t",
				
				't', TUOMItems.light_opal
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.lopal_boots, 1, 
				
				"t t",
				"t t",
				"   ",
				
				't', TUOMItems.light_opal
				
		);
	}
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
//		if(stack.getItem() == TUOMItems.lopal_boots){
//			int j = EnchantmentHelper.getEnchantmentLevel(TUOM.speedBoost.effectId, stack);
//			if(j > 0){														//ticks, add level of speedboost				
//				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 50, j - 1));
//			}
//		}
	}
}
