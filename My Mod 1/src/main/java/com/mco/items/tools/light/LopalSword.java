package com.mco.items.tools.light;

import library.items.LibItemSword;
import library.util.Actions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class LopalSword extends LibItemSword
{

	public LopalSword(ToolMaterial material) 
	{
		super(material);
		this.setScale(1.5F);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(TextFormatting.YELLOW + "Right click to summon a lightning bolt wherever you're looking!");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		RayTraceResult lookVec = Actions.getPlayerLookVec(playerIn, 50);
	
		if(lookVec.getBlockPos() !=null && !worldIn.isRemote)
		{
			BlockPos blockHit = lookVec.getBlockPos();
			//Actions.summonLightningAtPosition(worldIn, lookVec.getBlockPos(), 0);
			EntityLightningBolt entityDarkLightning = new EntityLightningBolt(worldIn, blockHit.getX(), blockHit.getY(), blockHit.getZ(), false);
			worldIn.spawnEntity(entityDarkLightning);
			playerIn.getHeldItem(handIn).damageItem(10, playerIn);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
}
