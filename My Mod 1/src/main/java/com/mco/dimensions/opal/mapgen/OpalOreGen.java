package com.mco.dimensions.opal.mapgen;

import java.util.Random;

import com.mco.main.TUOMBlocks;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OpalOreGen implements IWorldGenerator{

    private static final long SEED = 21052088057241959L;

    private static final WorldGenMinable COAL_GENERATOR = new WorldGenMinable(TUOMBlocks.DARK_COAL.getDefaultState(), 10, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE));
    private static final WorldGenMinable IRON_GENERATOR = new WorldGenMinable(TUOMBlocks.DARK_IRON.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE));
    private static final WorldGenMinable GOLD_GENERATOR = new WorldGenMinable(TUOMBlocks.DARK_GOLD.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE));
    private static final WorldGenMinable DIAMOND_GENERATOR = new WorldGenMinable(TUOMBlocks.DARK_DIAMOND.getDefaultState(), 10, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE));
    private static final WorldGenMinable REDSTONE_GENERATOR = new WorldGenMinable(TUOMBlocks.DARK_REDSTONE.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE));
    private static final WorldGenMinable LAPIS_GENERATOR = new WorldGenMinable(TUOMBlocks.DARK_LAPIS.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE));
    private static final WorldGenMinable EMERALD_GENERATOR = new WorldGenMinable(TUOMBlocks.DARK_EMERALD.getDefaultState(), 2, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE));

    private static final WorldGenMinable GARNET_GENERATOR = new WorldGenMinable(TUOMBlocks.DARK_GARNET.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE));
    private static final WorldGenMinable TOPAZ_GENERATOR = new WorldGenMinable(TUOMBlocks.DARK_TOPAZ.getDefaultState(), 10, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE));
    private static final WorldGenMinable FOPAL_GENERATOR = new WorldGenMinable(TUOMBlocks.DARK_FOPAL.getDefaultState(), 9, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE));
    private static final WorldGenMinable DOPAL_GENERATOR = new WorldGenMinable(TUOMBlocks.DARK_DOPAL.getDefaultState(), 12, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE));
    private static final WorldGenMinable LOPAL_GENERATOR = new WorldGenMinable(TUOMBlocks.DARK_LOPAL.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.DARK_STONE));
    
	private static final int COAL_PER_CHUNK = 80;
	private static final int IRON_PER_CHUNK = 60;
	private static final int GOLD_PER_CHUNK = 10;
	private static final int DIAMOND_PER_CHUNK = 3;
	private static final int REDSTONE_PER_CHUNK = 27;
	private static final int LAPIS_PER_CHUNK = 5;
	private static final int EMERALD_PER_CHUNK = 2;

	private static final int GARNET_PER_CHUNK = 65;
	private static final int TOPAZ_PER_CHUNK = 3;
	private static final int FOPAL_PER_CHUNK = 2;
	private static final int DOPAL_PER_CHUNK = 5;
	private static final int LOPAL_PER_CHUNK = 1;
	
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
        
        generateOres(COAL_PER_CHUNK, 64, 16, 50, COAL_GENERATOR);
        generateOres(IRON_PER_CHUNK, 64, 16, 40, IRON_GENERATOR);
        generateOres(GOLD_PER_CHUNK, 32, 16, 30, GOLD_GENERATOR);
        generateOres(DIAMOND_PER_CHUNK, 16, 16, 15, DIAMOND_GENERATOR);
        generateOres(REDSTONE_PER_CHUNK, 16, 16, 30, REDSTONE_GENERATOR);
        generateOres(LAPIS_PER_CHUNK, 31, 16, 30, LAPIS_GENERATOR);
        generateOres(EMERALD_PER_CHUNK, 32, 16, 12, EMERALD_GENERATOR);
        
        generateOres(GARNET_PER_CHUNK, 64, 16, 43, GARNET_GENERATOR);
        generateOres(TOPAZ_PER_CHUNK, 16, 16, 15, TOPAZ_GENERATOR);
        generateOres(FOPAL_PER_CHUNK, 16, 16, 15, FOPAL_GENERATOR);
        generateOres(DOPAL_PER_CHUNK, 24, 16, 20, DOPAL_GENERATOR);
        generateOres(LOPAL_PER_CHUNK, 14, 16, 15, LOPAL_GENERATOR);
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
