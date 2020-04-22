package com.mco.items.tools.topaz;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemShovel;
import net.minecraft.init.Items;

public class TopazShovel extends LibItemShovel {

	public TopazShovel(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial)
	{
		super(registryName, attackDamage, attackSpeed, toolMaterial);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"t",
				"s",
				"s",
				
				't', TUOMItems.item_topaz,
				's', Items.STICK			
				
		);
	}
	
}
