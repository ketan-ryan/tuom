package com.mco.items.tools.topaz;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemSword;
import library.particle.ParticleBehavior;
import library.particle.ParticleShape;
import library.util.Actions;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class TopazSword extends LibItemSword
{
	World worldObj;

	public TopazSword(String registryName, ToolMaterial material, World world) 
	{
		super(registryName, material);
		worldObj = world;
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	public void initRecipe() 
	{
		LibRegistry.addShapedRecipe(this, 1, 
				
				"t",
				"t",
				"s",
				
				't', TUOMItems.item_topaz,
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
//		target.setFire(2);
//		target.setGlowing(true);
//		target.setHealth(2);
//		target.setVelocity(1,  1,  1);
//		target.setCustomNameTag("rekt");
		
		return super.hitEntity(stack, target, attacker);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		RayTraceResult lookVec = Actions.getPlayerLookVec(playerIn, 20);
		if(lookVec.entityHit != null)
		{
			Actions.spawnParticleBeamToEntity(playerIn, lookVec.entityHit, EnumParticleTypes.END_ROD, 20);
			Actions.spawnParticleBurstAtEntity(worldIn, lookVec.entityHit, EnumParticleTypes.DRAGON_BREATH, ParticleShape.SPHERE, ParticleBehavior.VORTEX, 5, 60);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
}
