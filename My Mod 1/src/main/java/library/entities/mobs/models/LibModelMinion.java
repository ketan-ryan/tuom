package library.entities.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * minion - 
 * Created using Tabula 7.0.0
 */
public class LibModelMinion extends LibModelBase {
    public ModelRenderer body;
    public ModelRenderer rLeg;
    public ModelRenderer lLeg;
    public ModelRenderer lArm;
    public ModelRenderer rArm;

    public LibModelMinion() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.lArm = new ModelRenderer(this, 52, 0);
        this.lArm.setRotationPoint(4.300000000000002F, 15.0F, 0.30000000000000004F);
        this.lArm.addBox(0.0F, -1.0F, -2.0F, 3, 6, 4, 0.0F);
        this.lLeg = new ModelRenderer(this, 34, 18);
        this.lLeg.setRotationPoint(0.30000000000000004F, 20.0F, -1.7000000000000004F);
        this.lLeg.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.body = new ModelRenderer(this, 18, 0);
        this.body.setRotationPoint(-0.7F, 16.0F, -0.7F);
        this.body.addBox(-3.0F, -4.0F, -3.0F, 8, 8, 8, 0.0F);
        this.rArm = new ModelRenderer(this, 2, 0);
        this.rArm.setRotationPoint(-3.7000000000000006F, 15.0F, 0.30000000000000004F);
        this.rArm.addBox(-3.0F, -1.0F, -2.0F, 3, 6, 4, 0.0F);
        this.rLeg = new ModelRenderer(this, 16, 18);
        this.rLeg.setRotationPoint(-1.7000000000000004F, 20.0F, 0.30000000000000004F);
        this.rLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 4, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.lLeg.render(f5);
        this.body.render(f5);
        this.lArm.render(f5);
        this.rLeg.render(f5);
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
        this.rArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.lArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.rLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.lLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
    }

    @Override
    public float getShadowScale() {
        return 0.5F;
    }
}
