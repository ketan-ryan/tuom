package com.mco.blocks.crops;

import library.blocks.LibBlockCrops;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.item.Item;

public class LopalCrop extends LibBlockCrops 
{
	public LopalCrop() 
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
		return TUOMItems.LOPAL_SEEDS;
	}

	@Override
	protected Item getCrop() 
	{
		return TUOMItems.LOPAL_SHARD;
	}

}
