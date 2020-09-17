package com.mco.potions;

import com.mco.TUOM;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid=TUOM.MODID)
public class TUOMPotions 
{
	/** Initialize Dark Potion object and give it a move slowdown */
	public static final Potion DARK_POTION = new DarkPotion(true, 0x00000).setIconIndex(0, 0).setPotionName("Darkness Affliction").registerPotionAttributeModifier(
			SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", -0.40000000298023224D, 2);
	
	/** Initialize PotionType */
	public static final PotionType DARK_POTION_NORMAL = new PotionType(TUOM.MODID + ".darkPotion", new PotionEffect(DARK_POTION, 200));
	
	/**
	 * Register all potions
	 * 
	 * @param registry the IForgeRegistry<Potion> to register to
	 */
	public static void registerPotions(IForgeRegistry<Potion> registry) 
	{
		registerPotion(registry, "dark_potion", DARK_POTION);
	}
	
	/**
	 * Register all potion types
	 * 
	 * @param registry the IForgeRegistry<PotionType> to register to
	 */
	public static void registerPotionTypes(IForgeRegistry<PotionType> registry)
	{
		registerPotionEvent(registry, TUOM.MODID + ".dark_potion", DARK_POTION_NORMAL);
	}
	
	/**
	 * Actually registers potions
	 * 
	 * @param registry the IForgeRegistry<Potion> to register to
	 * @param name the potion's name
	 * @param potion the potion object to register
	 */
	public static <T extends Potion> void registerPotion(IForgeRegistry<Potion> registry, String name, T potion) 
	{
		potion.setRegistryName(TUOM.MODID, name);
		registry.register(potion);
	}
	
	/**
	 * Actually registers potion types
	 * 
	 * @param registry the IForgeRegistry<PotionType> to register to
	 * @param name the PotionType's name
	 * @param potionType the PotionType object to register
	 */
	public static <T extends PotionType> void registerPotionEvent(IForgeRegistry<PotionType> registry, String name, T potionType) 
	{
		potionType.setRegistryName(TUOM.MODID, name);
		registry.register(potionType);
	}
}
