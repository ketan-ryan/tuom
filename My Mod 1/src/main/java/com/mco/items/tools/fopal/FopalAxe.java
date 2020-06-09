package com.mco.items.tools.fopal;

import com.mco.TUOM;
import library.LibRegistry;
import library.items.LibItemAxe;
import com.mco.main.TUOMItems;
import net.minecraft.init.Items;

public class FopalAxe extends LibItemAxe {

	public FopalAxe(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(registryName, attackDamage + 2, attackSpeed, toolMaterial);
		this.setCreativeTab(TUOM.tuom_tab);
		this.setScale(1.25F);
	}
	
	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"ff",
				"fs",
				" s",
				
				'f', TUOMItems.fire_opal,
				's', Items.STICK			
				
		);
	}

}
