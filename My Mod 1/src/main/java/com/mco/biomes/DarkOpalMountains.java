package com.mco.biomes;

import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedChicken;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedCow;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedPig;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedSheep;
import com.mco.entities.mobs.dark.demon.corrupted.EntityDarkVex;

import net.minecraft.world.biome.BiomeDecorator;

public class DarkOpalMountains extends LibBiomeDark
{
	public DarkOpalMountains(BiomeProperties properties) 
	{
		super(properties);
		
		this.addMonsterSpawn(EntityDarkVex.class, 1, 1, 1);
		this.addMonsterSpawn(EntityCorruptedChicken.class, 1, 1, 3);
		this.addMonsterSpawn(EntityCorruptedCow.class, 1, 1, 3);
		this.addMonsterSpawn(EntityCorruptedPig.class, 1, 1, 3);
		this.addMonsterSpawn(EntityCorruptedSheep.class, 1, 1, 3);
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
