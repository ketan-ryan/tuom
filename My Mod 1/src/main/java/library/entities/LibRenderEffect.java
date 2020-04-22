package library.entities;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class LibRenderEffect extends Render<Entity> {

    private ResourceLocation textures;
    private float scale;

    public LibRenderEffect(RenderManager manager, Class<? extends Entity> entityClass) {
        super(manager);
        this.scale = 1.0f;
    }

    public LibRenderEffect setTexture(ResourceLocation texture) {
        this.textures = texture;
        return this;
    }

    public LibRenderEffect setScale(float scale) {
        this.scale = scale;
        this.shadowSize = scale;
        return this;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return textures;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();

        GlStateManager.translate((float)x, (float)y, (float)z);

        GlStateManager.translate(0, 0, -1.5F);

        //rotations here
        float ff = entityYaw;//this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
        ff *= -1F;
        float f8 = this.handleRotationFloat(entity, partialTicks);
        this.applyRotations(entity, f8, ff, partialTicks);



        //scaling here

        /*this.mainModel.setLivingAnimations(entity, f6, f5, partialTicks);
        this.mainModel.setRotationAngles(f6, f5, f8, f2, f7, f4, entity);*/


        if (entity instanceof LibEntityEffect) {
            LibEntityEffect effect = (LibEntityEffect)entity;

            if (effect.deathTicks > 0)
            {
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferbuilder = tessellator.getBuffer();
                RenderHelper.disableStandardItemLighting();
                float f = ((float)effect.deathTicks + partialTicks) / (float)effect.deathTicksMax;
                float f1 = 0.0F;

                if (f > 0.8F)
                {
                    f1 = (f - 0.8F) / 0.2F;
                }

                Random random = new Random(432L);
                GlStateManager.disableTexture2D();
                GlStateManager.shadeModel(7425);
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
                GlStateManager.disableAlpha();
                GlStateManager.enableCull();
                GlStateManager.depthMask(false);
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, -1.0F, -2.0F);

                int hex = effect.color;
                int r = (hex & 0xFF0000) >> 16;
                int g = (hex & 0xFF00) >> 8;
                int b = (hex & 0xFF);

                for (int i = 0; (float)i < (f + f * f) / 2.0F * 60.0F; ++i)
                {
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(random.nextFloat() * 360.0F + f * 90.0F, 0.0F, 0.0F, 1.0F);
                    float f2 = random.nextFloat() * 20.0F + 5.0F + f1 * 10.0F;
                    float f3 = random.nextFloat() * 2.0F + 1.0F + f1 * 2.0F;
                    bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
                    bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(255, 255, 255, (int)(255.0F * (1.0F - f1))).endVertex();
                    bufferbuilder.pos(-0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(r, g, b, 0).endVertex();
                    bufferbuilder.pos(0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(r, g, b, 0).endVertex();
                    bufferbuilder.pos(0.0D, (double)f2, (double)(1.0F * f3)).color(r, g, b, 0).endVertex();
                    bufferbuilder.pos(-0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(r, g, b, 0).endVertex();
                    tessellator.draw();
                }

                GlStateManager.popMatrix();
                GlStateManager.depthMask(true);
                GlStateManager.disableCull();
                GlStateManager.disableBlend();
                GlStateManager.shadeModel(7424);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.enableTexture2D();
                GlStateManager.enableAlpha();
                RenderHelper.enableStandardItemLighting();
            }
        }


        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

    }

    protected void applyRotations(Entity entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);

        GlStateManager.rotate(entityLiving.rotationPitch, 1F, 0F, 0F);

        /*if (entityLiving.deathTime > 0)
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

            if (s != null && ("Dinnerbone".equals(s) || "Grumm".equals(s)) && (!(entityLiving instanceof EntityPlayer) || ((EntityPlayer)entityLiving).isWearing(EnumPlayerModelParts.CAPE)))
            {
                GlStateManager.translate(0.0F, entityLiving.height + 0.1F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            }
        }*/
        GlStateManager.translate(0.0F, entityLiving.height + 0.1F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
    }

    protected float handleRotationFloat(Entity livingBase, float partialTicks)
    {
        return (float)livingBase.ticksExisted + partialTicks;
    }
}