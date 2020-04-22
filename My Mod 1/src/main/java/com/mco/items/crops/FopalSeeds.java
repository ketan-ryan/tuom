package com.mco.items.crops;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemSeeds;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public class FopalSeeds extends LibItemSeeds 
{
	public FopalSeeds(String registryName, Block crops) 
	{
		super(registryName, crops);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"s",
				"f",
				
				'f', TUOMItems.fire_opal,
				's', Items.WHEAT_SEEDS
				
		);
	}
}
