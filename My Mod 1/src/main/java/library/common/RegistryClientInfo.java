package library.common;

import net.minecraft.util.ResourceLocation;

public class RegistryClientInfo {

    private final ResourceLocation texture;
    private final boolean handheld;
    private final float scale;

    public RegistryClientInfo(ResourceLocation texture, boolean handheld, float scale) {
        this.texture = texture;
        this.handheld = handheld;
        this.scale = scale;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public boolean isHandheld() {
        return handheld;
    }

    public float getScale() {
        return scale;
    }
}
