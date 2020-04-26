package com.mco.potions;

import com.mco.TUOM;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class DarkPotion extends Potion 
{

	public static final ResourceLocation icon = new ResourceLocation("tuom:textures/gui/dark.png");
	
	public DarkPotion(boolean isBadEffectIn, int liquidColorIn) 
	{
		super(isBadEffectIn, liquidColorIn);
	}

	public Potion setIconIndex(int x, int y){
		super.setIconIndex(x, y);
		return(Potion) this;
	}

	public int getStatusIconIndex(){
		ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(icon);
		Minecraft.getMinecraft().renderEngine.bindTexture(icon);
		return super.getStatusIconIndex();
	}
	
	public boolean isBadEffect(){
		return true;
	}
	
}
