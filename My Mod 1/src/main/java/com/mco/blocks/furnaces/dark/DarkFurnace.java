package com.mco.blocks.furnaces.dark;

import java.util.List;

import library.blocks.LibBlockFurnace;
import com.mco.TUOM;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class DarkFurnace extends LibBlockFurnace 
{
	public DarkFurnace(String registryName, Class<? extends TileEntity> tileEntityClass) 
	{
		super(registryName, tileEntityClass);
		this.setCreativeTab(TUOM.tuom_tab);
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness((float) 3.5);
		this.setSoundType(SoundType.STONE);
	}
	
/*	@Override
	public void initRecipe() 
	{
		LibRegistry.addShapedRecipe(this, 1, 
				
				" d ",
				"dfd",
				" d ",
				
				'd', TUOMItems.dark_opal,
				'f', Blocks.FURNACE			
				
		);
	}
*/
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(TextFormatting.DARK_PURPLE + "A darkened forge that fuses ultimate ores");
	}
	
/*	*//**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     *//*
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof DarkFurnaceTileEntity)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (DarkFurnaceTileEntity)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }        

        super.breakBlock(worldIn, pos, state);
    }
	*/
}
