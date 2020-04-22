package com.mco.main;

import net.minecraft.util.DamageSource;

public class TUOMDamageSources extends DamageSource
{
	public TUOMDamageSources(String name) 
	{
		super(name);
	}
	
	public static DamageSource darkPunch = new DamageSource("darkPunch");
	public static DamageSource darkSkull = new DamageSource("darkSkull");
	public static DamageSource darkDamage = new DamageSource("darkDamage");
	public static DamageSource darkLaser = new DamageSource("darkLaser");	
	public static DamageSource darkLifedrain = new DamageSource("darkLifedrain").setDamageBypassesArmor();
}
