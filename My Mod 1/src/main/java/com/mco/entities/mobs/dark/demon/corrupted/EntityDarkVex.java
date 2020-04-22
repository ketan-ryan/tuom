package com.mco.entities.mobs.dark.demon.corrupted;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityDarkVex extends EntityMob
{
    private EntityLiving owner;
	
	public EntityDarkVex(World world) 
	{
		super(world);
		this.isImmuneToFire = true;
		this.setSize(2F, 3F);
        this.moveHelper = new EntityDarkVex.AIMoveControl(this);
        this.experienceValue = 12;
	}

	/**
     * Tries to move the entity towards the specified location.
     */
    public void move(MoverType type, double x, double y, double z)
    {
        super.move(type, x, y, z);
        this.doBlockCollisions();
    }
    
    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityDarkVex.AIChargeAttack());
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityDarkVex.class}));
        this.targetTasks.addTask(2, new EntityDarkVex.AICopyOwnerTarget(this));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
    }
    
    public EntityLiving getOwner()
    {
        return this.owner;
    }
    
    public void setOwner(EntityLiving ownerIn)
    {
        this.owner = ownerIn;
    }
    
    public void fall(float distance, float damageMultiplier){}
    
    class AIChargeAttack extends EntityAIBase
    {
        public AIChargeAttack()
        {
            this.setMutexBits(1);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            if (EntityDarkVex.this.getAttackTarget() != null && !EntityDarkVex.this.getMoveHelper().isUpdating() && EntityDarkVex.this.rand.nextInt(7) == 0)
            {
                return EntityDarkVex.this.getDistanceSq(EntityDarkVex.this.getAttackTarget()) > 8.0D;
            }
            else
            {
                return false;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return EntityDarkVex.this.getMoveHelper().isUpdating() && EntityDarkVex.this.getAttackTarget() != null && EntityDarkVex.this.getAttackTarget().isEntityAlive();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            EntityLivingBase entitylivingbase = EntityDarkVex.this.getAttackTarget();
            Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
            EntityDarkVex.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
            EntityDarkVex.this.playSound(SoundEvents.ENTITY_VEX_CHARGE, 1.0F, 1.0F);
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask()
        {
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = EntityDarkVex.this.getAttackTarget();

            if (EntityDarkVex.this.getEntityBoundingBox().intersects(entitylivingbase.getEntityBoundingBox()))
            {
                EntityDarkVex.this.attackEntityAsMob(entitylivingbase);
            }
            else
            {
                double d0 = EntityDarkVex.this.getDistanceSq(entitylivingbase);

                if (d0 < 9.0D)
                {
                    Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
                    EntityDarkVex.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
                }
            }
        }
    }

    class AICopyOwnerTarget extends EntityAITarget
    {
        public AICopyOwnerTarget(EntityCreature creature)
        {
            super(creature, false);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return EntityDarkVex.this.owner != null && EntityDarkVex.this.owner.getAttackTarget() != null && this.isSuitableTarget(EntityDarkVex.this.owner.getAttackTarget(), false);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            EntityDarkVex.this.setAttackTarget(EntityDarkVex.this.owner.getAttackTarget());
            super.startExecuting();
        }
    }

    class AIMoveControl extends EntityMoveHelper
    {
        public AIMoveControl(EntityDarkVex vex)
        {
            super(vex);
        }

        public void onUpdateMoveHelper()
        {
            if (this.action == EntityMoveHelper.Action.MOVE_TO)
            {
                double d0 = this.posX - EntityDarkVex.this.posX;
                double d1 = this.posY - EntityDarkVex.this.posY;
                double d2 = this.posZ - EntityDarkVex.this.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = (double)MathHelper.sqrt(d3);

                if (d3 < EntityDarkVex.this.getEntityBoundingBox().getAverageEdgeLength())
                {
                    this.action = EntityMoveHelper.Action.WAIT;
                    EntityDarkVex.this.motionX *= 0.5D;
                    EntityDarkVex.this.motionY *= 0.5D;
                    EntityDarkVex.this.motionZ *= 0.5D;
                }
                else
                {
                    EntityDarkVex.this.motionX += d0 / d3 * 0.05D * this.speed;
                    EntityDarkVex.this.motionY += d1 / d3 * 0.05D * this.speed;
                    EntityDarkVex.this.motionZ += d2 / d3 * 0.05D * this.speed;

                    if (EntityDarkVex.this.getAttackTarget() == null)
                    {
                        EntityDarkVex.this.rotationYaw = -((float)MathHelper.atan2(EntityDarkVex.this.motionX, EntityDarkVex.this.motionZ)) * (180F / (float)Math.PI);
                        EntityDarkVex.this.renderYawOffset = EntityDarkVex.this.rotationYaw;
                    }
                    else
                    {
                        double d4 = EntityDarkVex.this.getAttackTarget().posX - EntityDarkVex.this.posX;
                        double d5 = EntityDarkVex.this.getAttackTarget().posZ - EntityDarkVex.this.posZ;
                        EntityDarkVex.this.rotationYaw = -((float)MathHelper.atan2(d4, d5)) * (180F / (float)Math.PI);
                        EntityDarkVex.this.renderYawOffset = EntityDarkVex.this.rotationYaw;
                    }
                }
            }
        }
    }

    
}
