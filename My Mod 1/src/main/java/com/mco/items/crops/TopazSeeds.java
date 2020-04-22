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
	public TopazSeeds(String registryName, Block crops) 
	{
		super(registryName, crops);
		this.setCreativeTab(TUOM.tuom_tab);
	}
	
	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"s",
				"t",
				
				't', TUOMItems.item_topaz,
				's', Items.WHEAT_SEEDS
				
		);
	}
}
