package com.mco.items.tools.fopal;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemShovel;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.init.Items;

public class FopalShovel extends LibItemShovel {

	public FopalShovel(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial)
	{
		super(registryName, attackDamage, attackSpeed, toolMaterial);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"f",
				"s",
				"s",
				
				'f', TUOMItems.fire_opal,
				's', Items.STICK			
				
		);
	}
	
}
