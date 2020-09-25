package com.mco.entities.mobs.dark.demon.corrupted.render;

import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedCow;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCorruptedCow extends RenderLiving<EntityCorruptedCow>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("tuom:textures/entities/mobs/corrupted_cow.png");
		    
	public RenderCorruptedCow(RenderManager managerIn) 
	{
		super(managerIn, new ModelCow(), 1);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCorruptedCow entity) 
	{
		return TEXTURE;
	}
    
    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityCorruptedCow entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
