package com.mco.biomes;

import java.util.Random;

import library.blocks.LibBlockCrops;
import com.mco.dimensions.opal.WorldGenCrops;
import com.mco.main.TUOMBiomes;
import com.mco.main.TUOMBlocks;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;

public class DarkOpalBiomeDecorator extends BiomeDecorator
{
    public WorldGenerator dopalGen;

    // If you want to make these configurable, you'll need a ChunkGeneratorSettings
    // instance and use the fields from there instead.
    private int dirtSize = 33;
    private int gravelSize = 33;
    private int graniteSize = 33;
    private int dioriteSize = 33;
    private int andesiteSize = 33;
    private int coalSize = 17;
    private int ironSize = 9;
    private int goldSize = 9;
    private int redstoneSize = 9;
    private int diamondSize = 8;
    private int lapisSize = 8;
    private int dopalSize = 8;

    private int dirtCount = 10;
    private int gravelCount = 8;
    private int dioriteCount = 10;
    private int graniteCount = 10;
    private int andesiteCount = 10;
    private int coalCount = 20;
    private int ironCount = 20;
    private int goldCount = 2;
    private int redstoneCount = 8;
    private int diamondCount = 1;
    private int lapisCount = 1;
    private int dopalCount = 4;
    
    private int lapisCenterHeight =6;
    private int lapisSpread = 16;

    private int oreGenMinHeight = 0;

    private int dirtMaxHeight = 255;
    private int gravelMaxHeight = 255;
    private int dioriteMaxHeight = 80;
    private int graniteMaxHeight = 80;
    private int andesiteMaxHeight = 80;
    private int coalMaxHeight = 126;
    private int ironMaxHeight = 64;
    private int goldMaxHeight = 32;
    private int redstoneMaxHeight = 16;
    private int diamondMaxHeight = 16;
    private int dopalMaxHeight = 24;
    
    protected WorldGenCrops cropGen;
    protected WorldGenFlowers flowerGen;
    protected WorldGenLakes lakeGen;

    /**
     * Instantiates a new biome decorator DarkOpal.
     */
    public DarkOpalBiomeDecorator()
    {
        super();
    }

    /**
     *  
     * This is the function where ore generation and things like flowers are generated.
     *
     * @param worldIn the world in
     * @param random the random
     * @param biome the biome
     * @param pos the pos
     */
    @Override
    public void decorate(World worldIn, Random random, Biome biome, BlockPos pos)
    {
        if (decorating)
        {
            throw new RuntimeException("Already decorating");
        }
        else
        {
            chunkPos = pos;
            genDecorations(biome, worldIn, random);
            decorating = false;
        }
    }
    
    /**
     * This is where things like trees are generated.
     *
     * @param biomeIn the biome in
     * @param worldIn the world in
     * @param random the random
     */
    @Override
    protected void genDecorations(Biome biomeIn, World worldIn, Random random)
    {
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(worldIn, random, chunkPos));

        generate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.SAND, sandGen, sandPatchesPerChunk);
        generate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.CLAY, clayGen, clayPerChunk);
        generate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.SAND_PASS2, gravelGen, gravelPatchesPerChunk);
        generate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.SAND_PASS2, gravelGen, gravelPatchesPerChunk);
       
        if(biomeIn != TUOMBiomes.dark_plains) {
        	generateTrees(worldIn, biomeIn, random, chunkPos);
        }
        generateCrops(worldIn, biomeIn, random, chunkPos);
        generateGrass(worldIn, biomeIn, random, chunkPos);
        generateFalls(worldIn, random, chunkPos);       
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldIn, random, chunkPos));

    }
    
    private void generateTrees(World worldIn, Biome biomeIn, Random random, BlockPos chunkPos)
    {
    	int treesToGen;
    	if(biomeIn == TUOMBiomes.dark_mountains)
    		treesToGen = 10;
    	else if(biomeIn == TUOMBiomes.dark_forest)
    		treesToGen = 1;
    	else {
    	System.out.println("false");
    		treesToGen = 0;
    	}
        if (random.nextFloat() < extraTreeChance)
        {
            ++treesToGen;
        }

        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.TREE))
        for (int numTreesGenerated = 0; numTreesGenerated < treesToGen; ++numTreesGenerated)
        {
            int treeX = random.nextInt(16) + 8;
            int treeZ = random.nextInt(16) + 8;
            WorldGenAbstractTree treeGen = biomeIn.getRandomTreeFeature(random);
            treeGen.setDecorationDefaults();
            BlockPos blockpos = worldIn.getHeight(chunkPos.add(treeX, 0, treeZ));

            if (treeGen.generate(worldIn, random, blockpos) && biomeIn != TUOMBiomes.dark_plains)
            {
                treeGen.generateSaplings(worldIn, random, blockpos);
            }
        }
    }
    
    private void generateCrops(World worldIn, Biome biomeIn, Random random, BlockPos chunkPos)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.FLOWERS))
        for (int numFlowersGenerated = 0; numFlowersGenerated < flowersPerChunk; ++numFlowersGenerated)
        {
            int flowerX = random.nextInt(16) + 8;
            int flowerZ = random.nextInt(16) + 8;
            int yRange = worldIn.getHeight(chunkPos.add(flowerX, 0, flowerZ)).getY() + 32;
            
            cropGen = new WorldGenCrops((LibBlockCrops) TUOMBlocks.dopal_crop);
            
            if (yRange > 0)
            {
                int flowerY = random.nextInt(yRange);
                BlockPos flowerBlockPos = chunkPos.add(flowerX, flowerY, flowerZ);
                cropGen.generate(worldIn, random, flowerBlockPos);
            }
        }
    }
    
    private void generateFlowers(World worldIn, Biome biomeIn, Random random, BlockPos chunkPos)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.FLOWERS))
        for (int numFlowersGenerated = 0; numFlowersGenerated < flowersPerChunk; ++numFlowersGenerated)
        {
            int flowerX = random.nextInt(16) + 8;
            int flowerZ = random.nextInt(16) + 8;
            int yRange = worldIn.getHeight(chunkPos.add(flowerX, 0, flowerZ)).getY() + 32;
            
            flowerGen = new WorldGenFlowers(Blocks.RED_FLOWER, EnumFlowerType.ALLIUM);
            
            if (yRange > 0)
            {
                int flowerY = random.nextInt(yRange);
                BlockPos flowerBlockPos = chunkPos.add(flowerX, flowerY, flowerZ);
                flowerGen.generate(worldIn, random, flowerBlockPos);
            }
        }
    }
    
    private void generateGrass(World worldIn, Biome biomeIn, Random random, BlockPos chunkPos)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.GRASS))
        for (int numGrassPerChunk = 0; numGrassPerChunk < grassPerChunk; ++numGrassPerChunk)
        {
            int grassX = random.nextInt(16) + 8;
            int grassZ = random.nextInt(16) + 8;
            int yRange = worldIn.getHeight(chunkPos.add(grassX, 0, grassZ)).getY() * 2;

            if (yRange > 0)
            {
                int grassY = random.nextInt(yRange);
                biomeIn.getRandomWorldGenForGrass(random).generate(worldIn, random, chunkPos.add(grassX, grassY, grassZ));
            }
        }
    }
    
    private void generateFalls(World worldIn, Random random, BlockPos chunkPos)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.LAKE_WATER))
        for (int k5 = 0; k5 < 50; ++k5)
        {
            int i10 = random.nextInt(16) + 8;
            int l13 = random.nextInt(16) + 8;
            int i17 = random.nextInt(248) + 8;

            if (i17 > 0)
            {
                int k19 = random.nextInt(i17);
                BlockPos blockpos6 = chunkPos.add(i10, k19, l13);
                (new WorldGenLiquids(Blocks.FLOWING_WATER)).generate(worldIn, random, blockpos6);
            }
        }

        if(TerrainGen.decorate(worldIn, random, chunkPos, DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA))
        for (int l5 = 0; l5 < 20; ++l5)
        {
            int j10 = random.nextInt(16) + 8;
            int i14 = random.nextInt(16) + 8;
            int j17 = random.nextInt(random.nextInt(random.nextInt(240) + 8) + 8);
            BlockPos blockpos3 = chunkPos.add(j10, j17, i14);
            (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(worldIn, random, blockpos3);
        }
    }

    private void generate(World worldIn, Random random, BlockPos chunkPos, EventType eventType, WorldGenerator generator, int countPerChunk)
    {
        if(TerrainGen.decorate(worldIn, random, chunkPos, eventType))
        {
            for (int count = 0; count < countPerChunk; ++count)
            {
                int randX = random.nextInt(16) + 8;
                int randZ = random.nextInt(16) + 8;
                generator.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(chunkPos.add(randX, 0, randZ)));
            }
        }
    }
    
}
