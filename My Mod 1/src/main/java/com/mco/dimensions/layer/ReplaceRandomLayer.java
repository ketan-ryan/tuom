package com.mco.dimensions.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class ReplaceRandomLayer extends GenLayer 
{
    private final int chance;
    private final int replacement;

    public ReplaceRandomLayer(long seed, int chance, int replacement, GenLayer parent) 
    {
        super(seed);
        this.chance = chance;
        this.replacement = replacement;
        this.parent = parent;
    }

    @Override
    public int[] getInts(int originX, int originY, int width, int height) 
    {
        int[] sampled = this.parent.getInts(originX, originY, width, height);
        int[] result = IntCache.getIntCache(width * height);

        for (int localY = 0; localY < height; localY++) 
        {
            for (int localX = 0; localX < width; localX++) 
            {
                this.initChunkSeed(originX + localX, originY + localY);

                int index = localX + localY * width;
                if (this.nextInt(this.chance) == 0) 
                {
                    result[index] = this.replacement;
                } else 
                {
                    result[index] = sampled[index];
                }
            }
        }

        return result;
    }
}