package com.mco.items.armor;

import library.items.LibItemArmor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class TopazArmor extends LibItemArmor {

	public TopazArmor(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(materialIn, equipmentSlotIn);
	}

	@Override
	protected String getArmorWrapTexture() 
	{
		return "topaz_armor";
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if(!player.isSpectator()) 
		{
			int i = MathHelper.floor(player.posX);
	        int j = MathHelper.floor(player.posY);
	        int k = MathHelper.floor(player.posZ);
			
	        NonNullList<ItemStack> armor = player.inventory.armorInventory;
			int armorPieces = 0;
			
			for(ItemStack itemArmor: armor)
			{
				if(itemArmor != null && itemArmor.getItem() instanceof TopazArmor)
				{
					armorPieces += 1;
				}
			}	
	        
			if(armorPieces >= 4) 
			{
				for (int l = 0; l < 4; ++l)
		        {
		            i = MathHelper.floor(player.posX + (double)((float)(l % 2 * 2 - 1) * 0.25F));
		            j = MathHelper.floor(player.posY);
		            k = MathHelper.floor(player.posZ + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
		            BlockPos blockUnder = new BlockPos(i, j-1, k);
		
		            if (world.getBlockState(blockUnder).getMaterial() == Material.WATER && Blocks.ICE.canPlaceBlockAt(world, blockUnder) && world.getBlockState(blockUnder).equals(Blocks.WATER.getDefaultState()))
		            {
		                world.setBlockState(blockUnder, Blocks.ICE.getDefaultState());
		            }
		        }			
			}
		}
	}

}
