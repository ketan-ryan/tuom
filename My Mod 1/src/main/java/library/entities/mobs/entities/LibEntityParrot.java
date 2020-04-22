package library.entities.mobs.entities;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.netty.buffer.ByteBuf;
import library.entities.IDeathFade;
import library.util.UtilEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityShoulderRiding;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LibEntityParrot extends EntityShoulderRiding implements EntityFlying, IEntityAdditionalSpawnData, IDeathFade {

    /** COMMON VARIABLES **/

    // Sounds
    protected SoundEvent ambientSound = SoundEvents.ENTITY_PARROT_AMBIENT;
    protected SoundEvent parrotEatSound = SoundEvents.ENTITY_PARROT_EAT;
    protected SoundEvent hurtSound = SoundEvents.ENTITY_PARROT_HURT;
    protected SoundEvent deathSound = SoundEvents.ENTITY_PARROT_DEATH;
    protected SoundEvent stepSound = SoundEvents.ENTITY_PARROT_STEP;
    protected SoundEvent flySound = SoundEvents.ENTITY_PARROT_FLY;

    // Loot table
    protected ResourceLocation lootTable = LootTableList.ENTITIES_PARROT;

    /** COMMON METHODS **/

    // Sets the Entity's attributes
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        double flyingSpeed = 0.4D;  // how fast it flies
        double movementSpeed = 0.2D; // how fast it moves normally
        double maxHealth = 6.0D; // max health

        this.registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(flyingSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(movementSpeed);
    }


    // Sets the Entity's core behaviors
    @Override
    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(0, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(2, new EntityAIFollowOwnerFlying(this, 1.0D, 5.0F, 1.0F));
        this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
        this.tasks.addTask(3, new EntityAILandOnOwnersShoulder(this));
        this.tasks.addTask(3, new EntityAIFollow(this, 1.0D, 3.0F, 7.0F));
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

    public SoundEvent getAmbientSound(Random random) {
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
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(stepSound, 0.15F, 1.0F);
    }

    @Override
    protected float playFlySound(float p_191954_1_) {
        this.playSound(flySound, 0.15F, 1.0F);
        return p_191954_1_ + this.flapSpeed / 2.0F;
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

    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(LibEntityParrot.class, DataSerializers.VARINT);
    /**
     * Used to select entities the parrot can mimic the sound of
     */
    private static final Predicate<EntityLiving> CAN_MIMIC = new Predicate<EntityLiving>() {
        @Override
        public boolean apply(@Nullable EntityLiving p_apply_1_) {
            return p_apply_1_ != null && LibEntityParrot.MIMIC_SOUNDS.containsKey(p_apply_1_.getClass());
        }
    };
    private static final Item DEADLY_ITEM = Items.COOKIE;
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    private static final java.util.Map<Class<? extends Entity>, SoundEvent> MIMIC_SOUNDS = Maps.newHashMapWithExpectedSize(32);
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    private boolean partyParrot;
    private BlockPos jukeboxPosition;

    public LibEntityParrot(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.9F);
        this.moveHelper = new EntityFlyHelper(this);
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        this.setVariant(this.rand.nextInt(5));
        onFirstSpawn();
        return super.onInitialSpawn(difficulty, livingdata);
    }

    /**
     * Returns new PathNavigateGround instance
     */
    @Override
    protected PathNavigate createNavigator(World worldIn) {
        PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
        pathnavigateflying.setCanOpenDoors(false);
        pathnavigateflying.setCanFloat(true);
        pathnavigateflying.setCanEnterDoors(true);
        return pathnavigateflying;
    }

    @Override
    public float getEyeHeight() {
        return this.height * 0.6F;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate() {
        playMimicSound(this.world, this);

        if (this.jukeboxPosition == null || this.jukeboxPosition.distanceSq(this.posX, this.posY, this.posZ) > 12.0D || this.world.getBlockState(this.jukeboxPosition).getBlock() != Blocks.JUKEBOX) {
            this.partyParrot = false;
            this.jukeboxPosition = null;
        }

        updateArmSwingProgress();
        super.onLivingUpdate();
        UtilEntity.tickFire(this, this.burnsInDay);
        this.calculateFlapping();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setPartying(BlockPos pos, boolean p_191987_2_) {
        this.jukeboxPosition = pos;
        this.partyParrot = p_191987_2_;
    }

    @SideOnly(Side.CLIENT)
    public boolean isPartying() {
        return this.partyParrot;
    }

    private void calculateFlapping() {
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed = (float) ((double) this.flapSpeed + (double) (this.onGround ? -1 : 4) * 0.3D);
        this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);

        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping = (float) ((double) this.flapping * 0.9D);

        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }

        this.flap += this.flapping * 2.0F;
    }

    private static boolean playMimicSound(World worldIn, Entity p_192006_1_) {
        if (!p_192006_1_.isSilent() && worldIn.rand.nextInt(50) == 0) {
            List<EntityLiving> list = worldIn.<EntityLiving>getEntitiesWithinAABB(EntityLiving.class, p_192006_1_.getEntityBoundingBox().grow(20.0D), CAN_MIMIC);

            if (!list.isEmpty()) {
                EntityLiving entityliving = list.get(worldIn.rand.nextInt(list.size()));

                if (!entityliving.isSilent()) {
                    SoundEvent soundevent = MIMIC_SOUNDS.get(entityliving.getClass());
                    worldIn.playSound(null, p_192006_1_.posX, p_192006_1_.posY, p_192006_1_.posZ, soundevent, p_192006_1_.getSoundCategory(), 0.7F, getPitch(worldIn.rand));
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (hand == EnumHand.MAIN_HAND) {
            onInteract(player);
        }

        if (!this.isTamed() && TAME_ITEMS.contains(itemstack.getItem())) {
            if (!player.capabilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            if (!this.isSilent()) {
                this.world.playSound(null, this.posX, this.posY, this.posZ, parrotEatSound, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
            }

            if (!this.world.isRemote) {
                if (this.rand.nextInt(10) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                    this.setTamedBy(player);
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte) 7);
                } else {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte) 6);
                }
            }

            return true;
        } else if (itemstack.getItem() == DEADLY_ITEM) {
            if (!player.capabilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            this.addPotionEffect(new PotionEffect(MobEffects.POISON, 900));

            if (player.isCreative() || !this.getIsInvulnerable()) {
                this.attackEntityFrom(DamageSource.causePlayerDamage(player), Float.MAX_VALUE);
            }

            return true;
        } else {
            if (!this.world.isRemote && !this.isFlying() && this.isTamed() && this.isOwner(player)) {
                this.aiSit.setSitting(!this.isSitting());
            }

            return super.processInteract(player, hand);
        }
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(i, j, k);
        Block block = this.world.getBlockState(blockpos.down()).getBlock();
        return block instanceof BlockLeaves || block == Blocks.GRASS || block instanceof BlockLog || block == Blocks.AIR && this.world.getLight(blockpos) > 8 && super.getCanSpawnHere();
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
        return false;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return UtilEntity.createCopy(ageable);
    }

    public void playAmbientSound(World worldIn, Entity p_192005_1_) {
        if (!p_192005_1_.isSilent() && !playMimicSound(worldIn, p_192005_1_) && worldIn.rand.nextInt(200) == 0) {
            worldIn.playSound(null, p_192005_1_.posX, p_192005_1_.posY, p_192005_1_.posZ, getAmbientSound(worldIn.rand), p_192005_1_.getSoundCategory(), 1.0F, getPitch(worldIn.rand));
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
    }

    @Override
    @Nullable
    public SoundEvent getAmbientSound() {
        return getAmbientSound(this.rand);
    }

    @Override
    protected boolean makeFlySound() {
        return true;
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    @Override
    protected float getSoundPitch() {
        return getPitch(this.rand);
    }

    private static float getPitch(Random random) {
        return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
    }

    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.NEUTRAL;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    @Override
    public boolean canBePushed() {
        return true;
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
        if (!(entityIn instanceof EntityPlayer)) {
            super.collideWithEntity(entityIn);
        }
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        } else {
            if (this.aiSit != null) {
                this.aiSit.setSitting(false);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    public int getVariant() {
        return MathHelper.clamp(this.dataManager.get(VARIANT).intValue(), 0, 4);
    }

    public void setVariant(int p_191997_1_) {
        this.dataManager.set(VARIANT, Integer.valueOf(p_191997_1_));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(VARIANT, Integer.valueOf(0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getVariant());
        compound.setBoolean("justSpawned", justSpawned);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setVariant(compound.getInteger("Variant"));
        if (useBossBar) this.bossInfo.setName(this.getDisplayName());
        this.justSpawned = compound.getBoolean("justSpawned");
    }

    public boolean isFlying() {
        return !this.onGround;
    }

    static {
        registerMimicSound(EntityBlaze.class, SoundEvents.E_PARROT_IM_BLAZE);
        registerMimicSound(EntityCaveSpider.class, SoundEvents.E_PARROT_IM_SPIDER);
        registerMimicSound(EntityCreeper.class, SoundEvents.E_PARROT_IM_CREEPER);
        registerMimicSound(EntityElderGuardian.class, SoundEvents.E_PARROT_IM_ELDER_GUARDIAN);
        registerMimicSound(EntityDragon.class, SoundEvents.E_PARROT_IM_ENDERDRAGON);
        registerMimicSound(EntityEnderman.class, SoundEvents.E_PARROT_IM_ENDERMAN);
        registerMimicSound(EntityEndermite.class, SoundEvents.E_PARROT_IM_ENDERMITE);
        registerMimicSound(EntityEvoker.class, SoundEvents.E_PARROT_IM_EVOCATION_ILLAGER);
        registerMimicSound(EntityGhast.class, SoundEvents.E_PARROT_IM_GHAST);
        registerMimicSound(EntityHusk.class, SoundEvents.E_PARROT_IM_HUSK);
        registerMimicSound(EntityIllusionIllager.class, SoundEvents.E_PARROT_IM_ILLUSION_ILLAGER);
        registerMimicSound(EntityMagmaCube.class, SoundEvents.E_PARROT_IM_MAGMACUBE);
        registerMimicSound(EntityPigZombie.class, SoundEvents.E_PARROT_IM_ZOMBIE_PIGMAN);
        registerMimicSound(EntityPolarBear.class, SoundEvents.E_PARROT_IM_POLAR_BEAR);
        registerMimicSound(EntityShulker.class, SoundEvents.E_PARROT_IM_SHULKER);
        registerMimicSound(EntitySilverfish.class, SoundEvents.E_PARROT_IM_SILVERFISH);
        registerMimicSound(EntitySkeleton.class, SoundEvents.E_PARROT_IM_SKELETON);
        registerMimicSound(EntitySlime.class, SoundEvents.E_PARROT_IM_SLIME);
        registerMimicSound(EntitySpider.class, SoundEvents.E_PARROT_IM_SPIDER);
        registerMimicSound(EntityStray.class, SoundEvents.E_PARROT_IM_STRAY);
        registerMimicSound(EntityVex.class, SoundEvents.E_PARROT_IM_VEX);
        registerMimicSound(EntityVindicator.class, SoundEvents.E_PARROT_IM_VINDICATION_ILLAGER);
        registerMimicSound(EntityWitch.class, SoundEvents.E_PARROT_IM_WITCH);
        registerMimicSound(EntityWither.class, SoundEvents.E_PARROT_IM_WITHER);
        registerMimicSound(EntityWitherSkeleton.class, SoundEvents.E_PARROT_IM_WITHER_SKELETON);
        registerMimicSound(EntityWolf.class, SoundEvents.E_PARROT_IM_WOLF);
        registerMimicSound(EntityZombie.class, SoundEvents.E_PARROT_IM_ZOMBIE);
        registerMimicSound(EntityZombieVillager.class, SoundEvents.E_PARROT_IM_ZOMBIE_VILLAGER);
    }

    public static void registerMimicSound(Class<? extends Entity> cls, SoundEvent sound) {
        MIMIC_SOUNDS.put(cls, sound);
    }

    // Sets the Entity's drop items on Death
    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (!world.isRemote) {
            // drop items here
            dropItems(cause);
        }
    }

    /** YD FEATURES START **/

    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
    private boolean useBossBar = false;

    //used to help client fire an onInitialSpawn like method on entity
    private boolean justSpawned = true;

    private Vec3d playerPosition = new Vec3d(0, 0, 0);

    private boolean fadeOutDeath = false;
    private int fadeOutTicks = 20;

    protected boolean burnsInDay = false;

    /** NEW METHODS **/

    @Override
    public boolean isFadeOutDeath() {
        return fadeOutDeath;
    }

    @Override
    public int getFadeOutTicks() {
        return fadeOutTicks;
    }

    @Override
    public void enableFadeOutDeath(int fadeOutTicks) {
        this.fadeOutDeath = true;
        this.fadeOutTicks = fadeOutTicks;
    }

    public void enableBossBar() {
        useBossBar = true;
    }

    public void disableBossBar() {
        useBossBar = false;
    }

    public void onFirstSpawn() {

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

    public void onInteract(EntityPlayer player) {

    }

    /** HOOKS INTO VANILLA METHODS **/

    @Override
    public void addTrackingPlayer(EntityPlayerMP player) {
        super.addTrackingPlayer(player);
        if (useBossBar) this.bossInfo.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(EntityPlayerMP player) {
        super.removeTrackingPlayer(player);
        if (useBossBar) this.bossInfo.removePlayer(player);
    }

    @Override
    public void setCustomNameTag(String name) {
        super.setCustomNameTag(name);
        if (useBossBar) this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeBoolean(justSpawned);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        justSpawned = additionalData.readBoolean();
        if (justSpawned) {
            onFirstSpawn();
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        justSpawned = false;
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        if (useBossBar) this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
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

            //fix for relative riding rotation issue
            float yaw = this.renderYawOffset;
            //yaw = this.rotationYaw;

            //inverting x and z to make it correct to known standards
            double relZAdjX = -Math.sin(Math.toRadians(yaw - 0F) - (float)Math.PI) * pos.z;
            double relZAdjZ = Math.cos(Math.toRadians(yaw - 0F) - (float)Math.PI) * pos.z;
            double relXAdjX = -Math.sin(Math.toRadians(yaw - 90F) - (float)Math.PI) * pos.x;
            double relXAdjZ = Math.cos(Math.toRadians(yaw - 90F) - (float)Math.PI) * pos.x;

            passenger.setPosition(this.posX + relZAdjX + relXAdjX,
                    this.posY + pos.y,
                    this.posZ + relZAdjZ + relXAdjZ);
        }
    }

    @Override
    protected void onDeathUpdate()
    {
        ++this.deathTime;

        if (this.deathTime == fadeOutTicks)
        {
            if (!this.world.isRemote && (this.isPlayer() || this.recentlyHit > 0 && this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot")))
            {
                int i = this.getExperiencePoints(this.attackingPlayer);
                i = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(this, this.attackingPlayer, i);
                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
                }
            }

            this.setDead();

            for (int k = 0; k < 20; ++k)
            {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
            }
        }
    }
}