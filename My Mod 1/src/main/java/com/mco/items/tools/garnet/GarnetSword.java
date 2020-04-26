package com.mco.items.tools.garnet;

import java.util.List;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemSword;
import library.particle.ParticleBehavior;
import library.particle.ParticleShape;
import library.util.Actions;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class GarnetSword extends LibItemSword
{
	World worldObj;

	public GarnetSword(String registryName, ToolMaterial material) 
	{
		super(registryName, material);
		this.setCreativeTab(TUOM.tuom_tab);
	}
	
	@Override
	public void initRecipe() 
	{
		LibRegistry.addShapedRecipe(this, 1, 
				
				"g",
				"g",
				"s",
				
				'g', TUOMItems.item_garnet,
				's', Items.STICK			
				
		);
	}
	
}
