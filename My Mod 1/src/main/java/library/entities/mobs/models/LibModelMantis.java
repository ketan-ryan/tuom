package library.entities.mobs.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * mob_praying_mantis - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelMantis extends LibModelBase {
    public ModelRenderer root;
    public ModelRenderer tail;
    public ModelRenderer body1;
    public ModelRenderer leg_right_back;
    public ModelRenderer leg_left_back;
    public ModelRenderer body2;
    public ModelRenderer leg_right_front;
    public ModelRenderer leg_left_front;
    public ModelRenderer neck;
    public ModelRenderer arm_right;
    public ModelRenderer arm_left;
    public ModelRenderer head;
    public ModelRenderer eye_left;
    public ModelRenderer eye_right;
    public ModelRenderer jaw;
    public ModelRenderer tooth_right;
    public ModelRenderer tooth_left;
    public ModelRenderer tooth_right_middle;
    public ModelRenderer tooth_left_middle;
    public ModelRenderer arm_lower_right;
    public ModelRenderer arm_right_claw;
    public ModelRenderer arm_lower_left;
    public ModelRenderer arm_left_claw;
    public ModelRenderer leg_right_front_lower;
    public ModelRenderer leg_left_front_lower;
    public ModelRenderer leg_right_back_lower;
    public ModelRenderer leg_left_back_lower;

    public LibModelMantis() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.root = new ModelRenderer(this, 35, 180);
        this.root.setRotationPoint(0.0F, 0.0F, 5.0F);
        this.root.addBox(-7.5F, -5.0F, 0.0F, 15, 10, 25, 0.0F);
        this.tail = new ModelRenderer(this, 50, 230);
        this.tail.setRotationPoint(0.0F, 0.0F, 23.2F);
        this.tail.addBox(-6.0F, -4.0F, 0.0F, 12, 8, 14, 0.0F);
        this.setRotateAngle(tail, -0.136659280431156F, 0.0F, 0.0F);
        this.leg_right_front = new ModelRenderer(this, 180, 180);
        this.leg_right_front.setRotationPoint(-5.5F, 0.0F, -10.0F);
        this.leg_right_front.addBox(-25.0F, -3.0F, -4.0F, 25, 6, 8, 0.0F);
        this.setRotateAngle(leg_right_front, 0.24434609527920614F, 0.045553093477052F, -0.5009094953223726F);
        this.leg_right_back = new ModelRenderer(this, 180, 180);
        this.leg_right_back.setRotationPoint(-5.5F, 0.0F, 20.0F);
        this.leg_right_back.addBox(-25.0F, -3.0F, -4.0F, 25, 6, 8, 0.0F);
        this.setRotateAngle(leg_right_back, -0.24434609527920614F, 0.7285004297824331F, -0.6373942428283291F);
        this.tooth_right_middle = new ModelRenderer(this, 187, 23);
        this.tooth_right_middle.setRotationPoint(-2.3F, -2.0F, -8.5F);
        this.tooth_right_middle.addBox(-1.0F, -1.0F, -4.0F, 1, 2, 3, 0.0F);
        this.arm_lower_left = new ModelRenderer(this, 160, 85);
        this.arm_lower_left.setRotationPoint(0.0F, 0.0F, -24.0F);
        this.arm_lower_left.addBox(-1.8F, -4.9F, -27.0F, 3, 7, 30, 0.0F);
        this.setRotateAngle(arm_lower_left, 1.1838568316277536F, 0.0F, 0.0F);
        this.body2 = new ModelRenderer(this, 30, 70);
        this.body2.setRotationPoint(0.0F, -2.0F, -25.0F);
        this.body2.addBox(-9.5F, -7.0F, -25.0F, 19, 14, 30, 0.0F);
        this.setRotateAngle(body2, -0.6373942428283291F, 0.0F, 0.0F);
        this.arm_left_claw = new ModelRenderer(this, 185, 130);
        this.arm_left_claw.setRotationPoint(0.0F, 0.0F, -25.0F);
        this.arm_left_claw.addBox(-1.5F, 0.0F, -2.0F, 3, 21, 4, 0.0F);
        this.setRotateAngle(arm_left_claw, 0.8196066167365371F, 0.0F, 0.0F);
        this.jaw = new ModelRenderer(this, 122, 13);
        this.jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.jaw.addBox(-7.5F, -1.0F, -13.0F, 13, 2, 13, 0.0F);
        this.setRotateAngle(jaw, 0.5009094953223726F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 50, 10);
        this.head.setRotationPoint(0.0F, 0.0F, -12.0F);
        this.head.addBox(-11.0F, -8.0F, -10.0F, 21, 8, 10, 0.0F);
        this.setRotateAngle(head, 0.9105382707654417F, 0.0F, 0.0F);
        this.tooth_right = new ModelRenderer(this, 187, 23);
        this.tooth_right.setRotationPoint(-5.8F, -2.0F, -8.5F);
        this.tooth_right.addBox(-1.0F, -1.0F, -4.0F, 1, 2, 3, 0.0F);
        this.arm_lower_right = new ModelRenderer(this, 160, 85);
        this.arm_lower_right.setRotationPoint(0.0F, 0.0F, -24.0F);
        this.arm_lower_right.addBox(-1.8F, -4.9F, -27.0F, 3, 7, 30, 0.0F);
        this.setRotateAngle(arm_lower_right, 1.1838568316277536F, 0.0F, 0.0F);
        this.arm_left = new ModelRenderer(this, 160, 41);
        this.arm_left.setRotationPoint(11.3F, 3.0F, -14.0F);
        this.arm_left.addBox(-3.0F, -6.0F, -27.0F, 5, 9, 27, 0.0F);
        this.setRotateAngle(arm_left, 0.40980330836826856F, -0.36425021489121656F, 0.0F);
        this.leg_left_front = new ModelRenderer(this, 180, 180);
        this.leg_left_front.setRotationPoint(5.6F, 0.0F, -10.0F);
        this.leg_left_front.addBox(0.0F, -3.0F, -4.0F, 25, 6, 8, 0.0F);
        this.setRotateAngle(leg_left_front, 0.24434609527920614F, 0.045553093477052F, 0.5009094953223726F);
        this.eye_right = new ModelRenderer(this, 0, 4);
        this.eye_right.setRotationPoint(-10.5F, -4.0F, -5.0F);
        this.eye_right.addBox(-6.0F, -6.0F, -7.0F, 6, 12, 14, 0.0F);
        this.eye_left = new ModelRenderer(this, 0, 4);
        this.eye_left.setRotationPoint(10.0F, -4.0F, -5.0F);
        this.eye_left.addBox(0.0F, -6.0F, -7.0F, 6, 12, 14, 0.0F);
        this.leg_right_back_lower = new ModelRenderer(this, 185, 215);
        this.leg_right_back_lower.setRotationPoint(-23.0F, 0.0F, 0.0F);
        this.leg_right_back_lower.addBox(-20.0F, -2.0F, -3.0F, 20, 4, 6, 0.0F);
        this.setRotateAngle(leg_right_back_lower, 0.0F, 0.0F, -0.4553564018453205F);
        this.arm_right = new ModelRenderer(this, 160, 41);
        this.arm_right.setRotationPoint(-10.0F, 3.0F, -14.0F);
        this.arm_right.addBox(-3.0F, -6.0F, -27.0F, 5, 9, 27, 0.0F);
        this.setRotateAngle(arm_right, 0.40980330836826856F, 0.36425021489121656F, 0.0F);
        this.leg_right_front_lower = new ModelRenderer(this, 185, 215);
        this.leg_right_front_lower.setRotationPoint(-23.0F, 0.0F, 0.0F);
        this.leg_right_front_lower.addBox(-20.0F, -2.0F, -3.0F, 20, 4, 6, 0.0F);
        this.setRotateAngle(leg_right_front_lower, 0.0F, 0.0F, -0.8196066167365371F);
        this.neck = new ModelRenderer(this, 50, 37);
        this.neck.setRotationPoint(0.0F, 0.0F, -22.0F);
        this.neck.addBox(-4.0F, -3.5F, -16.0F, 8, 5, 16, 0.0F);
        this.leg_left_back = new ModelRenderer(this, 180, 180);
        this.leg_left_back.setRotationPoint(5.5F, 0.0F, 20.0F);
        this.leg_left_back.addBox(0.0F, -3.0F, -4.0F, 25, 6, 8, 0.0F);
        this.setRotateAngle(leg_left_back, -0.24434609527920614F, -0.7285004297824331F, 0.6373942428283291F);
        this.leg_left_back_lower = new ModelRenderer(this, 185, 215);
        this.leg_left_back_lower.mirror = true;
        this.leg_left_back_lower.setRotationPoint(23.0F, 0.0F, 0.0F);
        this.leg_left_back_lower.addBox(0.0F, -2.0F, -3.0F, 20, 4, 6, 0.0F);
        this.setRotateAngle(leg_left_back_lower, 0.0F, 0.0F, 0.4553564018453205F);
        this.arm_right_claw = new ModelRenderer(this, 185, 130);
        this.arm_right_claw.setRotationPoint(0.0F, 0.0F, -25.0F);
        this.arm_right_claw.addBox(-1.5F, 0.0F, -2.0F, 3, 21, 4, 0.0F);
        this.setRotateAngle(arm_right_claw, 0.8196066167365371F, 0.0F, 0.0F);
        this.tooth_left = new ModelRenderer(this, 187, 23);
        this.tooth_left.setRotationPoint(4.8F, -2.0F, -8.5F);
        this.tooth_left.addBox(-1.0F, -1.0F, -4.0F, 1, 2, 3, 0.0F);
        this.leg_left_front_lower = new ModelRenderer(this, 185, 215);
        this.leg_left_front_lower.mirror = true;
        this.leg_left_front_lower.setRotationPoint(23.0F, 0.0F, 0.0F);
        this.leg_left_front_lower.addBox(0.0F, -2.0F, -3.0F, 20, 4, 6, 0.0F);
        this.setRotateAngle(leg_left_front_lower, 0.0F, 0.0F, 0.8196066167365371F);
        this.tooth_left_middle = new ModelRenderer(this, 187, 23);
        this.tooth_left_middle.setRotationPoint(1.7F, -2.0F, -8.5F);
        this.tooth_left_middle.addBox(-1.0F, -1.0F, -4.0F, 1, 2, 3, 0.0F);
        this.body1 = new ModelRenderer(this, 35, 130);
        this.body1.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.body1.addBox(-8.5F, -6.0F, -25.0F, 17, 12, 25, 0.0F);
        this.setRotateAngle(body1, -0.27314402793711257F, 0.0F, 0.0F);
        this.root.addChild(this.tail);
        this.body1.addChild(this.leg_right_front);
        this.root.addChild(this.leg_right_back);
        this.jaw.addChild(this.tooth_right_middle);
        this.arm_left.addChild(this.arm_lower_left);
        this.body1.addChild(this.body2);
        this.arm_lower_left.addChild(this.arm_left_claw);
        this.head.addChild(this.jaw);
        this.neck.addChild(this.head);
        this.jaw.addChild(this.tooth_right);
        this.arm_right.addChild(this.arm_lower_right);
        this.body2.addChild(this.arm_left);
        this.body1.addChild(this.leg_left_front);
        this.head.addChild(this.eye_right);
        this.head.addChild(this.eye_left);
        this.leg_right_back.addChild(this.leg_right_back_lower);
        this.body2.addChild(this.arm_right);
        this.leg_right_front.addChild(this.leg_right_front_lower);
        this.body2.addChild(this.neck);
        this.root.addChild(this.leg_left_back);
        this.leg_left_back.addChild(this.leg_left_back_lower);
        this.arm_lower_right.addChild(this.arm_right_claw);
        this.jaw.addChild(this.tooth_left);
        this.leg_left_front.addChild(this.leg_left_front_lower);
        this.jaw.addChild(this.tooth_left_middle);
        this.root.addChild(this.body1);

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

        animateLimbs(head, arm_right, arm_left, leg_right_front, leg_left_front, leg_right_back, leg_left_back,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        if (leg_left_front != null) {
            leg_left_front.rotateAngleX = 0;
            leg_left_front.rotateAngleZ += MathHelper.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount;
        }
        if (leg_left_back != null) {
            leg_left_back.rotateAngleX = 0;
            leg_left_back.rotateAngleZ += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.4F * limbSwingAmount;
        }

        if (leg_right_front != null) {
            leg_right_front.rotateAngleX = 0;
            leg_right_front.rotateAngleZ += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.4F * limbSwingAmount;
        }

        if (leg_right_back != null) {
            leg_right_back.rotateAngleX = 0;
            leg_right_back.rotateAngleZ += MathHelper.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount;
        }
    }

    @Override
    public ModelRenderer getModelForItem() {
        return arm_right_claw;
    }

    @Override
    public void translateToHand() {
        GlStateManager.translate(0.075, 0, 0);
        GlStateManager.translate(0.0, 0.5, 0);
        GlStateManager.translate(0.0, 0, 0.05);
        GlStateManager.rotate(-90, 0, 1, 0);
    }

    @Override
    public float getShadowScale() {
        return 2.0F;
    }
}
