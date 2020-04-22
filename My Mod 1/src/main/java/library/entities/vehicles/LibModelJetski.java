package library.entities.vehicles;

import library.entities.mobs.models.LibModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * vehicle_jetski - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelJetski extends LibModelBase {
    public ModelRenderer hull;
    public ModelRenderer front_bow;
    public ModelRenderer seat;
    public ModelRenderer handlebar;
    public ModelRenderer handlebar_grip_left;
    public ModelRenderer handlebar_grip_right;
    public ModelRenderer display;
    public ModelRenderer hull_bumper;
    public ModelRenderer intake_lower_right;
    public ModelRenderer intake_lower_left;
    public ModelRenderer engine_right;
    public ModelRenderer engine_left;
    public ModelRenderer back_plate;

    public LibModelJetski() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.display = new ModelRenderer(this, 200, 4);
        this.display.setRotationPoint(0.0F, -19.0F, 26.2F);
        this.display.addBox(-7.0F, -6.0F, -0.5F, 14, 11, 1, 0.0F);
        this.setRotateAngle(display, 0.5918411493512771F, 0.0F, 0.0F);
        this.handlebar_grip_right = new ModelRenderer(this, 230, 27);
        this.handlebar_grip_right.setRotationPoint(-12.0F, -20.0F, 26.5F);
        this.handlebar_grip_right.addBox(-7.0F, -1.5F, -1.5F, 7, 3, 3, 0.0F);
        this.hull_bumper = new ModelRenderer(this, 1, 60);
        this.hull_bumper.setRotationPoint(0.0F, -4.0F, -9.0F);
        this.hull_bumper.addBox(-12.0F, -2.5F, 0.0F, 24, 5, 66, 0.0F);
        this.engine_left = new ModelRenderer(this, 187, 100);
        this.engine_left.setRotationPoint(12.4F, -6.7F, 35.0F);
        this.engine_left.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 27, 0.0F);
        this.setRotateAngle(engine_left, 0.0F, 0.0F, 0.7853981633974483F);
        this.seat = new ModelRenderer(this, 169, 52);
        this.seat.setRotationPoint(0.0F, -8.0F, 20.0F);
        this.seat.addBox(-7.5F, -3.0F, 0.0F, 15, 6, 27, 0.0F);
        this.intake_lower_right = new ModelRenderer(this, 180, 140);
        this.intake_lower_right.mirror = true;
        this.intake_lower_right.setRotationPoint(-9.5F, 4.0F, 60.0F);
        this.intake_lower_right.addBox(-3.5F, -3.5F, -30.0F, 7, 7, 30, 0.0F);
        this.setRotateAngle(intake_lower_right, 0.0F, 0.0F, 0.7853981633974483F);
        this.engine_right = new ModelRenderer(this, 187, 100);
        this.engine_right.mirror = true;
        this.engine_right.setRotationPoint(-12.3F, -6.7F, 35.0F);
        this.engine_right.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 27, 0.0F);
        this.setRotateAngle(engine_right, 0.0F, 0.0F, 0.7853981633974483F);
        this.back_plate = new ModelRenderer(this, 80, 224);
        this.back_plate.setRotationPoint(0.0F, -1.7F, 58.0F);
        this.back_plate.addBox(-6.5F, -3.0F, -1.0F, 13, 6, 1, 0.0F);
        this.handlebar_grip_left = new ModelRenderer(this, 230, 27);
        this.handlebar_grip_left.setRotationPoint(12.0F, -20.0F, 26.5F);
        this.handlebar_grip_left.addBox(0.0F, -1.5F, -1.5F, 7, 3, 3, 0.0F);
        this.hull = new ModelRenderer(this, 17, 140);
        this.hull.setRotationPoint(0.0F, 18.0F, -35.4F);
        this.hull.addBox(-9.5F, -7.5F, 0.0F, 19, 15, 56, 0.0F);
        this.front_bow = new ModelRenderer(this, 36, 0);
        this.front_bow.setRotationPoint(0.0F, 1.3F, -4.1F);
        this.front_bow.addBox(-10.0F, -7.5F, 0.0F, 20, 15, 36, 0.0F);
        this.setRotateAngle(front_bow, 0.5918411493512771F, 0.0F, 0.0F);
        this.handlebar = new ModelRenderer(this, 163, 30);
        this.handlebar.setRotationPoint(0.1F, -20.0F, 26.5F);
        this.handlebar.addBox(-12.5F, -1.0F, -1.0F, 25, 2, 2, 0.0F);
        this.intake_lower_left = new ModelRenderer(this, 180, 140);
        this.intake_lower_left.setRotationPoint(9.4F, 4.0F, 60.0F);
        this.intake_lower_left.addBox(-3.5F, -3.5F, -30.0F, 7, 7, 30, 0.0F);
        this.setRotateAngle(intake_lower_left, 0.0F, 0.0F, 0.7853981633974483F);
        this.hull.addChild(this.display);
        this.hull.addChild(this.handlebar_grip_right);
        this.hull.addChild(this.hull_bumper);
        this.hull.addChild(this.engine_left);
        this.hull.addChild(this.seat);
        this.hull.addChild(this.intake_lower_right);
        this.hull.addChild(this.engine_right);
        this.hull.addChild(this.back_plate);
        this.hull.addChild(this.handlebar_grip_left);
        this.hull.addChild(this.front_bow);
        this.hull.addChild(this.handlebar);
        this.hull.addChild(this.intake_lower_left);

        mapModelRotations(hull);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.hull.render(f5);
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

        animateLimbs(null, null, null, null, null, null, null,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
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
        return 2.0F;
    }
}
