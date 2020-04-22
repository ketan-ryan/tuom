package library.gui;

import library.LibRegistry;
import library.blocks.LibFurnaceTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class LibGUIHandler implements IGuiHandler {

    private static HashMap<Class, Class> lookupTileToContainer = new HashMap<>();
    public static HashMap<Class, Class> lookupTileToGUI = new HashMap<>();
    private static HashMap<Class, String> lookupContainerToTexture = new HashMap<>();
    private static int tileID = 0;

    public static void registerGUI(Class<? extends TileEntity> tile, Class<? extends Container> container, String textureGUI) {
        lookupTileToContainer.put(tile, container);
        lookupContainerToTexture.put(container, textureGUI);
        GameRegistry.registerTileEntity(tile, LibRegistry.getModid() + "_my_tile_entity_" + tileID++);

        try {
            registerGUIClient(tile, container, textureGUI);
        } catch (Exception ex) {
            //surpress expected error for dedicated server
            //ex.printStackTrace();
        }
    }

    /**
     * Workaround to not having a proxy to use as it is within mymod scope
     */
    @SideOnly(Side.CLIENT)
    public static void registerGUIClient(Class<? extends TileEntity> tile, Class<? extends Container> container, String textureGUI) {
        LibGUIHandler.lookupTileToGUI.put(tile, LibFurnaceGUI.class);
    }

    public static String getTexture(Class tile) {
        return lookupContainerToTexture.get(tile);
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);

        for (Map.Entry<Class, Class> entry : lookupTileToContainer.entrySet()) {
            if (te.getClass().equals(entry.getKey())) {
                try {
                    Constructor c = entry.getValue().getConstructor(InventoryPlayer.class, LibFurnaceTileEntity.class);
                    Container cont = (Container)c.newInstance(player.inventory, te);
                    return cont;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);

        for (Map.Entry<Class, Class> entry : lookupTileToGUI.entrySet()) {
            if (te.getClass().equals(entry.getKey())) {
                try {
                    Constructor c = entry.getValue().getConstructor(InventoryPlayer.class, LibFurnaceTileEntity.class, LibFurnaceContainer.class);
                    GuiContainer cont = (GuiContainer)c.newInstance(player.inventory, te, getServerGuiElement(ID, player, world, x, y, z));
                    return cont;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return null;
    }
}
