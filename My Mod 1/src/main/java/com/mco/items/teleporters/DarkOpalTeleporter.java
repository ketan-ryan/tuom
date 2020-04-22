package com.mco.items.teleporters;

import java.util.Random;

import javax.annotation.Nullable;

import com.mco.generation.TUOMWorldGenerator;
import com.mco.main.TUOMBiomes;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import com.mco.generation.TUOMWorldGenerator;
import com.mco.main.TUOMBiomes;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.biome.BiomeRiver;
import net.minecraft.world.biome.BiomeSavanna;

public class DarkOpalTeleporter extends Teleporter
{
	private final WorldServer worldServerInstance;
	private final Random random;
	private final Long2ObjectMap<Teleporter.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap(
			4096);

	public DarkOpalTeleporter(WorldServer worldIn) 
	{
		super(worldIn);
		this.worldServerInstance = worldIn;
		this.random = new Random(worldIn.getSeed());
	}

	private boolean isStartingBiomeAt(BlockPos pos) 
	{
		Biome biomeAt = world.getBiome(pos);
		if (!(biomeAt instanceof BiomeOcean) || !(biomeAt instanceof BiomeRiver)) 
		{
			return true;
		} else 
		{
			return false;
		}
	}
	
	@Nullable
	private BlockPos findSafeCoords(int range, BlockPos pos, Entity entity) 
	{
		if(entity.dimension == 0)
		{
			return entity.world.getSpawnPoint();
		}
		else
			for (int i = 0; i < 128; i++) 
			{
				BlockPos dPos = new BlockPos(pos.getX() + random.nextInt(range) - random.nextInt(range), 
						TUOMWorldGenerator.getGroundFromAbove(entity.world, pos.getX() + random.nextInt(range) - random.nextInt(range), pos.getZ() +
						random.nextInt(range) - random.nextInt(range)) + 4,	pos.getZ() + random.nextInt(range) - random.nextInt(range));
				return dPos;
			}
		return null;
	}
	
	@Override
	public void placeEntity(World world, Entity entity, float yaw) 
	{
		BlockPos pos = new BlockPos(entity);
		BlockPos safeCoords = findSafeCoords(200, pos, entity);

		if (safeCoords != null && world.getBiome(pos) != TUOMBiomes.dark_mountains)
		{
			entity.setLocationAndAngles(safeCoords.getX(), safeCoords.getY(), safeCoords.getZ(), 90.0F, 0.0F);
		} 
		else 
		{
			safeCoords = findSafeCoords(400, pos, entity);
			if (safeCoords != null && world.getBiome(pos) != TUOMBiomes.dark_mountains) 
			{
				entity.setLocationAndAngles(safeCoords.getX(), safeCoords.getY(), safeCoords.getZ(), 90.0F,
						0.0F);
			} 
		}
		
	}

}
