package com.mco.main;

import java.io.IOException;
import java.util.ArrayList;

import com.mco.TUOM;
import com.mco.items.archery.TopazArrow;
import com.mco.items.archery.TopazBow;
import com.mco.items.armor.DopalArmor;
import com.mco.items.armor.FopalArmor;
import com.mco.items.armor.GarnetArmor;
import com.mco.items.armor.LopalArmor;
import com.mco.items.armor.SatanicArmor;
import com.mco.items.armor.TopazArmor;
import com.mco.items.basic.ItemSatanic;
import com.mco.items.crops.DopalSeeds;
import com.mco.items.crops.FopalSeeds;
import com.mco.items.crops.LopalSeeds;
import com.mco.items.crops.TopazSeeds;
import com.mco.items.food.DopalApple;
import com.mco.items.food.FopalApple;
import com.mco.items.food.LopalApple;
import com.mco.items.food.TopazApple;
import com.mco.items.projectiles.FopalGrenade;
import com.mco.items.teleporters.DarkOpalTeleporterStaff;
import com.mco.items.tools.dark.DarkStaff;
import com.mco.items.tools.dark.DopalAxe;
import com.mco.items.tools.dark.DopalHoe;
import com.mco.items.tools.dark.DopalPickaxe;
import com.mco.items.tools.dark.DopalShovel;
import com.mco.items.tools.fopal.FopalAxe;
import com.mco.items.tools.fopal.FopalHoe;
import com.mco.items.tools.fopal.FopalPickaxe;
import com.mco.items.tools.fopal.FopalShovel;
import com.mco.items.tools.fopal.FopalSword;
import com.mco.items.tools.garnet.GarnetAxe;
import com.mco.items.tools.garnet.GarnetHoe;
import com.mco.items.tools.garnet.GarnetPickaxe;
import com.mco.items.tools.garnet.GarnetShovel;
import com.mco.items.tools.light.LopalAxe;
import com.mco.items.tools.light.LopalHoe;
import com.mco.items.tools.light.LopalPickaxe;
import com.mco.items.tools.light.LopalShovel;
import com.mco.items.tools.light.LopalSword;
import com.mco.items.tools.topaz.TopazAxe;
import com.mco.items.tools.topaz.TopazHoe;
import com.mco.items.tools.topaz.TopazPickaxe;
import com.mco.items.tools.topaz.TopazShovel;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * DECLARE ITEMS =================================================
 */
@Mod.EventBusSubscriber(modid = TUOM.MODID)
@ObjectHolder(TUOM.MODID)
public class TUOMItems {

	private static ArrayList<Item> items = new ArrayList<Item>();
	
	//name harvestLevel maxUses efficiency damage enchantability 
	private static final ToolMaterial TOPAZ_TM = EnumHelper.addToolMaterial("Topaz", 3, 1600, 10F, 6F, 10);
	private static final ToolMaterial FOPAL_TM = EnumHelper.addToolMaterial("Fire Opal", 3, 1700, 11.0F, 8.0F, 10);
	private static final ToolMaterial DOPAL_TM = EnumHelper.addToolMaterial("Dark Opal", 3, 1800, 12.0F, 10.0F, 10);
	private static final ToolMaterial LOPAL_TM = EnumHelper.addToolMaterial("Light Opal", 3, 1900, 13.0F, 12.0F, 10);
	private static final ToolMaterial GARNET_TM = EnumHelper.addToolMaterial("Garnet", 2, 150, 5.0F, 2.5F, 15);
	private static final ToolMaterial SATANIC_TM = EnumHelper.addToolMaterial("Satan", 3, 666, 5.0F, 6F, 7);
	private static final ToolMaterial DOPAL_STAFF_TM = EnumHelper.addToolMaterial("Staff", 3, 7500, 5.0F, 13F, 30);
	private static final ToolMaterial LIGHT_SWORD_TM = EnumHelper.addToolMaterial("Sword", 3, -1, 5.0F, 12.5F, 35);
	
	//Armor Material Registry
	private static final ArmorMaterial TOPAZ_AM = EnumHelper.addArmorMaterial("topaz_AM", "topaz_AM", 43, new int[]{4, 8, 7, 4}, 27, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F);
	private static final ArmorMaterial FOPAL_AM = EnumHelper.addArmorMaterial("Fire Opal", "Fire Opal", 53, new int[]{5, 9, 8, 5}, 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F);  
	private static final ArmorMaterial DOPAL_AM = EnumHelper.addArmorMaterial("Dark Opal", "Dark Opal", 63, new int[]{6, 10, 9, 6}, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F);   
	private static final ArmorMaterial LOPAL_AM = EnumHelper.addArmorMaterial("Light Opal", "Light Opal", 73, new int[]{7, 11, 10, 7}, 33, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F);   
	private static final ArmorMaterial GARNET_AM = EnumHelper.addArmorMaterial("Garnet", "Garnet", 20, new int[]{3, 7, 6, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F);   
	private static final ArmorMaterial SATANIC_AM = EnumHelper.addArmorMaterial("Satan", "Satan", 666, new int[]{8, 13, 11, 8}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F);
	
	//Items
	@ObjectHolder("item_garnet")
	public static final Item ITEM_GARNET = null;
	@ObjectHolder("item_topaz")
	public static final Item ITEM_TOPAZ = null;
	@ObjectHolder("item_fopal")
	public static final Item FIRE_OPAL = null;
	@ObjectHolder("item_dopal")
	public static final Item DARK_OPAL = null;
	@ObjectHolder("item_;opal")
	public static final Item LIGHT_OPAL = null;
	@ObjectHolder("item_satanic")
	public static final Item ITEM_SATANIC = null;
	
	@ObjectHolder("topaz_shard")
	public static final Item TOPAZ_SHARD = null;
	@ObjectHolder("fopal_shard")
	public static final Item FOPAL_SHARD = null;
	@ObjectHolder("lopal_shard")
	public static final Item LOPAL_SHARD = null;
	@ObjectHolder("dopal_shard")
	public static final Item DOPAL_SHARD = null;
	
	@ObjectHolder("topaz_apple")
	public static final Item TOPAZ_APPLE = null;
	@ObjectHolder("dopal_apple")
	public static final Item DOPAL_APPLE = null;
	@ObjectHolder("fopal_apple")
	public static final Item FOPAL_APPLE = null;
	@ObjectHolder("lopal_apple")
	public static final Item LOPAL_APLLE = null;
	@ObjectHolder("candy_cane")
	public static final Item CANDY_CANE = null;
	
	//Tools
	@ObjectHolder("garnet_pickaxe")
	public static final Item GARNET_PICKAXE = null;
	@ObjectHolder("garnet_hoe")
	public static final Item GARNET_HOE = null;
	@ObjectHolder("garnet_axe")
	public static final Item GARNET_AXE = null;
	@ObjectHolder("garnet_shovel")
	public static final Item GARNET_SHOVEL = null;
	
	@ObjectHolder("topaz_sword")
	public static final Item TOPAZ_SWORD = null;
	@ObjectHolder("topaz_pickaxe")
	public static final Item TOPAZ_PICKAXE = null;
	@ObjectHolder("topaz_hoe")
	public static final Item TOPAZ_HOE = null;
	@ObjectHolder("topaz_axe")
	public static final Item TOPAZ_AXE = null;
	@ObjectHolder("topaz_shovel")
	public static final Item TOPAZ_SHOVEL = null;
	
	@ObjectHolder("fopal_sword")
	public static final Item FOPAL_SWORD = null;
	@ObjectHolder("fopal_pickaxe")
	public static final Item FOPAL_PICKAXE = null;
	@ObjectHolder("fopal_hoe")
	public static final Item FOPAL_HOE = null;
	@ObjectHolder("fopal_axe")
	public static final Item FOPAL_AXE = null;
	@ObjectHolder("fopal_shovel")
	public static final Item FOPAL_SHOVEL = null;
	
	@ObjectHolder("dopal_sword")
	public static final Item DOPAL_SWORD = null;
	@ObjectHolder("dopal_pickaxe")
	public static final Item DOPAL_PICKAXE = null;
	@ObjectHolder("dopal_hoe")
	public static final Item DOPAL_HOE = null;
	@ObjectHolder("dopal_axe")
	public static final Item DOPAL_AXE = null;
	@ObjectHolder("dopal_shovel")
	public static final Item DOPAL_SHOVEL = null;
	
	@ObjectHolder("lopal_sword")
	public static final Item LOPAL_SWORD = null;
	@ObjectHolder("lopal_pickaxe")
	public static final Item LOPAL_PICKAXE = null;
	@ObjectHolder("lopal_hoe")
	public static final Item LOPAL_HOE = null;
	@ObjectHolder("lopal_axe")
	public static final Item LOPAL_AXE = null;
	@ObjectHolder("lopal_shovel")
	public static final Item LOPAL_SHOVEL = null;
	
	//Magic
	@ObjectHolder("dark_staff")
	public static final Item DARK_STAFF = null;
	
	//Archery and Projectiles
	@ObjectHolder("topaz_arrow")
	public static final Item TOPAZ_ARROW = null;
	@ObjectHolder("topaz_bow")
	public static final Item TOPAZ_BOW = null;
	@ObjectHolder("fopal_grenade")
	public static final Item FOPAL_GRENADE = null;
	
	//Armor Sets
	@ObjectHolder("topaz_boots")
	public static final Item TOPAZ_BOOTS = null;
	@ObjectHolder("topaz_leggings")
	public static final Item TOPAZ_LEGGINGS = null;
	@ObjectHolder("topaz_chestplate")
	public static final Item TOPAZ_CHESTPLATE = null;
	@ObjectHolder("topaz_helmet")
	public static final Item TOPAZ_HELMET = null;
	
	@ObjectHolder("fopal_boots")
	public static final Item FOPAL_BOOTS = null;
	@ObjectHolder("fopal_leggings")
	public static final Item FOPAL_LEGGINGS = null;
	@ObjectHolder("fopal_chestplate")
	public static final Item FOPAL_CHESTPLATE = null;
	@ObjectHolder("fopal_helmet")
	public static final Item FOPAL_HELMET = null;
	
	@ObjectHolder("dopal_boots")
	public static final Item DOPAL_BOOTS = null;
	@ObjectHolder("dopal_leggings")
	public static final Item DOPAL_LEGGINGS = null;
	@ObjectHolder("dopal_chestplate")
	public static final Item DOPAL_CHESTPLATE = null;
	@ObjectHolder("dopal_helmet")
	public static final Item DOPAL_HELMET = null;
	
	@ObjectHolder("lopal_boots")
	public static final Item LOPAL_BOOTS = null;
	@ObjectHolder("lopal_leggings")
	public static final Item LOPAL_LEGGINGS = null;
	@ObjectHolder("lopal_chestplate")
	public static final Item LOPAL_CHESTPLATE = null;
	@ObjectHolder("lopal_helmet")
	public static final Item LOPAL_HELMET = null;
	
	@ObjectHolder("garnet_boots")
	public static final Item GARNET_BOOTS = null;
	@ObjectHolder("garnet_leggings")
	public static final Item GARNET_LEGGINGS = null;
	@ObjectHolder("garnet_chestplate")
	public static final Item GARNET_CHESTPLATE = null;
	@ObjectHolder("garnet_helmet")
	public static final Item GARNET_HELMET = null;
	
	@ObjectHolder("satanic_boots")
	public static final Item SATANIC_BOOTS = null;
	@ObjectHolder("satanic_leggings")
	public static final Item SATANIC_LEGGINGS = null;
	@ObjectHolder("satanic_chestplate")
	public static final Item SATANIC_CHESTPLATE = null;
	@ObjectHolder("satanic_helmet")
	public static final Item SATANIC_HELMET = null;
	
	//Crops
	@ObjectHolder("topaz_seeds")
	public static final Item TOPAZ_SEEDS = null;
	@ObjectHolder("fopal_seeds")
	public static final Item FOPAL_SEEDS = null;
	@ObjectHolder("dopal_seeds")
	public static final Item DOPAL_SEEDS = null;
	@ObjectHolder("lopal_seeds")
	public static final Item LOPAL_SEEDS = null;

	//Dimensions
	@ObjectHolder("dark_orb")
	public static final Item DARK_ORB = null;
	@ObjectHolder("obsidian_stick")
	public static final Item OBSIDIAN_STICK = null;
	@ObjectHolder("dark_teleporter")
	public static final Item DARK_TELEPORTER = null;
	
	@ObjectHolder("dark_bomb")
	public static final Item DARK_BOMB = null;
	
	public static void registerItems(IForgeRegistry<Item> registry)
	{
		registerItem(registry, "item_garnet", new Item().setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "item_topaz", new Item().setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "fire_opal", new Item().setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "dark_opal", new Item().setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "light_opal", new Item().setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "item_satanic", new ItemSatanic().setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "topaz_shard", new Item().setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "fopal_shard", new Item().setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "dopal_shard", new Item().setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "lopal_shard", new Item().setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "topaz_apple", new TopazApple(3, 5, false).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "dopal_apple", new DopalApple(3, 5, false).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "fopal_apple", new FopalApple(3, 5, false).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "lopal_apple", new LopalApple(3, 5, false).setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "garnet_sword", new ItemSword(GARNET_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "garnet_pickaxe", new GarnetPickaxe(1, -2.8F, GARNET_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "garnet_axe", new GarnetAxe(2, -3F, GARNET_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "garnet_shovel", new GarnetShovel(0, -3F, GARNET_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "garnet_hoe", new GarnetHoe(-1, 0, GARNET_TM).setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "topaz_sword", new ItemSword(TOPAZ_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "topaz_pickaxe", new TopazPickaxe(1, -2.8F, TOPAZ_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "topaz_axe", new TopazAxe(2, -3F, TOPAZ_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "topaz_shovel", new TopazShovel(0, -3F, TOPAZ_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "topaz_hoe", new TopazHoe(-1, 0, TOPAZ_TM).setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "fopal_sword", new FopalSword(FOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "fopal_pickaxe", new FopalPickaxe(1, -2.8F, FOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "fopal_axe", new FopalAxe(2, -3F, FOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "fopal_shovel", new FopalShovel(0, -3F, FOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "fopal_hoe", new FopalHoe(-1, 0, FOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "dopal_sword", new ItemSword(DOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "dopal_pickaxe", new DopalPickaxe(1, -2.8F, DOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "dopal_axe", new DopalAxe(2, -3F, DOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "dopal_shovel", new DopalShovel(0, -3F, DOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "dopal_hoe", new DopalHoe(-1, 0, DOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "lopal_sword", new LopalSword(LOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "lopal_pickaxe", new LopalPickaxe(1, -2.8F, LOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "lopal_axe", new LopalAxe(2, -3F, LOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "lopal_shovel", new LopalShovel(0, -3F, LOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "lopal_hoe", new LopalHoe(-1, 0, LOPAL_TM).setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "dark_staff", new DarkStaff().setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "topaz_arrow", new TopazArrow().setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "topaz_bow", new TopazBow().setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "fopal_grenade", new FopalGrenade().setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "topaz_boots", new TopazArmor(TOPAZ_AM, EntityEquipmentSlot.FEET).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "topaz_leggings", new TopazArmor(TOPAZ_AM, EntityEquipmentSlot.LEGS).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "topaz_chestplate", new TopazArmor(TOPAZ_AM, EntityEquipmentSlot.CHEST).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "topaz_helmet", new TopazArmor(TOPAZ_AM, EntityEquipmentSlot.HEAD).setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "fopal_boots", new FopalArmor(FOPAL_AM, EntityEquipmentSlot.FEET).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "fopal_leggings", new FopalArmor(FOPAL_AM, EntityEquipmentSlot.LEGS).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "fopal_chestplate", new FopalArmor(FOPAL_AM, EntityEquipmentSlot.CHEST).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "fopal_helmet", new FopalArmor(FOPAL_AM, EntityEquipmentSlot.HEAD).setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "dopal_boots", new DopalArmor(DOPAL_AM, EntityEquipmentSlot.FEET).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "dopal_leggings", new DopalArmor(DOPAL_AM, EntityEquipmentSlot.LEGS).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "dopal_chestplate", new DopalArmor(DOPAL_AM, EntityEquipmentSlot.CHEST).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "dopal_helmet", new DopalArmor(DOPAL_AM, EntityEquipmentSlot.HEAD).setCreativeTab(TUOM.tuom_tab));

		registerItem(registry, "lopal_boots", new LopalArmor(LOPAL_AM, EntityEquipmentSlot.FEET).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "lopal_leggings", new LopalArmor(LOPAL_AM, EntityEquipmentSlot.LEGS).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "lopal_chestplate", new LopalArmor(LOPAL_AM, EntityEquipmentSlot.CHEST).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "lopal_helmet", new LopalArmor(LOPAL_AM, EntityEquipmentSlot.HEAD).setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "garnet_boots", new GarnetArmor(GARNET_AM, EntityEquipmentSlot.FEET).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "garnet_leggings", new GarnetArmor(GARNET_AM, EntityEquipmentSlot.LEGS).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "garnet_chestplate", new GarnetArmor(GARNET_AM, EntityEquipmentSlot.CHEST).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "garnet_helmet", new GarnetArmor(GARNET_AM, EntityEquipmentSlot.HEAD).setCreativeTab(TUOM.tuom_tab));
	
		registerItem(registry, "satanic_boots", new SatanicArmor(SATANIC_AM, EntityEquipmentSlot.FEET).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "satanic_leggings", new SatanicArmor(SATANIC_AM, EntityEquipmentSlot.LEGS).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "satanic_chestplate", new SatanicArmor(SATANIC_AM, EntityEquipmentSlot.CHEST).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "satanic_helmet", new SatanicArmor(SATANIC_AM, EntityEquipmentSlot.HEAD).setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "topaz_seeds", new TopazSeeds(TUOMBlocks.TOPAZ_CROP).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "fopal_seeds", new FopalSeeds(TUOMBlocks.FOPAL_CROP).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "dopal_seeds", new DopalSeeds(TUOMBlocks.DOPAL_CROP).setCreativeTab(TUOM.tuom_tab));
		registerItem(registry, "lopal_seeds", new LopalSeeds(TUOMBlocks.LOPAL_CROP).setCreativeTab(TUOM.tuom_tab));
		
		registerItem(registry, "dark_orb", new Item().setCreativeTab(TUOM.dim_tab));
		registerItem(registry, "obsidian_stick", new Item().setCreativeTab(TUOM.dim_tab));
		registerItem(registry, "dark_teleporter", new DarkOpalTeleporterStaff().setCreativeTab(TUOM.dim_tab));
		
		registerItem(registry, "dark_bomb", new Item());
		
		for(Block block : TUOMBlocks.blocks)
			registerItem(registry, block.getUnlocalizedName().substring(5), new ItemBlock(block));
	}
	
	public static <T extends Item> void registerItem(IForgeRegistry<Item> registry, String name, T item) 
	{
		item.setUnlocalizedName(name);
		item.setRegistryName(name);
		registry.register(item);
		items.add(item);
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) throws IOException 
	{
		JSONGenerator.generateJsonItem(items);
		for(Item item : items) {
			registerRender(item);
		}
	}
	
	private static void registerRender(Item item) 
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
}
