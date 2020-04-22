package com.mco.items.tools.fopal;

import java.util.List;

import library.LibRegistry;
import library.items.LibItemPickaxe;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class FopalPickaxe extends LibItemPickaxe 
{

	public FopalPickaxe(String registryName, float attackDamage, float attackSpeed, ToolMaterial toolMaterial) 
	{
		super(registryName, attackDamage, attackSpeed, toolMaterial);
		this.setCreativeTab(TUOM.tuom_tab);
		this.setScale(1.25F);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(TextFormatting.RED + "Auto smelts ores!");
	}
	
	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 1, 
				
				"fff",
				" s ",
				" s ",
				
				'f', TUOMItems.fire_opal,
				's', Items.STICK			
				
		);
	}

}
