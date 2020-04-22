package com.mco.dimensions.opal.mapgen;

import java.util.Random;

import com.mco.main.TUOMBlocks;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OpalOreGen implements IWorldGenerator{

    private static final long SEED = 21052088057241959L;

    private static final WorldGenerator COAL_GENERATOR = new WorldGenMinable(TUOMBlocks.dark_coal.getDefaultState(), 10, BlockMatcher.forBlock(TUOMBlocks.dark_stone));
    private static final WorldGenerator IRON_GENERATOR = new WorldGenMinable(TUOMBlocks.dark_iron.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.dark_stone));
    private static final WorldGenerator GOLD_GENERATOR = new WorldGenMinable(TUOMBlocks.dark_gold.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.dark_stone));
    private static final WorldGenerator DIAMOND_GENERATOR = new WorldGenMinable(TUOMBlocks.dark_diamond.getDefaultState(), 10, BlockMatcher.forBlock(TUOMBlocks.dark_stone));
    private static final WorldGenerator REDSTONE_GENERATOR = new WorldGenMinable(TUOMBlocks.dark_redstone.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.dark_stone));
    private static final WorldGenerator LAPIS_GENERATOR = new WorldGenMinable(TUOMBlocks.dark_lapis.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.dark_stone));
    private static final WorldGenerator EMERALD_GENERATOR = new WorldGenMinable(TUOMBlocks.dark_emerald.getDefaultState(), 2, BlockMatcher.forBlock(TUOMBlocks.dark_stone));

    private static final WorldGenerator GARNET_GENERATOR = new WorldGenMinable(TUOMBlocks.dark_garnet.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.dark_stone));
    private static final WorldGenerator TOPAZ_GENERATOR = new WorldGenMinable(TUOMBlocks.dark_topaz.getDefaultState(), 10, BlockMatcher.forBlock(TUOMBlocks.dark_stone));
    private static final WorldGenerator FOPAL_GENERATOR = new WorldGenMinable(TUOMBlocks.dark_fopal.getDefaultState(), 9, BlockMatcher.forBlock(TUOMBlocks.dark_stone));
    private static final WorldGenerator DOPAL_GENERATOR = new WorldGenMinable(TUOMBlocks.dark_dopal.getDefaultState(), 12, BlockMatcher.forBlock(TUOMBlocks.dark_stone));
    private static final WorldGenerator LOPAL_GENERATOR = new WorldGenMinable(TUOMBlocks.dark_lopal.getDefaultState(), 8, BlockMatcher.forBlock(TUOMBlocks.dark_stone));

    
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

    public void generate(Random random, int chunkX, int chunkZ, World world) {

        random.setSeed(random.nextLong() ^ SEED);

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        int globalX = chunkX << 4;
        int globalZ = chunkZ << 4;

        for (int i = 0; i < COAL_PER_CHUNK; i++) {
        	if(random.nextInt(100) < 50) {
	        	int offsetX = random.nextInt(16);
	            int offsetY = random.nextInt(64);
	            int offsetZ = random.nextInt(16);
            	pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
            	COAL_GENERATOR.generate(world, random, pos);
            }
        }
        for (int f = 0; f < IRON_PER_CHUNK; f++) {
            if(random.nextInt(100) < 40) {
	        	int offsetX = random.nextInt(16);
	            int offsetY = random.nextInt(64);
	            int offsetZ = random.nextInt(16);
	            pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
	            if(random.nextInt(10) < 7)
	            IRON_GENERATOR.generate(world, random, pos);
            }
        }
        for (int f = 0; f < GOLD_PER_CHUNK; f++) {
        	if(random.nextInt(100) < 30) {
	            int offsetX = random.nextInt(16);
	            int offsetY = random.nextInt(32);
	            int offsetZ = random.nextInt(16);
	            pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
	            GOLD_GENERATOR.generate(world, random, pos);
        	}
    	}
        for (int f = 0; f < DIAMOND_PER_CHUNK; f++) {
        	if(random.nextInt(100) < 15) {
	            int offsetX = random.nextInt(16);
	            int offsetY = random.nextInt(16);
	            int offsetZ = random.nextInt(16);
	            pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
	            DIAMOND_GENERATOR.generate(world, random, pos);
            }
        }
        for (int f = 0; f < REDSTONE_PER_CHUNK; f++) {
        	if(random.nextInt(100) < 30) {
	            int offsetX = random.nextInt(16);
	            int offsetY = random.nextInt(16);
	            int offsetZ = random.nextInt(16);
	            pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
	            REDSTONE_GENERATOR.generate(world, random, pos);
        	}
    	}
        for (int f = 0; f < LAPIS_PER_CHUNK; f++) {
        	if(random.nextInt(100) < 30) {
	            int offsetX = random.nextInt(16);
	            int offsetY = random.nextInt(31);
	            int offsetZ = random.nextInt(16);
	            pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
	            LAPIS_GENERATOR.generate(world, random, pos);
	        }
    	}
        for (int f = 0; f < EMERALD_PER_CHUNK; f++) {
            int offsetX = random.nextInt(16);
            int offsetY = random.nextInt(32);
            int offsetZ = random.nextInt(16);
            pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
            EMERALD_GENERATOR.generate(world, random, pos);
        }
        for (int f = 0; f < GARNET_PER_CHUNK; f++) {
            if(random.nextInt(100) < 43) {
	        	int offsetX = random.nextInt(16);
	            int offsetY = random.nextInt(64);
	            int offsetZ = random.nextInt(16);
	            pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
	            if(random.nextInt(10) < 7)
	            GARNET_GENERATOR.generate(world, random, pos);
            }
        }
        for (int f = 0; f < TOPAZ_PER_CHUNK; f++) {
        	if(random.nextInt(100) < 15) {
	            int offsetX = random.nextInt(16);
	            int offsetY = random.nextInt(16);
	            int offsetZ = random.nextInt(16);
	            pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
	            TOPAZ_GENERATOR.generate(world, random, pos);
            }
        }
        for (int f = 0; f < FOPAL_PER_CHUNK; f++) {
        	if(random.nextInt(100) < 15) {
	            int offsetX = random.nextInt(16);
	            int offsetY = random.nextInt(16);
	            int offsetZ = random.nextInt(16);
	            pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
	            FOPAL_GENERATOR.generate(world, random, pos);
        	}
        }
        for (int f = 0; f < DOPAL_PER_CHUNK; f++) {
        	if(random.nextInt(100) < 20) {
	            int offsetX = random.nextInt(16);
	            int offsetY = random.nextInt(24);
	            int offsetZ = random.nextInt(16);
	            pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
	            DOPAL_GENERATOR.generate(world, random, pos);
            }
        }
        for (int f = 0; f < LOPAL_PER_CHUNK; f++) {
        	if(random.nextInt(100) < 15) {
	            int offsetX = random.nextInt(16);
	            int offsetY = random.nextInt(14);
	            int offsetZ = random.nextInt(16);
	            pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
	            LOPAL_GENERATOR.generate(world, random, pos);
            }
        }
    }

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		// TODO Auto-generated method stub
		
	}
	
}
