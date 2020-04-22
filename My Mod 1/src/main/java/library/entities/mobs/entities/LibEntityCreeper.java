package library.entities.mobs.entities;

import library.ai.LibEntityAICreeperSwell;
import library.entities.LibEntityMob;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Collection;

public class LibEntityCreeper extends LibEntityMob<LibEntityCreeper> {

    /** COMMON VARIABLES **/

    // Sounds
    protected SoundEvent hurtSound = SoundEvents.ENTITY_CREEPER_HURT;
    protected SoundEvent deathSound = SoundEvents.ENTITY_CREEPER_DEATH;

    // Loot table
    protected ResourceLocation lootTable = LootTableList.ENTITIES_CREEPER;

    // How long (in ticks) before the creeper explodes (20 ticks is 1 second)
    protected int fuseTime = 30;

    // Radius of the explosoin
    protected int explosionRadius = 3;

    /** COMMON METHODS **/

    // Sets the Entity's attributes
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        double movementSpeed = 0.25D; // how fast it moves normally

        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(movementSpeed);
    }

    // Sets the Entity's core behaviors
    @Override
    protected void initEntityAI() {
        float maxStareDistance = 8.0F; // number of blocks away it will look at entities it watches
        Class<EntityOcelot> avoidClass = EntityOcelot.class;    // which entity to avoid
        Class<EntityPlayer> targetClass = EntityPlayer.class;   // which entity to watch and target

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new LibEntityAICreeperSwell(this));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, avoidClass, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, targetClass, maxStareDistance));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, targetClass, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
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

    public LibEntityCreeper(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.7F);
    }


    protected int getFuseTime() {
        return fuseTime;
    }

    protected int getExplosionRadius() {
        return explosionRadius;
    }


    private static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntityCreeper.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> POWERED = EntityDataManager.createKey(EntityCreeper.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IGNITED = EntityDataManager.createKey(EntityCreeper.class, DataSerializers.BOOLEAN);

    /**
     * All work variables needed to make the creeper work. There are for internal use mostly
     */
    private int lastActiveTime;     // Time when this creeper was last in an active state
    private int timeSinceIgnited;   // The amount of time since the creeper was close enough to the player to ignite
    private int droppedSkulls;

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(STATE, -1);
        this.dataManager.register(POWERED, false);
        this.dataManager.register(IGNITED, false);
    }

    /**
     * The maximum height from where the entity is alowed to jump (used in pathfinder)
     */
    @Override
    public int getMaxFallHeight() {
        return this.getAttackTarget() == null ? 3 : 3 + (int) (this.getHealth() - 1.0F);
    }

    /**
     * When this creeper falls we do the default fall behaviour but in addition we also make the creeper explode faster
     */
    @Override
    public void fall(float distance, float damageMultiplier) {
        super.fall(distance, damageMultiplier);

        this.timeSinceIgnited = (int) (this.timeSinceIgnited + distance * 1.5F);
        if (this.timeSinceIgnited > getFuseTime() - 5) {
            this.timeSinceIgnited = getFuseTime() - 5;
        }
    }

    /**
     * Write our entity data to NBT (for persistance). For the creeper we only need to store if the creeper
     * is powered (struck by lightning) and ignited.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        if (this.dataManager.get(POWERED)) {
            compound.setBoolean("powered", true);
        }
        compound.setBoolean("ignited", this.hasIgnited());
    }

    /**
     * Read our entity back from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        this.dataManager.set(POWERED, compound.getBoolean("powered"));
        if (compound.getBoolean("ignited")) {
            this.ignite();
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        if (this.isEntityAlive()) {
            onUpdateIgnition();
        }

        super.onUpdate();
    }

    /**
     * This is called in onUpdate and will handle ignition (if needed). If you want
     * to modify this (for example to avoid igniting under certain conditions) you can override
     * this.
     */
    protected void onUpdateIgnition() {
        this.lastActiveTime = this.timeSinceIgnited;

        if (this.hasIgnited()) {
            this.setCreeperState(1);
        }

        int i = this.getCreeperState();

        if (i > 0 && this.timeSinceIgnited == 0) {
            this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
        }

        this.timeSinceIgnited += i;

        if (this.timeSinceIgnited < 0) {
            this.timeSinceIgnited = 0;
        }

        if (this.timeSinceIgnited >= getFuseTime()) {
            this.timeSinceIgnited = getFuseTime();
            this.explode();
        }
    }

    /**
     * Called when the mob's health reaches 0. The creeper version will check how it got killed
     * and possibly add some additional loot
     */
    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (this.world.getGameRules().getBoolean("doMobLoot")) {
            if (cause.getTrueSource() instanceof EntitySkeleton) {
                int i = Item.getIdFromItem(Items.RECORD_13);
                int j = Item.getIdFromItem(Items.RECORD_WAIT);
                int k = i + this.rand.nextInt(j - i + 1);
                this.dropItem(Item.getItemById(k), 1);
            } else if (cause.getTrueSource() instanceof EntityCreeper && cause.getTrueSource() != this && ((EntityCreeper) cause.getTrueSource()).getPowered() && !((EntityCreeper) cause.getTrueSource()).isAIDisabled()) {
                ((EntityCreeper) cause.getTrueSource()).incrementDroppedSkulls();
                this.entityDropItem(new ItemStack(Items.SKULL, 1, 4), 0.0F);
            }
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return true;
    }

    /**
     * Returns true if the creeper is powered by a lightning bolt.
     */
    public boolean getPowered() {
        return this.dataManager.get(POWERED);
    }

    /**
     * Returns the intensity of the creeper's flash when it is ignited.
     */
    @SideOnly(Side.CLIENT)
    public float getCreeperFlashIntensity(float partialTicks) {
        return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * partialTicks) / (getFuseTime() - 2);
    }

    /**
     * Returns the current state of creeper, -1 is idle, 1 is 'in fuse'
     */
    public int getCreeperState() {
        return this.dataManager.get(STATE).intValue();
    }

    /**
     * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
     */
    public void setCreeperState(int state) {
        this.dataManager.set(STATE, state);
    }

    /**
     * Called when a lightning bolt hits the entity. This will cause the creeper to be 'powered'
     */
    @Override
    public void onStruckByLightning(EntityLightningBolt lightningBolt) {
        super.onStruckByLightning(lightningBolt);
        this.dataManager.set(POWERED, true);
    }

    /**
     * Called when the player right clicks the creeper with some item. The default
     * implementation will ignite the creeper if it is right clicked with flint and
     * steel. If you don't want this, or want additional behaviour you can override
     * this method.
     */
    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
            this.world.playSound(player, this.posX, this.posY, this.posZ, SoundEvents.ITEM_FLINTANDSTEEL_USE, this.getSoundCategory(), 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
            player.swingArm(hand);

            if (!this.world.isRemote) {
                this.ignite();
                itemstack.damageItem(1, player);
                return true;
            }
        }

        return super.processInteract(player, hand);
    }

    /**
     * Creates an explosion as determined by this creeper's power and explosion radius.
     * This is called from onUpdateIgnition(). You can override this if you want additional
     * or different explosion behaviour or you can call this to manually force this creeper
     * to explode.
     */
    public void explode() {
        if (!this.world.isRemote) {
            boolean flag = this.world.getGameRules().getBoolean("mobGriefing");
            float f = this.getPowered() ? 2.0F : 1.0F;
            this.dead = true;
            this.world.createExplosion(this, this.posX, this.posY, this.posZ, getExplosionRadius() * f, flag);
            this.setDead();
            this.spawnLingeringCloud();
        }
    }

    private void spawnLingeringCloud() {
        Collection<PotionEffect> collection = this.getActivePotionEffects();

        if (!collection.isEmpty()) {
            EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.world, this.posX, this.posY, this.posZ);
            entityareaeffectcloud.setRadius(2.5F);
            entityareaeffectcloud.setRadiusOnUse(-0.5F);
            entityareaeffectcloud.setWaitTime(10);
            entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() / 2);
            entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / entityareaeffectcloud.getDuration());

            for (PotionEffect potioneffect : collection) {
                entityareaeffectcloud.addEffect(new PotionEffect(potioneffect));
            }

            this.world.spawnEntity(entityareaeffectcloud);
        }
    }

    public boolean hasIgnited() {
        return this.dataManager.get(IGNITED);
    }

    /**
     * Call this if you manually want to ignite the creeper which will cause it to explode
     * after a while.
     */
    public void ignite() {
        this.dataManager.set(IGNITED, true);
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled() {
        return this.droppedSkulls < 1 && this.world.getGameRules().getBoolean("doMobLoot");
    }

    public void incrementDroppedSkulls() {
        ++this.droppedSkulls;
    }
}