package com.mco.entities.mobs.dark.demon.laser;

import library.entities.projectile.LibEntityProjectile;
import library.util.Actions;
import com.mco.main.TUOMDamageSources;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLaserProjectile extends LibEntityProjectile
{

	public EntityLaserProjectile(World worldIn)
    {
        super(worldIn);
        this.setSize(1, 1);
    }

    public static void registerFixesLaserProjectile(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "LaserProjectile");
    }

 /*   *//**
     * Gets the amount of gravity to apply to the thrown entity with each tick.
     *//*
    protected float getGravityVelocity()
    {
        return 0.01F;
    }*/
    
    @Override
	protected void onImpact(RayTraceResult result) 
	{
		super.onImpact(result);
		
		if(result.entityHit != null)
			result.entityHit.attackEntityFrom(TUOMDamageSources.darkLaser, 10F);

		this.setDead();
	}
}
