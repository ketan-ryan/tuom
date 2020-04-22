package com.mco.blocks.crops;

import java.util.Random;

import library.LibRegistry;
import library.blocks.LibBlockCrops;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FopalCrop extends LibBlockCrops 
{
	private static final AxisAlignedBB[] FIRE_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D)};
	private int growthRate;

	public FopalCrop(String registryName) 
	{
		super(registryName);
	}

	@Override
	protected int getGrowthDelay() 
	{
		return growthRate;
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
		for (int i = 0; i < 4; ++i)
        {
            BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
            IBlockState iblockstate = worldIn.getBlockState(blockpos);
            IBlockState iblockstate1 = worldIn.getBlockState(blockpos.up());
            
            if (!(iblockstate.getBlock() == Blocks.FIRE && worldIn.getLightFromNeighbors(blockpos.up()) >= 4))
            {
                growthRate = 1200;
            }
            else
            	growthRate = 2400;
          //  System.out.println(growthRate);
        }		
    }

	@Override
	protected Item getSeed() 
	{
		return TUOMItems.fopal_seeds;
	}

	@Override
	protected Item getCrop() 
	{
		return TUOMItems.fopal_shard;
	}	
}
