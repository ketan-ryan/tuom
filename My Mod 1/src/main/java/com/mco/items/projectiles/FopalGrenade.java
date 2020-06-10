package com.mco.items.projectiles;

import library.LibRegistry;
import library.entities.projectile.LibEntityProjectile;
import library.items.LibItemProjectile;
import com.mco.TUOM;
import com.mco.entities.projectiles.ProjectileGrenadeEntity;
import com.mco.main.TUOMItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class FopalGrenade extends LibItemProjectile 
{

	public FopalGrenade() 
	{
		super();
		this.setCreativeTab(TUOM.tuom_tab);
	}
	
	@Override
	public Class<? extends LibEntityProjectile> getEntityProjectile() 
	{
		return ProjectileGrenadeEntity.class;
	}

}
