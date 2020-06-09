package com.mco.items.tools.light;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemAxe;
import net.minecraft.init.Items;

public class LopalAxe extends LibItemAxe {

	public LopalAxe(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(registryName, attackDamage + 2, attackSpeed, toolMaterial);
		this.setCreativeTab(TUOM.tuom_tab);
		this.setScale(1.25F);
	}
	
	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"ll",
				"ls",
				" s",
				
				'l', TUOMItems.light_opal,
				's', Items.STICK			
				
		);
	}

}
