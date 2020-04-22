package com.mco.items.archery;

import com.mco.TUOM;
import library.LibRegistry;
import library.items.LibItemBow;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class Topaz_Bow extends LibItemBow {

	public Topaz_Bow(String registryName) 
	{
		super(registryName);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				" ts",
				"t s",
				" ts",
				
				't', TUOMItems.item_topaz,
				's', Items.STRING			
				
		);
	}
	
	@Override
	protected Item getAmmo() 
	{
		return TUOMItems.topaz_arrow;
	}

}
