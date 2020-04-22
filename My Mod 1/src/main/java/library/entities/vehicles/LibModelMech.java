package library.entities.vehicles;

import library.entities.mobs.models.LibModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

/**
 * vehicle_mech - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelMech extends LibModelBase {
    public ModelRenderer root;
    public ModelRenderer chest;
    public ModelRenderer leg_right;
    public ModelRenderer leg_left;
    public ModelRenderer cockpit;
    public ModelRenderer arm_shoulder_right;
    public ModelRenderer arm_shoulder_left;
    public ModelRenderer cockpit_frame_left_lower;
    public ModelRenderer cockpit_back;
    public ModelRenderer cockpit_frame_top;
    public ModelRenderer cockpit_frame_front;
    public ModelRenderer cockpit_frame_left_up;
    public ModelRenderer cockpit_frame_right_lower;
    public ModelRenderer cockpit_frame_right_up;
    public ModelRenderer cockpit_frame_right;
    public ModelRenderer cockpit_frame_left;
    public ModelRenderer arm_lower_right;
    public ModelRenderer arm_hand_right;
    public ModelRenderer arm_thumb_right;
    public ModelRenderer arm_finger2_right;
    public ModelRenderer arm_finger1_right;
    public ModelRenderer arm_lower_left;
    public ModelRenderer arm_hand_left;
    public ModelRenderer arm_thumb_left;
    public ModelRenderer arm_finger2_left;
    public ModelRenderer arm_finger1_left;
    public ModelRenderer leg_lower_right;
    public ModelRenderer leg_foot_right;
    public ModelRenderer leg_lower_left;
    public ModelRenderer leg_foot_left;

    public LibModelMech() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.leg_left = new ModelRenderer(this, 0, 158);
        this.leg_left.mirror = true;
        this.leg_left.setRotationPoint(14.0F, 5.0F, 0.0F);
        this.leg_left.addBox(-6.0F, 0.0F, -12.0F, 12, 25, 24, 0.0F);
        this.setRotateAngle(leg_left, -0.27314402793711257F, 0.0F, 0.0F);
        this.arm_lower_right = new ModelRenderer(this, 200, 150);
        this.arm_lower_right.setRotationPoint(-7.0F, 15.0F, 0.0F);
        this.arm_lower_right.addBox(-5.0F, 0.0F, -6.5F, 10, 23, 15, 0.0F);
        this.arm_hand_right = new ModelRenderer(this, 200, 200);
        this.arm_hand_right.setRotationPoint(0.0F, 20.8F, 0.8F);
        this.arm_hand_right.addBox(-3.5F, 0.0F, -6.5F, 7, 13, 13, 0.0F);
        this.setRotateAngle(arm_hand_right, -0.5009094953223726F, 0.0F, -0.18203784098300857F);
        this.root = new ModelRenderer(this, 80, 162);
        this.root.setRotationPoint(0.0F, -31.997F, 20.0F);
        this.root.addBox(-12.0F, -14.5F, -10.0F, 24, 20, 20, 0.0F);
        this.leg_lower_left = new ModelRenderer(this, 0, 211);
        this.leg_lower_left.mirror = true;
        this.leg_lower_left.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.leg_lower_left.addBox(-5.0F, 0.0F, -9.0F, 10, 30, 14, 0.0F);
        this.setRotateAngle(leg_lower_left, 0.2617993877991494F, 0.0F, 0.0F);
        this.leg_right = new ModelRenderer(this, 0, 158);
        this.leg_right.setRotationPoint(-14.0F, 5.0F, 0.0F);
        this.leg_right.addBox(-6.0F, 0.0F, -12.0F, 12, 25, 24, 0.0F);
        this.setRotateAngle(leg_right, -0.27314402793711257F, 0.0F, 0.0F);
        this.arm_thumb_left = new ModelRenderer(this, 220, 230);
        this.arm_thumb_left.mirror = true;
        this.arm_thumb_left.setRotationPoint(-1.8F, 10.4F, 0.0F);
        this.arm_thumb_left.addBox(-4.0F, 0.0F, -2.0F, 8, 13, 4, 0.0F);
        this.setRotateAngle(arm_thumb_left, 0.40980330836826856F, -1.5707963267948966F, 0.0F);
        this.cockpit_frame_right_up = new ModelRenderer(this, 180, 0);
        this.cockpit_frame_right_up.mirror = true;
        this.cockpit_frame_right_up.setRotationPoint(-10.0F, -12.0F, -1.8F);
        this.cockpit_frame_right_up.addBox(-2.0F, -3.0F, -12.0F, 4, 6, 24, 0.0F);
        this.setRotateAngle(cockpit_frame_right_up, -0.7853981633974483F, 0.0F, 0.0F);
        this.cockpit_frame_left_up = new ModelRenderer(this, 180, 0);
        this.cockpit_frame_left_up.mirror = true;
        this.cockpit_frame_left_up.setRotationPoint(10.0F, -12.0F, -1.8F);
        this.cockpit_frame_left_up.addBox(-2.0F, -3.0F, -12.0F, 4, 6, 24, 0.0F);
        this.setRotateAngle(cockpit_frame_left_up, -0.7853981633974483F, 0.0F, 0.0F);
        this.arm_finger2_right = new ModelRenderer(this, 197, 230);
        this.arm_finger2_right.setRotationPoint(-1.5F, 13.0F, 4.0F);
        this.arm_finger2_right.addBox(-2.5F, 0.0F, -2.0F, 5, 15, 3, 0.0F);
        this.setRotateAngle(arm_finger2_right, 0.0F, 1.5707963267948966F, 0.0F);
        this.arm_finger1_left = new ModelRenderer(this, 197, 230);
        this.arm_finger1_left.setRotationPoint(2.5F, 13.0F, -3.5F);
        this.arm_finger1_left.addBox(-2.5F, 0.0F, -2.0F, 5, 15, 3, 0.0F);
        this.setRotateAngle(arm_finger1_left, 0.0F, 1.5707963267948966F, 0.0F);
        this.arm_thumb_right = new ModelRenderer(this, 220, 230);
        this.arm_thumb_right.setRotationPoint(1.8F, 10.4F, 0.0F);
        this.arm_thumb_right.addBox(-4.0F, 0.0F, -2.0F, 8, 13, 4, 0.0F);
        this.setRotateAngle(arm_thumb_right, -0.40980330836826856F, -1.5707963267948966F, 0.0F);
        this.leg_lower_right = new ModelRenderer(this, 0, 211);
        this.leg_lower_right.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.leg_lower_right.addBox(-5.0F, 0.0F, -9.0F, 10, 30, 14, 0.0F);
        this.setRotateAngle(leg_lower_right, 0.2617993877991494F, 0.0F, 0.0F);
        this.cockpit_back = new ModelRenderer(this, 19, 0);
        this.cockpit_back.setRotationPoint(0.0F, -15.0F, 20.0F);
        this.cockpit_back.addBox(-8.0F, -5.0F, -20.0F, 16, 15, 24, 0.0F);
        this.leg_foot_right = new ModelRenderer(this, 60, 226);
        this.leg_foot_right.setRotationPoint(0.0F, 30.0F, 0.0F);
        this.leg_foot_right.addBox(-8.0F, -2.5F, -24.0F, 16, 5, 24, 0.0F);
        this.cockpit_frame_front = new ModelRenderer(this, 120, 10);
        this.cockpit_frame_front.setRotationPoint(-8.0F, -5.0F, -20.7F);
        this.cockpit_frame_front.addBox(0.0F, -2.0F, -2.0F, 16, 4, 4, 0.0F);
        this.setRotateAngle(cockpit_frame_front, -0.7853981633974483F, 0.0F, 0.0F);
        this.chest = new ModelRenderer(this, 4, 104);
        this.chest.setRotationPoint(0.0F, -10.0F, 11.0F);
        this.chest.addBox(-24.0F, -24.0F, -24.0F, 48, 24, 24, 0.0F);
        this.arm_hand_left = new ModelRenderer(this, 200, 200);
        this.arm_hand_left.mirror = true;
        this.arm_hand_left.setRotationPoint(0.0F, 18.8F, 0.8F);
        this.arm_hand_left.addBox(-3.5F, 0.0F, -6.5F, 7, 13, 13, 0.0F);
        this.setRotateAngle(arm_hand_left, -0.5009094953223726F, 0.0F, 0.18203784098300857F);
        this.cockpit_frame_right = new ModelRenderer(this, 120, 30);
        this.cockpit_frame_right.setRotationPoint(-10.0F, -22.0F, -6.0F);
        this.cockpit_frame_right.addBox(0.0F, -1.0F, -1.0F, 26, 2, 2, 0.0F);
        this.setRotateAngle(cockpit_frame_right, 0.0F, 0.7853981633974483F, 1.5707963267948966F);
        this.arm_lower_left = new ModelRenderer(this, 200, 150);
        this.arm_lower_left.mirror = true;
        this.arm_lower_left.setRotationPoint(7.0F, 15.0F, 0.0F);
        this.arm_lower_left.addBox(-5.0F, 0.0F, -6.5F, 10, 23, 15, 0.0F);
        this.cockpit_frame_left = new ModelRenderer(this, 120, 30);
        this.cockpit_frame_left.setRotationPoint(10.0F, -22.0F, -6.0F);
        this.cockpit_frame_left.addBox(0.0F, -1.0F, -1.0F, 26, 2, 2, 0.0F);
        this.setRotateAngle(cockpit_frame_left, 0.0F, 0.7853981633974483F, 1.5707963267948966F);
        this.cockpit_frame_left_lower = new ModelRenderer(this, 180, 0);
        this.cockpit_frame_left_lower.mirror = true;
        this.cockpit_frame_left_lower.setRotationPoint(10.0F, 1.0F, -14.5F);
        this.cockpit_frame_left_lower.addBox(-2.0F, -3.0F, -12.0F, 4, 6, 24, 0.0F);
        this.setRotateAngle(cockpit_frame_left_lower, -0.7853981633974483F, 0.0F, 0.0F);
        this.arm_shoulder_left = new ModelRenderer(this, 152, 70);
        this.arm_shoulder_left.mirror = true;
        this.arm_shoulder_left.setRotationPoint(24.0F, -12.0F, -12.0F);
        this.arm_shoulder_left.addBox(0.0F, -18.0F, -18.0F, 15, 36, 36, 0.0F);
        this.arm_finger2_left = new ModelRenderer(this, 197, 230);
        this.arm_finger2_left.mirror = true;
        this.arm_finger2_left.setRotationPoint(2.5F, 13.0F, 4.0F);
        this.arm_finger2_left.addBox(-2.5F, 0.0F, -2.0F, 5, 15, 3, 0.0F);
        this.setRotateAngle(arm_finger2_left, 0.0F, 1.5707963267948966F, 0.0F);
        this.cockpit_frame_top = new ModelRenderer(this, 120, 10);
        this.cockpit_frame_top.setRotationPoint(-8.0F, -18.0F, -8.0F);
        this.cockpit_frame_top.addBox(0.0F, -2.0F, -2.0F, 16, 4, 4, 0.0F);
        this.setRotateAngle(cockpit_frame_top, -0.7853981633974483F, 0.0F, 0.0F);
        this.cockpit = new ModelRenderer(this, 0, 46);
        this.cockpit.setRotationPoint(0.0F, -20.0F, -30.0F);
        this.cockpit.addBox(-15.0F, -5.0F, -20.0F, 30, 10, 40, 0.0F);
        this.setRotateAngle(cockpit, 0.17453292519943295F, 0.0F, 0.0F);
        this.leg_foot_left = new ModelRenderer(this, 60, 226);
        this.leg_foot_left.mirror = true;
        this.leg_foot_left.setRotationPoint(0.0F, 30.0F, 0.0F);
        this.leg_foot_left.addBox(-8.0F, -2.5F, -24.0F, 16, 5, 24, 0.0F);
        this.arm_shoulder_right = new ModelRenderer(this, 152, 70);
        this.arm_shoulder_right.setRotationPoint(-24.0F, -12.0F, -12.0F);
        this.arm_shoulder_right.addBox(-15.0F, -18.0F, -18.0F, 15, 36, 36, 0.0F);
        this.cockpit_frame_right_lower = new ModelRenderer(this, 180, 0);
        this.cockpit_frame_right_lower.mirror = true;
        this.cockpit_frame_right_lower.setRotationPoint(-10.0F, 1.0F, -14.5F);
        this.cockpit_frame_right_lower.addBox(-2.0F, -3.0F, -12.0F, 4, 6, 24, 0.0F);
        this.setRotateAngle(cockpit_frame_right_lower, -0.7853981633974483F, 0.0F, 0.0F);
        this.arm_finger1_right = new ModelRenderer(this, 197, 230);
        this.arm_finger1_right.setRotationPoint(-1.5F, 13.0F, -3.5F);
        this.arm_finger1_right.addBox(-2.5F, 0.0F, -2.0F, 5, 15, 3, 0.0F);
        this.setRotateAngle(arm_finger1_right, 0.0F, 1.5707963267948966F, 0.0F);
        this.root.addChild(this.leg_left);
        this.arm_shoulder_right.addChild(this.arm_lower_right);
        this.arm_lower_right.addChild(this.arm_hand_right);
        this.leg_left.addChild(this.leg_lower_left);
        this.root.addChild(this.leg_right);
        this.arm_hand_left.addChild(this.arm_thumb_left);
        this.cockpit.addChild(this.cockpit_frame_right_up);
        this.cockpit.addChild(this.cockpit_frame_left_up);
        this.arm_hand_right.addChild(this.arm_finger2_right);
        this.arm_hand_left.addChild(this.arm_finger1_left);
        this.arm_hand_right.addChild(this.arm_thumb_right);
        this.leg_right.addChild(this.leg_lower_right);
        this.cockpit.addChild(this.cockpit_back);
        this.leg_lower_right.addChild(this.leg_foot_right);
        this.cockpit.addChild(this.cockpit_frame_front);
        this.root.addChild(this.chest);
        this.arm_lower_left.addChild(this.arm_hand_left);
        this.cockpit.addChild(this.cockpit_frame_right);
        this.arm_shoulder_left.addChild(this.arm_lower_left);
        this.cockpit.addChild(this.cockpit_frame_left);
        this.cockpit.addChild(this.cockpit_frame_left_lower);
        this.chest.addChild(this.arm_shoulder_left);
        this.arm_hand_left.addChild(this.arm_finger2_left);
        this.cockpit.addChild(this.cockpit_frame_top);
        this.chest.addChild(this.cockpit);
        this.leg_lower_left.addChild(this.leg_foot_left);
        this.chest.addChild(this.arm_shoulder_right);
        this.cockpit.addChild(this.cockpit_frame_right_lower);
        this.arm_hand_right.addChild(this.arm_finger1_right);

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

        resetRotations();

        setRotationAngles(null, arm_shoulder_right, new Vec3d(0, 0, 0), arm_shoulder_left, new Vec3d(0, 0, 0), leg_right, leg_left, arm_finger1_left, arm_finger1_right,
                limbSwing / 4F, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        
        if (entityIn.isBeingRidden() && entityIn instanceof LibEntityVehicle) {
            //(float)Math.toRadians(entityIn.getPassengers().get(0).getRotationYawHead());
            cockpit.rotateAngleX = 0.17453292519943295F + (float)Math.toRadians(entityIn.getPassengers().get(0).rotationPitch + 45F) * 0.35F;
            cockpit.rotateAngleY = (float)Math.toRadians(entityIn.getPassengers().get(0).getRotationYawHead() - entityIn.getPassengers().get(0).rotationYaw);
            cockpit.rotateAngleZ = 0;
        }
        
    }

    @Override
    public float getShadowScale() {
		return 2.5F;
	}
}
