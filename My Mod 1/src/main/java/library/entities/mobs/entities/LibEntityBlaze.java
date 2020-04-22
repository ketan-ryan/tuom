package library.entities.mobs.entities;

import library.entities.LibEntityMob;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class LibEntityBlaze extends LibEntityMob<LibEntityBlaze> {

    /** COMMON VARIABLES **/

    // Sounds
    protected SoundEvent ambientSound = SoundEvents.ENTITY_BLAZE_AMBIENT;
    protected SoundEvent hurtSound = SoundEvents.ENTITY_BLAZE_HURT;
    protected SoundEvent deathSound = SoundEvents.ENTITY_BLAZE_DEATH;

    // Loot table
    protected ResourceLocation lootTable = LootTableList.ENTITIES_BLAZE;

    // Random offset used in floating behaviour
    protected float heightOffset = 0.5F;


    /** COMMON METHODS **/

    // Sets the Entity's attributes
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        double followRange = 48.0D; // how many blocks away it will follow its target
        double attackDamage = 6.0D; // how many half-hearts of damage it deals
        double movementSpeed = 0.23D; // how fast it moves normally

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(followRange);
    }

    // Sets the Entity's core behaviors
    @Override
    protected void initEntityAI() {
        float maxStartDistance = 8.0F; // number of blocks away it will look at entities it watches
        Class<EntityPlayer> watchClosest = EntityPlayer.class; // which entity types it will watch and attack

        this.tasks.addTask(4, new LibEntityBlaze.AIFireballAttack(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D, 0.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, watchClosest, maxStartDistance));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, watchClosest, true));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ambientSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return hurtSound;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return deathSound;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return lootTable;
    }

    /** INHERITED OVERRIDABLE METHODS **/

    //     protected void dropItems(DamageSource source)


    /** OTHER METHODS **/

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ON_FIRE, Byte.valueOf((byte) 0));
    }

    /**
     * ticks until heightOffset is randomized
     */
    private int heightOffsetUpdateTime;
    private static final DataParameter<Byte> ON_FIRE = EntityDataManager.<Byte>createKey(LibEntityBlaze.class, DataSerializers.BYTE);

    public LibEntityBlaze(World worldIn) {
        super(worldIn);
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.setPathPriority(PathNodeType.LAVA, 8.0F);
        this.setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
        //this.isImmuneToFire = true;
        this.experienceValue = 10;
    }



    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender() {
        return 15728880;
    }

    /**
     * Gets how bright this entity is.
     */
    @Override
    public float getBrightness() {
        return 1.0F;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate() {
        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }

        if (this.world.isRemote) {
            if (this.rand.nextInt(24) == 0 && !this.isSilent()) {
                this.world.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, SoundEvents.ENTITY_BLAZE_BURN, this.getSoundCategory(), 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
            }

            for (int i = 0; i < 2; ++i) {
                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, 0.0D, 0.0D, 0.0D);
            }
        }

        super.onLivingUpdate();
    }

    @Override
    protected void updateAITasks() {
        if (this.isWet()) {
            this.attackEntityFrom(DamageSource.DROWN, 1.0F);
        }

        --this.heightOffsetUpdateTime;

        if (this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5F + (float) this.rand.nextGaussian() * 3.0F;
        }

        EntityLivingBase entitylivingbase = this.getAttackTarget();

        if (entitylivingbase != null && entitylivingbase.posY + (double) entitylivingbase.getEyeHeight() > this.posY + (double) this.getEyeHeight() + (double) this.heightOffset) {
            this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            this.isAirBorne = true;
        }

        super.updateAITasks();
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    @Override
    public boolean isBurning() {
        return super.isBurning();/*this.isCharged();*/
    }

    public boolean isCharged() {
        return (this.dataManager.get(ON_FIRE).byteValue() & 1) != 0;
    }

    public void setOnFire(boolean onFire) {
        byte b0 = this.dataManager.get(ON_FIRE).byteValue();

        if (onFire) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 = (byte) (b0 & -2);
        }

        this.dataManager.set(ON_FIRE, Byte.valueOf(b0));
    }

    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    public static class AIFireballAttack extends EntityAIBase {
        private final LibEntityBlaze blaze;
        private int attackStep;
        private int attackTime;

        public AIFireballAttack(LibEntityBlaze blazeIn) {
            this.blaze = blazeIn;
            this.setMutexBits(3);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.blaze.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting() {
            this.attackStep = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        @Override
        public void resetTask() {
            this.blaze.setOnFire(false);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask() {
            --this.attackTime;
            EntityLivingBase entitylivingbase = this.blaze.getAttackTarget();
            double d0 = this.blaze.getDistanceSq(entitylivingbase);

            if (d0 < 4.0D) {
                if (this.attackTime <= 0) {
                    this.attackTime = 20;
                    this.blaze.attackEntityAsMob(entitylivingbase);
                }

                this.blaze.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            } else if (d0 < this.getFollowDistance() * this.getFollowDistance()) {
                double d1 = entitylivingbase.posX - this.blaze.posX;
                double d2 = entitylivingbase.getEntityBoundingBox().minY + (double) (entitylivingbase.height / 2.0F) - (this.blaze.posY + (double) (this.blaze.height / 2.0F));
                double d3 = entitylivingbase.posZ - this.blaze.posZ;

                if (this.attackTime <= 0) {
                    ++this.attackStep;

                    if (this.attackStep == 1) {
                        this.attackTime = 60;
                        this.blaze.setOnFire(true);
                    } else if (this.attackStep <= 4) {
                        this.attackTime = 6;
                    } else {
                        this.attackTime = 100;
                        this.attackStep = 0;
                        this.blaze.setOnFire(false);
                    }

                    if (this.attackStep > 1) {
                        float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                        this.blaze.world.playEvent(null, 1018, new BlockPos((int) this.blaze.posX, (int) this.blaze.posY, (int) this.blaze.posZ), 0);

                        for (int i = 0; i < 1; ++i) {
                            EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.blaze.world, this.blaze, d1 + this.blaze.getRNG().nextGaussian() * (double) f, d2, d3 + this.blaze.getRNG().nextGaussian() * (double) f);
                            entitysmallfireball.posY = this.blaze.posY + (double) (this.blaze.height / 2.0F) + 0.5D;
                            this.blaze.world.spawnEntity(entitysmallfireball);
                        }
                    }
                }

                this.blaze.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            } else {
                this.blaze.getNavigator().clearPath();
                this.blaze.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }

            super.updateTask();
        }

        private double getFollowDistance() {
            IAttributeInstance iattributeinstance = this.blaze.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
            return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
        }
    }
}