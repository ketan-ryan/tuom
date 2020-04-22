package com.mco.energy;

public interface IUltimateEnergy 
{
	public void consume(float value);
	public void fill(float value);
	public void set(float value);
	
	public float getEnergy();
}
