package com.mco.entities.mobs.dark.demon;

import java.util.List;
import java.util.Random;

import com.mco.entities.mobs.dark.demon.ai.AIBlindingPunches;
import com.mco.entities.mobs.dark.demon.ai.AIJump;
import library.entities.LibEntityMob;
import library.util.Actions;
import library.util.Conditions;
import com.mco.entities.mobs.dark.demon.ai.AIBlindingPunches;
import com.mco.entities.mobs.dark.demon.ai.AIDarkBombs;
import com.mco.entities.mobs.dark.demon.ai.AIJump;
import com.mco.entities.mobs.dark.demon.ai.AILifedrain;
import com.mco.entities.mobs.dark.demon.ai.AIMinion;
import com.mco.entities.mobs.dark.demon.ai.AIShield;
import com.mco.entities.mobs.dark.demon.ai.AISkullShoot;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedChicken;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedCow;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedPig;
import com.mco.entities.mobs.dark.demon.corrupted.EntityCorruptedSheep;
import com.mco.entities.projectiles.ProjectileGrenadeEntity;
import com.mco.main.TUOMConfig;
import com.mco.main.TUOMItems;
import com.mco.main.TUOMSoundHandler;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityIllusionIllager;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDarkOpalDemon extends LibEntityMob<LibEntityMob> implements IMob, IAnimatedEntity, IRangedAttackMob
{
	private SoundEvent spawnSound = TUOMSoundHandler.darkOpalSpawn;
	private SoundEvent idleSound = TUOMSoundHandler.darkOpalIdle;
	private SoundEvent hurtSound = TUOMSoundHandler.darkOpalHurt;
	private SoundEvent deathSound = TUOMSoundHandler.darkOpalDeath;
	
	private Animation animation = NO_ANIMATION;
	private int animationTick;
	
	public static Animation ANIMATION_JUMP = Animation.create(120);            
	public static Animation ANIMATION_SKULL = Animation.create(100);           
	public static Animation ANIMATION_LIFEDRAIN = Animation.create(35);        
	public static Animation ANIMATION_SHIELD = Animation.create(60);
	public static Animation ANIMATION_LIGHTNING = Animation.create(100);
	public static Animation ANIMATION_DEATH = Animation.create(300);           
	public static Animation ANIMATION_MINION = Animation.create(60);            
	public static Animation ANIMATION_PUNCH = Animation.create(120);           
	public static Animation ANIMATION_BOMBS = Animation.create(60);
	
	private static final Animation[] ANIMATIONS = {ANIMATION_JUMP, ANIMATION_SKULL, ANIMATION_LIFEDRAIN, ANIMATION_SHIELD, ANIMATION_LIGHTNING, 
			ANIMATION_DEATH, ANIMATION_MINION, ANIMATION_PUNCH, ANIMATION_BOMBS};
	
	public float targetDistance;
    public float targetAngle;
	
	protected ResourceLocation lootTable = LootTableList.ENTITIES_WOLF;
	
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, 
    		BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
    
	public AnimationAI currentAnim;

	public static Random rand = new Random();
	
	private int deathTicks;
	private float alpha = 0;	
	private boolean armored = false;

	public EntityDarkOpalDemon(World worldIn) 
	{
		super(worldIn);
		
		tasks.addTask(1, new AIJump(this, ANIMATION_JUMP));
		tasks.addTask(1, new AISkullShoot(this, ANIMATION_SKULL));
		tasks.addTask(1, new AIBlindingPunches(this, ANIMATION_PUNCH));
		tasks.addTask(1, new AIShield(this, ANIMATION_SHIELD));
		tasks.addTask(1, new AILifedrain(this, ANIMATION_LIFEDRAIN));
		tasks.addTask(1, new AIDarkBombs(this, ANIMATION_BOMBS));
		tasks.addTask(1, new AIMinion(this, ANIMATION_MINION));
		
		tasks.addTask(0, new EntityAIWander(this, 1.5D));
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 32F));
		tasks.addTask(2, new EntityAILookIdle(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
		
        this.lootTable = null;
        
		this.setSize(4F, 9F);
		this.isImmuneToFire = true;
		this.experienceValue = 50;
		this.ignoreFrustumCheck = true;
		this.enableFadeOutDeath(200);
		this.experienceValue = 150;
	}
	
	public boolean isAIDisabled()
	{
		return false;
	}
	
	@Override
	public boolean isEntityUndead()
	{
		return true;
	}
	
	public boolean canDespawn()
	{
		return false;
	}
	
	public boolean isNonBoss()
	{
		return false;
	}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(TUOMConfig.darkHealth);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8);
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
        
        if (getAnimation() != NO_ANIMATION) 
        {
            animationTick++;
            if (world.isRemote && animationTick >= animation.getDuration()) 
            {
                setAnimation(NO_ANIMATION);
            }
        }
        
        if (getAttackTarget() != null) 
        {
            targetDistance = getDistance(getAttackTarget());
            targetAngle = (float) getAngleBetweenEntities(this, getAttackTarget());
        }    
        
        if(this.getHealth() <= this.getMaxHealth() / 2.0F)
        {
	        if(getAttackTarget() != null && currentAnim == null && getAnimation() == NO_ANIMATION && getAnimation() != ANIMATION_DEATH && !isArmored())
	        {
	        	AnimationHandler.INSTANCE.sendAnimationMessage(this, ANIMATION_SHIELD);
	        }
	        armored = true;
        }
        
        /**
    	 * Randomly select an animation to play without overriding itself
    	 */
        if(getAttackTarget() != null && currentAnim == null && getAnimation() == NO_ANIMATION && getAnimation() != ANIMATION_DEATH)
        {
        	switch(new Random().nextInt(6))
        	{
        		case 0:
        			AnimationHandler.INSTANCE.sendAnimationMessage(this, ANIMATION_JUMP);
        			break;
        		case 1:
        			if(getAttackTarget() != null && this.getDistance(getAttackTarget()) > 16)
        			{
	        			AnimationHandler.INSTANCE.sendAnimationMessage(this, ANIMATION_SKULL);
        			}
        			break;
        		case 2:
        			AnimationHandler.INSTANCE.sendAnimationMessage(this,  ANIMATION_PUNCH);
        			break;
        		case 3:
        			AnimationHandler.INSTANCE.sendAnimationMessage(this, ANIMATION_LIFEDRAIN);
        			break;
        		case 4:
        			AnimationHandler.INSTANCE.sendAnimationMessage(this, ANIMATION_BOMBS);
        			break;
        		case 5: 
        			AnimationHandler.INSTANCE.sendAnimationMessage(this, ANIMATION_MINION);
        			break;
        		default: 
        			break;
        	}
        }
        
        /**
    	 * Handles particles of jump animation
    	 */        
        if(getAnimation() == ANIMATION_JUMP)
        {
        	if(getAnimationTick() == 25) 
        	{
        		for(int i = 0; i < 200; i++) 
        		{
	        		this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, 
	                		this.posY + (i / 50), this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0,
	                		1D, 0);
        		}
        	}
        	
        	else if(getAnimationTick() == 86) 
        	{
	        	for(int i = 0; i < 720; i++) 
				{
	                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, 
	                		this.posY - 1, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, Math.sin((float)Math.toRadians(i)),
	                		0.0D, Math.cos((float)Math.toRadians(i)));
				}	
        	}
        }
        
        /**
    	 * Handles particles of punch teleport
    	 */  
        if(getAnimation() == ANIMATION_PUNCH)
        {
        	if(getAnimationTick() == 1 || getAnimationTick() == 29 || getAnimationTick() == 35 || 
        			getAnimationTick() == 41 || getAnimationTick() == 47) 
        	{
        		spawnParticle(1000, EnumParticleTypes.DRAGON_BREATH, (int) (this.rand.nextDouble() * (double)this.height), 0, 0, 0);
        	}
        		
        }
        
	}
	/**
	 * @param forMax How many times the for loop iterates
	 * @param particle Particle to spawn
	 * @param yMod What value to change the y position by
	 * @param xMot X motion of the particle
	 * @param yMot Y motion of the particle
	 * @param zMot Z motion of the particle
	 * */
	private void spawnParticle(int forMax, EnumParticleTypes particle, int yMod, float xMot, float yMot, float zMot)
	{
		for(int i = 0; i < forMax; i++) 
		{
            this.world.spawnParticle(particle, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, 
            		this.posY + yMod, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, xMot,
            		0.0D, zMot);
		}	
	}
	
	/**
	 * Nullifies fall damage 
	 */
	public void fall(float distance, float damageMultiplier){}
	
	/**
	 * Helper method to get angle between entities
	 */
	public double getAngleBetweenEntities(Entity first, Entity second) 
	{
        return Math.atan2(second.posZ - first.posZ, second.posX - first.posX) * (180 / Math.PI) + 90;
    }
		
	/**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        
        if (this.hasCustomName())
        {
            this.bossInfo.setName(this.getDisplayName());
        }
    }
    
    /**
     * Returns whether the demon is armored with its boss armor or not by checking whether its health is below half of
     * its maximum.
     */
    public boolean isArmored()
    {
    	return this.armored;
    }
    
    public void setArmored(boolean b)
    {
    	this.armored = b;
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
	protected SoundEvent getAmbientSound()
	{
		return idleSound;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damage)
	{
		return hurtSound;
	}
	
	@Override
	protected SoundEvent getDeathSound()
	{
		return deathSound;
	}
	
	public boolean isAIEnabled()
	{
		return true;
	}
		
	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return this.height / 8.0F;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return 8;
	}

	/**
	 * Equips the demon with an item puts a message in chat 
	 */
	@Override
	public void onFirstSpawn()
	{
		super.onFirstSpawn();

		Actions.playSound(this, spawnSound, 5F, 1F);		
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(TUOMItems.dark_staff));
		EntityPlayer player = Actions.getClosestPlayer(this);
		Actions.chatAtPlayer(player, TextFormatting.DARK_PURPLE + "You've made a grave mistake...");
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) 
	{
		if(isArmored()) {
			Entity entity = source.getImmediateSource();

            if (entity instanceof EntityArrow)
            {
                return false;
            }
		}
		if(source.equals(DamageSource.WITHER))
			return false;
		return super.attackEntityFrom(source, amount);
	}
	
	@Override
	public void onDeath(DamageSource cause)
	{
		super.onDeath(cause);
		
		if(!world.isRemote)
			dropItem(TUOMItems.dark_staff, 1);		
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
	
	/**
	 * Handles death update. 
	 * Chat printout, particles, sounds, time changing, 
	*/
    protected void onDeathUpdate() 
    {
        onDeathAIUpdate();
        deathTicks++;

        if(getAnimation() == NO_ANIMATION && currentAnim == null)
        AnimationHandler.INSTANCE.sendAnimationMessage(this, ANIMATION_DEATH);
        
        //Death Message
        EntityPlayer player = Actions.getClosestPlayer(this);
        if(deathTicks == 1)
        	Actions.chatAtPlayer(player, TextFormatting.DARK_PURPLE + "You haven't seen the last of me...");

        //Main explosion
        if(deathTicks >= 75) {
	        for (int n = 0; n < 20; n++) 
	        {
	            double d2 = rand.nextGaussian() * 0.02D;
	            double d0 = rand.nextGaussian() * 0.02D;
	            double d1 = rand.nextGaussian() * 0.02D;
	            world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, posX + (double) (rand.nextFloat() * width * 2.0F) - (double) width, posY + (double) (rand.nextFloat() * height), posZ + (double) (rand.nextFloat() * width * 2.0F) - (double) width, d2, d0, d1);
	        }
        }
        
        //Replaces regular mobs with their corrupted variants, or husks
        if(deathTicks == 200)
        {
            List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, 
            		this.getEntityBoundingBox().grow(30.0D, 30.0D, 30.0D));
        	Actions.playSound(this, TUOMSoundHandler.darkOpalWhoosh, 1, 5);

            for(EntityLivingBase entity : list)
            {
            	BlockPos location = new BlockPos(entity.getPosition());
            	
            	if(entity instanceof EntityChicken)
            	{
            		EntityCorruptedChicken chicken = new EntityCorruptedChicken(world);
            		chicken.setPosition(location.getX(), location.getY(), location.getZ());
            		if(!world.isRemote)
            			world.spawnEntity(chicken);
            		entity.setDead();
            	}
            	else if (entity instanceof EntityPig)
            	{
            		EntityCorruptedPig pig = new EntityCorruptedPig(world);
            		pig.setPosition(location.getX(), location.getY(), location.getZ());
            		if(!world.isRemote)
            			world.spawnEntity(pig);
            		entity.setDead();
            	}
            	else if(entity instanceof EntityCow)
            	{
            		EntityCorruptedCow cow = new EntityCorruptedCow(world);
            		cow.setPosition(location.getX(), location.getY(), location.getZ());
            		if(!world.isRemote)
            			world.spawnEntity(cow);
            		entity.setDead();
            	}
            	else if(entity instanceof EntityRabbit)
            	{
            		EntityRabbit rabbit = new EntityRabbit(world);
            		rabbit.setRabbitType(99);
            		rabbit.setPosition(location.getX(), location.getY(), location.getZ());
            		if(!world.isRemote)
            			world.spawnEntity(rabbit);
            		entity.setDead();
            	}
            	else if(entity instanceof EntitySheep)
            	{
            		EntityCorruptedSheep sheep = new EntityCorruptedSheep(world);
            		sheep.setPosition(location.getX(), location.getY(), location.getZ());
            		if(!world.isRemote)
            			world.spawnEntity(sheep);
            		entity.setDead();
            	}
            	else if(entity instanceof EntityVillager)
            	{
            		EntityIllusionIllager illager = new EntityIllusionIllager(world);
            		illager.setPosition(location.getX(), location.getY(), location.getZ());
            		if(!world.isRemote)
            			world.spawnEntity(illager);
            		entity.setDead();
            	}
            	else if(!(entity instanceof EntityPlayer) && !(entity instanceof EntityMob) && entity.isNonBoss())
            	{
            		EntityHusk husk = new EntityHusk(world);
            		husk.setPosition(location.getX(), location.getY(), location.getZ());
            		if(!world.isRemote)
            			world.spawnEntity(husk);
            		entity.setDead();
            	}            	
            }            
        	setDead();
        }        

        if(Conditions.secondsGoneBy(world, 1)) 
        {
        	//Time updating
        	if(world.getWorldTime() < 23000 && TUOMConfig.darkDay)
        	{
        		long discrepancy = 23000 - world.getWorldTime();
        		world.provider.setWorldTime(world.getWorldTime() + discrepancy / 5);
        	}
        	
        	//Sends out rings and plays sound
	        for(int y = 0; y < 10; y ++) 
	        {
	        	alpha += 0.1F;
	        	if(y < 9)
	        		Actions.playSound(this, TUOMSoundHandler.darkOpalWhoosh, 5, 1);
	        	for(int i = 0; i < 360; i++) 
				{
	                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * ((double)this.width - y/2), 
	                		this.posY + y, this.posZ + (this.rand.nextDouble() - 0.5D) * ((double)this.width - y/2), 
	                		2 * Math.sin((float)Math.toRadians(i)), 0.0D, 2 * Math.cos((float)Math.toRadians(i)));
				}
	        }
        }
    }

    protected void onDeathAIUpdate() 
    {
    	if(getAnimation() != ANIMATION_DEATH)
    		AnimationHandler.INSTANCE.sendAnimationMessage(this, ANIMATION_DEATH);
    }
	
    public float getDarknessAlpha()
    {
    	return alpha;
    }
    
    public int getDeathTicks()
    {
    	return deathTicks;
    }

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) 
	{
    	ProjectileGrenadeEntity entityProjectileGrenade = new ProjectileGrenadeEntity(this.world);
        double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;
        double d1 = target.posX - this.posX;
        double d2 = d0 - entityProjectileGrenade.posY;
        double d3 = target.posZ - this.posZ;
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        entityProjectileGrenade.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entityProjectileGrenade);
	}

	@Override
	public void setSwingingArms(boolean swingingArms)
    {
    }
}
