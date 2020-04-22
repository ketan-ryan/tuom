package library.entities;

import library.entities.mobs.models.LibModelBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LibRender extends Render<Entity> {

    private ResourceLocation textures;
    private float scale;
    protected ModelBase mainModel;

    public LibRender(RenderManager manager, Class<? extends Entity> entityClass, ModelBase model) {
        super(manager);
        this.scale = 1.0f;
        this.mainModel = model;
    }

    public LibRender setTexture(ResourceLocation texture) {
        this.textures = texture;
        return this;
    }

    public LibRender setScale(float scale) {
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

        //maybe not needed, potentially remove when given real projectile model to test with
        float yFix = 0.5F;

        GlStateManager.translate((float)x, (float)y, (float)z);

        GlStateManager.translate(0, yFix, 0);

        //rotations here
        float f = entityYaw;//this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
        f *= -1F;
        float f8 = this.handleRotationFloat(entity, partialTicks);
        this.applyRotations(entity, f8, f, partialTicks);



        //scaling here

        /*this.mainModel.setLivingAnimations(entity, f6, f5, partialTicks);
        this.mainModel.setRotationAngles(f6, f5, f8, f2, f7, f4, entity);*/

        renderModel(entity, 0, 0, 0, entityYaw, entity.rotationPitch, 0.0625F);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

    }

    protected void renderModel(Entity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
    {
        boolean flag = true;//this.isVisible(entitylivingbaseIn);
        boolean flag1 = !flag && !entitylivingbaseIn.isInvisibleToPlayer(Minecraft.getMinecraft().player);

        if (flag || flag1)
        {
            if (!this.bindEntityTexture(entitylivingbaseIn))
            {
                return;
            }

            if (flag1)
            {
                GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }

            this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

            if (flag1)
            {
                GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }
        }
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