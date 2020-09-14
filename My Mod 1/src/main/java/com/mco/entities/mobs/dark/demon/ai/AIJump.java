package com.mco.entities.mobs.dark.demon.ai;

import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.main.TUOMConfig;
import com.mco.main.TUOMDamageSources;
import com.mco.main.TUOMSoundHandler;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;

public class AIJump extends AnimationAI<EntityDarkOpalDemon>
{
	protected Animation animation;
	protected EntityLivingBase attackTarget;
	protected EntityDarkOpalDemon demon;
	
	public AIJump(EntityDarkOpalDemon demon, Animation animation) 
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
		
		if(demon.getAnimationTick() < 90 && attackTarget != null)
		{
			demon.getLookHelper().setLookPositionWithEntity(attackTarget, 30, 30);
		}
		
		//Jump through blocks
		if(demon.getAnimationTick() > 20 && demon.getAnimationTick() < 60)
			demon.noClip = true;
		else if(demon.getAnimationTick() >= 60)
			demon.noClip = false;
		
		//Takeoff sound
		if(demon.getAnimationTick() == 10 && demon.getAttackTarget() != null){
			demon.playSound(TUOMSoundHandler.DARK_OPAL_TAKEOFF, 3, 1);
          
		//Land sound
		}else if(demon.getAnimationTick() == 70 && demon.getAttackTarget() != null){
			demon.playSound(TUOMSoundHandler.DARK_OPAL_LANDING, 3, 1);
		}
		
		//Wing flap on ascent
		if(demon.getAnimationTick() > 15 && demon.getAnimationTick() < 55 && demon.ticksExisted % 7 == 0)
			demon.playSound(TUOMSoundHandler.DARK_OPAL_FLAP, 10, 1);
		
		//Wing flutter on descent
		else if(demon.getAnimationTick() > 40 && demon.getAnimationTick() < 70 && demon.ticksExisted % 5 == 0) {
			demon.playSound(TUOMSoundHandler.DARK_OPAL_FLUTTER, 2, 1);
		}
		
		if(demon.getAnimationTick() > 40 && demon.getAnimationTick() < 70 && attackTarget != null) 
		{
			double d0 = demon.getDistance(attackTarget);
			double xTarget = attackTarget.posX;
			double zTarget = attackTarget.posZ;

			//If player is within 48 blocks, demon will land on player's face
			if(d0 < 48) 
			{
				if (demon.posX < xTarget)
	                demon.motionX += .1;
	            else if (demon.posX > xTarget)
	                demon.motionX -= .1;
	
	            if (demon.posZ < zTarget)
	                demon.motionZ += .1;
	            else if (demon.posZ > zTarget)
	                demon.motionZ -= .1;
            }
		}
	
		//Damage on impact
		if(demon.getAnimationTick() == 85)
		{
			if(attackTarget != null && demon.targetDistance <= 10)
			{
				attackTarget.attackEntityFrom(TUOMDamageSources.darkPunch, 10 * TUOMConfig.darkDamage);
			}
			
		}

		//Jump
		if(demon.getAnimationTick() == 20)
		{
			demon.motionY += 3;
		}
		
	}
	
	@Override
	public void resetTask()
	{
		super.resetTask();
		demon.currentAnim = null;
	}
		
}
	

