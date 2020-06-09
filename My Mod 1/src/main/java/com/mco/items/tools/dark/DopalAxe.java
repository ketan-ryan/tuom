package com.mco.items.tools.dark;

import com.mco.TUOM;
import library.LibRegistry;
import library.items.LibItemAxe;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.init.Items;

public class DopalAxe extends LibItemAxe {

	public DopalAxe(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(registryName, attackDamage + 2, attackSpeed, toolMaterial);
		this.setCreativeTab(TUOM.tuom_tab);
		this.setScale(1.25F);
	}
	
	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"dd",
				"ds",
				" s",
				
				'd', TUOMItems.fire_opal,
				's', Items.STICK			
				
		);
	}

}
