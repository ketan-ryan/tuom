package com.mco.energy;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/** 
* Ultimate Energy provider 
* 
* This class is responsible for providing a capability. Other modders may 
* attach their own provider with implementation that returns another 
* implementation of IUltimateEnergy to the target's (Entity, TE, ItemStack, etc.) disposal. 
*/ 
public class UltimateEnergyProvider implements ICapabilitySerializable<NBTBase>
{

	@CapabilityInject(IUltimateEnergy.class)
	public static final Capability<IUltimateEnergy> ULTIMATE_CAP = null;
	
	private IUltimateEnergy instance = ULTIMATE_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == ULTIMATE_CAP;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == ULTIMATE_CAP ? ULTIMATE_CAP.<T> cast(this.instance) : null;
	}
	
	@Override
	public NBTBase serializeNBT()
	{
		return ULTIMATE_CAP.getStorage().writeNBT(ULTIMATE_CAP, this.instance, null);
	}
	
	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		ULTIMATE_CAP.getStorage().readNBT(ULTIMATE_CAP, this.instance, null, nbt);
	}
	
}
