package com.mco.items.tools.dark;

import library.LibRegistry;
import library.items.LibItemShovel;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.init.Items;

public class DopalShovel extends LibItemShovel {

	public DopalShovel(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial)
	{
		super(registryName, attackDamage, attackSpeed, toolMaterial);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"d",
				"s",
				"s",
				
				'd', TUOMItems.dark_opal,
				's', Items.STICK			
				
		);
	}
	
}
