package com.mco.events;

import java.util.List;

import com.mco.TUOM;
import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.proxies.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = TUOM.MODID, value = Side.CLIENT)
public class TUOMClientEventHandler 
{
	private static Minecraft mc = Minecraft.getMinecraft();
	private static EntityDarkOpalDemon demon;
	private static final ResourceLocation darkDeathOverlay = new ResourceLocation("tuom:textures/entities/dark.png");
	private static final ResourceLocation icons = new ResourceLocation("tuom:textures/icons.png");
	
	/**
	 * Handles death overlay for Dark Opal Demon - finds one nearby then applies the overlay if it's in the death anim 
	 * 
	 * @param event the RenderGameOverlay event
	 */
	@SubscribeEvent
	public static void onRenderOverlay(RenderGameOverlayEvent.Post event)
	{
		EntityPlayer player = ClientProxy.getClientPlayer();
		float i = 1;
		List<EntityDarkOpalDemon> list = player.world.<EntityDarkOpalDemon>getEntitiesWithinAABB(EntityDarkOpalDemon.class, 
        		player.getEntityBoundingBox().grow(30));
		for(EntityDarkOpalDemon demon : list)
		{
			if(demon != null && demon.getDeathTicks() > 1 && demon.getDeathTicks() < 200)
			{
				if(player != null)
					i = demon.getDistance(player);
				//Setup GL methods
				GlStateManager.enableAlpha();
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
				//Set to black with progressively higher alpha
				GlStateManager.color(1, 1, 1, demon.getDarknessAlpha() / (i));
				//OSetup texture and size
				mc.getTextureManager().bindTexture(darkDeathOverlay);
				ScaledResolution res = event.getResolution();
				//Draw the overlay
				Gui.drawModalRectWithCustomSizedTexture(0,  0,  0,  0, res.getScaledWidth(), res.getScaledHeight(), 
						res.getScaledWidth(), res.getScaledHeight());
			}
		}
	}
	
}
