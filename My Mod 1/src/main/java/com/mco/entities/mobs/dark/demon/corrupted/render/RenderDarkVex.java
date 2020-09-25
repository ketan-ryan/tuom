package com.mco.entities.mobs.dark.demon.corrupted.render;


import com.mco.entities.mobs.dark.demon.corrupted.EntityDarkVex;
import com.mco.entities.mobs.dark.demon.corrupted.ModelDarkVex;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDarkVex extends RenderLiving<EntityDarkVex>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("tuom:textures/entities/mobs/dark_vex.png");
		    
	public RenderDarkVex(RenderManager managerIn) 
	{
		super(managerIn, new ModelDarkVex(), 1);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDarkVex entity) 
	{
		return TEXTURE;
	}
    
    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityDarkVex entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
