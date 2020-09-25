package com.mco.entities.mobs.dark.demon.corrupted.render;

import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedChicken;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCorruptedChicken extends RenderLiving<EntityCorruptedChicken>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("tuom:textures/entities/mobs/corrupted_chicken.png");
		    
	public RenderCorruptedChicken(RenderManager managerIn) 
	{
		super(managerIn, new ModelChicken(), 1);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCorruptedChicken entity) 
	{
		return TEXTURE;
	}
    
    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityCorruptedChicken entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
