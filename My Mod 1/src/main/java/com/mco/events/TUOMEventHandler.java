package com.mco.events;

import com.mco.TUOM;
import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.items.armor.DopalArmor;
import com.mco.main.TUOMDamageSources;
import com.mco.main.TUOMItems;
import com.mco.potions.TUOMPotions;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
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
	@SuppressWarnings("deprecated")
	public static void onEvent(HarvestDropsEvent event)
	{
		if(event.getHarvester() != null)
		{
			EntityPlayer player = event.getHarvester();
			World world = player.getEntityWorld();
			ItemStack itemHeld = event.getHarvester().getHeldItemMainhand();
			if(itemHeld != null && itemHeld.getItem().equals(TUOMItems.FOPAL_PICKAXE) && itemHeld.canHarvestBlock(event.getState()))
			{
				if(FurnaceRecipes.instance().getSmeltingResult(new ItemStack(ItemBlock.getItemFromBlock(event.getHarvester().world.getBlockState(event.getPos()).getBlock()))) != null)
				{
					System.out.println(world.getBlockState(event.getPos()));
					System.out.println(FurnaceRecipes.instance().getSmeltingResult(new ItemStack(ItemBlock.getItemFromBlock(event.getHarvester().world.getBlockState(event.getPos()).getBlock()))));
					ItemStack result = FurnaceRecipes.instance().getSmeltingResult(event.getHarvester().world.getBlockState(event.getPos()).getBlock().getItem(event.getWorld(), event.getPos(), event.getState()));
					if(result != null) 
					{
						if(!result.isEmpty()) 
						{
							event.getWorld().setBlockToAir(event.getPos());
		                    itemHeld.damageItem(5, event.getHarvester());
						}
						
						if(!event.getWorld().isRemote) 
						{
							ItemStack itemToSpawn = result.copy();
							EntityItem entityitem = new EntityItem(event.getWorld(), event.getPos().getX() + 0.5, event.getPos().getY() + 0.5, event.getPos().getZ() + 0.5, itemToSpawn);
		                    event.getWorld().spawnEntity(entityitem);
						}								
					}								
				}
			}
		}
	}
	
/*	@SubscribeEvent
	public static void autoSmelt(HarvestDropsEvent event) 
	{
		if(event.getHarvester() != null) 
		{
			EntityPlayer player = event.getHarvester();
			IBlockState state = event.getState();
			World world = event.getWorld();
			BlockPos pos = event.getPos();
			
			ItemStack heldItem = player.getHeldItemMainhand();
			ItemStack offItem = player.getHeldItemOffhand();
			
			if(heldItem != null && heldItem.getItem().equals(TUOMItems.FOPAL_PICKAXE) && heldItem.canHarvestBlock(state)) 
			{
				EnumFacing direction = EnumFacing.VALUES[world.rand.nextInt(6)];
				System.out.println(state.getBlock());
				//if(state.getBlock() == world.getBlockState(pos.offset(direction, 1)).getBlock()) 
				//{
					ItemStack result = FurnaceRecipes.instance().getSmeltingResult(world.getBlockState(pos).getBlock().getPickBlock(state, player.rayTrace(5, 1),world, pos, player));
					System.out.println(world.getBlockState(pos).getBlock().getPickBlock(state, player.rayTrace(5, 1),world, pos, player));
					if(result != null) 
					{
						player.getHeldItemMainhand().damageItem(5, player);
						event.getDrops().add(result);
					}
				//}
			}
		}
	}*/
	
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