package library.entities.mobs.entities;

import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class LibEntityCow extends LibEntityGenericPassive
{

    /** COMMON VARIABLES **/

    // Sounds
    protected SoundEvent ambientSound = SoundEvents.ENTITY_COW_AMBIENT;
    protected SoundEvent hurtSound = SoundEvents.ENTITY_COW_HURT;
    protected SoundEvent deathSound = SoundEvents.ENTITY_COW_DEATH;
    protected SoundEvent stepSound = SoundEvents.ENTITY_COW_STEP;

    // Loot table
    protected ResourceLocation lootTable = LootTableList.ENTITIES_PIG;

    /** COMMON METHODS **/

    // Sets the Entity's attributes
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        double maxHealth = 10.0D; // max health
        double movementSpeed = 0.20D; // how fast it moves normally
        double attackDamage = 1D; // how many half-hearts of damage it deals

        this.registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(movementSpeed);
    }

    // Sets the Entity's core behaviors
    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.WHEAT, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ambientSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return hurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return deathSound;
    }

    protected SoundEvent getStepSound()
    {
        return stepSound;
    }

    /** OTHER METHODS **/

    public LibEntityCow(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.4F);
    }

    public static void registerFixesCow(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, LibEntityCow.class);
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(getStepSound(), 0.15F, 1.0F);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_COW;
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild())
        {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            itemstack.shrink(1);

            if (itemstack.isEmpty())
            {
                player.setHeldItem(hand, new ItemStack(Items.MILK_BUCKET));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET)))
            {
                player.dropItem(new ItemStack(Items.MILK_BUCKET), false);
            }

            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }

    public float getEyeHeight()
    {
        return this.isChild() ? this.height : 1.3F;
    }
}