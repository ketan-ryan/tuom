package library.entities.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * tobo - 
 * Created using Tabula 7.0.0
 */
public class LibModelTobo extends LibModelBase {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer rightarm;
    public ModelRenderer leftarm;
    public ModelRenderer body2;
    public ModelRenderer head_1;

    public LibModelTobo() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.rightarm = new ModelRenderer(this, 31, 22);
        this.rightarm.setRotationPoint(-5.0F, 1.0F, 0.0F);
        this.rightarm.addBox(-5.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.leftarm = new ModelRenderer(this, 48, 22);
        this.leftarm.setRotationPoint(5.0F, 1.0F, 0.0F);
        this.leftarm.addBox(1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.body2 = new ModelRenderer(this, 42, 7);
        this.body2.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.body2.addBox(-4.0F, 4.0F, -2.0F, 8, 11, 3, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.head.addBox(-5.0F, -10.0F, -5.0F, 10, 10, 10, 0.0F);
        this.head_1 = new ModelRenderer(this, 41, 0);
        this.head_1.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.head_1.addBox(-5.0F, -8.0F, -6.0F, 10, 3, 1, 0.0F);
        this.body = new ModelRenderer(this, 0, 19);
        this.body.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.body.addBox(-5.0F, 0.0F, -3.0F, 10, 8, 5, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.body2.render(f5);
        this.head.render(f5);
        this.head_1.render(f5);
        this.body.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        /**
         * Arm Oscillations
         */
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
        float f6 = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float f7 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
        this.rightarm.rotateAngleZ = 0.0F;
        this.leftarm.rotateAngleZ = 0.0F;
        this.rightarm.rotateAngleY = -(0.1F - f6 * 0.6F);
        this.leftarm.rotateAngleY = 0.1F - f6 * 0.6F;
        this.rightarm.rotateAngleX = 0.0F;
        this.leftarm.rotateAngleX = 0.0F;
        this.rightarm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        this.leftarm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        this.rightarm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.leftarm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.rightarm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.leftarm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.rightarm.rotateAngleX += MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.leftarm.rotateAngleX += MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;

        /**
         * FullBody Floating
         */
        float oscillate = MathHelper.sin(par3 * 0.09F) * 0.5F;

        this.head.rotationPointY = -2.0F + oscillate;
        this.body.rotationPointY = -1.0F + oscillate;
        this.body2.rotationPointY = -1.0F + oscillate;
        this.rightarm.rotationPointY = 1.0F + oscillate;
        this.leftarm.rotationPointY = 1.0F + oscillate;
        this.head_1.rotationPointY = -2.0F + oscillate;
    }

    @Override
    public float getShadowScale() {
        return 0.5F;
    }
}
