package com.mco.items.food;

import library.LibRegistry;
import library.items.LibItemFood;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class TopazApple extends LibItemFood 
{

	public TopazApple(String registryName, int amount, float saturation, boolean isWolfFood) 
	{
		super(registryName, amount, saturation, isWolfFood);
		this.setAlwaysEdible();
	}

	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{   	
        if (!par2World.isRemote)
        {
           par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 3000, 12));
           par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 2));
        }
	}     
	
	@Override
	public void initRecipe() 
	{
		LibRegistry.addShapedRecipe(this, 1, 
				
				"ttt",
				"tat",
				"ttt",
				
				't', TUOMItems.item_topaz,
				'a', Items.APPLE			
				
		);
	}
	
}
