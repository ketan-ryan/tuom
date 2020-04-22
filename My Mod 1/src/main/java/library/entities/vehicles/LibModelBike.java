package library.entities.vehicles;

import library.entities.mobs.models.LibModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * vehicle_future_bike - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelBike extends LibModelBase {
    public ModelRenderer seat;
    public ModelRenderer front;
    public ModelRenderer back;
    public ModelRenderer front_bottom;
    public ModelRenderer front_bar;
    public ModelRenderer front_fan;
    public ModelRenderer back_bottom;
    public ModelRenderer back_engine;
    public ModelRenderer back_fan;
    public ModelRenderer back_fin_left;
    public ModelRenderer back_fin_right;

    public LibModelBike() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.front = new ModelRenderer(this, 0, 0);
        this.front.setRotationPoint(0.0F, -1.2F, 9.2F);
        this.front.addBox(-7.5F, -10.0F, 0.0F, 15, 20, 30, 0.0F);
        this.back_bottom = new ModelRenderer(this, 135, 50);
        this.back_bottom.setRotationPoint(0.0F, 11.0F, -1.3F);
        this.back_bottom.addBox(-8.5F, 0.0F, -30.0F, 17, 4, 33, 0.0F);
        this.back = new ModelRenderer(this, 0, 145);
        this.back.setRotationPoint(0.0F, -1.2F, -10.0F);
        this.back.addBox(-7.5F, -10.0F, -30.0F, 15, 20, 30, 0.0F);
        this.front_bottom = new ModelRenderer(this, 135, 50);
        this.front_bottom.setRotationPoint(0.0F, 11.0F, -1.3F);
        this.front_bottom.addBox(-8.5F, 0.0F, 0.0F, 17, 4, 33, 0.0F);
        this.back_engine = new ModelRenderer(this, 10, 110);
        this.back_engine.setRotationPoint(0.0F, 0.0F, -14.0F);
        this.back_engine.addBox(-4.0F, -12.0F, -11.0F, 8, 2, 22, 0.0F);
        this.back_fan = new ModelRenderer(this, 155, 110);
        this.back_fan.setRotationPoint(0.0F, 18.0F, -15.0F);
        this.back_fan.addBox(-7.5F, -2.0F, -7.5F, 15, 4, 15, 0.0F);
        this.back_fin_right = new ModelRenderer(this, 150, 140);
        this.back_fin_right.setRotationPoint(7.0F, -1.2F, -20.0F);
        this.back_fin_right.addBox(0.0F, -1.0F, -10.0F, 15, 1, 20, 0.0F);
        this.seat = new ModelRenderer(this, 0, 60);
        this.seat.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.seat.addBox(-5.0F, -5.0F, -11.0F, 10, 10, 22, 0.0F);
        this.setRotateAngle(seat, 0.0F, 3.141592653589793F, 0.0F);
        this.front_bar = new ModelRenderer(this, 130, 0);
        this.front_bar.setRotationPoint(0.0F, -11.0F, -1.3F);
        this.front_bar.addBox(-10.0F, 0.0F, 0.0F, 20, 4, 38, 0.0F);
        this.setRotateAngle(front_bar, -0.4553564018453205F, 0.0F, 0.0F);
        this.back_fin_left = new ModelRenderer(this, 150, 140);
        this.back_fin_left.setRotationPoint(-7.0F, -1.2F, -20.0F);
        this.back_fin_left.addBox(-15.0F, -1.0F, -10.0F, 15, 1, 20, 0.0F);
        this.front_fan = new ModelRenderer(this, 155, 110);
        this.front_fan.setRotationPoint(0.0F, 18.0F, 15.0F);
        this.front_fan.addBox(-7.5F, -2.0F, -7.5F, 15, 4, 15, 0.0F);
        this.seat.addChild(this.front);
        this.back.addChild(this.back_bottom);
        this.seat.addChild(this.back);
        this.front.addChild(this.front_bottom);
        this.back.addChild(this.back_engine);
        this.back.addChild(this.back_fan);
        this.back.addChild(this.back_fin_right);
        this.front.addChild(this.front_bar);
        this.back.addChild(this.back_fin_left);
        this.front.addChild(this.front_fan);

        mapModelRotations(seat);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.seat.render(f5);
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

        spinModelPieceY(this.front_fan, entityIn, ageInTicks, 0.5F);
        spinModelPieceY(this.back_fan, entityIn, ageInTicks, 0.5F);

        boolean tilt = (entityIn instanceof LibEntityVehicle ? ((LibEntityVehicle) entityIn).isFlyingControl() : false);
        if (tilt) {
            seat.rotateAngleX = (float)-Math.toRadians(entityIn.rotationPitch * 2F) * 0.35F;
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
        return 2.5F;
    }
}
