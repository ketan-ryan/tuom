package com.mco.items.food;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class DopalApple extends LibItemFood 
{
	public DopalApple(int amount, float saturation, boolean isWolfFood) 
	{
		super(amount, saturation, isWolfFood);
		this.setAlwaysEdible();
	}

	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{   	
        if (!par2World.isRemote)
        {
           par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 3000, 12));
           par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 2));
        }
	}     
}
