package com.mco.proxies;

import com.mco.energy.UltimateEnergy;
import library.LibRegistry;
import library.common.BakedModelLoader;
import library.common.IRegistryObject;
import library.common.Registry;
import library.gui.LibGUIHandler;
import com.mco.TUOM;
import com.mco.blocks.biomes.dark.DarkLeaves;
import com.mco.energy.IUltimateEnergy;
import com.mco.energy.UltimateEnergy;
import com.mco.energy.UltimateEnergyStorage;
import com.mco.events.TUOMEventHandler;
import com.mco.main.TUOMEntities;
import com.mco.potions.TUOMPotions;

import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) 
    {        
    	LibRegistry.initialize(TUOM.MODID, TUOM.instance());
        LibRegistry.preInit();        
    }

    public void init(FMLInitializationEvent event) 
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(TUOM.instance(), new LibGUIHandler());
        CapabilityManager.INSTANCE.register(IUltimateEnergy.class, new UltimateEnergyStorage(), UltimateEnergy::new);
    }

    public void postInit(FMLPostInitializationEvent event) 
    {
        Registry.getRegisteredObjects().forEach(IRegistryObject::initRecipe);
    }

    public void registerGUI(Class<? extends TileEntity> tile, Class<? extends Container> container, String textureGUI) {
        LibGUIHandler.registerGUI(tile, container, textureGUI);
    }
    
    public void setGraphicsLevel(DarkLeaves leaves, boolean isFancyEnabled) {
    }

}
