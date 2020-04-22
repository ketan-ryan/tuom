package library.entities.vehicles;

import library.entities.mobs.models.LibModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/**
 * vehicle_helicopter - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelHelicopter extends LibModelBase {
    public ModelRenderer root;
    public ModelRenderer bottom;
    public ModelRenderer frame2_right;
    public ModelRenderer frame2_left;
    public ModelRenderer tail1;
    public ModelRenderer nose;
    public ModelRenderer engine_right;
    public ModelRenderer engine_left;
    public ModelRenderer engine_blade_spin;
    public ModelRenderer back_slope;
    public ModelRenderer wing_left;
    public ModelRenderer wing_right;
    public ModelRenderer rocket_left;
    public ModelRenderer rocket_right;
    public ModelRenderer frame3_right;
    public ModelRenderer frame_middle;
    public ModelRenderer frame_front;
    public ModelRenderer frame_right;
    public ModelRenderer frame_left;
    public ModelRenderer frame3_left;
    public ModelRenderer tail2;
    public ModelRenderer tail_fin;
    public ModelRenderer tail_fin_engine_blade_spin;
    public ModelRenderer tail_fin_blade1;
    public ModelRenderer tail_fin_blade4;
    public ModelRenderer tail_fin_blade3;
    public ModelRenderer tail_fin_blade2;
    public ModelRenderer engine_right_nose;
    public ModelRenderer engine_left_nose;
    public ModelRenderer blade1;
    public ModelRenderer blade2;
    public ModelRenderer blade3;
    public ModelRenderer blade4;

    public LibModelHelicopter() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.frame_left = new ModelRenderer(this, 0, 80);
        this.frame_left.mirror = true;
        this.frame_left.setRotationPoint(20.0F, 2.0F, 0.0F);
        this.frame_left.addBox(0.0F, 0.0F, 0.0F, 20, 3, 4, 0.0F);
        this.setRotateAngle(frame_left, 0.0F, 0.0F, 1.5707963267948966F);
        this.back_slope = new ModelRenderer(this, 82, 199);
        this.back_slope.setRotationPoint(0.0F, 6.1F, 23.5F);
        this.back_slope.addBox(-5.0F, -11.5F, -10.0F, 10, 15, 20, 0.0F);
        this.setRotateAngle(back_slope, -0.4553564018453205F, 0.0F, 0.0F);
        this.tail_fin_blade2 = new ModelRenderer(this, 175, 211);
        this.tail_fin_blade2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail_fin_blade2.addBox(-0.5F, -23.0F, -1.5F, 1, 23, 3, 0.0F);
        this.setRotateAngle(tail_fin_blade2, -1.5707963267948966F, 0.0F, 0.0F);
        this.frame2_right = new ModelRenderer(this, 0, 37);
        this.frame2_right.setRotationPoint(-7.0F, -7.0F, -19.0F);
        this.frame2_right.addBox(-2.0F, -1.5F, -20.0F, 4, 2, 20, 0.0F);
        this.setRotateAngle(frame2_right, 0.36425021489121656F, 0.0F, 0.0F);
        this.tail1 = new ModelRenderer(this, 0, 186);
        this.tail1.setRotationPoint(0.0F, 10.0F, 45.0F);
        this.tail1.addBox(-6.5F, -10.0F, -25.0F, 13, 20, 50, 0.0F);
        this.root = new ModelRenderer(this, 51, 122);
        this.root.setRotationPoint(0.0F, -9.0F, 27.79999999999995F);
        this.root.addBox(-10.5F, -10.0F, -20.0F, 21, 23, 40, 0.0F);
        this.wing_left = new ModelRenderer(this, 152, 130);
        this.wing_left.mirror = true;
        this.wing_left.setRotationPoint(10.0F, 0.0F, 8.0F);
        this.wing_left.addBox(0.0F, -0.5F, -10.0F, 27, 1, 20, 0.0F);
        this.engine_left = new ModelRenderer(this, 0, 124);
        this.engine_left.mirror = true;
        this.engine_left.setRotationPoint(15.0F, -1.0F, 14.0F);
        this.engine_left.addBox(-4.5F, -6.0F, -10.0F, 9, 12, 20, 0.0F);
        this.wing_right = new ModelRenderer(this, 152, 130);
        this.wing_right.setRotationPoint(-10.5F, 0.0F, 8.0F);
        this.wing_right.addBox(-27.0F, -0.5F, -10.0F, 27, 1, 20, 0.0F);
        this.rocket_left = new ModelRenderer(this, 179, 161);
        this.rocket_left.mirror = true;
        this.rocket_left.setRotationPoint(10.0F, 6.5F, 0.0F);
        this.rocket_left.addBox(-3.0F, -6.0F, -10.0F, 6, 6, 20, 0.0F);
        this.blade4 = new ModelRenderer(this, 0, 0);
        this.blade4.setRotationPoint(0.0F, 1.5F, 0.0F);
        this.blade4.addBox(0.0F, -3.0F, -5.0F, 100, 1, 10, 0.0F);
        this.setRotateAngle(blade4, 0.0F, -1.5707963267948966F, 0.0F);
        this.engine_right_nose = new ModelRenderer(this, 43, 128);
        this.engine_right_nose.setRotationPoint(0.0F, 0.0F, -11.0F);
        this.engine_right_nose.addBox(-2.5F, -2.5F, -1.0F, 5, 5, 2, 0.0F);
        this.blade3 = new ModelRenderer(this, 0, 0);
        this.blade3.setRotationPoint(0.0F, 1.5F, 0.0F);
        this.blade3.addBox(0.0F, -3.0F, -5.0F, 100, 1, 10, 0.0F);
        this.setRotateAngle(blade3, 0.0F, 3.141592653589793F, 0.0F);
        this.frame2_left = new ModelRenderer(this, 0, 37);
        this.frame2_left.setRotationPoint(7.0F, -7.0F, -19.0F);
        this.frame2_left.addBox(-2.0F, -1.5F, -20.0F, 4, 2, 20, 0.0F);
        this.setRotateAngle(frame2_left, 0.36425021489121656F, 0.0F, 0.0F);
        this.engine_right = new ModelRenderer(this, 0, 124);
        this.engine_right.setRotationPoint(-15.0F, -1.0F, 14.0F);
        this.engine_right.addBox(-4.5F, -6.0F, -10.0F, 9, 12, 20, 0.0F);
        this.blade2 = new ModelRenderer(this, 0, 0);
        this.blade2.setRotationPoint(0.0F, 1.5F, 0.0F);
        this.blade2.addBox(0.0F, -3.0F, -5.0F, 100, 1, 10, 0.0F);
        this.setRotateAngle(blade2, 0.0F, 1.5707963267948966F, 0.0F);
        this.frame_right = new ModelRenderer(this, 0, 80);
        this.frame_right.setRotationPoint(3.0F, 2.0F, 0.0F);
        this.frame_right.addBox(0.0F, 0.0F, 0.0F, 20, 3, 4, 0.0F);
        this.setRotateAngle(frame_right, 0.0F, 0.0F, 1.5707963267948966F);
        this.engine_left_nose = new ModelRenderer(this, 43, 128);
        this.engine_left_nose.mirror = true;
        this.engine_left_nose.setRotationPoint(0.0F, 0.0F, -11.0F);
        this.engine_left_nose.addBox(-2.5F, -2.5F, -1.0F, 5, 5, 2, 0.0F);
        this.tail2 = new ModelRenderer(this, 140, 192);
        this.tail2.setRotationPoint(0.0F, 0.0F, 45.0F);
        this.tail2.addBox(-3.5F, -7.0F, -25.0F, 7, 14, 50, 0.0F);
        this.frame_front = new ModelRenderer(this, 0, 64);
        this.frame_front.setRotationPoint(-3.0F, -1.6F, -20.0F);
        this.frame_front.addBox(0.0F, 0.0F, 0.0F, 20, 2, 4, 0.0F);
        this.frame3_right = new ModelRenderer(this, 0, 37);
        this.frame3_right.setRotationPoint(0.0F, 0.0F, -20.0F);
        this.frame3_right.addBox(-2.0F, -1.5F, -20.0F, 4, 2, 20, 0.0F);
        this.setRotateAngle(frame3_right, 0.36425021489121656F, 0.0F, 0.0F);
        this.tail_fin_engine_blade_spin = new ModelRenderer(this, 150, 220);
        this.tail_fin_engine_blade_spin.setRotationPoint(4.0F, -5.0F, -7.0F);
        this.tail_fin_engine_blade_spin.addBox(-2.0F, -3.0F, -3.0F, 4, 6, 6, 0.0F);
        this.engine_blade_spin = new ModelRenderer(this, 34, 15);
        this.engine_blade_spin.setRotationPoint(0.0F, -13.0F, 0.0F);
        this.engine_blade_spin.addBox(-6.0F, -3.0F, -6.0F, 12, 6, 12, 0.0F);
        this.nose = new ModelRenderer(this, 143, 26);
        this.nose.setRotationPoint(0.0F, 25.0F, -55.0F);
        this.nose.addBox(-7.5F, -10.0F, -10.0F, 15, 20, 30, 0.0F);
        this.setRotateAngle(nose, 0.5462880558742251F, 0.0F, 0.0F);
        this.tail_fin_blade3 = new ModelRenderer(this, 175, 211);
        this.tail_fin_blade3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail_fin_blade3.addBox(-0.5F, -23.0F, -1.5F, 1, 23, 3, 0.0F);
        this.setRotateAngle(tail_fin_blade3, 3.141592653589793F, 0.0F, 0.0F);
        this.tail_fin_blade1 = new ModelRenderer(this, 175, 211);
        this.tail_fin_blade1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail_fin_blade1.addBox(-0.5F, -23.0F, -1.5F, 1, 23, 3, 0.0F);
        this.frame_middle = new ModelRenderer(this, 0, 64);
        this.frame_middle.setRotationPoint(-3.0F, -1.6F, -1.5F);
        this.frame_middle.addBox(0.0F, 0.0F, 0.0F, 20, 2, 4, 0.0F);
        this.rocket_right = new ModelRenderer(this, 179, 161);
        this.rocket_right.setRotationPoint(-10.0F, 6.5F, 0.0F);
        this.rocket_right.addBox(-3.0F, -6.0F, -10.0F, 6, 6, 20, 0.0F);
        this.tail_fin_blade4 = new ModelRenderer(this, 175, 211);
        this.tail_fin_blade4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail_fin_blade4.addBox(-0.5F, -23.0F, -1.5F, 1, 23, 3, 0.0F);
        this.setRotateAngle(tail_fin_blade4, 1.5707963267948966F, 0.0F, 0.0F);
        this.tail_fin = new ModelRenderer(this, 219, 189);
        this.tail_fin.setRotationPoint(0.0F, 4.2F, 27.6F);
        this.tail_fin.addBox(-1.0F, -25.0F, -11.5F, 2, 25, 16, 0.0F);
        this.bottom = new ModelRenderer(this, 0, 11);
        this.bottom.setRotationPoint(0.0F, 23.0F, -12.0F);
        this.bottom.addBox(-10.5F, -10.0F, -40.0F, 21, 20, 90, 0.0F);
        this.blade1 = new ModelRenderer(this, 0, 0);
        this.blade1.setRotationPoint(0.0F, 1.5F, 0.0F);
        this.blade1.addBox(0.0F, -3.0F, -5.0F, 100, 1, 10, 0.0F);
        this.frame3_left = new ModelRenderer(this, 0, 37);
        this.frame3_left.setRotationPoint(0.0F, 0.0F, -20.0F);
        this.frame3_left.addBox(-2.0F, -1.5F, -20.0F, 4, 2, 20, 0.0F);
        this.setRotateAngle(frame3_left, 0.36425021489121656F, 0.0F, 0.0F);
        this.frame_middle.addChild(this.frame_left);
        this.root.addChild(this.back_slope);
        this.tail_fin_engine_blade_spin.addChild(this.tail_fin_blade2);
        this.root.addChild(this.frame2_right);
        this.root.addChild(this.tail1);
        this.bottom.addChild(this.wing_left);
        this.root.addChild(this.engine_left);
        this.bottom.addChild(this.wing_right);
        this.wing_left.addChild(this.rocket_left);
        this.engine_blade_spin.addChild(this.blade4);
        this.engine_right.addChild(this.engine_right_nose);
        this.engine_blade_spin.addChild(this.blade3);
        this.root.addChild(this.frame2_left);
        this.root.addChild(this.engine_right);
        this.engine_blade_spin.addChild(this.blade2);
        this.frame_middle.addChild(this.frame_right);
        this.engine_left.addChild(this.engine_left_nose);
        this.tail1.addChild(this.tail2);
        this.frame3_right.addChild(this.frame_front);
        this.frame2_right.addChild(this.frame3_right);
        this.tail_fin.addChild(this.tail_fin_engine_blade_spin);
        this.root.addChild(this.engine_blade_spin);
        this.root.addChild(this.nose);
        this.tail_fin_engine_blade_spin.addChild(this.tail_fin_blade3);
        this.tail_fin_engine_blade_spin.addChild(this.tail_fin_blade1);
        this.frame3_right.addChild(this.frame_middle);
        this.wing_right.addChild(this.rocket_right);
        this.tail_fin_engine_blade_spin.addChild(this.tail_fin_blade4);
        this.tail2.addChild(this.tail_fin);
        this.root.addChild(this.bottom);
        this.engine_blade_spin.addChild(this.blade1);
        this.frame2_left.addChild(this.frame3_left);

        mapModelRotations(root);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.root.render(f5);
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
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

        float moveForward = ((EntityLivingBase) entityIn).moveForward;
        if (!entityIn.onGround || moveForward > 0.1F/* && entityIn.getPassengers().size() > 0*/) {
            spinModelPieceY(this.engine_blade_spin, entityIn, ageInTicks, 0.5F);
            spinModelPieceX(this.tail_fin_engine_blade_spin, entityIn, ageInTicks, 0.5F);
        }

        tiltModelPiece(root, entityIn, 0.7F);

    }

    @Override
    public ModelRenderer getModelForItem() {
        return null;
    }

    @Override
    public void translateToHand() {

    }

    @Override
    public float getShadowScale() {
        return 3.0F;
    }
}
