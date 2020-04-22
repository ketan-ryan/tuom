package library.entities.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ogre - 
 * Created using Tabula 7.0.0
 */
public class LibModelOgre extends LibModelBase {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer rArm;
    public ModelRenderer lArm;
    public ModelRenderer hips;
    public ModelRenderer rLeg;
    public ModelRenderer lLeg;
    public ModelRenderer rShoulder;
    public ModelRenderer lbHorn;
    public ModelRenderer ltHorn;
    public ModelRenderer rbHorn;
    public ModelRenderer lShoulder;
    public ModelRenderer rtHorn;
    public ModelRenderer mouth;
    public ModelRenderer tooth1;
    public ModelRenderer tooth2;
    public ModelRenderer tooth3;
    public ModelRenderer tooth4;

    public LibModelOgre() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.tooth1 = new ModelRenderer(this, 39, 7);
        this.tooth1.setRotationPoint(-3.0F, 0.0F, -1.0F);
        this.tooth1.addBox(-2.5F, -4.0F, -5.0F, 1, 2, 1, 0.0F);
        this.lLeg = new ModelRenderer(this, 51, 72);
        this.lLeg.setRotationPoint(3.0000000000000053F, 17.0F, 0.5999999999999984F);
        this.lLeg.addBox(-3.0F, 0.0F, -3.0F, 5, 7, 5, 0.0F);
        this.lbHorn = new ModelRenderer(this, 67, 22);
        this.lbHorn.setRotationPoint(-1.5265566588595902E-15F, 0.0F, -1.4F);
        this.lbHorn.addBox(4.0F, -6.0F, -1.0F, 2, 1, 1, 0.0F);
        this.lShoulder = new ModelRenderer(this, 74, 34);
        this.lShoulder.setRotationPoint(8.00000000000001F, 2.0F, -0.40000000000000047F);
        this.lShoulder.addBox(-1.0F, -3.0F, -4.0F, 5, 6, 6, 0.0F);
        this.tooth4 = new ModelRenderer(this, 54, 7);
        this.tooth4.setRotationPoint(-3.2F, 0.0F, -1.0F);
        this.tooth4.addBox(1.5F, -4.0F, -5.0F, 1, 2, 1, 0.0F);
        this.ltHorn = new ModelRenderer(this, 69, 16);
        this.ltHorn.setRotationPoint(-1.5265566588595902E-15F, 0.0F, -1.4F);
        this.ltHorn.addBox(5.0F, -8.0F, -1.0F, 1, 3, 1, 0.0F);
        this.hips = new ModelRenderer(this, 32, 59);
        this.hips.setRotationPoint(-4.000000000000002F, 12.0F, -0.40000000000000047F);
        this.hips.addBox(-2.0F, 0.0F, -2.0F, 12, 5, 5, 0.0F);
        this.tooth2 = new ModelRenderer(this, 44, 8);
        this.tooth2.setRotationPoint(-2.0F, 0.0F, -1.0F);
        this.tooth2.addBox(-1.0F, -3.0F, -5.0F, 1, 1, 1, 0.0F);
        this.rLeg = new ModelRenderer(this, 29, 72);
        this.rLeg.setRotationPoint(-3.0000000000000013F, 17.0F, -0.40000000000000047F);
        this.rLeg.addBox(-2.0F, 0.0F, -2.0F, 5, 7, 5, 0.0F);
        this.tooth3 = new ModelRenderer(this, 49, 7);
        this.tooth3.setRotationPoint(-4.3F, 0.3F, -1.4F);
        this.tooth3.addBox(0.0F, -3.5F, -4.5F, 1, 2, 1, 0.0F);
        this.rbHorn = new ModelRenderer(this, 25, 22);
        this.rbHorn.setRotationPoint(-1.5265566588595902E-15F, 0.0F, -1.4F);
        this.rbHorn.addBox(-6.0F, -6.0F, -1.0F, 2, 1, 1, 0.0F);
        this.body = new ModelRenderer(this, 27, 36);
        this.body.setRotationPoint(-3.0000000000000013F, 0.0F, -2.400000000000001F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 14, 12, 8, 0.0F);
        this.head = new ModelRenderer(this, 33, 17);
        this.head.setRotationPoint(-1.5265566588595902E-15F, 0.0F, -1.4F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.rtHorn = new ModelRenderer(this, 25, 16);
        this.rtHorn.setRotationPoint(-1.5265566588595902E-15F, 0.0F, -1.4F);
        this.rtHorn.addBox(-6.0F, -8.0F, -1.0F, 1, 3, 1, 0.0F);
        this.rShoulder = new ModelRenderer(this, 2, 34);
        this.rShoulder.setRotationPoint(-7.99999999999999F, 2.0F, -1.4F);
        this.rShoulder.addBox(-4.0F, -3.0F, -3.0F, 5, 6, 6, 0.0F);
        this.lArm = new ModelRenderer(this, 75, 49);
        this.lArm.setRotationPoint(8.00000000000001F, 2.0F, -1.4F);
        this.lArm.addBox(-1.0F, -2.0F, -2.0F, 4, 11, 4, 0.0F);
        this.rArm = new ModelRenderer(this, 8, 49);
        this.rArm.setRotationPoint(-7.99999999999999F, 2.0F, -1.4F);
        this.rArm.addBox(-3.0F, -2.0F, -2.0F, 4, 11, 4, 0.0F);
        this.mouth = new ModelRenderer(this, 42, 12);
        this.mouth.setRotationPoint(3.0F, 0.0F, 1.0F);
        this.mouth.addBox(-6.0F, -2.0F, -6.0F, 6, 1, 1, 0.0F);
        this.mouth.addChild(this.tooth1);
        this.mouth.addChild(this.tooth4);
        this.mouth.addChild(this.tooth2);
        this.mouth.addChild(this.tooth3);
        this.head.addChild(this.mouth);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.lLeg.render(f5);
        this.lbHorn.render(f5);
        this.lShoulder.render(f5);
        this.ltHorn.render(f5);
        this.hips.render(f5);
        this.rLeg.render(f5);
        this.rbHorn.render(f5);
        this.body.render(f5);
        this.head.render(f5);
        this.rtHorn.render(f5);
        this.rShoulder.render(f5);
        this.lArm.render(f5);
        this.rArm.render(f5);
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
        this.rbHorn.rotateAngleY = this.head.rotateAngleY;
        this.rbHorn.rotateAngleX = this.head.rotateAngleX;
        this.lbHorn.rotateAngleY = this.head.rotateAngleY;
        this.lbHorn.rotateAngleX = this.head.rotateAngleX;
        this.rtHorn.rotateAngleY = this.head.rotateAngleY;
        this.rtHorn.rotateAngleX = this.head.rotateAngleX;
        this.ltHorn.rotateAngleY = this.head.rotateAngleY;
        this.ltHorn.rotateAngleX = this.head.rotateAngleX;
        this.rArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.lArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.rArm.rotateAngleZ = 0.0F;
        this.lArm.rotateAngleZ = 0.0F;
        this.rShoulder.rotateAngleX = this.rArm.rotateAngleX;
        this.rShoulder.rotateAngleZ = this.rArm.rotateAngleZ;
        this.lShoulder.rotateAngleX = this.lArm.rotateAngleX;
        this.lShoulder.rotateAngleZ = this.lArm.rotateAngleZ;
        this.rLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.lLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.rLeg.rotateAngleY = 0.0F;
        this.lLeg.rotateAngleY = 0.0F;
    }

    @Override
    public float getShadowScale() {
        return 0.6F;
    }
}
