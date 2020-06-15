package com.mco.main;

import com.mco.TUOM;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid=TUOM.MODID)
public class TUOMConfig 
{
	@Config.Comment("How much HP the Dark Opal Demon will start with (Won't affect existing)")
	@Config.Name("Dark Health")
	public static float darkHealth = 2000F;
	
	@Config.Comment("Damage modifier for the Dark Opal Demon's attacks")
	@Config.Name("Dark Opal Demon Damage Modifier")
	public static float darkDamage = 1.0F;
	
	@Config.Comment("Can Dark Opal Demon adjust the time of day")
	@Config.Name("Can Adjust Day")
	public static boolean darkDay = true;
	
	@Config.Name("dimension")
	public static final Dimension DIMENSION = new Dimension();
	
	public static class Dimension
	{
		@Config.Name("Dimension ID")
		@Config.Comment("By default, we will automatically try to find an unused dimension ID. If you change this, it will use your value instead.")
		@Config.RequiresMcRestart
		
		public static int opalDimID = 0;
	}
	
	@Mod.EventBusSubscriber(modid=TUOM.MODID)
	private static class EventHandler
	{
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) 
		{
			if(event.getModID().equals(TUOM.MODID)) 
			{
				ConfigManager.sync(TUOM.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
