package com.mco.items.armor;

import org.lwjgl.input.Keyboard;

import library.LibRegistry;
import library.items.LibItemArmor;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DopalArmor extends LibItemArmor{
	
	public DopalArmor(String registryName, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(registryName, materialIn, equipmentSlotIn);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	protected String getArmorWrapTexture() 
	{
		return "dopal_armor";
	}
		
	@Override
	public void initRecipe() 
	{
		LibRegistry.addShapedRecipe(TUOMItems.dopal_helmet, 1, 
				
				"ttt",
				"t t",
				"   ",
				
				't', TUOMItems.dark_opal
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.dopal_chestplate, 1, 
				
				"t t",
				"ttt",
				"ttt",
				
				't', TUOMItems.dark_opal
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.dopal_leggings, 1, 
				
				"ttt",
				"t t",
				"t t",
				
				't', TUOMItems.dark_opal
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.dopal_boots, 1, 
				
				"t t",
				"t t",
				"   ",
				
				't', TUOMItems.dark_opal
				
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
		if (this.isWearingFullSet(player, TUOMItems.dopal_helmet, TUOMItems.dopal_chestplate, TUOMItems.dopal_leggings, 
				TUOMItems.dopal_boots) && player.getHeldItemOffhand().getItem() == TUOMItems.dark_staff 
				&& Keyboard.isKeyDown(Keyboard.KEY_SPACE)) { 
			player.motionY += .04;
			player.isAirBorne = true;
		}		
	}
	
	
}