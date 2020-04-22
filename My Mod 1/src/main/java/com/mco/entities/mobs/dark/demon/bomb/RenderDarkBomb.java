package com.mco.entities.mobs.dark.demon.bomb;

import com.mco.entities.mobs.dark.demon.layers.ModelDarkLaser;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderDarkBomb extends RenderLiving<EntityDarkBomb>
{
    public static final Factory FACTORY = new Factory();

	public static final ResourceLocation TEXTURE = new ResourceLocation("tuom:textures/entities/blank.png");
	
	public RenderDarkBomb(RenderManager managerIn) 
	{
		super(managerIn, new ModelDarkLaser(), 0);
		this.addLayer(new LayerDarkBombGlow());
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDarkBomb entity) 
	{
		return TEXTURE;
	}

	public static class Factory implements IRenderFactory<EntityDarkBomb>
    {
        @Override
        public Render<? super EntityDarkBomb> createRenderFor(RenderManager manager)
        {
            return new RenderDarkBomb(manager);
        }
    }
}
