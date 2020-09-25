package com.mco.items.tools.fopal;

import java.util.List;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemHoe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class FopalHoe extends LibItemHoe 
{
	public FopalHoe(float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(attackDamage, attackSpeed, toolMaterial);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(TextFormatting.RED + "Be careful when using this destructive Hoe -");
		tooltip.add(TextFormatting.RED + "Places fire in a cross pattern around the tilled block.");
	}
	
	/**
     * Called when a Block is right-clicked with this Item
     */
    @SuppressWarnings("incomplete-switch")
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(itemstack, player, worldIn, pos);
            if (hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();
            BlockPos posUp = pos.up();

            if (facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up()))
            {
                if (block == Blocks.GRASS || block == Blocks.GRASS_PATH)
                {
                    this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                    
                    if(facing != EnumFacing.DOWN)
                    {
                    	this.setBlock(itemstack, player, worldIn, posUp.south(), Blocks.FIRE.getDefaultState());
                    	this.setBlock(itemstack, player, worldIn, posUp.north(), Blocks.FIRE.getDefaultState());
                    	this.setBlock(itemstack, player, worldIn, posUp.east(), Blocks.FIRE.getDefaultState());
                    	this.setBlock(itemstack, player, worldIn, posUp.west(), Blocks.FIRE.getDefaultState());
                     	return EnumActionResult.SUCCESS;
                    }
                    
                    return EnumActionResult.SUCCESS;
                }

                if (block == Blocks.DIRT)
                {
                    switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT))
                    {
                        case DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());                            
                            if(facing != EnumFacing.DOWN)
                            {
                            	this.setBlock(itemstack, player, worldIn, posUp.south(), Blocks.FIRE.getDefaultState());
                            	this.setBlock(itemstack, player, worldIn, posUp.north(), Blocks.FIRE.getDefaultState());
                            	this.setBlock(itemstack, player, worldIn, posUp.east(), Blocks.FIRE.getDefaultState());
                            	this.setBlock(itemstack, player, worldIn, posUp.west(), Blocks.FIRE.getDefaultState());
                             	return EnumActionResult.SUCCESS;
                            }                            
                            return EnumActionResult.SUCCESS;
                            
                        case COARSE_DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));                            
                            if(facing != EnumFacing.DOWN)
                            {
                            	this.setBlock(itemstack, player, worldIn, posUp.south(), Blocks.FIRE.getDefaultState());
                            	this.setBlock(itemstack, player, worldIn, posUp.north(), Blocks.FIRE.getDefaultState());
                            	this.setBlock(itemstack, player, worldIn, posUp.east(), Blocks.FIRE.getDefaultState());
                            	this.setBlock(itemstack, player, worldIn, posUp.west(), Blocks.FIRE.getDefaultState());
                             	return EnumActionResult.SUCCESS;
                            }                            
                            return EnumActionResult.SUCCESS;
                    }
                }
            }
            
            return EnumActionResult.PASS;
        }
    }
}
	

