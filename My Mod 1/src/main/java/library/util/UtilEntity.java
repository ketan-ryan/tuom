package library.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.lang.reflect.Constructor;

public class UtilEntity {
    
    public static void tickFire(EntityLivingBase ent, boolean burns) {
        if (ent.world.isDaytime() && !ent.world.isRemote && !ent.isChild() && burns) {
            float f = ent.getBrightness();

            if (f > 0.5F && ent.getRNG().nextFloat() * 30.0F < (f - 0.4F) * 2.0F && ent.world
                    .canSeeSky(new BlockPos(ent.posX, ent.posY + ent.getEyeHeight(), ent.posZ))) {
                boolean flag = true;
                ItemStack itemstack = ent.getItemStackFromSlot(EntityEquipmentSlot.HEAD);

                if (!itemstack.isEmpty()) {
                    if (itemstack.isItemStackDamageable()) {
                        itemstack.setItemDamage(itemstack.getItemDamage() + ent.getRNG().nextInt(2));

                        if (itemstack.getItemDamage() >= itemstack.getMaxDamage()) {
                            ent.renderBrokenItemStack(itemstack);
                            ent.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if (flag) {
                    ent.setFire(8);
                }
            }
        }
    }

    public static EntityAgeable createCopy(EntityAgeable entity) {
        try {
            Constructor ctr = entity.getClass().getConstructor(World.class);
            return (EntityAgeable)ctr.newInstance(entity.world);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
