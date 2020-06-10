package com.mco.items.tools.light;

import library.LibRegistry;
import library.items.LibItemShovel;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.init.Items;

public class LopalShovel extends LibItemShovel
{
	public LopalShovel(float attackDamage, float attackSpeed, ToolMaterial toolMaterial)
	{
		super(attackDamage, attackSpeed, toolMaterial);
		this.setScale(1.25F);
	}
}
