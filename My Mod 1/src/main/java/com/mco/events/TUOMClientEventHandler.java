package com.mco.events;

import com.mco.TUOM;
import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.items.armor.DopalArmor;
import com.mco.main.TUOMConfig;
import com.mco.proxies.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.BossInfoClient;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.opengl.GL11;

import java.util.List;

@Mod.EventBusSubscriber(modid = TUOM.MODID, value = Side.CLIENT)
public class TUOMClientEventHandler
{
	/** Minecraft Instance */
	private static Minecraft mc = Minecraft.getMinecraft();

	/** Darkness screen overlay */
	private static final ResourceLocation DARK_DEATH_OVERLAY = new ResourceLocation("tuom:textures/entities/dark.png");
	/** Dark Opal Demon bossbar texture */
	private static final ResourceLocation DARK_BAR = new ResourceLocation(TUOM.MODID, "textures/bossbars/dopal_bossbar_512.png");

	/**
	 * Handles flight when dark armor equipped
	 * @param event the ClientTickEvent to listen to
	 */
	@SubscribeEvent
	public static void clientTick(TickEvent.ClientTickEvent event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		if(mc.gameSettings.keyBindJump.isKeyDown() && DopalArmor.hasFullSet() && !mc.player.isCreative() )
		{
			ClientProxy.getClientPlayer().motionY += 0.1;
			ClientProxy.getClientPlayer().motionX *= 1.01;
			ClientProxy.getClientPlayer().motionZ *= 1.01;
		}
	}

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
				mc.getTextureManager().bindTexture(DARK_DEATH_OVERLAY);
				ScaledResolution res = event.getResolution();
				//Draw the overlay
				Gui.drawModalRectWithCustomSizedTexture(0,  0,  0,  0, res.getScaledWidth(), res.getScaledHeight(),
						res.getScaledWidth(), res.getScaledHeight());
			}
		}
	}

	/**
	 * Handles the custom bossbar for the Dark Opal Demon
	 * Thanks to Xwancool for the texture!
	 * This method was originally written by the Betweenlands devs.
	 *
	 * @param event the bossinfo render event
	 */
	@SubscribeEvent
	public static void onRenderOverlay(RenderGameOverlayEvent.BossInfo event)
	{
		Entity boss = null;
		//Get instance of Minecraft and check all loaded entities
		for(Entity entity : mc.world.loadedEntityList) {
			if(entity instanceof EntityDarkOpalDemon) {
				//If we have a dark opal demon that can see the player
				if(boss == null || entity.getDistance(mc.player) < boss.getDistance(mc.player))
					boss = entity;
			}
		}
		if(boss != null && boss instanceof EntityDarkOpalDemon)
		{
			BossInfoClient info = event.getBossInfo();
			float percent = info.getPercent();
			ITextComponent name = info.getName();
			//We only want to cancel the event if it's a DoD
			//The cast should ensure it works if translated
			if(((EntityDarkOpalDemon)boss).getName().equals(name.getUnformattedText()))
			{
				event.setCanceled(true);

				//Set up values from config
				int texWidth = TUOMConfig.bossbarWidth;
				int texHeight = TUOMConfig.bossbarHeight/2;
				event.setIncrement(texHeight + 2);
				double renderWidth = TUOMConfig.bossbarRenderWidth;
				double renderHeight = (double)texHeight / (double)texWidth * renderWidth;
				double renderHealth  = (renderWidth - 16.0F / texWidth * renderWidth - (renderWidth - 16.0F / texWidth * renderWidth) * percent);

				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				mc.getTextureManager().bindTexture(DARK_BAR);
				//Old rendering code
				GlStateManager.enableBlend();

				GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GlStateManager.pushMatrix();
				GlStateManager.translate(event.getResolution().getScaledWidth() / 2 - renderWidth / 2.0D, event.getY() - 2, 0);
				GlStateManager.glBegin(GL11.GL_QUADS);
				//Background
				GlStateManager.glTexCoord2f(0, 0);
				GL11.glVertex2d(0, 0);
				GlStateManager.glTexCoord2f(0, 0.5F);
				GL11.glVertex2d(0, renderHeight);
				GlStateManager.glTexCoord2f(1, 0.5F);
				GL11.glVertex2d(renderWidth, renderHeight);
				GlStateManager.glTexCoord2f(1, 0);
				GL11.glVertex2d(renderWidth, 0);
				//Foreground
				if (percent > 0)
				{
					GlStateManager.glTexCoord2f(0, 0.5F);
					GL11.glVertex2d(0, 0);
					GlStateManager.glTexCoord2f(0, 1.0F);
					GL11.glVertex2d(0, renderHeight);
					GlStateManager.glTexCoord2f(16.0F / texWidth + (1.0F - 16.0F / texWidth) * percent, 1.0F);
					GL11.glVertex2d(renderWidth - renderHealth, renderHeight);
					GlStateManager.glTexCoord2f(16.0F / texWidth + (1.0F - 16.0F / texWidth) * percent, 0.5F);
					GL11.glVertex2d(renderWidth - renderHealth, 0);
				}
				GlStateManager.glEnd();
				GlStateManager.popMatrix();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				int strWidth = mc.fontRenderer.getStringWidth(name.getFormattedText());
				//Draw name with offset
				mc.fontRenderer.drawStringWithShadow(name.getFormattedText(), event.getResolution().getScaledWidth() / 2  - strWidth / 2, event.getY() + TUOMConfig.textOffset, 0x8800ff);
			}
		}
	}

}
