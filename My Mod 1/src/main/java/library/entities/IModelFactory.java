package library.entities;

import net.minecraft.client.model.ModelBiped;

public interface IModelFactory {

    ModelBiped createModel(float modelSize, boolean txt32);
}
