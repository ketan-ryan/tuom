package library.entities.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * threed - 
 * Created using Tabula 7.0.0
 */
public class LibModelThreed extends LibModelBase {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer rArm;
    public ModelRenderer lArm;
    public ModelRenderer rLeg;
    public ModelRenderer lLeg;
    public ModelRenderer lHead;
    public ModelRenderer rHead;

    public LibModelThreed() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.rArm = new ModelRenderer(this, 17, 18);
        this.rArm.setRotationPoint(-5.0F, 4.0F, 0.0F);
        this.rArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(rArm, -0.9666439294815066F, 0.2602502703666687F, 0.0F);
        this.head = new ModelRenderer(this, 34, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.lArm = new ModelRenderer(this, 67, 18);
        this.lArm.setRotationPoint(5.0F, 4.0F, 0.0F);
        this.lArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(lArm, -0.9666439294815066F, -0.2602502703666687F, 0.0F);
        this.rHead = new ModelRenderer(this, 0, 0);
        this.rHead.setRotationPoint(-3.0F, 0.0F, 0.0F);
        this.rHead.addBox(-8.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(rHead, 0.0F, 0.0F, -0.3346075117588043F);
        this.rLeg = new ModelRenderer(this, 33, 36);
        this.rLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.rLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.lLeg = new ModelRenderer(this, 51, 36);
        this.lLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.lLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.lHead = new ModelRenderer(this, 68, 0);
        this.lHead.setRotationPoint(3.0F, 0.0F, 1.0F);
        this.lHead.addBox(0.0F, -8.0F, -5.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(lHead, 0.0F, 0.0F, 0.3346075117588043F);
        this.body = new ModelRenderer(this, 36, 18);
        this.body.setRotationPoint(-1.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 10, 12, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.rArm.render(f5);
        this.head.render(f5);
        this.lArm.render(f5);
        this.rHead.render(f5);
        this.rLeg.render(f5);
        this.lLeg.render(f5);
        this.lHead.render(f5);
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
        this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
        this.rHead.rotateAngleY = this.head.rotateAngleY;
        this.rHead.rotateAngleX = this.head.rotateAngleX;
        this.lHead.rotateAngleY = this.head.rotateAngleY;
        this.lHead.rotateAngleX = this.head.rotateAngleX;
        this.rArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.lArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.rArm.rotateAngleZ = 0.0F;
        this.lArm.rotateAngleZ = 0.0F;
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
