package com.mco.dimensions.opal;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mco.dimensions.noise.OctaveNoiseSampler;
import com.mco.dimensions.opal.mapgen.OpalMapGenCaves;
import com.mco.dimensions.opal.mapgen.OpalMapGenRavine;
import com.mco.dimensions.util.BiomeWeightTable;
import com.mco.dimensions.util.NoiseChunkPrimer;
import com.mco.main.TUOMBiomes;
import com.mco.main.TUOMBlocks;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class OpalChunkGen implements IChunkGenerator
{
	private static final int HORIZONTAL_GRANULARITY = 4;
    private static final int VERTICAL_GRANULARITY = 2;

    private static final int NOISE_WIDTH = 16 / HORIZONTAL_GRANULARITY;
    private static final int NOISE_HEIGHT = 256 / VERTICAL_GRANULARITY;

    private static final int BUFFER_WIDTH = NOISE_WIDTH + 1;
    private static final int BUFFER_HEIGHT = NOISE_HEIGHT + 1;

    private static final int BIOME_WEIGHT_RADIUS = 2;

    private static final int BIOME_NOISE_OFFSET = BIOME_WEIGHT_RADIUS;
    private static final int BIOME_NOISE_SIZE = BUFFER_WIDTH + BIOME_WEIGHT_RADIUS * 2;

    private static final IBlockState STONE = TUOMBlocks.DARK_STONE.getDefaultState();
    
    private List<Biome.SpawnListEntry> mobs = Lists.newArrayList(new Biome.SpawnListEntry(EntityZombie.class, 100, 2, 4));
    
    private final World world;
    private final Random random;

    private Biome[] biomeBuffer;
    private Biome[] biomeNoiseBuffer;

    private final OctaveNoiseSampler worldNoise;
    private final OctaveNoiseSampler surfaceNoise;
    private final OctaveNoiseSampler ridgedSurfaceNoise;

    private final OctaveNoiseSampler depthNoise;

    private final double[] worldNoiseBuffer = new double[BUFFER_WIDTH * BUFFER_WIDTH * BUFFER_HEIGHT];
    private final double[] terrainBuffer = new double[BUFFER_WIDTH * BUFFER_WIDTH * BUFFER_HEIGHT];
    private final double[] surfaceBuffer = new double[BUFFER_WIDTH * BUFFER_WIDTH];
    private final double[] ridgedSurfaceBuffer = new double[BUFFER_WIDTH * BUFFER_WIDTH];

    private final double[] depthBuffer = new double[256];

    protected MapGenBase caveGenerator = new OpalMapGenCaves();
    protected MapGenBase ravineGenerator = new OpalMapGenRavine();
    
    private final NoiseChunkPrimer noisePrimer;
    private final BiomeWeightTable weightTable;
    
    protected WorldGenLakes lakeGenerator = new WorldGenLakes(Blocks.WATER);
    protected WorldGenLakes lavaLakeGenerator = new WorldGenLakes(Blocks.LAVA);
    protected ChunkPos chunkPos;
    
    public OpalChunkGen(World world) 
    {
        this.world = world;
        this.random = new Random(world.getSeed());

        this.worldNoise = OctaveNoiseSampler.perlin(this.random, 3);
        this.worldNoise.setAmplitude(5.0);
        this.worldNoise.setFrequency(0.1);

        this.surfaceNoise = OctaveNoiseSampler.perlin(this.random, 8);
        this.surfaceNoise.setAmplitude(3.0);
        this.surfaceNoise.setFrequency(0.04);

        this.ridgedSurfaceNoise = OctaveNoiseSampler.ridged(this.random, 3, 4.0);
        this.ridgedSurfaceNoise.setAmplitude(4.0);
        this.ridgedSurfaceNoise.setFrequency(0.08);

        this.depthNoise = OctaveNoiseSampler.perlin(this.random, 4);
        this.depthNoise.setFrequency(0.0625);

        this.noisePrimer = new NoiseChunkPrimer(HORIZONTAL_GRANULARITY, VERTICAL_GRANULARITY, NOISE_WIDTH, NOISE_HEIGHT);
        this.weightTable = new BiomeWeightTable(BIOME_WEIGHT_RADIUS);
        
        caveGenerator = TerrainGen.getModdedMapGen(new OpalMapGenCaves(), net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);        
        ravineGenerator = TerrainGen.getModdedMapGen(ravineGenerator, InitMapGenEvent.EventType.RAVINE);
        
        postTerrainGenEvents();
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkZ) 
    {
        int globalX = chunkX << 4;
        int globalZ = chunkZ << 4;
 
        this.random.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);

        BiomeProvider biomeProvider = this.world.getBiomeProvider();
        this.biomeBuffer = biomeProvider.getBiomes(this.biomeBuffer, globalX, globalZ, 16, 16);

        ChunkPrimer primer = new ChunkPrimer();
        this.primeChunk(primer, chunkX, chunkZ);

        Chunk chunk = new Chunk(this.world, primer, chunkX, chunkZ);

        byte[] chunkBiomes = chunk.getBiomeArray();
        for (int i = 0; i < chunkBiomes.length; i++) {
            chunkBiomes[i] = (byte) Biome.getIdForBiome(this.biomeBuffer[i]);
        }

        chunk.generateSkylightMap();
        
        for(int x = 0; x < 10; x++) {
        	for(int y = 0; y < 10; y++) {
        		for(int z = 0; z < 10; z++) {
        		//	world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState());
        		}
        	}
        }
        
        return chunk;
    }

    protected void postTerrainGenEvents() {
    }
    
    public void primeChunk(ChunkPrimer primer, int chunkX, int chunkZ) 
    {
        int originX = chunkX * HORIZONTAL_GRANULARITY - BIOME_NOISE_OFFSET;
        int originZ = chunkZ * HORIZONTAL_GRANULARITY - BIOME_NOISE_OFFSET;
        this.biomeNoiseBuffer = this.world.getBiomeProvider().getBiomesForGeneration(this.biomeNoiseBuffer, originX, originZ, BIOME_NOISE_SIZE, BIOME_NOISE_SIZE);

        this.populateNoise(chunkX, chunkZ);
        this.noisePrimer.primeChunk(primer, this.terrainBuffer, (density, x, y, z) -> {
            if (density > 0.0F) {
                return STONE;
            } else {
                return null;
            }
        });

        this.coverSurface(primer, chunkX, chunkZ);        
        
        caveGenerator.generate(world, chunkX, chunkZ, primer);
        ravineGenerator.generate(world, chunkX, chunkZ, primer);
    }

    protected void populateNoise(int chunkX, int chunkZ) 
    {
        this.worldNoise.sample3D(this.worldNoiseBuffer, chunkX * NOISE_WIDTH, 0, chunkZ * NOISE_WIDTH, BUFFER_WIDTH, BUFFER_HEIGHT, BUFFER_WIDTH);
        this.surfaceNoise.sample2D(this.surfaceBuffer, chunkX * NOISE_WIDTH, chunkZ * NOISE_WIDTH, BUFFER_WIDTH, BUFFER_WIDTH);
        this.ridgedSurfaceNoise.sample2D(this.ridgedSurfaceBuffer, chunkX * NOISE_WIDTH, chunkZ * NOISE_WIDTH, BUFFER_WIDTH, BUFFER_WIDTH);

        float heightOrigin = 62.0F / VERTICAL_GRANULARITY;

        int index = 0;
        int surfaceIndex = 0;

        for (int localZ = 0; localZ < NOISE_WIDTH + 1; localZ++) 
        {
            for (int localX = 0; localX < NOISE_WIDTH + 1; localX++) 
            {
                float heightVariation = 0.0F;
                float baseHeight = 0.0F;
                float ridgeWeight = 0.0F;
                float densityScale = 0.0F;
                float totalWeight = 0.0F;

                Biome originBiome = this.sampleNoiseBiome(localX, localZ);
                for (int neighborZ = -BIOME_WEIGHT_RADIUS; neighborZ <= BIOME_WEIGHT_RADIUS; neighborZ++) 
                {
                    for (int neighborX = -BIOME_WEIGHT_RADIUS; neighborX <= BIOME_WEIGHT_RADIUS; neighborX++) 
                    {
                        Biome neighborBiome = this.sampleNoiseBiome(localX + neighborX, localZ + neighborZ);
                        float neighborBaseHeight = neighborBiome.getBaseHeight();
                        float neighborHeightVariation = neighborBiome.getHeightVariation();
                        float neighborRidgeWeight = 1;
                        float neighborDensityScale = 1;
                        //float neighborRidgeWeight = IMidnightBiome.getRidgeWeight(neighborBiome);
                        //float neighborDensityScale = IMidnightBiome.getDensityScale(neighborBiome);

                        float biomeWeight = this.weightTable.get(neighborX, neighborZ) / (neighborBaseHeight + 2.0F);
                        if (neighborBiome.getBaseHeight() > originBiome.getBaseHeight()) 
                        {
                            biomeWeight *= 2.0F;
                        }

                        heightVariation += neighborHeightVariation * biomeWeight;
                        baseHeight += neighborBaseHeight * biomeWeight;
                        ridgeWeight += neighborRidgeWeight * biomeWeight;
                        densityScale += neighborDensityScale * biomeWeight;

                        totalWeight += biomeWeight;
                    }
                }

                heightVariation /= totalWeight;
                baseHeight /= totalWeight;
                ridgeWeight /= totalWeight;
                densityScale /= totalWeight;
                baseHeight += heightOrigin;

                heightVariation = heightVariation * 0.9F + 0.1F;

                double perlinSurfaceNoise = this.surfaceBuffer[surfaceIndex];
                double ridgedSurfaceNoise = this.ridgedSurfaceBuffer[surfaceIndex];
                surfaceIndex++;

                double surfaceNoise = perlinSurfaceNoise + (ridgedSurfaceNoise - perlinSurfaceNoise) * ridgeWeight;
                surfaceNoise = (surfaceNoise + 1.5) / 3.0;
                surfaceNoise = (surfaceNoise * heightVariation * 2.0) + baseHeight;

                for (int localY = 0; localY < NOISE_HEIGHT + 1; localY++) 
                {
                    double sampledNoise = this.worldNoiseBuffer[index];
                    double density = sampledNoise * Math.pow(heightVariation * 2.0, 3.0);

                    double densityBias = surfaceNoise - localY;
                    if (localY < baseHeight) 
                    {
                        densityBias *= 3.0;
                    }

                    this.terrainBuffer[index] = (density + densityBias) * densityScale;

                    index++;
                }
            }
        }
    }

    private Biome sampleNoiseBiome(int x, int z) 
    {
        return this.biomeNoiseBuffer[(x + BIOME_NOISE_OFFSET) + (z + BIOME_NOISE_OFFSET) * BIOME_NOISE_SIZE];
    }

    private void coverSurface(ChunkPrimer primer, int chunkX, int chunkZ)
    {
        if (!ForgeEventFactory.onReplaceBiomeBlocks(this, chunkX, chunkZ, primer, this.world)) 
        {
            return;
        }

        int globalX = chunkX << 4;
        int globalZ = chunkZ << 4;
        this.biomeBuffer = this.world.getBiomeProvider().getBiomes(this.biomeBuffer, globalX, globalZ, 16, 16);

        this.depthNoise.sample2D(this.depthBuffer, globalX, globalZ, 16, 16);

        for (int localZ = 0; localZ < 16; localZ++) 
        {
            for (int localX = 0; localX < 16; localX++) 
            {
                int index = localX + localZ * 16;
                Biome biome = this.biomeBuffer[index];
                double depth = this.depthBuffer[index];

                biome.genTerrainBlocks(this.world, this.random, primer, globalZ + localZ, globalX + localX, depth);
            }
        }
    }

    @Override
    public void populate(int parChunkX, int parChunkZ) 
    {
        BlockFalling.fallInstantly = true;
        
        int chunkStartXInWorld = parChunkX * 16;
        int chunkStartZInWorld = parChunkZ * 16;
        BlockPos blockpos = new BlockPos(chunkStartXInWorld, 0, chunkStartZInWorld);
        random.setSeed(world.getSeed());
        long k = random.nextLong() / 2L * 2L + 1L;
        long l = random.nextLong() / 2L * 2L + 1L;
        random.setSeed(parChunkX * k + parChunkZ * l ^ world.getSeed());
        chunkPos = new ChunkPos(parChunkX, parChunkZ);
        boolean villageHasGenerated = false;

        ForgeEventFactory.onChunkPopulate(true, this, world, random, parChunkX, parChunkZ, villageHasGenerated);

        if (TerrainGen.populate(this, world, random, parChunkX, parChunkZ, villageHasGenerated, PopulateChunkEvent.Populate.EventType.LAKE))
        {
            int lakeStartX = random.nextInt(16) + 8;
            int lakeStartY = random.nextInt(256);
            int lakeStartZ = random.nextInt(16) + 8;
            if(random.nextInt(175) == 0)
            lakeGenerator.generate(world, random, blockpos.add(lakeStartX, lakeStartY, lakeStartZ));
        }

        if (TerrainGen.populate(this, world, random, parChunkX, parChunkZ, villageHasGenerated, PopulateChunkEvent.Populate.EventType.LAVA))
        {
            int lavaStartX = random.nextInt(16) + 8;
            int lavaStartY = random.nextInt(random.nextInt(248) + 8);
            int lavaStartZ = random.nextInt(16) + 8;

            if (random.nextInt(200) == 0)
            {          
                lavaLakeGenerator.generate(world, random, blockpos.add(lavaStartX, lavaStartY, lavaStartZ));
            }
        }

        TUOMBiomes.dark_forest.decorate(world, random, new BlockPos(chunkStartXInWorld, 0, chunkStartZInWorld));
        TUOMBiomes.dark_mountains.decorate(world, random, new BlockPos(chunkStartXInWorld, 0, chunkStartZInWorld));
        TUOMBiomes.dark_plains.decorate(world, random, new BlockPos(chunkStartXInWorld, 0, chunkStartZInWorld));

        /*
         * Spawn creatures
         */
        if (TerrainGen.populate(this, world, random, parChunkX, parChunkZ, villageHasGenerated, PopulateChunkEvent.Populate.EventType.CUSTOM))
        {            
            WorldEntitySpawner.performWorldGenSpawning(world, TUOMBiomes.dark_forest, chunkStartXInWorld + 8, chunkStartZInWorld + 8, 16, 16, random);
            WorldEntitySpawner.performWorldGenSpawning(world, TUOMBiomes.dark_mountains, chunkStartXInWorld + 8, chunkStartZInWorld + 8, 16, 16, random);
            WorldEntitySpawner.performWorldGenSpawning(world, TUOMBiomes.dark_plains, chunkStartXInWorld + 8, chunkStartZInWorld + 8, 16, 16, random);

        }
        
        ForgeEventFactory.onChunkPopulate(false, this, world, random, parChunkX, parChunkZ, villageHasGenerated);

        BlockFalling.fallInstantly = false;
	}

    @Override
    public boolean generateStructures(Chunk chunk, int x, int z) 
    {
        return false;
    } 

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) 
    {
        return this.world.getBiome(pos).getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World world, String structureName, BlockPos position, boolean findUnexplored) 
    {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunk, int x, int z)
    {
    }

    @Override
    public boolean isInsideStructure(World world, String structureName, BlockPos pos) 
    {
        return false;
    }
}
