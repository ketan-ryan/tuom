package library.util;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import library.entities.LibEntityEffect;
import library.entities.mobs.entities.LibEntityZombie;
import library.particle.ParticleBehavior;
import library.particle.ParticleEmitterCustom;
import library.particle.ParticleShape;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEmitter;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Actions {

    /**
     * Launch a projectile towards an entity
     *
     * @param entitySource
     * @param target
     * @param projectile
     * @param speed
     */
	public static void launchProjectile(Entity entitySource, Entity target, Class projectile, double speed) {
		launchProjectileRelative(entitySource, target, projectile, speed, 0, 0, 0);
	}

    /**
     * Launch a projectile in the same direction the entity is looking
     *
     * @param entitySource
     * @param projectile
     * @param speed
     */
	public static void launchProjectileInDirection(Entity entitySource, Class projectile, double speed) {
		launchProjectileInDirectionRelative(entitySource, projectile, speed, 0, 0, 0);
	}

	/**
	 * Creates and launches a projectile of the provided class at the given target
	 * from the given source at the given speed, with extra initial position options relative to entity aim
	 *
	 * @param entitySource
	 * @param target
	 * @param projectile
	 * @param speed
	 */
	public static void launchProjectileRelative(Entity entitySource, Entity target, Class projectile,
												double speed, double relativeX, double relativeY, double relativeZ) {
		if (entitySource.world.isRemote || !(entitySource instanceof EntityLivingBase) || !(target instanceof EntityLivingBase))
			return;

		double relZAdjX = -Math.sin(Math.toRadians(entitySource.rotationYaw - 0F) - (float)Math.PI) * relativeZ;
		double relZAdjZ = Math.cos(Math.toRadians(entitySource.rotationYaw - 0F) - (float)Math.PI) * relativeZ;
		double relXAdjX = -Math.sin(Math.toRadians(entitySource.rotationYaw - 90F) - (float)Math.PI) * relativeX;
		double relXAdjZ = Math.cos(Math.toRadians(entitySource.rotationYaw - 90F) - (float)Math.PI) * relativeX;

		relativeX = relZAdjX + relXAdjX;
		relativeZ = relZAdjZ + relXAdjZ;

		//Vec3d aimWithSpeed = UtilMath.getAim(entitySource, target, speed);
		Vec3d aimWithSpeed = UtilMath.getAim(new Vec3d(entitySource.posX + relativeX, 0.5D + entitySource.posY + (double)(entitySource.height / 2.0F) + relativeY, entitySource.posZ + relativeZ),
				new Vec3d(target.posX, target.getEntityBoundingBox().minY + (double)(target.height / 2.0F), target.posZ), speed);

		/*Vec3d position = UtilMath.getAim(new Vec3d(entitySource.posX + relativeX, 0.5D + entitySource.posY + (double)(entitySource.height / 2.0F) + relativeY, entitySource.posZ + relativeZ),
				new Vec3d(target.posX, target.getEntityBoundingBox().minY + (double)(target.height / 2.0F), target.posZ), 1D);*/
		Vec3d position = new Vec3d(entitySource.posX + relativeX, entitySource.posY + relativeY + (double)(entitySource.height / 2.0F) + 0.5D, entitySource.posZ + relativeZ);
		//Vec3d position = UtilMath.getPosition(entitySource, target, 1D);

		Entity ent = null;
		try {
			if (IProjectile.class.isAssignableFrom(projectile)) {
				Constructor constructor = projectile.getConstructor(World.class);
				IProjectile prj = (IProjectile) constructor.newInstance(entitySource.world);
				prj.shoot(aimWithSpeed.x, aimWithSpeed.y, aimWithSpeed.z, (float) speed, 0);
				ent = (Entity) prj;
			} else if (EntityFireball.class.isAssignableFrom(projectile)) {
				Constructor constructor = projectile.getConstructor(World.class);
				EntityFireball prj = (EntityFireball) constructor.newInstance(entitySource.world);
				prj.accelerationX = aimWithSpeed.x;
				prj.accelerationY = aimWithSpeed.y;
				prj.accelerationZ = aimWithSpeed.z;
				ent = (Entity) prj;
			}

			// custom hacks
			if (ent instanceof EntityArrow) {
				ReflectionHelper.setPrivateValue(EntityArrow.class, (EntityArrow) ent, entitySource, "shootingEntity",
						"field_70250_c");
			} else if (ent instanceof EntityThrowable) {
				ReflectionHelper.setPrivateValue(EntityThrowable.class, (EntityThrowable) ent, entitySource, "thrower",
						"field_70192_c");
			} else if (ent instanceof EntityFireball) {
				((EntityFireball) ent).shootingEntity = (EntityLivingBase)entitySource;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (ent != null) {
			ent.setPosition(position.x, position.y, position.z);
			entitySource.world.spawnEntity(ent);
		}

	}

    /**
     * Launch a projectile in the same direction the entity is looking, with extra initial position options relative to entity aim
     *
     * @param entitySource
     * @param projectile
     * @param speed
     * @param relativeX
     * @param relativeY
     * @param relativeZ
     */
	public static void launchProjectileInDirectionRelative(Entity entitySource, Class projectile, double speed
			, double relativeX, double relativeY, double relativeZ) {
		if (entitySource.world.isRemote || !(entitySource instanceof EntityLivingBase))
			return;

		// Vec3d position = UtilMath.getPosition(entitySource, target, 1D);

		double f = -MathHelper.sin(entitySource.rotationYaw * 0.017453292F)
				* MathHelper.cos(entitySource.rotationPitch * 0.017453292F);
		double f1 = -MathHelper.sin(entitySource.rotationPitch * 0.017453292F);
		double f2 = MathHelper.cos(entitySource.rotationYaw * 0.017453292F)
				* MathHelper.cos(entitySource.rotationPitch * 0.017453292F);

		double relZAdjX = -Math.sin(Math.toRadians(entitySource.rotationYaw - 0F) - (float)Math.PI) * relativeZ;
		double relZAdjZ = Math.cos(Math.toRadians(entitySource.rotationYaw - 0F) - (float)Math.PI) * relativeZ;
		double relXAdjX = -Math.sin(Math.toRadians(entitySource.rotationYaw - 90F) - (float)Math.PI) * relativeX;
		double relXAdjZ = Math.cos(Math.toRadians(entitySource.rotationYaw - 90F) - (float)Math.PI) * relativeX;

		relativeX = relZAdjX + relXAdjX;
		relativeZ = relZAdjZ + relXAdjZ;

		Vec3d aim = new Vec3d(f, f1, f2);
		Vec3d aimWithSpeed = new Vec3d(f * speed, f1 * speed, f2 * speed);
        //add shooter momentum
        aimWithSpeed = aimWithSpeed.addVector(entitySource.motionX, entitySource.motionY, entitySource.motionZ);

        // use relative position method here
        double dist = 3D;
        Vec3d position = new Vec3d(entitySource.posX + (aim.x * dist) + relativeX, entitySource.posY + 2D + (aim.y * dist) + relativeY,
                entitySource.posZ + (aim.z * dist) + relativeZ);

        //fix fast moving vehicle colliding with projectiles issue
        position = position.addVector(entitySource.motionX, entitySource.motionY, entitySource.motionZ);
		// this.setThrowableHeading((double)f, (double)f1, (double)f2, velocity,
		// inaccuracy);

		Entity ent = null;
		try {
			if (IProjectile.class.isAssignableFrom(projectile)) {
				Constructor constructor = projectile.getConstructor(World.class);
				IProjectile prj = (IProjectile) constructor.newInstance(entitySource.world);
				prj.shoot(aimWithSpeed.x, aimWithSpeed.y, aimWithSpeed.z, (float) speed, 0);
				ent = (Entity) prj;
			} else if (EntityFireball.class.isAssignableFrom(projectile)) {
				Constructor constructor = projectile.getConstructor(World.class);
				EntityFireball prj = (EntityFireball) constructor.newInstance(entitySource.world);
				prj.accelerationX = aimWithSpeed.x;
				prj.accelerationY = aimWithSpeed.y;
				prj.accelerationZ = aimWithSpeed.z;
				ent = (Entity) prj;
			}

			// custom hacks
			if (ent instanceof EntityArrow) {
				ReflectionHelper.setPrivateValue(EntityArrow.class, (EntityArrow) ent, entitySource, "shootingEntity",
						"field_70250_c");
			} else if (ent instanceof EntityThrowable) {
				ReflectionHelper.setPrivateValue(EntityThrowable.class, (EntityThrowable) ent, entitySource, "thrower",
						"field_70192_c");
			} else if (ent instanceof EntityFireball) {
				((EntityFireball) ent).shootingEntity = (EntityLivingBase)entitySource;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (ent != null) {
			ent.setPosition(position.x, position.y, position.z);
			entitySource.world.spawnEntity(ent);
		}
	}

	/**
	 * Gets a list of entities within range that extend the provided class,
	 * excluding the source entity itself
	 *
	 * @param entitySource
	 * @param targetClass
	 * @param minRange
	 * @param maxRange
	 * @return
	 */
	public static <T extends EntityLivingBase> List<T> getEntitiesInRange(Entity entitySource,
																		  Class<? extends T> targetClass, double minRange, double maxRange) {
		return entitySource.world.getEntities(targetClass, withinRangeExcludingSelf(entitySource.posX,
				entitySource.posY, entitySource.posZ, minRange, maxRange, entitySource));
	}

	/**
	 * Returns a random entity within range of the type
	 *
	 * @param entitySource
	 * @param targetClass
	 * @param minRange
	 * @param maxRange
	 * @return
	 */
	public static <T extends EntityLivingBase> T getEntityInRange(Entity entitySource, Class<? extends T> targetClass,
																  double minRange, double maxRange) {
		List<T> entities = entitySource.world.getEntities(targetClass, withinRangeExcludingSelf(entitySource.posX,
				entitySource.posY, entitySource.posZ, minRange, maxRange, entitySource));
		if (entities.size() == 0)
			return null;
		return entities.get(entitySource.world.rand.nextInt(entities.size()));
	}

	private static <T extends Entity> Predicate<T> withinRangeExcludingSelf(final double x, final double y,
																			final double z, double minRange, double maxRange, Entity entitySource) {
		final double minRange1 = minRange * minRange;
		final double maxRange1 = maxRange * maxRange;
		return new Predicate<T>() {
			public boolean apply(@Nullable T p_apply_1_) {
				return p_apply_1_ != null && p_apply_1_.getDistanceSq(x, y, z) >= minRange1
						&& p_apply_1_.getDistanceSq(x, y, z) <= maxRange1 && !p_apply_1_.equals(entitySource);
			}
		};
	}

	/**
	 * Return a raytrace result of the given distance and look angle of the entity
	 * provided
	 *
	 * @param entity
	 * @param blockReachDistance
	 * @return
	 */
	public static RayTraceResult getPlayerLookVec(Entity entity, double blockReachDistance) {
		Vec3d vec3d = entity.getPositionEyes(1F);
		Vec3d vec3d1 = entity.getLook(1F);
		Vec3d vec3d2 = vec3d.addVector(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance,
				vec3d1.z * blockReachDistance);
		RayTraceResult rayTrace = entity.world.rayTraceBlocks(vec3d, vec3d2, false, false, true);
		return getPlayerLookVecWithEntities(entity, rayTrace, blockReachDistance);
	}

	private static RayTraceResult getPlayerLookVecWithEntities(Entity entity, RayTraceResult objectMouseOver,
															   double d0) {
		World world = entity.world;
		Entity pointedEntity = null;
		// double d0 = (double)playerController.getBlockReachDistance();
		// objectMouseOver = entity.rayTrace(d0, partialTicks);
		Vec3d vec3d = entity.getPositionEyes(1F);
		boolean flag = false;
		int i = 3;
		double d1 = d0;

		if (d0 > 3.0D) {
			flag = true;
		}

		if (objectMouseOver != null) {
			d1 = objectMouseOver.hitVec.distanceTo(vec3d);
		}

		Vec3d vec3d1 = entity.getLook(1.0F);
		Vec3d vec3d2 = vec3d.addVector(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
		pointedEntity = null;
		Vec3d vec3d3 = null;
		double accuracyScanSize = d0;
		List<Entity> list = world.getEntitiesInAABBexcluding(entity,
				entity.getEntityBoundingBox().expand(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).grow(accuracyScanSize,
						accuracyScanSize, accuracyScanSize),
				Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>() {
					public boolean apply(@Nullable Entity p_apply_1_) {
						return p_apply_1_ != null && p_apply_1_.canBeCollidedWith();
					}
				}));
		double d2 = d1;

		for (int j = 0; j < list.size(); ++j) {
			Entity entity1 = list.get(j);
			AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox()
					.grow((double) entity1.getCollisionBorderSize());
			RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);

			if (axisalignedbb.contains(vec3d)) {
				if (d2 >= 0.0D) {
					pointedEntity = entity1;
					vec3d3 = raytraceresult == null ? vec3d : raytraceresult.hitVec;
					d2 = 0.0D;
				}
			} else if (raytraceresult != null) {
				double d3 = vec3d.distanceTo(raytraceresult.hitVec);

				if (d3 < d2 || d2 == 0.0D) {
					if (entity1.getLowestRidingEntity() == entity.getLowestRidingEntity()
							&& !entity1.canRiderInteract()) {
						if (d2 == 0.0D) {
							pointedEntity = entity1;
							vec3d3 = raytraceresult.hitVec;
						}
					} else {
						pointedEntity = entity1;
						vec3d3 = raytraceresult.hitVec;
						d2 = d3;
					}
				}
			}
		}

		/*
		 * if (pointedEntity != null && flag && vec3d.distanceTo(vec3d3) > 3.0D) {
		 * pointedEntity = null; objectMouseOver = new
		 * RayTraceResult(RayTraceResult.Type.MISS, vec3d3, (EnumFacing)null, new
		 * BlockPos(vec3d3)); }
		 */

		if (pointedEntity != null && (d2 < d1 || objectMouseOver == null)) {
			objectMouseOver = new RayTraceResult(pointedEntity, vec3d3);

			/*
			 * if (pointedEntity instanceof EntityLivingBase || pointedEntity instanceof
			 * EntityItemFrame) { pointedEntity = pointedEntity; }
			 */
		}

		return objectMouseOver;
	}

	/**
	 * Summons lightning at the given block position, creating fire on the ground in
	 * a circle of the given radius
	 *
	 * @param world
	 * @param position
	 * @param radiusOfFire
	 */
	public static void summonLightningAtPosition(World world, BlockPos position, int radiusOfFire) {
		if (world.isRemote)
			return;
		EntityLightningBolt lightning = new EntityLightningBolt(world, position.getX(), position.getY(),
				position.getZ(), false);
		world.addWeatherEffect(lightning);
		for (int x = -radiusOfFire; x < radiusOfFire; x++) {
			for (int z = -radiusOfFire; z < radiusOfFire; z++) {
				BlockPos pos = new BlockPos(MathHelper.floor(position.getX()) + x, 0,
						MathHelper.floor(position.getZ()) + z);
				int y = world.getHeight(pos.getX(), pos.getZ());
				pos = new BlockPos(pos.getX(), y, pos.getZ());
				if (pos.getDistance(MathHelper.floor(position.getX()), MathHelper.floor(position.getY()),
						MathHelper.floor(position.getZ())) <= radiusOfFire) {
					world.setBlockState(pos, Blocks.FIRE.getDefaultState());
				}
			}
		}
	}

	/**
	 * Summons lightning at the given entity position, creating fire on the ground
	 * in a circle of the given radius
	 *
	 * @param entity
	 * @param radiusOfFire
	 */
	public static void summonLightningAtEntity(Entity entity, int radiusOfFire) {
		summonLightningAtPosition(entity.world, entity.getPosition(), radiusOfFire);
	}

	/**
	 * Set fire to an entity for the given amount of seconds
	 *
	 * @param entity
	 * @param seconds
	 */
	public static void setFire(Entity entity, int seconds) {
		if (entity.world.isRemote)
			return;
		entity.setFire(seconds * 20);
	}

	/**
	 * Spawn a particle at the given entity, spreading the given amount over a
	 * circular area of the given size
	 *
	 * @param entity
	 * @param particleType
	 * @param amount
	 * @param areaSize
	 */
	public static void spawnParticleAtEntity(Entity entity, EnumParticleTypes particleType, int amount,
											 float areaSize) {
		if (!entity.world.isRemote) return;
		spawnParticleAtPositionImpl(entity.world, new Vec3d(entity.posX, entity.posY + 1, entity.posZ), particleType, amount, areaSize);
	}

	/**
	 * Spawn a particle at the given position, spreading the given amount over a
	 * circular area of the given size
	 *
	 * @param world
	 * @param position
	 * @param particleType
	 * @param amount
	 * @param areaSize
	 */
	public static void spawnParticleAtPosition(World world, BlockPos position, EnumParticleTypes particleType,
											   int amount, float areaSize) {
		if (!world.isRemote)
			return;
		spawnParticleAtPositionImpl(world, new Vec3d(position.getX() + 0.5F, position.getY() + 0.5F, position.getZ() + 0.5F), particleType, amount, areaSize);
	}

	private static void spawnParticleAtPositionImpl(World world, Vec3d position, EnumParticleTypes particleType,
													int amount, float areaSize) {
		if (!world.isRemote)
			return;
		for (int i = 0; i < amount; i++) {
			float areaX = (world.rand.nextFloat() - world.rand.nextFloat()) * areaSize;
			float areaY = (world.rand.nextFloat() - world.rand.nextFloat()) * areaSize;
			float areaZ = (world.rand.nextFloat() - world.rand.nextFloat()) * areaSize;
			world.spawnParticle(particleType, true, position.x + areaX, position.y + areaY,
					position.z + areaZ, 0, 0, 0);
		}
	}

    /**
     * Spawns a beam of particles from the first entity to the second, with an option to limit how far it extends from first entity
     *
     * @param entityFrom
     * @param entityTo
     * @param particleType
     * @param maxDistance
     */
	public static void spawnParticleBeamToEntity(Entity entityFrom, Entity entityTo, EnumParticleTypes particleType, double maxDistance) {
		if (!entityFrom.world.isRemote) return;
		spawnParticleBeamToPositionImpl(entityFrom.world, new Vec3d(entityFrom.posX, entityFrom.posY + 1, entityFrom.posZ),
				new Vec3d(entityTo.posX, entityTo.posY + (entityTo.height / 4F * 3F), entityTo.posZ),
				particleType, maxDistance);
	}

	/**
	 * Spawn a particle at the given position, spreading the given amount over a
	 * circular area of the given size
	 *
	 * @param world
	 * @param position
	 * @param particleType
	 * @param amount
	 * @param areaSize
	 */
	public static void spawnParticleBeamToPosition(Entity entityFrom, BlockPos positionTo, EnumParticleTypes particleType, double maxDistance) {
		if (!entityFrom.world.isRemote) return;
		spawnParticleBeamToPositionImpl(entityFrom.world, new Vec3d(entityFrom.posX, entityFrom.posY + 1, entityFrom.posZ),
				new Vec3d(positionTo.getX() + 0.5F, positionTo.getY() + 0.5F, positionTo.getZ() + 0.5F),
				particleType, maxDistance);
	}

	private static void spawnParticleBeamToPositionImpl(World world, Vec3d positionFrom, Vec3d positionTo, EnumParticleTypes particleType, double maxDistance) {
		if (!world.isRemote) return;

		Vec3d vec = UtilMath.getAim(positionFrom, positionTo, 1);

		double distToEnd = Math.min(UtilMath.getDistance(positionFrom, positionTo), maxDistance);
		double step = 0.5D;

		for (double curDist = 0; curDist < distToEnd; curDist += step) {
			double posX = positionFrom.x + vec.x * curDist;
			double posY = positionFrom.y + vec.y * curDist;
			double posZ = positionFrom.z + vec.z * curDist;
			world.spawnParticle(particleType, true, posX, posY, posZ, 0, 0, 0);
		}
	}

    /**
     * Spawn a burst of particles around the entity, with options for type, shape, behavior, radius and duration in ticks
     *
     * @param world
     * @param entity
     * @param particleType
     * @param shape
     * @param behavior
     * @param radius
     * @param ticks
     */
	public static void spawnParticleBurstAtEntity(World world, Entity entity, EnumParticleTypes particleType, ParticleShape shape, ParticleBehavior behavior, float radius, int ticks) {
		if (entity == null) return;
		spawnParticleBurst(world, new Vec3d(entity.posX, entity.posY + 1, entity.posZ), particleType, shape, behavior, radius, ticks);
	}

    /**
     * Spawn a burst of particles around a position, with options for type, shape, behavior, radius and duration in ticks
     *
     * @param world
     * @param position
     * @param particleType
     * @param shape
     * @param behavior
     * @param radius
     * @param ticks
     */
	public static void spawnParticleBurstAtPosition(World world, BlockPos position, EnumParticleTypes particleType, ParticleShape shape, ParticleBehavior behavior, float radius, int ticks) {
		spawnParticleBurst(world, new Vec3d(position.getX() + 0.5F, position.getY() + 0.5F, position.getZ() + 0.5F), particleType, shape, behavior, radius, ticks);
	}

	private static void spawnParticleBurst(World world, Vec3d positionTo, EnumParticleTypes particleType, ParticleShape shape, ParticleBehavior behavior, float radius, int ticks) {
		if (!world.isRemote) return;
		Queue<ParticleEmitter> list = ReflectionHelper.getPrivateValue(ParticleManager.class, Minecraft.getMinecraft().effectRenderer, "particleEmitters", "field_178933_d");
		Entity dummyEntity = new EntityItem(world);
		ParticleEmitterCustom emitter = new ParticleEmitterCustom(world, dummyEntity, particleType, shape, behavior, radius, 1, ticks);
		emitter.setPosition(positionTo.x, positionTo.y, positionTo.z);
		list.add(emitter);
	}

	/**
	 * Sets the block above the position to fire block Fire block can have a maximum
	 * duration of 15, each value roughly counts down every 1.5 seconds, not
	 * factoring in neighbor fire, rain, etc
	 *
	 * @param position
	 * @param duration
	 */
	public static void setBlockFire(World world, BlockPos position, int duration) {
		if (world.isRemote || position == null)
			return;
		world.setBlockState(position.add(0, 1, 0),
				Blocks.FIRE.getDefaultState().withProperty(BlockFire.AGE, 15 - (duration & 15)));
	}

	/**
	 * Sets the world time to the value provided
	 *
	 * @param world
	 * @param time
	 */
	public static void setTime(World world, int time) {
		world.setWorldTime(time);
	}

	/**
	 * Create an entity of the given class type at the given position
	 *
	 * @param world
	 * @param position
	 * @param entity
	 */
	public static void createEntity(World world, BlockPos position, Class<? extends Entity> entity) {
		if (world.isRemote)
			return;
		try {
			Constructor constructor = entity.getConstructor(World.class);
			Entity ent = (Entity) constructor.newInstance(world);
			ent.setPosition(position.getX() + 0.5, position.getY() + 1, position.getZ() + 0.5);
			if (ent instanceof EntityLiving) {
				((EntityLiving) ent).onInitialSpawn(world.getDifficultyForLocation(position), null);
			}
			world.spawnEntity(ent);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Create an explosion at a position of the given size, size also represense the
	 * damage it does
	 *
	 * @param world
	 * @param position
	 * @param size
	 */
	public static void createExplosion(World world, BlockPos position, float size) {
		if (world.isRemote)
			return;
		world.createExplosion(null, position.getX() + 0.5F, position.getY() + 0.5F, position.getZ() + 0.5F, size, true);
	}

	/**
	 * Set block state at the given position
	 *
	 * @param world
	 * @param position
	 * @param blockState
	 */
	public static void setBlockState(World world, BlockPos position, IBlockState blockState) {
		if (world.isRemote || position == null)
			return;
		world.setBlockState(position, blockState);
	}

	/**
	 * Set a circular radius of blocks at the given position of the given size
	 *
	 * @param world
	 * @param position
	 * @param blockState
	 * @param radius
	 * @param replaceAir
	 *            If true, replaces air blocks, if false, it does not
	 */
	public static void setBlockStates(World world, BlockPos position, IBlockState blockState, int radius,
									  boolean replaceAir) {

		if (world.isRemote || position == null)
			return;

		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {
					BlockPos pos = new BlockPos(position.getX() + x, position.getY() + y, position.getZ() + z);
					if (pos.getDistance(position.getX(), position.getY(), position.getZ()) <= radius) {
						if (replaceAir || (!world.isAirBlock(pos) && !world.getBlockState(pos).getBlock().isReplaceable(world, pos))) {
							world.setBlockState(pos, blockState);
						}
					}
				}
			}
		}
	}

	/**
	 * Heal entity by given amount
	 *
	 * @param entity
	 * @param halfHearts
	 */
	public static void addHealth(EntityLivingBase entity, float halfHearts) {
		entity.heal(halfHearts);
	}

	/**
	 * Give a player an item
	 *
	 * @param player
	 * @param item
	 * @param count
	 */
	public static void givePlayer(EntityPlayer player, Item item, int count) {
		if (player.world.isRemote)
			return;
		player.addItemStackToInventory(new ItemStack(item, count));
	}

	/**
	 * Searches for and takes a certain amount of blocks or items from the player if
	 * found
	 *
	 * @param player
	 * @param item
	 *            Block or Item
	 * @param count
	 */
	public static void takeFromPlayer(EntityPlayer player, Object item, int count) {
		if (player.world.isRemote)
			return;
		List<Object> items = new ArrayList<>();
		items.add(item);
		for (int i = 0; i < items.size(); i++) {

			Object itemToAdd = items.get(i);

			ItemStack newStack = null;

			if (itemToAdd instanceof net.minecraft.item.Item) {
				newStack = new ItemStack((net.minecraft.item.Item) itemToAdd, 1);
			} else if (itemToAdd instanceof net.minecraft.block.Block) {
				newStack = new ItemStack((net.minecraft.block.Block) itemToAdd, 1);
			}

			if (newStack != null) {

				boolean metaUsed = false;

				// Main inventory
				for (int ii = 0; ii < player.inventory.mainInventory.size(); ii++) {
					ItemStack playerSlotStack = player.inventory.mainInventory.get(ii);

					if (!playerSlotStack.isEmpty() && playerSlotStack.getItem() == newStack.getItem()
							&& (!metaUsed || playerSlotStack.getItemDamage() == newStack.getItemDamage())) {
						playerSlotStack.shrink(count--);
						if (count <= 0)
							return;
						// player.inventory.mainInventory.set(ii, ItemStack.EMPTY);
					}
				}

				// Armor
				for (int ii = 0; ii < player.inventory.armorInventory.size(); ii++) {
					ItemStack playerSlotStack = player.inventory.armorInventory.get(ii);

					if (!playerSlotStack.isEmpty() && playerSlotStack.getItem() == newStack.getItem()
							&& (!metaUsed || playerSlotStack.getItemDamage() == newStack.getItemDamage())) {
						playerSlotStack.shrink(count--);
						if (count <= 0)
							return;
					}
				}

				// Cursor
				if (!player.inventory.getItemStack().isEmpty()
						&& player.inventory.getItemStack().getItem() == newStack.getItem()
						&& player.inventory.getItemStack().getItemDamage() == newStack.getItemDamage()) {
					player.inventory.getItemStack().shrink(count--);
					if (count <= 0)
						return;
				}

				// Offhand inventory
				for (int ii = 0; ii < player.inventory.offHandInventory.size(); ii++) {
					ItemStack playerSlotStack = player.inventory.offHandInventory.get(ii);

					if (!playerSlotStack.isEmpty() && playerSlotStack.getItem() == newStack.getItem()
							&& (!metaUsed || playerSlotStack.getItemDamage() == newStack.getItemDamage())) {
						playerSlotStack.shrink(count--);
						if (count <= 0)
							return;
					}
				}
			}

		}
	}

	/**
	 * Send a message to player
	 *
	 * @param player
	 * @param message
	 */
	public static void chatAtPlayer(EntityPlayer player, String message) {
		if (player == null || player.world.isRemote)
			return;
		player.sendMessage(new TextComponentString(message));
	}

	/**
	 * Play a sound at the players location
	 *
	 * @param target
	 * @param soundEvent
	 * @param pitch
	 * @param volume
	 */
	public static void playSound(Entity target, SoundEvent soundEvent, float pitch, float volume) {
		playSound(target.world, target.getPosition(), soundEvent, pitch, volume);
	}

	/**
	 * Play a sound at the given location
	 *
	 * @param world
	 * @param position
	 * @param soundEvent
	 * @param pitch
	 * @param volume
	 */
	public static void playSound(World world, BlockPos position, SoundEvent soundEvent, float pitch, float volume) {
		if (world.isRemote)
			return;
		world.playSound(null, position.getX(), position.getY(), position.getZ(), soundEvent, SoundCategory.PLAYERS,
				volume, pitch);
	}

	/**
	 * Replaces the given entity with an instance of the new one of the given class
	 * type at the same position and rotation
	 *
	 * @param sourceEntity
	 * @param newEntity
	 */
	public static void replaceEntity(Entity sourceEntity, Class<? extends Entity> newEntity) {
		if (sourceEntity.world.isRemote)
			return;
		try {
			Constructor constructor = newEntity.getConstructor(World.class);
			Entity ent = (Entity) constructor.newInstance(sourceEntity.world);
			ent.setPositionAndRotation(sourceEntity.posX, sourceEntity.posY, sourceEntity.posZ,
					sourceEntity.rotationYaw, sourceEntity.rotationPitch);
			if (ent instanceof EntityLiving) {
				((EntityLiving) ent).onInitialSpawn(sourceEntity.world.getDifficultyForLocation(sourceEntity.getPosition()), null);
			}
			sourceEntity.world.spawnEntity(ent);
			sourceEntity.setDead();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Spawns an entity of the given class type at the given position
	 *
	 * @param world
	 * @param newEntity
	 * @param position
	 */
	public static void spawnEntity(World world, Class<? extends Entity> newEntity, BlockPos position) {
		if (world.isRemote)
			return;
		try {
			Constructor constructor = newEntity.getConstructor(World.class);
			Entity ent = (Entity) constructor.newInstance(world);
			ent.setPosition(position.getX() + 0.5, position.getY() + 0.5, position.getZ() + 0.5);
			world.spawnEntity(ent);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static Entity spawnEntityImpl(World world, Class<? extends Entity> newEntity, Vec3d position) {
		if (world.isRemote)
			return null;
		try {
			Constructor constructor = newEntity.getConstructor(World.class);
			Entity ent = (Entity) constructor.newInstance(world);
			ent.setPosition(position.x, position.y, position.z);
			world.spawnEntity(ent);
			return ent;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private static Entity spawnEntityImpl2(World world, Class<? extends Entity> newEntity, Vec3d position) {
		if (world.isRemote)
			return null;
		try {
			Constructor constructor = newEntity.getConstructor(World.class);
			Entity ent = (Entity) constructor.newInstance(world);
			ent.setPosition(position.x, position.y, position.z);
			return ent;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

    /**
     * Create an ender dragon like explosion at a position with color and duration options
     *
     * @param world
     * @param position
     * @param color
     * @param duration
     */
	public static void bossExplosionAtPosition(World world, BlockPos position, int color, int duration) {
		if (world.isRemote) return;
		Entity ent = Actions.spawnEntityImpl2(world, LibEntityEffect.class, new Vec3d(position.getX() + 0.5, position.getY() + 0.5, position.getZ() + 0.5));
		((LibEntityEffect)ent).color = color;
		((LibEntityEffect)ent).deathTicksMax = duration;
		world.spawnEntity(ent);
	}

    /**
     * Create an ender dragon like explosion at an entity with color and duration options
     *
     * @param world
     * @param entity
     * @param color
     * @param duration
     */
	public static void bossExplosionAtEntity(World world, Entity entity, int color, int duration) {
		if (world.isRemote) return;
		Entity ent = Actions.spawnEntityImpl2(world, LibEntityEffect.class, new Vec3d(entity.posX, entity.posY + (entity.height / 4F * 2F) - 1F, entity.posZ));
		((LibEntityEffect)ent).color = color;
		((LibEntityEffect)ent).deathTicksMax = duration;
		world.spawnEntity(ent);
	}

	/**
	 * Destroy the entity
	 *
	 * @param entity
	 */
	public static void destroyEntity(Entity entity) {
		if (entity.world.isRemote)
			return;
		entity.setDead();
	}

	/**
	 * Teleport the given entity to the target entity
	 *
	 * @param entity
	 * @param entityTarget
	 */
	public static void teleportEntityToEntity(EntityLivingBase entity, Entity entityTarget) {
		if (entity == null || entity.world.isRemote)
			return;
		if (entity.isRiding()) {
			entity.dismountRidingEntity();
		}

		entity.setPositionAndUpdate(entityTarget.posX, entityTarget.posY, entityTarget.posZ);
		entity.fallDistance = 0.0F;
	}

	/**
	 * Teleport the given entity to the given position
	 *
	 * @param entity
	 * @param position
	 */
	public static void teleportEntityToPosition(EntityLivingBase entity, BlockPos position) {
		if (entity.world.isRemote)
			return;
		if (entity.isRiding()) {
			entity.dismountRidingEntity();
		}

		entity.setPositionAndUpdate(position.getX(), position.getY(), position.getZ());
		entity.fallDistance = 0.0F;
	}

	/**
	 * Damage an entity by the given amount
	 *
	 * @param entityTarget
	 * @param damageAmount
	 */
	public static void damageEntity(Entity entityTarget, float damageAmount) {
		entityTarget.attackEntityFrom(DamageSource.GENERIC, damageAmount);
	}

	/**
	 * Give the player a potion effect for the given duration and amplifier
	 *
	 * @param player
	 * @param potion
	 * @param durationIn
	 * @param amplifierIn
	 * @param showParticles
	 *            Setting to false disabled particles around player, as well is the
	 *            in game overlay at the top right of your screen
	 */
	public static void addPotionEffect(EntityPlayer player, Potion potion, int durationIn, int amplifierIn,
									   boolean showParticles) {
		if (player.world.isRemote) return;
		player.addPotionEffect(new PotionEffect(potion, durationIn, amplifierIn, false, showParticles));
	}

	/**
	 * Give any Living Entity a potion effect for the given duration and amplifier
	 *
	 * @param player
	 * @param potion
	 * @param durationIn
	 * @param amplifierIn
	 * @param showParticles
	 *            Setting to false disabled particles around player, as well is the
	 *            in game overlay at the top right of your screen
	 */
	public static void addPotionEffect(EntityLivingBase entity, Potion potion, int durationIn, int amplifierIn,
									   boolean showParticles) {
		if (entity.world.isRemote) return;
		entity.addPotionEffect(new PotionEffect(potion, durationIn, amplifierIn, false, showParticles));
	}

	/**
	 * Swing entities arm
	 *
	 * @param entity
	 */
	public static void swingArm(EntityLivingBase entity) {
		entity.swingArm(EnumHand.MAIN_HAND);
	}

	/**
	 * Works best for monster based mobs, causes damage to target, attempts to use
	 * item in hand to buff damage, effects etc Attempts to swing arm during attack
	 *
	 * @param source
	 * @param target
	 */
	public static void attackEntity(EntityLivingBase source, EntityLivingBase target) {
		source.attackEntityAsMob(target);
		swingArm(source);
	}

	/**
	 * Gets the closest player to entity
	 *
	 * @param source
	 * @return
	 */
	public static EntityPlayer getClosestPlayer(Entity source) {
		double clDist = 9999;
		EntityPlayer clPlayer = null;
		for (EntityPlayer player : source.world.playerEntities) {
			double dist = source.getDistance(player);
			if (dist < clDist) {
				clDist = dist;
				clPlayer = player;
			}
		}
		return clPlayer;
	}

	/**
	 * Enables rain for the default amount of time
	 */
	public static void setRaining() {
		World world = DimensionManager.getWorld(0);
		// client side bail
		if (world == null)
			return;

		world.getWorldInfo().setRaining(true);
		world.getWorldInfo().setRainTime(world.rand.nextInt(12000) + 12000);
	}

	/**
	 * Enables rain and thunder for the default amount of time
	 */
	public static void setThundering() {
		World world = DimensionManager.getWorld(0);
		// client side bail
		if (world == null)
			return;

		world.getWorldInfo().setRaining(true);
		world.getWorldInfo().setRainTime(world.rand.nextInt(12000) + 12000);
		world.getWorldInfo().setThundering(true);
		world.getWorldInfo().setThunderTime(world.rand.nextInt(12000) + 3600);
	}

	/**
	 * Clears the weather of rain and thunder for the default amount of time
	 */
	public static void clearWeather() {
		World world = DimensionManager.getWorld(0);
		// client side bail
		if (world == null)
			return;

		world.getWorldInfo().setRaining(false);
		world.getWorldInfo().setRainTime(world.rand.nextInt(168000) + 12000);
		world.getWorldInfo().setThundering(false);
		world.getWorldInfo().setThunderTime(world.rand.nextInt(168000) + 12000);
	}

	/**
	 * Get a random position within the radius given
	 *
	 * @param pos
	 * @param radius
	 * @return
	 */
	public static BlockPos getRandomPosition(BlockPos pos, double radius) {
		Random rand = new Random();
		return new BlockPos(pos).add((rand.nextDouble() - rand.nextDouble()) * radius,
				(rand.nextDouble() - rand.nextDouble()) * radius, (rand.nextDouble() - rand.nextDouble()) * radius);
	}

	/**
	 * Tries to teleport randomly to a spot within the radius given, will try
	 * multiple times until it finds a spot
	 *
	 * @param entity
	 * @param radius
	 * @return
	 */
	public static boolean teleportRandomly(EntityLivingBase entity, double radius) {
		Random rand = new Random();
		int attempts = 20;
		int tries = 0;
		boolean teleported = false;
		while (!teleported && tries++ <= attempts) {
			double x = entity.posX + (rand.nextDouble() - rand.nextDouble()) * radius;
			double y = entity.posY + (rand.nextDouble() - rand.nextDouble()) * radius;
			double z = entity.posZ + (rand.nextDouble() - rand.nextDouble()) * radius;
			teleported = entity.attemptTeleport(x, y, z);
		}
		return teleported;
	}

	/**
	 * Spawns a new entity of the given type and mounts it onto entityRidden
	 *
	 * @param entityRidden
	 * @param entityRider
	 */
	public static void createAndSetRider(Entity entityRidden, Class<? extends Entity> entityRider) {



		if (entityRidden.world.isRemote)
			return;
		try {

			Constructor constructor = entityRider.getConstructor(World.class);
			Entity ent = (Entity) constructor.newInstance(entityRidden.world);

			ent.setLocationAndAngles(entityRidden.posX, entityRidden.posY, entityRidden.posZ, entityRidden.rotationYaw,
					0.0F);
			if (ent instanceof EntityLiving) {
				((EntityLiving) ent).onInitialSpawn(entityRidden.world.getDifficultyForLocation(entityRidden.getPosition()), null);
			}
			entityRidden.world.spawnEntity(ent);
			ent.startRiding(entityRidden);
			entityRidden.updatePassenger(ent);
			if (ent instanceof LibEntityZombie) {
				((LibEntityZombie) ent).setChild(false);
			} else if (ent instanceof EntityZombie) {
				((EntityZombie) ent).setChild(false);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

    /**
     * Mounts entityRider onto entityRidden
     *
     * @param entityRidden
     * @param entityRider
     */
	public static void setRider(Entity entityRidden, Entity entityRider) {
		entityRider.startRiding(entityRidden);
	}

	/**
	 * Gets the block at the given position
	 *
	 * @param world
	 * @param pos
	 * @return
	 */
	public static Block getBlock(World world, BlockPos pos) {
		return world.getBlockState(pos).getBlock();
	}


	/**
	 * Creates an Item at the Position
	 *
	 * @param world
	 * @param pos
	 * @param
	 * @return
	 */
	public static void createItem(World world, BlockPos pos, Item item, int quantity) {

		if (world.isRemote) return;

		ItemStack stack = new ItemStack(item, quantity);
		EntityItem entityItem = new EntityItem(world, pos.getX() + 0.5F, pos.getY() + 1.5F, pos.getZ() + 0.5F, stack);
		world.spawnEntity(entityItem);
	}


	/**
	 * Summons the Evoker Fang Spell between two entities
	 *
	 * @param target
	 * @param attacker
	 */
	public static void castVindicatorFangs(EntityLivingBase attacker, EntityLivingBase target) {

		if (target != null) {

			double d0 = Math.min(target.posY, attacker.posY);
			double d1 = Math.max(target.posY, attacker.posY) + 1.0D;
			float f = (float) MathHelper.atan2(target.posZ - attacker.posZ, target.posX - attacker.posX);

			if (attacker.getDistanceSq(target) < 9.0D) {
				for (int i = 0; i < 10; ++i) {
					float f1 = f + (float) i * (float) Math.PI * 0.4F;
					Actions.spawnFangs(attacker.posX + (double) MathHelper.cos(f1) * 1.5D,
							attacker.posZ + (double) MathHelper.sin(f1) * 1.5D, d0, d1, f1, 0, attacker);
				}

				for (int k = 0; k < 16; ++k) {
					float f2 = f + (float) k * (float) Math.PI * 2.0F / 8.0F + ((float) Math.PI * 2F / 5F);
					Actions.spawnFangs(attacker.posX + (double) MathHelper.cos(f2) * 2.5D,
							attacker.posZ + (double) MathHelper.sin(f2) * 2.5D, d0, d1, f2, 3, attacker);
				}
			} else {
				for (int l = 0; l < 32; ++l) {
					double d2 = 1.25D * (double) (l + 1);
					int j = 1 * l;
					Actions.spawnFangs(attacker.posX + (double) MathHelper.cos(f) * d2,
							attacker.posZ + (double) MathHelper.sin(f) * d2, d0, d1, f, j, attacker);
				}
			}
		}
	}

	private static void spawnFangs(double p_190876_1_, double p_190876_3_, double p_190876_5_, double p_190876_7_,
								   float p_190876_9_, int p_190876_10_, EntityLivingBase attacker) {
		BlockPos blockpos = new BlockPos(p_190876_1_, p_190876_7_, p_190876_3_);
		boolean flag = false;
		double d0 = 0.0D;

		while (true) {
			if (!attacker.world.isBlockNormalCube(blockpos, true) && attacker.world.isBlockNormalCube(blockpos.down(), true)) {
				if (!attacker.world.isAirBlock(blockpos)) {
					IBlockState iblockstate = attacker.world.getBlockState(blockpos);
					AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(attacker.world, blockpos);

					if (axisalignedbb != null) {
						d0 = axisalignedbb.maxY;
					}
				}

				flag = true;
				break;
			}

			blockpos = blockpos.down();

			if (blockpos.getY() < MathHelper.floor(p_190876_5_) - 1) {
				break;
			}
		}

		if (flag) {
			EntityEvokerFangs entityevokerfangs = new EntityEvokerFangs(attacker.world, p_190876_1_,
					(double) blockpos.getY() + d0, p_190876_3_, p_190876_9_, p_190876_10_, attacker);
			attacker.world.spawnEntity(entityevokerfangs);

			;
		}
	}

    /**
     * Swaps the held items for both entities to eachothers original item
     *
     * @param entity1
     * @param entity2
     */
	public static void swapHeldItems(EntityLivingBase entity1, EntityLivingBase entity2) {
		if (entity1.world.isRemote) return;
		ItemStack item1 = entity1.getHeldItemMainhand();
		ItemStack item2 = entity2.getHeldItemMainhand();

		entity1.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, item2.copy());
		entity2.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, item1.copy());
	}

    /**
     * Get the closest player to the position given
     *
     * @param world
     * @param pos
     * @return
     */
    public static EntityPlayer getAtPositionClosestPlayer(World world, BlockPos pos) {
	    return world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), -1, false);
    }

    /**
     * Get the position of the closest block given within range
     *
     * @param source
     * @param block
     * @param range
     * @return
     */
    public static BlockPos getBlockInRange(Entity source, Block block, int range) {

        double distCl = 999;
        BlockPos posCl = null;
        for (int x = -range; x < range; x++) {
            for (int y = -range; y < range; y++) {
                for (int z = -range; z < range; z++) {
                    BlockPos pos = new BlockPos(source.getPosition().add(x, y, z));
                    IBlockState state = source.world.getBlockState(pos);
                    if (state.getBlock() == block) {
                        //return pos;
                        double dist = Math.sqrt(source.getDistanceSq(pos));
                        if (dist < distCl) {
                            distCl = dist;
                            posCl = pos;
                        }
                    }
                }
            }
        }

        return posCl;
    }

    /**
     * Get a list of positions of the given block within a range
     *
     * @param source
     * @param block
     * @param range
     * @return
     */
    public static List<BlockPos> getBlocksInRange(Entity source, Block block, int range) {

        List<BlockPos> listPos = new ArrayList<>();

        for (int x = -range; x < range; x++) {
            for (int y = -range; y < range; y++) {
                for (int z = -range; z < range; z++) {
                    BlockPos pos = new BlockPos(source.getPosition().add(x, y, z));
                    IBlockState state = source.world.getBlockState(pos);
                    if (state.getBlock() == block) {
                        listPos.add(pos);
                    }
                }
            }
        }

        return listPos;
    }

}
