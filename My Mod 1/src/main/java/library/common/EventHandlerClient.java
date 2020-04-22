package library.common;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class EventHandlerClient {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {

        // Register the models for all items and blocks. This is automaticly done
        // for all items and blocks in the mod that extend from the base classes
        // (LibItem, LibItemArmor and LibBlockSimple)
        Registry.getRegisteredObjects().forEach(IRegistryObject::initModel);
    }

}
