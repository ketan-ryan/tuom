package com.mco.events;

import com.mco.TUOM;
import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.items.armor.DopalArmor;
import com.mco.main.TUOMDamageSources;
import com.mco.main.TUOMItems;
import com.mco.potions.TUOMPotions;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author TheMCOverlordYT
 */
/**
 * @author TheMCOverlordYT
 */
@Mod.EventBusSubscriber(modid = TUOM.MODID)
public class TUOMEventHandler 
{	
	/**
	 * Autosmelting for fire opal pickaxe 
	 * Damages pickaxe by an extra 4 uses
	 */
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void onEvent(HarvestDropsEvent event)
	{
		if(event.getHarvester() != null)
		{
			EntityPlayer player = event.getHarvester();

			Item mainHand = player.getHeldItemMainhand().getItem();
			World world = event.getWorld();

			FurnaceRecipes fr = FurnaceRecipes.instance();
			if(TUOMItems.FOPAL_PICKAXE.equals(mainHand))
			{
				Block block = event.getState().getBlock();
				for(ItemStack stack : event.getDrops())
				{
					ItemStack sr = fr.getSmeltingResult(stack);
					if(ItemStack.EMPTY != sr && !event.isSilkTouching() && event.getFortuneLevel() == 0)
					{
						event.getDrops().clear();
						event.getDrops().add(sr);
						player.getHeldItemMainhand().damageItem(4, player);
					}
				}
			}
		}
	}

	/*
	 * Potions
	 */
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void onEntityUpdate(LivingUpdateEvent e)
	{
		//Handle only dark potion
		if(e.getEntityLiving().isPotionActive(TUOMPotions.DARK_POTION))
		{
			//If expired, remove
			if(e.getEntityLiving().getActivePotionEffect(TUOMPotions.DARK_POTION).getDuration() == 0 )
			{
				e.getEntityLiving().removeActivePotionEffect(TUOMPotions.DARK_POTION);
				return;
			}
			
			//When applied, should take rapid damage
			else 
			{	
				e.getEntityLiving().attackEntityFrom(TUOMDamageSources.darkDamage, .3F);
				e.getEntityLiving().hurtResistantTime = 7;
			}
		}
		
	}
	
	/**
	 * When armored, any damage the {@link EntityDarkOpalDemon}
	 * takes will be reduced by 50%
	 *
	 * Cancels fall damage if player is wearing full dark opal
	 * 
	 * @param event the LivingDamageEvent to cancel
	 */
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void onDarkShield(LivingHurtEvent event)
	{
		//We only want this to apply to the demon
		if(event.getEntity() instanceof EntityDarkOpalDemon)
		{
			EntityDarkOpalDemon demon = (EntityDarkOpalDemon) event.getEntity();
			
			//If armored, halve damge
			if(demon.isArmored())
				event.setAmount(event.getAmount() * .50F);			
		}
		else if(event.getEntity() instanceof EntityPlayer)
		{
			if(DopalArmor.hasFullSet() && event.getSource() == DamageSource.FALL) {
				event.setAmount(0);
				event.setCanceled(true);
			}
		}
	}

//	public static void onEntityUpdateEvent(LivingUpdateEvent event){
//	if( event.entityLiving.getActivePotionEffect(TUOM.lightPotion).getDuration() == 0){
//		event.entityLiving.removePotionEffect(TUOM.lightPotion.id);
//		return;
//		}
//}
	
//	@SubscribeEvent
//	public static void onEvent(LivingUpdateEvent event){
//		if(event.getEntityLiving().isPotionActive(TUOM.lightPotion)){
//			if(event.getEntityLiving().motionY >= .000000001F){
//				event.getEntityLiving().motionY = -1F;
//			}
//		}
//	}
//	
//	@SubscribeEvent
//	public static void onEvent(FOVUpdateEvent event){
//		if(event.getEntity().isPotionActive(TUOM.lightPotion)){
//			event.setNewfov(1F);
//		}
//	}		
}