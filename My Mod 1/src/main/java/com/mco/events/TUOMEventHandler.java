package com.mco.events;

import java.util.List;

import com.mco.TUOM;
import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.main.TUOMDamageSources;
import com.mco.main.TUOMItems;
import com.mco.potions.TUOMPotions;
import com.mco.proxies.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author TheMCOverlordYT
 */
@Mod.EventBusSubscriber(modid = TUOM.MODID)
public class TUOMEventHandler 
{
	private static Minecraft mc = Minecraft.getMinecraft();
	private static EntityDarkOpalDemon demon;
	private static final ResourceLocation darkDeathOverlay = new ResourceLocation("tuom:textures/entities/dark.png");
	private static final ResourceLocation icons = new ResourceLocation("tuom:textures/icons.png");
	
	/**
	 * Nullifies fall damage if wearing full dark opal set and has dark staff equipped in offhand
	 */
/*	@SubscribeEvent
	public void cancelFallDamage(LivingFallEvent event) 
	{
		if(event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.getEntity();
			if(DopalArmor.getHasFullSet())
				event.setCanceled(true);
		}
	}
	*/
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
	
	/**
	 * Handles death overlay for Dark Opal Demon - finds one nearby then applies the overlay if it's in the death anim 
	 * 
	 * @param event the RenderGameOverlay event
	 */
	@SubscribeEvent
	public static void onRenderOverlay(RenderGameOverlayEvent.Post event)
	{
		EntityPlayer player = ClientProxy.getClientPlayer();
		
		List<EntityDarkOpalDemon> list = player.world.<EntityDarkOpalDemon>getEntitiesWithinAABB(EntityDarkOpalDemon.class, 
        		player.getEntityBoundingBox().grow(20));
		for(EntityDarkOpalDemon demon : list)
		{
			if(demon != null && demon.getDeathTicks() > 1 && demon.getDeathTicks() < 200)
			{
				//Setup GL methods
				GlStateManager.enableAlpha();
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
				//Set to black with progressively higher alpha
				GlStateManager.color(1, 1, 1, demon.getDarknessAlpha() / 10);
				//OSetup texture and size
				mc.getTextureManager().bindTexture(darkDeathOverlay);
				ScaledResolution res = event.getResolution();
				//Draw the overlay
				Gui.drawModalRectWithCustomSizedTexture(0,  0,  0,  0, res.getScaledWidth(), res.getScaledHeight(), 
						res.getScaledWidth(), res.getScaledHeight());
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
	 * When armored, any damge the {@link EntityDarkOpalDemon}
	 * takes will be reduced by 50%
	 * 
	 * @param event the LivingDamageEvent to cancel
	 */
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void onDarkShield(LivingDamageEvent event)
	{
		//We only want this to apply to the demon
		if(event.getEntity() instanceof EntityDarkOpalDemon)
		{
			EntityDarkOpalDemon demon = (EntityDarkOpalDemon) event.getEntity();
			
			//If armored, halve damge
			if(demon.isArmored())
				event.setAmount(event.getAmount() * .50F);			
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
	/*	
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void onEvent(RenderGameOverlayEvent event)
	{
		//EntityWither
		//private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
	}*/
	
}
