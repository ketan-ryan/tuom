package com.mco.generation;

import java.util.Random;

import com.mco.dimensions.TUOMWorldGen;
import com.mco.main.TUOMBlocks;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OpalOreGen implements IWorldGenerator{

    private static final long SEED = 21052088057241959L;
	
	private static World worldObj;
	private static int globalX;
	private static int globalZ;
	private static BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
	{
		worldObj = world;
        random.setSeed(random.nextLong() ^ SEED);

        this.globalX = chunkX << 4;
        this.globalZ = chunkZ << 4;
        
		int id = world.provider.getDimension();
		
		if(id == -1) {
			generateNether();
		}
		else if(id == 0) {
			generateOverworld();
		}
		else if(id == 1) {
			generateEnd();
		}
		else if (id == TUOMWorldGen.OPAL_DIM_ID) {
			generateOpal();
		}
    }	
	
	private void generateNether() {}
	
	private void generateOverworld() 
	{        
        generateOres(65, 64, 16, 43, new WorldGenMinable(TUOMBlocks.GARNET_ORE.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE)));
        generateOres(3, 16, 16, 15, new WorldGenMinable(TUOMBlocks.TOPAZ_ORE.getDefaultState(), 10, BlockMatcher.forBlock(Blocks.STONE)));
        generateOres(2, 16, 16, 15, new WorldGenMinable(TUOMBlocks.FOPAL_ORE.getDefaultState(), 9, BlockMatcher.forBlock(Blocks.STONE)));
        generateOres(2, 24, 16, 20, new WorldGenMinable(TUOMBlocks.DOPAL_ORE.getDefaultState(), 12, BlockMatcher.forBlock(Blocks.STONE)));
        generateOres(1, 14, 16, 15, new WorldGenMinable(TUOMBlocks.LOPAL_ORE.getDefaultState(), 8, BlockMatcher.forBlock(Blocks.STONE)));
    }
	
	private void generateEnd() {}
	
	private void generateOpal() 
	{
        generateOres(80, 64, 16, 50, new WorldGenMinable(TUOMBlocks.DARK_COAL.getDefaultState(), 10, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE)));
        generateOres(60, 64, 16, 40, new WorldGenMinable(TUOMBlocks.DARK_IRON.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE)));
        generateOres(10, 32, 16, 30, new WorldGenMinable(TUOMBlocks.DARK_GOLD.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE)));
        generateOres(3, 16, 16, 15, new WorldGenMinable(TUOMBlocks.DARK_DIAMOND.getDefaultState(), 10, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE)));
        generateOres(27, 16, 16, 30, new WorldGenMinable(TUOMBlocks.DARK_REDSTONE.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE)));
        generateOres(5, 31, 16, 30, new WorldGenMinable(TUOMBlocks.DARK_LAPIS.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE)));
        generateOres(2, 32, 16, 12, new WorldGenMinable(TUOMBlocks.DARK_EMERALD.getDefaultState(), 2, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE)));
        
        generateOres(65, 64, 16, 43, new WorldGenMinable(TUOMBlocks.DARK_GARNET.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE)));
        generateOres(3, 16, 16, 15, new WorldGenMinable(TUOMBlocks.DARK_TOPAZ.getDefaultState(), 10, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE)));
        generateOres(2, 16, 16, 15, new WorldGenMinable(TUOMBlocks.DARK_FOPAL.getDefaultState(), 9, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE)));
        generateOres(5, 24, 16, 20, new WorldGenMinable(TUOMBlocks.DARK_DOPAL.getDefaultState(), 12, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE)));
        generateOres(1, 14, 16, 15, new WorldGenMinable(TUOMBlocks.DARK_LOPAL.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE)));
	}
	
	private static void generateOres(int numPerChunk, int offY, int offZ, int chance, WorldGenMinable gen) 
	{
		for(int i = 0; i < numPerChunk; i++) 
		{
			if(new Random().nextInt(100) < chance) 
			{
				int offsetX = new Random().nextInt(16);
				int offsetY = new Random().nextInt(offY);
				int offsetZ = new Random().nextInt(offZ);
				
				pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
				gen.generate(worldObj, new Random(), pos);
			}
		}
	}
}
