package com.mco.items.tools.light;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemAxe;
import net.minecraft.init.Items;

public class LopalAxe extends LibItemAxe 
{
	public LopalAxe(float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(attackDamage + 2, attackSpeed, toolMaterial);
		this.setScale(1.25F);
	}
}
