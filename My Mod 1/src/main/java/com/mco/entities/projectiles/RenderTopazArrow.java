package com.mco.entities.projectiles;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.ResourceLocation;

public class RenderTopazArrow extends RenderArrow<EntityTippedArrow>{

    private static final ResourceLocation TEXTURE = new ResourceLocation("tuom:textures/entities/projectiles/topaz_arrow.png");

    public RenderTopazArrow(RenderManager manager)
    {
        super(manager);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityTippedArrow entity)
    {
        return TEXTURE;
    }

}
