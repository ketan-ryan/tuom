package library.entities;

import io.netty.buffer.ByteBuf;
import library.entities.IDeathFade;
import library.util.UtilEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import javax.annotation.Nullable;

public abstract class LibEntityTameable extends EntityTameable implements IEntityAdditionalSpawnData, IDeathFade {

    public LibEntityTameable(World worldIn) {
        super(worldIn);
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
    public void onLivingUpdate() {
        updateArmSwingProgress();
        super.onLivingUpdate();
        UtilEntity.tickFire(this, this.burnsInDay);
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
    public EntityAgeable createChild(EntityAgeable ageable) {

        return UtilEntity.createCopy(ageable);
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
