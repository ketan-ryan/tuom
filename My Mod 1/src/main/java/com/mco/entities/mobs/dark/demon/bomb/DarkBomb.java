package com.mco.entities.mobs.dark.demon.bomb;

import library.entities.projectile.LibEntityProjectile;
import library.items.LibItemProjectile;

public class DarkBomb extends LibItemProjectile
{
	public DarkBomb() 
	{
		super();
	}
	
	@Override
	public Class<? extends LibEntityProjectile> getEntityProjectile() 
	{
		return EntityProjectileDarkBomb.class;
	}
}
