package library.entities.vehicles;

import library.entities.mobs.models.LibModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * vehicle_sailboat - Undefined
 * Created using Tabula 6.0.0
 */
public class LibModelSailboat extends LibModelBase {
    public ModelRenderer hull_root;
    public ModelRenderer front1;
    public ModelRenderer hull_side_right;
    public ModelRenderer hull_side_left;
    public ModelRenderer hull_front3_left;
    public ModelRenderer hull_front3_right;
    public ModelRenderer hull_front2_right;
    public ModelRenderer hull_front2_left;
    public ModelRenderer hull_front;
    public ModelRenderer hull_side_back;
    public ModelRenderer pole;
    public ModelRenderer bench_back;
    public ModelRenderer bench_front;
    public ModelRenderer front2;
    public ModelRenderer sail1;
    public ModelRenderer sail2;
    public ModelRenderer sail3;
    public ModelRenderer sail4;
    public ModelRenderer sail5;

    public LibModelSailboat() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.sail2 = new ModelRenderer(this, 170, 77);
        this.sail2.setRotationPoint(0.5F, -33.8F, 0.0F);
        this.sail2.addBox(0.0F, -12.0F, -20.0F, 1, 12, 40, 0.0F);
        this.hull_front = new ModelRenderer(this, 75, 26);
        this.hull_front.setRotationPoint(0.5F, 0.0F, -40.4F);
        this.hull_front.addBox(-3.5F, -9.0F, -2.5F, 7, 9, 3, 0.0F);
        this.hull_side_right = new ModelRenderer(this, 0, 180);
        this.hull_side_right.setRotationPoint(-10.5F, 0.0F, 0.0F);
        this.hull_side_right.addBox(-1.5F, -9.0F, -30.0F, 3, 9, 60, 0.0F);
        this.sail1 = new ModelRenderer(this, 150, 134);
        this.sail1.setRotationPoint(0.5F, -21.8F, 0.0F);
        this.sail1.addBox(0.0F, -12.0F, -25.0F, 1, 12, 50, 0.0F);
        this.sail3 = new ModelRenderer(this, 185, 39);
        this.sail3.setRotationPoint(0.5F, -45.8F, 0.0F);
        this.sail3.addBox(0.0F, -7.0F, -12.5F, 1, 7, 25, 0.0F);
        this.hull_front2_right = new ModelRenderer(this, 40, 50);
        this.hull_front2_right.setRotationPoint(-4.5F, 0.0F, -35.0F);
        this.hull_front2_right.addBox(-1.5F, -9.0F, -5.0F, 3, 12, 5, 0.0F);
        this.bench_front = new ModelRenderer(this, 125, 160);
        this.bench_front.setRotationPoint(0.5F, -5.0F, -17.0F);
        this.bench_front.addBox(-9.5F, -1.0F, -2.5F, 19, 2, 6, 0.0F);
        this.sail5 = new ModelRenderer(this, 201, 0);
        this.sail5.setRotationPoint(0.5F, -56.8F, 0.0F);
        this.sail5.addBox(0.0F, -4.0F, -3.5F, 1, 4, 7, 0.0F);
        this.hull_front2_left = new ModelRenderer(this, 40, 50);
        this.hull_front2_left.mirror = true;
        this.hull_front2_left.setRotationPoint(5.5F, 0.0F, -35.0F);
        this.hull_front2_left.addBox(-1.5F, -9.0F, -5.0F, 3, 12, 5, 0.0F);
        this.bench_back = new ModelRenderer(this, 125, 160);
        this.bench_back.setRotationPoint(0.5F, -5.0F, 20.0F);
        this.bench_back.addBox(-9.5F, -1.0F, -2.5F, 19, 2, 6, 0.0F);
        this.pole = new ModelRenderer(this, 201, 200);
        this.pole.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.pole.addBox(0.0F, -50.0F, -1.0F, 2, 50, 2, 0.0F);
        this.setRotateAngle(pole, 0.0F, -0.7740535232594852F, 0.0F);
        this.hull_root = new ModelRenderer(this, 0, 80);
        this.hull_root.setRotationPoint(0.0F, 21.4F, -10.9F);
        this.hull_root.addBox(-12.0F, 0.0F, -30.0F, 25, 3, 60, 0.0F);
        this.hull_front3_right = new ModelRenderer(this, 40, 50);
        this.hull_front3_right.setRotationPoint(-7.5F, 0.0F, -30.0F);
        this.hull_front3_right.addBox(-1.5F, -9.0F, -5.0F, 3, 12, 5, 0.0F);
        this.sail4 = new ModelRenderer(this, 193, 15);
        this.sail4.setRotationPoint(0.5F, -52.8F, 0.0F);
        this.sail4.addBox(0.0F, -4.0F, -7.5F, 1, 4, 15, 0.0F);
        this.front1 = new ModelRenderer(this, 64, 60);
        this.front1.setRotationPoint(1.0F, 0.0F, -32.5F);
        this.front1.addBox(-7.0F, 0.0F, -2.5F, 13, 3, 5, 0.0F);
        this.hull_front3_left = new ModelRenderer(this, 40, 50);
        this.hull_front3_left.mirror = true;
        this.hull_front3_left.setRotationPoint(8.5F, 0.0F, -30.0F);
        this.hull_front3_left.addBox(-1.5F, -9.0F, -5.0F, 3, 12, 5, 0.0F);
        this.hull_side_left = new ModelRenderer(this, 0, 180);
        this.hull_side_left.mirror = true;
        this.hull_side_left.setRotationPoint(11.5F, 0.0F, 0.0F);
        this.hull_side_left.addBox(-1.5F, -9.0F, -30.0F, 3, 9, 60, 0.0F);
        this.hull_side_back = new ModelRenderer(this, 63, 157);
        this.hull_side_back.setRotationPoint(0.5F, 3.0F, 32.5F);
        this.hull_side_back.addBox(-9.5F, -12.0F, -2.5F, 19, 12, 3, 0.0F);
        this.front2 = new ModelRenderer(this, 74, 45);
        this.front2.setRotationPoint(-0.5F, 0.0F, -5.0F);
        this.front2.addBox(-3.5F, 0.0F, -2.5F, 7, 3, 5, 0.0F);
        this.pole.addChild(this.sail2);
        this.hull_root.addChild(this.hull_front);
        this.hull_root.addChild(this.hull_side_right);
        this.pole.addChild(this.sail1);
        this.pole.addChild(this.sail3);
        this.hull_root.addChild(this.hull_front2_right);
        this.hull_root.addChild(this.bench_front);
        this.pole.addChild(this.sail5);
        this.hull_root.addChild(this.hull_front2_left);
        this.hull_root.addChild(this.bench_back);
        this.hull_root.addChild(this.pole);
        this.hull_root.addChild(this.hull_front3_right);
        this.pole.addChild(this.sail4);
        this.hull_root.addChild(this.front1);
        this.hull_root.addChild(this.hull_front3_left);
        this.hull_root.addChild(this.hull_side_left);
        this.hull_root.addChild(this.hull_side_back);
        this.front1.addChild(this.front2);

        mapModelRotations(hull_root);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.translate(0, -0.2F, 0);
        this.hull_root.render(f5);
        GlStateManager.translate(0, 0.2F, 0);
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

        animateLimbs(null, null, null, null, null, null, null,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
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
        return 3.0F;
    }
}
