package com.mco.dimensions.noise;

import java.util.Random;

public class RidgeNoiseSampler extends PerlinNoiseSampler {
    private final double exponent;

    public RidgeNoiseSampler(Random random, double exponent) {
        super(random);
        this.exponent = exponent;
    }

    @Override
    protected double evaluate(double value) {
        double ridged = Math.pow(1.0 - Math.abs(value), this.exponent) * 2.0 - 0.5;
        return ridged * this.amplitude;
    }
}