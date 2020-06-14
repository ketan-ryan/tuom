package com.mco.dimensions.opal;

import com.mco.dimensions.layer.ReplaceRandomLayer;
import com.mco.dimensions.layer.TUOMSeedLayer;
import com.mco.main.TUOMBiomes;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.storage.WorldInfo;

public class OpalBiomeProvider extends BiomeProvider
{
	public OpalBiomeProvider(WorldInfo info) 
	{
		super(info);
	}

	@Override
    public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) 
	{
        GenLayer generationLayer = getBiomeProcedure();
        GenLayerVoronoiZoom layer = new GenLayerVoronoiZoom(10, generationLayer);
        layer.initWorldGenSeed(seed);

        GenLayer[] layers = new GenLayer[] { generationLayer, layer, generationLayer };
        return super.getModdedBiomeGenerators(worldType, seed, layers);
    }

    private static GenLayer getBiomeProcedure() 
    {
        GenLayer layer = new TUOMSeedLayer(0);
        if(layer != null) 
        {
        	//seed, chance, replacement, GenLayer parent
        	//Voronoi param1: size of biome
	        layer = new ReplaceRandomLayer(100, 33, Biome.getIdForBiome(TUOMBiomes.DARK_FOREST), layer);
	        layer = new GenLayerVoronoiZoom(1000, layer);
	        layer = new ReplaceRandomLayer(100,  33, Biome.getIdForBiome(TUOMBiomes.DARK_MOUNTAINS), layer);
	        layer = new GenLayerFuzzyZoom(1000, layer);
	        layer = new ReplaceRandomLayer(100,  33, Biome.getIdForBiome(TUOMBiomes.DARK_PLAINS), layer);
	        layer = new GenLayerFuzzyZoom(5000, layer);
	        
	        layer = GenLayerZoom.magnify(8000, layer, 2);
	        layer = GenLayerZoom.magnify(10000, layer, 1);
        }
        return layer;
    }
}
