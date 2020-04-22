package com.mco.dimensions.noise;

import java.util.Random;

import net.minecraft.util.math.MathHelper;

public class PerlinNoiseSampler implements INoiseSampler {
    private static final double[] GRAD_X = new double[] { 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0 };
    private static final double[] GRAD_Y = new double[] { 1.0, 1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0 };
    private static final double[] GRAD_Z = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 0.0, 1.0, 0.0, -1.0 };

    private final int[] permutations;

    protected final double offsetX;
    protected final double offsetY;
    protected final double offsetZ;

    protected double frequency = 1.0;
    protected double amplitude = 1.0;

    public PerlinNoiseSampler(Random random) {
        this.permutations = new int[512];

        this.offsetX = random.nextDouble() * 256.0;
        this.offsetY = random.nextDouble() * 256.0;
        this.offsetZ = random.nextDouble() * 256.0;

        this.initPermutationTable(random);
    }

    private void initPermutationTable(Random random) {
        for (int i = 0; i < 256; i++) {
            this.permutations[i] = i;
        }

        for (int i = 0; i < 256; ++i) {
            int shuffleIndex = random.nextInt(256 - i) + i;
            int value = this.permutations[i];
            this.permutations[i] = this.permutations[shuffleIndex];
            this.permutations[shuffleIndex] = value;
            this.permutations[i + 256] = this.permutations[i];
        }
    }

    @Override
    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    @Override
    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    @Override
    public void sample2D(double[] buffer, double originX, double originY, int sizeX, int sizeY) {
        int index = 0;

        for (int localY = 0; localY < sizeY; ++localY) {
            double scaledY = originY + localY * this.frequency + this.offsetZ;
            int floorY = MathHelper.floor(scaledY);
            int permY = floorY & 255;
            double interY = scaledY - floorY;
            double easedY = this.ease(interY);

            for (int localX = 0; localX < sizeX; ++localX) {
                double scaledX = originX + localX * this.frequency + this.offsetX;
                int floorX = MathHelper.floor(scaledX);
                int permX = floorX & 255;
                double interX = scaledX - floorX;
                double easedX = this.ease(interX);

                int permA = this.permutations[permX];
                int permB = this.permutations[permA] + permY;
                int permC = this.permutations[permX + 1];
                int permD = this.permutations[permC] + permY;

                double noiseX1 = this.lerp(easedX, this.grad2D(this.permutations[permB], interX, interY), this.grad3D(this.permutations[permD], interX - 1.0, 0.0, interY));
                double noiseX2 = this.lerp(easedX, this.grad3D(this.permutations[permB + 1], interX, 0.0, interY - 1.0), this.grad3D(this.permutations[permD + 1], interX - 1.0, 0.0, interY - 1.0));
                double noise = this.lerp(easedY, noiseX1, noiseX2);
                buffer[index++] += this.evaluate(noise);
            }
        }
    }

    @Override
    public void sample3D(double[] buffer, double originX, double originY, double originZ, int sizeX, int sizeY, int sizeZ) {
        int index = 0;

        for (int localZ = 0; localZ < sizeZ; ++localZ) {
            double scaledZ = originZ + localZ * this.frequency + this.offsetZ;
            int floorZ = MathHelper.floor(scaledZ);
            int permZ = floorZ & 255;
            double interZ = scaledZ - floorZ;
            double easedZ = this.ease(interZ);

            for (int localX = 0; localX < sizeX; ++localX) {
                double scaledX = originX + localX * this.frequency+ this.offsetX;
                int floorX = MathHelper.floor(scaledX);
                int permX = floorX & 255;
                double interX = scaledX - floorX;
                double easedX = this.ease(interX);

                for (int localY = 0; localY < sizeY; ++localY) {
                    double scaledY = originY + localY * this.frequency + this.offsetY;
                    int floorY = MathHelper.floor(scaledY);
                    int permY = floorY & 255;
                    double interY = scaledY - floorY;
                    double easedY = this.ease(interY);

                    int permA = this.permutations[permX] + permY;
                    int permB = this.permutations[permA] + permZ;
                    int permC = this.permutations[permA + 1] + permZ;
                    int permD = this.permutations[permX + 1] + permY;
                    int permE = this.permutations[permD] + permZ;
                    int permF = this.permutations[permD + 1] + permZ;

                    double noiseX1Y1 = this.lerp(easedX, this.grad3D(this.permutations[permB], interX, interY, interZ), this.grad3D(this.permutations[permE], interX - 1.0, interY, interZ));
                    double noiseX2Y1 = this.lerp(easedX, this.grad3D(this.permutations[permC], interX, interY - 1.0, interZ), this.grad3D(this.permutations[permF], interX - 1.0, interY - 1.0, interZ));
                    double noiseX1Y2 = this.lerp(easedX, this.grad3D(this.permutations[permB + 1], interX, interY, interZ - 1.0), this.grad3D(this.permutations[permE + 1], interX - 1.0, interY, interZ - 1.0));
                    double noiseX2Y2 = this.lerp(easedX, this.grad3D(this.permutations[permC + 1], interX, interY - 1.0, interZ - 1.0), this.grad3D(this.permutations[permF + 1], interX - 1.0, interY - 1.0, interZ - 1.0));

                    double noiseY1 = this.lerp(easedY, noiseX1Y1, noiseX2Y1);
                    double noiseY2 = this.lerp(easedY, noiseX1Y2, noiseX2Y2);
                    double noise = this.lerp(easedZ, noiseY1, noiseY2);
                    buffer[index++] += this.evaluate(noise);
                }
            }
        }
    }

    protected double evaluate(double value) {
        return value * this.amplitude;
    }

    private double lerp(double delta, double a, double b) {
        return a + delta * (b - a);
    }

    private double grad2D(int perm, double x, double z) {
        int gradIndex = perm & 15;
        return GRAD_X[gradIndex] * x + GRAD_Z[gradIndex] * z;
    }

    private double grad3D(int perm, double x, double y, double z) {
        int gradIndex = perm & 15;
        return GRAD_X[gradIndex] * x + GRAD_Y[gradIndex] * y + GRAD_Z[gradIndex] * z;
    }

    private double ease(double value) {
        return value * value * value * (value * (value * 6.0 - 15.0) + 10.0);
    }
}