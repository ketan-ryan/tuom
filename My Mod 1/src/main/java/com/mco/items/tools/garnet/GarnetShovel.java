package com.mco.items.tools.garnet;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemShovel;
import net.minecraft.init.Items;

public class GarnetShovel extends LibItemShovel {

	public GarnetShovel(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial)
	{
		super(registryName, attackDamage, attackSpeed, toolMaterial);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"g",
				"s",
				"s",
				
				'g', TUOMItems.item_garnet,
				's', Items.STICK			
				
		);
	}
	
}
