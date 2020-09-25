package com.mco.items.tools.fopal;

import com.mco.TUOM;
import library.LibRegistry;
import library.items.LibItemAxe;
import com.mco.main.TUOMItems;
import net.minecraft.init.Items;

public class FopalAxe extends LibItemAxe 
{
	public FopalAxe(float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(attackDamage + 2, attackSpeed, toolMaterial);
		this.setScale(1.25F);
	}
}
