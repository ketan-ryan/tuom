package library.entities.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * fourm - 
 * Created using Tabula 7.0.0
 */
public class LibModelFourm extends LibModelBase {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer rightarm;
    public ModelRenderer leftarm;
    public ModelRenderer rightleg;
    public ModelRenderer leftleg;
    public ModelRenderer leftarm2;
    public ModelRenderer rightarm2;

    public LibModelFourm() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.leftarm2 = new ModelRenderer(this, 47, 36);
        this.leftarm2.setRotationPoint(3.0F, 8.0F, -1.0F);
        this.leftarm2.addBox(0.0F, 0.0F, 0.0F, 4, 10, 4, 0.0F);
        this.setRotateAngle(leftarm2, -0.4833219647407532F, -0.0F, -1.3012514114379883F);
        this.body = new ModelRenderer(this, 21, 18);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.rightarm = new ModelRenderer(this, 3, 18);
        this.rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(rightarm, -0.4833219647407532F, -0.0F, 1.3012514114379883F);
        this.leftarm = new ModelRenderer(this, 47, 18);
        this.leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(leftarm, -0.4833219647407532F, -0.0F, -1.3012514114379883F);
        this.leftleg = new ModelRenderer(this, 36, 54);
        this.leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.rightarm2 = new ModelRenderer(this, 3, 36);
        this.rightarm2.setRotationPoint(-4.0F, 4.0F, -1.0F);
        this.rightarm2.addBox(0.0F, 0.0F, 0.0F, 4, 10, 4, 0.0F);
        this.setRotateAngle(rightarm2, -0.4833219647407532F, -0.0F, 1.3012514114379883F);
        this.rightleg = new ModelRenderer(this, 17, 54);
        this.rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.head = new ModelRenderer(this, 17, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.leftarm2.render(f5);
        this.body.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.leftleg.render(f5);
        this.rightarm2.render(f5);
        this.rightleg.render(f5);
        this.head.render(f5);
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
        this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.head.rotateAngleX = par5 / (180F / (float)Math.PI);

        this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;

        this.leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;

        this.rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.rightleg.rotateAngleY = 0.0F;
        this.leftleg.rotateAngleY = 0.0F;
    }

    @Override
    public float getShadowScale() {
        return 0.5F;
    }
}
