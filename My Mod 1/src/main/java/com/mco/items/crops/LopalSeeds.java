package com.mco.items.crops;

import com.mco.TUOM;
import library.LibRegistry;
import library.items.LibItemSeeds;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public class LopalSeeds extends LibItemSeeds 
{
	public LopalSeeds(Block crops) 
	{
		super(crops);
		this.setCreativeTab(TUOM.tuom_tab);
	}
}
