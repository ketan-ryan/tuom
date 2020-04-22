package library.common;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * An item for the registry (block or item)
 */
public interface IRegistryObject {

    /**
     * Initialize the model. The block or item has to implement this so that model specific initialization
     * can be done. The basic LibItem, LibItemArmor and LibBlockSimple already have a default implementation
     * of this that works for most blocks and items.
     */
    @SideOnly(Side.CLIENT)
    void initModel();

    /**
     * Implement this in your block or item to setup a recipe for this block or item. The default implementation
     * in LibItem, LibItemArmor and LibBlockSimple is empty.
     */
    void initRecipe();

    /**
     * The unique name of this object (registry name)
     */
    String getUniqueName();
}
