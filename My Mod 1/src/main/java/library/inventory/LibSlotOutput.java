package library.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;

public class LibSlotOutput extends SlotFurnaceOutput {

    public LibSlotOutput(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(null, inventoryIn, slotIndex, xPosition, yPosition);
    }

    @Override
    protected void onCrafting(ItemStack stack) {

    }
}
