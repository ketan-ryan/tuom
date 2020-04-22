package library.entities.vehicles;

import library.entities.mobs.models.LibModelBase;
import library.entities.vehicles.LibEntityVehicle;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/**
 * vehicle_monster_truck - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelMonsterTruck extends LibModelBase {
    public ModelRenderer body;
    public ModelRenderer windshield_right;
    public ModelRenderer engine;
    public ModelRenderer front;
    public ModelRenderer back;
    public ModelRenderer side_right;
    public ModelRenderer side_left;
    public ModelRenderer trunk;
    public ModelRenderer bumper_front;
    public ModelRenderer bumper_back;
    public ModelRenderer wheel_left_back;
    public ModelRenderer wheel_left_front;
    public ModelRenderer wheel_right_front;
    public ModelRenderer wheel_right_back;
    public ModelRenderer wheel_well2;
    public ModelRenderer wheel_well1;
    public ModelRenderer windshield_left;
    public ModelRenderer front_shield;
    public ModelRenderer intake;
    public ModelRenderer wheel_left_back_tread;
    public ModelRenderer wheel_left_back_tread2;
    public ModelRenderer wheel_left_front_tread;
    public ModelRenderer wheel_left_front_tread2;
    public ModelRenderer wheel_right_front_tread;
    public ModelRenderer wheel_right_front_tread2;
    public ModelRenderer wheel_right_back_tread;
    public ModelRenderer wheel_right_back_tread2;

    public LibModelMonsterTruck() {
        this.textureWidth = 512;
        this.textureHeight = 512;
        this.wheel_left_front_tread = new ModelRenderer(this, 10, 400);
        this.wheel_left_front_tread.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wheel_left_front_tread.addBox(-11.5F, -25.0F, -25.0F, 23, 50, 50, 0.0F);
        this.windshield_right = new ModelRenderer(this, 350, 10);
        this.windshield_right.setRotationPoint(-14.5F, -14.1F, 0.0F);
        this.windshield_right.addBox(-3.0F, -7.1F, -15.0F, 6, 6, 30, 0.0F);
        this.setRotateAngle(windshield_right, 0.8196066167365371F, 0.0F, 0.0F);
        this.wheel_well2 = new ModelRenderer(this, 375, 475);
        this.wheel_well2.setRotationPoint(0.0F, 25.0F, 65.0F);
        this.wheel_well2.addBox(-25.0F, -2.0F, -2.0F, 50, 4, 4, 0.0F);
        this.front_shield = new ModelRenderer(this, 355, 80);
        this.front_shield.setRotationPoint(0.0F, -10.5F, -7.7F);
        this.front_shield.addBox(-11.5F, -2.5F, -3.0F, 23, 5, 6, 0.0F);
        this.wheel_left_back_tread = new ModelRenderer(this, 10, 400);
        this.wheel_left_back_tread.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wheel_left_back_tread.addBox(-11.5F, -25.0F, -25.0F, 23, 50, 50, 0.0F);
        this.wheel_left_front_tread2 = new ModelRenderer(this, 180, 410);
        this.wheel_left_front_tread2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wheel_left_front_tread2.addBox(-11.0F, -12.5F, -30.0F, 22, 25, 60, 0.0F);
        this.setRotateAngle(wheel_left_front_tread2, 1.5707963267948966F, 0.0F, 0.0F);
        this.side_right = new ModelRenderer(this, 350, 115);
        this.side_right.setRotationPoint(-14.0F, -11.5F, 14.0F);
        this.side_right.addBox(-2.0F, -3.5F, -21.0F, 4, 7, 42, 0.0F);
        this.wheel_well1 = new ModelRenderer(this, 375, 475);
        this.wheel_well1.setRotationPoint(0.0F, 25.0F, -20.0F);
        this.wheel_well1.addBox(-25.0F, -2.0F, -2.0F, 50, 4, 4, 0.0F);
        this.wheel_right_back_tread = new ModelRenderer(this, 10, 400);
        this.wheel_right_back_tread.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wheel_right_back_tread.addBox(-11.5F, -25.0F, -25.0F, 23, 50, 50, 0.0F);
        this.bumper_back = new ModelRenderer(this, 50, 375);
        this.bumper_back.setRotationPoint(0.0F, 15.0F, 98.0F);
        this.bumper_back.addBox(-25.0F, 0.0F, -4.0F, 50, 9, 8, 0.0F);
        this.wheel_left_front = new ModelRenderer(this, 180, 410);
        this.wheel_left_front.setRotationPoint(35.0F, 25.0F, -20.0F);
        this.wheel_left_front.addBox(-11.0F, -12.5F, -30.0F, 22, 25, 60, 0.0F);
        this.windshield_left = new ModelRenderer(this, 350, 10);
        this.windshield_left.setRotationPoint(14.5F, -14.1F, 0.0F);
        this.windshield_left.addBox(-3.0F, -7.1F, -15.0F, 6, 6, 30, 0.0F);
        this.setRotateAngle(windshield_left, 0.8196066167365371F, 0.0F, 0.0F);
        this.trunk = new ModelRenderer(this, 25, 275);
        this.trunk.setRotationPoint(0.0F, 0.0F, 75.0F);
        this.trunk.addBox(-20.0F, -8.0F, -25.0F, 40, 30, 50, 0.0F);
        this.front = new ModelRenderer(this, 330, 190);
        this.front.setRotationPoint(0.0F, -28.5F, 20.0F);
        this.front.addBox(-20.0F, -2.5F, -15.0F, 40, 5, 30, 0.0F);
        this.body = new ModelRenderer(this, 10, 125);
        this.body.setRotationPoint(0.0F, -30.0F, -25.0F);
        this.body.addBox(-20.0F, -8.0F, -50.0F, 40, 30, 100, 0.0F);
        this.engine = new ModelRenderer(this, 120, 50);
        this.engine.setRotationPoint(0.0F, -11.5F, -25.0F);
        this.engine.addBox(-5.0F, -3.5F, -10.0F, 10, 7, 20, 0.0F);
        this.wheel_right_front = new ModelRenderer(this, 180, 410);
        this.wheel_right_front.setRotationPoint(-35.0F, 25.0F, -20.0F);
        this.wheel_right_front.addBox(-11.0F, -12.5F, -30.0F, 22, 25, 60, 0.0F);
        this.wheel_right_back = new ModelRenderer(this, 180, 410);
        this.wheel_right_back.setRotationPoint(-35.0F, 25.0F, 65.0F);
        this.wheel_right_back.addBox(-11.0F, -12.5F, -30.0F, 22, 25, 60, 0.0F);
        this.wheel_left_back_tread2 = new ModelRenderer(this, 180, 410);
        this.wheel_left_back_tread2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wheel_left_back_tread2.addBox(-11.0F, -12.5F, -30.0F, 22, 25, 60, 0.0F);
        this.setRotateAngle(wheel_left_back_tread2, 1.5707963267948966F, 0.0F, 0.0F);
        this.intake = new ModelRenderer(this, 126, 20);
        this.intake.setRotationPoint(0.0F, -5.0F, 0.8F);
        this.intake.addBox(-3.5F, -1.5F, -8.0F, 7, 3, 16, 0.0F);
        this.back = new ModelRenderer(this, 340, 250);
        this.back.setRotationPoint(0.0F, -19.5F, 45.0F);
        this.back.addBox(-20.0F, -11.5F, -10.0F, 40, 23, 15, 0.0F);
        this.wheel_right_front_tread = new ModelRenderer(this, 10, 400);
        this.wheel_right_front_tread.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wheel_right_front_tread.addBox(-11.5F, -25.0F, -25.0F, 23, 50, 50, 0.0F);
        this.bumper_front = new ModelRenderer(this, 100, 90);
        this.bumper_front.setRotationPoint(0.0F, 15.0F, -51.5F);
        this.bumper_front.addBox(-25.0F, 0.0F, -4.0F, 50, 9, 8, 0.0F);
        this.wheel_right_back_tread2 = new ModelRenderer(this, 180, 410);
        this.wheel_right_back_tread2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wheel_right_back_tread2.addBox(-11.0F, -12.5F, -30.0F, 22, 25, 60, 0.0F);
        this.setRotateAngle(wheel_right_back_tread2, 1.5707963267948966F, 0.0F, 0.0F);
        this.wheel_left_back = new ModelRenderer(this, 180, 410);
        this.wheel_left_back.setRotationPoint(35.0F, 25.0F, 65.0F);
        this.wheel_left_back.addBox(-11.0F, -12.5F, -30.0F, 22, 25, 60, 0.0F);
        this.side_left = new ModelRenderer(this, 350, 115);
        this.side_left.mirror = true;
        this.side_left.setRotationPoint(14.0F, -11.5F, 14.0F);
        this.side_left.addBox(-2.0F, -3.5F, -21.0F, 4, 7, 42, 0.0F);
        this.wheel_right_front_tread2 = new ModelRenderer(this, 180, 410);
        this.wheel_right_front_tread2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.wheel_right_front_tread2.addBox(-11.0F, -12.5F, -30.0F, 22, 25, 60, 0.0F);
        this.setRotateAngle(wheel_right_front_tread2, 1.5707963267948966F, 0.0F, 0.0F);
        this.wheel_left_front.addChild(this.wheel_left_front_tread);
        this.body.addChild(this.windshield_right);
        this.body.addChild(this.wheel_well2);
        this.body.addChild(this.front_shield);
        this.wheel_left_back.addChild(this.wheel_left_back_tread);
        this.wheel_left_front.addChild(this.wheel_left_front_tread2);
        this.body.addChild(this.side_right);
        this.body.addChild(this.wheel_well1);
        this.wheel_right_back.addChild(this.wheel_right_back_tread);
        this.body.addChild(this.bumper_back);
        this.body.addChild(this.wheel_left_front);
        this.body.addChild(this.windshield_left);
        this.body.addChild(this.trunk);
        this.body.addChild(this.front);
        this.body.addChild(this.engine);
        this.body.addChild(this.wheel_right_front);
        this.body.addChild(this.wheel_right_back);
        this.wheel_left_back.addChild(this.wheel_left_back_tread2);
        this.engine.addChild(this.intake);
        this.body.addChild(this.back);
        this.wheel_right_front.addChild(this.wheel_right_front_tread);
        this.body.addChild(this.bumper_front);
        this.wheel_right_back.addChild(this.wheel_right_back_tread2);
        this.body.addChild(this.wheel_left_back);
        this.body.addChild(this.side_left);
        this.wheel_right_front.addChild(this.wheel_right_front_tread2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
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
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        float moveForward = ((EntityLivingBase) entityIn).moveForward;
        if (entityIn instanceof LibEntityVehicle) {
            moveForward = ((LibEntityVehicle) entityIn).moveForwardLife * 0.4F;
            wheel_left_back.rotateAngleX = moveForward;
            wheel_left_front.rotateAngleX = moveForward;
            wheel_right_back.rotateAngleX = moveForward;
            wheel_right_front.rotateAngleX = moveForward;
        } else {
            boolean isForward = moveForward >= 0;
            wheel_left_back.rotateAngleX = isForward ? limbSwing : -limbSwing;
            wheel_left_front.rotateAngleX = isForward ? limbSwing : -limbSwing;
            wheel_right_back.rotateAngleX = isForward ? limbSwing : -limbSwing;
            wheel_right_front.rotateAngleX = isForward ? limbSwing : -limbSwing;
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
        return 6.0F;
    }
}
