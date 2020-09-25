package com.mco.biomes;

import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedChicken;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedCow;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedPig;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedSheep;
import com.mco.entities.mobs.dark.demon.corrupted.EntityDarkVex;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

public class DarkOpalBiome extends LibBiomeDark
{
	public DarkOpalBiome(BiomeProperties properties) 
	{
		super(properties);
		
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
