package com.mco.items.tools.fopal;

import java.util.List;

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

public class FopalSword extends LibItemSword
{
	World worldObj;

	public FopalSword(String registryName, ToolMaterial material, World world) 
	{
		super(registryName, material);
		worldObj = world;
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(TextFormatting.RED + "Sets entities on fire when hit.");
		tooltip.add(TextFormatting.RED + "Right click an entity to set on fire (damages sword).");
	}
	
	@Override
	public void initRecipe() 
	{
		LibRegistry.addShapedRecipe(this, 1, 
				
				"f",
				"f",
				"s",
				
				'f', TUOMItems.fire_opal,
				's', Items.STICK			
				
		);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		/*if(target != null) 
		{
			EntityLightningBolt lightning = new EntityLightningBolt(worldObj, target.posX, target.posY, target.posZ, true);
			if(lightning != null)
				worldObj.addWeatherEffect(lightning);
		}
//		*/
		target.setFire(1);
//		target.setVelocity(1,  1,  1);
		
		return super.hitEntity(stack, target, attacker);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		RayTraceResult lookVec = Actions.getPlayerLookVec(playerIn, 50);
		
		if(lookVec.entityHit != null)
		{
			float damageMod = (float) (lookVec.entityHit.getDistance(playerIn) / 2);
			Actions.spawnParticleBeamToEntity(playerIn, lookVec.entityHit, EnumParticleTypes.FLAME, 20);
			lookVec.entityHit.setFire(5);
			playerIn.getHeldItem(handIn).damageItem(Math.round(damageMod), playerIn);
		}
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
}
