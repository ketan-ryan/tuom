package com.mco.dimensions.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;

public class NoiseChunkPrimer 
{
	private final int horizontalGranularity;
    private final int verticalGranularity;

    private final int noiseWidth;
    private final int noiseHeight;

    public NoiseChunkPrimer(int horizontalGranularity, int verticalGranularity, int noiseWidth, int noiseHeight) {
        this.horizontalGranularity = horizontalGranularity;
        this.verticalGranularity = verticalGranularity;
        this.noiseWidth = noiseWidth;
        this.noiseHeight = noiseHeight;
    }

    public void primeChunk(ChunkPrimer primer, double[] sampledNoise, Handler handler) {
        int sampleWidth = this.noiseWidth + 1;
        int sampleHeight = this.noiseHeight + 1;

        double noiseScaleXZ = 1.0 / this.horizontalGranularity;
        double noiseScaleY = 1.0 / this.verticalGranularity;

        for (int noiseZ = 0; noiseZ < this.noiseWidth; noiseZ++) {
            int indexZ = noiseZ * sampleWidth;
            int indexZDown = (noiseZ + 1) * sampleWidth;

            for (int noiseX = 0; noiseX < this.noiseWidth; noiseX++) {
                int indexX = (indexZ + noiseX) * sampleHeight;
                int indexXRight = (indexZ + noiseX + 1) * sampleHeight;
                int indexXDown = (indexZDown + noiseX) * sampleHeight;
                int indexXDownRight = (indexZDown + noiseX + 1) * sampleHeight;

                for (int noiseY = 0; noiseY < this.noiseHeight; noiseY++) {
                    double valueOrigin = sampledNoise[indexX + noiseY];
                    double valueDown = sampledNoise[indexXRight + noiseY];
                    double valueRight = sampledNoise[indexXDown + noiseY];
                    double valueDownRight = sampledNoise[indexXDownRight + noiseY];

                    double stepOrigin = (sampledNoise[indexX + noiseY + 1] - valueOrigin) * noiseScaleY;
                    double stepDown = (sampledNoise[indexXRight + noiseY + 1] - valueDown) * noiseScaleY;
                    double stepRight = (sampledNoise[indexXDown + noiseY + 1] - valueRight) * noiseScaleY;
                    double stepDownRight = (sampledNoise[indexXDownRight + noiseY + 1] - valueDownRight) * noiseScaleY;

                    for (int localY = 0; localY < this.verticalGranularity; localY++) {
                        double originZ = valueOrigin;
                        double targetZ = valueDown;
                        double verticalStepZ1 = (valueRight - valueOrigin) * noiseScaleXZ;
                        double verticalStepZ2 = (valueDownRight - valueDown) * noiseScaleXZ;

                        for (int localZ = 0; localZ < this.horizontalGranularity; localZ++) {
                            double densityStep = (targetZ - originZ) * noiseScaleXZ;
                            double density = originZ;

                            for (int localX = 0; localX < this.horizontalGranularity; localX++) {
                                int blockX = noiseX * this.horizontalGranularity + localX;
                                int blockY = noiseY * this.verticalGranularity + localY;
                                int blockZ = noiseZ * this.horizontalGranularity + localZ;
                                IBlockState state = handler.getState(density, blockX, blockY, blockZ);
                                if (state != null) {
                                    primer.setBlockState(blockX, blockY, blockZ, state);
                                }
                                density += densityStep;
                            }

                            originZ += verticalStepZ1;
                            targetZ += verticalStepZ2;
                        }

                        valueOrigin += stepOrigin;
                        valueDown += stepDown;
                        valueRight += stepRight;
                        valueDownRight += stepDownRight;
                    }
                }
            }
        }
    }

    public interface Handler {
        IBlockState getState(double density, int x, int y, int z);
    }

}
