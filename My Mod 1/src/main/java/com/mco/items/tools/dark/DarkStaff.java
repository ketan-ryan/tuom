package com.mco.items.tools.dark;

import java.util.List;

import com.mco.TUOM;
import com.mco.main.TUOMDamageSources;
import library.particle.ParticleBehavior;
import library.particle.ParticleShape;
import library.util.Actions;
import com.mco.TUOM;
import com.mco.main.TUOMDamageSources;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class DarkStaff extends Item
{
	
	public DarkStaff(String registryName, ToolMaterial material) 
	{
		super();
		this.setCreativeTab(TUOM.tuom_tab);
		this.setRegistryName("dark_staff");
		this.setUnlocalizedName("dark_staff");
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		Actions.swingArm(playerIn);
		
		RayTraceResult lookVec = Actions.getPlayerLookVec(playerIn, 32F);
		
		if(lookVec.entityHit != null)
		{
			Actions.spawnParticleBeamToEntity(playerIn, lookVec.entityHit, EnumParticleTypes.DRAGON_BREATH, 32);
			Actions.spawnParticleBurstAtEntity(worldIn, lookVec.entityHit, EnumParticleTypes.DRAGON_BREATH, ParticleShape.SPHERE, 
					ParticleBehavior.EMIT, 2, 10);
			lookVec.entityHit.attackEntityFrom(TUOMDamageSources.darkDamage, 8);
			playerIn.getHeldItem(handIn).damageItem(8, playerIn);
			
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
		
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		//Obfuscated until achievement unlock//
		tooltip.add("A " + TextFormatting.DARK_PURPLE + "dark and powerful" + TextFormatting.WHITE + " staff with unknown properties");
		tooltip.add(TextFormatting.OBFUSCATED + "Equip in offhand when wearing full Dark Opal Set to fly");		
	}

}
