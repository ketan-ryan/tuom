package com.mco.generation;

import java.util.Random;

import com.google.common.base.Predicate;
import com.mco.dimensions.TUOMWorldGen;
import com.mco.dimensions.opal.mapgen.OpalOreGen;
import com.mco.main.TUOMBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OpalWorldGenerator extends WorldGenerator implements IWorldGenerator
{	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;

		int id = world.provider.getDimension();
		if(id == -1)
		{
		}
		else if(id == 0)
		{
			//           block to gen, blockAmt, chance, veinCount, minH, maxH, block to replace, world, rand, chunkX, chunkZ
			runGenerator(TUOMBlocks.GARNET_ORE.getDefaultState(), 12, 60, 70, 1, 100, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
			runGenerator(TUOMBlocks.TOPAZ_ORE.getDefaultState(), 8, 15, 5, 1, 26, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
			runGenerator(TUOMBlocks.FOPAL_ORE.getDefaultState(), 6, 12, 4, 1, 26, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
			runGenerator(TUOMBlocks.DOPAL_ORE.getDefaultState(), 6, 10, 3, 1, 26, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);
			runGenerator(TUOMBlocks.LOPAL_ORE.getDefaultState(), 6, 8, 2, 1, 26, BlockMatcher.forBlock(Blocks.STONE), world, random, chunkX, chunkZ);

		}
		else if(id == 1)
		{
		}
		else if(id == TUOMWorldGen.OPAL_DIM_ID)
		{
			generateDark(world, new Random(), blockX + 8, blockZ + 8);
			OpalOreGen oreGen = new OpalOreGen();
			oreGen.generate(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
			//          block to gen, blockAmt, chance, veinCount, minH, maxH, block to replace, world, rand, chunkX, chunkZ
/*			runGenerator(TUOMBlocks.garnet_ore.getDefaultState(), 12, 60, 70, 1, 100, BlockMatcher.forBlock(TUOMBlocks.dark_stone), world, random, chunkX, chunkZ);
			runGenerator(TUOMBlocks.topaz_ore.getDefaultState(), 8, 15, 5, 35, 200, BlockMatcher.forBlock(TUOMBlocks.dark_stone), world, random, chunkX, chunkZ);
			runGenerator(TUOMBlocks.fopal_ore.getDefaultState(), 6, 12, 4, 35, 200, BlockMatcher.forBlock(TUOMBlocks.dark_stone), world, random, chunkX, chunkZ);
			runGenerator(TUOMBlocks.dopal_ore.getDefaultState(), 6, 10, 3, 35, 200, BlockMatcher.forBlock(TUOMBlocks.dark_stone), world, random, chunkX, chunkZ);
			runGenerator(TUOMBlocks.lopal_ore.getDefaultState(), 6, 8, 2, 35, 200, BlockMatcher.forBlock(TUOMBlocks.dark_stone), world, random, chunkX, chunkZ);*/
		}
	}
	
	/**
	 * @param blockToGen The block you are spawning
	 * @param blockAmount How many blocks should spawn
	 * @param chance The chance your block has of spawning
	 * @param veinCount The max amount of veins to spawn in a chunk
	 * @param minHeight The minimum height the vein can spawn in the world. Must be greater than 0
	 * @param maxHeight The maximum height the vein can spawn at in the world. Must be less than 257
	 * @param blockToReplace The block in the world your block will be spawned in 
	 * */
	private void runGenerator(IBlockState blockToGen, int blockAmount, int chance, int veinCount, int minHeight, int maxHeight, Predicate<IBlockState> blockToReplace, World world, Random rand, int chunkX, int chunkZ){
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

/*		WorldGenMinable generator = new WorldGenMinable(blockToGen, blockAmount, blockToReplace);
		
		for (int i = 0; i < veinCount; i++) {
            if (rand.nextInt(100) < chance) {
                BlockPos pos = new BlockPos(chunkX * 16 + rand.nextInt(16), minHeight + rand.nextInt(maxHeight - minHeight), chunkZ * 16 + rand.nextInt(16));
                generator.generate(world, rand, pos);
            }
        }*/
		 BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

	        int globalX = chunkX << 4;
	        int globalZ = chunkZ << 4;

/*	        for (int i = 0; i < COAL_PER_CHUNK; i++) {
	            int offsetX = random.nextInt(16);
	            int offsetY = random.nextInt(64);
	            int offsetZ = random.nextInt(16);

	            pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
	            COAL_GENERATOR.generate(world, random, pos);
	        }*/
	}
	
	private void generateOverworld(World world, Random rand, int blockX, int blockZ)
	{
	}
	 	
	/**
	 * Structure
	 * */
	private void generateDark(World world, Random rand, int blockX, int blockZ)
	{
		if ((int) (Math.random() * 1000) == 0)
		{
			int y = getGroundFromAbove(world, blockX, blockZ);
			BlockPos pos = new BlockPos(blockX, y, blockZ);
			WorldGenerator structure = new TUOMStructureGen();
			structure.generate(world, rand, pos);
		}
	}
		
	public static int getGroundFromAbove(World world, int x, int z)
	{
		int y = 255;
		boolean foundGround = false;
		
		while(!foundGround && y-- >= 64)
		{
			Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			foundGround = blockAt == Blocks.GRASS || blockAt == Blocks.SAND || 
					blockAt == Blocks.SNOW || blockAt == Blocks.SNOW_LAYER || blockAt == Blocks.GLASS || blockAt == Blocks.MYCELIUM;
		}
		return y;
	}

	private void addVine(World world, BlockPos pos, PropertyBool property)
	{
		IBlockState iBlockState = Blocks.VINE.getDefaultState().withProperty(property, Boolean.valueOf(true));
		this.setBlockAndNotifyAdequately(world, pos, iBlockState);
		int i = 15 + new Random().nextInt(15);
		
		for(BlockPos blockPos = pos.down(); world.isAirBlock(blockPos) && i > 0; i--)
		{
			setBlockAndNotifyAdequately(world, blockPos, iBlockState);
			blockPos = blockPos.down();
		}
	}
	
	public static boolean canSpawnHere(Template template, World world, BlockPos posAboveGround)
	{
		int zWidth = template.getSize().getZ();
		int xWidth = template.getSize().getX();
		
		//Check all corners to see which ones are replaceable
		boolean corner1 = isCornerValid(world, posAboveGround);
		boolean corner2 = isCornerValid(world, posAboveGround.add(xWidth, 0, zWidth));
		
		//If y > 64 and all corners are valid, spawn
		return posAboveGround.getY() > 60 && corner1 && corner2;
	}
	
	public static boolean isCornerValid(World world, BlockPos pos)
	{
		int variation = 3;
		int highestBlock = getGroundFromAbove(world, pos.getX(), pos.getZ());
		
		if(highestBlock > pos.getY() - variation && highestBlock < pos.getY() + variation)
			return true;
		
		return false;
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		// TODO Auto-generated method stub
		return false;
	}
}
