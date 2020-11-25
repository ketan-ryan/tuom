package com.mco.blocks.ores;

import com.mco.main.TUOMItems;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class LopalOre extends BlockOre
{

	public LopalOre(String harvestTool, int harvestLevel) 
	{
 		setHarvestLevel(harvestTool, harvestLevel);
		this.setHardness(10F);
		this.setLightLevel(.5F);
		this.setResistance(20F);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return TUOMItems.LIGHT_OPAL;
	}
}
