package com.mco.blocks.ores;

import com.mco.main.TUOMItems;

import library.LibRegistry;
import library.blocks.LibBlockOre;

public class DopalOre extends LibBlockOre 
{
	public DopalOre(String harvestTool, int harvestLevel) 
	{
		super(harvestTool, harvestLevel);
		this.setHardness(10F);
		this.setLightLevel(.5F);
		this.setResistance(20F);
	}

	@Override
	public void initRecipe() 
	{
		LibRegistry.addSmeltingRecipe(TUOMItems.DARK_OPAL, 1, 10, this);
	}	
}
