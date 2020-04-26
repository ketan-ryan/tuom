package com.mco.main;

import com.mco.TUOM;
import com.mco.blocks.biomes.dark.DarkLeaves;
import com.mco.blocks.biomes.dark.DarkObsidian;
import com.mco.blocks.biomes.dark.DarkSapling;
import com.mco.blocks.crops.DopalCrop;
import com.mco.blocks.crops.FopalCrop;
import com.mco.blocks.crops.LopalCrop;
import com.mco.blocks.crops.TopazCrop;
import com.mco.blocks.furnaces.dark.DarkFurnace;
import com.mco.blocks.furnaces.dark.DarkFurnaceContainer;
import com.mco.blocks.ores.DopalOre;
import com.mco.blocks.ores.GarnetOre;
import com.mco.blocks.ores.TUOMOre;
import com.mco.blocks.ores.TopazOre;
import library.LibRegistry;
import library.blocks.LibBlockSimple;
import library.blocks.LibItemBlock;
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
import com.mco.blocks.furnaces.dark.DarkFurnaceContainer;
import com.mco.blocks.furnaces.dark.DarkFurnaceTileEntity;
import com.mco.blocks.ores.DopalOre;
import com.mco.blocks.ores.FopalOre;
import com.mco.blocks.ores.GarnetOre;
import com.mco.blocks.ores.LopalOre;
import com.mco.blocks.ores.TUOMOre;
import com.mco.blocks.ores.TopazOre;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.BlockStatePaletteRegistry;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

/**
 * DECLARE BLOCKS =================================================
 */
@Mod.EventBusSubscriber(modid = TUOM.MODID)
@ObjectHolder(TUOM.MODID)
public class TUOMBlocks 
{
	//Ores
	public static Block topaz_ore;
	public static Block fopal_ore;
	public static Block lopal_ore;
	public static Block dopal_ore;
	public static Block garnet_ore;
	
	public static Block dark_coal;
	public static Block dark_iron;
	public static Block dark_gold;
	public static Block dark_redstone;
	public static Block dark_lapis;
	public static Block dark_diamond;
	public static Block dark_emerald;
	
	public static Block dark_garnet;
	public static Block dark_topaz;
	public static Block dark_fopal;
	public static Block dark_dopal;
	public static Block dark_lopal;
	
	//Crops
	public static Block topaz_crop;
	public static Block fopal_crop;
	public static Block lopal_crop;
	public static Block dopal_crop;
	
	//Furnaces
	public static Block dark_furnace;
	public static Block opal_fuser;
	
	//Biome
	public static Block dark_stone;
	public static Block dark_cobble;
	public static Block dark_brick;
	public static Block dark_obsidian;
	
	public static Block dark_log;
	public static Block dark_leaves;
	public static Block dark_planks;
	public static Block dark_sapling;
	
	/**
	 * REGISTER BLOCKS =================================================
	 */
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) 
	{
		//Ores
		garnet_ore = new GarnetOre("garnet_ore", "pickaxe", 1);
		event.getRegistry().register(garnet_ore);
		
		topaz_ore = new TopazOre("topaz_ore", "pickaxe", 2);
		event.getRegistry().register(topaz_ore);
		
		fopal_ore = new FopalOre("fopal_ore", "pickaxe", 3);
		event.getRegistry().register(fopal_ore);
		
		dopal_ore = new DopalOre("dopal_ore", "pickaxe", 3);
		event.getRegistry().register(dopal_ore);
		
		lopal_ore = new LopalOre("lopal_ore", "pickaxe", 3);
		event.getRegistry().register(lopal_ore);
		
		dark_coal = new TUOMOre("dark_coal", 0, 2, 0.1F, 3, 15, false, Items.COAL, 1, 2, 2);
		event.getRegistry().register(dark_coal);
		
		dark_iron = new TUOMOre("dark_iron", 1, 0, 0.7F, 3, 15, true, Items.IRON_INGOT, 1, 0, 0);
		event.getRegistry().register(dark_iron);
		
		dark_gold = new TUOMOre("dark_gold", 2, 0, 1.0F, 3, 15, true, Items.GOLD_INGOT, 1, 0, 0);
		event.getRegistry().register(dark_gold);
		
		dark_redstone = new TUOMOre("dark_redstone", 2, 5, .7F, 3, 15, false, Items.REDSTONE, 4, 8, 4);
		event.getRegistry().register(dark_redstone);
		
		dark_lapis = new TUOMOre("dark_lapis", 1, 5, 0.2F, 3, 15, false, new ItemStack(Items.DYE, 1, 4).getItem(), 4, 5, 32);
		event.getRegistry().register(dark_lapis);
		
		dark_diamond = new TUOMOre("dark_diamond", 2, 7, 1.0F, 3, 15, false, Items.DIAMOND, 1, 4, 1);
		event.getRegistry().register(dark_diamond);
		
		dark_emerald = new TUOMOre("dark_emerald", 2, 7, 1.0F, 3, 15, false, Items.EMERALD, 1, 4, 1);
		event.getRegistry().register(dark_emerald);
		
		dark_garnet = new GarnetOre("dark_garnet", "pickaxe", 1);
		event.getRegistry().register(dark_garnet);
		
		dark_topaz = new TopazOre("dark_topaz", "pickaxe", 2);
		event.getRegistry().register(dark_topaz);
		
		dark_fopal = new FopalOre("dark_fopal", "pickaxe", 3);
		event.getRegistry().register(dark_fopal);
		
		dark_dopal = new DopalOre("dark_dopal", "pickaxe", 3);
		event.getRegistry().register(dark_dopal);
		
		dark_lopal = new LopalOre("dark_lopal", "pickaxe", 3);
		event.getRegistry().register(dark_lopal);
		
		//Crops
		topaz_crop = new TopazCrop("topaz_crop");
		event.getRegistry().register(topaz_crop);

		fopal_crop = new FopalCrop("fopal_crop");
		event.getRegistry().register(fopal_crop);
		
		lopal_crop = new LopalCrop("lopal_crop");
		event.getRegistry().register(lopal_crop);
		
		dopal_crop = new DopalCrop("dopal_crop");
		event.getRegistry().register(dopal_crop);
		
		//Furnaces
		dark_furnace = new DarkFurnace("dark_furnace", DarkFurnaceTileEntity.class);
		event.getRegistry().register(dark_furnace);
		GameRegistry.registerTileEntity(DarkFurnaceTileEntity.class, new ResourceLocation("tuom:blocks/dark_furnace/dark_furnace_gui.png"));
	//	LibRegistry.registerGUI(DarkFurnaceTileEntity.class, DarkFurnaceContainer.class, "blocks/dark_furnace/dark_furnace_gui.png");
		
		//Biome
		dark_stone = new LibBlockSimple("dark_stone").setHardness(1.5F).setResistance(10);
		event.getRegistry().register(dark_stone);
		
		dark_obsidian = new DarkObsidian("dark_obsidian");
		event.getRegistry().register(dark_obsidian);       
		
		dark_log = new DarkLog().setRegistryName("dark_log").setUnlocalizedName("dark_log");
		event.getRegistry().register(dark_log);
		
		dark_planks = new LibBlockSimple("dark_planks");
		event.getRegistry().register(dark_planks);
		
		dark_leaves = new DarkLeaves("dark_leaves");
		event.getRegistry().register(dark_leaves);
		
		dark_sapling = new DarkSapling("dark_sapling");
		event.getRegistry().register(dark_sapling);
	}
	

	/**
	 * REGISTER BLOCK ITEMS =================================================
	 */
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) 
	{
		//Ores
		event.getRegistry().register(new LibItemBlock(topaz_ore));
		event.getRegistry().register(new LibItemBlock(fopal_ore));
		event.getRegistry().register(new LibItemBlock(lopal_ore));
		event.getRegistry().register(new LibItemBlock(dopal_ore));
		event.getRegistry().register(new LibItemBlock(garnet_ore));
		
		event.getRegistry().register(new LibItemBlock(dark_coal));
		event.getRegistry().register(new LibItemBlock(dark_iron));
		event.getRegistry().register(new LibItemBlock(dark_gold));
		event.getRegistry().register(new LibItemBlock(dark_redstone));
		event.getRegistry().register(new LibItemBlock(dark_lapis));
		event.getRegistry().register(new LibItemBlock(dark_diamond));
		event.getRegistry().register(new LibItemBlock(dark_emerald));
		
		event.getRegistry().register(new LibItemBlock(dark_garnet));
		event.getRegistry().register(new LibItemBlock(dark_topaz));
		event.getRegistry().register(new LibItemBlock(dark_fopal));
		event.getRegistry().register(new LibItemBlock(dark_dopal));
		event.getRegistry().register(new LibItemBlock(dark_lopal));
		
		//Crops
		event.getRegistry().register(new LibItemBlock(topaz_crop));
		event.getRegistry().register(new LibItemBlock(fopal_crop));
		event.getRegistry().register(new LibItemBlock(lopal_crop));
		event.getRegistry().register(new LibItemBlock(dopal_crop));

		//Furnaces
		event.getRegistry().register(new LibItemBlock(dark_furnace));
		
		//Biome
		event.getRegistry().register(new LibItemBlock(dark_stone));
		event.getRegistry().register(new LibItemBlock(dark_obsidian));
		event.getRegistry().register(new LibItemBlock(dark_log));
		event.getRegistry().register(new LibItemBlock(dark_planks));
		event.getRegistry().register(new LibItemBlock(dark_leaves));
		event.getRegistry().register(new LibItemBlock(dark_sapling));
		
	}
	

	/**
	 * GENERATE ORE =================================================
	 */
	public static void init(FMLInitializationEvent event) 
	{
//		%chance, minheight, maxheight, veinsize, veincount
		LibRegistry.registerOreGen(garnet_ore.getDefaultState(), 99, 30, 200, 8, 8);
		LibRegistry.registerOreGen(topaz_ore.getDefaultState(), 10, 5, 30, 4, 1);
		LibRegistry.registerOreGen(fopal_ore.getDefaultState(), 7, 0, 25, 2, 1);
		LibRegistry.registerOreGen(dopal_ore.getDefaultState(), 3, 0, 20, 2, 1);
		LibRegistry.registerOreGen(lopal_ore.getDefaultState(), 2, 0, 15, 2, 1);
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event)
	{
		for(int i = 0; i < DarkPlanks.EnumType.values().length; i++)
		{
			registerRender(dark_planks, i, "planks_" + DarkPlanks.EnumType.values()[i].getName());
		}
		registerRender(dark_log, 0, "dark_log");
		registerRender(dark_leaves, 0, "dark_leaves");
		registerRender(dark_sapling, 0, "dark_sapling");
	}
	
	public static void registerBlockWithVariants(Block block, ItemBlock itemBlock)
	{
		ForgeRegistries.BLOCKS.register(block);
		block.setCreativeTab(TUOM.tuom_tab);
		itemBlock.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(itemBlock);
	}
	
	public static void registerRender(Block block, int meta, String fileName)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(new ResourceLocation(TUOM.MODID, fileName), "inventory"));
	}
	
}
