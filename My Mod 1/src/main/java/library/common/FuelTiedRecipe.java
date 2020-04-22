package library.common;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class FuelTiedRecipe {

    private IRecipe recipe;
    private ItemStack fuel;
    private int fuelBurnTime;

    public FuelTiedRecipe(IRecipe recipe, ItemStack fuel, int fuelBurnTime) {
        this.recipe = recipe;
        this.fuel = fuel;
        this.fuelBurnTime = fuelBurnTime;
    }

    public IRecipe getRecipe() {
        return recipe;
    }

    public void setRecipe(IRecipe recipe) {
        this.recipe = recipe;
    }

    public ItemStack getFuel() {
        return fuel;
    }

    public void setFuel(ItemStack fuel) {
        this.fuel = fuel;
    }

    public int getFuelBurnTime() {
        return fuelBurnTime;
    }

    public void setFuelBurnTime(int fuelBurnTime) {
        this.fuelBurnTime = fuelBurnTime;
    }
}
