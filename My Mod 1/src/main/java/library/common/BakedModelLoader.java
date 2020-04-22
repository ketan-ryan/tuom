package library.common;

import library.LibRegistry;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

import java.util.HashMap;
import java.util.Map;

public class BakedModelLoader implements ICustomModelLoader {

    private static final Map<String, SingleTextureModel> models = new HashMap<>();

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        return Registry.getObjectsWithClientInfo().anyMatch(name -> {
            RegistryClientInfo info = Registry.getClientInfoByName(name);
            if (info.getTexture() != null) {
                if (modelLocation.getResourceDomain().equals(LibRegistry.getModid()) && ("single_" + name).equals(modelLocation.getResourcePath())) {
                    return true;
                }
            }
            return false;
        });
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) throws Exception {
        String uniquename = modelLocation.getResourcePath().substring(7);
        if (models.containsKey(uniquename)) {
            return models.get(uniquename);
        }
        RegistryClientInfo info = Registry.getClientInfoByName(uniquename);
        SingleTextureModel model = new SingleTextureModel(info.getTexture(), info.isHandheld(), info.getScale());
        models.put(uniquename, model);
        return model;
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }
}
