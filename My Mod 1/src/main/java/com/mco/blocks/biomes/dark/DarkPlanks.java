package com.mco.blocks.biomes.dark;

import com.mco.blocks.IHasModel;
import com.mco.blocks.IMetaName;
import com.mco.blocks.IHasModel;
import com.mco.blocks.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class DarkPlanks extends Block implements IMetaName, IHasModel
{
	public static final PropertyEnum<DarkPlanks.EnumType> VARIANT = PropertyEnum.<DarkPlanks.EnumType>create("variant", DarkPlanks.EnumType.class);
	
	public DarkPlanks(String registryName) 
	{
		super(Material.WOOD);
		setUnlocalizedName(registryName);
		setRegistryName(registryName);
		setSoundType(SoundType.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, DarkPlanks.EnumType.DARK));
	}
	
	@Override
	public int damageDropped(IBlockState state) 
	{
		return ((DarkPlanks.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) 
	{
		for(DarkPlanks.EnumType darkplanks$enumtype : DarkPlanks.EnumType.values())
		{
			items.add(new ItemStack(this, 1, darkplanks$enumtype.getMeta()));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		return this.getDefaultState().withProperty(VARIANT, DarkPlanks.EnumType.byMetadata(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		return ((DarkPlanks.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) 
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, (int)(getMetaFromState(world.getBlockState(pos))));
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this,  new IProperty[] {VARIANT});
	}
	
	@Override
	public void registerModels() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSpecialName(ItemStack stack) 
	{
		return DarkPlanks.EnumType.values() [stack.getItemDamage()].getName();
	}
	
	public static enum EnumType implements IStringSerializable
	{
		DARK(0, "dark"),
		FIRE(1, "fire"),
		LIGHT(2, "light");
		
		private static final DarkPlanks.EnumType[] META_LOOKUP = new DarkPlanks.EnumType[values().length];
		private final int meta;
		private final String name, unlocalizedName;
			
		private EnumType(int meta, String name)
		{
			this(meta, name, name);
		}
		
		private EnumType(int meta, String name, String unlocalizedName)
		{
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}
		
		@Override
		public String getName() 
		{
			return this.name;
		}
		
		public int getMeta()
		{
			return this.meta;
		}
		
		public String getUnlocalizedName()
		{
			return this.unlocalizedName;
		}		
		
		@Override
		public String toString()
		{
			return this.name;
		}
		
		public static DarkPlanks.EnumType byMetadata(int meta)
		{
			return META_LOOKUP[meta];
		}
		
		static
		{
			for(DarkPlanks.EnumType darkplanks$enumtype : values())
			{
				META_LOOKUP[darkplanks$enumtype.getMeta()] = darkplanks$enumtype;
			}
		}
		
	}
	
	
}
