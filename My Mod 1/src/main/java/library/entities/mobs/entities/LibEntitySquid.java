package library.entities.mobs.entities;

import io.netty.buffer.ByteBuf;
import library.ai.LibEntityAIAttackBlock;
import library.ai.LibEntityAIDigTowardsTarget;
import library.entities.IDeathFade;
import library.util.UtilEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

public class LibEntitySquid extends EntityCreature implements IEntityAdditionalSpawnData, IDeathFade {

    /** COMMON VARIABLES **/

    // Sounds
    protected SoundEvent ambientSound = SoundEvents.ENTITY_SQUID_AMBIENT;
    protected SoundEvent hurtSound = SoundEvents.ENTITY_SQUID_HURT;
    protected SoundEvent deathSound = SoundEvents.ENTITY_SQUID_DEATH;

    // Loot table
    protected ResourceLocation lootTable = LootTableList.ENTITIES_SQUID;

    /** COMMON METHODS **/

    // Sets the Entity's attributes
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        double maxHealth = 10.0D; // max health

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);
    }

    // Sets the Entity's core behaviors
    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new LibEntitySquid.AIMoveRandom(this));
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

    /** OTHER METHODS **/

    public float squidPitch;
    public float prevSquidPitch;
    public float squidYaw;
    public float prevSquidYaw;
    /**
     * appears to be rotation in radians; we already have pitch & yaw, so this completes the triumvirate.
     */
    public float squidRotation;
    /**
     * previous squidRotation in radians
     */
    public float prevSquidRotation;
    /**
     * angle of the tentacles in radians
     */
    public float tentacleAngle;
    /**
     * the last calculated angle of the tentacles in radians
     */
    public float lastTentacleAngle;
    private float randomMotionSpeed;
    /**
     * change in squidRotation in radians.
     */
    private float rotationVelocity;
    private float rotateSpeed;
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;

    public LibEntitySquid(World worldIn) {
        super(worldIn);
        this.setSize(0.8F, 0.8F);
        this.rand.setSeed((1 + this.getEntityId()));
        this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
    }

    @Override
    public float getEyeHeight() {
        return this.height * 0.5F;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate() {
        updateArmSwingProgress();
        super.onLivingUpdate();
        UtilEntity.tickFire(this, this.burnsInDay);
        this.prevSquidPitch = this.squidPitch;
        this.prevSquidYaw = this.squidYaw;
        this.prevSquidRotation = this.squidRotation;
        this.lastTentacleAngle = this.tentacleAngle;
        this.squidRotation += this.rotationVelocity;

        if (this.squidRotation > (Math.PI * 2D)) {
            if (this.world.isRemote) {
                this.squidRotation = ((float) Math.PI * 2F);
            } else {
                this.squidRotation = (float) (this.squidRotation - (Math.PI * 2D));

                if (this.rand.nextInt(10) == 0) {
                    this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
                }

                this.world.setEntityState(this, (byte) 19);
            }
        }

        if (this.inWater) {
            if (this.squidRotation < (float) Math.PI) {
                float f = this.squidRotation / (float) Math.PI;
                this.tentacleAngle = MathHelper.sin(f * f * (float) Math.PI) * (float) Math.PI * 0.25F;

                if (f > 0.75D) {
                    this.randomMotionSpeed = 1.0F;
                    this.rotateSpeed = 1.0F;
                } else {
                    this.rotateSpeed *= 0.8F;
                }
            } else {
                this.tentacleAngle = 0.0F;
                this.randomMotionSpeed *= 0.9F;
                this.rotateSpeed *= 0.99F;
            }

            if (!this.world.isRemote) {
                this.motionX = (this.randomMotionVecX * this.randomMotionSpeed);
                this.motionY = (this.randomMotionVecY * this.randomMotionSpeed);
                this.motionZ = (this.randomMotionVecZ * this.randomMotionSpeed);
            }

            float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.renderYawOffset += (-((float) MathHelper.atan2(this.motionX, this.motionZ)) * (180F / (float) Math.PI) - this.renderYawOffset) * 0.1F;
            this.rotationYaw = this.renderYawOffset;
            this.squidYaw = (float) (this.squidYaw + Math.PI * this.rotateSpeed * 1.5D);
            this.squidPitch += (-((float) MathHelper.atan2(f1, this.motionY)) * (180F / (float) Math.PI) - this.squidPitch) * 0.1F;
        } else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * (float) Math.PI * 0.25F;

            if (!this.world.isRemote) {
                this.motionX = 0.0D;
                this.motionZ = 0.0D;

                if (this.isPotionActive(MobEffects.LEVITATION)) {
                    this.motionY += 0.05D * (this.getActivePotionEffect(MobEffects.LEVITATION).getAmplifier() + 1) - this.motionY;
                } else if (!this.hasNoGravity()) {
                    this.motionY -= 0.08D;
                }

                this.motionY *= 0.9800000190734863D;
            }

            this.squidPitch = (float) (this.squidPitch + (-90.0F - this.squidPitch) * 0.02D);
        }
    }

    @Override
    public void travel(float p_191986_1_, float p_191986_2_, float p_191986_3_) {
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
    public boolean getCanSpawnHere() {
        return this.posY > 45.0D && this.posY < this.world.getSeaLevel() && super.getCanSpawnHere();
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

    /**
     * Handler for {@link World#setEntityState}
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 19) {
            this.squidRotation = 0.0F;
        } else {
            super.handleStatusUpdate(id);
        }
    }

    public void setMovementVector(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn) {
        this.randomMotionVecX = randomMotionVecXIn;
        this.randomMotionVecY = randomMotionVecYIn;
        this.randomMotionVecZ = randomMotionVecZIn;
    }

    public boolean hasMovementVector() {
        return this.randomMotionVecX != 0.0F || this.randomMotionVecY != 0.0F || this.randomMotionVecZ != 0.0F;
    }

    public static class AIMoveRandom extends EntityAIBase {
        private final LibEntitySquid squid;

        public AIMoveRandom(LibEntitySquid p_i45859_1_) {
            this.squid = p_i45859_1_;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask() {
            int i = this.squid.getIdleTime();

            if (i > 100) {
                this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
            } else if (this.squid.getRNG().nextInt(50) == 0 || !this.squid.inWater || !this.squid.hasMovementVector()) {
                float f = this.squid.getRNG().nextFloat() * ((float) Math.PI * 2F);
                float f1 = MathHelper.cos(f) * 0.2F;
                float f2 = -0.1F + this.squid.getRNG().nextFloat() * 0.2F;
                float f3 = MathHelper.sin(f) * 0.2F;
                this.squid.setMovementVector(f1, f2, f3);
            }
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

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        onFirstSpawn();
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("justSpawned", justSpawned);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        if (useBossBar) this.bossInfo.setName(this.getDisplayName());
        this.justSpawned = compound.getBoolean("justSpawned");
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
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (hand == EnumHand.MAIN_HAND) {
            onInteract(player);
        }
        return super.processInteract(player, hand);
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

    public boolean canBreatheUnderwater()
    {
        return true;
    }

    /**
     * Checks that the entity is not colliding with any blocks / liquids
     */
    public boolean isNotColliding()
    {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return 120;
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return true;
    }

    /**
     * Get the experience points the entity currently has.
     */
    protected int getExperiencePoints(EntityPlayer player)
    {
        return 1 + this.world.rand.nextInt(3);
    }

    /**
     * Gets called every tick from main Entity class
     */
    public void onEntityUpdate()
    {
        int i = this.getAir();
        super.onEntityUpdate();

        if (this.isEntityAlive() && !this.isInWater())
        {
            --i;
            this.setAir(i);

            if (this.getAir() == -20)
            {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.DROWN, 2.0F);
            }
        }
        else
        {
            this.setAir(300);
        }
    }

    public boolean isPushedByWater()
    {
        return false;
    }
}