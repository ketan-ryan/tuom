package com.mco.entities.projectiles;

import library.entities.projectile.LibEntityArrow;
import com.mco.main.TUOMItems;
import net.minecraft.item.Item;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityTopazArrow extends LibEntityArrow 
{
	public EntityTopazArrow(World world) 
	{
		super(world);
		this.setDamage(7);
	//	this.setKnockbackStrength(2);
	}

	@Override
	public Item getItemProjectile() 
	{
		return TUOMItems.topaz_arrow;
	}

	@Override
	protected void onHit(RayTraceResult rayTraceResultIn) 
	{
		if(rayTraceResultIn.entityHit != null) 
		{
		//	rayTraceResultIn.entityHit.setGlowing(true);
		//	rayTraceResultIn.entityHit.setCustomNameTag("Topaz Struck");
		}
		//this.setDead();
		super.onHit(rayTraceResultIn);
	}
		
}
