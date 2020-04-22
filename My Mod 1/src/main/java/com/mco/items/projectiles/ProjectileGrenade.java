package com.mco.items.projectiles;

import library.LibRegistry;
import library.entities.projectile.LibEntityProjectile;
import library.items.LibItemProjectile;
import com.mco.TUOM;
import com.mco.entities.projectiles.ProjectileGrenadeEntity;
import com.mco.main.TUOMItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class ProjectileGrenade extends LibItemProjectile 
{

	public ProjectileGrenade(String registryName) 
	{
		super(registryName);
		this.setCreativeTab(TUOM.tuom_tab);
	}

	@Override
	public void initRecipe() {
		LibRegistry.addShapedRecipe(this, 4, 
				
				"sfs",
				"ftf",
				"sfs",
				
				'f', TUOMItems.fire_opal,
				's', Items.SNOWBALL,
				't', Blocks.TNT
				
		);
	}
	
	@Override
	public Class<? extends LibEntityProjectile> getEntityProjectile() 
	{
		return ProjectileGrenadeEntity.class;
	}

}
