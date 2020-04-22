package com.mco.entities.mobs.dark.demon.skull;

import library.entities.mobs.models.LibModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Darkhead - Me
 * Created using Tabula 5.1.0
 */
public class ModelDarkSkull extends LibModelBase 
{
    public ModelRenderer HeadCubeA;
    public ModelRenderer LHornA;
    public ModelRenderer RHornA;
    public ModelRenderer LHornC;
    public ModelRenderer LHornD;
    public ModelRenderer LHornE;
    public ModelRenderer LHornB;
    public ModelRenderer RHornB;
    public ModelRenderer RHornC;
    public ModelRenderer RHornD;
    public ModelRenderer RHornE;

    public ModelDarkSkull() {
        
    	this.textureWidth = 64;
        this.textureHeight = 64;
        
        this.LHornC = new ModelRenderer(this, 0, 8);
        this.LHornC.setRotationPoint(-1.0F, -8.7F, 1.1F);
        this.LHornC.addBox(8.0F, -14.0F, 14.0F, 3, 4, 3, 0.0F);
        this.setRotateAngle(LHornC, 0.0F, 0.0F, 0.2673844414055313F);
        this.RHornB = new ModelRenderer(this, 0, 50);
        this.RHornB.setRotationPoint(-1.5F, -5.3F, 0.5F);
        this.RHornB.addBox(8.0F, -14.0F, 14.0F, 4, 4, 4, 0.0F);
        this.setRotateAngle(RHornB, 0.0F, 0.0F, 0.20001473227855018F);
        this.LHornA = new ModelRenderer(this, 0, 30);
        this.LHornA.setRotationPoint(-15.0F, -5.0F, -13.0F);
        this.LHornA.addBox(8.0F, -14.0F, 14.0F, 5, 5, 5, 0.0F);
        this.setRotateAngle(LHornA, 0.0F, 0.0F, 0.10646508437165408F);
        this.RHornE = new ModelRenderer(this, 0, 0);
        this.RHornE.setRotationPoint(2.2F, -18.8F, 2.2F);
        this.RHornE.addBox(8.0F, -9.0F, 14.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(RHornE, 0.0F, 0.0F, 0.41050144006906625F);
        this.LHornB = new ModelRenderer(this, 0, 40);
        this.LHornB.setRotationPoint(-1.5F, -5.3F, 0.5F);
        this.LHornB.addBox(8.0F, -14.0F, 14.0F, 4, 4, 4, 0.0F);
        this.setRotateAngle(LHornB, 0.0F, 0.0F, 0.20001473227855018F);
        this.RHornA = new ModelRenderer(this, 0, 30);
        this.RHornA.setRotationPoint(-15.0F, -5.0F, -20.0F);
        this.RHornA.addBox(8.0F, -14.0F, 14.0F, 5, 5, 5, 0.0F);
        this.setRotateAngle(RHornA, 0.0F, 0.0F, 0.10646508437165408F);
        this.RHornC = new ModelRenderer(this, 0, 8);
        this.RHornC.setRotationPoint(-1.0F, -8.7F, 1.1F);
        this.RHornC.addBox(8.0F, -14.0F, 14.0F, 3, 4, 3, 0.0F);
        this.setRotateAngle(RHornC, 0.0F, 0.0F, 0.2673844414055313F);
        this.LHornE = new ModelRenderer(this, 0, 0);
        this.LHornE.setRotationPoint(2.2F, -18.8F, 2.2F);
        this.LHornE.addBox(8.0F, -9.0F, 14.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(LHornE, 0.0F, 0.0F, 0.41050144006906625F);
        this.HeadCubeA = new ModelRenderer(this, 0, 0);
        this.HeadCubeA.setRotationPoint(0.0F, 24.0F, 0.1F);
        this.HeadCubeA.addBox(-7.5F, -15.0F, -7.5F, 15, 15, 15, 0.0F);
        this.setRotateAngle(HeadCubeA, 0.0F, -1.5707963267948966F, 0.0F);
        this.LHornD = new ModelRenderer(this, 0, 0);
        this.LHornD.setRotationPoint(0.2F, -14.1F, 1.7F);
        this.LHornD.addBox(8.0F, -12.0F, 14.0F, 2, 3, 2, 0.0F);
        this.setRotateAngle(LHornD, 0.0F, 0.0F, 0.35814156250923646F);
        this.RHornD = new ModelRenderer(this, 0, 0);
        this.RHornD.setRotationPoint(0.2F, -14.1F, 1.7F);
        this.RHornD.addBox(8.0F, -12.0F, 14.0F, 2, 3, 2, 0.0F);
        this.setRotateAngle(RHornD, 0.0F, 0.0F, 0.35814156250923646F);
        this.LHornA.addChild(this.LHornC);
        this.RHornA.addChild(this.RHornB);
        this.HeadCubeA.addChild(this.LHornA);
        this.RHornA.addChild(this.RHornE);
        this.LHornA.addChild(this.LHornB);
        this.HeadCubeA.addChild(this.RHornA);
        this.RHornA.addChild(this.RHornC);
        this.LHornA.addChild(this.LHornE);
        this.LHornA.addChild(this.LHornD);
        this.RHornA.addChild(this.RHornD);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.HeadCubeA.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
