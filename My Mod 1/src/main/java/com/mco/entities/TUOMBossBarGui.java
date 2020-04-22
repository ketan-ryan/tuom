package com.mco.entities;

import java.util.List;

import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;

import com.mco.TUOM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TUOMBossBarGui extends Gui
{
	private Minecraft mc;
	private static ResourceLocation texture = new ResourceLocation(TUOM.MODID, "textures/entities/bossbars/dopal_bossbar.png");
	
	public TUOMBossBarGui(Minecraft minecraft) 
	{
		mc = minecraft;
	}

	@SubscribeEvent
	  public void onRenderOverlay(RenderGameOverlayEvent event)
	  {
	    if (event.isCancelable()) {
	      return;
	    }
	    int u = 0;
	    int v = 0;
	    String outstring = null;
	    int color = 16382457;
	    FontRenderer fr = this.mc.fontRenderer;
	    
	    int barWidth = 182;
	    int namey = 10;
	    int barHeight = 5;
	    int y = 0;
	    float fade = 1.0F;
	    float gfHealth = 0.0F;
	    boolean flag = true;
	    
	    Entity entity = null;
	    EntityPlayer player = null;
	    player = this.mc.player;
	    if (player == null) {
	      return;
	    }
	    List list = player.world.playerEntities;
	    if ((list != null) && (!list.isEmpty())) {
	      for (int j = 0; j < list.size(); j++)
	      {
	        entity = (Entity)list.get(j);
	        if ((entity != null) && (!entity.isDead) && ((entity instanceof EntityLiving)))
	        {
	          EntityLiving e = (EntityLiving)entity;
	         
	          if ((e instanceof EntityDarkOpalDemon))
	          {
	            texture = new ResourceLocation(TUOM.MODID, "textures/bossbars/dopal_bossbar.png");
	            barWidth = 185;
	            barHeight = 27;
	            color = 12369084;
	            y = 0;
	            namey = y + 20;
	            ScaledResolution res = new ScaledResolution(this.mc);
	            int width = res.getScaledWidth();
	            int barWidthFilled = (int)(gfHealth * (barWidth + 1));
	            int x = width / 2 - barWidth / 2;
	            if (flag) {
	          //    fr.drawStringWithShadow((int)e.func_110143_aJ() + "/" + (int)e.func_110138_aP(), width / 2 - fr.func_78256_a((int)e.func_110143_aJ() + "/" + (int)e.func_110138_aP()) / 2, namey + 10, 16382457);
	            }
	          }
	          
	        }
	      }
	    }
	  }
	
}
