package com.mco.dimensions.util;

import net.minecraft.util.math.MathHelper;

public class BiomeWeightTable 
{
	private final int radius;
    private final int diameter;

    private final float[] table;

    public BiomeWeightTable(int radius) {
        this.radius =  radius;
        this.diameter = (radius * 2) + 1;

        this.table = new float[this.diameter * this.diameter];
        for (int z = -radius; z <= radius; z++) {
            for (int x = -radius; x <= radius; x++) {
                float weight = 10.0F / MathHelper.sqrt((x * x + z * z) + 0.2F);
                int arrayX = x + radius;
                int arrayZ = z + radius;
                this.table[arrayX + arrayZ * this.diameter] = weight;
            }
        }
    }

    public float get(int x, int z) {
        return this.table[(x + this.radius) + (z + this.radius) * this.diameter];
    }

    public int getRadius() {
        return this.radius;
    }

}
