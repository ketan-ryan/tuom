package com.mco.main;

import java.io.IOException;
import java.util.ArrayList;

import com.mco.TUOM;
import com.mco.blocks.biomes.dark.DarkLeaves;
import com.mco.blocks.biomes.dark.DarkLog;
import com.mco.blocks.biomes.dark.DarkObsidian;
import com.mco.blocks.biomes.dark.DarkPlanks;
import com.mco.blocks.biomes.dark.DarkSapling;
import com.mco.blocks.crops.DopalCrop;
import com.mco.blocks.crops.FopalCrop;
import com.mco.blocks.crops.LopalCrop;
import com.mco.blocks.crops.TopazCrop;
import com.mco.blocks.furnaces.dark.DarkFurnace;
import com.mco.blocks.furnaces.dark.DarkFurnaceTileEntity;
import com.mco.blocks.ores.DopalOre;
import com.mco.blocks.ores.FopalOre;
import com.mco.blocks.ores.GarnetOre;
import com.mco.blocks.ores.LopalOre;
import com.mco.blocks.ores.TUOMOre;
import com.mco.blocks.ores.TopazOre;

import library.LibRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * DECLARE BLOCKS =================================================
 */
@Mod.EventBusSubscriber(modid = TUOM.MODID)
@ObjectHolder(TUOM.MODID)
public class TUOMBlocks 
{
	public static ArrayList<Block> blocks = new ArrayList<>();
	
	//Ores
	@ObjectHolder("topaz_ore")
	public static final Block TOPAZ_ORE = null;
	@ObjectHolder("fopal_ore")
	public static final Block FOPAL_ORE = null;
	@ObjectHolder("dopal_ore")
	public static final Block DOPAL_ORE = null;
	@ObjectHolder("lopal_ore")
	public static final Block LOPAL_ORE = null;
	@ObjectHolder("garnet_ore")
	public static final Block GARNET_ORE = null;
	
	@ObjectHolder("dark_coal")
	public static final Block DARK_COAL = null;
	@ObjectHolder("dark_iron")
	public static final Block DARK_IRON = null;
	@ObjectHolder("dark_gold")
	public static final Block DARK_GOLD = null;
	@ObjectHolder("dark_redstone")
	public static final Block DARK_REDSTONE = null;
	@ObjectHolder("dark_lapis")
	public static final Block DARK_LAPIS = null;
	@ObjectHolder("dark_diamond")
	public static final Block DARK_DIAMOND = null;
	@ObjectHolder("dark_emerald")
	public static final Block DARK_EMERALD = null;
	
	@ObjectHolder("dark_garnet")
	public static final Block DARK_GARNET = null;
	@ObjectHolder("dark_topaz")
	public static final Block DARK_TOPAZ = null;
	@ObjectHolder("dark_fopal")
	public static final Block DARK_FOPAL = null;
	@ObjectHolder("dark_dopal")
	public static final Block DARK_DOPAL = null;
	@ObjectHolder("dark_lopal")
	public static final Block DARK_LOPAL = null;
	
	//Crops
	@ObjectHolder("topaz_crop")
	public static final Block TOPAZ_CROP = null;
	@ObjectHolder("fopal_crop")
	public static final Block FOPAL_CROP = null;
	@ObjectHolder("lopal_crop")
	public static final Block LOPAL_CROP = null;
	@ObjectHolder("dopal_crop")
	public static final Block DOPAL_CROP = null;
	
	//Furnaces
	@ObjectHolder("dark_furnace")
	public static final Block DARK_FURNACE = null;
	@ObjectHolder("opal_fuser")
	public static final Block OPAL_FUSER = null;
	
	//Biome
	@ObjectHolder("dark_stone")
	public static final Block DARK_STONE = null;
	@ObjectHolder("dark_cobble")
	public static final Block DARK_COBBLE = null;
	@ObjectHolder("dark_brick")
	public static final Block DARK_BRICK = null;
	@ObjectHolder("dark_obsidian")
	public static final Block DARK_OBSIDIAN = null;
	
	@ObjectHolder("dark_log")
	public static final Block DARK_LOG = null;
	@ObjectHolder("dark_leaves")
	public static final Block DARK_LEAVES = null;
	@ObjectHolder("dark_planks")
	public static final Block DARK_PLANKS = null;
	@ObjectHolder("dark_sapling")
	public static final Block DARK_SAPLING = null;
	
	public static void registerBlocks(IForgeRegistry<Block> registry)
	{
		registerBlock(registry, "garnet_ore", new GarnetOre("pickaxe", 1).setCreativeTab(TUOM.tuom_tab));
		registerBlock(registry, "topaz_ore", new TopazOre("pickaxe", 2).setCreativeTab(TUOM.tuom_tab));
		registerBlock(registry, "fopal_ore", new FopalOre("pickaxe", 3).setCreativeTab(TUOM.tuom_tab));
		registerBlock(registry, "dopal_ore", new DopalOre("pickaxe", 3).setCreativeTab(TUOM.tuom_tab));
		registerBlock(registry, "lopal_ore", new LopalOre("pickaxe", 3).setCreativeTab(TUOM.tuom_tab));
		
		registerBlock(registry, "dark_coal", new TUOMOre(0, 2, 0.1F, 3, 15, false, Items.COAL, 1, 2, 2).setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_iron", new TUOMOre(1, 0, 0.7F, 3, 15, true, Items.IRON_INGOT, 1, 0, 0).setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_gold", new TUOMOre(2, 0, 1.0F, 3, 15, true, Items.GOLD_INGOT, 1, 0, 0).setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_redstone", new TUOMOre(2, 5, 0.7F, 3, 15, false, Items.REDSTONE, 4, 8, 4).setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_lapis", new TUOMOre(1, 5, 0.2F, 3, 15, false, new ItemStack(Items.DYE, 1, 4).getItem(), 4, 5, 32).setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_diamond", new TUOMOre(2, 7, 1.0F, 3, 15, false, Items.DIAMOND, 1, 4, 1).setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_emerald", new TUOMOre(2, 7, 1.0F, 3, 15, false, Items.EMERALD, 1, 4, 1).setCreativeTab(TUOM.dim_tab));

		registerBlock(registry, "dark_garnet", new GarnetOre("pickaxe", 1).setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_topaz", new TopazOre("pickaxe", 2).setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_fopal", new FopalOre("pickaxe", 3).setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_dopal", new DopalOre("pickaxe", 3).setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_lopal", new LopalOre("pickaxe", 3).setCreativeTab(TUOM.dim_tab));
		
		registerBlock(registry, "topaz_crop", new TopazCrop());
		registerBlock(registry, "fopal_crop", new FopalCrop());
		registerBlock(registry, "dopal_crop", new DopalCrop());
		registerBlock(registry, "lopal_crop", new LopalCrop());
		
		registerBlock(registry, "dark_furnace", new DarkFurnace(DarkFurnaceTileEntity.class).setCreativeTab(TUOM.tuom_tab));
	//	GameRegistry.registerTileEntity(DarkFurnaceTileEntity.class, new ResourceLocation("tuom:blocks/dark_furnace/dark_furnace_gui.png"));
	//	LibRegistry.registerGUI(DarkFurnaceTileEntity.class, DarkFurnaceContainer.class, "blocks/dark_furnace/dark_furnace_gui.png");

		registerBlock(registry, "dark_stone", new Block(Material.ROCK).setResistance(10).setHardness(1.5F).setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_obsidian", new DarkObsidian().setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_log", new DarkLog().setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_planks", new Block(Material.WOOD).setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_leaves", new DarkLeaves().setCreativeTab(TUOM.dim_tab));
		registerBlock(registry, "dark_sapling", new DarkSapling().setCreativeTab(TUOM.dim_tab));
	}	
	
	public static <T extends Block> void registerBlock(IForgeRegistry<Block> registry, String name, T block)
	{
		block.setUnlocalizedName(name);
		block.setRegistryName(name);
		registry.register(block);
		blocks.add(block);
	}

	/**
	 * GENERATE ORE =================================================
	 */
	public static void init(FMLInitializationEvent event) 
	{
//		%chance, minheight, maxheight, veinsize, veincount
		LibRegistry.registerOreGen(GARNET_ORE.getDefaultState(), 99, 30, 200, 8, 8);
		LibRegistry.registerOreGen(TOPAZ_ORE.getDefaultState(), 10, 5, 30, 4, 1);
		LibRegistry.registerOreGen(FOPAL_ORE.getDefaultState(), 7, 0, 25, 2, 1);
		LibRegistry.registerOreGen(DOPAL_ORE.getDefaultState(), 3, 0, 20, 2, 1);
		LibRegistry.registerOreGen(LOPAL_ORE.getDefaultState(), 2, 0, 15, 2, 1);
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) throws IOException
	{
		if(!JSONGenerator.getInstance().inJar())
			JSONGenerator.generateJsonBlock(blocks);
		
		for(int i = 0; i < DarkPlanks.EnumType.values().length; i++)
		{
			registerRender(DARK_PLANKS, i, "planks_" + DarkPlanks.EnumType.values()[i].getName());
		}
		
		for(Block block: blocks)
		{
			registerRender(block, 0, block.getUnlocalizedName().substring(5));
		}
	}
	
	public static void registerRender(Block block, int meta, String fileName)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(new ResourceLocation(TUOM.MODID, fileName), "inventory"));
	}
	
}
