package library.blocks;

import library.LibRegistry;
import library.common.IRegistryObject;
import library.common.Registry;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public abstract class LibBlockCrops extends BlockCrops implements IRegistryObject {

    public LibBlockCrops(String registryName) {
        super();
        setUnlocalizedName(LibRegistry.getModid() + "." + registryName);
        setRegistryName(registryName);
        setCreativeTab(CreativeTabs.MISC);
        Registry.register(this);
    }

    /**
     * Get the delay in ticks between crop growth stages
     *
     * @return Recommended ranges: 1 to 1000
     */
    protected abstract int getGrowthDelay();

    /**
     * Get the seed item to drop when you break a crop that isn't fully grown
     *
     * @return
     */
    @Override
    protected abstract Item getSeed();

    /**
     * Get the crop item to drop when you break a crop that is fully grown
     *
     * @return
     */
    @Override
    protected abstract Item getCrop();

    @Override
    public String getUniqueName() {
        return getRegistryName().toString();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }

    @Override
    public void initRecipe() {

    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        //super.updateTick(worldIn, pos, state, rand);
        this.checkAndDropBlock(worldIn, pos, state);

        if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
        {
            int i = this.getAge(state);

            if (i < this.getMaxAge())
            {
                worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                worldIn.scheduleBlockUpdate(pos, this, getGrowthDelay(), 1);
                /*float f = getGrowthChance(this, worldIn, pos);

                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0))
                {

                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }*/
            }
        }
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.scheduleBlockUpdate(pos, this, getGrowthDelay(), 1);
        super.onBlockAdded(worldIn, pos, state);
    }
}
