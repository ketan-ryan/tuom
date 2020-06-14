package com.mco.entities.vehicles;

import library.entities.vehicles.LibModelJet;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDarkShip extends RenderLiving<DarkShip>{

    private static final ResourceLocation TEXTURE = new ResourceLocation("tuom:textures/entities/vehicles/dark_ship.png");
    
	public RenderDarkShip(RenderManager managerIn) 
	{
		super(managerIn, new LibModelJet(), 1);
	}

	@Override
	protected ResourceLocation getEntityTexture(DarkShip entity) 
	{
		return TEXTURE;
	}
    
    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(DarkShip entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
