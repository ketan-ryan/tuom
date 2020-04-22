package com.mco.items.crops;

import library.LibRegistry;
import library.items.LibItemSeeds;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;

public class DopalSeeds extends LibItemSeeds 
{
	public DopalSeeds(String registryName, Block crops) 
	{
		super(registryName, crops);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"s",
				"d",
				
				'd', TUOMItems.dark_opal,
				's', Items.WHEAT_SEEDS
				
		);
	}
}
