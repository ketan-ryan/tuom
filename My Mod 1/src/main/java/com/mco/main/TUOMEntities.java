package com.mco.main;

import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.bomb.EntityDarkBomb;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedSheep;
import com.mco.entities.mobs.dark.demon.corrupted.EntityDarkVex;
import com.mco.entities.mobs.dark.demon.laser.EntityLaser;
import com.mco.entities.mobs.dark.demon.laser.EntityLaserProjectile;
import library.LibRegistry;
import library.entities.mobs.models.LibModelChicken;
import library.entities.mobs.models.LibModelSheep;
import library.entities.vehicles.LibModelJet;
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
import com.mco.entities.mobs.dark.demon.laser.EntityLaser;
import com.mco.entities.mobs.dark.demon.laser.EntityLaserProjectile;
import com.mco.entities.mobs.dark.demon.skull.EntityDarkSkull;
import com.mco.entities.mobs.dark.demon.skull.ModelDarkSkull;
import com.mco.entities.projectiles.EntityCustomFallingBlock;
import com.mco.entities.projectiles.EntityTopazArrow;
import com.mco.entities.projectiles.FopalGrenadeEntity;
import com.mco.entities.vehicles.DarkShip;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class TUOMEntities {
	
	private static int mobID = 0;
	
	/**
	 * REGISTER ENTITIES =================================================
	 */
	public static void preInit() {

		/** VEHICLES */

		LibRegistry.registerEntity("dark_ship", DarkShip.class, 0x4e0072, 0x000000);
		
		/** PROJECTILES */

		LibRegistry.registerEntity("topaz_arrow", EntityTopazArrow.class);
		LibRegistry.registerEntity("projectile_grenade_entity", FopalGrenadeEntity.class);
		LibRegistry.registerEntity("dark_skull", EntityDarkSkull.class);
		LibRegistry.registerEntity("falling_block", EntityCustomFallingBlock.class);
		LibRegistry.registerEntity("dark_bomb_projectile", EntityProjectileDarkBomb.class);
		
		/** MOBS */

		LibRegistry.registerEntity("dark_demon", EntityDarkOpalDemon.class, 0x000000, 0x4e0072);
		LibRegistry.registerEntity("corrupted_cow", EntityCorruptedCow.class, 0x6d390a, 0x00000);
		LibRegistry.registerEntity("corrupted_chicken", EntityCorruptedChicken.class, 0xFFFFFF, 0x00000);
		LibRegistry.registerEntity("corrupted_pig", EntityCorruptedPig.class, 0xff82fc, 0x00000);
		LibRegistry.registerEntity("corrupted_sheep", EntityCorruptedSheep.class, 0xdbdde0, 0x00000);
		LibRegistry.registerEntity("dark_vex", EntityDarkVex.class, 0x00000, 0x00000);
		
		LibRegistry.registerEntity("laser", EntityLaser.class);
		LibRegistry.registerEntity("laser_projectile", EntityLaserProjectile.class);
	
		LibRegistry.registerEntity("dark_bomb", EntityDarkBomb.class);
	//	LibRegistry.registerEntity("dark_lightning", EntityDarkLightning.class);
		
	}
	
	@SubscribeEvent
	public static void onEntityRegistry(RegistryEvent.Register<EntityEntry> event)
	{
/*	    event.getRegistry().register(EntityEntryBuilder.create()
	    		.entity(EntityCorruptedCow.class)
                        .factory(EntityCorruptedCow::new)
                        .id(new ResourceLocation(TUOM.MODID, "corrupted_cow"), mobID++)
                        .name(TUOM.MODID + ".corrupted_cow")
                        .tracker(1024, 20, false)
                        .build());*/
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
	

	/**
	 * REGISTER ENTITY SPAWNS =================================================
	 */
	public static void registerEntitySpawns() {

	}

}
