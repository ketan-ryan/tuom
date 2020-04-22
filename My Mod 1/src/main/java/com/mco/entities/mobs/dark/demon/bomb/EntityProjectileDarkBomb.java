package com.mco.entities.mobs.dark.demon.bomb;

import library.entities.projectile.LibEntityProjectile;
import library.util.Actions;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileDarkBomb extends LibEntityProjectile
{

	public EntityProjectileDarkBomb(World worldIn) 
	{
		super(worldIn);
	}

	public void onUpdate()
	{
		super.onUpdate();
		Actions.spawnParticleAtEntity(this, EnumParticleTypes.DRAGON_BREATH, 2, 1);
	}
	
	@Override
	protected void onImpact(RayTraceResult result) 
	{
		super.onImpact(result);

		Actions.spawnEntity(world, EntityDarkBomb.class, this.getPosition());

		this.setDead();
	}
	
}
