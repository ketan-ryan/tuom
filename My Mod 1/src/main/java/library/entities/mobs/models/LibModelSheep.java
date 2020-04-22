package library.entities.mobs.models;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LibModelSheep extends ModelQuadruped
{
    private float headRotationAngleX;

    public ModelRenderer head2;
    public ModelRenderer body2;

    public ModelRenderer leg11;
    public ModelRenderer leg22;
    public ModelRenderer leg33;
    public ModelRenderer leg44;

    public LibModelSheep()
    {
        super(12, 0.0F);
        this.textureWidth = 64;
        this.textureHeight = 64;

        float scale = 0;
        int height = 12;

        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, height, 4, scale);
        this.leg1.setRotationPoint(-3.0F, (float)(24 - height), 7.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, height, 4, scale);
        this.leg2.setRotationPoint(3.0F, (float)(24 - height), 7.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, height, 4, scale);
        this.leg3.setRotationPoint(-3.0F, (float)(24 - height), -5.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, height, 4, scale);
        this.leg4.setRotationPoint(3.0F, (float)(24 - height), -5.0F);

        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
        this.head.setRotationPoint(0.0F, 6.0F, -8.0F);
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);

        this.head2 = new ModelRenderer(this, 0, 0+32);
        this.head2.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
        this.head2.setRotationPoint(0.0F, 6.0F, -8.0F);
        this.body2 = new ModelRenderer(this, 28, 8+32);
        this.body2.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
        this.body2.setRotationPoint(0.0F, 5.0F, 2.0F);

        this.leg11 = new ModelRenderer(this, 0, 16+32);
        this.leg11.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg11.setRotationPoint(-3.0F, 12.0F, 7.0F);
        this.leg22 = new ModelRenderer(this, 0, 16+32);
        this.leg22.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg22.setRotationPoint(3.0F, 12.0F, 7.0F);
        this.leg33 = new ModelRenderer(this, 0, 16+32);
        this.leg33.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg33.setRotationPoint(-3.0F, 12.0F, -5.0F);
        this.leg44 = new ModelRenderer(this, 0, 16+32);
        this.leg44.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.5F);
        this.leg44.setRotationPoint(3.0F, 12.0F, -5.0F);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
        //this.head.rotationPointY = 6.0F + ((EntitySheep)entitylivingbaseIn).getHeadRotationPointY(partialTickTime) * 9.0F;
        //this.headRotationAngleX = ((EntitySheep)entitylivingbaseIn).getHeadRotationAngleX(partialTickTime);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        this.head2.rotateAngleX = headPitch * 0.017453292F;
        this.head2.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body2.rotateAngleX = ((float)Math.PI / 2F);
        this.leg11.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg22.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leg33.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leg44.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.head.rotateAngleX = this.headRotationAngleX;
        this.head2.rotateAngleX = this.headRotationAngleX;
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        boolean isChildBak = this.isChild;
        this.isChild = false;
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.isChild = isChildBak;

        this.head2.render(scale);
        this.body2.render(scale);

        this.leg11.render(scale);
        this.leg22.render(scale);
        this.leg33.render(scale);
        this.leg44.render(scale);
    }
}