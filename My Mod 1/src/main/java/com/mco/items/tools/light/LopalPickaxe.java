package com.mco.items.tools.light;

import java.util.List;

import com.mco.TUOM;
import library.LibRegistry;
import library.items.LibItemPickaxe;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class LopalPickaxe extends LibItemPickaxe 
{
	public LopalPickaxe(float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(attackDamage, attackSpeed, toolMaterial);
		this.setScale(1.25F);
	}
}
