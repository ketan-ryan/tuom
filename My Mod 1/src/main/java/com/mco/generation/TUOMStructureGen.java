package com.mco.generation;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.mco.TUOM;
import com.mco.biomes.DarkOpalPlains;
import com.mco.dimensions.TUOMWorldGen;
import com.mco.entities.mobs.dark.demon.corrupted.EntityDarkVex;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;

public class TUOMStructureGen extends WorldGenerator implements IWorldGenerator
{
	
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		int blockX = chunkX * 16;
		int blockZ = chunkZ * 16;
		int id = world.provider.getDimension();
		
		if(id == -1) {}
		else if(id == 0) {}
		else if(id == 1) {}
		else if(id == TUOMWorldGen.OPAL_DIM_ID)
		{
			if ((int) (Math.random() * 1000) == 0)
			{
				int y = getGroundFromAbove(world, blockX, blockZ);
				BlockPos pos = new BlockPos(blockX, y, blockZ);
				generateDarkTower(world, rand, pos);
			}
		}
	}
	
	private boolean generateDarkTower(World world, Random rand, BlockPos pos) 
	{
		WorldServer worldServer = (WorldServer)world;
		MinecraftServer minecraftServer = world.getMinecraftServer();
		TemplateManager templateManager = worldServer.getStructureTemplateManager();
		Template templateDarkBottom = templateManager.getTemplate(minecraftServer, new ResourceLocation(TUOM.MODID + ":dark_tower"));
		Template templateDarkTop = templateManager.getTemplate(minecraftServer, new ResourceLocation(TUOM.MODID + ":dark_tower_top"));
		
		if(templateDarkBottom == null)
		{
			return false;
		}
		
		if(canSpawnHere(templateDarkBottom, worldServer, pos) && world.getBiome(pos) instanceof DarkOpalPlains)
		{
			IBlockState iblockstate = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
			
			Rotation rotation = Rotation.NONE;
			switch(rand.nextInt(3))
			{
				case 0:
					rotation = Rotation.NONE;
					break;
				case 1:
					rotation = Rotation.CLOCKWISE_180;
					break;
				case 2:
					rotation = Rotation.CLOCKWISE_90;
					break;
				case 3:
					rotation = Rotation.COUNTERCLOCKWISE_90;
					break;
				default:
					break;
			}
			
			PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(rotation)
					.setIgnoreEntities(false).setChunk((ChunkPos) null).setReplacedBlock((Block)null).setIgnoreStructureBlock(false);
			
			templateDarkBottom.getDataBlocks(pos, placementsettings);
			templateDarkBottom.addBlocksToWorld(world,  pos.add(0, 1, 0), placementsettings);
			
			Map<BlockPos, String> map = templateDarkBottom.getDataBlocks(pos, placementsettings);
			
			for(Entry<BlockPos, String> entry : map.entrySet())
			{
				BlockPos dataPos = entry.getKey();
				
				if("dark demon chest".equals(entry.getValue()))
				{
					TileEntity tileentity = world.getTileEntity(dataPos);
					
					if(tileentity instanceof TileEntityChest)
					{
						((TileEntityChest)tileentity).setLootTable(TUOM.DARK_CHEST_DEMON, rand.nextLong());
					}
					world.setBlockState(dataPos.up(), Blocks.AIR.getDefaultState());
				}
				if("chest lava".equals(entry.getValue()))
				{
					TileEntity tileentity = world.getTileEntity(dataPos);
					
					if(tileentity instanceof TileEntityChest)
					{
						((TileEntityChest)tileentity).setLootTable(TUOM.DARK_CHEST_LAVA, rand.nextLong());
					}
					world.setBlockState(dataPos.up(), Blocks.AIR.getDefaultState());
				}
				if("chest top".equals(entry.getValue()))
				{
					TileEntity tileentity = world.getTileEntity(dataPos);
					
					if(tileentity instanceof TileEntityChest)
					{
						((TileEntityChest)tileentity).setLootTable(TUOM.DARK_CHEST_TOP, rand.nextLong());
					}
					world.setBlockState(dataPos.up(), Blocks.AIR.getDefaultState());
				}
				if("spawner".equals(entry.getValue()))
				{
					world.setBlockState(dataPos.up(), Blocks.AIR.getDefaultState(), 3);
					TileEntity tilespawner = world.getTileEntity(dataPos);	
					if(tilespawner != null)
						((TileEntityMobSpawner)tilespawner).getSpawnerBaseLogic().setEntityId(EntityList.getKey(EntityDarkVex.class));
				}
//				if("dark_demon".equals(entry.getValue()))
//				{
//					demon = new EntityDarkOpalDemon(world);
//					demon.setPosition(dataPos.getX(), dataPos.getY(), dataPos.getZ());
//					world.spawnEntity(demon);
//					world.setBlockState(dataPos.up(), Blocks.AIR.getDefaultState());
//				}
				if("top".equals(entry.getValue()))
				{
					world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
					templateDarkTop.getDataBlocks(dataPos, placementsettings);
					templateDarkTop.addBlocksToWorld(world, dataPos, placementsettings);
				}
			}
			return true;
		}
		return false;
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
		return false;
	}
	
}
