package com.mco.events;

import java.util.List;

import com.mco.TUOM;
import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.items.armor.DopalArmor;
import com.mco.main.TUOMDamageSources;
import com.mco.TUOM;
import com.mco.entities.mobs.dark.demon.EntityDarkOpalDemon;
import com.mco.items.armor.DopalArmor;
import com.mco.main.TUOMDamageSources;
import com.mco.main.TUOMItems;
import com.mco.proxies.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author TheMCOverlordYT
 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = TUOM.MODID)
public class TUOMEventHandler 
{
	private static Minecraft mc = Minecraft.getMinecraft();
	private static EntityDarkOpalDemon demon;
	private static final ResourceLocation darkDeathOverlay = new ResourceLocation("tuom:textures/entities/dark.png");
	private static final ResourceLocation icons = new ResourceLocation("tuom:textures/icons.png");
	
	/**
	 * Nullifies fall damage if wearing full dark opal set and has dark staff equipped in offhand
	 */
	@SubscribeEvent
	public void cancelFallDamage(LivingFallEvent event) 
	{
		if(event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.getEntity();
			if(DopalArmor.isWearingFullSet(player, TUOMItems.DOPAL_HELMET, TUOMItems.DOPAL_CHESTPLATE, TUOMItems.DOPAL_LEGGINGS,
					TUOMItems.DOPAL_BOOTS) && player.getHeldItemOffhand().getItem() == TUOMItems.DARK_STAFF)
				event.setCanceled(true);
		}
	}
	
	/**
	 * Autosmelting for fire opal pickaxe 
	 * Damages pickaxe by an extra 4 uses
	 */
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onEvent(BlockEvent.BreakEvent event)
	{
		if(!event.getWorld().isRemote)
		{
			if(event.getPlayer() != null)
			{
				ItemStack itemHeld = event.getPlayer().getHeldItemMainhand();
				if(itemHeld != null && itemHeld.getItem().equals(TUOMItems.FOPAL_PICKAXE) && itemHeld.canHarvestBlock(event.getState()))
				{
					if(FurnaceRecipes.instance().getSmeltingResult(event.getPlayer().world.getBlockState(event.getPos()).getBlock().getItem(event.getWorld(), event.getPos(), event.getState())) != null)
					{
						ItemStack result = FurnaceRecipes.instance().getSmeltingResult(event.getPlayer().world.getBlockState(event.getPos()).getBlock().getItem(event.getWorld(), event.getPos(), event.getState()));
						int expSpawn = event.getExpToDrop();
						if(result != null) 
						{
							if(!result.isEmpty()) 
							{
								event.getWorld().setBlockToAir(event.getPos());
			                    itemHeld.damageItem(5, event.getPlayer());
							}
							
							if(!event.getWorld().isRemote) 
							{
								ItemStack itemToSpawn = result.copy();
								EntityItem entityitem = new EntityItem(event.getWorld(), event.getPos().getX() + 0.5, event.getPos().getY() + 0.5, event.getPos().getZ() + 0.5, itemToSpawn);
								EntityXPOrb xpToSpawn = new EntityXPOrb(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), expSpawn);
								
			                    event.getWorld().spawnEntity(entityitem);
			                    event.getWorld().spawnEntity(xpToSpawn);
							}								
						}								
					}
				}
			}
		}
	}
	
	/**
	 * Handles death overlay for Dark Opal Demon - finds one nearby then applies the overlay if it's in the death anim 
	 */
	@SubscribeEvent
	public void onRenderOverlay(RenderGameOverlayEvent.Post event)
	{
		EntityPlayer player = ClientProxy.getClientPlayer();
		
		List<EntityDarkOpalDemon> list = player.world.<EntityDarkOpalDemon>getEntitiesWithinAABB(EntityDarkOpalDemon.class, 
        		player.getEntityBoundingBox().grow(20));
		for(EntityDarkOpalDemon demon : list)
		{
			if(demon != null && demon.getDeathTicks() > 1 && demon.getDeathTicks() < 200)
			{
				GlStateManager.enableAlpha();
				GlStateManager.enableBlend();
				GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
				GlStateManager.color(1, 1, 1, demon.getDarknessAlpha() / 10);
				mc.getTextureManager().bindTexture(darkDeathOverlay);
				ScaledResolution res = event.getResolution();
				Gui.drawModalRectWithCustomSizedTexture(0,  0,  0,  0, res.getScaledWidth(), res.getScaledHeight(), 
						res.getScaledWidth(), res.getScaledHeight());
			}
		}
	}
	
	/*
	 * Potions
	 */
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onEntityUpdate(LivingUpdateEvent e)
	{
		if(e.getEntityLiving().isPotionActive(TUOM.darkPotion))
		{
			if(e.getEntityLiving().getActivePotionEffect(TUOM.darkPotion).getDuration() == 0 )
			{
				System.out.println("remove");
				e.getEntityLiving().removeActivePotionEffect(TUOM.darkPotion);
				return;
			}
			else 
			{	
				e.getEntityLiving().attackEntityFrom(TUOMDamageSources.darkDamage, .3F);
				System.out.println("hurt");
				e.getEntityLiving().hurtResistantTime = 7;
			}
		}
		
	}

	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onEvent(EntityViewRenderEvent.FogDensity event)
	{
		if(((EntityLivingBase) event.getEntity()).isPotionActive(TUOM.darkPotion))
		{
			System.out.println("density");
			event.setDensity(1F);
			event.setCanceled(true);
		}
	}


	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onEvent(EntityViewRenderEvent.FogColors event)
	{
		if(((EntityLivingBase) event.getEntity()).isPotionActive(TUOM.darkPotion))
		{
			event.setRed(-1F);
			event.setGreen(-1F);
			event.setBlue(-1F);
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onDarkShield(LivingDamageEvent event)
	{
		if(event.getEntity() instanceof EntityDarkOpalDemon)
		{
			EntityDarkOpalDemon demon = (EntityDarkOpalDemon) event.getEntity();
			if(demon.isArmored())
			{
				event.setAmount(event.getAmount() * .50F);
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
	/*	
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void onEvent(RenderGameOverlayEvent event)
	{
		//EntityWither
		//private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
	}*/
	
}
