package com.mco.entities.mobs.dark.demon.layers;

import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.ModelDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.RenderDarkOpalDemon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class LayerDemonAura implements LayerRenderer<EntityDarkOpalDemon>
{
	private static final ResourceLocation DarkOpalDemon_ARMOR = new ResourceLocation("tuom:textures/entities/mobs/dark_demon_armor.png");
    private final RenderDarkOpalDemon DarkOpalDemonRenderer;
    private final ModelDarkOpalDemon DarkOpalDemonModel = new ModelDarkOpalDemon();

    public LayerDemonAura(RenderDarkOpalDemon DarkOpalDemonRendererIn)
    {
        this.DarkOpalDemonRenderer = DarkOpalDemonRendererIn;
    }

    public void doRenderLayer(EntityDarkOpalDemon demon, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (demon.isArmored())
        {
            GlStateManager.depthMask(!demon.isInvisible());
            this.DarkOpalDemonRenderer.bindTexture(DarkOpalDemon_ARMOR);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f = (float)demon.ticksExisted + partialTicks;
            float f1 = MathHelper.cos(f * 0.02F) * 3.0F;
            float f2 = f * 0.01F;
            GlStateManager.translate(f1, f2, 0.0F);
            GlStateManager.scale(1.25F, 1.25F, 1.25F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            float f3 = 0.5F;
            GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
            this.DarkOpalDemonModel.setLivingAnimations(demon, limbSwing, limbSwingAmount, partialTicks);
            this.DarkOpalDemonModel.setModelAttributes(this.DarkOpalDemonRenderer.getMainModel());
            Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
            this.DarkOpalDemonModel.render(demon, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
        }        
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}
