package com.mco.entities.vehicles;

import org.lwjgl.input.Keyboard;

import library.entities.vehicles.LibEntityVehicle;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class DarkShip extends LibEntityVehicle
{

	public DarkShip(World worldIn) 
	{
		super(worldIn);
		this.setAirVehicle();
		this.setAIMoveSpeed(1F);
		this.setRelativePassengerPosition(0, 1, 0);
		this.setSize(4, 2);
		this.setCanHover(true);
		this.setHealth(1F);
		this.addParticles(-.8F, 1F, 0F, EnumParticleTypes.SMOKE_NORMAL, 25, .1F);
		this.addParticles(.8F, 1F, 0F, EnumParticleTypes.SMOKE_NORMAL, 25, .1F);
	}

	@Override
	public float getVehicleSpeed()
	{	
		return 0.5F;
	}
		
}
