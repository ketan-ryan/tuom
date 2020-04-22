package com.mco.entities.mobs.dark.demon.ai;

import java.util.Random;

import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import library.util.Actions;
import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.main.TUOMDamageSources;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;

public class AIShield extends AnimationAI<EntityDarkOpalDemon>
{
	protected Animation animation;
	protected EntityLivingBase attackTarget;
	protected EntityDarkOpalDemon demon;
	
	public AIShield(EntityDarkOpalDemon demon, Animation animation) 
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
		
		if(demon.getAnimationTick() == 40) 
		{	
			demon.setArmored(true);
		}

	}
	
	@Override
	public void resetTask()
	{
		super.resetTask();
		demon.currentAnim = null;
	}	
}
