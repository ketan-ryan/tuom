package library.entities.mobs.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * mob_chaos_creeper - Either Mojang or a mod author
 * Created using Tabula 6.0.0
 */
public class LibModelMutantCreeper extends LibModelBase {
    public ModelRenderer body_root;
    public ModelRenderer chest;
    public ModelRenderer leg_back_right;
    public ModelRenderer leg_front_right;
    public ModelRenderer leg_left_back;
    public ModelRenderer leg_front_left;
    public ModelRenderer head;
    public ModelRenderer chin;

    public LibModelMutantCreeper() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.chin = new ModelRenderer(this, 45, 24);
        this.chin.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.chin.addBox(-5.0F, -3.0F, -5.0F, 10, 3, 10, 0.0F);
        this.setRotateAngle(chin, 0.0F, 0.0F, -0.006806784082777885F);
        this.body_root = new ModelRenderer(this, 51, 58);
        this.body_root.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.body_root.addBox(-4.0F, -12.0F, -2.0F, 8, 12, 4, 0.0F);
        this.head = new ModelRenderer(this, 44, 0);
        this.head.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.head.addBox(-5.0F, -10.0F, -5.0F, 10, 10, 10, 0.0F);
        this.setRotateAngle(head, 0.136659280431156F, 0.0F, 0.0F);
        this.leg_front_left = new ModelRenderer(this, 61, 85);
        this.leg_front_left.setRotationPoint(1.5F, 0.2F, -2.0F);
        this.leg_front_left.addBox(-1.0F, 0.0F, -1.0F, 2, 26, 2, 0.0F);
        this.leg_back_right = new ModelRenderer(this, 61, 85);
        this.leg_back_right.setRotationPoint(-1.5F, 0.0F, 2.0F);
        this.leg_back_right.addBox(-1.0F, 0.0F, -1.0F, 2, 26, 2, 0.0F);
        this.leg_left_back = new ModelRenderer(this, 61, 85);
        this.leg_left_back.setRotationPoint(1.5F, 0.0F, 2.0F);
        this.leg_left_back.addBox(-1.0F, 0.0F, -1.0F, 2, 26, 2, 0.0F);
        this.leg_front_right = new ModelRenderer(this, 61, 85);
        this.leg_front_right.setRotationPoint(-1.5F, 0.0F, -2.0F);
        this.leg_front_right.addBox(-1.0F, 0.0F, -1.0F, 2, 26, 2, 0.0F);
        this.chest = new ModelRenderer(this, 51, 41);
        this.chest.setRotationPoint(0.0F, -12.0F, 0.0F);
        this.chest.addBox(-4.0F, -8.0F, -2.0F, 8, 8, 4, 0.0F);
        this.setRotateAngle(chest, 0.31869712141416456F, 0.0F, 0.0F);
        this.head.addChild(this.chin);
        this.chest.addChild(this.head);
        this.body_root.addChild(this.leg_front_left);
        this.body_root.addChild(this.leg_back_right);
        this.body_root.addChild(this.leg_left_back);
        this.body_root.addChild(this.leg_front_right);
        this.body_root.addChild(this.chest);

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

        animateLimbs(head, null, null, leg_front_right, leg_front_left, leg_back_right, leg_left_back,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

    @Override
    public ModelRenderer getModelForItem() {
        return chin;
    }

    @Override
    public void translateToHand() {
        GlStateManager.translate(0, -0.75, -0.2);
        GlStateManager.rotate(-90, 0, 1, 0);
        GlStateManager.rotate(15, 0, 1, 0);
    }

    @Override
    public float getShadowScale() {
        return 0.8F;
    }
}
