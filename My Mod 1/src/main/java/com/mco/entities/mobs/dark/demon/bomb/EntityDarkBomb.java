package com.mco.entities.mobs.dark.demon.bomb;

import library.entities.LibEntityMob;
import library.particle.ParticleBehavior;
import library.particle.ParticleShape;
import library.util.Actions;
import com.mco.main.TUOMSoundHandler;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class EntityDarkBomb extends LibEntityMob
{

	public EntityDarkBomb(World world) 
	{
		super(world);
	}

	public void onUpdate()
	{
		if(this.ticksExisted == 70)
		{
			world.createExplosion(this, this.posX, this.posY, this.posZ, 5, false);
			this.setDead();
		}
	}
	
	@Override
	public void onFirstSpawn()
	{
		Actions.spawnParticleBurstAtEntity(world, this, EnumParticleTypes.SMOKE_LARGE, ParticleShape.SPHERE, ParticleBehavior.VORTEX, 3, 70);
		world.playSound(this.posX, this.posY, this.posZ, TUOMSoundHandler.darkOpalBomb, SoundCategory.MASTER, 7, .1F, false);
	}
	
}
