package com.mco.main;

import com.mco.TUOM;
import com.mco.potions.TUOMPotions;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod.EventBusSubscriber(modid = TUOM.MODID)
public class RegistryEventHandler 
{

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) 
	{
		TUOMItems.registerItems(event.getRegistry());
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		TUOMBlocks.registerBlocks(event.getRegistry());
	}
	
	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event)
	{
		TUOMBiomes.registerBiomes(event.getRegistry());
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event) 
	{
		TUOMEntities.registerEntities(event.getRegistry());
	}
	
	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event)
	{
		TUOMPotions.registerPotions(event.getRegistry());
	}
	
	@SubscribeEvent
	public static void registerPotionTypes(RegistryEvent.Register<PotionType> event)
	{
		TUOMPotions.registerPotionTypes(event.getRegistry());
	}
	
	@SubscribeEvent
	public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event)
	{
		TUOMSoundHandler.registerSounds(event.getRegistry());
	}
}
