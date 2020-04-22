package library.entities.projectile;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public abstract class LibEntityArrow extends EntityTippedArrow implements IEntityAdditionalSpawnData {

    private SoundEvent hitSound = SoundEvents.ENTITY_ARROW_HIT;
    private float hitVolume = 1F;

    public LibEntityArrow(World world) {
        super(world);
    }

    /**
     * Gets the item to give to player when the arrow is picked up
     *
     * @return
     */
    public abstract Item getItemProjectile();

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(getItemProjectile());
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        if (shootingEntity != null) {
            buffer.writeInt(shootingEntity.getEntityId());
        } else {
            buffer.writeInt(-1);
        }
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        int entityID = additionalData.readInt();
        if (entityID != -1) shootingEntity = this.world.getEntityByID(entityID);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        //fix world reload default color set bug
        this.setPotionEffect(new ItemStack(Items.ARROW));
    }

    @Override
    public void playSound(SoundEvent soundIn, float volume, float pitch) {
        super.playSound(getHitSound(), getHitVolume(), pitch);
    }

    private SoundEvent getHitSound() {
        return hitSound;
    }

    public void setHitSound(SoundEvent hitSound) {
        this.hitSound = hitSound;
    }

    private float getHitVolume() {
        return hitVolume;
    }

    public void setHitVolume(float hitVolume) {
        this.hitVolume = hitVolume;
    }
}