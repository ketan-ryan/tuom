package com.mco.entities.mobs.dark.demon.ai;

import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.main.TUOMConfig;
import com.mco.main.TUOMDamageSources;
import library.util.Actions;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;

public class AILifedrain extends AnimationAI
{
	protected Animation animation;
	protected EntityLivingBase attackTarget;
	protected EntityDarkOpalDemon demon;
	
	public AILifedrain(EntityDarkOpalDemon demon, Animation animation) 
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

		if(demon.getDistance(attackTarget) < 32 && attackTarget != null) 
		{
			attackTarget.hurtResistantTime = 7;
			attackTarget.attackEntityFrom(TUOMDamageSources.darkLifedrain, 0.2F * TUOMConfig.darkDamage);
			Actions.addPotionEffect(attackTarget, MobEffects.SLOWNESS, 1, 10, false);
			demon.heal(.6F);
		}
	}
	
	@Override
	public void resetTask()
	{
		super.resetTask();
		demon.currentAnim = null;
	}	
}
