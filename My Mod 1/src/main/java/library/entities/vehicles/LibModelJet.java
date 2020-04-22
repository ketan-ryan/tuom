package library.entities.vehicles;

import library.entities.mobs.models.LibModelBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * vehicle_flying - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelJet extends LibModelBase {
	public ModelRenderer cockpit;
    public ModelRenderer ship_nose;
    public ModelRenderer seat;
    public ModelRenderer display;
    public ModelRenderer back;
    public ModelRenderer right_engine_intake;
    public ModelRenderer right_wing;
    public ModelRenderer right_fin;
    public ModelRenderer right_wing_intake;
    public ModelRenderer left_wing;
    public ModelRenderer left_engine_intake;
    public ModelRenderer left_wing_intake;
    public ModelRenderer left_fin;
    public ModelRenderer right_engine_nose;
    public ModelRenderer left_engine_nose;

    public LibModelJet() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.left_engine_intake = new ModelRenderer(this, 165, 34);
        this.left_engine_intake.setRotationPoint(15.0F, 10.0F, 0.0F);
        this.left_engine_intake.addBox(-7.5F, -7.5F, -16.5F, 10, 10, 35, 0.0F);
        this.right_wing_intake = new ModelRenderer(this, 120, 160);
        this.right_wing_intake.setRotationPoint(-29.0F, 13.0F, 0.0F);
        this.right_wing_intake.addBox(-3.5F, -3.5F, 0.0F, 5, 5, 35, 0.0F);
        this.left_wing_intake = new ModelRenderer(this, 120, 160);
        this.left_wing_intake.setRotationPoint(29.0F, 13.0F, 0.0F);
        this.left_wing_intake.addBox(-3.5F, -3.5F, 0.0F, 5, 5, 35, 0.0F);
        this.cockpit = new ModelRenderer(this, 0, 60);
        this.cockpit.setRotationPoint(0.30000000000000004F, 0.0F, -8.0F);
        this.cockpit.addBox(-7.5F, -7.5F, -16.5F, 15, 15, 35, 0.0F);
        this.display = new ModelRenderer(this, 120, 85);
        this.display.setRotationPoint(0.0F, -7.7F, -7.0F);
        this.display.addBox(-5.0F, -5.0F, -3.5F, 10, 10, 7, 0.0F);
        this.setRotateAngle(display, -1.0016444577195458F, 0.0F, 0.0F);
        this.right_engine_nose = new ModelRenderer(this, 185, 0);
        this.right_engine_nose.setRotationPoint(-1.5F, -2.9F, -12.0F);
        this.right_engine_nose.addBox(-5.0F, -5.0F, -14.5F, 8, 10, 15, 0.0F);
        this.setRotateAngle(right_engine_nose, 0.5462880558742251F, 0.0F, 0.0F);
        this.seat = new ModelRenderer(this, 120, 50);
        this.seat.setRotationPoint(0.0F, -10.0F, 15.0F);
        this.seat.addBox(-5.0F, -7.5F, -0.5F, 10, 15, 2, 0.0F);
        this.right_wing = new ModelRenderer(this, 70, 219);
        this.right_wing.mirror = true;
        this.right_wing.setRotationPoint(-8.2F, 9.0F, -3.5F);
        this.right_wing.addBox(-60.0F, -0.5F, -15.0F, 60, 1, 30, 0.0F);
        this.setRotateAngle(right_wing, 0.0F, 0.6373942428283291F, 0.0F);
        this.ship_nose = new ModelRenderer(this, 0, 0);
        this.ship_nose.setRotationPoint(-1.1F, 8.2F, -28.0F);
        this.ship_nose.addBox(-7.5F, -7.5F, -16.5F, 17, 15, 35, 0.0F);
        this.setRotateAngle(ship_nose, 0.5462880558742251F, 0.0F, 0.0F);
        this.left_fin = new ModelRenderer(this, 150, 130);
        this.left_fin.setRotationPoint(28.2F, 11.5F, 28.0F);
        this.left_fin.addBox(-0.5F, -12.0F, -6.0F, 1, 12, 6, 0.0F);
        this.setRotateAngle(left_fin, -0.4553564018453205F, 0.0F, 0.0F);
        this.left_wing = new ModelRenderer(this, 70, 219);
        this.left_wing.setRotationPoint(56.5F, 9.0F, 32.0F);
        this.left_wing.addBox(-60.0F, -0.5F, -15.0F, 60, 1, 30, 0.0F);
        this.setRotateAngle(left_wing, 0.0F, -0.6373942428283291F, 0.0F);
        this.right_engine_intake = new ModelRenderer(this, 165, 34);
        this.right_engine_intake.setRotationPoint(-10.0F, 10.0F, 0.0F);
        this.right_engine_intake.addBox(-7.5F, -7.5F, -16.5F, 10, 10, 35, 0.0F);
        this.right_fin = new ModelRenderer(this, 150, 130);
        this.right_fin.setRotationPoint(-30.0F, 11.5F, 28.0F);
        this.right_fin.addBox(-0.5F, -12.0F, -6.0F, 1, 12, 6, 0.0F);
        this.setRotateAngle(right_fin, -0.4553564018453205F, 0.0F, 0.0F);
        this.back = new ModelRenderer(this, 33, 120);
        this.back.setRotationPoint(0.0F, 0.0F, 20.0F);
        this.back.addBox(-5.0F, -6.0F, -3.5F, 10, 12, 7, 0.0F);
        this.left_engine_nose = new ModelRenderer(this, 185, 0);
        this.left_engine_nose.setRotationPoint(-1.5F, -2.9F, -12.0F);
        this.left_engine_nose.addBox(-5.0F, -5.0F, -14.5F, 8, 10, 15, 0.0F);
        this.setRotateAngle(left_engine_nose, 0.5462880558742251F, 0.0F, 0.0F);
        this.cockpit.addChild(this.left_engine_intake);
        this.cockpit.addChild(this.right_wing_intake);
        this.cockpit.addChild(this.left_wing_intake);
        this.cockpit.addChild(this.display);
        this.right_engine_intake.addChild(this.right_engine_nose);
        this.cockpit.addChild(this.seat);
        this.cockpit.addChild(this.right_wing);
        this.cockpit.addChild(this.ship_nose);
        this.cockpit.addChild(this.left_fin);
        this.cockpit.addChild(this.left_wing);
        this.cockpit.addChild(this.right_engine_intake);
        this.cockpit.addChild(this.right_fin);
        this.cockpit.addChild(this.back);
        this.left_engine_intake.addChild(this.left_engine_nose);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.cockpit.render(f5);
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
        boolean tilt = false;
        if (entityIn instanceof LibEntityVehicle) {
            tilt = ((LibEntityVehicle) entityIn).isFlyingControl();
        }
        if (tilt) {
            cockpit.rotateAngleX = (float) Math.toRadians(entityIn.rotationPitch * 2F) * 0.35F;
        }
    }

    @Override
    public float getShadowScale() {
        return 3F;
    }
}
