package com.mco.items.tools.fopal;

import library.items.LibItemPickaxe;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class FopalPickaxe extends LibItemPickaxe 
{
	public FopalPickaxe(float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(attackDamage, attackSpeed, toolMaterial);
		this.setScale(1.25F);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(TextFormatting.RED + "Auto smelts ores! (Doesn't work with fortune)");
	}
}
