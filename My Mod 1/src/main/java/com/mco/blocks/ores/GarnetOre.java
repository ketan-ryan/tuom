package com.mco.blocks.ores;

import java.util.Random;

import library.blocks.LibBlockOre;
import com.mco.TUOM;
import com.mco.main.TUOMItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class GarnetOre extends LibBlockOre 
{

	public GarnetOre(String registryName, String harvestTool, int harvestLevel) 
	{
		super(registryName, harvestTool, harvestLevel);
		this.setCreativeTab(TUOM.tuom_tab);
		this.setHardness(3F);
		this.setResistance(5F);		
	}

	@Override
    public int quantityDropped(Random random) 
	{
        return 2 + random.nextInt(2);
    }
	
	/**
     * Get the quantity dropped based on the given fortune level
     */
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return this.quantityDropped(random) + random.nextInt(fortune + 1);
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return TUOMItems.item_garnet;		
	}
	
}
