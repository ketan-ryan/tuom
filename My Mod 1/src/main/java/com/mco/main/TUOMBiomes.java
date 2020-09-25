 package com.mco.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mco.TUOM;
import com.mco.biomes.DarkOpalBiome;
import com.mco.biomes.DarkOpalMountains;
import com.mco.biomes.DarkOpalPlains;
import com.mco.biomes.SatanicBiome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(TUOM.MODID)
public class TUOMBiomes 
{
    private static final List<Biome> SEED_BIOMES = new ArrayList<>();
	
	private static final Biome.BiomeProperties SATANIC_PROPERTIES = new Biome.BiomeProperties("Satan's Realm")
																				.setBaseHeight(0.0F)
																				.setHeightVariation(0.0F)
																				.setTemperature(0.1F)
																				.setRainfall(10.0F);
	
	private static final Biome.BiomeProperties DARK_FOREST_PROPERTIES = new Biome.BiomeProperties("Dark Opal Forest")
																				.setBaseHeight(0.0F)
																				.setHeightVariation(0.3F)
																				.setTemperature(0.3F)
																				.setRainfall(0.7F)
																				.setSnowEnabled()
																				.setWaterColor(0x9200D6);
	
	private static final Biome.BiomeProperties DARK_MOUNTAIN_PROPERTIES = new Biome.BiomeProperties("Dark Opal Mountains")
																				.setBaseHeight(1F)
																				.setHeightVariation(0.5F)
																				.setTemperature(0.1F)
																				.setRainfall(0.8F)
																				.setSnowEnabled()
																				.setWaterColor(0x9200D6);
	
	private static final Biome.BiomeProperties DARK_PLAINS_PROPERTIES = new Biome.BiomeProperties("Dark Opal Plains")
																				.setBaseHeight(0.0F)
																				.setHeightVariation(0.0F)
																				.setTemperature(0.0F)
																				.setRainfall(1.0F)
																				.setSnowEnabled()
																				.setWaterColor(0x9200D6);
	
	public static final Biome DARK_FOREST = new DarkOpalBiome(DARK_FOREST_PROPERTIES);
	public static final Biome DARK_MOUNTAINS = new DarkOpalMountains(DARK_MOUNTAIN_PROPERTIES);
	public static final Biome DARK_PLAINS = new DarkOpalPlains(DARK_PLAINS_PROPERTIES);
	
	public static final Biome SATANIC = new SatanicBiome(SATANIC_PROPERTIES);
	
    public static void registerBiomes(IForgeRegistry<Biome> registry) 
    {
    	registerBiome(registry, "satanic", SATANIC, Type.COLD, Type.PLAINS, Type.WET);
    	registerBiome(registry, "dark_opal_forest", DARK_FOREST, Type.SPOOKY, Type.DENSE, Type.WET);
    	registerBiome(registry, "dark_opal_mountains", DARK_MOUNTAINS, Type.SPOOKY, Type.DENSE, Type.WET);
    	registerBiome(registry, "dark_opal_plains", DARK_PLAINS, Type.SPOOKY, Type.PLAINS, Type.WET);
    }
    
    public static <T extends Biome> void registerBiome(IForgeRegistry<Biome> registry, String name, T biome, BiomeDictionary.Type... types) 
    {
    	biome.setRegistryName(name);
    	registry.register(biome);
    	BiomeDictionary.addTypes(biome, types);
    	
    	if(biome != SATANIC)
    		SEED_BIOMES.add(biome);
    }

    public static List<Biome> getSeedBiomes() 
    {
    	if(SEED_BIOMES.contains(SATANIC))
    		SEED_BIOMES.remove(SATANIC);
        return Collections.unmodifiableList(SEED_BIOMES);
    }
	
}
