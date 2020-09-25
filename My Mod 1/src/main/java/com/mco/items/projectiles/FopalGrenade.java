package com.mco.items.projectiles;

import com.mco.entities.projectiles.FopalGrenadeEntity;

import library.entities.projectile.LibEntityProjectile;
import library.items.LibItemProjectile;

public class FopalGrenade extends LibItemProjectile 
{

	public FopalGrenade() 
	{
		super();
	}
	
	@Override
	public Class<? extends LibEntityProjectile> getEntityProjectile() 
	{
		return FopalGrenadeEntity.class;
	}

}
