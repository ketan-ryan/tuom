package com.mco.entities.mobs.fire;

import library.entities.LibEntityMob;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityFireOpalDemon extends LibEntityMob<LibEntityMob> implements IMob, IAnimatedEntity
{

	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, 
    		BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
	
	private Animation animation = NO_ANIMATION;
	private int animationTick;
	
	public static Animation ANIMATION_SWORD = Animation.create(120);            
	public static Animation ANIMATION_SKULL = Animation.create(100);           
	public static Animation ANIMATION_FIREBLAST = Animation.create(35);        
	public static Animation ANIMATION_SHIELD = Animation.create(60);
	public static Animation ANIMATION_LIGHTNING = Animation.create(100);
	public static Animation ANIMATION_DEATH = Animation.create(300);           
	public static Animation ANIMATION_MINION = Animation.create(60);            
	public static Animation ANIMATION_PUNCH = Animation.create(120);           
	public static Animation ANIMATION_BOMBS = Animation.create(60);
	
	private static final Animation[] ANIMATIONS = {ANIMATION_SWORD, ANIMATION_SKULL, ANIMATION_FIREBLAST, ANIMATION_SHIELD, ANIMATION_LIGHTNING, 
			ANIMATION_DEATH, ANIMATION_MINION, ANIMATION_PUNCH, ANIMATION_BOMBS};
	
	public EntityFireOpalDemon(World world) {
		super(world);
	}

	/**
     * Sets the custom name tag for this entity
     */
    public void setCustomNameTag(String name)
    {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }
	
    /**
     * Add the given player to the list of players tracking this entity. For instance, a player may track a boss in
     * order to view its associated boss bar.
     */
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }
    

    @Override
	public int getAnimationTick() 
	{
		return animationTick;
	}

	@Override
	public void setAnimationTick(int tick) 
	{
		animationTick = tick;
	}

	@Override
	public Animation getAnimation() 
	{
		return this.animation;
	}

	@Override
	public void setAnimation(Animation animation) 
	{
		if (animation == NO_ANIMATION) {
            onAnimationFinish(this.animation);
            setAnimationTick(0);
        }
        this.animation = animation;
	}

	@Override
	public Animation[] getAnimations() 
	{
		return ANIMATIONS;
	}

	protected void onAnimationFinish(Animation animation) {}

	public Animation getDeathAnimation() {
		return ANIMATION_DEATH;
	}

}
