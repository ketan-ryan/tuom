package com.mco.main;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Bootstrap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public final class TUOMSoundHandler 
{	
    public static final Map<String, SoundEvent> SOUND_EVENTS = new HashMap<String, SoundEvent>();
    private static Map<String, ResourceLocation> cachedResources = new HashMap<String, ResourceLocation>();

    public static final SoundEvent darkOpalSpawn;
    public static final SoundEvent darkOpalHurt;
    public static final SoundEvent darkOpalIdle;
    public static final SoundEvent darkOpalDeath;
    public static final SoundEvent darkOpalTakeoff;
    public static final SoundEvent darkOpalLanding;
    public static final SoundEvent darkOpalFlutter;
    public static final SoundEvent darkOpalFlap;
    public static final SoundEvent darkOpalWhoosh;
    public static final SoundEvent darkOpalBomb;
    public static final SoundEvent darkOpalBombLaunch;
   
    static {
        if (!Bootstrap.isRegistered()) {
            throw new RuntimeException("Accessed Sounds before Bootstrap!");
        }
        else {
            darkOpalSpawn = getRegisteredSoundEvent("tuom:darkdemon-spawn");
            darkOpalIdle = getRegisteredSoundEvent("tuom:darkdemon-say");
            darkOpalHurt = getRegisteredSoundEvent("tuom:darkdemon-hurt");
            darkOpalDeath = getRegisteredSoundEvent("tuom:darkdemon-death");
            darkOpalTakeoff = getRegisteredSoundEvent("tuom:darkdemon-takeoff");
            darkOpalLanding = getRegisteredSoundEvent("tuom:darkdemon-landing");
            darkOpalFlutter = getRegisteredSoundEvent("tuom:darkdemon-flutter");
            darkOpalFlap = getRegisteredSoundEvent("tuom:darkdemon-flap");
            darkOpalWhoosh = getRegisteredSoundEvent("tuom:darkdemon-whoosh");
            darkOpalBomb = getRegisteredSoundEvent("tuom:darkdemon-bomb");
            darkOpalBombLaunch = getRegisteredSoundEvent("tuom:darkdemon-bomb-launch");
        }
    }

    private static SoundEvent getRegisteredSoundEvent(String id) {
        SoundEvent soundevent = new SoundEvent(new ResourceLocation(id));

        if (soundevent == null) {
            throw new IllegalStateException("Invalid Sound requested: " + id);
        }
        else {
            SOUND_EVENTS.put(id, soundevent);
            return soundevent;
        }
    }
    
    public static ResourceLocation getResourceRAW(String rs) {
        if (!cachedResources.containsKey(rs)) {
            cachedResources.put(rs, new ResourceLocation(rs));
        }
        return cachedResources.get(rs);
    }
    
    public static SoundEvent getSound(String id) {
        if (SOUND_EVENTS.containsKey(id)) {
            return SOUND_EVENTS.get(id);
        }
        else if (SoundEvent.REGISTRY.containsKey(getResourceRAW(id))) {
            return SoundEvent.REGISTRY.getObject(getResourceRAW(id));
        }
        else {
            return null;
        }
    }        

}
