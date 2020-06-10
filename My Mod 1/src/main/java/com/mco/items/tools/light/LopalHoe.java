package com.mco.items.tools.light;

import java.util.List;

import com.mco.TUOM;
import library.LibRegistry;
import library.items.LibItemHoe;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
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

public class LopalHoe extends LibItemHoe 
{
	public LopalHoe(float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(attackDamage, attackSpeed, toolMaterial);
		this.setScale(1.25F);
	}
}
	

