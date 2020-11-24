package com.mco.items.armor;

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
	private static boolean fullSet = false;

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
				if(TUOMItems.DARK_STAFF.equals(item) && armorPieces == 4)
						fullSet = true;
				else
					fullSet = false;
			}
		}	
	}

	public static boolean hasFullSet()
	{
		return fullSet;
	}
}