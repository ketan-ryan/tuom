package com.mco.blocks.biomes.dark;

import library.blocks.LibBlockSimple;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class DarkObsidian extends LibBlockSimple
{

	public DarkObsidian(String registryName) 
	{
		super(registryName);
		this.setHardness(50F);
		this.setResistance(2500F);
		this.setSoundType(SoundType.STONE);
	}

	/**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.PURPLE;
    }
	
}
