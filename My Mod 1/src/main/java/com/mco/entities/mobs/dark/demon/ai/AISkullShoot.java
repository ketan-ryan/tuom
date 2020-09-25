package com.mco.entities.mobs.dark.demon.ai;

import java.util.Random;

import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.skull.EntityDarkSkull;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AISkullShoot extends AnimationAI<EntityDarkOpalDemon>
{

	protected EntityDarkOpalDemon demon;
	protected Animation animation;
	protected EntityLivingBase attackTarget;
	protected Random rand = new Random();
	
	public AISkullShoot(EntityDarkOpalDemon demon, Animation animation) 
	{
		super(demon);
		setMutexBits(8);
		this.demon = demon;
		this.animation = animation;
		attackTarget = null;
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
		int tick = demon.getAnimationTick();
		if(attackTarget != null)
		{
			demon.getLookHelper().setLookPositionWithEntity(attackTarget, 30, 30);
		}

		if((tick >= 20 && tick <= 24) && attackTarget != null)
		{
	        Vec3d vec3d = demon.getLook(1.0F);
	        double d2 = attackTarget.posX - (demon.posX + vec3d.x);
	        double d3 = attackTarget.getEntityBoundingBox().minY + (double)(attackTarget.height / 2.0F) - (0.5D + demon.posY + (double)(demon.height / 2.0F));
	        double d4 = attackTarget.posZ - (demon.posZ + vec3d.z);
	        demon.world.playEvent((EntityPlayer)null, 1016, new BlockPos(demon), 0);
	        EntityDarkSkull entitydarkskull = new EntityDarkSkull(demon.world, demon, d2, d3, d4);
	        entitydarkskull.posX = demon.posX + vec3d.x * 4.0D;
	        entitydarkskull.posY = demon.posY + (double)(demon.height / 2.0F) + 0.5D;
	        entitydarkskull.posZ = demon.posZ + vec3d.z * 4.0D;
	        demon.world.spawnEntity(entitydarkskull);
		}
	}
	
	@Override
	public void resetTask()
	{
		super.resetTask();
		demon.currentAnim = null;
	}
	
}
