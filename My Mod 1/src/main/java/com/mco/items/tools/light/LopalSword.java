package com.mco.items.tools.light;

import java.util.List;

import com.mco.TUOM;
import com.mco.entities.lightning.dark.EntityDarkLightning;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemSword;
import library.util.Actions;
import com.mco.TUOM;
import com.mco.entities.lightning.dark.EntityDarkLightning;
import com.mco.main.TUOMItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class LopalSword extends LibItemSword
{
	World worldObj;

	public LopalSword(String registryName, ToolMaterial material, World world) 
	{
		super(registryName, material);
		worldObj = world;
		this.setCreativeTab(TUOM.tuom_tab);
		this.setScale(1.5F);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(TextFormatting.YELLOW + "Right click to summon a lightning bolt wherever you're looking!");
	}
	
	@Override
	public void initRecipe() 
	{
		LibRegistry.addShapedRecipe(this, 1, 
				
				"l",
				"l",
				"s",
				
				'l', TUOMItems.light_opal,
				's', Items.STICK			
				
		);
	}
		
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		RayTraceResult lookVec = Actions.getPlayerLookVec(playerIn, 50);
	
		if(lookVec.getBlockPos() !=null && !worldIn.isRemote)
		{
			BlockPos blockHit = lookVec.getBlockPos();
			//Actions.summonLightningAtPosition(worldIn, lookVec.getBlockPos(), 0);
			EntityDarkLightning entityDarkLightning = new EntityDarkLightning(worldIn, blockHit.getX(), blockHit.getY(), blockHit.getZ(), false);
			worldIn.spawnEntity(entityDarkLightning);
			playerIn.getHeldItem(handIn).damageItem(10, playerIn);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
}
