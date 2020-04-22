package com.mco.blocks.ores;

import java.util.Random;

import javax.annotation.Nullable;

import library.LibRegistry;
import library.blocks.LibBlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TUOMOre extends LibBlockOre{
	
	private boolean dropSelf;
	private Item itemToDrop;
	private int quantity;
	private int fortune;
	private int yield;
	private int xp;
	private float smeltXP;
	
	/**
	 * @param registryName The name to register the ore as
	 * @param harvestLevel What pickaxe you need to break the block 
	 * @param xp How much XP the block drops upon being mined
	 * @param smeltXP How much XP the player gains from smelting the ore
	 * @param hardness How long it takes to mine the block
	 * @param resistance The block's resistance to TNT
	 * @param dropSelf Whether the block drops an ore or itself
	 * @param itemToDrop The ore to drop
	 * @param quantity The base amount of the ore to drop
	 * @param fortune How much fortune affects the ore
	 * @param yield The max extra that can drop off the ore without fortune
	 * */
	public TUOMOre(String registryName, int harvestLevel, int xp, float smeltXP, int hardness, int resistance, boolean dropSelf, 
			@Nullable Item itemToDrop, int quantity, int fortune, int yield) {
		
		super(registryName, "pickaxe", harvestLevel);
	
		this.dropSelf = dropSelf;
		this.itemToDrop = itemToDrop;
		this.quantity = quantity;
		this.fortune = fortune;
		this.yield = yield;
		this.xp = xp;
		this.smeltXP = smeltXP;
		
		setHardness(hardness);
		setResistance(resistance);	
	}

	@Override
    public int quantityDropped(Random random) 
	{
		if(!dropSelf)
			return quantity + random.nextInt(yield);
		else
			return 1;
    }
	
	/**
     * Get the quantity dropped based on the given fortune level
     */
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
    	if(!dropSelf)
    		return this.quantityDropped(random) + random.nextInt(fortune + 1);
    	else
    		return 1;
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if(!dropSelf)
			return this.itemToDrop;
		else
			return new ItemStack(this).getItem();
	}
	
	@Override
	public void dropXpOnBlockBreak(World worldIn, BlockPos pos, int amount) {
		super.dropXpOnBlockBreak(worldIn, pos, new Random().nextInt(this.xp));
	}
	
	@Override
	public void initRecipe() 
	{
		if(!dropSelf)
			LibRegistry.addSmeltingRecipe(this.itemToDrop, 1, this.smeltXP, this);
	}
		
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(this);
	}
	
}
