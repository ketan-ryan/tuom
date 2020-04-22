package library.common;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

public class SingleTextureModel implements IModel {

    private final ResourceLocation texture;
    private final boolean handheld;
    private final float scale;

    public SingleTextureModel(ResourceLocation texture, boolean handheld, float scale) {
        this.texture = texture;
        this.handheld = handheld;
        this.scale = scale;
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return new SingleTextureItemBakedModel(state, format, bakedTextureGetter, texture, handheld, scale);
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return Collections.emptySet();
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return ImmutableSet.of(texture);
    }

    @Override
    public IModelState getDefaultState() {
        return TRSRTransformation.identity();
    }
}
