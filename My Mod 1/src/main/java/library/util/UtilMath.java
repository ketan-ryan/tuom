package library.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class UtilMath {

    public static Vec3d getAim(Entity entitySource, Entity entityTarget, double speed) {
        double distFromMe = 0.5D;
        //Vec3d vec3d = entitySource.getLook(1.0F);
        double vecX = entityTarget.posX - (entitySource.posX/* + vec3d.x * distFromMe*/);
        double vecY = entityTarget.getEntityBoundingBox().minY + (double)(entityTarget.height / 2.0F) - (0.5D + entitySource.posY + (double)(entitySource.height / 2.0F));
        double vecZ = entityTarget.posZ - (entitySource.posZ/* + vec3d.z * distFromMe*/);

        double dist = entitySource.getDistance(entityTarget);

        return new Vec3d(vecX / dist * speed, vecY / dist * speed, vecZ / dist * speed);
    }

    public static Vec3d getPosition(Entity entitySource, Entity entityTarget, double distFromSelf) {
        double distFromMe = 0.5D;
        //Vec3d vec3d = entitySource.getLook(1.0F);
        double vecX = entityTarget.posX - (entitySource.posX/* + vec3d.x * distFromMe*/);
        double vecY = entityTarget.getEntityBoundingBox().minY + (double)(entityTarget.height / 2.0F) - (0.5D + entitySource.posY + (double)(entitySource.height / 2.0F));
        double vecZ = entityTarget.posZ - (entitySource.posZ/* + vec3d.z * distFromMe*/);

        double dist = entitySource.getDistance(entityTarget);

        return new Vec3d(entitySource.posX + vecX / dist * distFromSelf, entitySource.posY + (double)(entitySource.height / 2.0F) + 0.5D, entitySource.posZ + vecZ / dist * distFromSelf);
    }

    public static Vec3d getAim(Vec3d source, Vec3d target, double amp) {
        double vecX = target.x - (source.x);
        double vecY = target.y - source.y;
        double vecZ = target.z - (source.z);

        double dist = MathHelper.sqrt(vecX * vecX + vecY * vecY + vecZ * vecZ);

        return new Vec3d(vecX / dist * amp, vecY / dist * amp, vecZ / dist * amp);
    }

    public static double getDistance(Vec3d source, Vec3d target) {
        double vecX = target.x - (source.x);
        double vecY = target.y - source.y;
        double vecZ = target.z - (source.z);

        return MathHelper.sqrt(vecX * vecX + vecY * vecY + vecZ * vecZ);
    }

}
