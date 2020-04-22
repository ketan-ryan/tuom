package library.entities;

import library.entities.mobs.models.*;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LibRenderMob extends RenderLiving<EntityLiving> {

    private ResourceLocation texture;
    private float scale;

    public LibRenderMob(RenderManager manager, Class<? extends EntityLiving> entityClass, ModelBase model) {
        super(manager, model, 0.5F);
        this.scale = 1.0f;
        if (getMainModel() instanceof LibModelBase) {
            if (((LibModelBase) getMainModel()).getModelForItem() != null) {
                this.addLayer(new LibLayerHeldItem(this));
            }
        } else if (getMainModel() instanceof LibModelSkeleton ||
                getMainModel() instanceof LibModelZombie) {
            this.addLayer(new LayerHeldItem(this));
        }
    }

    public LibRenderMob setTexture(ResourceLocation texture) {
        this.texture = texture;
        return this;
    }

    public LibRenderMob setScale(float scale) {
        this.scale = scale;
        this.shadowSize = scale;
        if (getMainModel() instanceof LibModelBase) {
            this.shadowSize = scale * ((LibModelBase) getMainModel()).getShadowScale();
            ((LibModelBase) getMainModel()).extraScaleForRender = scale;
        }
        return this;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLiving entity) {
        return texture;
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    @Override
    protected void preRenderCallback(EntityLiving entity, float partialTickTime) {
        GlStateManager.scale(scale, scale, scale);
    }

    @Override
    public void doRender(EntityLiving entity, double x, double y, double z, float entityYaw, float partialTicks) {

        //temp
        /*mainModel = new LibModelElephant();
        texture = new ResourceLocation(LibRegistry.getModid(), "textures/entities/test/bike.png");
        setScale(2F);*/
        //setScale(1F);
        float oldScale = this.scale;

        if (mainModel instanceof LibModelGhast) {
            setScale(this.scale * 4F);
        }

        GlStateManager.pushMatrix();
        if (entity.isChild() && !(getMainModel() instanceof LibModelZombie) &&
                !(getMainModel() instanceof LibModelOcelot) &&
                !(getMainModel() instanceof LibModelPig) &&
                !(getMainModel() instanceof LibModelCow) &&
                /*!(getMainModel() instanceof LibModelSheep) &&*/
                !(getMainModel() instanceof LibModelSkeleton) &&
                !(getMainModel() instanceof LibModelRabbit) &&
                !(getMainModel() instanceof LibModelLlama) &&
                !(getMainModel() instanceof LibModelPolarBear)) {
            setScale(this.scale * 0.5F);
            /*GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * 0.0625F, 0.0F);*/
        }

        if (mainModel instanceof LibModelBase) {
            ((LibModelBase) mainModel).resetRotations();
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        setScale(oldScale);

        GlStateManager.popMatrix();
    }

    @Override
    protected void renderModel(EntityLiving entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {

        /*if (entitylivingbaseIn.isChild()) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scaleFactor, 0.0F);
        }*/


        //GlStateManager.enableAlpha();
        boolean fadeOutDeath = false;
        float fadeOutTicks = 20F;
        if (entitylivingbaseIn instanceof IDeathFade) {
            fadeOutDeath = ((IDeathFade) entitylivingbaseIn).isFadeOutDeath();
            fadeOutTicks = ((IDeathFade) entitylivingbaseIn).getFadeOutTicks();
        }

        //System.out.println(entitylivingbaseIn.deathTime + " - " + entitylivingbaseIn.hurtTime);

        if (fadeOutDeath && entitylivingbaseIn.deathTime > 0) {

            GlStateManager.enableBlend();
            GlStateManager.depthMask(false);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.alphaFunc(516, 0.003921569F);
            float f = 1F - ((float)entitylivingbaseIn.deathTime) / fadeOutTicks;
            GlStateManager.color(1.0F, 1.0F, 1.0F, f);
        }
        super.renderModel(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        if (fadeOutDeath) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1F);
            GlStateManager.depthMask(true);
            GlStateManager.disableBlend();
            GlStateManager.alphaFunc(516, 0.1F);
        }
    }

    @Override
    protected void applyRotations(EntityLiving entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);

        boolean fadeOutDeath = false;
        if (entityLiving instanceof IDeathFade) {
            fadeOutDeath = ((IDeathFade) entityLiving).isFadeOutDeath();
        }

        if (entityLiving.deathTime > 0 && !fadeOutDeath)
        {
            float f = ((float)entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
            f = MathHelper.sqrt(f);

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            GlStateManager.rotate(f * this.getDeathMaxRotation(entityLiving), 0.0F, 0.0F, 1.0F);
        }
        else
        {
            String s = TextFormatting.getTextWithoutFormattingCodes(entityLiving.getName());

            if (s != null && ("Dinnerbone".equals(s) || "Grumm".equals(s)))
            {
                GlStateManager.translate(0.0F, entityLiving.height + 0.1F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            }
        }
    }

    private static final DynamicTexture TEXTURE_BRIGHTNESS = new DynamicTexture(16, 16);
    @Override
    protected boolean setBrightness(EntityLiving entitylivingbaseIn, float partialTicks, boolean combineTextures) {
        //return super.setBrightness(entitylivingbaseIn, partialTicks, combineTextures);
        float f = entitylivingbaseIn.getBrightness();
        int i = this.getColorMultiplier(entitylivingbaseIn, f, partialTicks);
        boolean flag = (i >> 24 & 255) > 0;
        boolean flag1 = entitylivingbaseIn.hurtTime > 0 || entitylivingbaseIn.deathTime > 0;

        if (entitylivingbaseIn instanceof IDeathFade) {
            if (((IDeathFade) entitylivingbaseIn).isFadeOutDeath()) {
                flag1 = entitylivingbaseIn.hurtTime > 0 && entitylivingbaseIn.deathTime <= 0;
            }
        }

        if (!flag && !flag1)
        {
            return false;
        }
        else if (!flag && !combineTextures)
        {
            return false;
        }
        else
        {
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            GlStateManager.enableTexture2D();
            GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.defaultTexUnit);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PRIMARY_COLOR);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.defaultTexUnit);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.enableTexture2D();
            GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, OpenGlHelper.GL_INTERPOLATE);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_CONSTANT);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.GL_PREVIOUS);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE2_RGB, OpenGlHelper.GL_CONSTANT);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND2_RGB, 770);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
            this.brightnessBuffer.position(0);

            if (flag1)
            {
                this.brightnessBuffer.put(1.0F);
                this.brightnessBuffer.put(0.0F);
                this.brightnessBuffer.put(0.0F);
                this.brightnessBuffer.put(0.3F);
            }
            else
            {
                float f1 = (float)(i >> 24 & 255) / 255.0F;
                float f2 = (float)(i >> 16 & 255) / 255.0F;
                float f3 = (float)(i >> 8 & 255) / 255.0F;
                float f4 = (float)(i & 255) / 255.0F;
                this.brightnessBuffer.put(f2);
                this.brightnessBuffer.put(f3);
                this.brightnessBuffer.put(f4);
                this.brightnessBuffer.put(1.0F - f1);
            }

            this.brightnessBuffer.flip();
            GlStateManager.glTexEnv(8960, 8705, this.brightnessBuffer);
            GlStateManager.setActiveTexture(OpenGlHelper.GL_TEXTURE2);
            GlStateManager.enableTexture2D();
            GlStateManager.bindTexture(TEXTURE_BRIGHTNESS.getGlTextureId());
            GlStateManager.glTexEnvi(8960, 8704, OpenGlHelper.GL_COMBINE);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_RGB, 8448);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_RGB, OpenGlHelper.GL_PREVIOUS);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE1_RGB, OpenGlHelper.lightmapTexUnit);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_RGB, 768);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND1_RGB, 768);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_COMBINE_ALPHA, 7681);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_SOURCE0_ALPHA, OpenGlHelper.GL_PREVIOUS);
            GlStateManager.glTexEnvi(8960, OpenGlHelper.GL_OPERAND0_ALPHA, 770);
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            return true;
        }
    }
}