package com.mco.blocks.ores;

import com.mco.TUOM;
import library.LibRegistry;
import library.blocks.LibBlockOre;
import com.mco.TUOM;
import com.mco.main.TUOMItems;

public class TopazOre extends LibBlockOre 
{
	public TopazOre(String registryName, String harvestTool, int harvestLevel) 
	{
		super(registryName, harvestTool, harvestLevel);
		this.setCreativeTab(TUOM.tuom_tab);
		this.setHardness(10F);
		this.setLightLevel(.5F);
		this.setResistance(20F);
	}

	@Override
	public void initRecipe() 
	{
		LibRegistry.addSmeltingRecipe(TUOMItems.ITEM_TOPAZ, 1, 10, this);
	}	
}
