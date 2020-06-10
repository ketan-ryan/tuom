package com.mco.items.archery;

import com.mco.entities.projectiles.EntityTopazArrow;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;

public class TopazArrow extends LibItemArrow 
{
	public TopazArrow() 
	{
		super();
	}
	
	@Override
	public Class<? extends EntityTippedArrow> getEntityProjectile() 
	{
		return EntityTopazArrow.class;
	}
}
