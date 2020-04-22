package com.mco.biomes;

import java.util.List;
import java.util.Random;

import com.mco.entities.mobs.dark.demon.corrupted.*;
import library.biomes.LibBiome;
import com.mco.dimensions.opal.WorldGenDarkTree;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedChicken;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedCow;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedPig;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedSheep;
import com.mco.entities.mobs.dark.demon.corrupted.EntityDarkVex;
import com.mco.main.TUOMBlocks;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class DarkOpalBiome extends LibBiomeDark
{
	public DarkOpalBiome(String registryName, BiomeProperties properties) 
	{
		super(registryName, properties);
		
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityDarkVex.class, 15, 1, 3));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCorruptedChicken.class, 15, 1, 3));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCorruptedCow.class, 15, 1, 3));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCorruptedPig.class, 15, 1, 3));
		this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCorruptedSheep.class, 15, 1, 3));

	}

	@Override
	public BiomeDecorator createBiomeDecorator()
	{
		super.createBiomeDecorator();
		BiomeDecorator biomeDecorator = new DarkOpalBiomeDecorator();
		
		biomeDecorator.treesPerChunk = 0;
		biomeDecorator.extraTreeChance = 0F;
		
		return getModdedBiomeDecorator(biomeDecorator);
	}	    
}
