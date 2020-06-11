package library.blocks;

import library.LibRegistry;
import library.common.IRegistryObject;
import library.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import com.mco.TUOM;

import java.lang.reflect.Constructor;

public class LibBlockSimple extends Block implements IRegistryObject, ITileEntityProvider {

    /** Methods to Override **/

    public static int guiIDToUse = 0;

    /**
     * Override this method if you want to implement a recipe for this block
     */
    @Override
    public void initRecipe() {
    }

    /** Constructors **/

    public LibBlockSimple() {
        this(-1, null);
    }

    public LibBlockSimple(int guiID, Class<? extends TileEntity> tileEntityClass) {
        super(Material.ROCK);
        this.guiID = guiID;
        this.tileEntityClass = tileEntityClass;
    }


    /** Internal methods **/

    protected final int guiID;
    protected final Class tileEntityClass;

    @Override
    public String getUniqueName() {
        return getRegistryName().toString();
    }

    @Override
    @SideOnly(Side.CLIENT)
	public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        if (tileEntityClass == null) {
            return null;
        }
        try {
            return (TileEntity)tileEntityClass.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (guiID == -1) {
            return false;
        }

        if (worldIn.isRemote) return true;

        TileEntity te = worldIn.getTileEntity(pos);
        if (te.getClass().isAssignableFrom(tileEntityClass)) {
            playerIn.openGui(LibRegistry.getMod(), guiID, worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        } else {
            return false;
        }
    }

    public boolean patternMatches(BlockPattern pattern, World worldIn, BlockPos pos) {
        return pattern.match(worldIn, pos) != null;
    }

    public void replacePatternWithEntity(BlockPattern pattern, World worldIn, BlockPos pos, Class<? extends Entity> entityClass) {
        BlockPattern.PatternHelper patternHelper = pattern.match(worldIn, pos);

        if (patternHelper != null) {
            for (int j = 0; j < pattern.getPalmLength(); ++j) {
                for (int k = 0; k < pattern.getThumbLength(); ++k) {
                    BlockWorldState blockworldstate1 = patternHelper.translateOffset(j, k, 0);
                    worldIn.setBlockState(blockworldstate1.getPos(), Blocks.AIR.getDefaultState(), 2);
                }
            }

            try {
                Constructor constructor = entityClass.getConstructor(World.class);
                Entity entity = (Entity) constructor.newInstance(worldIn);

                BlockPos blockpos1 = patternHelper.translateOffset(0, 2, 0).getPos();
                entity.setLocationAndAngles((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.05D, (double) blockpos1.getZ() + 0.5D, 0.0F, 0.0F);                
                if (entity instanceof EntityLiving) {
                     ((EntityLiving) entity).onInitialSpawn(worldIn.getDifficultyForLocation(blockpos1), null);
                }
                worldIn.spawnEntity(entity);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }


}