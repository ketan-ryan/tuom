package library.entities.mobs.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * mob_centaur - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelCentaur extends LibModelBase {
    public ModelRenderer abdomin_root;
    public ModelRenderer waist;
    public ModelRenderer leg_right_front_upper;
    public ModelRenderer tail1;
    public ModelRenderer leg_left_front_upper;
    public ModelRenderer leg_left_back_upper;
    public ModelRenderer leg_right_back_upper;
    public ModelRenderer chest;
    public ModelRenderer head;
    public ModelRenderer shoulder_right;
    public ModelRenderer arm_right;
    public ModelRenderer shoulder_left;
    public ModelRenderer arm_left;
    public ModelRenderer jaw;
    public ModelRenderer leg_right_front_lower;
    public ModelRenderer tail2;
    public ModelRenderer leg_left_front_lower;
    public ModelRenderer leg_left_back_lower;
    public ModelRenderer leg_right_back_lower;

    public LibModelCentaur() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.leg_right_front_upper = new ModelRenderer(this, 30, 85);
        this.leg_right_front_upper.setRotationPoint(-4.5F, 3.0F, -7.0F);
        this.leg_right_front_upper.addBox(-2.5F, 0.0F, -3.0F, 5, 7, 6, 0.0F);
        this.leg_left_back_lower = new ModelRenderer(this, 17, 111);
        this.leg_left_back_lower.mirror = true;
        this.leg_left_back_lower.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.leg_left_back_lower.addBox(-2.0F, 0.0F, -2.0F, 4, 7, 4, 0.0F);
        this.leg_right_back_lower = new ModelRenderer(this, 17, 111);
        this.leg_right_back_lower.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.leg_right_back_lower.addBox(-2.0F, 0.0F, -2.0F, 4, 7, 4, 0.0F);
        this.head = new ModelRenderer(this, 5, 10);
        this.head.setRotationPoint(0.0F, -9.0F, 8.0F);
        this.head.addBox(-3.5F, -7.0F, -3.5F, 7, 7, 7, 0.0F);
        this.setRotateAngle(head, -0.36425021489121656F, 0.0F, 0.0F);
        this.shoulder_right = new ModelRenderer(this, 95, 24);
        this.shoulder_right.setRotationPoint(-11.5F, -5.0F, 2.1F);
        this.shoulder_right.addBox(-3.0F, -7.0F, -3.5F, 6, 7, 7, 0.0F);
        this.setRotateAngle(shoulder_right, -1.1838568316277536F, 0.0F, 0.0F);
        this.jaw = new ModelRenderer(this, 5, 28);
        this.jaw.setRotationPoint(0.0F, -0.9F, -1.0F);
        this.jaw.addBox(-5.0F, -1.5F, -3.5F, 10, 3, 5, 0.0F);
        this.setRotateAngle(jaw, 0.045553093477052F, 0.0F, 0.0F);
        this.arm_left = new ModelRenderer(this, 99, 42);
        this.arm_left.mirror = true;
        this.arm_left.setRotationPoint(11.5F, -5.0F, 2.1F);
        this.arm_left.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(arm_left, -1.1838568316277536F, 0.0F, 0.0F);
        this.leg_right_front_lower = new ModelRenderer(this, 17, 111);
        this.leg_right_front_lower.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.leg_right_front_lower.addBox(-2.0F, 0.0F, -2.0F, 4, 7, 4, 0.0F);
        this.leg_left_back_upper = new ModelRenderer(this, 30, 85);
        this.leg_left_back_upper.mirror = true;
        this.leg_left_back_upper.setRotationPoint(4.5F, 3.0F, 6.5F);
        this.leg_left_back_upper.addBox(-2.5F, 0.0F, -3.0F, 5, 7, 6, 0.0F);
        this.shoulder_left = new ModelRenderer(this, 95, 24);
        this.shoulder_left.mirror = true;
        this.shoulder_left.setRotationPoint(11.5F, -5.0F, 2.1F);
        this.shoulder_left.addBox(-3.0F, -7.0F, -3.5F, 6, 7, 7, 0.0F);
        this.setRotateAngle(shoulder_left, -1.1838568316277536F, 0.0F, 0.0F);
        this.abdomin_root = new ModelRenderer(this, 31, 50);
        this.abdomin_root.setRotationPoint(-0.2F, 6.81F, 0.2F);
        this.abdomin_root.addBox(-6.0F, -3.5F, -11.0F, 12, 7, 21, 0.0F);
        this.setRotateAngle(abdomin_root, 0.0F, 6.283185307179586F, 0.0F);
        this.tail1 = new ModelRenderer(this, 90, 90);
        this.tail1.setRotationPoint(0.0F, -1.8F, 9.5F);
        this.tail1.addBox(-2.0F, -1.0F, 0.0F, 4, 2, 5, 0.0F);
        this.setRotateAngle(tail1, -0.18203784098300857F, 0.0F, 0.0F);
        this.chest = new ModelRenderer(this, 38, 7);
        this.chest.setRotationPoint(0.0F, 1.0F, -1.0F);
        this.chest.addBox(-8.5F, -9.0F, 0.0F, 17, 9, 9, 0.0F);
        this.setRotateAngle(chest, 0.7853981633974483F, 0.0F, 0.0F);
        this.arm_right = new ModelRenderer(this, 99, 42);
        this.arm_right.setRotationPoint(-11.5F, -5.0F, 2.1F);
        this.arm_right.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(arm_right, -1.1838568316277536F, 0.0F, 0.0F);
        this.leg_left_front_lower = new ModelRenderer(this, 17, 111);
        this.leg_left_front_lower.mirror = true;
        this.leg_left_front_lower.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.leg_left_front_lower.addBox(-2.0F, 0.0F, -2.0F, 4, 7, 4, 0.0F);
        this.tail2 = new ModelRenderer(this, 92, 105);
        this.tail2.setRotationPoint(0.1F, -0.2F, 4.6F);
        this.tail2.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 4, 0.0F);
        this.setRotateAngle(tail2, -0.5009094953223726F, 0.0F, 0.0F);
        this.waist = new ModelRenderer(this, 47, 30);
        this.waist.setRotationPoint(0.0F, -4.5F, -8.8F);
        this.waist.addBox(-5.0F, -3.5F, -3.5F, 10, 7, 7, 0.0F);
        this.leg_left_front_upper = new ModelRenderer(this, 30, 85);
        this.leg_left_front_upper.mirror = true;
        this.leg_left_front_upper.setRotationPoint(4.5F, 3.0F, -7.0F);
        this.leg_left_front_upper.addBox(-2.5F, 0.0F, -3.0F, 5, 7, 6, 0.0F);
        this.leg_right_back_upper = new ModelRenderer(this, 30, 85);
        this.leg_right_back_upper.setRotationPoint(-4.5F, 3.0F, 6.5F);
        this.leg_right_back_upper.addBox(-2.5F, 0.0F, -3.0F, 5, 7, 6, 0.0F);
        this.abdomin_root.addChild(this.leg_right_front_upper);
        this.leg_left_back_upper.addChild(this.leg_left_back_lower);
        this.leg_right_back_upper.addChild(this.leg_right_back_lower);
        this.chest.addChild(this.head);
        this.chest.addChild(this.shoulder_right);
        this.head.addChild(this.jaw);
        this.chest.addChild(this.arm_left);
        this.leg_right_front_upper.addChild(this.leg_right_front_lower);
        this.abdomin_root.addChild(this.leg_left_back_upper);
        this.chest.addChild(this.shoulder_left);
        this.abdomin_root.addChild(this.tail1);
        this.waist.addChild(this.chest);
        this.chest.addChild(this.arm_right);
        this.leg_left_front_upper.addChild(this.leg_left_front_lower);
        this.tail1.addChild(this.tail2);
        this.abdomin_root.addChild(this.waist);
        this.abdomin_root.addChild(this.leg_left_front_upper);
        this.abdomin_root.addChild(this.leg_right_back_upper);

        mapModelRotations(abdomin_root);
    }


    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.abdomin_root.render(f5);
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

        animateLimbs(head, arm_right, arm_left, leg_right_front_upper, leg_left_front_upper, leg_right_back_upper, leg_left_back_upper,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);


    }

    @Override
    public ModelRenderer getModelForItem() {
        return this.arm_right;
    }

    @Override
    public void translateToHand() {
        GlStateManager.translate(0.075, 0, 0);
    }

    @Override
    public float getShadowScale() {
        return 1.1F;
    }
}
