package com.mco.entities.mobs.dark.demon.corrupted.render;

import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedSheep;

import library.entities.mobs.models.LibModelSheep;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCorruptedSheep extends RenderLiving<EntityCorruptedSheep>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("tuom:textures/entities/mobs/corrupted_sheep.png");
		    
	public RenderCorruptedSheep(RenderManager managerIn) 
	{
		super(managerIn, new LibModelSheep(), 1);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCorruptedSheep entity) 
	{
		return TEXTURE;
	}
    
    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityCorruptedSheep entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
