package library.entities.mobs.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * mob_boss - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelMutantEnderman extends LibModelBase {
    public ModelRenderer torso_root;
    public ModelRenderer leg_upper_left;
    public ModelRenderer chest;
    public ModelRenderer leg_upper_right;
    public ModelRenderer leg_lower_left;
    public ModelRenderer head;
    public ModelRenderer arm_upper_left;
    public ModelRenderer arm_upper_right;
    public ModelRenderer jaw;
    public ModelRenderer jaw_bolt_right;
    public ModelRenderer jaw_bolt_left;
    public ModelRenderer arm_lower_left;
    public ModelRenderer arm_lower_right;
    public ModelRenderer leg_lower_right;

    public LibModelMutantEnderman() {
        this.textureWidth = 127;
        this.textureHeight = 128;
        this.jaw = new ModelRenderer(this, 58, 5);
        this.jaw.setRotationPoint(-1.0F, -2.0F, 3.0F);
        this.jaw.addBox(-5.0F, 0.0F, -10.0F, 11, 2, 10, 0.0F);
        this.setRotateAngle(jaw, 0.22759093446006054F, 0.0F, 0.0F);
        this.arm_lower_left = new ModelRenderer(this, 102, 67);
        this.arm_lower_left.mirror = true;
        this.arm_lower_left.setRotationPoint(-0.0F, 27.0F, 0.0F);
        this.arm_lower_left.addBox(-1.5F, 0.0F, -1.5F, 3, 27, 3, 0.0F);
        this.leg_lower_left = new ModelRenderer(this, 52, 87);
        this.leg_lower_left.mirror = true;
        this.leg_lower_left.setRotationPoint(0.0F, 36.0F, 0.0F);
        this.leg_lower_left.addBox(-2.0F, 0.0F, -2.0F, 4, 36, 4, 0.0F);
        this.arm_lower_right = new ModelRenderer(this, 102, 67);
        this.arm_lower_right.setRotationPoint(-0.0F, 27.0F, 0.0F);
        this.arm_lower_right.addBox(-1.5F, 0.0F, -1.5F, 3, 27, 3, 0.0F);
        this.arm_upper_left = new ModelRenderer(this, 100, 31);
        this.arm_upper_left.mirror = true;
        this.arm_upper_left.setRotationPoint(12.0F, -1.0F, 0.0F);
        this.arm_upper_left.addBox(-2.0F, 0.0F, -2.0F, 4, 27, 4, 0.0F);
        this.jaw_bolt_right = new ModelRenderer(this, 107, 14);
        this.jaw_bolt_right.setRotationPoint(-5.0F, -1.0F, -1.0F);
        this.jaw_bolt_right.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(jaw_bolt_right, -0.045553093477052F, 0.0F, 0.0F);
        this.leg_lower_right = new ModelRenderer(this, 52, 87);
        this.leg_lower_right.setRotationPoint(0.0F, 36.0F, 0.0F);
        this.leg_lower_right.addBox(-2.0F, 0.0F, -2.0F, 4, 36, 4, 0.0F);
        this.leg_upper_left = new ModelRenderer(this, 52, 45);
        this.leg_upper_left.mirror = true;
        this.leg_upper_left.setRotationPoint(3.5F, -2.0F, 2.0F);
        this.leg_upper_left.addBox(-2.0F, 0.0F, -2.0F, 4, 36, 4, 0.0F);
        this.head = new ModelRenderer(this, 15, 0);
        this.head.setRotationPoint(0.0F, -11.0F, -4.0F);
        this.head.addBox(-5.0F, -10.0F, -5.0F, 9, 9, 9, 0.0F);
        this.setRotateAngle(head, 0.6829473363053812F, 0.0F, 0.0F);
        this.chest = new ModelRenderer(this, 5, 22);
        this.chest.setRotationPoint(0.0F, -24.0F, 0.0F);
        this.chest.addBox(-15.0F, -11.0F, -5.0F, 30, 10, 10, 0.0F);
        this.leg_upper_right = new ModelRenderer(this, 52, 45);
        this.leg_upper_right.setRotationPoint(-3.5F, -2.0F, 2.0F);
        this.leg_upper_right.addBox(-2.0F, 0.0F, -2.0F, 4, 36, 4, 0.0F);
        this.torso_root = new ModelRenderer(this, 10, 49);
        this.torso_root.setRotationPoint(0.0F, -46.6F, -2.0F);
        this.torso_root.addBox(-5.4F, -23.0F, -1.0F, 11, 22, 5, 2.0F);
        this.setRotateAngle(torso_root, 0.0F, 6.286675965683574F, 0.0F);
        this.arm_upper_right = new ModelRenderer(this, 100, 31);
        this.arm_upper_right.setRotationPoint(-12.0F, -1.0F, 0.0F);
        this.arm_upper_right.addBox(-2.0F, 0.0F, -2.0F, 4, 27, 4, 0.0F);
        this.jaw_bolt_left = new ModelRenderer(this, 108, 14);
        this.jaw_bolt_left.mirror = true;
        this.jaw_bolt_left.setRotationPoint(5.0F, -1.0F, -1.0F);
        this.jaw_bolt_left.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(jaw_bolt_left, -0.045553093477052F, 0.0F, 0.0F);
        this.head.addChild(this.jaw);
        this.arm_upper_left.addChild(this.arm_lower_left);
        this.leg_upper_left.addChild(this.leg_lower_left);
        this.arm_upper_right.addChild(this.arm_lower_right);
        this.chest.addChild(this.arm_upper_left);
        this.jaw.addChild(this.jaw_bolt_right);
        this.leg_upper_right.addChild(this.leg_lower_right);
        this.torso_root.addChild(this.leg_upper_left);
        this.chest.addChild(this.head);
        this.torso_root.addChild(this.chest);
        this.torso_root.addChild(this.leg_upper_right);
        this.chest.addChild(this.arm_upper_right);
        this.jaw.addChild(this.jaw_bolt_left);

        mapModelRotations(torso_root);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.torso_root.render(f5);
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

        animateLimbs(head, arm_upper_right, arm_upper_left, leg_upper_right, leg_upper_left, null, null,
                limbSwing, limbSwingAmount / 2F, ageInTicks / 2F, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

    @Override
    public ModelRenderer getModelForItem() {
        return arm_lower_right;
    }

    @Override
    public void translateToHand() {
        GlStateManager.translate(0.075, 1, 0);
    }

    @Override
    public float getShadowScale() {
        return 1.0F;
    }
}
