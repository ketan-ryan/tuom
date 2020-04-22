package com.mco.biomes;

import java.util.List;
import java.util.Random;

import library.biomes.LibBiome;
import com.mco.dimensions.opal.WorldGenDarkTree;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedChicken;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedCow;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedPig;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedSheep;
import com.mco.entities.mobs.dark.demon.corrupted.EntityDarkVex;
import com.mco.main.TUOMBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class DarkOpalMountains extends LibBiomeDark
{
	public DarkOpalMountains(String registryName, BiomeProperties properties) 
	{
		super(registryName, properties);
	}

	public BiomeDecorator createBiomeDecorator()
	{
		super.createBiomeDecorator();
		BiomeDecorator biomeDecorator = new DarkOpalBiomeDecorator();
		
		biomeDecorator.treesPerChunk = -999;
		biomeDecorator.extraTreeChance = 100F;
		
		return getModdedBiomeDecorator(biomeDecorator);
	}	    
}
