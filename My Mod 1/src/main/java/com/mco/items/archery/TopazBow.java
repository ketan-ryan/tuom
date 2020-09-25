package com.mco.items.archery;

import com.mco.TUOM;
import library.LibRegistry;
import library.items.LibItemBow;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class TopazBow extends LibItemBow {

	public TopazBow() 
	{
		super();
	}
	
	@Override
	protected Item getAmmo() 
	{
		return TUOMItems.TOPAZ_ARROW;
	}

}
