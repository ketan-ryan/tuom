package com.mco.energy;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class UltimateEnergyStorage implements IStorage<IUltimateEnergy>
{

	@Override
	public NBTBase writeNBT(Capability<IUltimateEnergy> capability, IUltimateEnergy instance, EnumFacing side)
	{
		return new NBTTagFloat(instance.getEnergy());
	}

	@Override
	public void readNBT(Capability<IUltimateEnergy> capability, IUltimateEnergy instance, EnumFacing side, NBTBase nbt)
	{
		instance.set(((NBTPrimitive) nbt).getFloat()); 
	}
	
}
