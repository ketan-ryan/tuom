package library.entities.vehicles;

import library.entities.mobs.models.LibModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/**
 * vehicle_hovercraft - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelHovercraft extends LibModelBase {
    public ModelRenderer body_root;
    public ModelRenderer bumper_front;
    public ModelRenderer bumper_back;
    public ModelRenderer front_visor;
    public ModelRenderer front_visor_side_right;
    public ModelRenderer ski_right;
    public ModelRenderer ski_left;
    public ModelRenderer engine;
    public ModelRenderer seat;
    public ModelRenderer panel_side_right;
    public ModelRenderer panel_side_left;
    public ModelRenderer front_visor_side_left;
    public ModelRenderer ski_right_front;
    public ModelRenderer ski_left_front;
    public ModelRenderer engine_back_spin;
    public ModelRenderer blade_1;
    public ModelRenderer blade_2;

    public LibModelHovercraft() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.front_visor_side_left = new ModelRenderer(this, 90, 30);
        this.front_visor_side_left.setRotationPoint(10.0F, 1.9F, -25.0F);
        this.front_visor_side_left.addBox(-2.5F, -18.0F, -4.5F, 5, 18, 8, 0.0F);
        this.setRotateAngle(front_visor_side_left, -1.0471975511965976F, 0.0F, 0.0F);
        this.front_visor_side_right = new ModelRenderer(this, 90, 30);
        this.front_visor_side_right.setRotationPoint(-10.0F, 1.9F, -25.0F);
        this.front_visor_side_right.addBox(-2.5F, -18.0F, -4.5F, 5, 18, 8, 0.0F);
        this.setRotateAngle(front_visor_side_right, -1.0471975511965976F, 0.0F, 0.0F);
        this.ski_left = new ModelRenderer(this, 5, 163);
        this.ski_left.setRotationPoint(18.5F, 7.0F, 0.0F);
        this.ski_left.addBox(-5.5F, -1.0F, -40.0F, 11, 2, 80, 0.0F);
        this.panel_side_right = new ModelRenderer(this, 20, 60);
        this.panel_side_right.setRotationPoint(-10.0F, -7.5F, 26.5F);
        this.panel_side_right.addBox(-1.5F, -1.5F, -40.0F, 3, 3, 40, 0.0F);
        this.blade_1 = new ModelRenderer(this, 120, 250);
        this.blade_1.setRotationPoint(0.0F, 0.0F, 3.5F);
        this.blade_1.addBox(-17.5F, -1.5F, -1.5F, 35, 3, 1, 0.0F);
        this.blade_2 = new ModelRenderer(this, 120, 250);
        this.blade_2.setRotationPoint(0.0F, 0.0F, 3.8F);
        this.blade_2.addBox(-17.5F, -1.5F, -1.5F, 35, 3, 1, 0.0F);
        this.setRotateAngle(blade_2, 0.0F, 0.0F, -1.5707963267948966F);
        this.ski_right = new ModelRenderer(this, 5, 163);
        this.ski_right.setRotationPoint(-18.5F, 7.0F, 0.0F);
        this.ski_right.addBox(-5.5F, -1.0F, -40.0F, 11, 2, 80, 0.0F);
        this.panel_side_left = new ModelRenderer(this, 20, 60);
        this.panel_side_left.setRotationPoint(10.0F, -7.5F, 26.5F);
        this.panel_side_left.addBox(-1.5F, -1.5F, -40.0F, 3, 3, 40, 0.0F);
        this.ski_left_front = new ModelRenderer(this, 80, 150);
        this.ski_left_front.setRotationPoint(0.0F, 0.3F, -39.3F);
        this.ski_left_front.addBox(-5.0F, -1.0F, -4.0F, 10, 2, 4, 0.0F);
        this.setRotateAngle(ski_left_front, -0.7853981633974483F, 0.0F, 0.0F);
        this.bumper_back = new ModelRenderer(this, 128, 202);
        this.bumper_back.setRotationPoint(0.0F, 0.4F, 30.0F);
        this.bumper_back.addBox(-12.5F, -4.5F, -4.5F, 25, 9, 9, 0.0F);
        this.setRotateAngle(bumper_back, -0.7853981633974483F, 0.0F, 0.0F);
        this.bumper_front = new ModelRenderer(this, 117, 2);
        this.bumper_front.setRotationPoint(0.0F, 0.4F, -30.0F);
        this.bumper_front.addBox(-12.5F, -4.5F, -4.5F, 25, 12, 9, 0.0F);
        this.setRotateAngle(bumper_front, -0.7853981633974483F, 0.0F, 0.0F);
        this.engine_back_spin = new ModelRenderer(this, 144, 225);
        this.engine_back_spin.setRotationPoint(0.0F, -11.0F, 5.0F);
        this.engine_back_spin.addBox(-4.0F, -4.0F, -2.0F, 8, 8, 4, 0.0F);
        this.body_root = new ModelRenderer(this, 65, 61);
        this.body_root.setRotationPoint(0.0F, 16.0F, -2.4F);
        this.body_root.addBox(-15.0F, -6.0F, -30.0F, 30, 12, 60, 0.0F);
        this.ski_right_front = new ModelRenderer(this, 80, 150);
        this.ski_right_front.setRotationPoint(0.0F, 0.3F, -39.3F);
        this.ski_right_front.addBox(-5.0F, -1.0F, -4.0F, 10, 2, 4, 0.0F);
        this.setRotateAngle(ski_right_front, -0.7853981633974483F, 0.0F, 0.0F);
        this.front_visor = new ModelRenderer(this, 131, 30);
        this.front_visor.setRotationPoint(0.0F, 0.0F, -25.0F);
        this.front_visor.addBox(-7.5F, -18.0F, -4.5F, 15, 18, 9, 0.0F);
        this.setRotateAngle(front_visor, -1.0471975511965976F, 0.0F, 0.0F);
        this.seat = new ModelRenderer(this, 135, 142);
        this.seat.setRotationPoint(0.0F, -6.0F, 23.0F);
        this.seat.addBox(-8.5F, -8.0F, -3.5F, 17, 8, 7, 0.0F);
        this.engine = new ModelRenderer(this, 127, 165);
        this.engine.setRotationPoint(0.0F, 0.0F, 30.0F);
        this.engine.addBox(-11.5F, -19.0F, -3.5F, 23, 19, 7, 0.0F);
        this.body_root.addChild(this.front_visor_side_left);
        this.body_root.addChild(this.front_visor_side_right);
        this.body_root.addChild(this.ski_left);
        this.body_root.addChild(this.panel_side_right);
        this.engine_back_spin.addChild(this.blade_1);
        this.engine_back_spin.addChild(this.blade_2);
        this.body_root.addChild(this.ski_right);
        this.body_root.addChild(this.panel_side_left);
        this.ski_left.addChild(this.ski_left_front);
        this.body_root.addChild(this.bumper_back);
        this.body_root.addChild(this.bumper_front);
        this.engine.addChild(this.engine_back_spin);
        this.ski_right.addChild(this.ski_right_front);
        this.body_root.addChild(this.front_visor);
        this.body_root.addChild(this.seat);
        this.body_root.addChild(this.engine);

        mapModelRotations(body_root);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.translate(0, -0.25F, 0);
        this.body_root.render(f5);
        GlStateManager.translate(0, 0.25F, 0);
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
        if (entityIn instanceof LibEntityVehicle) {
            moveForward = ((LibEntityVehicle) entityIn).moveForwardLife * 0.4F;
            this.engine_back_spin.rotateAngleZ = moveForward;
        } else {
            boolean isForward = moveForward >= 0;
            this.engine_back_spin.rotateAngleZ = isForward ? limbSwing : -limbSwing;
        }
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
        return 2.7F;
    }
}
