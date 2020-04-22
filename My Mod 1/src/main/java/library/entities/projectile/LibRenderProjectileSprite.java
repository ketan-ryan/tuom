package library.entities.projectile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public abstract class LibRenderProjectileSprite implements IRenderFactory {

    @Override
    public Render createRenderFor(RenderManager manager) {
        return new RenderSnowball<>(manager, getItem(),
                Minecraft.getMinecraft().getRenderItem());
    }

    public abstract Item getItem();
}
