package com.mco.items.basic.shards;

import library.LibRegistry;
import library.items.LibItemSimple;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TopazShard extends LibItemSimple 
{
	
	public TopazShard(String registryName) 
	{
		super(registryName);
		setCreativeTab(TUOM.tuom_tab);
	}
	
	@Override
	public void initRecipe() 
	{
		//GameRegistry.addShapelessRecipe(new ItemStack(TUOMItems.item_topaz, 1), new ItemStack(TUOMItems.topaz_shard, 4));
	}
}
