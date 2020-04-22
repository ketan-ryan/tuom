package library.entities.mobs.models;

import library.entities.IAnimationSpeed;
import library.entities.vehicles.LibEntityVehicle;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class LibModelBase extends ModelBase {

    public List<ModelRenderer> listArmHierarchy = null;
    private HashMap<ModelRenderer, ModelRenderer> lookupChildToParent = new HashMap<>();
    public HashMap<ModelRenderer, Vec3d> lookupModelToDefaultRotations = new HashMap<>();

    public float extraScaleForRender = 1F;

    public LibModelBase() {

    }

    public void staggerWaggle(float ageInTicks, Vec3d dimensions, Function<Float, Float> function, float speed, int timeStagger, float ampAntiStagger, ModelRenderer... models) {
        float index = 1;
        for (ModelRenderer model : models) {
            //wing_1_right.rotateAngleX += MathHelper.sin((ageInTicks) * 0.067F) * range * 1F;
            if (dimensions.x != 0) {
                model.rotateAngleX += function.apply((ageInTicks - (timeStagger * (index - 1))) * speed) * dimensions.x * (ampAntiStagger * index);
            }
            if (dimensions.y != 0) {
                model.rotateAngleY += function.apply((ageInTicks - (timeStagger * (index - 1))) * speed) * dimensions.y * (ampAntiStagger * index);
            }
            if (dimensions.z != 0) {
                model.rotateAngleZ += function.apply((ageInTicks - (timeStagger * (index - 1))) * speed) * dimensions.z * (ampAntiStagger * index);
            }
            index++;
        }
    }

    public void setRotationAngles(ModelRenderer head, ModelRenderer arm_right, Vec3d arm_right_angles, ModelRenderer arm_left, Vec3d arm_left_angles, ModelRenderer leg_right1, ModelRenderer leg_left1, ModelRenderer leg_right2, ModelRenderer leg_left2, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

        if (entityIn instanceof IAnimationSpeed) {
            limbSwing *= ((IAnimationSpeed) entityIn).getAnimationSpeed();
        }

        limbSwingAmount /= this.extraScaleForRender;
        ageInTicks /= this.extraScaleForRender;

        if (arm_right != null) {
            arm_right.rotateAngleX += (float) arm_right_angles.x;
            arm_right.rotateAngleY += (float) arm_right_angles.y;
            arm_right.rotateAngleZ += (float) arm_right_angles.z;
        }

        if (arm_left != null) {
            arm_left.rotateAngleX += (float) arm_left_angles.x;
            arm_left.rotateAngleY += (float) arm_left_angles.y;
            arm_left.rotateAngleZ += (float) arm_left_angles.z;
        }

        float idleAmp = 1F;

        //looking
        if (head != null) head.rotateAngleX += headPitch * 0.017453292F;
        if (head != null) head.rotateAngleY += netHeadYaw * 0.017453292F;

        //moving
        if (leg_left1 != null) {
            leg_left1.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }
        if (leg_left2 != null) {
            leg_left2.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        }

        if (leg_right1 != null) {
            leg_right1.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        }

        if (leg_right2 != null) {
            leg_right2.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }


        //attacking
        if (this.swingProgress > 0.0F && arm_right != null && arm_left != null) {
            ModelRenderer modelrenderer = arm_right;
            float f1 = this.swingProgress;
            ModelRenderer bipedBody = this.getParent(modelrenderer);
            if (bipedBody != null) {
                bipedBody.rotateAngleY += MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

                /*arm_right.rotationPointZ = MathHelper.sin(bipedBody.rotateAngleY) * 5.0F;
                arm_right.rotationPointX = -MathHelper.cos(bipedBody.rotateAngleY) * 5.0F;
                arm_left.rotationPointZ = -MathHelper.sin(bipedBody.rotateAngleY) * 5.0F;
                arm_left.rotationPointX = MathHelper.cos(bipedBody.rotateAngleY) * 5.0F;*/
                arm_right.rotateAngleY += bipedBody.rotateAngleY;
                arm_left.rotateAngleY += bipedBody.rotateAngleY;
                arm_left.rotateAngleX += bipedBody.rotateAngleY;
                f1 = 1.0F - this.swingProgress;
                f1 = f1 * f1;
                f1 = f1 * f1;
                f1 = 1.0F - f1;
                float f2 = MathHelper.sin(f1 * (float) Math.PI);
                float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * (head != null ? -(head.rotateAngleX - 0.7F) * 0.75F : 1F);
                modelrenderer.rotateAngleX += (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
                modelrenderer.rotateAngleY += bipedBody.rotateAngleY * 2.0F;
                modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
            }
        } else {

            if (arm_right != null) {
                arm_right.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
                ModelRenderer bipedBody = this.getParent(arm_right);
                if (bipedBody != null) {
                    bipedBody.rotateAngleY = 0;
                }
            }

            if (arm_left != null)
                arm_left.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        }

        //idle
        if (arm_right != null) arm_right.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F * idleAmp + 0.05F;
        if (arm_left != null) arm_left.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        if (arm_right != null) arm_right.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * idleAmp * 0.05F;
        if (arm_left != null) arm_left.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }

    public void animateLimbs(ModelRenderer head, ModelRenderer arm_right, ModelRenderer arm_left, ModelRenderer leg_right1, ModelRenderer leg_left1, ModelRenderer leg_right2, ModelRenderer leg_left2, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.setRotationAngles(head, arm_right, new Vec3d(0, 0, 0), arm_left, new Vec3d(0, 0, 0), leg_right1, leg_left1, leg_right2, leg_left2, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

    public ModelRenderer getModelForItem() {
        return null;
    }

    public void resetRotations() {
        for (Map.Entry<ModelRenderer, Vec3d> entry : lookupModelToDefaultRotations.entrySet()) {
            entry.getKey().rotateAngleX = (float)entry.getValue().x;
            entry.getKey().rotateAngleY = (float)entry.getValue().y;
            entry.getKey().rotateAngleZ = (float)entry.getValue().z;
        }
    }

    public void mapModelRotations(ModelRenderer root) {
        recurseModel(root);
    }

    private void recurseModel(ModelRenderer parent) {
        if (parent.childModels != null && parent.childModels.size() > 0) {
            for (ModelRenderer child : parent.childModels) {
                if (!lookupModelToDefaultRotations.containsKey(child)) {
                    lookupModelToDefaultRotations.put(child, new Vec3d(child.rotateAngleX, child.rotateAngleY, child.rotateAngleZ));
                }

                if (child.childModels != null && child.childModels.size() > 0) {
                    recurseModel(child);
                }
            }
        }
    }

    public List<ModelRenderer> getArmHierarchyFromBody() {
        //since ModelRenderer doesnt provide parent lookup, we have to generate one ourselves the hard way
        if (listArmHierarchy == null) {
            listArmHierarchy = new ArrayList<>();
            ModelRenderer armToUse = getModelForItem();
            if (armToUse != null) {
                lookupChildToParent = new HashMap<>();
                for (ModelRenderer modelPart : boxList) {
                    if (modelPart.childModels != null) {
                        for (ModelRenderer modelChild : modelPart.childModels) {
                            lookupChildToParent.put(modelChild, modelPart);
                        }
                    }
                }

                listArmHierarchy.add(armToUse);
                ModelRenderer nextParent = lookupChildToParent.get(armToUse);
                while (nextParent != null) {
                    listArmHierarchy.add(nextParent);
                    nextParent = lookupChildToParent.get(nextParent);
                }

                Collections.reverse(listArmHierarchy);
            } else {
                return new ArrayList<>();
            }
        }
        return listArmHierarchy;
    }

    public ModelRenderer getParent(ModelRenderer renderer) {
        //init it
        getArmHierarchyFromBody();
        return lookupChildToParent.get(renderer);
    }

    public void translateToHand() {

    }

    public float getShadowScale() {
        return 1F;
    }

    public void tiltModelPiece(ModelRenderer model, Entity entity, float scale) {
        model.rotateAngleX = (float)Math.toRadians(entity.rotationPitch) * scale;
    }

    public void spinModelPieceX(ModelRenderer model, Entity entity, float limbSwing, float speedScale) {
        spinModelPiece(model, new Vec3d(1, 0, 0), entity, limbSwing, speedScale);
    }

    public void spinModelPieceY(ModelRenderer model, Entity entity, float limbSwing, float speedScale) {
        spinModelPiece(model, new Vec3d(0, 1, 0), entity, limbSwing, speedScale);
    }

    public void spinModelPieceZ(ModelRenderer model, Entity entity, float limbSwing, float speedScale) {
        spinModelPiece(model, new Vec3d(0, 0, 1), entity, limbSwing, speedScale);
    }

    private void spinModelPiece(ModelRenderer model, Vec3d vec, Entity entity, float limbSwing, float speedScale) {

        float moveForward = ((EntityLivingBase) entity).moveForward;
        float val;
        if (false && entity instanceof LibEntityVehicle) {
            moveForward = ((LibEntityVehicle) entity).moveForwardLife * 0.4F;
            val = moveForward;
        } else {
            boolean isForward = moveForward >= 0;
            val = isForward ? limbSwing : -limbSwing;
        }

        if (vec.x != 0) {
            model.rotateAngleX += val * vec.x * speedScale;
        } else if (vec.y != 0) {
            model.rotateAngleY += val * vec.y * speedScale;
        } else if (vec.z != 0) {
            model.rotateAngleZ += val * vec.z * speedScale;
        }
    }
}
