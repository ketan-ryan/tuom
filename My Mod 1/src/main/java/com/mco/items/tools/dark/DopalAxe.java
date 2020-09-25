package com.mco.items.tools.dark;

import com.mco.TUOM;
import library.LibRegistry;
import library.items.LibItemAxe;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.init.Items;

public class DopalAxe extends LibItemAxe 
{
	public DopalAxe(float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(attackDamage + 2, attackSpeed, toolMaterial);
		this.setScale(1.25F);
	}
}
