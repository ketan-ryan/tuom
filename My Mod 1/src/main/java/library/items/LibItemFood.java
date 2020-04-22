package library.items;

import library.LibRegistry;
import library.common.IJavaDocs;
import library.common.IRegistryObject;
import library.common.Registry;
import library.common.RegistryClientInfo;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class LibItemFood extends ItemFood implements IRegistryObject, IJavaDocs {

    @Nullable
    private ResourceLocation textureName = null;
    private boolean handheld = false;
    private float scale = 1.0f;

    public LibItemFood(String registryName, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        setUnlocalizedName(LibRegistry.getModid() + "." + registryName);
        setRegistryName(registryName);
        setCreativeTab(CreativeTabs.MISC);
        Registry.register(this);
        setSingleTexture(new ResourceLocation(LibRegistry.getModid(), "items/" + registryName), false);

    }

    @Override
    public Item setSingleTexture(ResourceLocation textureName, boolean handheld) {
        this.textureName = textureName;
        this.handheld = handheld;
        return this;
    }

    @Override
    public Item setHandheld(boolean handheld) {
        this.handheld = handheld;
        return this;
    }

    @Override
    public Item setScale(float scale) {
        this.scale = scale;
        return this;
    }

    @Override
    public String getUniqueName() {
        return getRegistryName().toString();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        if (textureName == null) {
            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        } else {
            Registry.registerClientInfo(getUniqueName(), new RegistryClientInfo(textureName, handheld, scale));
            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(LibRegistry.getModid() + ":single_" + getUniqueName()));
        }
    }

    @Override
    public void initRecipe() {

    }
}
