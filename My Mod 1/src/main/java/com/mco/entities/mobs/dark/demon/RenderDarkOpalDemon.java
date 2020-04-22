package com.mco.entities.mobs.dark.demon;

import com.mco.entities.mobs.dark.demon.layers.LayerDarkOpalDeath;
import com.mco.entities.mobs.dark.demon.layers.LayerDemonAura;
import com.mco.entities.mobs.AdvancedLibLayerHeldItem;
import com.mco.entities.mobs.dark.demon.layers.LayerDarkGlow;
import com.mco.entities.mobs.dark.demon.layers.LayerDarkOpalDeath;
import com.mco.entities.mobs.dark.demon.layers.LayerDemonAura;
import com.mco.entities.mobs.dark.demon.layers.LayerLeftEyeGlow;
import com.mco.entities.mobs.dark.demon.layers.LayerRightEyeGlow;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderDarkOpalDemon extends RenderLiving<EntityDarkOpalDemon>
{
    private static final ResourceLocation DEMON_TEXTURES = new ResourceLocation("tuom:textures/entities/mobs/dark_demon.png");
	private static final ResourceLocation DEMON_EXPLODING_TEXTURES = new ResourceLocation("tuom:textures/entities/mobs/dark_demon_explode.png");
	
    public static final Factory FACTORY = new Factory();
	
   public ModelDarkOpalDemon demonModel = new ModelDarkOpalDemon();
    
	public RenderDarkOpalDemon(RenderManager managerIn) 
	{
		super(managerIn, new ModelDarkOpalDemon(), 1);
		this.addLayer(new LayerDarkGlow(this));
    	this.addLayer(new AdvancedLibLayerHeldItem(this));
    	this.addLayer(new LayerDarkOpalDeath());
    	this.addLayer(new LayerRightEyeGlow());
    	this.addLayer(new LayerLeftEyeGlow());
    	this.addLayer(new LayerDemonAura(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDarkOpalDemon demon) 
	{
		return DEMON_TEXTURES;
	}

	/**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityDarkOpalDemon demon, float partialTickTime) 
    {
        GlStateManager.rotate(-90, 0, 1, 0);
    }
    
    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityDarkOpalDemon demon, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(demon, x, y, z, entityYaw, partialTicks);
    }
    
    public static class Factory implements IRenderFactory<EntityDarkOpalDemon>
    {
        @Override
        public Render<? super EntityDarkOpalDemon> createRenderFor(RenderManager manager)
        {
            return new RenderDarkOpalDemon(manager);
        }

    }

}
