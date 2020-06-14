package com.mco.dimensions.opal;

import com.mco.dimensions.TUOMWorldGen;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OpaliteWorldProvider extends WorldProvider 
{
	@Override
	public DimensionType getDimensionType() {
		return TUOMWorldGen.OPAL_DIMENSION_TYPE;
	}

	/**
	 * Creates a new {@link BiomeProvider} for the WorldProvider, and also sets the
	 * values of {@link #hasSkylight} and {@link #hasNoSky} appropriately.
	 * 
	 * Note that subclasses generally override this method without calling the
	 * parent version.
	 * 
	 * 
	 * NEW BIOME PROVIDER LATER
	 * *****************************************************************************************************
	 * 
	 * 
	 */
	public void init() {
		this.setDimension(TUOMWorldGen.OPAL_DIM_ID);
		this.hasSkyLight = true;
		this.biomeProvider = new OpalBiomeProvider(world.getWorldInfo());
	}

	public IChunkGenerator createChunkGenerator() {
		return new OpalChunkGen(world);
	}

	@Override
	public boolean isDaytime() {
		return false;
	}
	
	/**
	 * Return Vec3D with biome specific fog color
	 */
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		return new Vec3d(0.20000000298023224D, 0.029999999329447746D, 0.29999999329447746D);
	}

	@Override
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
		return null;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) 
	{
		return 0.75F;
	}

	/**
	 * Returns 'true' if in the "main surface world", but 'false' if in the Nether
	 * or End dimensions.
	 *
	 * @return true, if is surface world
	 */
	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	/**
	 * the y level at which clouds are rendered.
	 */
	@SideOnly(Side.CLIENT)
	public float getCloudHeight() {
		return 100;
	}

	@SideOnly(Side.CLIENT)
	public boolean isSkyColored() {
		return true;
	}

	/**
	 * Returns a double value representing the Y value relative to the top of the
	 * map at which void fog is at its maximum. The default factor of 0.03125
	 * relative to 256, for example, means the void fog will be at its maximum at
	 * (256*0.03125), or 8.
	 */
	@SideOnly(Side.CLIENT)
	public double getVoidFogYFactor() {
		return .03215;
	}
	
	@Override
	public boolean canDoLightning(net.minecraft.world.chunk.Chunk chunk) {
		return true;
	}

	/**
	 * Returns true if the given X,Z coordinate should show environmental fog.
	 */
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int x, int z) {
		return world.getWorldTime() > 12000;
	}

	/**
	 * Creates the light to brightness table
	 */
	protected void generateLightBrightnessTable() {
		float baseLight = 0.6F;

		for (int i = 0; i <= 15; ++i) {
			float alpha = 1.0F - i / 15.0F;
			float brightness = (1.0F - alpha) / (alpha * 5.0F + 1.0F);
			this.lightBrightnessTable[i] = (float) (Math.pow(brightness, 2.5F) * 1.0F) + baseLight;
		}
	}
}
