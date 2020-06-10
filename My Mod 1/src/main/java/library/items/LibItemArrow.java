package library.items;

import library.LibRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class LibItemArrow extends LibItem {

    public LibItemArrow() {
    	super();
    }

    /**
     * Get the entity class to spawn when the bow shoots
     *
     * @return
     */
    public abstract Class<? extends EntityTippedArrow> getEntityProjectile();

    public boolean isInfinite(ItemStack stack, ItemStack bow, EntityPlayer player) {
        return false;
    }
}
