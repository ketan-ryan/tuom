package com.mco.items.tools.garnet;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemAxe;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.init.Items;

public class GarnetAxe extends LibItemAxe 
{
	public GarnetAxe(float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(6F, attackSpeed, toolMaterial);
		this.setScale(1.25F);
	}
}
