package library.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class LibSlotIngredient extends Slot {

    public LibSlotIngredient(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
}
