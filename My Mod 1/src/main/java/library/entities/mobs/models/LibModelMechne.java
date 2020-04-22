package library.entities.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * mechne - 
 * Created using Tabula 7.0.0
 */
public class LibModelMechne extends LibModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer rightarmdetail;
    public ModelRenderer leftarm;
    public ModelRenderer rightlegtop;
    public ModelRenderer waist;
    public ModelRenderer leftlegup;
    public ModelRenderer leftleglower;
    public ModelRenderer rightarm;
    public ModelRenderer rightleglower;
    public ModelRenderer righttoe1;
    public ModelRenderer lefttoe1;
    public ModelRenderer lefttoe2;
    public ModelRenderer righttoe1_1;
    public ModelRenderer rightforearm;
    public ModelRenderer bodylower;
    public ModelRenderer leftarmdetail;
    public ModelRenderer headdetail;

    public LibModelMechne() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.rightarm = new ModelRenderer(this, 20, 31);
        this.rightarm.setRotationPoint(-10.0F, 2.0F, 0.0F);
        this.rightarm.addBox(-1.0F, 0.0F, -4.0F, 2, 8, 8, 0.0F);
        this.bodylower = new ModelRenderer(this, 41, 50);
        this.bodylower.setRotationPoint(0.0F, 13.0F, 1.0F);
        this.bodylower.addBox(-7.0F, -9.0F, -6.0F, 13, 6, 9, 0.0F);
        this.lefttoe1 = new ModelRenderer(this, 56, 118);
        this.lefttoe1.setRotationPoint(4.0F, 10.0F, -1.0F);
        this.lefttoe1.addBox(1.0F, 13.0F, -2.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(lefttoe1, 0.05166174585903216F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 34, 18);
        this.body.setRotationPoint(0.0F, 13.0F, 1.0F);
        this.body.addBox(-9.0F, -12.0F, -7.0F, 17, 6, 12, 0.0F);
        this.rightlegtop = new ModelRenderer(this, 53, 85);
        this.rightlegtop.setRotationPoint(-5.0F, 10.0F, -1.0F);
        this.rightlegtop.addBox(-2.0F, -1.0F, -1.0F, 4, 6, 5, 0.0F);
        this.setRotateAngle(rightlegtop, -0.11152653920243764F, 0.0F, 0.0F);
        this.leftarmdetail = new ModelRenderer(this, 80, 52);
        this.leftarmdetail.setRotationPoint(8.0F, 3.0F, 0.0F);
        this.leftarmdetail.addBox(1.5F, 0.5F, -11.0F, 2, 4, 20, 0.0F);
        this.rightarmdetail = new ModelRenderer(this, 4, 58);
        this.rightarmdetail.setRotationPoint(-10.0F, 3.0F, 0.0F);
        this.rightarmdetail.addBox(-3.0F, 6.0F, -9.0F, 2, 2, 10, 0.0F);
        this.righttoe1 = new ModelRenderer(this, 56, 118);
        this.righttoe1.setRotationPoint(-4.0F, 10.0F, -1.0F);
        this.righttoe1.addBox(0.0F, 13.0F, -2.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(righttoe1, 0.05166174585903216F, 0.0F, 0.0F);
        this.leftleglower = new ModelRenderer(this, 50, 102);
        this.leftleglower.setRotationPoint(3.0F, 10.0F, -1.0F);
        this.leftleglower.addBox(0.0F, 5.0F, -2.0F, 2, 9, 4, 0.0F);
        this.setRotateAngle(leftleglower, 0.3490658503988659F, 0.0F, 0.0F);
        this.headdetail = new ModelRenderer(this, 81, 1);
        this.headdetail.setRotationPoint(0.0F, -13.5F, -5.5F);
        this.headdetail.addBox(-2.5F, -1.0F, -4.0F, 5, 2, 4, 0.0F);
        this.head = new ModelRenderer(this, 50, 2);
        this.head.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.head.addBox(-5.0F, -14.0F, -8.0F, 8, 4, 6, 0.0F);
        this.lefttoe2 = new ModelRenderer(this, 56, 118);
        this.lefttoe2.setRotationPoint(4.0F, 10.0F, -1.0F);
        this.lefttoe2.addBox(-3.0F, 13.0F, -2.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(lefttoe2, 0.05166174585903216F, 0.0F, 0.0F);
        this.waist = new ModelRenderer(this, 51, 69);
        this.waist.setRotationPoint(-1.0F, 10.0F, 0.0F);
        this.waist.addBox(-3.0F, -2.0F, -3.0F, 7, 5, 6, 0.0F);
        this.rightleglower = new ModelRenderer(this, 50, 102);
        this.rightleglower.setRotationPoint(-4.0F, 10.0F, -1.0F);
        this.rightleglower.addBox(-2.0F, 5.0F, -2.0F, 2, 9, 4, 0.0F);
        this.setRotateAngle(rightleglower, 0.3490658503988659F, 0.0F, 0.0F);
        this.leftlegup = new ModelRenderer(this, 53, 85);
        this.leftlegup.setRotationPoint(4.0F, 10.0F, -1.0F);
        this.leftlegup.addBox(-2.0F, -1.0F, -1.0F, 4, 6, 5, 0.0F);
        this.setRotateAngle(leftlegup, -0.11152653920243764F, 0.0F, 0.0F);
        this.leftarm = new ModelRenderer(this, 76, 19);
        this.leftarm.setRotationPoint(8.0F, 3.0F, 0.0F);
        this.leftarm.addBox(0.0F, 0.0F, -12.0F, 3, 5, 20, 0.0F);
        this.rightforearm = new ModelRenderer(this, 2, 38);
        this.rightforearm.setRotationPoint(-10.0F, 2.0F, 0.0F);
        this.rightforearm.addBox(-2.0F, 6.0F, -10.0F, 2, 3, 12, 0.0F);
        this.righttoe1_1 = new ModelRenderer(this, 56, 118);
        this.righttoe1_1.setRotationPoint(-4.0F, 10.0F, -1.0F);
        this.righttoe1_1.addBox(-4.0F, 13.0F, -2.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(righttoe1_1, 0.05166174585903216F, 0.0F, 0.0F);
        this.head.addChild(this.headdetail);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.rightarm.render(f5);
        this.bodylower.render(f5);
        this.lefttoe1.render(f5);
        this.body.render(f5);
        this.rightlegtop.render(f5);
        this.leftarmdetail.render(f5);
        this.rightarmdetail.render(f5);
        this.righttoe1.render(f5);
        this.leftleglower.render(f5);
        this.head.render(f5);
        this.lefttoe2.render(f5);
        this.waist.render(f5);
        this.rightleglower.render(f5);
        this.leftlegup.render(f5);
        this.leftarm.render(f5);
        this.rightforearm.render(f5);
        this.righttoe1_1.render(f5);
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
        this.headdetail.rotateAngleY = this.head.rotateAngleY;
        this.headdetail.rotateAngleX = this.head.rotateAngleX;

        this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.rightarm.rotateAngleZ = 0.0F;
        this.leftarm.rotateAngleZ = 0.0F;
        this.rightarmdetail.rotateAngleX = this.rightarm.rotateAngleX;
        this.leftarmdetail.rotateAngleX = this.leftarm.rotateAngleX;
        this.rightarmdetail.rotateAngleZ = 0.0F;
        this.leftarmdetail.rotateAngleZ = 0.0F;
        this.rightforearm.rotateAngleX = this.rightarm.rotateAngleX;
        this.rightforearm.rotateAngleZ = 0.0F;

        this.rightlegtop.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 + -0.1115358F;
        this.leftlegup.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2 + -0.1115358F;
        this.rightlegtop.rotateAngleY = 0.0F;
        this.leftleglower.rotateAngleY = 0.0F;
        this.rightleglower.rotateAngleX = this.rightlegtop.rotateAngleX + 0.3490659F;
        this.leftleglower.rotateAngleX = this.leftlegup.rotateAngleX + 0.3490659F;
        this.rightleglower.rotateAngleY = 0.0F;
        this.leftleglower.rotateAngleY = 0.0F;
        this.righttoe1.rotateAngleX = this.rightlegtop.rotateAngleX + 0.051637F;
        this.lefttoe1.rotateAngleX = this.leftlegup.rotateAngleX + 0.051637F;
        this.righttoe1.rotateAngleY = 0.0F;
        this.lefttoe2.rotateAngleY = 0.0F;
        this.righttoe1_1.rotateAngleX = this.rightlegtop.rotateAngleX + 0.051637F;
        this.lefttoe2.rotateAngleX = this.leftlegup.rotateAngleX + 0.051637F;
        this.righttoe1_1.rotateAngleY = 0.0F;
        this.lefttoe2.rotateAngleY = 0.0F;
    }

    @Override
    public float getShadowScale() {
        return 1F;
    }
}
