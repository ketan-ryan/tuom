package library.items;

import library.LibRegistry;
import library.entities.projectile.LibEntityArrow;
import library.entities.projectile.LibEntityProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Constructor;

public abstract class LibItemProjectile extends LibItem {

    public LibItemProjectile() {
        super();
        this.maxStackSize = 16;
    }
    public float throwSpeed = 1.5F;
    public int throwRate = 20;
    public float throwInnacuracy = 1F;

    /**
     * Create the entity when this item is thrown
     */
    //protected abstract EntityThrowable createEntity(World world, EntityPlayer player);
    public abstract Class<? extends LibEntityProjectile> getEntityProjectile();

    /**
     * Called when the equipped item is right clicked.
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.capabilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, getThrowSound(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        playerIn.getCooldownTracker().setCooldown(this, throwRate);

        if (!worldIn.isRemote) {
            //EntityThrowable entityThrowable = createEntity(worldIn, playerIn);
            try {
                Constructor constructor = getEntityProjectile().getConstructor(World.class);
                LibEntityProjectile entityThrowable = (LibEntityProjectile)constructor.newInstance(worldIn);
                entityThrowable.setPosition(playerIn.posX, playerIn.posY + (double)playerIn.getEyeHeight() - 0.10000000149011612D, playerIn.posZ);
                entityThrowable.setThrower(playerIn);
                entityThrowable.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, throwSpeed, throwInnacuracy);
                worldIn.spawnEntity(entityThrowable);

            } catch (Exception ex) {
                ex.printStackTrace();
                return new ActionResult<>(EnumActionResult.FAIL, itemstack);
            }

        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    public SoundEvent getThrowSound() {
        return SoundEvents.ENTITY_ENDERPEARL_THROW;
    }
}