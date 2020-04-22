package com.mco.dimensions.layer;

import java.util.List;

import com.mco.main.TUOMBiomes;
import com.mco.main.TUOMBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class TUOMSeedLayer extends GenLayer 
{
    public TUOMSeedLayer(long seed) 
    {
        super(seed);
    }

    @Override
    public int[] getInts(int originX, int originY, int width, int height) 
    {
        List<Biome> seedBiomes = TUOMBiomes.getSeedBiomes();

        int[] result = IntCache.getIntCache(width * height);
        for (int localY = 0; localY < height; localY++) 
        {
            for (int localX = 0; localX < width; localX++) 
            {
                this.initChunkSeed(originX + localX, originY + localY);
                Biome biome = seedBiomes.get(this.nextInt(seedBiomes.size()));
                result[localX + localY * width] = Biome.getIdForBiome(biome);
            }
        }

        return result;
    }
}