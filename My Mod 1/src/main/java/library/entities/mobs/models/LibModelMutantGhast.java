package library.entities.mobs.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * mob_chaos-ghast - 
 * Created using Tabula 6.0.0
 */
public class LibModelMutantGhast extends LibModelBase {
    public ModelRenderer body_root;
    public ModelRenderer body_shell;
    public ModelRenderer egg;
    public ModelRenderer claw_front;
    public ModelRenderer claw_back;
    public ModelRenderer claw_right;
    public ModelRenderer claw_left;
    public ModelRenderer claw_lower_front;
    public ModelRenderer claw_lower_back;
    public ModelRenderer claw_lower_right;
    public ModelRenderer claw_lower_left;

    public LibModelMutantGhast() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.egg = new ModelRenderer(this, 45, 90);
        this.egg.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.egg.addBox(-3.5F, 0.0F, -3.5F, 7, 5, 7, 0.0F);
        this.claw_front = new ModelRenderer(this, 0, 97);
        this.claw_front.setRotationPoint(0.0F, 5.0F, -5.0F);
        this.claw_front.addBox(-3.0F, -1.5F, -10.0F, 6, 3, 10, 0.0F);
        this.setRotateAngle(claw_front, 0.6829473363053812F, 0.0F, 0.0F);
        this.claw_lower_back = new ModelRenderer(this, 9, 113);
        this.claw_lower_back.setRotationPoint(0.0F, 1.5F, 9.0F);
        this.claw_lower_back.addBox(-1.0F, 0.0F, -2.5F, 2, 8, 5, 0.0F);
        this.setRotateAngle(claw_lower_back, 0.0F, 1.593485607070823F, 0.0F);
        this.claw_lower_right = new ModelRenderer(this, 9, 113);
        this.claw_lower_right.setRotationPoint(0.0F, 1.5F, -9.0F);
        this.claw_lower_right.addBox(-1.0F, 0.0F, -2.5F, 2, 8, 5, 0.0F);
        this.setRotateAngle(claw_lower_right, 0.0F, -1.593485607070823F, 0.0F);
        this.claw_lower_front = new ModelRenderer(this, 9, 113);
        this.claw_lower_front.setRotationPoint(0.0F, 1.5F, -9.0F);
        this.claw_lower_front.addBox(-1.0F, 0.0F, -2.5F, 2, 8, 5, 0.0F);
        this.setRotateAngle(claw_lower_front, 0.0F, -1.593485607070823F, 0.0F);
        this.body_root = new ModelRenderer(this, 30, 47);
        this.body_root.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.body_root.addBox(-9.5F, -9.5F, -9.5F, 19, 19, 19, 0.0F);
        this.claw_left = new ModelRenderer(this, 0, 97);
        this.claw_left.setRotationPoint(5.0F, 5.0F, 0.0F);
        this.claw_left.addBox(-3.0F, -1.5F, -10.0F, 6, 3, 10, 0.0F);
        this.setRotateAngle(claw_left, 0.6829473363053812F, -1.5707963267948966F, 0.045553093477052F);
        this.claw_right = new ModelRenderer(this, 0, 97);
        this.claw_right.setRotationPoint(-5.0F, 5.0F, 0.0F);
        this.claw_right.addBox(-3.0F, -1.5F, -10.0F, 6, 3, 10, 0.0F);
        this.setRotateAngle(claw_right, 0.6829473363053812F, 1.5707963267948966F, 0.045553093477052F);
        this.body_shell = new ModelRenderer(this, 25, 0);
        this.body_shell.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body_shell.addBox(-10.0F, -10.0F, -10.0F, 20, 20, 20, 0.0F);
        this.claw_lower_left = new ModelRenderer(this, 9, 113);
        this.claw_lower_left.setRotationPoint(0.0F, 1.5F, -9.0F);
        this.claw_lower_left.addBox(-1.0F, 0.0F, -2.5F, 2, 8, 5, 0.0F);
        this.setRotateAngle(claw_lower_left, 0.0F, -1.593485607070823F, 0.0F);
        this.claw_back = new ModelRenderer(this, 0, 97);
        this.claw_back.setRotationPoint(0.0F, 5.0F, 5.0F);
        this.claw_back.addBox(-3.0F, -1.5F, 0.0F, 6, 3, 10, 0.0F);
        this.setRotateAngle(claw_back, -0.6829473363053812F, 0.0F, 0.01884955592153876F);
        this.body_root.addChild(this.egg);
        this.body_root.addChild(this.claw_front);
        this.claw_back.addChild(this.claw_lower_back);
        this.claw_right.addChild(this.claw_lower_right);
        this.claw_front.addChild(this.claw_lower_front);
        this.body_root.addChild(this.claw_left);
        this.body_root.addChild(this.claw_right);
        this.body_root.addChild(this.body_shell);
        this.claw_left.addChild(this.claw_lower_left);
        this.body_root.addChild(this.claw_back);

        mapModelRotations(body_root);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body_root.render(f5);
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

        animateLimbs(null, claw_front, claw_back, null, null, null, null,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        animateLimbs(null, claw_right, claw_left, null, null, null, null,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

    @Override
    public ModelRenderer getModelForItem() {
        return claw_lower_front;
    }

    @Override
    public void translateToHand() {
        GlStateManager.translate(0.3, 0, 0);
        GlStateManager.translate(0, 0, -0.05);
        GlStateManager.rotate(90, 0, 1, 0);
        GlStateManager.rotate(-45, 1, 0, 0);
    }

    @Override
    public float getShadowScale() {
        return 1.0F;
    }
}
