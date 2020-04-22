package library.entities;

import io.netty.buffer.ByteBuf;
import library.entities.LibEntityMob;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class LibEntityEffect extends LibEntityMob {

    public int deathTicks;

    public int color = 0xFF00FF;
    public int deathTicksMax = 200;

    public LibEntityEffect(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        this.setHealth(0);
        //this.setSize(0.1F, 0.1F);
        this.entityCollisionReduction = 1F;
        this.setNoGravity(true);
    }

    @Override
    protected void onDeathUpdate() {
        //super.onDeathUpdate();

        ++this.deathTicks;

        if (this.deathTicks >= deathTicksMax - 20 && this.deathTicks <= deathTicksMax)
        {
            float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + (double)f, this.posY + 2.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
        }

        if (this.deathTicks == deathTicksMax && !this.world.isRemote)
        {
            this.setDead();
        }
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        super.writeSpawnData(buffer);
        buffer.writeInt(deathTicksMax);
        buffer.writeInt(color);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        super.readSpawnData(additionalData);
        deathTicksMax = additionalData.readInt();
        color = additionalData.readInt();
    }
}
