package com.mco.proxies;

import com.mco.TUOM;
import com.mco.entities.lightning.dark.RenderDarkLightning;
import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.RenderDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.bomb.EntityDarkBomb;
import com.mco.entities.projectiles.RenderCustomFallingBlock;
import com.mco.events.TUOMEventHandler;
import com.mco.main.TUOMEntities;
import com.mco.potions.TUOMPotions;

import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import library.LibRegistry;
import library.common.BakedModelLoader;
import library.gui.LibFurnaceGUI;
import library.gui.LibGUIHandler;
import com.mco.TUOM;
import com.mco.entities.lightning.dark.EntityDarkLightning;
import com.mco.entities.lightning.dark.RenderDarkLightning;
import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.RenderDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.bomb.EntityDarkBomb;
import com.mco.entities.mobs.dark.demon.bomb.RenderDarkBomb;
import com.mco.entities.projectiles.EntityCustomFallingBlock;
import com.mco.entities.projectiles.RenderCustomFallingBlock;
import com.mco.main.TUOMEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy 
{
	private static final Minecraft MINECRAFT = Minecraft.getMinecraft();
	
    public static int sphereIdOutside;
    public static int sphereIdInside;
    
    @Override
    public void preInit(FMLPreInitializationEvent event) 
    {
        super.preInit(event);

        OBJLoader.INSTANCE.addDomain(TUOM.MODID);
        ModelLoaderRegistry.registerLoader(new BakedModelLoader());
        TUOMEntities.registerEntityModels();
        LibRegistry.registerEntityModels();
      
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkLightning.class, RenderDarkLightning.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityCustomFallingBlock.class, RenderCustomFallingBlock.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkOpalDemon.class, RenderDarkOpalDemon.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkBomb.class, RenderDarkBomb.FACTORY);
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new TUOMEventHandler());
    }
    
    public static EntityPlayer getClientPlayer() {
    	return MINECRAFT.player;
    }
    
    //GuiBossOverlay
    //GuiIngameForge.renderBossHealth
    
    @Override
    public void registerGUI(Class<? extends TileEntity> tile, Class<? extends Container> container, String textureGUI) 
    {
        super.registerGUI(tile, container, textureGUI);
        LibGUIHandler.lookupTileToGUI.put(tile, LibFurnaceGUI.class);
    }
    
    private void setupSphere()
    {
        Sphere sphere = new Sphere();
        //Set up parameters that are common to both outside and inside.

        //GLU_FILL as a solid.
        sphere.setDrawStyle(100012);
        //GLU_SMOOTH will try to smoothly apply lighting
        sphere.setNormals(100000);

        //First make the call list for the outside of the sphere
        sphere.setOrientation(100020);

        sphereIdOutside = GlStateManager.glGenLists(1);
        //Create a new list to hold our sphere data.
        GlStateManager.glNewList(sphereIdOutside, 0x1300);
        //binds the texture
        ResourceLocation rL = new ResourceLocation("tuom:textures/entities/purple.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(rL);
        //The drawing the sphere is automatically doing is getting added to our list. Careful, the last 2 variables
        //control the detail, but have a massive impact on performance. 32x32 is a good balance on my machine.
        sphere.draw(0.5F, 48, 48);
        GlStateManager.glEndList();

        //Now make the call list for the inside of the sphere
        sphere.setOrientation(GLU.GLU_INSIDE);
        sphereIdInside = GlStateManager.glGenLists(1);
        //Create a new list to hold our sphere data.
        GlStateManager.glNewList(sphereIdInside, 0x1300);
        Minecraft.getMinecraft().getTextureManager().bindTexture(rL);
        sphere.draw(0.5F, 48, 48);
        GlStateManager.glEndList();
    }
}
