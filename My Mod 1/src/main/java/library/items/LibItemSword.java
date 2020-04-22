package library.items;

import com.google.common.collect.Multimap;
import library.LibRegistry;
import library.common.IJavaDocs;
import library.common.IRegistryObject;
import library.common.Registry;
import library.common.RegistryClientInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class LibItemSword extends ItemSword implements IRegistryObject, IJavaDocs {


    //private final Item.ToolMaterial material;

    public LibItemSword(String registryName, ToolMaterial material) {
        super(material);
        this.maxStackSize = 1;
        this.setMaxDamage(material.getMaxUses());
        this.attackDamage += material.getAttackDamage();
        setUnlocalizedName(LibRegistry.getModid() + "." + registryName);
        setRegistryName(registryName);
        setCreativeTab(CreativeTabs.MISC);
        Registry.register(this);
        setSingleTexture(new ResourceLocation(LibRegistry.getModid(), "items/" + registryName), true);
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    public float attackSpeed = -2.4F;
    public float attackDamage = 3.0F;

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double) attackSpeed, 0));
        }

        return multimap;
    }

    
    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        Block block = state.getBlock();

        if (block == Blocks.WEB) {
            return 15.0F;
        } else {
            Material material = state.getMaterial();
            return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
        }
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        return true;
    }

    /**
     * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
     */
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if ((double) state.getBlockHardness(worldIn, pos) != 0.0D) {
            stack.damageItem(2, entityLiving);
        }

        return true;
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        return blockIn.getBlock() == Blocks.WEB;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @Nullable
    private ResourceLocation textureName = null;
    private boolean handheld = false;
    private float scale = 1.0f;

    /**
     * Set a custom texture path for the item
     *
     * @param texturePath
     */
    public LibItemSword setTexturePath(String texturePath) {
        setSingleTexture(new ResourceLocation(LibRegistry.getModid(), texturePath), this.handheld);
        return this;
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
