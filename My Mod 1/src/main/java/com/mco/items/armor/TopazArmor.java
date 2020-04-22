package com.mco.items.armor;

import com.mco.TUOM;
import com.mco.main.TUOMItems;
import library.LibRegistry;
import library.items.LibItemArmor;
import library.util.Actions;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class TopazArmor extends LibItemArmor {

	public TopazArmor(String registryName, ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) 
	{
		super(registryName, materialIn, equipmentSlotIn);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	protected String getArmorWrapTexture() 
	{
		return "topaz_armor";
	}
	
	
	@Override
	public void initRecipe() 
	{
		LibRegistry.addShapedRecipe(TUOMItems.topaz_helmet, 1,
				
				"ttt",
				"t t",
				"   ",
				
				't', TUOMItems.item_topaz
				//'r', new ItemStack(Blocks.WOOL, 1, EnumDyeColor.RED.getMetadata())
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.topaz_chestplate, 1, 
				
				"t t",
				"ttt",
				"ttt",
				
				't', TUOMItems.item_topaz
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.topaz_leggings, 1, 
				
				"ttt",
				"t t",
				"t t",
				
				't', TUOMItems.item_topaz
				
		);
		LibRegistry.addShapedRecipe(TUOMItems.topaz_boots, 1, 
				
				"t t",
				"t t",
				"   ",
				
				't', TUOMItems.item_topaz
				
		);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		super.onArmorTick(world, player, itemStack);
		
		int i = MathHelper.floor(player.posX);
        int j = MathHelper.floor(player.posY);
        int k = MathHelper.floor(player.posZ);
		
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

		if(this.getEquipmentSlot() == EntityEquipmentSlot.CHEST)
			Actions.addPotionEffect(player, MobEffects.RESISTANCE, 10, 0, false);
		
		else if(this.getEquipmentSlot() == EntityEquipmentSlot.FEET)
				Actions.addPotionEffect(player, MobEffects.SPEED, 10, 0, false);
		
		else if(this.getEquipmentSlot() == EntityEquipmentSlot.LEGS)
					Actions.addPotionEffect(player, MobEffects.JUMP_BOOST, 10, 0, false);
		
	}

}
