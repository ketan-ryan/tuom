package library.entities.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * gorilla.tcn - TechneToTabulaImporter
 * Created using Tabula 7.0.0
 */
public class LibModelAbe extends LibModelBase {
    public ModelRenderer rHand;
    public ModelRenderer lHand;
    public ModelRenderer head;
    public ModelRenderer Body;
    public ModelRenderer rLeg;
    public ModelRenderer lLeg;
    public ModelRenderer rArm;
    public ModelRenderer lArm;
    public ModelRenderer Nose;

    public LibModelAbe() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.rLeg = new ModelRenderer(this, 30, 40);
        this.rLeg.setRotationPoint(-2.5F, 17.0F, 1.0F);
        this.rLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.Nose = new ModelRenderer(this, 36, 1);
        this.Nose.setRotationPoint(-1.0F, 12.5F, -7.0F);
        this.Nose.addBox(-1.5F, 0.0F, -3.0F, 3, 2, 1, 0.0F);
        this.Body = new ModelRenderer(this, 26, 19);
        this.Body.setRotationPoint(-1.0F, 14.0F, -4.0F);
        this.Body.addBox(-4.0F, -2.0F, -3.0F, 8, 9, 6, 0.0F);
        this.setRotateAngle(Body, 0.7900454998016356F, -0.0F, 0.0F);
        this.lHand = new ModelRenderer(this, 52, 35);
        this.lHand.setRotationPoint(3.0F, 13.0F, -4.0F);
        this.lHand.addBox(-0.5F, 8.0F, -1.5F, 3, 3, 3, 0.0F);
        this.head = new ModelRenderer(this, 30, 7);
        this.head.setRotationPoint(-1.0F, 11.5F, -7.0F);
        this.head.addBox(-3.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F);
        this.lArm = new ModelRenderer(this, 55, 19);
        this.lArm.setRotationPoint(3.0F, 13.0F, -4.0F);
        this.lArm.addBox(0.0F, -1.0F, -1.0F, 2, 12, 2, 0.0F);
        this.rArm = new ModelRenderer(this, 16, 19);
        this.rArm.setRotationPoint(-5.0F, 13.0F, -4.0F);
        this.rArm.addBox(-2.0F, -1.0F, -1.0F, 2, 12, 2, 0.0F);
        this.rHand = new ModelRenderer(this, 13, 35);
        this.rHand.setRotationPoint(-5.0F, 13.0F, -4.0F);
        this.rHand.addBox(-2.5F, 8.0F, -1.5F, 3, 3, 3, 0.0F);
        this.lLeg = new ModelRenderer(this, 40, 40);
        this.lLeg.setRotationPoint(0.5F, 17.0F, 1.0F);
        this.lLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.rLeg.render(f5);
        this.Nose.render(f5);
        this.Body.render(f5);
        this.lHand.render(f5);
        this.head.render(f5);
        this.lArm.render(f5);
        this.rArm.render(f5);
        this.rHand.render(f5);
        this.lLeg.render(f5);
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

        this.rArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.lArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.rHand.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.lHand.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;

        this.rLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.lLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.rLeg.rotateAngleY = 0.0F;
        this.lLeg.rotateAngleY = 0.0F;
    }

    @Override
    public float getShadowScale() {
        return 0.5F;
    }
}
