package library.entities;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LibRenderArrow extends RenderArrow<EntityTippedArrow>
{
    private ResourceLocation texture = new ResourceLocation("textures/entity/projectiles/my_projectile_1.png");

    public LibRenderArrow(RenderManager manager, ResourceLocation texture)
    {
        super(manager);
        this.texture = texture;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityTippedArrow entity)
    {
        return texture;
    }
}