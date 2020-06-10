package com.mco.items.tools.topaz;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemPickaxe;
import net.minecraft.init.Items;

public class TopazPickaxe extends LibItemPickaxe 
{
	public TopazPickaxe(float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(attackDamage, attackSpeed, toolMaterial);
		this.setScale(1.25F);
	}
}
