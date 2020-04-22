package com.mco.items.basic;

import java.util.List;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemSimple;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemLopal extends LibItemSimple 
{

	public ItemLopal(String registryName) 
	{
		super(registryName);
		setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"ll",
				"ll",
				
				'l', TUOMItems.lopal_shard
				
		);
	}
	
}
