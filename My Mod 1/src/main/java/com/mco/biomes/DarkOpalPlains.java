package com.mco.biomes;

import java.util.Random;

import com.mco.main.TUOMBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;

public class DarkOpalPlains extends LibBiomeDark
{
	public DarkOpalPlains(String registryName, BiomeProperties properties) 
	{
		super(registryName, properties);
		this.topBlock = Blocks.GRASS.getDefaultState();
		this.fillerBlock = TUOMBlocks.DARK_STONE.getDefaultState();

		this.setGrassColor(0x36004F);
		this.setFoliageColor(0x36004F);
		this.setSkyColor(0x36004F);
		
	}

	public void genTerrainBlocks(final World world, final Random rand, final ChunkPrimer primer, final int x, final int z, final double noiseVal) 
    {
		this.genDarkBiomeTerrain(world, rand, primer, x, z, noiseVal);
	}
	
	@Override
	public BiomeDecorator createBiomeDecorator()
	{
		super.createBiomeDecorator();
		BiomeDecorator biomeDecorator = new DarkOpalBiomeDecorator();
		
		return getModdedBiomeDecorator(biomeDecorator);
	}	    
	
	/**
     * Given x, z coordinates, we count down all the y positions starting at 255 and working our way down. When we hit a
     * non-air block, we replace it with this.topBlock (default grass, descendants may set otherwise), and then a
     * relatively shallow layer of blocks of type this.fillerBlock (default dirt). A random set of blocks below y == 5
     * (but always including y == 0) is replaced with bedrock.
     *  
     * If we don't hit non-air until somewhat below sea level, we top with gravel and fill down with stone.
     *  
     * If this.fillerBlock is red sand, we replace some of that with red sandstone.
     */
    protected void genDarkBiomeTerrain(final World worldIn, final Random rand, final ChunkPrimer chunkPrimerIn, final int x, final int z, final double noiseVal) 
    {
		final int i = worldIn.getSeaLevel();
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate2 = this.fillerBlock;
		int j = -1;
		final int k = (int) (noiseVal / 3.0 + 3.0 + rand.nextDouble() * 0.25);
		final int l = x & 0xF;
		final int i2 = z & 0xF;
		new BlockPos.MutableBlockPos();
		for (int j2 = 255; j2 >= 0; --j2) 
		{
			if (j2 <= rand.nextInt(5)) 
			{
				chunkPrimerIn.setBlockState(i2, j2, l, Blocks.BEDROCK.getDefaultState());
			} else 
			{
				final IBlockState iblockstate3 = chunkPrimerIn.getBlockState(i2, j2, l);
				if (iblockstate3.getMaterial() == Material.AIR) 
				{
					j = -1;
				} else if (iblockstate3.getBlock() == TUOMBlocks.DARK_STONE) 
				{
					if (j == -1) 
					{
						if (k <= 0)
						{
							iblockstate = Blocks.AIR.getDefaultState();
							iblockstate2 = TUOMBlocks.DARK_STONE.getDefaultState();
						} else if (j2 >= i - 4 && j2 <= i + 1) 
						{
							iblockstate = this.topBlock;
							iblockstate2 = this.fillerBlock;
						}
						j = k;
						if (j2 >= i - 1) 
						{
							chunkPrimerIn.setBlockState(i2, j2, l, iblockstate);
						} else if (j2 < i - 7 - k) 
						{
							iblockstate = Blocks.AIR.getDefaultState();
							iblockstate2 = TUOMBlocks.DARK_STONE.getDefaultState();
							chunkPrimerIn.setBlockState(i2, j2, l, TUOMBlocks.DARK_STONE.getDefaultState());
						} else 
						{
							chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						}
					} else if (j > 0) 
					{
						--j;
						chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						if (j == 0 && (iblockstate2.getBlock() == Blocks.SAND) && k > 1) 
						{
							j = rand.nextInt(4) + Math.max(0, j2 - 88);
							iblockstate2 = Blocks.SANDSTONE.getDefaultState();
						}
					}
				}
			}
		}
	}
	
	
}
