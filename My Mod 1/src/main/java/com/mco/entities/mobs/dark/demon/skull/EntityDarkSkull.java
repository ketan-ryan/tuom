package com.mco.entities.mobs.dark.demon.skull;

import com.mco.TUOM;
import com.mco.main.TUOMDamageSources;
import library.util.Actions;
import com.mco.TUOM;
import com.mco.main.TUOMDamageSources;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDarkSkull extends EntityFireball
{

	public EntityDarkSkull(World worldIn) 
	{
		super(worldIn);
	}

    public EntityDarkSkull(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ)
    {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }
	
    public static void registerFixesWitherSkull(DataFixer fixer)
    {
        EntityFireball.registerFixesFireball(fixer, "DarkSkull");
    }
    
    @SideOnly(Side.CLIENT)
    public EntityDarkSkull(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ)
    {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }
    
    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }
    
    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    public boolean isBurning()
    {
        return false;
    }
    
	@Override
	protected void onImpact(RayTraceResult result) 
	{
		if (!this.world.isRemote)
		{
			if (result.entityHit != null)
			{
				if (result.entityHit.attackEntityFrom(TUOMDamageSources.darkSkull, 15.0F) && !result.entityHit.isEntityAlive())
				{
					if(shootingEntity != null)
					{
						this.shootingEntity.heal(10.0F);
					}
					result.entityHit.attackEntityFrom(DamageSource.MAGIC, 5.0F);
					Actions.addPotionEffect((EntityLivingBase) result.entityHit, TUOM.darkPotion, 200, 5, true);
				}
			}
			else
			{
			}

			if (result.entityHit instanceof EntityLivingBase)
			{
				//	((EntityLivingBase)result.entityHit).addPotionEffect(new PotionEffect(ClientProxy.darkPotion.id, 40, 1));
			}
	        this.world.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, false, net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.shootingEntity));

		}

		this.setDead();
	}

}
