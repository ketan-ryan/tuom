package com.mco.items.tools.light;

import library.LibRegistry;
import library.items.LibItemShovel;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.init.Items;

public class LopalShovel extends LibItemShovel {

	public LopalShovel(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial)
	{
		super(registryName, attackDamage, attackSpeed, toolMaterial);
		this.setCreativeTab(TUOM.tuom_tab);
		this.setScale(1.25F);
	}

	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"l",
				"s",
				"s",
				
				'l', TUOMItems.light_opal,
				's', Items.STICK			
				
		);
	}
	
}
