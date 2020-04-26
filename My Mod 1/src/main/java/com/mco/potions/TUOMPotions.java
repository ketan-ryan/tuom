package com.mco.potions;

import com.mco.TUOM;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid=TUOM.MODID)
public class TUOMPotions 
{

	public static final Potion DARK_POTION = new DarkPotion(true, 0x00000).setIconIndex(0, 0).setPotionName("Darkness Affliction").registerPotionAttributeModifier(
			SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", -0.40000000298023224D, 2).setRegistryName(TUOM.MODID, "darkPotion");
	public static final PotionType DARK_POTION_NORMAL = new PotionType(TUOM.MODID + ".darkPotion", new PotionEffect(DARK_POTION, 200)).setRegistryName(TUOM.MODID, "darkPotion");
	
	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) 
	{
		event.getRegistry().register(DARK_POTION);
	}
	
	@SubscribeEvent
	public static void registerPotionTypes(RegistryEvent.Register<PotionType> event)
	{
		event.getRegistry().register(DARK_POTION_NORMAL);
	}
}
