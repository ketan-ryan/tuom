package com.mco.energy;

public class UltimateEnergy implements IUltimateEnergy
{

	private float energy = 0F;
	
	public void consume(float value)
	{
		this.energy -= value;
	}
	
	public void fill(float value)
	{
		this.energy += value;
	}
	
	public void set(float value)
	{
		this.energy = value;
	}
	
	public float getEnergy()
	{
		return energy;
	}
}
