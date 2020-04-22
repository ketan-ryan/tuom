package library.items;

import library.LibRegistry;
import library.common.IJavaDocs;
import library.common.IRegistryObject;
import library.common.Registry;
import library.common.RegistryClientInfo;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Set;

public class LibItemTool extends ItemTool implements IRegistryObject, IJavaDocs {

    @Nullable
    private ResourceLocation textureName = null;
    private boolean handheld = false;
    private float scale = 1.0f;

    public LibItemTool(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial, Set<Block> effectiveBlocksIn) {
        super(attackDamage, attackSpeed, toolMaterial, effectiveBlocksIn);
        setUnlocalizedName(LibRegistry.getModid() + "." + registryName);
        setRegistryName(registryName);
        setCreativeTab(CreativeTabs.MISC);
        Registry.register(this);
        setSingleTexture(new ResourceLocation(LibRegistry.getModid(), "items/" + registryName), true);
        if (this instanceof LibItemPickaxe)
        {
            toolClass = "pickaxe";
        }
        else if (this instanceof LibItemAxe)
        {
            toolClass = "axe";
        }
        else if (this instanceof LibItemShovel)
        {
            toolClass = "shovel";
        }

    }

    private String toolClass;
    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, @javax.annotation.Nullable net.minecraft.entity.player.EntityPlayer player, @javax.annotation.Nullable IBlockState blockState)
    {
        int level = super.getHarvestLevel(stack, toolClass,  player, blockState);
        if (level == -1 && toolClass.equals(this.toolClass))
        {
            return this.toolMaterial.getHarvestLevel();
        }
        else
        {
            return level;
        }
    }

    public LibItemTool(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial) {
        this(registryName, attackDamage, attackSpeed, toolMaterial, Collections.emptySet());
    }

    @Override
    public LibItemTool setSingleTexture(ResourceLocation textureName, boolean handheld) {
        this.textureName = textureName;
        this.handheld = handheld;
        return this;
    }

    @Override
    public LibItemTool setHandheld(boolean handheld) {
        this.handheld = handheld;
        return this;
    }

    @Override
    public LibItemTool setScale(float scale) {
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
