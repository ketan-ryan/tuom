package library.entities.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * wraith - 
 * Created using Tabula 7.0.0
 */
public class LibModelWraith extends LibModelBase {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer rightarm;
    public ModelRenderer leftarm;

    public LibModelWraith() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.rightarm = new ModelRenderer(this, 2, 18);
        this.rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(rightarm, -1.408740667551876F, 0.14500328543619004F, -0.2254782333332755F);
        this.head = new ModelRenderer(this, 16, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.body = new ModelRenderer(this, 21, 18);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 19, 4, 0.0F);
        this.setRotateAngle(body, 0.37178611755371094F, 0.0F, 0.0F);
        this.leftarm = new ModelRenderer(this, 49, 18);
        this.leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(leftarm, -1.408740667551876F, -0.14500328543619004F, 0.2254782333332755F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.rightarm.render(f5);
        this.head.render(f5);
        this.body.render(f5);
        this.leftarm.render(f5);
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
        this.rightarm.rotateAngleX = -((float)Math.PI / 2F);
        this.leftarm.rotateAngleX = -((float)Math.PI / 2F);
        this.rightarm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        this.leftarm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        this.rightarm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.leftarm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.rightarm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.leftarm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;

        /**
         * FullBody Floating
         */
        float oscillate = MathHelper.sin(par3 * 0.09F) * 0.5F;

        this.head.rotationPointY = 0.0F + oscillate;
        this.body.rotationPointY = 0.0F + oscillate;
        this.rightarm.rotationPointY = 2.0F + oscillate;
        this.leftarm.rotationPointY = 2.0F + oscillate;

    }

    @Override
    public float getShadowScale() {
        return 0.5F;
    }
}
