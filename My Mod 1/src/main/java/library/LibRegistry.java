package library;

import com.google.common.collect.ImmutableList;
import library.biomes.LibBiome;
import library.entities.*;
import library.entities.LibEntityEffect;
import library.gui.LibGUIHandler;
import library.world.LibWorldGen;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LibRegistry {

    private static String modid;
    private static Object mod;

    private static int mobID = 0;

    public static void initialize(String modid, Object mod) {
        LibRegistry.modid = modid;
        LibRegistry.mod = mod;
    }

    public static String getModid() {
        return modid;
    }

    public static void setModid(String modid) {
        LibRegistry.modid = modid;
    }

    public static Object getMod() {
        return mod;
    }

    public static void setMod(Object mod) {
        LibRegistry.mod = mod;
    }

    /**
     * Register an entity with a name, class and egg colors
     *
     * @param name
     * @param entityClass
     * @param eggPrimary
     * @param eggSecondary
     */
    public static void registerEntity(String name, Class<? extends Entity> entityClass, int eggPrimary, int eggSecondary) {
        EntityRegistry.registerModEntity(new ResourceLocation(modid, name),
                entityClass, name, mobID++, mod, 64, 3, true, eggPrimary, eggSecondary);

    }

    /**
     * Register an entity with a name and class
     *
     * @param name
     * @param entityClass
     */
    public static void registerEntity(String name, Class<? extends Entity> entityClass) {
        EntityRegistry.registerModEntity(new ResourceLocation(modid, name),
                entityClass, name, mobID++, mod, 64, 3, true);
    }

    /**
     * Register a projectile based entity with a custom model
     *
     * @param entityClass
     * @param modelClass
     * @param scale
     */
    @SideOnly(Side.CLIENT)
    public static void registerProjectileCustomModel(Class<? extends Entity> entityClass,
                                                     Class<? extends ModelBase> modelClass,
                                                     float scale) {

        try {
            Constructor constructor = modelClass.getConstructor();
            ModelBase model = (ModelBase) constructor.newInstance();

            RenderingRegistry.registerEntityRenderingHandler(entityClass, manager -> {
                return new LibRender(manager, entityClass, model)
                        .setTexture(new ResourceLocation(modid, "textures/entities/" + "projectiles" + "/" + EntityRegistry.getEntry(entityClass).getName() + ".png"))
                        .setScale(scale);
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Register a generic renderer for an entity.
     *
     * @param entityClass
     * @param renderClass
     */
    @SideOnly(Side.CLIENT)
    public static void registerRenderer(Class<? extends Entity> entityClass, Class<? extends Render> renderClass) {
        IRenderFactory factory = manager -> {
            try {
                Constructor<? extends Render> constructor = renderClass.getConstructor(RenderManager.class);
                return constructor.newInstance(manager);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        };
        RenderingRegistry.registerEntityRenderingHandler(entityClass, factory);
    }

    @SideOnly(Side.CLIENT)
    private static void registerEntityModelCategory(Class<? extends EntityLiving> entityClass,
                                                    Class<? extends ModelBase> modelClass,
                                                    String category,
                                                    float scale) {
        if (EntityRegistry.getEntry(entityClass) != null) {
            registerEntityModelTexture(entityClass, modelClass, "textures/entities/" + category + "/" + EntityRegistry.getEntry(entityClass).getName() + ".png", scale);
        } else {
            System.out.println("Error trying to register renderer for " + entityClass + ", you probably haven't registered the entity yet!");
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerEntityModelTexture(Class<? extends EntityLiving> entityClass,
                                                   Class<? extends ModelBase> modelClass,
                                                   String texture,
                                                   float scale) {

        try {
            Constructor constructor = modelClass.getConstructor();
            ModelBase model = (ModelBase) constructor.newInstance();

            RenderingRegistry.registerEntityRenderingHandler(entityClass, manager -> {
                return new LibRenderMob(manager, entityClass, model)
                        .setTexture(new ResourceLocation(modid, texture))
                        .setScale(scale);
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Register an arrow renderer for an entity projectile based class
     *
     * @param entityClass
     */
    @SideOnly(Side.CLIENT)
    public static void registerProjectileModel(Class entityClass) {
        //registerRenderer(entityClass, renderClass);
        RenderingRegistry.registerEntityRenderingHandler(entityClass, manager -> {
            return new LibRenderArrow(manager, new ResourceLocation(modid, "textures/entities/" + "projectiles" +
                    "/" + EntityRegistry.getEntry(entityClass).getName() + ".png"));
        });
    }

    /**
     * Register an arrow renderer for a projectile based class with a custom arrow texture
     *
     * The final parameter for texture will look in the directory assets/mymod" so
     * for this parameter you need to add "textures/" + the path to your file, for
     * example: "textures/entities/projectiles/my_entity_arrow_1.png"
     *
     * @param entityClass
     * @param texture
     */
    @SideOnly(Side.CLIENT)
    public static void registerCustomTexturePathProjectileModel(Class entityClass, String texture) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, manager -> {
            return new LibRenderArrow(manager, new ResourceLocation(modid, texture));
        });
    }

    /**
     * Register an item sprite renderer for an entity projectile based class
     *
     * @param entityClass
     * @param renderer
     */
    @SideOnly(Side.CLIENT)
    public static void registerProjectileSprite(Class<? extends Entity> entityClass, float scale) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, manager -> {
            return new LibRenderSprite(manager, new ResourceLocation(modid, "textures/entities/" + "projectiles" +
                    "/" + EntityRegistry.getEntry(entityClass).getName() + ".png"), scale);
        });
    }
    /*@SideOnly(Side.CLIENT)
    public static void registerProjectileSprite(Class<? extends Entity> entityClass, IRenderFactory renderer) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderer);
    }*/

    /**
     * Register a model and set the scale for a mob entity class.
     *
     * @param entityClass
     * @param model
     * @param scale
     */
    @SideOnly(Side.CLIENT)
    public static void registerMobModel(Class<? extends EntityLiving> entityClass,
                                        Class<? extends ModelBase> modelClass,
                                        float scale) {
        registerEntityModelCategory(entityClass, modelClass, "mobs", scale);
    }

    /**
     * Register a model and set the scale and custom texture for a mob entity class.
     *
     * The final parameter for texture will look in the directory assets/mymod" so
     * for this parameter you need to add "textures/" + the path to your file, for
     * example: "textures/entities/mobs/my_mob_1.png"
     *
     * @param entityClass
     * @param model
     * @param scale
     * @param texture
     */
    @SideOnly(Side.CLIENT)
    public static void registerCustomTexturePathMobModel(Class<? extends EntityLiving> entityClass,
                                                         Class<? extends ModelBase> modelClass,
                                                         float scale,
                                                         String texture) {
        registerEntityModelTexture(entityClass, modelClass, texture, scale);
    }

    /**
     * Register a model and set the scale for a vehicle entity class.
     *
     * @param entityClass
     * @param model
     * @param scale
     */
    @SideOnly(Side.CLIENT)
    public static void registerVehicleModel(Class<? extends EntityLiving> entityClass,
                                        Class<? extends ModelBase> modelClass,
                                        float scale) {
        registerEntityModelCategory(entityClass, modelClass, "vehicles", scale);
    }

    /**
     * Register a model and set the scale and custom texture for a vehicle entity class.
     *
     * The final parameter for texture will look in the directory assets/mymod" so
     * for this parameter you need to add "textures/" + the path to your file, for
     * example: "textures/entities/vehicles/my_vehicle_1.png"
     *
     * @param entityClass
     * @param model
     * @param scale
     * @param texture
     */
    @SideOnly(Side.CLIENT)
    public static void registerCustomTexturePathVehicleModel(Class<? extends EntityLiving> entityClass,
                                            Class<? extends ModelBase> modelClass,
                                            float scale, String texture) {
        registerEntityModelTexture(entityClass, modelClass, texture, scale);
    }

    /**
     * Register a biome to be generated
     *
     * @param biome
     * @param weight Recommended values: 1 to 100, higher means more likely to generate
     */
    public static void registerBiome(Biome biome, int weight) {
        ForgeRegistries.BIOMES.register(biome);
        if (biome instanceof LibBiome) {
            BiomeManager.addBiome(((LibBiome)biome).getType(), new BiomeManager.BiomeEntry(biome, weight));
        } else {
            BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biome, weight));
        }
        BiomeProvider.allowedBiomes.add(biome);
    }

    /**
     * Replace almost all vanilla biomes with your own biome to help find it easier in the world
     *
     * @param biome
     */
    public static void replaceAllBiomesWith(Biome biome) {
        ImmutableList<BiomeManager.BiomeEntry> biomes = BiomeManager.getBiomes(BiomeManager.BiomeType.COOL);
        for (BiomeManager.BiomeEntry entry : biomes) {
            BiomeManager.removeBiome(BiomeManager.BiomeType.COOL, entry);
        }
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 10));

        biomes = BiomeManager.getBiomes(BiomeManager.BiomeType.WARM);
        for (BiomeManager.BiomeEntry entry : biomes) {
            BiomeManager.removeBiome(BiomeManager.BiomeType.WARM, entry);
        }
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biome, 10));

        biomes = BiomeManager.getBiomes(BiomeManager.BiomeType.ICY);
        for (BiomeManager.BiomeEntry entry : biomes) {
            BiomeManager.removeBiome(BiomeManager.BiomeType.ICY, entry);
        }
        BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(biome, 30));

        biomes = BiomeManager.getBiomes(BiomeManager.BiomeType.DESERT);
        for (BiomeManager.BiomeEntry entry : biomes) {
            BiomeManager.removeBiome(BiomeManager.BiomeType.DESERT, entry);
        }
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(biome, 10));

        BiomeProvider.allowedBiomes.clear();

    }

    /**
     * Register an ore gen for your block using the standard vein ore gen feature. chance, veinSize and veinCount will slow your game down the higher the values are
     *
     * @param state
     * @param chance    Percent chance to gen per chunk, ranging from 0 to 100
     * @param minHeight Min Y height to gen at, ranging from 0 to 255
     * @param maxHeight Max Y height to gen at, ranging from 0 to 255
     * @param veinSize  How many connected blocks generated per vein, suggested ranges: 0 to 12
     * @param veinCount How many veins generated per chunk, suggested ranges: 0 to 4
     */
    public static void registerOreGen(IBlockState state, int chance, int minHeight, int maxHeight, int veinSize, int veinCount) {
        GameRegistry.registerWorldGenerator(new LibWorldGen(state, chance, minHeight, maxHeight, veinSize, veinCount), 100);
    }

    /**
     * Register a GUI container for a tile entity, with custom texture
     *
     * @param tile
     * @param container
     * @param textureGUI
     */
    public static void registerGUI(Class<? extends TileEntity> tile, Class<? extends Container> container, String textureGUI) {
        LibGUIHandler.registerGUI(tile, container, textureGUI);
    }

    private static ItemStack getStack(Object blockOrItem, int count) {
        ItemStack stack = null;
        if (blockOrItem instanceof Item) {
            stack = new ItemStack((Item)blockOrItem, count);
        } else if (blockOrItem instanceof Block) {
            stack = new ItemStack((Block)blockOrItem, count);
        } else if (blockOrItem instanceof ItemStack) {
            stack = (ItemStack)blockOrItem;
        }
        return stack;
    }

    /**
     * Add a shaped recipe for your item
     *
     * @param output Block, Item, or ItemStack
     * @param count Ranging from 1 to 64
     * @param params Uses the 9x9 vanilla recipe format
     */
    public static void addShapedRecipe(Object output, int count, Object... params) {
        String name = getStack(output, count).getItem().getRegistryName().getResourcePath();
        try {
            GameRegistry.addShapedRecipe(new ResourceLocation(modid, name + "_recipe"),
                    new ResourceLocation(modid, "shaped_recipes"), getStack(output, count), params);
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("========================================");
            System.out.println("Recipe for " + name + " is invalid");
            System.out.println("========================================");
        }
    }

    /**
     * Add a shapeless recipe for your item
     *
     * @param output Block, Item, or ItemStack
     * @param count Ranging from 1 to 64
     * @param params List of Ingredient items, eg: Ingredient.fromItem(MyItem.item)
     */
    public static void addShapelessRecipe(Object output, int count, Ingredient... params) {
        String name = getStack(output, count).getItem().getRegistryName().getResourcePath();
        try {
            GameRegistry.addShapelessRecipe(new ResourceLocation(modid, name + "_recipe"),
                    new ResourceLocation(modid, "shapeless_recipes"), getStack(output, count), params);
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("========================================");
            System.out.println("Recipe for " + name + " is invalid");
            System.out.println("========================================");
        }
    }

    /**
     * Add a smelting recipe for the vanilla furnace
     *
     * @param output Block, Item, or ItemStack
     * @param count Ranging from 1 to 64
     * @param xp Experience gained from smelting item
     * @param input Block, Item, or ItemStack
     */
    public static void addSmeltingRecipe(Object output, int count, float xp, Object input) {
        String name = getStack(output, count).getItem().getRegistryName().getResourcePath();
        try {
            GameRegistry.addSmelting(getStack(input, 1), getStack(output, count), xp);
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("========================================");
            System.out.println("Recipe for " + name + " is invalid");
            System.out.println("========================================");
        }
    }

    /**
     * Register a custom armor material
     *
     * @param name
     * @param durability
     * @param reductionAmounts
     * @param enchantability
     * @param soundOnEquip
     * @param toughness
     * @return
     */
    public static ItemArmor.ArmorMaterial registerArmorMaterial(String name, int durability, int[] reductionAmounts, int enchantability, SoundEvent soundOnEquip, float toughness) {
        return EnumHelper.addArmorMaterial(name, name, durability, reductionAmounts, enchantability, soundOnEquip, toughness);
    }

    /**
     * Register a custom tool material
     *
     * @param name
     * @param harvestLevel
     * @param maxUses
     * @param efficiency
     * @param damage
     * @param enchantability
     * @return
     */
    public static Item.ToolMaterial registerToolMaterial(String name, int harvestLevel, int maxUses, float efficiency, float damage, int enchantability) {
        return EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability);
    }

    /**
     * Register an entity to be spawned for a list of biomes
     *
     * @param entityClass
     * @param weightedProb
     * @param min
     * @param max
     * @param typeOfCreature
     * @param biomes
     */
    public static void registerEntitySpawn(Class <? extends EntityLiving > entityClass, int weightedProb, int min, int max, EnumCreatureType typeOfCreature, Biome... biomes) {
        EntityRegistry.addSpawn(entityClass, weightedProb, min, max, typeOfCreature, biomes);
    }

    /**
     * Not for student use!
     */
    @SideOnly(Side.CLIENT)
    public static void registerEntityModels() {
        try {

            RenderingRegistry.registerEntityRenderingHandler(LibEntityEffect.class, manager -> {
                return new LibRenderEffect(manager, LibEntityEffect.class)
                        .setScale(1F);
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Not for student use!
     */
    public static void preInit() {
        LibRegistry.registerEntity("lib_entity_effect", LibEntityEffect.class);
    }
}
