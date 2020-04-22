package library.entities.mobs.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * mob_turtle - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelTurtle extends LibModelBase {
    public ModelRenderer root;
    public ModelRenderer shell1;
    public ModelRenderer head;
    public ModelRenderer leg_right_front;
    public ModelRenderer leg_right_back;
    public ModelRenderer leg_back_left;
    public ModelRenderer left_leg_front;
    public ModelRenderer tail;
    public ModelRenderer shell2;
    public ModelRenderer shell3;
    public ModelRenderer nose;
    public ModelRenderer jaw;
    public ModelRenderer toe1_right_front;
    public ModelRenderer toe2_right_front;
    public ModelRenderer toe1_right_back;
    public ModelRenderer toe2_right_back;
    public ModelRenderer toe2_left_back;
    public ModelRenderer toe1_left_back;
    public ModelRenderer toe2_left_front;
    public ModelRenderer toe1_left_front;

    public LibModelTurtle() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.tail = new ModelRenderer(this, 108, 223);
        this.tail.setRotationPoint(0.0F, 1.5F, 14.5F);
        this.tail.addBox(-2.5F, -1.5F, 0.0F, 5, 3, 10, 0.0F);
        this.setRotateAngle(tail, -0.31869712141416456F, 0.0F, 0.0F);
        this.shell2 = new ModelRenderer(this, 85, 157);
        this.shell2.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.shell2.addBox(-9.5F, -2.5F, -9.5F, 19, 5, 19, 0.0F);
        this.toe1_left_back = new ModelRenderer(this, 25, 100);
        this.toe1_left_back.setRotationPoint(-1.9F, 3.0F, -8.0F);
        this.toe1_left_back.addBox(-2.0F, -1.0F, -6.0F, 4, 2, 6, 0.0F);
        this.setRotateAngle(toe1_left_back, 0.0F, 0.136659280431156F, 0.0F);
        this.toe2_right_front = new ModelRenderer(this, 3, 100);
        this.toe2_right_front.setRotationPoint(-1.9F, 3.0F, -8.0F);
        this.toe2_right_front.addBox(-2.0F, -1.0F, -6.0F, 4, 2, 6, 0.0F);
        this.setRotateAngle(toe2_right_front, 0.0F, 0.136659280431156F, 0.0F);
        this.nose = new ModelRenderer(this, 107, 6);
        this.nose.setRotationPoint(0.0F, 0.0F, -5.5F);
        this.nose.addBox(-3.5F, -3.0F, -6.0F, 7, 5, 6, 0.0F);
        this.setRotateAngle(nose, 0.36425021489121656F, 0.0F, 0.0F);
        this.leg_right_front = new ModelRenderer(this, 7, 73);
        this.leg_right_front.setRotationPoint(-10.3F, 2.7F, -11.0F);
        this.leg_right_front.addBox(-4.5F, -4.0F, -10.0F, 9, 8, 10, 0.0F);
        this.setRotateAngle(leg_right_front, 0.0F, 1.0471975511965976F, 0.0F);
        this.leg_back_left = new ModelRenderer(this, 7, 73);
        this.leg_back_left.setRotationPoint(10.3F, 2.7F, 11.0F);
        this.leg_back_left.addBox(-4.5F, -4.0F, -10.0F, 9, 8, 10, 0.0F);
        this.setRotateAngle(leg_back_left, 0.0F, -1.7453292519943295F, 0.0F);
        this.toe2_left_back = new ModelRenderer(this, 3, 100);
        this.toe2_left_back.setRotationPoint(2.3F, 3.0F, -8.0F);
        this.toe2_left_back.addBox(-2.0F, -1.0F, -6.0F, 4, 2, 6, 0.0F);
        this.setRotateAngle(toe2_left_back, 0.0F, -0.18203784098300857F, 0.0F);
        this.toe1_right_front = new ModelRenderer(this, 25, 100);
        this.toe1_right_front.setRotationPoint(2.3F, 3.0F, -8.0F);
        this.toe1_right_front.addBox(-2.0F, -1.0F, -6.0F, 4, 2, 6, 0.0F);
        this.setRotateAngle(toe1_right_front, 0.0F, -0.18203784098300857F, 0.0F);
        this.left_leg_front = new ModelRenderer(this, 7, 73);
        this.left_leg_front.setRotationPoint(10.3F, 2.7F, -11.0F);
        this.left_leg_front.addBox(-4.5F, -4.0F, -10.0F, 9, 8, 10, 0.0F);
        this.setRotateAngle(left_leg_front, 0.0F, -1.0471975511965976F, 0.0F);
        this.shell1 = new ModelRenderer(this, 70, 110);
        this.shell1.setRotationPoint(0.0F, -11.0F, 0.0F);
        this.shell1.addBox(-12.5F, -4.5F, -12.5F, 25, 9, 25, 0.0F);
        this.toe2_left_front = new ModelRenderer(this, 3, 100);
        this.toe2_left_front.setRotationPoint(2.3F, 3.0F, -8.0F);
        this.toe2_left_front.addBox(-2.0F, -1.0F, -6.0F, 4, 2, 6, 0.0F);
        this.setRotateAngle(toe2_left_front, 0.0F, -0.18203784098300857F, 0.0F);
        this.root = new ModelRenderer(this, 60, 50);
        this.root.setRotationPoint(0.0F, 17.009999999999998F, 1.0F);
        this.root.addBox(-15.5F, -6.5F, -15.5F, 31, 13, 31, 0.0F);
        this.toe1_left_front = new ModelRenderer(this, 25, 100);
        this.toe1_left_front.setRotationPoint(-1.9F, 3.0F, -8.0F);
        this.toe1_left_front.addBox(-2.0F, -1.0F, -6.0F, 4, 2, 6, 0.0F);
        this.setRotateAngle(toe1_left_front, 0.0F, 0.136659280431156F, 0.0F);
        this.toe1_right_back = new ModelRenderer(this, 25, 100);
        this.toe1_right_back.setRotationPoint(2.3F, 3.0F, -8.0F);
        this.toe1_right_back.addBox(-2.0F, -1.0F, -6.0F, 4, 2, 6, 0.0F);
        this.setRotateAngle(toe1_right_back, 0.0F, -0.18203784098300857F, 0.0F);
        this.jaw = new ModelRenderer(this, 53, 17);
        this.jaw.setRotationPoint(0.0F, 5.0F, 0.5F);
        this.jaw.addBox(-4.5F, -3.0F, -12.0F, 9, 6, 12, 0.0F);
        this.setRotateAngle(jaw, 0.27314402793711257F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 105, 23);
        this.head.setRotationPoint(0.0F, -2.0F, -15.0F);
        this.head.addBox(-4.0F, -3.0F, -7.0F, 8, 6, 7, 0.0F);
        this.shell3 = new ModelRenderer(this, 100, 197);
        this.shell3.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.shell3.addBox(-5.5F, -1.5F, -5.5F, 11, 3, 11, 0.0F);
        this.leg_right_back = new ModelRenderer(this, 7, 73);
        this.leg_right_back.setRotationPoint(-10.3F, 2.7F, 11.0F);
        this.leg_right_back.addBox(-4.5F, -4.0F, -10.0F, 9, 8, 10, 0.0F);
        this.setRotateAngle(leg_right_back, 0.0F, 1.7453292519943295F, 0.0F);
        this.toe2_right_back = new ModelRenderer(this, 3, 100);
        this.toe2_right_back.setRotationPoint(-1.9F, 3.0F, -8.0F);
        this.toe2_right_back.addBox(-2.0F, -1.0F, -6.0F, 4, 2, 6, 0.0F);
        this.setRotateAngle(toe2_right_back, 0.0F, 0.136659280431156F, 0.0F);
        this.root.addChild(this.tail);
        this.shell1.addChild(this.shell2);
        this.leg_back_left.addChild(this.toe1_left_back);
        this.leg_right_front.addChild(this.toe2_right_front);
        this.head.addChild(this.nose);
        this.root.addChild(this.leg_right_front);
        this.root.addChild(this.leg_back_left);
        this.leg_back_left.addChild(this.toe2_left_back);
        this.leg_right_front.addChild(this.toe1_right_front);
        this.root.addChild(this.left_leg_front);
        this.root.addChild(this.shell1);
        this.left_leg_front.addChild(this.toe2_left_front);
        this.left_leg_front.addChild(this.toe1_left_front);
        this.leg_right_back.addChild(this.toe1_right_back);
        this.head.addChild(this.jaw);
        this.root.addChild(this.head);
        this.shell2.addChild(this.shell3);
        this.root.addChild(this.leg_right_back);
        this.leg_right_back.addChild(this.toe2_right_back);

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

        animateLimbs(this.head, this.jaw, null, this.leg_right_front, this.left_leg_front, this.leg_right_back, this.leg_back_left,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

    @Override
    public ModelRenderer getModelForItem() {
        return jaw;
    }

    @Override
    public void translateToHand() {
        GlStateManager.translate(-0.1, -0.65, -0.5);
        GlStateManager.rotate(-35, 0, 0, 1);
        GlStateManager.rotate(-90, 0, 1, 0);

    }

    @Override
    public float getShadowScale() {
        return 1.6F;
    }
}
