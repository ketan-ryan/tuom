package com.mco.items.basic;

import com.mco.TUOM;
import com.mco.generation.TUOMWorldGenerator;
import com.mco.items.teleporters.DarkOpalTeleporter;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemSimple;
import library.util.Actions;
import com.mco.TUOM;
import com.mco.generation.TUOMWorldGenerator;
import com.mco.items.teleporters.DarkOpalTeleporter;
import com.mco.main.TUOMItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ItemDopal extends LibItemSimple 
{
	public ItemDopal(String registryName) 
	{
		super(registryName);
		setCreativeTab(TUOM.tuom_tab);
	}
	
	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"dd",
				"dd",
				
				'd', TUOMItems.dopal_shard
				
		);
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

		return super.onItemRightClick(worldIn, playerIn, handIn);		
	}	
}
