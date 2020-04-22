package library.entities.vehicles;

import library.entities.mobs.models.LibModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/**
 * vehicle_biplane - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelBiPlane extends LibModelBase {
    public ModelRenderer body_root;
    public ModelRenderer wing_top;
    public ModelRenderer wing_bottom;
    public ModelRenderer back;
    public ModelRenderer wingbar_right;
    public ModelRenderer wingbar_left;
    public ModelRenderer console;
    public ModelRenderer seat;
    public ModelRenderer engine_pipe1_right;
    public ModelRenderer nose;
    public ModelRenderer engine_pipe2_right;
    public ModelRenderer engine_pipe3_right;
    public ModelRenderer engine_pipe1_left;
    public ModelRenderer engine_pipe1_left_1;
    public ModelRenderer engine_pipe1_left_2;
    public ModelRenderer wing_back;
    public ModelRenderer tail_fin;
    public ModelRenderer propeller_top_spin;
    public ModelRenderer blade_1;
    public ModelRenderer blade_2;

    public LibModelBiPlane() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.propeller_top_spin = new ModelRenderer(this, 20, 19);
        this.propeller_top_spin.setRotationPoint(0.0F, 0.0F, -25.0F);
        this.propeller_top_spin.addBox(-5.0F, -5.0F, -4.0F, 10, 10, 4, 0.0F);
        this.wing_back = new ModelRenderer(this, 80, 200);
        this.wing_back.setRotationPoint(0.0F, 0.0F, 48.9F);
        this.wing_back.addBox(-20.0F, -1.0F, -10.0F, 40, 1, 11, 0.0F);
        this.engine_pipe1_right = new ModelRenderer(this, 75, 10);
        this.engine_pipe1_right.setRotationPoint(-9.0F, -4.0F, -16.0F);
        this.engine_pipe1_right.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.setRotateAngle(engine_pipe1_right, 0.7853981633974483F, 0.0F, 0.6373942428283291F);
        this.engine_pipe3_right = new ModelRenderer(this, 75, 10);
        this.engine_pipe3_right.setRotationPoint(-9.0F, -4.0F, -9.0F);
        this.engine_pipe3_right.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.setRotateAngle(engine_pipe3_right, 0.7853981633974483F, 0.0F, 0.6373942428283291F);
        this.back = new ModelRenderer(this, 90, 150);
        this.back.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.back.addBox(-6.5F, -6.5F, 25.0F, 13, 9, 25, 0.0F);
        this.wing_bottom = new ModelRenderer(this, 11, 46);
        this.wing_bottom.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wing_bottom.addBox(-50.0F, -1.0F, -10.0F, 100, 1, 20, 0.0F);
        this.wingbar_right = new ModelRenderer(this, 240, 40);
        this.wingbar_right.setRotationPoint(-40.0F, 0.0F, 0.0F);
        this.wingbar_right.addBox(-0.5F, -20.0F, -0.5F, 1, 19, 1, 0.0F);
        this.nose = new ModelRenderer(this, 175, 10);
        this.nose.setRotationPoint(0.0F, 0.0F, -21.0F);
        this.nose.addBox(-11.5F, -9.0F, -2.5F, 23, 18, 5, 0.0F);
        this.body_root = new ModelRenderer(this, 60, 75);
        this.body_root.setRotationPoint(0.0F, 16.2F, -3.4F);
        this.body_root.addBox(-10.0F, -7.5F, -25.0F, 20, 15, 50, 0.0F);
        this.wingbar_left = new ModelRenderer(this, 240, 40);
        this.wingbar_left.setRotationPoint(40.0F, 0.0F, 0.0F);
        this.wingbar_left.addBox(-0.5F, -20.0F, -0.5F, 1, 19, 1, 0.0F);
        this.blade_2 = new ModelRenderer(this, 10, 5);
        this.blade_2.setRotationPoint(0.0F, 0.0F, -4.5F);
        this.blade_2.addBox(-1.0F, -20.0F, -0.7F, 2, 40, 1, 0.0F);
        this.setRotateAngle(blade_2, 0.0F, -0.0F, 1.5707963267948966F);
        this.engine_pipe2_right = new ModelRenderer(this, 75, 10);
        this.engine_pipe2_right.setRotationPoint(-9.0F, -4.0F, -12.5F);
        this.engine_pipe2_right.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.setRotateAngle(engine_pipe2_right, 0.7853981633974483F, 0.0F, 0.6373942428283291F);
        this.seat = new ModelRenderer(this, 119, 32);
        this.seat.setRotationPoint(0.0F, -7.6F, 10.0F);
        this.seat.addBox(-5.0F, -8.0F, -1.0F, 10, 8, 2, 0.0F);
        this.engine_pipe1_left_1 = new ModelRenderer(this, 75, 10);
        this.engine_pipe1_left_1.mirror = true;
        this.engine_pipe1_left_1.setRotationPoint(9.0F, -4.0F, -12.5F);
        this.engine_pipe1_left_1.addBox(0.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.setRotateAngle(engine_pipe1_left_1, 0.7853981633974483F, 0.0F, -0.6373942428283291F);
        this.blade_1 = new ModelRenderer(this, 10, 5);
        this.blade_1.setRotationPoint(0.0F, 0.0F, -4.5F);
        this.blade_1.addBox(-1.0F, -20.0F, -0.5F, 2, 40, 1, 0.0F);
        this.wing_top = new ModelRenderer(this, 11, 46);
        this.wing_top.setRotationPoint(0.0F, -20.0F, 0.0F);
        this.wing_top.addBox(-50.0F, -1.0F, -10.0F, 100, 1, 20, 0.0F);
        this.engine_pipe1_left = new ModelRenderer(this, 75, 10);
        this.engine_pipe1_left.mirror = true;
        this.engine_pipe1_left.setRotationPoint(9.0F, -4.0F, -16.0F);
        this.engine_pipe1_left.addBox(0.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.setRotateAngle(engine_pipe1_left, 0.7853981633974483F, 0.0F, -0.6373942428283291F);
        this.engine_pipe1_left_2 = new ModelRenderer(this, 75, 10);
        this.engine_pipe1_left_2.mirror = true;
        this.engine_pipe1_left_2.setRotationPoint(9.0F, -4.0F, -9.0F);
        this.engine_pipe1_left_2.addBox(0.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.setRotateAngle(engine_pipe1_left_2, 0.7853981633974483F, 0.0F, -0.6373942428283291F);
        this.console = new ModelRenderer(this, 110, 5);
        this.console.setRotationPoint(0.0F, -5.2F, -8.5F);
        this.console.addBox(-8.0F, -4.0F, -5.0F, 16, 8, 10, 0.0F);
        this.setRotateAngle(console, 0.5918411493512771F, 0.0F, 0.0F);
        this.tail_fin = new ModelRenderer(this, 120, 220);
        this.tail_fin.setRotationPoint(0.0F, -6.5F, 43.5F);
        this.tail_fin.addBox(-0.5F, -10.0F, -6.0F, 1, 10, 12, 0.0F);
        this.back.addChild(this.propeller_top_spin);
        this.back.addChild(this.wing_back);
        this.body_root.addChild(this.engine_pipe1_right);
        this.body_root.addChild(this.engine_pipe3_right);
        this.body_root.addChild(this.back);
        this.body_root.addChild(this.wing_bottom);
        this.body_root.addChild(this.wingbar_right);
        this.body_root.addChild(this.nose);
        this.body_root.addChild(this.wingbar_left);
        this.propeller_top_spin.addChild(this.blade_2);
        this.body_root.addChild(this.engine_pipe2_right);
        this.body_root.addChild(this.seat);
        this.body_root.addChild(this.engine_pipe1_left_1);
        this.propeller_top_spin.addChild(this.blade_1);
        this.body_root.addChild(this.wing_top);
        this.body_root.addChild(this.engine_pipe1_left);
        this.body_root.addChild(this.engine_pipe1_left_2);
        this.body_root.addChild(this.console);
        this.back.addChild(this.tail_fin);

        mapModelRotations(body_root);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body_root.render(f5);
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

        resetRotations();

        float moveForward = ((EntityLivingBase) entityIn).moveForward;
        if (!entityIn.onGround || moveForward > 0.1F/* && entityIn.getPassengers().size() > 0*/) {
            spinModelPieceZ(this.propeller_top_spin, entityIn, ageInTicks, 0.5F);
        }

        tiltModelPiece(body_root, entityIn, 0.7F);
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
