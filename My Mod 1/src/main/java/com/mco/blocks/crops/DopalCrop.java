package com.mco.blocks.crops;

import library.blocks.LibBlockCrops;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.item.Item;

public class DopalCrop extends LibBlockCrops 
{
	public DopalCrop(String registryName) 
	{
		super(registryName);
	}

	@Override
	protected int getGrowthDelay() 
	{
		return 1200;
	}

	@Override
	protected Item getSeed() 
	{
		return TUOMItems.dopal_seeds;
	}

	@Override
	protected Item getCrop() 
	{
		return TUOMItems.dopal_shard;
	}

}
