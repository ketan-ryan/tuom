package library.entities.mobs.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * ModelWither - Either Mojang or a mod author
 * Created using Tabula 6.0.0
 */
public class LibModelMutantWither extends LibModelBase {
    public ModelRenderer chest_root;
    public ModelRenderer arm_left;
    public ModelRenderer arm_right;
    public ModelRenderer head_right;
    public ModelRenderer head_left;
    public ModelRenderer spine1;
    public ModelRenderer head_main;
    public ModelRenderer arm_lower_left;
    public ModelRenderer arm_hand_left;
    public ModelRenderer thumb_left;
    public ModelRenderer finger_left;
    public ModelRenderer finger2_left;
    public ModelRenderer arm_lower_right;
    public ModelRenderer arm_hand_right;
    public ModelRenderer humb_right;
    public ModelRenderer finger_right;
    public ModelRenderer finger2_right;
    public ModelRenderer spine2;
    public ModelRenderer spine1_bone;
    public ModelRenderer spine2_bone;
    public ModelRenderer spine3_bone;
    public ModelRenderer mowhawk;
    public ModelRenderer jaw;
    public ModelRenderer tooth_right;
    public ModelRenderer tooth_left;
    public ModelRenderer tooth_left_middle;
    public ModelRenderer tooth_right_middle;

    public LibModelMutantWither() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.arm_lower_right = new ModelRenderer(this, 15, 81);
        this.arm_lower_right.setRotationPoint(0.0F, 0.1F, -14.0F);
        this.arm_lower_right.addBox(-0.5F, -0.5F, -10.0F, 1, 1, 10, 0.0F);
        this.setRotateAngle(arm_lower_right, -0.7740535232594852F, -0.31869712141416456F, 0.0F);
        this.arm_hand_right = new ModelRenderer(this, 18, 100);
        this.arm_hand_right.setRotationPoint(0.0F, 0.0F, -10.0F);
        this.arm_hand_right.addBox(-2.0F, -0.5F, -4.0F, 4, 1, 4, 0.0F);
        this.setRotateAngle(arm_hand_right, -0.091106186954104F, -0.045553093477052F, 2.777342438698576F);
        this.tooth_left_middle = new ModelRenderer(this, 42, 45);
        this.tooth_left_middle.setRotationPoint(-1.1F, -0.1F, -5.3F);
        this.tooth_left_middle.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.humb_right = new ModelRenderer(this, 37, 108);
        this.humb_right.setRotationPoint(-2.0F, -0.5F, -1.0F);
        this.humb_right.addBox(-4.0F, 0.0F, -1.0F, 4, 1, 2, 0.0F);
        this.setRotateAngle(humb_right, 0.0F, 0.0F, 0.8196066167365371F);
        this.finger2_left = new ModelRenderer(this, 24, 109);
        this.finger2_left.setRotationPoint(-1.0F, 0.0F, -4.0F);
        this.finger2_left.addBox(-1.0F, -0.5F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(finger2_left, 0.5462880558742251F, 0.0F, 0.0F);
        this.finger_right = new ModelRenderer(this, 12, 109);
        this.finger_right.setRotationPoint(1.0F, 0.0F, -4.0F);
        this.finger_right.addBox(-0.5F, -0.5F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(finger_right, -0.5462880558742251F, 0.0F, 0.0F);
        this.jaw = new ModelRenderer(this, 52, 39);
        this.jaw.setRotationPoint(0.0F, -0.5F, -0.1F);
        this.jaw.addBox(-4.0F, 0.0F, -6.0F, 8, 3, 6, 0.0F);
        this.setRotateAngle(jaw, 0.5462880558742251F, 0.0F, 0.0F);
        this.finger2_right = new ModelRenderer(this, 24, 109);
        this.finger2_right.setRotationPoint(-1.0F, 0.0F, -4.0F);
        this.finger2_right.addBox(-1.0F, -0.5F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(finger2_right, -0.5462880558742251F, 0.0F, 0.0F);
        this.arm_right = new ModelRenderer(this, 10, 61);
        this.arm_right.setRotationPoint(-16.0F, 0.0F, 0.0F);
        this.arm_right.addBox(-1.0F, -1.0F, -14.0F, 2, 2, 14, 0.0F);
        this.setRotateAngle(arm_right, 0.6829473363053812F, 0.0F, 0.0F);
        this.tooth_right = new ModelRenderer(this, 42, 45);
        this.tooth_right.setRotationPoint(-3.1F, -0.1F, -5.3F);
        this.tooth_right.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.head_right = new ModelRenderer(this, 9, 23);
        this.head_right.setRotationPoint(-8.0F, -3.0F, -2.0F);
        this.head_right.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6, 0.0F);
        this.spine1_bone = new ModelRenderer(this, 86, 72);
        this.spine1_bone.setRotationPoint(0.0F, 8.5F, 1.2F);
        this.spine1_bone.addBox(-5.5F, -1.5F, -1.5F, 11, 3, 3, 0.0F);
        this.setRotateAngle(spine1_bone, 0.2617993877991494F, 0.0F, 0.0F);
        this.thumb_left = new ModelRenderer(this, 38, 108);
        this.thumb_left.mirror = true;
        this.thumb_left.setRotationPoint(-2.0F, -0.5F, -1.0F);
        this.thumb_left.addBox(-4.0F, 0.0F, -1.0F, 4, 1, 2, 0.0F);
        this.setRotateAngle(thumb_left, 0.0F, 0.0F, -0.8196066167365371F);
        this.head_main = new ModelRenderer(this, 50, 20);
        this.head_main.setRotationPoint(0.0F, -3.0F, -4.0F);
        this.head_main.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.head_left = new ModelRenderer(this, 90, 23);
        this.head_left.setRotationPoint(8.0F, -3.0F, -2.0F);
        this.head_left.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6, 0.0F);
        this.tooth_right_middle = new ModelRenderer(this, 42, 45);
        this.tooth_right_middle.setRotationPoint(1.0F, -0.1F, -5.3F);
        this.tooth_right_middle.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.chest_root = new ModelRenderer(this, 31, 54);
        this.chest_root.setRotationPoint(0.0F, 3.2F, 2.0F);
        this.chest_root.addBox(-15.0F, -3.0F, -3.0F, 30, 6, 6, 0.0F);
        this.setRotateAngle(chest_root, 0.33161255787892263F, 0.0F, 0.0F);
        this.arm_lower_left = new ModelRenderer(this, 15, 81);
        this.arm_lower_left.setRotationPoint(0.0F, 0.1F, -14.0F);
        this.arm_lower_left.addBox(-0.5F, -0.5F, -10.0F, 1, 1, 10, 0.0F);
        this.setRotateAngle(arm_lower_left, -0.7740535232594852F, 0.31869712141416456F, 0.0F);
        this.spine3_bone = new ModelRenderer(this, 92, 96);
        this.spine3_bone.setRotationPoint(0.0F, 10.1F, 0.5F);
        this.spine3_bone.addBox(-3.5F, -1.0F, -1.0F, 7, 2, 2, 0.0F);
        this.setRotateAngle(spine3_bone, 0.7853981633974483F, 0.0F, 0.0F);
        this.spine1 = new ModelRenderer(this, 62, 72);
        this.spine1.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.spine1.addBox(-1.5F, 0.0F, -1.5F, 3, 10, 3, 0.0F);
        this.arm_hand_left = new ModelRenderer(this, 18, 100);
        this.arm_hand_left.setRotationPoint(0.0F, 0.0F, -10.0F);
        this.arm_hand_left.addBox(-2.0F, -0.5F, -4.0F, 4, 1, 4, 0.0F);
        this.setRotateAngle(arm_hand_left, 0.31869712141416456F, 0.0F, 0.22759093446006054F);
        this.tooth_left = new ModelRenderer(this, 42, 45);
        this.tooth_left.setRotationPoint(3.2F, -0.1F, -5.3F);
        this.tooth_left.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.arm_left = new ModelRenderer(this, 10, 61);
        this.arm_left.setRotationPoint(16.0F, 0.0F, 0.0F);
        this.arm_left.addBox(-1.0F, -1.0F, -14.0F, 2, 2, 14, 0.0F);
        this.setRotateAngle(arm_left, 0.6829473363053812F, 0.0F, 0.0F);
        this.finger_left = new ModelRenderer(this, 12, 109);
        this.finger_left.setRotationPoint(1.0F, 0.0F, -4.0F);
        this.finger_left.addBox(-0.5F, -0.5F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(finger_left, 0.5462880558742251F, 0.0F, 0.0F);
        this.spine2 = new ModelRenderer(this, 64, 93);
        this.spine2.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.spine2.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
        this.setRotateAngle(spine2, 0.9105382707654417F, 0.0F, 0.0F);
        this.mowhawk = new ModelRenderer(this, 57, 5);
        this.mowhawk.setRotationPoint(0.0F, -9.3F, 0.0F);
        this.mowhawk.addBox(-0.5F, -1.5F, -4.0F, 1, 3, 8, 0.0F);
        this.spine2_bone = new ModelRenderer(this, 90, 82);
        this.spine2_bone.setRotationPoint(0.0F, 5.5F, 0.5F);
        this.spine2_bone.addBox(-4.5F, -1.0F, -1.0F, 9, 2, 2, 0.0F);
        this.setRotateAngle(spine2_bone, 0.7853981633974483F, 0.0F, 0.0F);
        this.arm_right.addChild(this.arm_lower_right);
        this.arm_lower_right.addChild(this.arm_hand_right);
        this.jaw.addChild(this.tooth_left_middle);
        this.arm_hand_right.addChild(this.humb_right);
        this.arm_hand_left.addChild(this.finger2_left);
        this.arm_hand_right.addChild(this.finger_right);
        this.head_main.addChild(this.jaw);
        this.arm_hand_right.addChild(this.finger2_right);
        this.chest_root.addChild(this.arm_right);
        this.jaw.addChild(this.tooth_right);
        this.chest_root.addChild(this.head_right);
        this.spine1.addChild(this.spine1_bone);
        this.arm_hand_left.addChild(this.thumb_left);
        this.chest_root.addChild(this.head_main);
        this.chest_root.addChild(this.head_left);
        this.jaw.addChild(this.tooth_right_middle);
        this.arm_left.addChild(this.arm_lower_left);
        this.spine2.addChild(this.spine3_bone);
        this.chest_root.addChild(this.spine1);
        this.arm_lower_left.addChild(this.arm_hand_left);
        this.jaw.addChild(this.tooth_left);
        this.chest_root.addChild(this.arm_left);
        this.arm_hand_left.addChild(this.finger_left);
        this.spine1.addChild(this.spine2);
        this.head_main.addChild(this.mowhawk);
        this.spine2.addChild(this.spine2_bone);

        mapModelRotations(chest_root);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.chest_root.render(f5);
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

        animateLimbs(head_main, arm_right, arm_left, null, null, null, null,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        animateLimbs(null, jaw, null, null, null, null, null,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

    @Override
    public ModelRenderer getModelForItem() {
        return arm_hand_right;
    }

    @Override
    public void translateToHand() {
        GlStateManager.translate(-0.2, -0.2, -0.7);
        GlStateManager.rotate(90, 0, 1, 0);
        GlStateManager.rotate(90, 0, 0, 1);
        GlStateManager.translate(0.2, -0.0, 0.2);
    }

    @Override
    public float getShadowScale() {
        return 1.0F;
    }
}
