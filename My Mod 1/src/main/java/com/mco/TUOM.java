package com.mco;

import org.apache.logging.log4j.Logger;

import com.mco.generation.TUOMWorldGenerator;
import com.mco.main.TUOMBiomes;
import com.mco.main.TUOMBlocks;
import com.mco.main.TUOMItems;
import com.mco.potions.DarkPotion;
import com.mco.proxies.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * MOD INFO =================================================
 */
@Mod(modid = "tuom", name = "The Ultimate Ore Mod", version = "0.2.0")
@Mod.EventBusSubscriber


/**
 * DECLARE =================================================
 */
public class TUOM {

	// ID
	public static final String MODID = "tuom";
	
	public static final int GUI_OPAL_FUSER = 0;

	// Creative Tabs
	public static CreativeTabs tuom_tab;
	
	public static Potion darkPotion;
	
	public static ResourceLocation DARK_CHEST_DEMON, DARK_CHEST_LAVA, DARK_CHEST_TOP;
	
	private static Logger logger;

	/**
	 * REGISTER =================================================
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		proxy.preInit(event);
		
		// Creative Tabs
		tuom_tab = new CreativeTabs("tuom_tab") 
		{
			@Override
			public ItemStack getTabIconItem() 
			{
				return new ItemStack(TUOMItems.item_topaz);
			}
		};

		GameRegistry.registerWorldGenerator(new TUOMWorldGenerator(), 0);	
		DimensionManager.registerDimension(TUOMWorldGenerator.OPAL_DIM_ID, TUOMWorldGenerator.OPAL_DIMENSION_TYPE);
	}
	

	/**
	 * PROXIES =================================================
	 */
	@Mod.Instance
	private static TUOM instance;

	public static TUOM instance() 
	{
		return instance;
	}
	

	@SidedProxy(clientSide = "com.mco.proxies.ClientProxy", serverSide = "com.mco.proxies.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void init(FMLInitializationEvent event) 
	{
		proxy.init(event);
		
		TUOMBlocks.init(event);
		TUOMBlocks.registerRenders();
		TUOMBiomes.init(event);
    	darkPotion = new DarkPotion(true, 0x00000).setIconIndex(0, 0).setPotionName("potion.darkEffect").registerPotionAttributeModifier(
    			SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", -0.40000000298023224D, 2);
    	
    	DARK_CHEST_DEMON = LootTableList.register(new ResourceLocation(MODID, "chests/dark_tower_demon"));
    	DARK_CHEST_LAVA = LootTableList.register(new ResourceLocation(MODID, "chests/dark_tower_lava"));
    	DARK_CHEST_TOP = LootTableList.register(new ResourceLocation(MODID, "chests/dark_tower_top"));

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		proxy.postInit(event);
	}

}
