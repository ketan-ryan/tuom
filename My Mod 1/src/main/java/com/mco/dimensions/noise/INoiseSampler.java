package com.mco.dimensions.noise;

public interface INoiseSampler 
{
	void setFrequency(double frequency);

    void setAmplitude(double amplitude);

    void sample2D(double[] buffer, double originX, double originY, int sizeX, int sizeY);

    void sample3D(double[] buffer, double originX, double originY, double originZ, int sizeX, int sizeY, int sizeZ);
}
