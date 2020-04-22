package library.entities.mobs.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/**
 * mob_dragon - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelHydra extends LibModelBase {
    public ModelRenderer root;
    public ModelRenderer neck_1;
    public ModelRenderer tail_1;
    public ModelRenderer wing_1_left;
    public ModelRenderer wing_1_right;
    public ModelRenderer head;
    public ModelRenderer snout;
    public ModelRenderer jaw;
    public ModelRenderer tail_2;
    public ModelRenderer tail_3;
    public ModelRenderer tail_4;
    public ModelRenderer tailwing_1_left;
    public ModelRenderer tailwing_1_right;
    public ModelRenderer tailwing_2_left;
    public ModelRenderer tailwing_2_right;
    public ModelRenderer wing_2_left;
    public ModelRenderer wing_3_left;
    public ModelRenderer wing_2_right;
    public ModelRenderer wing_3_right;

    public LibModelHydra() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.tail_4 = new ModelRenderer(this, 215, 226);
        this.tail_4.setRotationPoint(0.0F, 0.0F, 19.2F);
        this.tail_4.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 15, 0.0F);
        this.setRotateAngle(tail_4, 0.18203784098300857F, 0.0F, 0.0F);
        this.wing_2_right = new ModelRenderer(this, 10, 115);
        this.wing_2_right.setRotationPoint(-35.0F, -1.0F, 0.0F);
        this.wing_2_right.addBox(-30.0F, 1.0F, -10.0F, 30, 1, 20, 0.0F);
        this.wing_1_right = new ModelRenderer(this, 0, 70);
        this.wing_1_right.setRotationPoint(-6.0F, -3.0F, 13.0F);
        this.wing_1_right.addBox(-35.0F, -1.0F, -12.5F, 35, 2, 25, 0.0F);
        this.setRotateAngle(wing_1_right, 0.0F, 0.27314402793711257F, 0.22759093446006054F);
        this.tailwing_1_right = new ModelRenderer(this, 10, 180);
        this.tailwing_1_right.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailwing_1_right.addBox(-30.0F, -1.0F, -10.0F, 30, 2, 20, 0.0F);
        this.setRotateAngle(tailwing_1_right, 0.0F, 0.0F, 0.18203784098300857F);
        this.wing_1_left = new ModelRenderer(this, 0, 70);
        this.wing_1_left.setRotationPoint(6.0F, -3.0F, 13.0F);
        this.wing_1_left.addBox(0.0F, -1.0F, -12.5F, 35, 2, 25, 0.0F);
        this.setRotateAngle(wing_1_left, -0.091106186954104F, -0.27314402793711257F, -0.22759093446006054F);
        this.jaw = new ModelRenderer(this, 95, 25);
        this.jaw.setRotationPoint(0.0F, 2.5F, -20.0F);
        this.jaw.addBox(-5.0F, -2.0F, -11.0F, 10, 4, 11, 0.0F);
        this.setRotateAngle(jaw, 0.40980330836826856F, 0.0F, 0.0F);
        this.tail_3 = new ModelRenderer(this, 150, 228);
        this.tail_3.setRotationPoint(0.0F, 0.0F, 27.0F);
        this.tail_3.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 20, 0.0F);
        this.setRotateAngle(tail_3, 0.18203784098300857F, 0.0F, 0.0F);
        this.tailwing_2_right = new ModelRenderer(this, 25, 220);
        this.tailwing_2_right.setRotationPoint(-30.0F, -1.0F, 0.0F);
        this.tailwing_2_right.addBox(-20.0F, 0.0F, -7.5F, 20, 1, 15, 0.0F);
        this.setRotateAngle(tailwing_2_right, 0.0F, 0.0F, 0.36425021489121656F);
        this.tail_2 = new ModelRenderer(this, 135, 184);
        this.tail_2.setRotationPoint(6.0F, 0.8F, 29.0F);
        this.tail_2.addBox(-6.0F, -6.0F, 0.0F, 12, 12, 30, 0.0F);
        this.setRotateAngle(tail_2, 0.091106186954104F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 150, 2);
        this.head.setRotationPoint(0.0F, 1.0F, -20.0F);
        this.head.addBox(-6.0F, -5.0F, -20.0F, 12, 10, 20, 0.0F);
        this.setRotateAngle(head, 0.27314402793711257F, 0.0F, 0.0F);
        this.tailwing_2_left = new ModelRenderer(this, 25, 220);
        this.tailwing_2_left.setRotationPoint(30.0F, -1.0F, 0.0F);
        this.tailwing_2_left.addBox(0.0F, 0.0F, -7.5F, 20, 1, 15, 0.0F);
        this.setRotateAngle(tailwing_2_left, 0.0F, 0.0F, -0.36425021489121656F);
        this.root = new ModelRenderer(this, 130, 83);
        this.root.setRotationPoint(0.0F, 15.0F, -10.1F);
        this.root.addBox(-10.0F, -10.0F, 0.0F, 20, 19, 30, 0.0F);
        this.tailwing_1_left = new ModelRenderer(this, 10, 180);
        this.tailwing_1_left.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailwing_1_left.addBox(0.0F, -1.0F, -10.0F, 30, 2, 20, 0.0F);
        this.setRotateAngle(tailwing_1_left, 0.0F, 0.0F, -0.18203784098300857F);
        this.neck_1 = new ModelRenderer(this, 140, 36);
        this.neck_1.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.neck_1.addBox(-7.5F, -5.0F, -23.0F, 15, 13, 25, 0.0F);
        this.setRotateAngle(neck_1, -0.22759093446006054F, 0.0F, 0.0F);
        this.wing_2_left = new ModelRenderer(this, 10, 115);
        this.wing_2_left.setRotationPoint(35.0F, -1.0F, 0.0F);
        this.wing_2_left.addBox(0.0F, 1.0F, -10.0F, 30, 1, 20, 0.0F);
        this.wing_3_right = new ModelRenderer(this, 30, 150);
        this.wing_3_right.setRotationPoint(-30.0F, 0.0F, 0.0F);
        this.wing_3_right.addBox(-15.0F, 1.0F, -7.5F, 15, 1, 15, 0.0F);
        this.tail_1 = new ModelRenderer(this, 135, 135);
        this.tail_1.setRotationPoint(-7.5F, 0.0F, 29.0F);
        this.tail_1.addBox(0.0F, -7.5F, 0.0F, 15, 15, 30, 0.0F);
        this.setRotateAngle(tail_1, 0.136659280431156F, 0.0F, 0.0F);
        this.snout = new ModelRenderer(this, 100, 7);
        this.snout.setRotationPoint(0.0F, -1.0F, -20.0F);
        this.snout.addBox(-4.0F, -2.0F, -10.0F, 8, 4, 10, 0.0F);
        this.wing_3_left = new ModelRenderer(this, 30, 150);
        this.wing_3_left.setRotationPoint(30.0F, 0.0F, 0.0F);
        this.wing_3_left.addBox(0.0F, 1.0F, -7.5F, 15, 1, 15, 0.0F);
        this.tail_3.addChild(this.tail_4);
        this.wing_1_right.addChild(this.wing_2_right);
        this.root.addChild(this.wing_1_right);
        this.tail_4.addChild(this.tailwing_1_right);
        this.root.addChild(this.wing_1_left);
        this.head.addChild(this.jaw);
        this.tail_2.addChild(this.tail_3);
        this.tailwing_1_right.addChild(this.tailwing_2_right);
        this.tail_1.addChild(this.tail_2);
        this.neck_1.addChild(this.head);
        this.tailwing_1_left.addChild(this.tailwing_2_left);
        this.tail_4.addChild(this.tailwing_1_left);
        this.root.addChild(this.neck_1);
        this.wing_1_left.addChild(this.wing_2_left);
        this.wing_2_right.addChild(this.wing_3_right);
        this.root.addChild(this.tail_1);
        this.head.addChild(this.snout);
        this.wing_2_left.addChild(this.wing_3_left);

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

        animateLimbs(head, null, null, null, null, null, null,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        limbSwingAmount /= this.extraScaleForRender;
        ageInTicks /= this.extraScaleForRender;

        staggerWaggle(ageInTicks, new Vec3d(0.0, 0, 0.1), MathHelper::sin, 0.067F, 5, 1F,
                wing_1_right, wing_2_right, wing_3_right);
        staggerWaggle(ageInTicks, new Vec3d(0.0, 0, -0.1), MathHelper::sin, 0.067F, 5, 1F,
                wing_1_left, wing_2_left, wing_3_left);

        staggerWaggle(ageInTicks, new Vec3d(0.1, 0, 0.0), MathHelper::sin, 0.067F, 5, 0.5F,
                tail_1, tail_2, tail_3, tail_4);

        staggerWaggle(ageInTicks, new Vec3d(0.0, 0.0, -0.1), MathHelper::sin, 0.067F, 5, 1F,
                tailwing_1_left, tailwing_2_left);

        staggerWaggle(ageInTicks, new Vec3d(0.0, 0.0, 0.1), MathHelper::sin, 0.067F, 5, 1F,
                tailwing_1_right, tailwing_2_right);
    }

    @Override
    public ModelRenderer getModelForItem() {
        return wing_3_right;
    }

    @Override
    public void translateToHand() {
        //GlStateManager.translate(0.075, 1, 0);
    }

    @Override
    public float getShadowScale() {
        return 3.0F;
    }
}
