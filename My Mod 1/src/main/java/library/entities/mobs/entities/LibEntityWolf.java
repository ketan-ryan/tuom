package library.entities.mobs.entities;

import com.google.common.base.Predicate;
import library.ai.LibEntityAIBeg;
import library.entities.LibEntityTameable;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class LibEntityWolf extends LibEntityTameable {

    /** COMMON VARIABLES **/

    // Sounds
    protected SoundEvent shakeSound = SoundEvents.ENTITY_WOLF_SHAKE;
    protected SoundEvent stepSound = SoundEvents.ENTITY_WOLF_STEP;
    protected SoundEvent growlSound = SoundEvents.ENTITY_WOLF_GROWL;
    protected SoundEvent pantSound = SoundEvents.ENTITY_WOLF_PANT;
    protected SoundEvent whineSound = SoundEvents.ENTITY_WOLF_WHINE;
    protected SoundEvent ambientSound = SoundEvents.ENTITY_WOLF_AMBIENT;
    protected SoundEvent hurtSound = SoundEvents.ENTITY_WOLF_HURT;
    protected SoundEvent deathSound = SoundEvents.ENTITY_WOLF_DEATH;

    // Loot table
    protected ResourceLocation lootTable = LootTableList.ENTITIES_WOLF;

    // Tameable item
    protected Item tameableItem = Items.BONE;

    protected float soundVolume = 0.4F;

    /** COMMON METHODS **/

    // Sets the Entity's attributes
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        double maxHealthTamed = 20.0D; // max health
        double maxHealthWild = 8.0D; // max health
        double movementSpeed = 0.3D; // how fast it moves normally
        double attackDamage = 2.0D; // how many half-hearts of damage it deals

        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(movementSpeed);

        if (this.isTamed()) {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealthTamed);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealthWild);
        }

        this.registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(attackDamage);
    }

    // Sets the Entity's core behaviors
    @Override
    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityLlama.class, 24.0F, 1.5D, 1.5D));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(9, new LibEntityAIBeg(this, 8.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(10, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate<Entity>() {
            @Override
            public boolean apply(@Nullable Entity p_apply_1_) {
                return p_apply_1_ instanceof LibEntitySheep || p_apply_1_ instanceof EntityRabbit;
            }
        }));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, AbstractSkeleton.class, false));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isAngry()) {
            return growlSound;
        } else if (this.rand.nextInt(3) == 0) {
            return this.isTamed() && this.dataManager.get(DATA_HEALTH_ID).floatValue() < 10.0F ? whineSound : pantSound;
        } else {
            return ambientSound;
        }
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


    /** OTHER METHODS **/

    public IAttributeInstance registerAttribute(IAttribute attribute) {
        IAttributeInstance entry = this.getEntityAttribute(attribute);
        if (entry != null) {
            return entry;
        } else {
            return this.getAttributeMap().registerAttribute(attribute);
        }
    }

    /**
     * Override this method to change the tameable item
     */
    protected boolean isTameableItem(ItemStack itemstack) {
        return itemstack.getItem() == tameableItem;
    }

    /**
     * Override this method to make some creatures not attackable by the wolf
     */
    protected boolean isAttackableCreature(EntityLivingBase target) {
        return !(target instanceof EntityCreeper) && !(target instanceof EntityGhast);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume() {
        return soundVolume;
    }


    /**
     * Core Entity Methods
     **/

    private Vec3d playerPosition = new Vec3d(0, 0, 0);

    private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.<Float>createKey(EntityWolf.class, DataSerializers.FLOAT);
    private static final DataParameter<Boolean> BEGGING = EntityDataManager.<Boolean>createKey(EntityWolf.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> COLLAR_COLOR = EntityDataManager.<Integer>createKey(EntityWolf.class, DataSerializers.VARINT);
    /**
     * Float used to smooth the rotation of the wolf head
     */
    private float headRotationCourse;
    private float headRotationCourseOld;
    /**
     * true is the wolf is wet else false
     */
    private boolean isWet;
    /**
     * True if the wolf is shaking else False
     */
    private boolean isShaking;
    /**
     * This time increases while wolf is shaking and emitting water particles.
     */
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;

    private boolean canSit = false;

    public boolean canSit() {
        return canSit;
    }

    public void enableSitting() {
        this.canSit = true;
    }

    public void disableSitting() {
        this.canSit = false;
    }

    public LibEntityWolf(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 0.85F);
        this.setTamed(false);
    }

    /**
     * Sets the active target the Task system uses for tracking
     */
    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn);

        if (entitylivingbaseIn == null) {
            this.setAngry(false);
        } else if (!this.isTamed()) {
            this.setAngry(true);
        }
    }

    @Override
    protected void updateAITasks() {
        this.dataManager.set(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
        this.dataManager.register(BEGGING, Boolean.valueOf(false));
        this.dataManager.register(COLLAR_COLOR, Integer.valueOf(EnumDyeColor.RED.getDyeDamage()));
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(stepSound, 0.15F, 1.0F);
    }

    public static void registerFixesWolf(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntityWolf.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Angry", this.isAngry());
        compound.setByte("CollarColor", (byte) this.getCollarColor().getDyeDamage());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setAngry(compound.getBoolean("Angry"));

        if (compound.hasKey("CollarColor", 99)) {
            this.setCollarColor(EnumDyeColor.byDyeDamage(compound.getByte("CollarColor")));
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate() {
        updateArmSwingProgress();
        if (!canSit && this.aiSit != null) {
            this.aiSit.setSitting(false);
        }
        super.onLivingUpdate();

        if (!this.world.isRemote && this.isWet && !this.isShaking && !this.hasPath() && this.onGround) {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
            this.world.setEntityState(this, (byte) 8);
        }

        if (!this.world.isRemote && this.getAttackTarget() == null && this.isAngry()) {
            this.setAngry(false);
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        super.onUpdate();
        this.headRotationCourseOld = this.headRotationCourse;

        if (this.isBegging()) {
            this.headRotationCourse += (1.0F - this.headRotationCourse) * 0.4F;
        } else {
            this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;
        }

        if (this.isWet()) {
            this.isWet = true;
            this.isShaking = false;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        } else if ((this.isWet || this.isShaking) && this.isShaking) {
            if (this.timeWolfIsShaking == 0.0F) {
                this.playSound(shakeSound, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
            this.timeWolfIsShaking += 0.05F;

            if (this.prevTimeWolfIsShaking >= 2.0F) {
                this.isWet = false;
                this.isShaking = false;
                this.prevTimeWolfIsShaking = 0.0F;
                this.timeWolfIsShaking = 0.0F;
            }

            if (this.timeWolfIsShaking > 0.4F) {
                float f = (float) this.getEntityBoundingBox().minY;
                int i = (int) (MathHelper.sin((this.timeWolfIsShaking - 0.4F) * (float) Math.PI) * 7.0F);

                for (int j = 0; j < i; ++j) {
                    float f1 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    float f2 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    this.world.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX + (double) f1, (double) (f + 0.8F), this.posZ + (double) f2, this.motionX, this.motionY, this.motionZ);
                }
            }
        }
    }

    /**
     * True if the wolf is wet
     */
    @SideOnly(Side.CLIENT)
    public boolean isWolfWet() {
        return this.isWet;
    }

    /**
     * Used when calculating the amount of shading to apply while the wolf is wet.
     */
    @SideOnly(Side.CLIENT)
    public float getShadingWhileWet(float p_70915_1_) {
        return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_70915_1_) / 2.0F * 0.25F;
    }

    @SideOnly(Side.CLIENT)
    public float getShakeAngle(float p_70923_1_, float p_70923_2_) {
        float f = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_70923_1_ + p_70923_2_) / 1.8F;

        if (f < 0.0F) {
            f = 0.0F;
        } else if (f > 1.0F) {
            f = 1.0F;
        }

        return MathHelper.sin(f * (float) Math.PI) * MathHelper.sin(f * (float) Math.PI * 11.0F) * 0.15F * (float) Math.PI;
    }

    @SideOnly(Side.CLIENT)
    public float getInterestedAngle(float p_70917_1_) {
        return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * p_70917_1_) * 0.15F * (float) Math.PI;
    }

    @Override
    public float getEyeHeight() {
        return this.height * 0.8F;
    }

    /**
     * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
     * use in wolves.
     */
    @Override
    public int getVerticalFaceSpeed() {
        return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        } else {
            Entity entity = source.getTrueSource();

            if (this.aiSit != null) {
                this.aiSit.setSitting(false);
            }

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(source, amount);
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

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);

        //Fix for mob egg calling this due to nbt write then read which undoes students health set
        /*if (tamed) {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        }*/

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {

        ItemStack itemstack = player.getHeldItem(hand);

        if (this.isTamed()) {
            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof ItemFood) {
                    ItemFood itemfood = (ItemFood) itemstack.getItem();

                    if (itemfood.isWolfsFavoriteMeat() && this.dataManager.get(DATA_HEALTH_ID).floatValue() < 20.0F) {
                        if (!player.capabilities.isCreativeMode) {
                            itemstack.shrink(1);
                        }

                        this.heal((float) itemfood.getHealAmount(itemstack));
                        return true;
                    }
                } else if (itemstack.getItem() == Items.DYE) {
                    EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(itemstack.getMetadata());

                    if (enumdyecolor != this.getCollarColor()) {
                        this.setCollarColor(enumdyecolor);

                        if (!player.capabilities.isCreativeMode) {
                            itemstack.shrink(1);
                        }

                        return true;
                    }
                }
            }

            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPath();
                this.setAttackTarget(null);
            }
        } else if (isTameableItem(itemstack) && !this.isAngry()) {
            if (!player.capabilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            if (!this.world.isRemote) {
                if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                    this.setTamedBy(player);
                    this.navigator.clearPath();
                    this.setAttackTarget(null);
                    this.aiSit.setSitting(true);
                    this.setHealth(20.0F);
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte) 7);
                } else {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte) 6);
                }
            }

            return true;
        }

        return super.processInteract(player, hand);
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 8) {
            this.isShaking = true;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @SideOnly(Side.CLIENT)
    public float getTailRotation() {
        if (this.isAngry()) {
            return 1.5393804F;
        } else {
            return this.isTamed() ? (0.55F - (this.getMaxHealth() - this.dataManager.get(DATA_HEALTH_ID).floatValue()) * 0.02F) * (float) Math.PI : ((float) Math.PI / 5F);
        }
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() instanceof ItemFood && ((ItemFood) stack.getItem()).isWolfsFavoriteMeat();
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    @Override
    public int getMaxSpawnedInChunk() {
        return 8;
    }

    /**
     * Determines whether this wolf is angry or not.
     */
    public boolean isAngry() {
        return (this.dataManager.get(TAMED).byteValue() & 2) != 0;
    }

    /**
     * Sets whether this wolf is angry or not.
     */
    public void setAngry(boolean angry) {
        byte b0 = this.dataManager.get(TAMED).byteValue();

        if (angry) {
            this.dataManager.set(TAMED, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.dataManager.set(TAMED, Byte.valueOf((byte) (b0 & -3)));
        }
    }

    public EnumDyeColor getCollarColor() {
        return EnumDyeColor.byDyeDamage(this.dataManager.get(COLLAR_COLOR).intValue() & 15);
    }

    public void setCollarColor(EnumDyeColor collarcolor) {
        this.dataManager.set(COLLAR_COLOR, Integer.valueOf(collarcolor.getDyeDamage()));
    }

    public void setBegging(boolean beg) {
        this.dataManager.set(BEGGING, Boolean.valueOf(beg));
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if (!this.isTamed()) {
            return false;
        } else if (!(otherAnimal instanceof EntityWolf)) {
            return false;
        } else {
            EntityWolf entitywolf = (EntityWolf) otherAnimal;

            if (!entitywolf.isTamed()) {
                return false;
            } else if (entitywolf.isSitting()) {
                return false;
            } else {
                return this.isInLove() && entitywolf.isInLove();
            }
        }
    }

    public boolean isBegging() {
        return this.dataManager.get(BEGGING).booleanValue();
    }

    @Override
    public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
        if (isAttackableCreature(target)) {
            if (target instanceof EntityWolf) {
                EntityWolf entitywolf = (EntityWolf) target;

                if (entitywolf.isTamed() && entitywolf.getOwner() == owner) {
                    return false;
                }
            }

            if (target instanceof EntityPlayer && owner instanceof EntityPlayer && !((EntityPlayer) owner).canAttackPlayer((EntityPlayer) target)) {
                return false;
            } else {
                return !(target instanceof AbstractHorse) || !((AbstractHorse) target).isTame();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return !this.isAngry() && super.canBeLeashedTo(player);
    }

    // Override this to add (random) drops when this entity dies
    protected void dropItems(DamageSource source) {
    }

    // Drop an itemstack in the world at a specific location
    public void dropItem(ItemStack stack) {
        BlockPos pos = getPosition();
        EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
        world.spawnEntity(entityItem);
    }

    // Drop an item in the world at a specific location
    public void dropItem(Block block, int amount) {
        dropItem(new ItemStack(block, amount));
    }



    /**
     * Set relative position for passenger, which is then rotated correctly
     *
     * @param x positive x is right, negative x is left
     * @param y positive y is up, negative y is down
     * @param z positive z is back, negative z is front
     */
    public void setRelativePassengerPosition(float x, float y, float z) {
        playerPosition = new Vec3d(x, y, z);
    }

    @Nullable
    @Override
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    @Override
    public void updatePassenger(Entity passenger)
    {

        if (this.isPassenger(passenger))
        {
            Vec3d pos = playerPosition;

            //inverting x and z to make it correct to known standards
            double relZAdjX = -Math.sin(Math.toRadians(this.rotationYaw - 0F) - (float)Math.PI) * pos.z;
            double relZAdjZ = Math.cos(Math.toRadians(this.rotationYaw - 0F) - (float)Math.PI) * pos.z;
            double relXAdjX = -Math.sin(Math.toRadians(this.rotationYaw - 90F) - (float)Math.PI) * pos.x;
            double relXAdjZ = Math.cos(Math.toRadians(this.rotationYaw - 90F) - (float)Math.PI) * pos.x;

            passenger.setPosition(this.posX + relZAdjX + relXAdjX,
                    this.posY + (pos.y / (this.isChild() ? 4F : 1F)),
                    this.posZ + relZAdjZ + relXAdjZ);

            if (this.isChild()) {
                passenger.dismountRidingEntity();
            }
        }
    }

    public void onInteract(EntityPlayer player) {

    }
}
