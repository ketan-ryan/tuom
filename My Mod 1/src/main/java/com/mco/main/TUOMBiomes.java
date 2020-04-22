package com.mco.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.mco.biomes.DarkOpalMountains;
import library.LibRegistry;
import com.mco.biomes.DarkOpalBiome;
import com.mco.biomes.DarkOpalPlains;
import com.mco.biomes.SatanicBiome;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class TUOMBiomes 
{
	public static Biome dark_forest, dark_mountains, dark_plains;
	public static Biome satanic_biome;
	
    private static final List<Biome> SEED_BIOMES = new ArrayList<>();

	public static void init(FMLInitializationEvent event) 
	{
		/**
		 * DECLARE BIOMES =================================================
		 */
		
		Biome.BiomeProperties satanic_biome_properties;
		
		Biome.BiomeProperties dark_forest_properties;
		Biome.BiomeProperties dark_mountain_properties;
		Biome.BiomeProperties dark_plains_properties;
		/**
		 * REGISTER BIOMES =================================================
		 */
		
		satanic_biome_properties = new Biome.BiomeProperties("Satan's Realm");
		satanic_biome_properties.setBaseHeight(0.0F);
		satanic_biome_properties.setHeightVariation(0.0F);
		satanic_biome_properties.setTemperature(0.1F);
		satanic_biome_properties.setRainfall(10.0F);
		
		satanic_biome = new SatanicBiome("satanic_biome", satanic_biome_properties);
		LibRegistry.registerBiome(satanic_biome, 0);

		dark_forest_properties = new Biome.BiomeProperties("Dark Opal Forest");
		dark_forest_properties.setBaseHeight(0F);
		dark_forest_properties.setHeightVariation(.3F);
		dark_forest_properties.setTemperature(.3F);
		dark_forest_properties.setRainfall(.7F);
		dark_forest_properties.setSnowEnabled();
		dark_forest_properties.setWaterColor(0x9200D6);
		
		dark_forest = new DarkOpalBiome("dark_opal_forest", dark_forest_properties);
		
		LibRegistry.registerBiome(dark_forest, 0);
		BiomeDictionary.addTypes(dark_forest, Type.SPOOKY, Type.DENSE, Type.WET);
		
		dark_mountain_properties = new Biome.BiomeProperties("Dark Opal Mountains");
		dark_mountain_properties.setBaseHeight(1F);
		dark_mountain_properties.setHeightVariation(.5F);
		dark_mountain_properties.setTemperature(.1F);
		dark_mountain_properties.setRainfall(.8F);
		dark_mountain_properties.setSnowEnabled();
		dark_mountain_properties.setWaterColor(0x9200D6);
		
		dark_mountains = new DarkOpalMountains("dark_opal_mountains", dark_mountain_properties);
		
		LibRegistry.registerBiome(dark_mountains, 0);
		BiomeDictionary.addTypes(dark_mountains, Type.SPOOKY, Type.DENSE, Type.WET);
		
		dark_plains_properties = new Biome.BiomeProperties("Dark Opal Plains");
		dark_plains_properties.setBaseHeight(0F);
		dark_plains_properties.setHeightVariation(0F);
		dark_plains_properties.setTemperature(0F);
		dark_plains_properties.setRainfall(1F);
		dark_plains_properties.setSnowEnabled();
		dark_plains_properties.setWaterColor(0x9200D6);
		
		dark_plains = new DarkOpalPlains("dark_opal_plains", dark_plains_properties);
		
		LibRegistry.registerBiome(dark_plains, 0);
		BiomeDictionary.addTypes(dark_plains, Type.SPOOKY, Type.PLAINS, Type.WET);
		
		addSeedBiomes(dark_forest);
		addSeedBiomes(dark_mountains);
		addSeedBiomes(dark_plains);
	}

	public static void addSeedBiomes(Biome... biomes) 
	{
        SEED_BIOMES.addAll(Arrays.asList(biomes));
    }

    public static List<Biome> getSeedBiomes() 
    {
        return Collections.unmodifiableList(SEED_BIOMES);
    }
	
}
