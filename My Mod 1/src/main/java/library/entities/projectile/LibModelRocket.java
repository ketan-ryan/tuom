package library.entities.projectile;

import library.entities.mobs.models.LibModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * projectile_rocket - Undefined
 * Created using Tabula 7.0.0
 */
public class LibModelRocket extends LibModelBase {
    public ModelRenderer rocket_root;
    public ModelRenderer warhead;
    public ModelRenderer fin_right;
    public ModelRenderer fin_left;
    public ModelRenderer fin_bottom;
    public ModelRenderer fin_top;
    public ModelRenderer tip;

    public LibModelRocket() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.tip = new ModelRenderer(this, 30, 1);
        this.tip.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.tip.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 1, 0.0F);
        this.fin_left = new ModelRenderer(this, 23, 50);
        this.fin_left.mirror = true;
        this.fin_left.setRotationPoint(0.3F, 0.0F, 8.0F);
        this.fin_left.addBox(-1.5F, -0.5F, 0.0F, 3, 1, 6, 0.0F);
        this.setRotateAngle(fin_left, 0.0F, 1.0471975511965976F, 0.0F);
        this.fin_right = new ModelRenderer(this, 23, 50);
        this.fin_right.setRotationPoint(-0.9F, 0.0F, 8.0F);
        this.fin_right.addBox(-1.5F, -0.5F, 0.0F, 3, 1, 6, 0.0F);
        this.setRotateAngle(fin_right, 0.0F, -1.0471975511965976F, 0.010297442586766544F);
        this.rocket_root = new ModelRenderer(this, 12, 25);
        this.rocket_root.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.rocket_root.addBox(-1.0F, -1.0F, -10.0F, 2, 2, 20, 0.0F);
        this.fin_bottom = new ModelRenderer(this, 23, 50);
        this.fin_bottom.mirror = true;
        this.fin_bottom.setRotationPoint(0.0F, 0.0F, 7.5F);
        this.fin_bottom.addBox(-1.5F, -0.5F, 0.0F, 3, 1, 6, 0.0F);
        this.setRotateAngle(fin_bottom, 0.0F, 1.0471975511965976F, 1.5707963267948966F);
        this.warhead = new ModelRenderer(this, 23, 10);
        this.warhead.setRotationPoint(0.0F, 0.0F, -12.5F);
        this.warhead.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
        this.fin_top = new ModelRenderer(this, 23, 50);
        this.fin_top.mirror = true;
        this.fin_top.setRotationPoint(0.0F, 0.0F, 7.5F);
        this.fin_top.addBox(-1.5F, -0.5F, 0.0F, 3, 1, 6, 0.0F);
        this.setRotateAngle(fin_top, 0.0F, 1.0471975511965976F, -1.5707963267948966F);
        this.warhead.addChild(this.tip);
        this.rocket_root.addChild(this.fin_left);
        this.rocket_root.addChild(this.fin_right);
        this.rocket_root.addChild(this.fin_bottom);
        this.rocket_root.addChild(this.warhead);
        this.rocket_root.addChild(this.fin_top);

        mapModelRotations(rocket_root);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.rocket_root.render(f5);
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

        animateLimbs(rocket_root, null, null, null, null, null, null,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

    @Override
    public float getShadowScale() {
        return 1.5F;
    }
}
