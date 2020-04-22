package library.entities.mobs.entities;

import library.entities.LibEntityChestHorse;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
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
import java.util.UUID;

public class LibEntityHorse extends LibEntityChestHorse {

    /** COMMON VARIABLES **/

    // Sounds
    protected SoundEvent ambientSound = SoundEvents.ENTITY_HORSE_AMBIENT;
    protected SoundEvent deathSound = SoundEvents.ENTITY_HORSE_DEATH;
    protected SoundEvent hurtSound = SoundEvents.ENTITY_HORSE_HURT;
    protected SoundEvent angrySound = SoundEvents.ENTITY_HORSE_ANGRY;

    // Loot table
    protected ResourceLocation lootTable = LootTableList.ENTITIES_HORSE;

    /** COMMON METHODS **/

    // Sets the Entity's attributes
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        float maxHealth = this.getModifiedMaxHealth();
        double movementSpeed = this.getModifiedMovementSpeed();
        double jumpStrength = this.getModifiedJumpStrength();
        double attackDamage = 1D; // how many half-hearts of damage it deals

        this.registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(movementSpeed);
        this.getEntityAttribute(JUMP_STRENGTH).setBaseValue(jumpStrength);
    }

    // Sets the Entity's core behaviors
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.2D));
        this.tasks.addTask(1, new EntityAIRunAroundLikeCrazy(this, 1.2D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D, AbstractHorse.class));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.7D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
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
        super.getAmbientSound();
        return ambientSound;
    }

    @Override
    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return deathSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        super.getHurtSound(p_184601_1_);
        return hurtSound;
    }

    @Override
    protected SoundEvent getAngrySound() {
        super.getAngrySound();
        return angrySound;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return lootTable;
    }


    /** OTHER METHODS **/

    private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
    private static final DataParameter<Integer> HORSE_VARIANT = EntityDataManager.<Integer>createKey(net.minecraft.entity.passive.EntityHorse.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> HORSE_ARMOR = EntityDataManager.<Integer>createKey(net.minecraft.entity.passive.EntityHorse.class, DataSerializers.VARINT);
    private static final String[] HORSE_TEXTURES = new String[]{"textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png"};
    private static final String[] HORSE_TEXTURES_ABBR = new String[]{"hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
    private static final String[] HORSE_MARKING_TEXTURES = new String[]{null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png"};
    private static final String[] HORSE_MARKING_TEXTURES_ABBR = new String[]{"", "wo_", "wmo", "wdo", "bdo"};
    private String texturePrefix;
    private final String[] horseTexturesArray = new String[3];

    public LibEntityHorse(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(HORSE_VARIANT, 0);
        this.dataManager.register(HORSE_ARMOR, HorseArmorType.NONE.getOrdinal());
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
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getHorseVariant());

        if (!this.horseChest.getStackInSlot(1).isEmpty()) {
            compound.setTag("ArmorItem", this.horseChest.getStackInSlot(1).writeToNBT(new NBTTagCompound()));
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setHorseVariant(compound.getInteger("Variant"));

        if (compound.hasKey("ArmorItem", 10)) {
            ItemStack itemstack = new ItemStack(compound.getCompoundTag("ArmorItem"));

            if (!itemstack.isEmpty() && HorseArmorType.isHorseArmor(itemstack.getItem())) {
                this.horseChest.setInventorySlotContents(1, itemstack);
            }
        }

        this.updateHorseSlots();
    }

    public void setHorseVariant(int variant) {
        this.dataManager.set(HORSE_VARIANT, variant);
        this.resetTexturePrefix();
    }

    public int getHorseVariant() {
        return this.dataManager.get(HORSE_VARIANT);
    }

    private void resetTexturePrefix() {
        this.texturePrefix = null;
    }

    @SideOnly(Side.CLIENT)
    private void setHorseTexturePaths() {
        int i = this.getHorseVariant();
        int j = (i & 255) % 7;
        int k = ((i & 65280) >> 8) % 5;
        HorseArmorType horsearmortype = this.getHorseArmorType();
        this.horseTexturesArray[0] = HORSE_TEXTURES[j];
        this.horseTexturesArray[1] = HORSE_MARKING_TEXTURES[k];
        this.horseTexturesArray[2] = horsearmortype.getTextureName();
        this.texturePrefix = "horse/" + HORSE_TEXTURES_ABBR[j] + HORSE_MARKING_TEXTURES_ABBR[k] + horsearmortype.getHash();
    }

    @SideOnly(Side.CLIENT)
    public String getHorseTexture() {
        if (this.texturePrefix == null) {
            this.setHorseTexturePaths();
        }

        return this.texturePrefix;
    }

    @SideOnly(Side.CLIENT)
    public String[] getVariantTexturePaths() {
        if (this.texturePrefix == null) {
            this.setHorseTexturePaths();
        }

        return this.horseTexturesArray;
    }

    /**
     * Updates the items in the saddle and armor slots of the horse's inventory.
     */
    @Override
    protected void updateHorseSlots() {
        super.updateHorseSlots();
        this.setHorseArmorStack(this.horseChest.getStackInSlot(1));
    }

    /**
     * Set horse armor stack (for example: new ItemStack(Items.iron_horse_armor))
     */
    public void setHorseArmorStack(ItemStack itemStackIn) {
        HorseArmorType horsearmortype = HorseArmorType.getByItemStack(itemStackIn);
        this.dataManager.set(HORSE_ARMOR, horsearmortype.getOrdinal());
        this.resetTexturePrefix();

        if (!this.world.isRemote) {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);
            int i = horsearmortype.getProtection();

            if (i != 0) {
                this.getEntityAttribute(SharedMonsterAttributes.ARMOR).applyModifier((new AttributeModifier(ARMOR_MODIFIER_UUID, "Horse armor bonus", i, 0)).setSaved(false));
            }
        }
    }

    public HorseArmorType getHorseArmorType() {
        return HorseArmorType.getByOrdinal(this.dataManager.get(HORSE_ARMOR));
    }

    /**
     * Called by InventoryBasic.onInventoryChanged() on a array that is never filled.
     */
    @Override
    public void onInventoryChanged(IInventory invBasic) {
        HorseArmorType horsearmortype = this.getHorseArmorType();
        super.onInventoryChanged(invBasic);
        HorseArmorType horsearmortype1 = this.getHorseArmorType();

        if (this.ticksExisted > 20 && horsearmortype != horsearmortype1 && horsearmortype1 != HorseArmorType.NONE) {
            this.playSound(SoundEvents.ENTITY_HORSE_ARMOR, 0.5F, 1.0F);
        }
    }

    @Override
    protected void playGallopSound(SoundType p_190680_1_) {
        super.playGallopSound(p_190680_1_);

        if (this.rand.nextInt(10) == 0) {
            this.playSound(SoundEvents.ENTITY_HORSE_BREATHE, p_190680_1_.getVolume() * 0.6F, p_190680_1_.getPitch());
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.world.isRemote && this.dataManager.isDirty()) {
            this.dataManager.setClean();
            this.resetTexturePrefix();
        }
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        boolean flag = !itemstack.isEmpty();

        if (flag && itemstack.getItem() == Items.SPAWN_EGG) {
            return super.processInteract(player, hand);
        } else {
            if (!this.isChild()) {
                if (this.isTame() && player.isSneaking()) {
                    this.openGUI(player);
                    return true;
                }

                if (this.isBeingRidden()) {
                    return super.processInteract(player, hand);
                }
            }

            if (flag) {
                if (this.handleEating(player, itemstack)) {
                    if (!player.capabilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }

                    return true;
                }

                if (itemstack.interactWithEntity(player, this, hand)) {
                    return true;
                }

                if (!this.isTame()) {
                    this.makeMad();
                    return true;
                }

                boolean flag1 = HorseArmorType.getByItemStack(itemstack) != HorseArmorType.NONE;
                boolean flag2 = !this.isChild() && !this.isHorseSaddled() && itemstack.getItem() == Items.SADDLE;

                if (flag1 || flag2) {
                    this.openGUI(player);
                    return true;
                }
            }

            if (this.isChild()) {
                return super.processInteract(player, hand);
            } else {
                this.mountTo(player);
                return true;
            }
        }
    }


    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if (!(otherAnimal instanceof LibEntityHorse)) {
            return false;
        } else {
            return this.canMate() && ((LibEntityHorse) otherAnimal).canMate();
        }
    }

    @Override
    public boolean wearsArmor() {
        return true;
    }

    @Override
    public boolean isArmor(ItemStack stack) {
        return HorseArmorType.isHorseArmor(stack.getItem());
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        int i;

        if (livingdata instanceof net.minecraft.entity.passive.EntityHorse.GroupData) {
            i = ((net.minecraft.entity.passive.EntityHorse.GroupData) livingdata).variant;
        } else {
            i = this.rand.nextInt(7);
            livingdata = new net.minecraft.entity.passive.EntityHorse.GroupData(i);
        }

        this.setHorseVariant(i | this.rand.nextInt(5) << 8);
        return livingdata;
    }

    public static class GroupData implements IEntityLivingData {
        public int variant;

        public GroupData(int variantIn) {
            this.variant = variantIn;
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        int i = 0;

        if (entityIn instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entityIn).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag)
        {
            if (i > 0 && entityIn instanceof EntityLivingBase)
            {
                ((EntityLivingBase)entityIn).knockBack(this, (float)i * 0.5F, (double) MathHelper.sin(this.rotationYaw * 0.017453292F), (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                entityIn.setFire(j * 4);
            }

            if (entityIn instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer))
                {
                    float f1 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                    if (this.rand.nextFloat() < f1)
                    {
                        entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
                        this.world.setEntityState(entityplayer, (byte)30);
                    }
                }
            }

            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }
}