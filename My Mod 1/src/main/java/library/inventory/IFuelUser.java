package library.inventory;

import net.minecraft.item.ItemStack;

public interface IFuelUser {

    int getItemBurnTime(ItemStack stack);

    boolean isItemFuel(ItemStack stack);

}
