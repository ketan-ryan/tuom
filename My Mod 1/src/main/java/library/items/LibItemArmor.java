package library.items;

import javax.annotation.Nullable;

import com.mco.TUOM;

import library.LibRegistry;
import library.common.IJavaDocs;
import library.common.IRegistryObject;
import library.common.Registry;
import library.common.RegistryClientInfo;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class LibItemArmor extends ItemArmor implements IRegistryObject, IJavaDocs {

    @Nullable
    private ResourceLocation textureName = null;
    private boolean handheld = false;
	//private String wrapTexture;
    private float scale = 1.0f;


    public LibItemArmor( ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, 0, equipmentSlotIn);
    }

    /**
     * Gets the wrap texture for the 3D armor render, expects to be in items/models and ends with either _layer_1.png or _layer_2.png
     *
     * @return eg: "my_armor_1" for file "items/models/my_armor_1_layer_1.png"
     */
    protected abstract String getArmorWrapTexture();

    private String getArmorWrapTextureImpl() {
        return "items/models/" + getArmorWrapTexture() + "_layer_" + (getEquipmentSlot() == EntityEquipmentSlot.LEGS ? "2" : "1") + ".png";
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

    /**
     * Student facing method for setting texture path override easily
     *
     * @param texturePath
     */
    public Item setTexturePath(String texturePath) {
        setSingleTexture(new ResourceLocation(LibRegistry.getModid(), texturePath), this.handheld);
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

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {

    		return TUOM.MODID + ":textures/" + getArmorWrapTextureImpl();
        
    }
}
