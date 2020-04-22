package com.mco.entities.projectiles;

import library.entities.projectile.LibEntityProjectile;
import library.util.Actions;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ProjectileGrenadeEntity extends LibEntityProjectile 
{

	public ProjectileGrenadeEntity(World worldIn) 
	{
		super(worldIn);
	}

	@Override
	protected void onImpact(RayTraceResult result) 
	{
		super.onImpact(result);

		Actions.spawnParticleAtEntity(this, EnumParticleTypes.FLAME, 20, 2);
		this.world.newExplosion((Entity)null, this.posX, this.posY, this.posZ, 7F, true, true);

		this.setDead();
	}
	
}
