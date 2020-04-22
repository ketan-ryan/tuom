package com.mco.items.tools.topaz;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemPickaxe;
import net.minecraft.init.Items;

public class TopazPickaxe extends LibItemPickaxe 
{

	public TopazPickaxe(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(registryName, attackDamage, attackSpeed, toolMaterial);
		this.setCreativeTab(TUOM.tuom_tab);
		this.setScale(1.25F);
	}
	
	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"ttt",
				" s ",
				" s ",
				
				't', TUOMItems.item_topaz,
				's', Items.STICK			
				
		);
	}

}
