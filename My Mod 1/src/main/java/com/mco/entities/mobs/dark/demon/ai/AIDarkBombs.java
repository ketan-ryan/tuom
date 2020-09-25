package com.mco.entities.mobs.dark.demon.ai;

import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.bomb.EntityProjectileDarkBomb;
import com.mco.main.TUOMSoundHandler;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.SoundCategory;

public class AIDarkBombs extends AnimationAI
{
	protected Animation animation;
	protected EntityLivingBase attackTarget;
	protected EntityDarkOpalDemon demon;
	
	public AIDarkBombs(EntityDarkOpalDemon demon, Animation animation) 
	{
		super(demon);
		setMutexBits(8);
		this.demon = demon;
		this.animation = animation;
		this.attackTarget = null;
	}

	@Override
	public Animation getAnimation() 
	{
		return animation;
	}

	@Override
	public boolean isAutomatic()
	{
		return true;
	}
	
	@Override
	public void startExecuting()
	{
		super.startExecuting();
		demon.currentAnim = this;
		attackTarget = demon.getAttackTarget();
	}
		
	@Override
	public void updateTask()
	{
		super.updateTask();	

		if(demon.getAnimationTick() < 100 && attackTarget != null)
		{
			demon.getLookHelper().setLookPositionWithEntity(attackTarget, 30, 30);
		}
		
		if(demon.getAnimationTick() == 20 && !demon.world.isRemote)
		{
			demon.world.playSound(demon.posX, demon.posY, demon.posZ, TUOMSoundHandler.DARK_OPAL_BOMB_LAUNCH, SoundCategory.MASTER, 5, 1, false);
			for (int i = 0; i < 360; i += 60) 
			{
				EntityProjectileDarkBomb bomb = new EntityProjectileDarkBomb(demon.world);
				bomb.motionX = Math.sin((float)Math.toRadians(i)) * 2 / 8;
				bomb.motionZ = Math.cos((float)Math.toRadians(i)) * 2 / 8;
				bomb.motionY = 1F;
				bomb.setPosition(demon.posX, demon.posY + 10, demon.posZ);
				demon.world.spawnEntity(bomb);
			}
		}	
	}
	
	@Override
	public void resetTask()
	{
		super.resetTask();
		demon.currentAnim = null;
	}	
	
}
