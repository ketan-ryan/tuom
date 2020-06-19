package com.mco.items.armor;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import com.mco.TUOM;
import com.mco.main.TUOMItems;

import library.items.LibItemArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class DopalArmor extends LibItemArmor
{	
	private static boolean hasFullSet = false;
	
	public DopalArmor(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(materialIn, equipmentSlotIn);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	protected String getArmorWrapTexture() 
	{
		return "dopal_armor";
	}
 
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{		
		if(!player.isSpectator()) 
		{
			NonNullList<ItemStack> armor = player.inventory.armorInventory;
			int armorPieces = 0;
			
			for(ItemStack itemArmor: armor)
			{
				if(itemArmor != null && itemArmor.getItem() instanceof DopalArmor) 
				{
					armorPieces += 1;
				}
			}	
			
			Item item = player.getHeldItemOffhand().getItem();
			if(item != null)
			{
				if(item == TUOMItems.DARK_STAFF) 
				{
					if(armorPieces >= 4) 
					{
						hasFullSet = true;
						try 
						{
							Keyboard.create();
						} catch (LWJGLException e) 
						{
							e.printStackTrace();
						}
						if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) 
						{
							player.motionY += 0.04;
							player.motionX *= 1.01;
							player.motionZ *= 1.01;
							player.isAirBorne = true;
						}
					}
					else
						hasFullSet = false;
				}
			}
		}	
	}
	
	public static boolean getHasFullSet() 
	{
		return hasFullSet;
	}
}