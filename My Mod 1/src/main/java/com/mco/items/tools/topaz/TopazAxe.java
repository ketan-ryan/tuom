package com.mco.items.tools.topaz;

import library.LibRegistry;
import library.items.LibItemAxe;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.init.Items;

public class TopazAxe extends LibItemAxe {

	public TopazAxe(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(registryName, attackDamage, attackSpeed, toolMaterial);
		this.setCreativeTab(TUOM.tuom_tab);
		this.setScale(1.25F);
	}
	
	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"tt",
				"ts",
				" s",
				
				't', TUOMItems.item_topaz,
				's', Items.STICK			
				
		);
	}

}
