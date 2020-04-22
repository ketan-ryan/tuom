package library.entities.mobs.entities;

import io.netty.buffer.ByteBuf;
import library.entities.LibEntityMob;
import net.minecraft.block.Block;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import javax.annotation.Nullable;

public class LibEntityGenericMonster extends LibEntityMob {

    /** COMMON VARIABLES **/

    // Sounds
    protected SoundEvent stepSound = SoundEvents.ENTITY_ZOMBIE_STEP;
    protected SoundEvent ambientSound = SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    protected SoundEvent hurtSound = SoundEvents.ENTITY_ZOMBIE_HURT;
    protected SoundEvent deathSound = SoundEvents.ENTITY_ZOMBIE_DEATH;

    // Loot table
    protected ResourceLocation lootTable = LootTableList.ENTITIES_ZOMBIE;

    protected float soundVolume = 0.4F;

    /** COMMON METHODS **/

    // Sets the Entity's attributes
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        double movementSpeed = 0.25D; // how fast it moves normally
        double attackDamage = 2D; // how many half-hearts of damage it deals
        double maxHealth = 8D; // max health

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(attackDamage);
    }

    // Sets the Entity's core behaviors
    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
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

    /** OTHER METHODS **/

    public LibEntityGenericMonster(World worldIn) {
        super(worldIn);
        this.experienceValue = 3;
        this.setSize(0.4F, 0.3F);
    }



    /*protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_ENDERMITE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return SoundEvents.ENTITY_ENDERMITE_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_ENDERMITE_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_ENDERMITE_STEP, 0.15F, 1.0F);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_ENDERMITE;
    }*/

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
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(stepSound, 0.15F, 1.0F);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return lootTable;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume() {
        return soundVolume;
    }
}