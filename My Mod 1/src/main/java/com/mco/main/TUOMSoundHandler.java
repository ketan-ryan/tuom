package com.mco.main;

import java.util.HashMap;
import java.util.Map;

import com.mco.TUOM;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

public final class TUOMSoundHandler
{	
    public static final Map<String, SoundEvent> SOUND_EVENTS = new HashMap<String, SoundEvent>();
    private static Map<String, ResourceLocation> cachedResources = new HashMap<String, ResourceLocation>();

    public static final SoundEvent DARK_OPAL_SPAWN = new SoundEvent(new ResourceLocation(TUOM.MODID, "darkdemon-spawn"));
    public static final SoundEvent DARK_OPAL_HURT = new SoundEvent(new ResourceLocation(TUOM.MODID, "darkdemon-say"));
    public static final SoundEvent DARK_OPAL_IDLE = new SoundEvent(new ResourceLocation(TUOM.MODID, "darkdemon-hurt"));
    public static final SoundEvent DARK_OPAL_DEATH = new SoundEvent(new ResourceLocation(TUOM.MODID, "darkdemon-death"));
    public static final SoundEvent DARK_OPAL_TAKEOFF = new SoundEvent(new ResourceLocation(TUOM.MODID, "darkdemon-takeoff"));
    public static final SoundEvent DARK_OPAL_LANDING = new SoundEvent(new ResourceLocation(TUOM.MODID, "darkdemon-landing"));
    public static final SoundEvent DARK_OPAL_FLUTTER = new SoundEvent(new ResourceLocation(TUOM.MODID, "darkdemon-flutter"));
    public static final SoundEvent DARK_OPAL_FLAP = new SoundEvent(new ResourceLocation(TUOM.MODID, "darkdemon-flap"));
    public static final SoundEvent DARK_OPAL_WHOOSH = new SoundEvent(new ResourceLocation(TUOM.MODID, "darkdemon-whoosh"));
    public static final SoundEvent DARK_OPAL_BOMB = new SoundEvent(new ResourceLocation(TUOM.MODID, "darkdemon-bomb"));
    public static final SoundEvent DARK_OPAL_BOMB_LAUNCH = new SoundEvent(new ResourceLocation(TUOM.MODID, "darkdemon-bomb-launch"));

    public static final SoundEvent SATAN_SPAWN = new SoundEvent(new ResourceLocation(TUOM.MODID, "satan-spawn"));
    public static final SoundEvent SATAN_IDLE = new SoundEvent(new ResourceLocation(TUOM.MODID, "satan-idle"));
    public static final SoundEvent SATAN_HURT = new SoundEvent(new ResourceLocation(TUOM.MODID, "satan-hurt"));
    public static final SoundEvent SATAN_DEATH = new SoundEvent(new ResourceLocation(TUOM.MODID, "satan-death"));
    
	public static void registerSounds(IForgeRegistry<SoundEvent> registry) 
	{
		registerSound(registry, "darkdemon_spawn", DARK_OPAL_SPAWN);
		registerSound(registry, "darkdemon_hurt", DARK_OPAL_HURT);
		registerSound(registry, "darkdemon_say", DARK_OPAL_IDLE);
		registerSound(registry, "darkdemon_death", DARK_OPAL_DEATH);
		registerSound(registry, "darkdemon_takeoff", DARK_OPAL_TAKEOFF);
		registerSound(registry, "darkdemon_landing", DARK_OPAL_LANDING);
		registerSound(registry, "darkdemon_flutter", DARK_OPAL_FLUTTER);
		registerSound(registry, "darkdemon_flap", DARK_OPAL_FLAP);
		registerSound(registry, "darkdemon_whoosh", DARK_OPAL_WHOOSH);
		registerSound(registry, "darkdemon_bomb", DARK_OPAL_BOMB);
		registerSound(registry, "darkdemon_bomb_launch", DARK_OPAL_BOMB_LAUNCH);

		registerSound(registry, "satan_spawn", SATAN_SPAWN);
		registerSound(registry, "satan_idle", SATAN_IDLE);
		registerSound(registry, "satan_hurt", SATAN_HURT);
		registerSound(registry, "satan_death", SATAN_DEATH);

	}
	
	public static <T extends SoundEvent> void registerSound(IForgeRegistry<SoundEvent> registry, String name, T sound) 
	{
		sound.setRegistryName(name);
		registry.register(sound);
	}
}
