package com.mco.blocks.crops;

import library.blocks.LibBlockCrops;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.item.Item;

public class DopalCrop extends LibBlockCrops 
{
	public DopalCrop() 
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
		return TUOMItems.DOPAL_SEEDS;
	}

	@Override
	protected Item getCrop() 
	{
		return TUOMItems.DOPAL_SHARD;
	}

}
