package com.mco.generation;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.mco.TUOM;
import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.corrupted.EntityDarkVex;
import com.mco.TUOM;
import com.mco.biomes.DarkOpalPlains;
import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.entities.mobs.dark.demon.corrupted.EntityDarkVex;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntityStructure;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class TUOMStructureGen extends WorldGenerator
{
	Random rand = new Random();
	
	int r;
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
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
		
		if(OpalWorldGenerator.canSpawnHere(templateDarkBottom, worldServer, pos) && world.getBiome(pos) instanceof DarkOpalPlains)
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
				EntityDarkOpalDemon demon;
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

}
