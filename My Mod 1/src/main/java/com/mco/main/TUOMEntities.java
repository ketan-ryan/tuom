package com.mco.main;

import com.mco.TUOM;
import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.ModelDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.bomb.EntityDarkBomb;
import com.mco.entities.mobs.dark.demon.bomb.EntityProjectileDarkBomb;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedChicken;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedCow;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedPig;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedSheep;
import com.mco.entities.mobs.dark.demon.corrupted.EntityDarkVex;
import com.mco.entities.mobs.dark.demon.corrupted.ModelDarkVex;
import com.mco.entities.mobs.dark.demon.laser.EntityLaserProjectile;
import com.mco.entities.mobs.dark.demon.skull.EntityDarkSkull;
import com.mco.entities.mobs.dark.demon.skull.ModelDarkSkull;
import com.mco.entities.projectiles.EntityCustomFallingBlock;
import com.mco.entities.projectiles.EntityTopazArrow;
import com.mco.entities.projectiles.FopalGrenadeEntity;
import com.mco.entities.vehicles.DarkShip;

import library.LibRegistry;
import library.entities.mobs.models.LibModelChicken;
import library.entities.mobs.models.LibModelSheep;
import library.entities.vehicles.LibModelJet;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = TUOM.MODID)
public class TUOMEntities {
	
	private static int mobID = 0;
		
	private static final EntityEntry DARK_SHIP = EntityEntryBuilder.create()
																.entity(DarkShip.class)
																.id(new ResourceLocation(TUOM.MODID, "dark_ship"), mobID++)
																.name("dark_ship")
																//range, update frequency, should send velocity updates
																.tracker(64, 3, true)
																.egg(0x4e0072, 0x000000)
																.build();
	
	private static final EntityEntry TOPAZ_ARROW = EntityEntryBuilder.create()
																.entity(EntityTopazArrow.class)
																.id(new ResourceLocation(TUOM.MODID, "topaz_arrow"), mobID++)
																.name("topaz_arrow")
																.tracker(64, 3, true)
																.build();
	
	private static final EntityEntry FOPAL_GRENADE = EntityEntryBuilder.create()	
																.entity(FopalGrenadeEntity.class)
																.id(new ResourceLocation(TUOM.MODID, "fopal_grenade"), mobID++)
																.name("fopal_grenade")
																.tracker(64, 3, true)
																.build();
	
	private static final EntityEntry DARK_SKULL = EntityEntryBuilder.create()
																.entity(EntityDarkSkull.class)
																.id(new ResourceLocation(TUOM.MODID, "dark_skull"), mobID++)
																.name("dark_skull")
																.tracker(64, 3, true)
																.build();

	private static final EntityEntry FALLING_BLOCK = EntityEntryBuilder.create()
																.entity(EntityCustomFallingBlock.class)
																.id(new ResourceLocation(TUOM.MODID, "falling_block"), mobID++)
																.name("falling_block")
																.tracker(64, 3, true)
																.build();
																
	private static final EntityEntry DARK_BOMB_PROJECTILE = EntityEntryBuilder.create()
																.entity(EntityDarkBomb.class)
																.id(new ResourceLocation(TUOM.MODID, "dark_bomb_projectile"), mobID++)
																.name("dark_bomb_projectile")
																.tracker(64, 3, true)
																.build();
	
	private static final EntityEntry DARK_DEMON = EntityEntryBuilder.create()
																.entity(EntityDarkOpalDemon.class)
																.id(new ResourceLocation(TUOM.MODID, "dark_demon"), mobID++)
																.name("dark_demon")
																.tracker(80, 3, true)
																.egg(0x000000, 0x4e0072)
																.build();
	
	private static final EntityEntry CORRUPTED_COW = EntityEntryBuilder.create()
																.entity(EntityCorruptedCow.class)
																.id(new ResourceLocation(TUOM.MODID, "corrupted_cow"), mobID++)
																.name("corrupted_cow")
																.tracker(64, 3, false)
																.egg(0x6d390a, 0x00000)
																.spawn(EnumCreatureType.CREATURE, 15, 1, 3, TUOMBiomes.dark_forest, TUOMBiomes.dark_mountains, TUOMBiomes.dark_plains)
																.build();
	
	private static final EntityEntry CORRUPTED_CHICKEN = EntityEntryBuilder.create()	
																.entity(EntityCorruptedChicken.class)
																.id(new ResourceLocation(TUOM.MODID, "corrupted_chicken"), mobID++)
																.name("corrupted_chicken")
																.tracker(64, 3, false)
																.egg(0xFFFFFF, 0x00000)
																.spawn(EnumCreatureType.CREATURE, 15, 1, 3, TUOMBiomes.dark_forest, TUOMBiomes.dark_mountains, TUOMBiomes.dark_plains)
																.build();
	
	private static final EntityEntry CORRUPTED_PIG = EntityEntryBuilder.create()
																.entity(EntityCorruptedPig.class)
																.id(new ResourceLocation(TUOM.MODID, "corrupted_pig"), mobID++)
																.name("corrupted_pig")
																.tracker(64, 3, false)
																.egg(0xff82fc, 0x00000)
																.spawn(EnumCreatureType.CREATURE, 15, 1, 3, TUOMBiomes.dark_forest, TUOMBiomes.dark_mountains, TUOMBiomes.dark_plains)
																.build();
	
	private static final EntityEntry CORRUPTED_SHEEP = EntityEntryBuilder.create()
																.entity(EntityCorruptedSheep.class)
																.id(new ResourceLocation(TUOM.MODID, "corrupted_sheep"), mobID++)
																.name("corrupted_sheep")
																.tracker(64, 3, false)
																.egg(0xdbdde0, 0x00000)
																.spawn(EnumCreatureType.CREATURE, 15, 1, 3, TUOMBiomes.dark_forest, TUOMBiomes.dark_mountains, TUOMBiomes.dark_plains)
																.build();
	
	private static final EntityEntry DARK_VEX = EntityEntryBuilder.create()
																.entity(EntityDarkVex.class)
																.id(new ResourceLocation(TUOM.MODID, "dark_vex"), mobID++)
																.name("dark_vex")
																.tracker(64, 3, false)
																.egg(0x00000, 0x00000)
																.spawn(EnumCreatureType.CREATURE, 15, 1, 3, TUOMBiomes.dark_forest, TUOMBiomes.dark_mountains, TUOMBiomes.dark_plains)
																.build();
	
	private static final EntityEntry DARK_BOMB = EntityEntryBuilder.create()
																.entity(EntityDarkBomb.class)
																.id(new ResourceLocation(TUOM.MODID, "dark_bomb"), mobID++)
																.name("dark_bomb")
																.tracker(64, 3, true)
																.build();
	
	public static void registerEntities(IForgeRegistry<EntityEntry> registry) 
	{
		registerEntity(registry, "dark_ship", DARK_SHIP);
		registerEntity(registry, "topaz_arrow", TOPAZ_ARROW);
		registerEntity(registry, "fopal_grenade", FOPAL_GRENADE);
		registerEntity(registry, "dark_skull", DARK_SKULL);
		registerEntity(registry, "dark_bomb_projectile", DARK_BOMB_PROJECTILE);
		registerEntity(registry, "falling_block", FALLING_BLOCK);
		
		registerEntity(registry, "dark_demon", DARK_DEMON);
		registerEntity(registry, "corrupted_cow", CORRUPTED_COW);
		registerEntity(registry, "corrupted_chicken", CORRUPTED_CHICKEN);
		registerEntity(registry, "corrupted_pig", CORRUPTED_PIG);
		registerEntity(registry, "corrupted_sheep", CORRUPTED_SHEEP);
		registerEntity(registry, "dark_vex", DARK_VEX);
		registerEntity(registry, "dark_bomb", DARK_BOMB);
	}
	
	public static <T extends EntityEntry> void registerEntity(IForgeRegistry<EntityEntry> registry, String name, T entityEntry) {
		entityEntry.setRegistryName(TUOM.MODID, name);
		registry.register(entityEntry);
	}

	/**
	 * REGISTER ENTITY MODELS =================================================
	 */
	@SideOnly(Side.CLIENT)
	public static void registerEntityModels() {

		/** VEHICLES */

		LibRegistry.registerVehicleModel(DarkShip.class, LibModelJet.class, 1F);
		
		/** PROJECTILES */

		LibRegistry.registerProjectileModel(EntityTopazArrow.class);
		LibRegistry.registerProjectileSprite(FopalGrenadeEntity.class, 1F);
		LibRegistry.registerProjectileSprite(EntityProjectileDarkBomb.class, 1);
		LibRegistry.registerProjectileSprite(EntityLaserProjectile.class, 1);
		LibRegistry.registerProjectileCustomModel(EntityDarkSkull.class, ModelDarkSkull.class, 1);
		
		/** MOBS */

		LibRegistry.registerMobModel(EntityDarkOpalDemon.class, ModelDarkOpalDemon.class, 1F);
		LibRegistry.registerMobModel(EntityCorruptedCow.class, ModelCow.class, 1.25F);        
		LibRegistry.registerMobModel(EntityCorruptedChicken.class, LibModelChicken.class, 1.25F);
		LibRegistry.registerMobModel(EntityCorruptedPig.class, ModelPig.class, 1.25F);        
		LibRegistry.registerMobModel(EntityCorruptedSheep.class, LibModelSheep.class, 1.25F);
		LibRegistry.registerMobModel(EntityDarkVex.class, ModelDarkVex.class, 2F);
	}
}
