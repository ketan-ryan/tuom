package com.mco.items.crops;

import library.LibRegistry;
import library.items.LibItemSeeds;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public class TopazSeeds extends LibItemSeeds 
{
	public TopazSeeds(Block crops) 
	{
		super(crops);
		this.setCreativeTab(TUOM.tuom_tab);
	}
}
