package library.common;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.vecmath.Vector4f;

import org.lwjgl.util.vector.Vector3f;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

public class SingleTextureItemBakedModel implements IBakedModel {

    private TextureAtlasSprite sprite;
    private VertexFormat format;

    public static final float SCALE = 0.0625F;

    private static final ItemCameraTransforms HANDHELD = createHandheldTransforms(1.0f);
    private static final ItemCameraTransforms GENERATED = createGeneratedTransforms(1.0f);

    private HashMap<EnumFacing, List<BakedQuad>> cache = new HashMap<>();

    private static ItemCameraTransforms createGeneratedTransforms(float scale) {
        return new ItemCameraTransforms(
                ItemTransformVec3f.DEFAULT,
                new ItemTransformVec3f(new Vector3f(0, 0, 0), (Vector3f) new Vector3f(0, 3.0f, 1).scale(scale * SCALE), new Vector3f(0.55f * scale, 0.55f * scale, 0.55f * scale)),
                ItemTransformVec3f.DEFAULT,
                new ItemTransformVec3f(new Vector3f(0, -90, 25), (Vector3f) new Vector3f(1.13f, 3.2f, 1.13f).scale(scale * SCALE), new Vector3f(0.68f * scale, 0.68f * scale, 0.68f * scale)),
                new ItemTransformVec3f(new Vector3f(0, 180, 0), (Vector3f) new Vector3f(0, 13, 7).scale(scale * SCALE), new Vector3f(1, 1, 1)),
                ItemTransformVec3f.DEFAULT,
                new ItemTransformVec3f(new Vector3f(0, 0, 0), (Vector3f) new Vector3f(0, 2, 0).scale(scale * SCALE), new Vector3f(0.5f * scale, 0.5f * scale, 0.5f * scale)),
                new ItemTransformVec3f(new Vector3f(0, 180, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)));
    }

    private static ItemCameraTransforms createHandheldTransforms(float scale) {
        return new ItemCameraTransforms(
                new ItemTransformVec3f(new Vector3f(0, 90, -55), (Vector3f) new Vector3f(0, 4.0f, 0.5f).scale(scale * SCALE), new Vector3f(0.85f * scale, 0.85f * scale, 0.85f * scale)),
                new ItemTransformVec3f(new Vector3f(0, -90, 55), (Vector3f) new Vector3f(0, 4.0f, 0.5f).scale(scale * SCALE), new Vector3f(0.85f * scale, 0.85f * scale, 0.85f * scale)),
                new ItemTransformVec3f(new Vector3f(0, 90, -25), (Vector3f) new Vector3f(1.13f, 3.2f, 1.13f).scale(scale * SCALE), new Vector3f(0.68f * scale, 0.68f * scale, 0.68f * scale)),
                new ItemTransformVec3f(new Vector3f(0, -90, 25), (Vector3f) new Vector3f(1.13f, 3.2f, 1.13f).scale(scale * SCALE), new Vector3f(0.68f * scale, 0.68f * scale, 0.68f * scale)),
                new ItemTransformVec3f(new Vector3f(0, 180, 0), (Vector3f) new Vector3f(0, 13, 7).scale(scale * SCALE), new Vector3f(1, 1, 1)),
                ItemTransformVec3f.DEFAULT,
                new ItemTransformVec3f(new Vector3f(0, 0, 0), (Vector3f) new Vector3f(0, 2, 0).scale(scale * SCALE), new Vector3f(0.5f * scale, 0.5f * scale, 0.5f * scale)),
                new ItemTransformVec3f(new Vector3f(0, 180, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)));
    }


    private ItemCameraTransforms transforms;

    public SingleTextureItemBakedModel(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter, ResourceLocation texture, boolean handheld, float scale) {
        this.format = format;
        sprite = bakedTextureGetter.apply(texture);
        if (Math.abs(scale-1.0f) < 0.001f) {
            transforms = handheld ? HANDHELD : GENERATED;
        } else {
            transforms = handheld ? createHandheldTransforms(scale) : createGeneratedTransforms(scale);
        }
    }


    private static final BakedQuad buildQuad(
            VertexFormat format, Optional<TRSRTransformation> transform, EnumFacing side, TextureAtlasSprite sprite, int tint,
            float x0, float y0, float z0, float u0, float v0,
            float x1, float y1, float z1, float u1, float v1,
            float x2, float y2, float z2, float u2, float v2,
            float x3, float y3, float z3, float u3, float v3) {
        UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
        builder.setQuadTint(tint);
        builder.setQuadOrientation(side);
        builder.setTexture(sprite);
        putVertex(builder, format, transform, side, x0, y0, z0, u0, v0);
        putVertex(builder, format, transform, side, x1, y1, z1, u1, v1);
        putVertex(builder, format, transform, side, x2, y2, z2, u2, v2);
        putVertex(builder, format, transform, side, x3, y3, z3, u3, v3);
        return builder.build();
    }

    private static void putVertex(UnpackedBakedQuad.Builder builder, VertexFormat format, Optional<TRSRTransformation> transform, EnumFacing side, float x, float y, float z, float u, float v) {
        Vector4f vec = new Vector4f();
        for (int e = 0; e < format.getElementCount(); e++) {
            switch (format.getElement(e).getUsage()) {
                case POSITION:
                    if (transform.isPresent()) {
                        vec.x = x;
                        vec.y = y;
                        vec.z = z;
                        vec.w = 1;
                        transform.get().getMatrix().transform(vec);
                        builder.put(e, vec.x, vec.y, vec.z, vec.w);
                    } else {
                        builder.put(e, x, y, z, 1);
                    }
                    break;
                case COLOR:
                    builder.put(e, 1f, 1f, 1f, 1f);
                    break;
                case UV:
                    if (format.getElement(e).getIndex() == 0) {
                        builder.put(e, u, v, 0f, 1f);
                        break;
                    }
                case NORMAL:
                    builder.put(e, (float) side.getFrontOffsetX(), (float) side.getFrontOffsetY(), (float) side.getFrontOffsetZ(), 0f);
                    break;
                default:
                    builder.put(e);
                    break;
            }
        }
    }

    private static boolean isTransparent(int[] pixels, int uMax, int vMax, int u, int v)
    {
        //int test = (pixels[u + (vMax - 1 - v) * uMax] >> 24 & 0xFF);
        //System.out.println(test);
        return (pixels[u + (vMax - 1 - v) * uMax] >> 24 & 0xFF) == 0;
    }

    private static void addSideQuad(ImmutableList.Builder<BakedQuad> builder, BitSet faces, VertexFormat format, Optional<TRSRTransformation> transform, EnumFacing side, int tint, TextureAtlasSprite sprite, int uMax, int vMax, int u, int v)
    {
        int si = side.ordinal();
        if(si > 4) si -= 2;
        int index = (vMax + 1) * ((uMax + 1) * si + u) + v;
        if(!faces.get(index))
        {
            faces.set(index);
            builder.add(buildSideQuad(format, transform, side, tint, sprite, u, v));
        }
    }

    private static BakedQuad buildSideQuad(VertexFormat format, Optional<TRSRTransformation> transform, EnumFacing side, int tint, TextureAtlasSprite sprite, int u, int v)
    {
        final float eps0 = 30e-5f;
        final float eps1 = 45e-5f;
        final float eps2 = .5f;
        final float eps3 = .5f;
        float x0 = (float)u / sprite.getIconWidth();
        float y0 = (float)v / sprite.getIconHeight();
        float x1 = x0, y1 = y0;
        float z1 = 7.5f / 16f - eps1, z2 = 8.5f / 16f + eps1;
        switch(side)
        {
            case WEST:
                z1 = 8.5f / 16f + eps1;
                z2 = 7.5f / 16f - eps1;
            case EAST:
                y1 = (v + 1f) / sprite.getIconHeight();
                break;
            case DOWN:
                z1 = 8.5f / 16f + eps1;
                z2 = 7.5f / 16f - eps1;
            case UP:
                x1 = (u + 1f) / sprite.getIconWidth();
                break;
            default:
                throw new IllegalArgumentException("can't handle z-oriented side");
        }
        float u0 = 16f * (x0 - side.getDirectionVec().getX() * eps3 / sprite.getIconWidth());
        float u1 = 16f * (x1 - side.getDirectionVec().getX() * eps3 / sprite.getIconWidth());
        float v0 = 16f * (1f - y0 - side.getDirectionVec().getY() * eps3 / sprite.getIconHeight());
        float v1 = 16f * (1f - y1 - side.getDirectionVec().getY() * eps3 / sprite.getIconHeight());
        switch(side)
        {
            case WEST:
            case EAST:
                y0 -= eps1;
                y1 += eps1;
                v0 -= eps2 / sprite.getIconHeight();
                v1 += eps2 / sprite.getIconHeight();
                break;
            case DOWN:
            case UP:
                x0 -= eps1;
                x1 += eps1;
                u0 += eps2 / sprite.getIconWidth();
                u1 -= eps2 / sprite.getIconWidth();
                break;
            default:
                throw new IllegalArgumentException("can't handle z-oriented side");
        }
        switch(side)
        {
            case WEST:
                x0 += eps0;
                x1 += eps0;
                break;
            case EAST:
                x0 -= eps0;
                x1 -= eps0;
                break;
            case DOWN:
                y0 -= eps0;
                y1 -= eps0;
                break;
            case UP:
                y0 += eps0;
                y1 += eps0;
                break;
            default:
                throw new IllegalArgumentException("can't handle z-oriented side");
        }
        return buildQuad(
                format, transform, side.getOpposite(), sprite, tint, // getOpposite is related either to the swapping of V direction, or something else
                x0, y0, z1, sprite.getInterpolatedU(u0), sprite.getInterpolatedV(v0),
                x1, y1, z1, sprite.getInterpolatedU(u1), sprite.getInterpolatedV(v1),
                x1, y1, z2, sprite.getInterpolatedU(u1), sprite.getInterpolatedV(v1),
                x0, y0, z2, sprite.getInterpolatedU(u0), sprite.getInterpolatedV(v0)
        );
    }

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {

        if (cache.containsKey(side)) {
            return cache.get(side);
        }

        ImmutableList.Builder<BakedQuad> builder = ImmutableList.builder();

        int uMax = sprite.getIconWidth();
        int vMax = sprite.getIconHeight();

        int tint = 0;
        Optional<TRSRTransformation> transform = Optional.of(TRSRTransformation.identity());

        BitSet faces = new BitSet((uMax + 1) * (vMax + 1) * 4);
        for(int f = 0; f < sprite.getFrameCount(); f++)
        {
            int[] pixels = sprite.getFrameTextureData(f)[0];
            boolean ptu;
            boolean[] ptv = new boolean[uMax];
            Arrays.fill(ptv, true);
            for(int v = 0; v < vMax; v++)
            {
                ptu = true;
                for(int u = 0; u < uMax; u++)
                {
                    boolean t = isTransparent(pixels, uMax, vMax, u, v);
                    if(ptu && !t) // left - transparent, right - opaque
                    {
                        addSideQuad(builder, faces, format, transform, EnumFacing.WEST, tint, sprite, uMax, vMax, u, v);
                    }
                    if(!ptu && t) // left - opaque, right - transparent
                    {
                        addSideQuad(builder, faces, format, transform, EnumFacing.EAST, tint, sprite, uMax, vMax, u, v);
                    }
                    if(ptv[u] && !t) // up - transparent, down - opaque
                    {
                        addSideQuad(builder, faces, format, transform, EnumFacing.UP, tint, sprite, uMax, vMax, u, v);
                    }
                    if(!ptv[u] && t) // up - opaque, down - transparent
                    {
                        addSideQuad(builder, faces, format, transform, EnumFacing.DOWN, tint, sprite, uMax, vMax, u, v);
                    }
                    ptu = t;
                    ptv[u] = t;
                }
                if(!ptu) // last - opaque
                {
                    addSideQuad(builder, faces, format, transform, EnumFacing.EAST, tint, sprite, uMax, vMax, uMax, v);
                }
            }
            // last line
            for(int u = 0; u < uMax; u++)
            {
                if(!ptv[u])
                {
                    addSideQuad(builder, faces, format, transform, EnumFacing.DOWN, tint, sprite, uMax, vMax, u, vMax);
                }
            }
        }
        // front
        builder.add(buildQuad(format, transform, EnumFacing.NORTH, sprite, tint,
                0, 0, 7.5f / 16f, sprite.getMinU(), sprite.getMaxV(),
                0, 1, 7.5f / 16f, sprite.getMinU(), sprite.getMinV(),
                1, 1, 7.5f / 16f, sprite.getMaxU(), sprite.getMinV(),
                1, 0, 7.5f / 16f, sprite.getMaxU(), sprite.getMaxV()
        ));
        // back
        builder.add(buildQuad(format, transform, EnumFacing.SOUTH, sprite, tint,
                0, 0, 8.5f / 16f, sprite.getMinU(), sprite.getMaxV(),
                1, 0, 8.5f / 16f, sprite.getMaxU(), sprite.getMaxV(),
                1, 1, 8.5f / 16f, sprite.getMaxU(), sprite.getMinV(),
                0, 1, 8.5f / 16f, sprite.getMinU(), sprite.getMinV()
        ));
        cache.put(side, builder.build());
        return cache.get(side);

    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.NONE;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return sprite;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return transforms;
    }
}
