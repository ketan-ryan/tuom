package com.mco.items.basic;

import library.LibRegistry;
import library.items.LibItemSimple;
import com.mco.TUOM;
import com.mco.main.TUOMItems;

public class ItemTopaz extends LibItemSimple 
{

	public ItemTopaz(String registryName) 
	{
		super(registryName);
		setCreativeTab(TUOM.tuom_tab);
	}
	
	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"tt",
				"tt",
				
				't', TUOMItems.topaz_shard	
				
		);
	}
}
