package com.mco.items.tools.dark;

import java.util.List;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemPickaxe;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class DopalPickaxe extends LibItemPickaxe 
{

	public DopalPickaxe(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(registryName, attackDamage, attackSpeed, toolMaterial);
		this.setCreativeTab(TUOM.tuom_tab);
		this.setScale(1.25F);
	}
	
	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"ddd",
				" s ",
				" s ",
				
				'd', TUOMItems.dark_opal,
				's', Items.STICK			
				
		);
	}

}
