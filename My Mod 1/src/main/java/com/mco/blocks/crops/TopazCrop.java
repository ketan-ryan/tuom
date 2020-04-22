package com.mco.blocks.crops;

import library.blocks.LibBlockCrops;
import com.mco.main.TUOMItems;
import net.minecraft.item.Item;

public class TopazCrop extends LibBlockCrops 
{
	public TopazCrop(String registryName) 
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
		return TUOMItems.topaz_seeds;
	}

	@Override
	protected Item getCrop() 
	{
		return TUOMItems.topaz_shard;
	}

}
