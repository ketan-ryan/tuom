package library.entities.projectile;

import io.netty.buffer.ByteBuf;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class LibEntityProjectile extends EntityThrowable implements IEntityAdditionalSpawnData {

    public LibEntityProjectile(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null) {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5F);
        }
    }

    public void setThrower(Entity entity) {
        ReflectionHelper.setPrivateValue(EntityThrowable.class, this, entity, "thrower", "field_70192_c");
    }

    @Override
    public void onUpdate() {
        EntityLivingBase entitylivingbase = this.getThrower();

        if (entitylivingbase != null && entitylivingbase instanceof EntityPlayer && !entitylivingbase.isEntityAlive()) {
            this.setDead();
        } else {
            super.onUpdate();
        }
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        if (getThrower() != null) {
            buffer.writeInt(getThrower().getEntityId());
        } else {
            buffer.writeInt(-1);
        }
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        int entityID = additionalData.readInt();
        if (entityID != -1) {
            ignoreEntity = this.world.getEntityByID(entityID);
            setThrower(ignoreEntity);
        }
    }
}
