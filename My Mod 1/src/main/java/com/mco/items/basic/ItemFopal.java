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

public class ItemFopal extends LibItemSimple 
{
	public ItemFopal(String registryName) 
	{
		super(registryName);
		setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"ff",
				"ff",
				
				'f', TUOMItems.fopal_shard
				
		);
	}
}
