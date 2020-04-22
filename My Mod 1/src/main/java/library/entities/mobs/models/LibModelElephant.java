package library.entities.mobs.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/**
 * mob_elephant - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelElephant extends LibModelBase {
    public ModelRenderer root;
    public ModelRenderer back_1;
    public ModelRenderer back_2;
    public ModelRenderer leg_right_front;
    public ModelRenderer leg_left_front;
    public ModelRenderer leg_right_back;
    public ModelRenderer leg_left_back;
    public ModelRenderer head;
    public ModelRenderer trunk_1;
    public ModelRenderer ear_right;
    public ModelRenderer ear_left;
    public ModelRenderer trunk_2;
    public ModelRenderer trunk_3;
    public ModelRenderer trunk_4;
    public ModelRenderer tail_1;
    public ModelRenderer tail_2;
    public ModelRenderer leg_right_front_lower;
    public ModelRenderer leg_left_front_lower;
    public ModelRenderer leg_right_back_lower;
    public ModelRenderer leg_left_back_lower;

    public LibModelElephant() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.leg_left_front = new ModelRenderer(this, 15, 0);
        this.leg_left_front.setRotationPoint(-7.0F, 12.0F, 10.0F);
        this.leg_left_front.addBox(-5.0F, 0.0F, -5.0F, 10, 22, 10, 0.0F);
        this.trunk_4 = new ModelRenderer(this, 190, 90);
        this.trunk_4.setRotationPoint(0.0F, 8.0F, 0.5F);
        this.trunk_4.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3, 0.0F);
        this.leg_right_back = new ModelRenderer(this, 15, 0);
        this.leg_right_back.setRotationPoint(9.0F, 12.0F, -28.0F);
        this.leg_right_back.addBox(-5.0F, 0.0F, -5.0F, 10, 22, 10, 0.0F);
        this.head = new ModelRenderer(this, 80, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.head.addBox(-12.5F, -13.0F, 0.0F, 25, 26, 20, 0.0F);
        this.setRotateAngle(head, -0.091106186954104F, 0.0F, 0.0F);
        this.leg_left_front_lower = new ModelRenderer(this, 20, 40);
        this.leg_left_front_lower.setRotationPoint(1.0F, 20.0F, 1.0F);
        this.leg_left_front_lower.addBox(-5.0F, 0.0F, -5.0F, 8, 18, 8, 0.0F);
        this.ear_left = new ModelRenderer(this, 190, 110);
        this.ear_left.setRotationPoint(-10.0F, 0.0F, 6.0F);
        this.ear_left.addBox(-16.0F, -13.0F, 0.0F, 16, 18, 3, 0.0F);
        this.setRotateAngle(ear_left, 0.0F, -0.45378560551852565F, 0.0F);
        this.trunk_3 = new ModelRenderer(this, 190, 60);
        this.trunk_3.setRotationPoint(0.0F, 11.0F, 0.5F);
        this.trunk_3.addBox(-2.0F, 0.0F, -2.0F, 4, 9, 4, 0.0F);
        this.ear_right = new ModelRenderer(this, 190, 110);
        this.ear_right.setRotationPoint(10.0F, 0.0F, 6.0F);
        this.ear_right.addBox(0.0F, -13.0F, 1.5F, 16, 18, 3, 0.0F);
        this.setRotateAngle(ear_right, 0.0F, 0.4553564018453205F, 0.0F);
        this.back_2 = new ModelRenderer(this, 83, 172);
        this.back_2.setRotationPoint(2.5F, 2.0F, -12.0F);
        this.back_2.addBox(-10.0F, -13.0F, -22.0F, 20, 26, 22, 0.0F);
        this.setRotateAngle(back_2, 0.045553093477052F, 0.0F, 0.0F);
        this.root = new ModelRenderer(this, 70, 105);
        this.root.setRotationPoint(0.0F, -27.0F, -8.0F);
        this.root.addBox(-10.0F, -15.0F, -15.0F, 25, 33, 30, 0.0F);
        this.setRotateAngle(root, 0.0F, 3.141592653589793F, 0.0F);
        this.trunk_1 = new ModelRenderer(this, 180, 0);
        this.trunk_1.setRotationPoint(0.0F, 0.0F, 16.0F);
        this.trunk_1.addBox(-4.0F, 0.0F, 0.0F, 8, 15, 8, 0.0F);
        this.trunk_2 = new ModelRenderer(this, 185, 30);
        this.trunk_2.setRotationPoint(0.0F, 13.0F, 4.5F);
        this.trunk_2.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F);
        this.leg_right_front = new ModelRenderer(this, 15, 0);
        this.leg_right_front.setRotationPoint(12.0F, 12.0F, 10.0F);
        this.leg_right_front.addBox(-5.0F, 0.0F, -5.0F, 10, 22, 10, 0.0F);
        this.leg_right_front_lower = new ModelRenderer(this, 20, 40);
        this.leg_right_front_lower.setRotationPoint(1.0F, 20.0F, 1.0F);
        this.leg_right_front_lower.addBox(-5.0F, 0.0F, -5.0F, 8, 18, 8, 0.0F);
        this.leg_right_back_lower = new ModelRenderer(this, 20, 40);
        this.leg_right_back_lower.setRotationPoint(1.0F, 20.0F, 1.0F);
        this.leg_right_back_lower.addBox(-5.0F, 0.0F, -5.0F, 8, 18, 8, 0.0F);
        this.tail_2 = new ModelRenderer(this, 115, 243);
        this.tail_2.setRotationPoint(0.0F, -1.0F, -10.0F);
        this.tail_2.addBox(-1.5F, -1.5F, -8.0F, 3, 3, 8, 0.0F);
        this.setRotateAngle(tail_2, 0.5009094953223726F, 0.0F, 0.0F);
        this.leg_left_back = new ModelRenderer(this, 15, 0);
        this.leg_left_back.setRotationPoint(-4.0F, 12.0F, -28.0F);
        this.leg_left_back.addBox(-5.0F, 0.0F, -5.0F, 10, 22, 10, 0.0F);
        this.back_1 = new ModelRenderer(this, 90, 55);
        this.back_1.setRotationPoint(2.5F, 2.0F, 13.0F);
        this.back_1.addBox(-10.0F, -13.0F, 0.0F, 20, 26, 14, 0.0F);
        this.setRotateAngle(back_1, -0.136659280431156F, 0.0F, 0.0F);
        this.tail_1 = new ModelRenderer(this, 110, 223);
        this.tail_1.setRotationPoint(0.0F, 2.0F, -20.0F);
        this.tail_1.addBox(-2.5F, -2.5F, -12.0F, 5, 5, 12, 0.0F);
        this.setRotateAngle(tail_1, 0.6829473363053812F, 0.0F, 0.0F);
        this.leg_left_back_lower = new ModelRenderer(this, 20, 40);
        this.leg_left_back_lower.setRotationPoint(1.0F, 20.0F, 1.0F);
        this.leg_left_back_lower.addBox(-5.0F, 0.0F, -5.0F, 8, 18, 8, 0.0F);
        this.root.addChild(this.leg_left_front);
        this.trunk_3.addChild(this.trunk_4);
        this.root.addChild(this.leg_right_back);
        this.back_1.addChild(this.head);
        this.leg_left_front.addChild(this.leg_left_front_lower);
        this.head.addChild(this.ear_left);
        this.trunk_2.addChild(this.trunk_3);
        this.head.addChild(this.ear_right);
        this.root.addChild(this.back_2);
        this.head.addChild(this.trunk_1);
        this.trunk_1.addChild(this.trunk_2);
        this.root.addChild(this.leg_right_front);
        this.leg_right_front.addChild(this.leg_right_front_lower);
        this.leg_right_back.addChild(this.leg_right_back_lower);
        this.tail_1.addChild(this.tail_2);
        this.root.addChild(this.leg_left_back);
        this.root.addChild(this.back_1);
        this.back_2.addChild(this.tail_1);
        this.leg_left_back.addChild(this.leg_left_back_lower);

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

        animateLimbs(head, null, null, leg_right_front, leg_left_front, leg_right_back, leg_left_back,
                limbSwing, limbSwingAmount * 0.5F, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        limbSwingAmount /= this.extraScaleForRender;
        ageInTicks /= this.extraScaleForRender;

        staggerWaggle(ageInTicks, new Vec3d(0.05, 0, 0), MathHelper::sin, 0.067F, 5, 1F,
                trunk_1, trunk_2, trunk_3, trunk_4);

        staggerWaggle(ageInTicks, new Vec3d(0, 0, 0.05), MathHelper::cos, 0.09F, 5, 1F,
                trunk_1, trunk_2, trunk_3, trunk_4);

        int stagger = 5;
        float range = 0.05F;

        ear_left.rotateAngleY += MathHelper.cos((ageInTicks + 0) * 0.09F) * range - 0.15F;
        ear_right.rotateAngleY += -MathHelper.cos((ageInTicks + 0) * 0.09F) * range + 0.15F;

        tail_1.rotateAngleZ += MathHelper.cos((ageInTicks + 0) * 0.09F) * range * 5F;
        tail_1.rotateAngleX += MathHelper.sin((ageInTicks + 0) * 0.067F) * range * 1F;

        tail_2.rotateAngleZ += MathHelper.cos((ageInTicks - stagger) * 0.09F) * range * 5F;
        tail_2.rotateAngleX += MathHelper.sin((ageInTicks - stagger) * 0.067F) * range * 1F;
    }

    @Override
    public ModelRenderer getModelForItem() {
        return trunk_4;
    }

    @Override
    public void translateToHand() {
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.rotate(-30, 1, 0, 0);
        GlStateManager.translate(0.075, 0, 0);
    }

    @Override
    public float getShadowScale() {
        return 2.0F;
    }
}
