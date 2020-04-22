package com.mco.dimensions.opal;

import java.util.Random;

import library.blocks.LibBlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenCrops 
{
	private LibBlockCrops flower;
    private IBlockState state;

    public WorldGenCrops(LibBlockCrops flowerIn)
    {
        this.setGeneratedBlock(flowerIn);
    }

    public void setGeneratedBlock(LibBlockCrops flowerIn)
    {
        this.flower = flowerIn;
        this.state = flowerIn.getDefaultState();
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 255) && this.flower.canBlockStay(worldIn, blockpos, this.state))
            {
                worldIn.setBlockState(blockpos, this.state, 2);
            }
        }

        return true;
    }
}
