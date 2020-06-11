package com.mco.dimensions.opal.mapgen;

import com.mco.main.TUOMBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.MapGenCaves;

public class OpalMapGenCaves extends MapGenCaves{
	
	@Override
	protected boolean canReplaceBlock(IBlockState state, IBlockState aboveState) {
		if (state.getBlock() == TUOMBlocks.DARK_STONE || state.getBlock() == Blocks.GRASS)
        {          
            return true;
        }
		else
        {
            return (state.getBlock() == Blocks.SAND || state.getBlock() == Blocks.GRAVEL) && state.getMaterial() != Material.WATER;
        }
	}
}
