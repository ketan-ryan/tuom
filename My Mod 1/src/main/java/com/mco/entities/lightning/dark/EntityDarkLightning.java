package com.mco.entities.lightning.dark;

import com.mco.main.TUOMBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityDarkLightning extends EntityLightningBolt
{

	public EntityDarkLightning(World worldIn, double x, double y, double z, boolean effectOnlyIn) 
	{
		super(worldIn, x, y, z, effectOnlyIn);
		this.ignoreFrustumCheck = true;
	}

}
