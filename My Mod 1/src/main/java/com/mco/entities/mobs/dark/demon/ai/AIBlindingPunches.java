package com.mco.entities.mobs.dark.demon.ai;

import java.util.Random;

import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.main.TUOMConfig;
import com.mco.main.TUOMDamageSources;

import library.util.Actions;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;

public class AIBlindingPunches extends AnimationAI<EntityDarkOpalDemon>
{
	protected Animation animation;
	protected EntityLivingBase attackTarget;
	protected EntityDarkOpalDemon demon;
	
	public AIBlindingPunches(EntityDarkOpalDemon demon, Animation animation) 
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
		
		if(demon.getAnimationTick() < 120 && attackTarget != null)
		{
			demon.getLookHelper().setLookPositionWithEntity(attackTarget, 30, 30);
		}
		
		if(attackTarget != null)
		{
			//Disable hurt resistance so it can take rapid - fire punches
			attackTarget.hurtResistantTime = 0;
		
			if(demon.getAnimationTick() == 1) {
				this.teleportToEntity(demon, attackTarget);
			}
			
			if(demon.getAnimationTick() == 21)
			{
				this.teleportToEntity(demon, attackTarget);
				attackTarget.attackEntityFrom(TUOMDamageSources.darkPunch, 7 * TUOMConfig.darkDamage);
				Actions.addPotionEffect(attackTarget, MobEffects.SLOWNESS, 120, 20, false);
			}
			
			if(demon.getAnimationTick() == 29 || demon.getAnimationTick() == 35 || demon.getAnimationTick() == 41) {
				this.teleportToEntity(demon, attackTarget);
				attackTarget.attackEntityFrom(TUOMDamageSources.darkPunch, 5 * TUOMConfig.darkDamage);
			}
			
			//Uppercut
			if(demon.getAnimationTick() == 47) {
				this.teleportToEntity(demon, attackTarget);
				attackTarget.attackEntityFrom(TUOMDamageSources.darkPunch, 3 * TUOMConfig.darkDamage);
				attackTarget.motionY += 3.4000000059604645D;
			}
			
			//Jump to meet the player in midair
			if(demon.getAnimationTick() == 70)
				demon.motionY += 2.4000000059604645D;
			
			//Resistance so the player doesn't get one-shot from uppercut
			if(demon.getAnimationTick() == 100 && demon.getDistance(attackTarget) < 10) {
				attackTarget.attackEntityFrom(TUOMDamageSources.darkPunch, 10 * TUOMConfig.darkDamage);
				attackTarget.knockBack(demon, 3, 1, 1);
				Actions.addPotionEffect(attackTarget, MobEffects.RESISTANCE, 30, 100, false);
			}
		}
		
	}
	
	@Override
	public void resetTask()
	{
		super.resetTask();
		demon.currentAnim = null;
	}
	
	/**
	 * Teleports demon to target and plays pitched-down teleport sound
	 * */
	private void teleportToEntity(EntityLivingBase entityToTp, EntityLivingBase target)
	{
        demon.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 10.0F, 0.1F);
		entityToTp.setPosition(target.posX + new Random().nextInt(3), target.posY, target.posZ + new Random().nextInt(3));
	}
	
}
