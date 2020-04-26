package com.mco.main;

import com.mco.items.archery.Topaz_Bow;
import com.mco.items.armor.DopalArmor;
import com.mco.items.armor.FopalArmor;
import com.mco.items.armor.SatanicArmor;
import com.mco.items.basic.ItemGarnet;
import com.mco.items.basic.ItemSatanic;
import com.mco.items.basic.ItemTopaz;
import com.mco.items.basic.shards.FopalShard;
import com.mco.items.basic.shards.TopazShard;
import com.mco.items.crops.DopalSeeds;
import com.mco.items.crops.LopalSeeds;
import com.mco.items.crops.TopazSeeds;
import com.mco.items.food.FopalApple;
import com.mco.items.food.TopazApple;
import com.mco.items.projectiles.ProjectileGrenade;
import com.mco.items.teleporters.DarkOpalTeleporterStaff;
import com.mco.items.tools.dark.DopalAxe;
import com.mco.items.tools.dark.DopalShovel;
import com.mco.items.tools.dark.DopalSword;
import com.mco.items.tools.fopal.FopalAxe;
import com.mco.items.tools.fopal.FopalPickaxe;
import com.mco.items.tools.fopal.FopalSword;
import com.mco.items.tools.garnet.GarnetHoe;
import com.mco.items.tools.light.LopalHoe;
import com.mco.items.tools.light.LopalPickaxe;
import com.mco.items.tools.light.LopalShovel;
import com.mco.items.tools.topaz.TopazAxe;
import com.mco.items.tools.topaz.TopazHoe;
import library.LibRegistry;
import library.items.LibItemSimple;

import com.mco.TUOM;
import com.mco.items.archery.TopazArrow;
import com.mco.items.armor.GarnetArmor;
import com.mco.items.armor.LopalArmor;
import com.mco.items.armor.TopazArmor;
import com.mco.items.basic.ItemDopal;
import com.mco.items.basic.ItemFopal;
import com.mco.items.basic.ItemLopal;
import com.mco.items.basic.shards.DopalShard;
import com.mco.items.basic.shards.LopalShard;
import com.mco.items.crops.FopalSeeds;
import com.mco.items.food.DopalApple;
import com.mco.items.food.LopalApple;
import com.mco.items.tools.dark.DarkStaff;
import com.mco.items.tools.dark.DopalHoe;
import com.mco.items.tools.dark.DopalPickaxe;
import com.mco.items.tools.fopal.FopalHoe;
import com.mco.items.tools.fopal.FopalShovel;
import com.mco.items.tools.garnet.GarnetAxe;
import com.mco.items.tools.garnet.GarnetPickaxe;
import com.mco.items.tools.garnet.GarnetShovel;
import com.mco.items.tools.garnet.GarnetSword;
import com.mco.items.tools.light.LopalAxe;
import com.mco.items.tools.light.LopalSword;
import com.mco.items.tools.topaz.TopazPickaxe;
import com.mco.items.tools.topaz.TopazShovel;
import com.mco.items.tools.topaz.TopazSword;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;

/**
 * DECLARE ITEMS =================================================
 */
@Mod.EventBusSubscriber(modid = TUOM.MODID)
@ObjectHolder(TUOM.MODID)
public class TUOMItems {

	//Items
	public static Item item_garnet;
	public static Item item_topaz;
	public static Item fire_opal;
	public static Item dark_opal;
	public static Item light_opal;
	public static Item item_satanic;
	
	public static Item topaz_shard;
	public static Item fopal_shard;
	public static Item lopal_shard;
	public static Item dopal_shard;
	
	//Tools
	public static Item garnet_sword;
	public static Item garnet_pickaxe;
	public static Item garnet_hoe;
	public static Item garnet_axe;
	public static Item garnet_shovel;
	
	public static Item topaz_sword;
	public static Item topaz_pickaxe;
	public static Item topaz_hoe;
	public static Item topaz_axe;
	public static Item topaz_shovel;
	
	public static Item fopal_sword;
	public static Item fopal_pickaxe;
	public static Item fopal_hoe;
	public static Item fopal_axe;
	public static Item fopal_shovel;
	
	public static Item dopal_sword;
	public static Item dopal_pickaxe;
	public static Item dopal_hoe;
	public static Item dopal_axe;
	public static Item dopal_shovel;
	
	public static Item lopal_sword;
	public static Item lopal_pickaxe;
	public static Item lopal_hoe;
	public static Item lopal_axe;
	public static Item lopal_shovel;
	
	//Magic
	public static Item dark_staff;
	
	//Archery and Projectiles
	public static Item topaz_arrow;
	public static Item topaz_bow;
	public static Item projectile_grenade;
	
	//Tool Materials
	public static ToolMaterial topaz_TM;
	public static ToolMaterial fopal_TM;
	public static ToolMaterial dopal_TM;
	public static ToolMaterial lopal_TM;
	public static ToolMaterial garnet_TM;
	public static ToolMaterial satanic_TM;
	public static ToolMaterial dopal_staff_TM;
	public static ToolMaterial light_sword_TM;
	
	//Armor Materials
	public static ArmorMaterial topaz_AM;
	public static ArmorMaterial fopal_AM;
	public static ArmorMaterial dopal_AM;
	public static ArmorMaterial lopal_AM;
	public static ArmorMaterial garnet_AM;
	public static ArmorMaterial satanic_AM;
	
	//Armor Sets
	public static Item topaz_boots;
	public static Item topaz_leggings;
	public static Item topaz_chestplate;
	public static Item topaz_helmet;
	
	public static Item dopal_boots;
	public static Item dopal_leggings;
	public static Item dopal_chestplate;
	public static Item dopal_helmet;
	
	public static Item fopal_boots;
	public static Item fopal_leggings;
	public static Item fopal_chestplate;
	public static Item fopal_helmet;
	
	public static Item lopal_boots;
	public static Item lopal_leggings;
	public static Item lopal_chestplate;
	public static Item lopal_helmet;
	
	public static Item garnet_boots;
	public static Item garnet_leggings;
	public static Item garnet_chestplate;
	public static Item garnet_helmet;
	
	public static Item satanic_boots;
	public static Item satanic_leggings;
	public static Item satanic_chestplate;
	public static Item satanic_helmet;
	
	//Crops
	public static Item topaz_seeds;
	public static Item fopal_seeds;
	public static Item lopal_seeds;
	public static Item dopal_seeds;
	
	//Foods
	public static Item topaz_apple;
	public static Item dopal_apple;
	public static Item fopal_apple;
	public static Item lopal_apple;
	public static Item candy_cane;

	//Dimensions
	public static Item dark_orb;
	public static Item obsidian_stick;
	public static Item dark_teleporter;
	
	public static void preInit() 
	{
		//Tool Material Registry
		topaz_TM = LibRegistry.registerToolMaterial("Topaz", 3, 1600, 10F, 6F, 10);
		fopal_TM = LibRegistry.registerToolMaterial("Fire Opal", 3, 1700, 11.0F, 8.0F, 10);
	    dopal_TM = LibRegistry.registerToolMaterial("Dark Opal", 3, 1800, 12.0F, 10.0F, 10);
	    lopal_TM = LibRegistry.registerToolMaterial("Light Opal", 3, 1900, 13.0F, 12.0F, 10);
	    garnet_TM = LibRegistry.registerToolMaterial("Garnet", 2, 150, 5.0F, 2.5F, 15);
	    satanic_TM = LibRegistry.registerToolMaterial("Satan", 3, 666, 5.0F, 6F, 7);
	    dopal_staff_TM = LibRegistry.registerToolMaterial("Staff", 3, 7500, 5.0F, 13F, 30);
	    light_sword_TM = LibRegistry.registerToolMaterial("Sword", 3, -1, 5.0F, 12.5F, 35);
		
		//Armor Material Registry
		topaz_AM = LibRegistry.registerArmorMaterial("topaz_AM", 43, new int[]{4, 8, 7, 4}, 27, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F);
		fopal_AM = LibRegistry.registerArmorMaterial("Fire Opal", 53, new int[]{5, 9, 8, 5}, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F);  
	    dopal_AM = LibRegistry.registerArmorMaterial("Dark Opal", 63, new int[]{6, 10, 9, 6}, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F);   
	    lopal_AM = LibRegistry.registerArmorMaterial("Light Opal", 73, new int[]{7, 11, 10, 7}, 33, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F);   
	    garnet_AM = LibRegistry.registerArmorMaterial("Garnet", 20, new int[]{3, 7, 6, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F);   
	    satanic_AM = LibRegistry.registerArmorMaterial("Satan", 666, new int[]{8, 13, 11, 8}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F);
		
	    //Item Registry
	    item_garnet = new ItemGarnet("item_garnet");
		item_topaz = new ItemTopaz("item_topaz");
		fire_opal = new ItemFopal("fire_opal");
		dark_opal = new ItemDopal("dark_opal");
		light_opal = new ItemLopal("light_opal");
		topaz_shard = new TopazShard("topaz_shard");
		lopal_shard = new LopalShard("lopal_shard");
		dopal_shard = new DopalShard("dopal_shard");
		fopal_shard = new FopalShard("fopal_shard");
		item_satanic = new ItemSatanic("item_satanic");
		
		//Food Registry
		topaz_apple = new TopazApple("topaz_apple", 3, 5, false);
		dopal_apple = new DopalApple("dopal_apple", 3, 5, false);
		fopal_apple = new FopalApple("fopal_apple", 3, 5, false);
		lopal_apple = new LopalApple("lopal_apple", 3, 5, false);
		
		//Staff Registry
		dark_staff = new DarkStaff("dark_staff", dopal_staff_TM);
		
		//Sword Registry
		garnet_sword = new GarnetSword("garnet_sword", garnet_TM);
		topaz_sword = new TopazSword("topaz_sword", topaz_TM);
		fopal_sword = new FopalSword("fopal_sword", fopal_TM);
		dopal_sword = new DopalSword("dopal_sword", dopal_TM);
		lopal_sword = new LopalSword("lopal_sword", lopal_TM);
		
		//Pickaxe Registry
		garnet_pickaxe = new GarnetPickaxe("garnet_pickaxe", 1, -2.8F, garnet_TM);
		topaz_pickaxe = new TopazPickaxe("topaz_pickaxe", 1, -2.8F, topaz_TM);
		fopal_pickaxe = new FopalPickaxe("fopal_pickaxe", 1, -2.8F, fopal_TM);
		dopal_pickaxe = new DopalPickaxe("dopal_pickaxe", 1, -2.8F, dopal_TM);
		lopal_pickaxe = new LopalPickaxe("lopal_pickaxe", 1, -2.8F, lopal_TM);
		
		//Axe Registry
		garnet_axe = new GarnetAxe("garnet_axe", 2, -3, garnet_TM);
		topaz_axe = new TopazAxe("topaz_axe", 2, -3, topaz_TM);
		fopal_axe = new FopalAxe("fopal_axe", 2, -3, fopal_TM);
		dopal_axe = new DopalAxe("dopal_axe", 2, -3, dopal_TM);
		lopal_axe = new LopalAxe("lopal_axe", 2, -3, lopal_TM);
		
		//Hoe Registry
		garnet_hoe = new GarnetHoe("garnet_hoe", -1, 0, garnet_TM);
		topaz_hoe = new TopazHoe("topaz_hoe", -1, 0, topaz_TM);
		fopal_hoe = new FopalHoe("fopal_hoe", -1, 0, fopal_TM);
		dopal_hoe = new DopalHoe("dopal_hoe", -1, 0, dopal_TM);
		lopal_hoe = new LopalHoe("lopal_hoe", -1, 0, lopal_TM);
		
		//Shovel Registry
		garnet_shovel = new GarnetShovel("garnet_shovel", 0, -3, garnet_TM);
		topaz_shovel = new TopazShovel("topaz_shovel", 0, -3, topaz_TM);
		fopal_shovel = new FopalShovel("fopal_shovel", 0, -3, fopal_TM);
		dopal_shovel = new DopalShovel("dopal_shovel", 0, -3, dopal_TM);
		lopal_shovel = new LopalShovel("lopal_shovel", 0, -3, lopal_TM);		
		
		//Arrow Registry
		topaz_arrow = new TopazArrow("topaz_arrow");		
		
		//Projectile Registry
		projectile_grenade = new ProjectileGrenade("projectile_grenade");
		
		//Bow Registry 
		topaz_bow = new Topaz_Bow("topaz_bow");
		
		//Seeds
		topaz_seeds = new TopazSeeds("topaz_seeds", TUOMBlocks.topaz_crop);
		fopal_seeds = new FopalSeeds("fopal_seeds", TUOMBlocks.fopal_crop);
		lopal_seeds = new LopalSeeds("lopal_seeds", TUOMBlocks.lopal_crop);
		dopal_seeds = new DopalSeeds("dopal_seeds", TUOMBlocks.dopal_crop);
		
		//Armor Registry
		topaz_boots = new TopazArmor("topaz_boots", topaz_AM, EntityEquipmentSlot.FEET);
		topaz_leggings = new TopazArmor("topaz_leggings", topaz_AM, EntityEquipmentSlot.LEGS);
		topaz_chestplate = new TopazArmor("topaz_chestplate", topaz_AM, EntityEquipmentSlot.CHEST);
		topaz_helmet = new TopazArmor("topaz_helmet", topaz_AM, EntityEquipmentSlot.HEAD);
		
		dopal_boots = new DopalArmor("dopal_boots", dopal_AM, EntityEquipmentSlot.FEET);
		dopal_leggings = new DopalArmor("dopal_leggings", dopal_AM, EntityEquipmentSlot.LEGS);
		dopal_chestplate = new DopalArmor("dopal_chestplate", dopal_AM, EntityEquipmentSlot.CHEST);
		dopal_helmet = new DopalArmor("dopal_helmet", dopal_AM, EntityEquipmentSlot.HEAD);
		
		fopal_boots = new FopalArmor("fopal_boots", fopal_AM, EntityEquipmentSlot.FEET);
		fopal_leggings = new FopalArmor("fopal_leggings", fopal_AM, EntityEquipmentSlot.LEGS);
		fopal_chestplate = new FopalArmor("fopal_chestplate", fopal_AM, EntityEquipmentSlot.CHEST);
		fopal_helmet = new FopalArmor("fopal_helmet", fopal_AM, EntityEquipmentSlot.HEAD);
		
		lopal_boots = new LopalArmor("lopal_boots", lopal_AM, EntityEquipmentSlot.FEET);
		lopal_leggings = new LopalArmor("lopal_leggings", lopal_AM, EntityEquipmentSlot.LEGS);
		lopal_chestplate = new LopalArmor("lopal_chestplate", lopal_AM, EntityEquipmentSlot.CHEST);
		lopal_helmet = new LopalArmor("lopal_helmet", lopal_AM, EntityEquipmentSlot.HEAD);
		
		garnet_boots = new GarnetArmor("garnet_boots", garnet_AM, EntityEquipmentSlot.FEET);
		garnet_leggings = new GarnetArmor("garnet_leggings", garnet_AM, EntityEquipmentSlot.LEGS);
		garnet_chestplate = new GarnetArmor("garnet_chestplate", garnet_AM, EntityEquipmentSlot.CHEST);
		garnet_helmet = new GarnetArmor("garnet_helmet", garnet_AM, EntityEquipmentSlot.HEAD);
		
		satanic_boots = new SatanicArmor("satanic_boots", satanic_AM, EntityEquipmentSlot.FEET);
		satanic_leggings = new SatanicArmor("satanic_leggings", satanic_AM, EntityEquipmentSlot.LEGS);
		satanic_chestplate = new SatanicArmor("satanic_chestplate", satanic_AM, EntityEquipmentSlot.CHEST);
		satanic_helmet = new SatanicArmor("satanic_helmet", satanic_AM, EntityEquipmentSlot.HEAD);
		
		//Dimension Registry
		dark_orb = new LibItemSimple("dark_orb");
		obsidian_stick = new LibItemSimple("obsidian_stick");
		dark_teleporter = new DarkOpalTeleporterStaff("dark_teleporter");
		
	}
	
	/**
	 * REGISTER ITEMS =================================================
	 */
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) 
	{
		event.getRegistry().register(item_garnet);
		event.getRegistry().register(item_topaz);		
		event.getRegistry().register(fire_opal);		
		event.getRegistry().register(dark_opal);		
		event.getRegistry().register(light_opal);		
		event.getRegistry().register(topaz_shard);		
		event.getRegistry().register(lopal_shard);
		event.getRegistry().register(dopal_shard);
		event.getRegistry().register(fopal_shard);		
		event.getRegistry().register(item_satanic);
		
		event.getRegistry().register(topaz_apple);
		event.getRegistry().register(dopal_apple);		
		event.getRegistry().register(fopal_apple);		
		event.getRegistry().register(lopal_apple);
		
		event.getRegistry().register(dark_staff);

		event.getRegistry().register(garnet_sword);			
		event.getRegistry().register(topaz_sword);			
		event.getRegistry().register(fopal_sword);			
		event.getRegistry().register(dopal_sword);			
		event.getRegistry().register(lopal_sword);	

		event.getRegistry().register(garnet_pickaxe);		
		event.getRegistry().register(topaz_pickaxe);
		event.getRegistry().register(fopal_pickaxe);
		event.getRegistry().register(dopal_pickaxe);
		event.getRegistry().register(lopal_pickaxe);

		event.getRegistry().register(garnet_axe);		
		event.getRegistry().register(topaz_axe);		
		event.getRegistry().register(fopal_axe);		
		event.getRegistry().register(dopal_axe);
		event.getRegistry().register(lopal_axe);

		event.getRegistry().register(garnet_hoe);	
		event.getRegistry().register(topaz_hoe);		
		event.getRegistry().register(fopal_hoe);		
		event.getRegistry().register(dopal_hoe);		
		event.getRegistry().register(lopal_hoe);

		event.getRegistry().register(garnet_shovel);	
		event.getRegistry().register(topaz_shovel);
		event.getRegistry().register(fopal_shovel);		
		event.getRegistry().register(dopal_shovel);		
		event.getRegistry().register(lopal_shovel);

		event.getRegistry().register(topaz_arrow);

		event.getRegistry().register(projectile_grenade);

		event.getRegistry().register(topaz_bow);

		event.getRegistry().register(topaz_seeds);		
		event.getRegistry().register(fopal_seeds);		
		event.getRegistry().register(lopal_seeds);		
		event.getRegistry().register(dopal_seeds);

		event.getRegistry().register(topaz_boots);
		event.getRegistry().register(topaz_leggings);
		event.getRegistry().register(topaz_chestplate);
		event.getRegistry().register(topaz_helmet);
		
		event.getRegistry().register(dopal_boots);
		event.getRegistry().register(dopal_leggings);
		event.getRegistry().register(dopal_chestplate);
		event.getRegistry().register(dopal_helmet);
		
		event.getRegistry().register(fopal_boots);
		event.getRegistry().register(fopal_leggings);
		event.getRegistry().register(fopal_chestplate);
		event.getRegistry().register(fopal_helmet);

		event.getRegistry().register(lopal_boots);
		event.getRegistry().register(lopal_leggings);
		event.getRegistry().register(lopal_chestplate);
		event.getRegistry().register(lopal_helmet);
		
		event.getRegistry().register(garnet_boots);
		event.getRegistry().register(garnet_leggings);
		event.getRegistry().register(garnet_chestplate);
		event.getRegistry().register(garnet_helmet);

		event.getRegistry().register(satanic_boots);
		event.getRegistry().register(satanic_leggings);
		event.getRegistry().register(satanic_chestplate);
		event.getRegistry().register(satanic_helmet);

		event.getRegistry().register(dark_orb);
		event.getRegistry().register(obsidian_stick);
		event.getRegistry().register(dark_teleporter); 
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) 
	{
		registerRender(dark_staff);
	}
	
	private static void registerRender(Item item) 
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
}
