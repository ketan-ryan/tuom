package library.common;

import library.items.LibItemTool;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Unify documentation for methods various base classes use
 */
public interface IJavaDocs {

    /**
     * Set a simple texture for item without need for json
     *
     * @param textureName
     * @param handheld
     * @return
     */
    Item setSingleTexture(ResourceLocation textureName, boolean handheld);

    /**
     * Mark item to be held like a weapon or tool in hand
     *
     * @param handheld
     * @return
     */
    Item setHandheld(boolean handheld);

    /**
     * Sets the scale of the item for appearance in the world
     *
     * @param scale Recommended ranges: 0.1 to 5
     * @return
     */
    Item setScale(float scale);

}
