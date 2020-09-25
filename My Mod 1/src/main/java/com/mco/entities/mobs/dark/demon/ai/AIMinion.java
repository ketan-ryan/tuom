package com.mco.entities.mobs.dark.demon.ai;

import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.corrupted.EntityDarkVex;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class AIMinion extends AnimationAI
{
	protected Animation animation;
	protected EntityLivingBase attackTarget;
	protected EntityDarkOpalDemon demon;
	
	public AIMinion(EntityDarkOpalDemon demon, Animation animation) 
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
		
		if(demon.getAnimationTick() == 36)
		{
			EntityDarkVex backLeft = new EntityDarkVex(demon.world);
			EntityDarkVex backRight = new EntityDarkVex(demon.world);
			EntityDarkVex frontLeft = new EntityDarkVex(demon.world);
			EntityDarkVex frontRight = new EntityDarkVex(demon.world);
			
			backLeft.setPosition(demon.posX - 2, demon.posY - 1, demon.posZ - 2);
			backLeft.motionY = 1;
			
			backRight.setPosition(demon.posX - 2, demon.posY - 1, demon.posZ + 2);
			backRight.motionY = 1;
			
			frontLeft.setPosition(demon.posX + 2, demon.posY - 1, demon.posZ - 2);
			frontLeft.motionY = 1;
			
			frontRight.setPosition(demon.posX + 2, demon.posY - 1, demon.posZ + 2);
			frontRight.motionY = 1;
			
			demon.world.spawnEntity(backLeft);
			demon.world.spawnEntity(backRight);
			demon.world.spawnEntity(frontLeft);
			demon.world.spawnEntity(frontRight);

			if(!demon.world.isRemote)
				this.shakeNearbyPlayerCameras(32);			
		}	
	}
	
	public void shakeNearbyPlayerCameras(double distance)
	{
	    if (!demon.world.loadedEntityList.isEmpty() && !demon.world.isRemote)
	    {
	            for (int l1 = 0; l1 < demon.world.loadedEntityList.size(); ++l1)
	            {
	                Entity entity = (Entity)demon.world.loadedEntityList.get(l1);

	                if (entity != null && entity.dimension == demon.dimension && entity.isEntityAlive() && entity instanceof 
	                		EntityLivingBase && !(entity instanceof EntityDarkOpalDemon) && entity.getDistance(demon) < distance && !entity.world.isRemote){
	                    try
	                    {
	                        ReflectionHelper.findField(entity.getClass(), new String[] { "hurt_timer" }).setInt(entity, 0);
	                    }
	                    catch (Exception e)
	                    {
	                        entity.hurtResistantTime = 0;
	                    }
	                    entity.hurtResistantTime = 0;
	                    demon.world.setEntityState((EntityLivingBase)entity, (byte)2);
	                }
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
