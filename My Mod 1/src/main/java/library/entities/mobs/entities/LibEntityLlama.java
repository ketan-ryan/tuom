package library.entities.mobs.entities;

import library.ai.LibEntityAILlamaFollowCaravan;
import library.entities.LibEntityChestHorse;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.AbstractChestHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
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

public class LibEntityLlama extends LibEntityChestHorse implements IRangedAttackMob {

    /** COMMON VARIABLES **/

    // Sounds
    protected SoundEvent eatSound = SoundEvents.ENTITY_LLAMA_EAT;
    protected SoundEvent angrySound = SoundEvents.ENTITY_LLAMA_ANGRY;
    protected SoundEvent ambientSound = SoundEvents.ENTITY_LLAMA_AMBIENT;
    protected SoundEvent hurtSound = SoundEvents.ENTITY_LLAMA_HURT;
    protected SoundEvent deathSound = SoundEvents.ENTITY_LLAMA_DEATH;
    protected SoundEvent stepSound = SoundEvents.ENTITY_LLAMA_STEP;
    protected SoundEvent chestSound = SoundEvents.ENTITY_LLAMA_CHEST;
    protected SoundEvent swagSound = SoundEvents.ENTITY_LLAMA_SWAG;

    // Loot table
    protected ResourceLocation lootTable = LootTableList.ENTITIES_LLAMA;

    /** COMMON METHODS **/

    // Sets the Entity's attributes
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        double followRange = 40.0D; // how many blocks away it will follow its target
        double attackDamage = 1D; // how many half-hearts of damage it deals

        this.registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(followRange);
    }

    // Sets the Entity's core behaviors
    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRunAroundLikeCrazy(this, 1.2D));
        this.tasks.addTask(2, new LibEntityAILlamaFollowCaravan(this, 2.1D));
        this.tasks.addTask(3, new EntityAIAttackRanged(this, 1.25D, 40, 20.0F));
        this.tasks.addTask(3, new EntityAIPanic(this, 1.2D));
        this.tasks.addTask(4, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.7D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new LibEntityLlama.AIHurtByTarget(this));
        this.targetTasks.addTask(2, new LibEntityLlama.AIDefendTarget(this));
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
    protected SoundEvent getAngrySound() {
        return angrySound;
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
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(stepSound, 0.15F, 1.0F);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return lootTable;
    }

    @Override
    protected void playChestEquipSound() {
        this.playSound(chestSound, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
    }


    /** OTHER METHODS **/

    private static final DataParameter<Integer> DATA_STRENGTH_ID = EntityDataManager.<Integer>createKey(LibEntityLlama.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> DATA_COLOR_ID = EntityDataManager.<Integer>createKey(LibEntityLlama.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.<Integer>createKey(LibEntityLlama.class, DataSerializers.VARINT);
    private boolean didSpit;
    @Nullable
    private LibEntityLlama caravanHead;
    @Nullable
    private LibEntityLlama caravanTail;

    public LibEntityLlama(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 1.87F);
    }

    private void setStrength(int strengthIn) {
        this.dataManager.set(DATA_STRENGTH_ID, Integer.valueOf(Math.max(1, Math.min(5, strengthIn))));
    }

    private void setRandomStrength() {
        int i = this.rand.nextFloat() < 0.04F ? 5 : 3;
        this.setStrength(1 + this.rand.nextInt(i));
    }

    public int getStrength() {
        return this.dataManager.get(DATA_STRENGTH_ID).intValue();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getVariant());
        compound.setInteger("Strength", this.getStrength());

        if (!this.horseChest.getStackInSlot(1).isEmpty()) {
            compound.setTag("DecorItem", this.horseChest.getStackInSlot(1).writeToNBT(new NBTTagCompound()));
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        this.setStrength(compound.getInteger("Strength"));
        super.readEntityFromNBT(compound);
        this.setVariant(compound.getInteger("Variant"));

        if (compound.hasKey("DecorItem", 10)) {
            this.horseChest.setInventorySlotContents(1, new ItemStack(compound.getCompoundTag("DecorItem")));
        }

        this.updateHorseSlots();
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(DATA_STRENGTH_ID, Integer.valueOf(0));
        this.dataManager.register(DATA_COLOR_ID, Integer.valueOf(-1));
        this.dataManager.register(DATA_VARIANT_ID, Integer.valueOf(0));
    }

    public int getVariant() {
        return MathHelper.clamp(this.dataManager.get(DATA_VARIANT_ID).intValue(), 0, 3);
    }

    public void setVariant(int variantIn) {
        this.dataManager.set(DATA_VARIANT_ID, Integer.valueOf(variantIn));
    }

    @Override
    protected int getInventorySize() {
        return this.hasChest() ? 2 + 3 * this.getInventoryColumns() : super.getInventorySize();
    }

    @Override
    public void updatePassenger(Entity passenger) {
        if (this.isPassenger(passenger)) {
            float f = MathHelper.cos(this.renderYawOffset * 0.017453292F);
            float f1 = MathHelper.sin(this.renderYawOffset * 0.017453292F);
            float f2 = 0.3F;
            passenger.setPosition(this.posX + (0.3F * f1), this.posY + this.getMountedYOffset() + passenger.getYOffset(), this.posZ - (0.3F * f));
        }
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    @Override
    public double getMountedYOffset() {
        return this.height * 0.67D;
    }

    /**
     * returns true if all the conditions for steering the entity are met. For pigs, this is true if it is being ridden
     * by a player and the player is holding a carrot-on-a-stick
     */
    @Override
    public boolean canBeSteered() {
        return false;
    }

    @Override
    protected boolean handleEating(EntityPlayer player, ItemStack stack) {
        int i = 0;
        int j = 0;
        float f = 0.0F;
        boolean flag = false;
        Item item = stack.getItem();

        if (item == Items.WHEAT) {
            i = 10;
            j = 3;
            f = 2.0F;
        } else if (item == Item.getItemFromBlock(Blocks.HAY_BLOCK)) {
            i = 90;
            j = 6;
            f = 10.0F;

            if (this.isTame() && this.getGrowingAge() == 0) {
                flag = true;
                this.setInLove(player);
            }
        }

        if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
            this.heal(f);
            flag = true;
        }

        if (this.isChild() && i > 0) {
            this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, 0.0D, 0.0D, 0.0D);

            if (!this.world.isRemote) {
                this.addGrowth(i);
            }

            flag = true;
        }

        if (j > 0 && (flag || !this.isTame()) && this.getTemper() < this.getMaxTemper()) {
            flag = true;

            if (!this.world.isRemote) {
                this.increaseTemper(j);
            }
        }

        if (flag && !this.isSilent()) {
            this.world.playSound(null, this.posX, this.posY, this.posZ, eatSound, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
        }

        return flag;
    }

    /**
     * Dead and sleeping entities cannot move
     */
    @Override
    protected boolean isMovementBlocked() {
        return this.getHealth() <= 0.0F || this.isEatingHaystack();
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setRandomStrength();
        int i;

        if (livingdata instanceof LibEntityLlama.GroupData) {
            i = ((LibEntityLlama.GroupData) livingdata).variant;
        } else {
            i = this.rand.nextInt(4);
            livingdata = new LibEntityLlama.GroupData(i);
        }

        this.setVariant(i);
        return livingdata;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasColor() {
        return this.getColor() != null;
    }

    @Override
    public void makeMad() {
        SoundEvent soundevent = this.getAngrySound();

        if (soundevent != null) {
            this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
        }
    }

    @Override
    public int getInventoryColumns() {
        return this.getStrength();
    }

    @Override
    public boolean wearsArmor() {
        return true;
    }

    @Override
    public boolean isArmor(ItemStack stack) {
        return stack.getItem() == Item.getItemFromBlock(Blocks.CARPET);
    }

    @Override
    public boolean canBeSaddled() {
        return false;
    }

    /**
     * Called by InventoryBasic.onInventoryChanged() on a array that is never filled.
     */
    @Override
    public void onInventoryChanged(IInventory invBasic) {
        EnumDyeColor enumdyecolor = this.getColor();
        super.onInventoryChanged(invBasic);
        EnumDyeColor enumdyecolor1 = this.getColor();

        if (this.ticksExisted > 20 && enumdyecolor1 != null && enumdyecolor1 != enumdyecolor) {
            this.playSound(swagSound, 0.5F, 1.0F);
        }
    }

    /**
     * Updates the items in the saddle and armor slots of the horse's inventory.
     */
    @Override
    protected void updateHorseSlots() {
        if (!this.world.isRemote) {
            super.updateHorseSlots();
            this.setColorByItem(this.horseChest.getStackInSlot(1));
        }
    }

    private void setColor(@Nullable EnumDyeColor color) {
        this.dataManager.set(DATA_COLOR_ID, Integer.valueOf(color == null ? -1 : color.getMetadata()));
    }

    private void setColorByItem(ItemStack stack) {
        if (this.isArmor(stack)) {
            this.setColor(EnumDyeColor.byMetadata(stack.getMetadata()));
        } else {
            this.setColor(null);
        }
    }

    @Nullable
    public EnumDyeColor getColor() {
        int i = this.dataManager.get(DATA_COLOR_ID).intValue();
        return i == -1 ? null : EnumDyeColor.byMetadata(i);
    }

    @Override
    public int getMaxTemper() {
        return 30;
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
        return otherAnimal != this && otherAnimal instanceof LibEntityLlama && this.canMate() && ((LibEntityLlama) otherAnimal).canMate();
    }

    private void spit(EntityLivingBase target) {
//        EntityLlamaSpit entityllamaspit = new EntityLlamaSpit(this.world, this);
//        double d0 = target.posX - this.posX;
//        double d1 = target.getEntityBoundingBox().minY + (target.height / 3.0F) - entityllamaspit.posY;
//        double d2 = target.posZ - this.posZ;
//        float f = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;
//        entityllamaspit.setThrowableHeading(d0, d1 + f, d2, 1.5F, 10.0F);
//        this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_LLAMA_SPIT, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
//        this.world.spawnEntity(entityllamaspit);
//        this.didSpit = true;
    }

    private void setDidSpit(boolean didSpitIn) {
        this.didSpit = didSpitIn;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
        int i = MathHelper.ceil((distance * 0.5F - 3.0F) * damageMultiplier);

        if (i > 0) {
            if (distance >= 6.0F) {
                this.attackEntityFrom(DamageSource.FALL, i);

                if (this.isBeingRidden()) {
                    for (Entity entity : this.getRecursivePassengers()) {
                        entity.attackEntityFrom(DamageSource.FALL, i);
                    }
                }
            }

            IBlockState iblockstate = this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.2D - this.prevRotationYaw, this.posZ));
            Block block = iblockstate.getBlock();

            if (iblockstate.getMaterial() != Material.AIR && !this.isSilent()) {
                SoundType soundtype = block.getSoundType();
                this.world.playSound(null, this.posX, this.posY, this.posZ, soundtype.getStepSound(), this.getSoundCategory(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
            }
        }
    }

    public void leaveCaravan() {
        if (this.caravanHead != null) {
            this.caravanHead.caravanTail = null;
        }

        this.caravanHead = null;
    }

    public void joinCaravan(LibEntityLlama caravanHeadIn) {
        this.caravanHead = caravanHeadIn;
        this.caravanHead.caravanTail = this;
    }

    public boolean hasCaravanTrail() {
        return this.caravanTail != null;
    }

    public boolean inCaravan() {
        return this.caravanHead != null;
    }

    @Nullable
    public LibEntityLlama getCaravanHead() {
        return this.caravanHead;
    }

    @Override
    protected double followLeashSpeed() {
        return 2.0D;
    }

    @Override
    protected void followMother() {
        if (!this.inCaravan() && this.isChild()) {
            super.followMother();
        }
    }

    @Override
    public boolean canEatGrass() {
        return false;
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        this.spit(target);
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {
    }

    public static class AIDefendTarget extends EntityAINearestAttackableTarget<EntityWolf> {
        public AIDefendTarget(LibEntityLlama llama) {
            super(llama, EntityWolf.class, 16, false, true, null);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            if (super.shouldExecute() && this.targetEntity != null && !this.targetEntity.isTamed()) {
                return true;
            } else {
                this.taskOwner.setAttackTarget(null);
                return false;
            }
        }

        @Override
        protected double getTargetDistance() {
            return super.getTargetDistance() * 0.25D;
        }
    }

    public static class AIHurtByTarget extends EntityAIHurtByTarget {
        public AIHurtByTarget(LibEntityLlama llama) {
            super(llama, false);
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        @Override
        public boolean shouldContinueExecuting() {
            if (this.taskOwner instanceof LibEntityLlama) {
                LibEntityLlama entityllama = (LibEntityLlama) this.taskOwner;

                if (entityllama.didSpit) {
                    entityllama.setDidSpit(false);
                    return false;
                }
            }

            return super.shouldContinueExecuting();
        }
    }

    static class GroupData implements IEntityLivingData {
        public int variant;

        private GroupData(int variantIn) {
            this.variant = variantIn;
        }
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

    public IAttributeInstance registerAttribute(IAttribute attribute) {
        IAttributeInstance entry = this.getEntityAttribute(attribute);
        if (entry != null) {
            return entry;
        } else {
            return this.getAttributeMap().registerAttribute(attribute);
        }
    }
}