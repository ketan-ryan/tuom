package com.mco.blocks.crops;

import library.blocks.LibBlockCrops;
import com.mco.main.TUOMItems;
import net.minecraft.item.Item;

public class TopazCrop extends LibBlockCrops 
{
	public TopazCrop() 
	{
		super();
	}

	@Override
	protected int getGrowthDelay() 
	{
		return 1200;
	}

	@Override
	protected Item getSeed() 
	{
		return TUOMItems.TOPAZ_SEEDS;
	}

	@Override
	protected Item getCrop() 
	{
		return TUOMItems.TOPAZ_SHARD;
	}

}
