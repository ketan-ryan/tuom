package library.entities.mobs.entities;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class LibEntityPolarBear extends LibEntityGenericPassive {

    /** COMMON VARIABLES **/

    // Sounds
    protected SoundEvent babyAmbientSound = SoundEvents.ENTITY_POLAR_BEAR_BABY_AMBIENT;
    protected SoundEvent ambientSound = SoundEvents.ENTITY_POLAR_BEAR_AMBIENT;
    protected SoundEvent hurtSound = SoundEvents.ENTITY_POLAR_BEAR_HURT;
    protected SoundEvent deathSound = SoundEvents.ENTITY_POLAR_BEAR_DEATH;
    protected SoundEvent stepSound = SoundEvents.ENTITY_POLAR_BEAR_STEP;
    protected SoundEvent warningSound = SoundEvents.ENTITY_POLAR_BEAR_WARNING;

    // Loot table
    protected ResourceLocation lootTable = LootTableList.ENTITIES_POLAR_BEAR;

    /** COMMON METHODS **/

    // Sets the Entity's attributes
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        double maxHealth = 30.0D; // max health
        double followRange = 20.0D; // how many blocks away it will follow its target
        double movementSpeed = 0.25D; // how fast it moves normally
        double attackDamage = 6.0D; // how many half-hearts of damage it deals

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(followRange);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        this.registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(attackDamage);
    }

    // Sets the Entity's core behaviors
    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new LibEntityPolarBear.AIMeleeAttack());
        this.tasks.addTask(1, new LibEntityPolarBear.AIPanic());
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new LibEntityPolarBear.AIHurtByTarget());
        this.targetTasks.addTask(2, new LibEntityPolarBear.AIAttackPlayer());
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isChild() ? babyAmbientSound : ambientSound;
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
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(stepSound, 0.15F, 1.0F);
    }

    protected void playWarningSound() {
        if (this.warningSoundTicks <= 0) {
            this.playSound(warningSound, 1.0F, 1.0F);
            this.warningSoundTicks = 40;
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return lootTable;
    }

    /** OTHER METHODS **/

    public IAttributeInstance registerAttribute(IAttribute attribute) {
        IAttributeInstance entry = this.getEntityAttribute(attribute);
        if (entry != null) {
            return entry;
        } else {
            return this.getAttributeMap().registerAttribute(attribute);
        }
    }

    private static final DataParameter<Boolean> IS_STANDING = EntityDataManager.<Boolean>createKey(LibEntityPolarBear.class, DataSerializers.BOOLEAN);
    private float clientSideStandAnimation0;
    private float clientSideStandAnimation;
    private int warningSoundTicks;

    public LibEntityPolarBear(World worldIn) {
        super(worldIn);
        this.setSize(1.3F, 1.4F);
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(IS_STANDING, Boolean.valueOf(false));
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.world.isRemote) {
            this.clientSideStandAnimation0 = this.clientSideStandAnimation;

            if (this.isStanding()) {
                this.clientSideStandAnimation = MathHelper.clamp(this.clientSideStandAnimation + 1.0F, 0.0F, 6.0F);
            } else {
                this.clientSideStandAnimation = MathHelper.clamp(this.clientSideStandAnimation - 1.0F, 0.0F, 6.0F);
            }
        }

        if (this.warningSoundTicks > 0) {
            --this.warningSoundTicks;
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag) {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    public boolean isStanding() {
        return ((Boolean) this.dataManager.get(IS_STANDING)).booleanValue();
    }

    public void setStanding(boolean standing) {
        this.dataManager.set(IS_STANDING, Boolean.valueOf(standing));
    }

    @SideOnly(Side.CLIENT)
    public float getStandingAnimationScale(float p_189795_1_) {
        return (this.clientSideStandAnimation0 + (this.clientSideStandAnimation - this.clientSideStandAnimation0) * p_189795_1_) / 6.0F;
    }

    @Override
    protected float getWaterSlowDown() {
        return 0.98F;
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        if (livingdata instanceof LibEntityPolarBear.GroupData) {
            if (((LibEntityPolarBear.GroupData) livingdata).madeParent) {
                this.setGrowingAge(-24000);
            }
        } else {
            LibEntityPolarBear.GroupData entitypolarbear$groupdata = new LibEntityPolarBear.GroupData();
            entitypolarbear$groupdata.madeParent = true;
            livingdata = entitypolarbear$groupdata;
        }

        return super.onInitialSpawn(difficulty, livingdata);
    }

    public class AIAttackPlayer extends EntityAINearestAttackableTarget<EntityPlayer> {
        public AIAttackPlayer() {
            super(LibEntityPolarBear.this, EntityPlayer.class, 20, true, true, (Predicate) null);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            if (LibEntityPolarBear.this.isChild()) {
                return false;
            } else {
                if (super.shouldExecute()) {
                    for (LibEntityPolarBear entitypolarbear : LibEntityPolarBear.this.world.getEntitiesWithinAABB(LibEntityPolarBear.class, LibEntityPolarBear.this.getEntityBoundingBox().grow(8.0D, 4.0D, 8.0D))) {
                        if (entitypolarbear.isChild()) {
                            return true;
                        }
                    }
                }

                //commented out to keep it from cancelling other task targetting
                //LibEntityPolarBear.this.setAttackTarget((EntityLivingBase) null);
                return false;
            }
        }

        @Override
        protected double getTargetDistance() {
            return super.getTargetDistance() * 0.5D;
        }
    }

    public class AIHurtByTarget extends EntityAIHurtByTarget {
        public AIHurtByTarget() {
            super(LibEntityPolarBear.this, false);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting() {
            super.startExecuting();

            if (LibEntityPolarBear.this.isChild()) {
                this.alertOthers();
                this.resetTask();
            }
        }

        @Override
        protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn) {
            if (creatureIn instanceof LibEntityPolarBear && !creatureIn.isChild()) {
                super.setEntityAttackTarget(creatureIn, entityLivingBaseIn);
            }
        }
    }

    public class AIMeleeAttack extends EntityAIAttackMelee {
        public AIMeleeAttack() {
            super(LibEntityPolarBear.this, 1.25D, true);
        }

        @Override
        protected void checkAndPerformAttack(EntityLivingBase p_190102_1_, double p_190102_2_) {
            double d0 = this.getAttackReachSqr(p_190102_1_);

            if (p_190102_2_ <= d0 && this.attackTick <= 0) {
                this.attackTick = 20;
                this.attacker.attackEntityAsMob(p_190102_1_);
                LibEntityPolarBear.this.setStanding(false);
            } else if (p_190102_2_ <= d0 * 2.0D) {
                if (this.attackTick <= 0) {
                    LibEntityPolarBear.this.setStanding(false);
                    this.attackTick = 20;
                }

                if (this.attackTick <= 10) {
                    LibEntityPolarBear.this.setStanding(true);
                    LibEntityPolarBear.this.playWarningSound();
                }
            } else {
                this.attackTick = 20;
                LibEntityPolarBear.this.setStanding(false);
            }
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        @Override
        public void resetTask() {
            LibEntityPolarBear.this.setStanding(false);
            super.resetTask();
        }

        @Override
        protected double getAttackReachSqr(EntityLivingBase attackTarget) {
            return (double) (4.0F + attackTarget.width);
        }
    }

    public class AIPanic extends EntityAIPanic {
        public AIPanic() {
            super(LibEntityPolarBear.this, 2.0D);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            return !LibEntityPolarBear.this.isChild() && !LibEntityPolarBear.this.isBurning() ? false : super.shouldExecute();
        }
    }

    public static class GroupData implements IEntityLivingData {
        public boolean madeParent;

        private GroupData() {
        }
    }
}