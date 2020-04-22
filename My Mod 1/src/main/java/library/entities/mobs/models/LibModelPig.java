package library.entities.mobs.models;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LibModelPig extends ModelQuadruped {
    public LibModelPig() {
        this(0.0F);
    }

    public LibModelPig(float scale) {
        super(6, scale);
        this.head.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1, scale);
        this.childYOffset = 4.0F;
    }
}