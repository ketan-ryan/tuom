package library.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Conditions {

    /**
     * Return true if the given amount of seconds has gone by
     *
     * @param world
     * @param seconds
     * @return
     */
    public static boolean secondsGoneBy(World world, int seconds) {
        if (seconds == 0) return true;
        return world.getTotalWorldTime() % (seconds * 20) == 0;
    }

    /**
     * Return true if the given amount of game ticks has gone by
     * @param world
     * @param ticks
     * @return
     */
    public static boolean ticksGoneBy(World world, int ticks) {
        if (ticks == 0) return true;
        return world.getTotalWorldTime() % (ticks) == 0;
    }

    /**
     * Return true if the given block or item is found in the entities inventory
     *
     * @param entity EntityLivingBase or Player
     * @param item   Block or Item
     * @return
     */
    public static boolean isItemInInventory(Entity entity, Object item) {
        if (!(entity instanceof EntityLivingBase)) return false;
        EntityLivingBase ent = (EntityLivingBase)entity;
        List<Object> items = new ArrayList<>();
        items.add(item);
        for (int i = 0; i < items.size(); i++) {

            Object itemToAdd = items.get(i);

            ItemStack newStack = null;

            if (itemToAdd instanceof net.minecraft.item.Item) {
                newStack = new ItemStack((net.minecraft.item.Item) itemToAdd, 1);
            } else if (itemToAdd instanceof net.minecraft.block.Block) {
                newStack = new ItemStack((net.minecraft.block.Block) itemToAdd, 1);
            }

            if (newStack != null) {

                boolean metaUsed = false;

                if (ent instanceof EntityPlayer) {

                    EntityPlayer player = (EntityPlayer)ent;

                    //Main inventory
                    for (int ii = 0; ii < player.inventory.mainInventory.size(); ii++) {
                        ItemStack playerSlotStack = player.inventory.mainInventory.get(ii);

                        if (!playerSlotStack.isEmpty() && playerSlotStack.getItem() == newStack.getItem() && (!metaUsed || playerSlotStack.getItemDamage() == newStack.getItemDamage())) {
                            return true;
                            //player.inventory.mainInventory.set(ii, ItemStack.EMPTY);
                        }
                    }

                    //Armor
                    for (int ii = 0; ii < player.inventory.armorInventory.size(); ii++) {
                        ItemStack playerSlotStack = player.inventory.armorInventory.get(ii);

                        if (!playerSlotStack.isEmpty() && playerSlotStack.getItem() == newStack.getItem() && (!metaUsed || playerSlotStack.getItemDamage() == newStack.getItemDamage())) {
                            return true;
                        }
                    }

                    //Cursor
                    if (!player.inventory.getItemStack().isEmpty()
                            && player.inventory.getItemStack().getItem() == newStack.getItem() && player.inventory.getItemStack().getItemDamage() == newStack.getItemDamage()) {
                        return true;
                    }

                    //Offhand inventory
                    for (int ii = 0; ii < player.inventory.offHandInventory.size(); ii++) {
                        ItemStack playerSlotStack = player.inventory.offHandInventory.get(ii);

                        if (!playerSlotStack.isEmpty() && playerSlotStack.getItem() == newStack.getItem() && (!metaUsed || playerSlotStack.getItemDamage() == newStack.getItemDamage())) {
                            return true;
                        }
                    }
                } else if (ent instanceof EntityLiving) {
                    EntityLiving entL = (EntityLiving)ent;

                    return newStack.isItemEqualIgnoreDurability(entL.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND)) ||
                            newStack.isItemEqualIgnoreDurability(entL.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND)) ||
                            newStack.isItemEqualIgnoreDurability(entL.getItemStackFromSlot(EntityEquipmentSlot.CHEST)) ||
                            newStack.isItemEqualIgnoreDurability(entL.getItemStackFromSlot(EntityEquipmentSlot.FEET)) ||
                            newStack.isItemEqualIgnoreDurability(entL.getItemStackFromSlot(EntityEquipmentSlot.HEAD)) ||
                            newStack.isItemEqualIgnoreDurability(entL.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
                    //return newStack.isItemEqualIgnoreDurability(ent.getHeldItem(EnumHand.MAIN_HAND));
                }
            }


        }
        return false;
    }

    /**
     * Return true if the given block or item is in the entities hand
     *
     * @param entity EntityLivingBase or EntityPlayer
     * @param item   Block or Item
     * @return
     */
    public static boolean isItemInHand(Entity entity, Object item) {
        if (!(entity instanceof EntityLivingBase)) return false;
        EntityLivingBase ent = (EntityLivingBase)entity;
        ItemStack newStack = null;

        if (item instanceof net.minecraft.item.Item) {
            newStack = new ItemStack((net.minecraft.item.Item) item, 1);
        } else if (item instanceof net.minecraft.block.Block) {
            newStack = new ItemStack((net.minecraft.block.Block) item, 1);
        }

        if (newStack != null) {
            return newStack.isItemEqualIgnoreDurability(ent.getHeldItem(EnumHand.MAIN_HAND));
        }
        return false;
    }

    /**
     * Return true if the given armor set is equipped on the entity
     *
     * @param entity
     * @param helm
     * @param chest
     * @param leggings
     * @param feet
     * @return
     */
    public static boolean isArmorSetEquipped(EntityLivingBase entity, Item helm, Item chest, Item leggings, Item feet) {

        Iterable<ItemStack> listArmorSet = null;

        if (entity instanceof EntityPlayer) {
            listArmorSet = ((EntityPlayer) entity).inventory.armorInventory;
        } else if (entity instanceof EntityLiving) {
            listArmorSet = entity.getArmorInventoryList();
        }

        int i = 0;

        for (ItemStack stack : listArmorSet) {
            if (i == 0 && stack.getItem() != feet) {
                return false;
            } else if (i == 1 && stack.getItem() != leggings) {
                return false;
            } else if (i == 2 && stack.getItem() != chest) {
                return false;
            } else if (i == 3 && stack.getItem() != helm) {
                return false;
            }
            i++;
        }

        return true;
    }

    /**
     * Return true if a specific entity class is being ridding by the entity
     *
     * @param entityRidden
     * @param entityRider
     * @return
     */
    public static boolean isEntityRidden(Entity entityRidden, Class entityRider) {
        Entity ent = entityRidden.getPassengers().isEmpty() ? null : entityRidden.getPassengers().get(0);
        return ent != null && entityRider.isAssignableFrom(ent.getClass());
    }

    /**
     * Return true if an entity is riding a specific entity class
     *
     * @param entityRider
     * @param entityRidden
     * @return
     */
    public static boolean isEntityRiding(Entity entityRider, Class entityRidden) {
        return entityRider.getRidingEntity() != null && entityRidden.isAssignableFrom(entityRider.getRidingEntity().getClass());
    }

}
