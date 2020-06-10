package com.mco.items.teleporters;

import com.mco.TUOM;
import library.items.LibItemSimple;
import library.util.Actions;
import com.mco.TUOM;
import com.mco.generation.TUOMWorldGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class DarkOpalTeleporterStaff extends Item
{
	public DarkOpalTeleporterStaff() 
	{
		super();
		this.setMaxDamage(50);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		if(!worldIn.isRemote)
		{
			if(playerIn.dimension == 0)
				playerIn.changeDimension(TUOMWorldGenerator.OPAL_DIM_ID, new DarkOpalTeleporter(playerIn.getServer().getWorld(0)));
			else if(playerIn.dimension == TUOMWorldGenerator.OPAL_DIM_ID)
				playerIn.changeDimension(0, new DarkOpalTeleporter(playerIn.getServer().getWorld(0)));
			else
				Actions.playSound(playerIn, SoundEvents.BLOCK_ANVIL_BREAK, 1F, 1F);
		}
		playerIn.getHeldItem(handIn).damageItem(1, playerIn);
		return super.onItemRightClick(worldIn, playerIn, handIn);		
	}	
	
}
