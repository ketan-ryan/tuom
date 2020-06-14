package com.mco.entities.mobs.dark.demon.corrupted.render;

import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedPig;

import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCorruptedPig extends RenderLiving<EntityCorruptedPig>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("tuom:textures/entities/mobs/corrupted_pig.png");
		    
	public RenderCorruptedPig(RenderManager managerIn) 
	{
		super(managerIn, new ModelPig(), 1);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCorruptedPig entity) 
	{
		return TEXTURE;
	}
    
    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityCorruptedPig entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
