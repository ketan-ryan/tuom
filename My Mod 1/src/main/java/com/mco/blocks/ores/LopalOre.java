package com.mco.blocks.ores;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.blocks.LibBlockOre;

public class LopalOre extends LibBlockOre 
{

	public LopalOre(String harvestTool, int harvestLevel) 
	{
		super(harvestTool, harvestLevel);
		this.setHardness(10F);
		this.setLightLevel(.5F);
		this.setResistance(20F);
	}

	@Override
	public void initRecipe() 
	{
		LibRegistry.addSmeltingRecipe(TUOMItems.LIGHT_OPAL, 1, 10, this);
	}

	
}
