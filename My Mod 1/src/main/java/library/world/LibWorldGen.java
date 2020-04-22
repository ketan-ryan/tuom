package library.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class LibWorldGen implements IWorldGenerator {

    private int chance = 100;
    private int minHeight = 10;
    private int maxHeight = 100;
    private int veinCount = 1;
    private WorldGenMinable gen;

    public LibWorldGen(IBlockState state, int chance, int minHeight, int maxHeight, int veinSize, int veinCount) {
        this.chance = chance;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinCount = veinCount;
        gen = new WorldGenMinable(state, veinSize);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        for (int i = 0; i < veinCount; i++) {
            if (random.nextInt(100) < chance) {
                BlockPos pos = new BlockPos(chunkX * 16 + random.nextInt(16),
                        minHeight + random.nextInt(maxHeight - minHeight),
                        chunkZ * 16 + random.nextInt(16));


                gen.generate(world, random, pos);
            }
        }

    }
}
