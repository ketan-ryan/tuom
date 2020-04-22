package library.entities.mobs.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * mob_boss - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelTitan extends LibModelBase {
    public ModelRenderer abdomin_root;
    public ModelRenderer waist;
    public ModelRenderer leg_left_upper;
    public ModelRenderer leg_right_upper;
    public ModelRenderer chest;
    public ModelRenderer head;
    public ModelRenderer shoulder_right;
    public ModelRenderer shoulder_left;
    public ModelRenderer arm_left;
    public ModelRenderer arm_right;
    public ModelRenderer helmet;
    public ModelRenderer leg_left_lower;
    public ModelRenderer leg_right_lower;

    public LibModelTitan() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.shoulder_left = new ModelRenderer(this, 7, 50);
        this.shoulder_left.mirror = true;
        this.shoulder_left.setRotationPoint(4.1F, -11.0F, 11.0F);
        this.shoulder_left.addBox(12.0F, -7.0F, -7.0F, 12, 14, 14, 0.0F);
        this.setRotateAngle(shoulder_left, -1.1838568316277536F, 0.0F, 0.0F);
        this.waist = new ModelRenderer(this, 90, 93);
        this.waist.setRotationPoint(2.5F, -7.0F, -3.0F);
        this.waist.addBox(-10.0F, -7.5F, -7.5F, 20, 15, 15, 0.0F);
        this.leg_left_lower = new ModelRenderer(this, 80, 220);
        this.leg_left_lower.setRotationPoint(0.0F, 17.0F, 0.0F);
        this.leg_left_lower.addBox(-4.5F, 0.0F, -4.5F, 9, 24, 10, 0.0F);
        this.arm_right = new ModelRenderer(this, 17, 90);
        this.arm_right.mirror = true;
        this.arm_right.setRotationPoint(-22.0F, -14.0F, 18.8F);
        this.arm_right.addBox(-4.0F, 0.0F, -4.0F, 8, 43, 8, 0.0F);
        this.setRotateAngle(arm_right, -1.1838568316277536F, 0.0F, 0.0F);
        this.leg_right_lower = new ModelRenderer(this, 80, 220);
        this.leg_right_lower.mirror = true;
        this.leg_right_lower.setRotationPoint(0.0F, 17.0F, 0.0F);
        this.leg_right_lower.addBox(-4.5F, 0.0F, -4.5F, 9, 24, 10, 0.0F);
        this.chest = new ModelRenderer(this, 70, 48);
        this.chest.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.chest.addBox(-17.0F, -16.7F, 0.0F, 33, 18, 18, 0.0F);
        this.setRotateAngle(chest, 0.7740535232594852F, 0.0F, 0.0F);
        this.leg_left_upper = new ModelRenderer(this, 70, 180);
        this.leg_left_upper.setRotationPoint(8.5F, 5.0F, -3.0F);
        this.leg_left_upper.addBox(-5.0F, 0.0F, -5.0F, 10, 17, 11, 0.0F);
        this.leg_right_upper = new ModelRenderer(this, 70, 180);
        this.leg_right_upper.mirror = true;
        this.leg_right_upper.setRotationPoint(-3.5F, 5.0F, -3.0F);
        this.leg_right_upper.addBox(-5.0F, 0.0F, -5.0F, 10, 17, 11, 0.0F);
        this.head = new ModelRenderer(this, 95, 9);
        this.head.setRotationPoint(0.0F, -17.0F, 13.0F);
        this.head.addBox(-7.5F, -15.0F, -7.5F, 15, 15, 15, 0.0F);
        this.setRotateAngle(head, -0.36425021489121656F, 0.0F, 0.0F);
        this.shoulder_right = new ModelRenderer(this, 7, 50);
        this.shoulder_right.setRotationPoint(-17.0F, -11.0F, 11.0F);
        this.shoulder_right.addBox(-12.0F, -7.0F, -7.0F, 12, 14, 14, 0.0F);
        this.setRotateAngle(shoulder_right, -1.1838568316277536F, 0.0F, 0.0F);
        this.arm_left = new ModelRenderer(this, 17, 90);
        this.arm_left.mirror = true;
        this.arm_left.setRotationPoint(22.0F, -14.0F, 18.8F);
        this.arm_left.addBox(-4.0F, 0.0F, -4.0F, 8, 43, 8, 0.0F);
        this.setRotateAngle(arm_left, -1.1838568316277536F, 0.0F, 0.0F);
        this.helmet = new ModelRenderer(this, 170, 4);
        this.helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmet.addBox(-8.5F, -16.0F, -8.5F, 17, 17, 17, 0.0F);
        this.abdomin_root = new ModelRenderer(this, 60, 132);
        this.abdomin_root.setRotationPoint(-2.6F, -21.999999999999996F, 2.399999999999997F);
        this.abdomin_root.addBox(-10.0F, -7.5F, -7.5F, 25, 15, 10, 0.0F);
        this.setRotateAngle(abdomin_root, 0.0F, 6.283185307179586F, 0.0F);
        this.chest.addChild(this.shoulder_left);
        this.abdomin_root.addChild(this.waist);
        this.leg_left_upper.addChild(this.leg_left_lower);
        this.chest.addChild(this.arm_right);
        this.leg_right_upper.addChild(this.leg_right_lower);
        this.waist.addChild(this.chest);
        this.abdomin_root.addChild(this.leg_left_upper);
        this.abdomin_root.addChild(this.leg_right_upper);
        this.chest.addChild(this.head);
        this.chest.addChild(this.shoulder_right);
        this.chest.addChild(this.arm_left);
        this.head.addChild(this.helmet);

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

        resetRotations();

        limbSwingAmount /= this.extraScaleForRender;
        ageInTicks /= this.extraScaleForRender;


        limbSwingAmount /= 2F;
        limbSwing /= 2F;
        ageInTicks /= 2F;

        if (this.isChild) {
            limbSwingAmount /= 2F;
            limbSwing /= 2F;
            ageInTicks /= 2F;
        }

        animateLimbs(this.head, this.arm_right, this.arm_left, this.leg_right_upper, this.leg_left_upper, null, null,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

    @Override
    public ModelRenderer getModelForItem() {
        return this.arm_right;
    }

    @Override
    public void translateToHand() {
        GlStateManager.translate(0.075, 2, 0);
    }

    @Override
    public float getShadowScale() {
        return 1.4F;
    }
}
