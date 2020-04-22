package com.mco.biomes;

import library.biomes.LibBiome;

public class SatanicBiome extends LibBiome 
{

	public SatanicBiome(String registryName, BiomeProperties properties) 
	{
		super(registryName, properties);

		/*this.topBlock = TUOMBlocks.satanic_block;
		this.fillerBlock = TUOMBlocks.satanic_block;*/
		
		this.setGrassColor(0xFF0000);
		this.setWaterColor(0xFF0000);
		this.setFoliageColor(0xFF0000);
		this.setSkyColor(0xFF0000);
		
		//this.addMonsterSpawn(entityclassIn, weight, groupCountMin, groupCountMax);	
	}

}
