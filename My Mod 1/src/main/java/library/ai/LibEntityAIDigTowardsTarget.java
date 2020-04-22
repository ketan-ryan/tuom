package library.ai;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * Based off of TaskDigTowardsTarget from CoroUtil
 */
public class LibEntityAIDigTowardsTarget extends EntityAIBase
{
    private EntityCreature entity = null;
	private IBlockState stateCurMining = null;
    private BlockPos posCurMining = null;
    private EntityLivingBase targetLastTracked = null;
    private int digTimeCur = 0;
    private int digTimeMax = 15*20;
    private double curBlockDamage = 0D;
    //doesnt factor in ai tick delay of % 3
    private int noMoveTicks = 0;

    private int maxHardnessDiggable = 0;
    private double digSpeed = 0.01D;

	public static String dataUseInvasionRules = "HW_Inv_UseInvasionRules";
	public static String dataUsePlayerList = "HW_Inv_UsePlayerList";
	public static String dataWhitelistMode = "HW_Inv_WhitelistMode";
	public static String dataListPlayers = "HW_Inv_ListPlayers";

	public SoundEvent breakBlockSound = null;

    public LibEntityAIDigTowardsTarget(EntityCreature creatureIn, int maxHardnessDiggable, double digSpeed, SoundEvent breakBlockSound)
    {
        this.entity = creatureIn;
        this.maxHardnessDiggable = maxHardnessDiggable;
        this.digSpeed = digSpeed;
        this.breakBlockSound = breakBlockSound;
		this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
    	//this method ticks every 3 ticks in best conditions
		boolean forInvasion = entity.getEntityData().getBoolean(dataUseInvasionRules);
    	
    	//prevent day digging, easy way to prevent digging once invasion ends
		if (forInvasion) {
			if (entity.world.isDaytime()) return false;
		}
    	
    	//System.out.println("should?");
    	/**
    	 * Zombies wouldnt try to mine if they are bunched up behind others, as they are still technically pathfinding, this helps resolve that issue, and maybe water related issues
    	 */
    	double movementThreshold = 0.05D;
    	int noMoveThreshold = 5;
    	if (posCurMining == null && entity.motionX < movementThreshold && entity.motionX > -movementThreshold && 
    			entity.motionZ < movementThreshold && entity.motionZ > -movementThreshold) {
    		
    		noMoveTicks++;
    		
    	} else {
    		noMoveTicks = 0;
    	}
    	
    	//System.out.println("noMoveTicks: " + noMoveTicks);
    	/*if (noMoveTicks > noMoveThreshold) {
    		System.out.println("ent not moving enough, try to mine!? " + noMoveTicks + " ent: " + entity.getEntityId());
    	}*/
    	
    	if (!entity.onGround && !entity.isInWater()) return false;
    	//return true if not pathing, has target
    	if (entity.getAttackTarget() != null || targetLastTracked != null) {
    		if (entity.getAttackTarget() == null) {
    			//System.out.println("forcing reset of target2");
    			entity.setAttackTarget(targetLastTracked);
				//fix for scenario where setAttackTarget calls forge event and someone undoes target setting
				if (entity.getAttackTarget() == null || entity.getAttackTarget().getHealth() <= 0) {
					return false;
				}
    		} else {
    			//was breaking other detarget logic, this task would just reforce attack even if this task isnt always being used
    			//targetLastTracked = entity.getAttackTarget();
    		}

    		//prevent invasion spawned diggers to not dig for players with invasions off
			if (entity.getEntityData().getBoolean(dataUsePlayerList)) {
				String playerName = entity.getAttackTarget() != null ? entity.getAttackTarget().getName() : "";
				boolean whitelistMode = entity.getEntityData().getBoolean(dataWhitelistMode);
				String listPlayers = entity.getEntityData().getString(dataListPlayers);

				if (whitelistMode) {
					if (!listPlayers.contains(playerName)) {
						return false;
					}
				} else {
					if (listPlayers.contains(playerName)) {
						return false;
					}
				}
			}

            //if (!entity.getNavigator().noPath()) System.out.println("path size: " + entity.getNavigator().getPath().getCurrentPathLength());
            if (entity.getNavigator().noPath() || entity.getNavigator().getPath().getCurrentPathLength() == 1 || noMoveTicks > noMoveThreshold) {
                if (!canPathToTarget()) {
                    //if (entity.motionX < 0.1D && entity.motionZ < 0.1D) {
                    if (updateBlockToMine()) {
                        //System.out.println("should mine!");
                        return true;
                    }
                }
            } else {
                //clause for if stuck trying to path

            }
    	}
    	
        return false;
    }

    public boolean canPathToTarget() {
        EntityLivingBase target = entity.getAttackTarget();
        if (target == null) target = targetLastTracked;
        if (target != null) {

            Path entityPathEntity = this.entity.getNavigator().getPathToEntityLiving(target);

            if (entityPathEntity != null)
            {
                if (entityPathEntity.getCurrentPathLength() == 1) {
                    return false;
                } else {
                    return true;
                }
            }
            else
            {
                return this.getAttackReachSqr(target) >= this.entity.getDistanceSq(target.posX, target.getEntityBoundingBox().minY, target.posZ);
            }
        } else {
            return false;
        }

    }

    protected double getAttackReachSqr(EntityLivingBase attackTarget)
    {
        return (double)(this.entity.width * 2.0F * this.entity.width * 2.0F + attackTarget.width);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting()
    {
    	//System.out.println("continue!");
    	if (posCurMining == null) return false;
        BlockPos pos = new BlockPos(posCurMining.getX(), posCurMining.getY(), posCurMining.getZ());
        IBlockState state = entity.world.getBlockState(pos);
    	if (!entity.world.isAirBlock(pos)) {
    		return true;
    	} else {
			setMiningBlock(null, null);
    		//System.out.println("ending execute");
    		return false;
    	}
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
    	//System.out.println("start!");
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask()
    {
    	//System.out.println("reset!");
    	digTimeCur = 0;
    	curBlockDamage = 0;
		setMiningBlock(null, null);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask()
    {
    	//System.out.println("running!");
    	
    	if (entity.getAttackTarget() != null) {
    		targetLastTracked = entity.getAttackTarget();
    	} else {
    		if (targetLastTracked != null) {
    			//System.out.println("forcing reset of target");
    			entity.setAttackTarget(targetLastTracked);
    		}
    	}
    	
    	tickMineBlock();
    }
    
    public boolean updateBlockToMine() {

		//fix for scenario where setAttackTarget calls forge event and someone undoes target setting
		if (entity.getAttackTarget() == null) {
			return false;
		}

		setMiningBlock(null, null);
    	
    	double vecX = entity.getAttackTarget().posX - entity.posX;
    	//feet
    	double vecY = entity.getAttackTarget().posY - entity.getEntityBoundingBox().minY;
    	double vecZ = entity.getAttackTarget().posZ - entity.posZ;
    	
    	double dist = (double) MathHelper.sqrt(vecX * vecX/* + vecY * vecY*/ + vecZ * vecZ);

    	double scanVecX = (vecX / dist);
		double scanVecZ = (vecZ / dist);
    	
    	double scanX = entity.posX + scanVecX;
    	double scanZ = entity.posZ + scanVecZ;
    	
    	BlockPos coords = new BlockPos(MathHelper.floor(scanX), MathHelper.floor(entity.getEntityBoundingBox().minY + 1), MathHelper.floor(scanZ));
    	
    	IBlockState state = entity.world.getBlockState(coords);
    	Block block = state.getBlock();
    	//Block block = entity.world.getBlock(coords.posX, coords.posY, coords.posZ);
    	
    	//System.out.println("ahead to target: " + block);
    	
    	if (canMineBlock(entity.world, coords, block)) {
			setMiningBlock(state, coords);
    		//entity.world.setBlock(coords.posX, coords.posY, coords.posZ, Blocks.air);
    		return true;
    	} else {
    		if (vecY > 0) {
    			//coords.posY++;
    			coords = coords.add(0, 1, 0);
    			state = entity.world.getBlockState(coords);
    			//fix for digging up stairs missing one above itself
				if (!canMineBlock(entity.world, coords, block)) {
					coords = new BlockPos(MathHelper.floor(entity.posX), MathHelper.floor(entity.getEntityBoundingBox().minY + 2), MathHelper.floor(entity.posZ));
					state = entity.world.getBlockState(coords);
				}
    	    	//block = entity.world.getBlock(coords.posX, coords.posY, coords.posZ);
        		if (canMineBlock(entity.world, coords, block)) {
					setMiningBlock(state, coords);
            		return true;
        		}
    		}
    		
    		//if dont or cant dig up, continue strait
    		//coords.posY--;
    		coords = coords.add(0, -1, 0);

    		state = entity.world.getBlockState(coords);
	    	block = state.getBlock();
    		if (canMineBlock(entity.world, coords, block)) {
				setMiningBlock(state, coords);
        		return true;
    		} else {
    			//try to dig down if all else failed and target is below
    			if (vecY < 0) {
    				coords = coords.add(0, -1, 0);
    	    		state = entity.world.getBlockState(coords);
    	    		block = state.getBlock();
    	    		//block = entity.world.getBlock(coords.posX, coords.posY, coords.posZ);
					//dig strait down if other options fail
					if (!canMineBlock(entity.world, coords, block)) {
						coords = new BlockPos(MathHelper.floor(entity.posX), MathHelper.floor(entity.getEntityBoundingBox().minY - 1), MathHelper.floor(entity.posZ));
						state = entity.world.getBlockState(coords);
					}
    		    	
    	    		if (canMineBlock(entity.world, coords, block)) {
						setMiningBlock(state, coords);
    	        		return true;
    	    		}
    			}
    		}
    		
    		return false;
    	}
    }

    public void setMiningBlock(IBlockState state, BlockPos pos) {
		this.posCurMining = pos;
		this.stateCurMining = state;
	}
    
    public void tickMineBlock() {
    	if (posCurMining == null) return;

		IBlockState state = entity.world.getBlockState(posCurMining);
		Block block = state.getBlock();
    	
    	//force stop mining if pushed away, or if block changed
    	if (stateCurMining != state || entity.getDistance(posCurMining.getX(), posCurMining.getY(), posCurMining.getZ()) > 4) {
    		//entity.world.destroyBlockInWorldPartially(entity.getEntityId(), posCurMining.posX, posCurMining.posY, posCurMining.posZ, 0);
    		entity.world.sendBlockBreakProgress(entity.getEntityId(), posCurMining, 0);
			setMiningBlock(null, null);
			curBlockDamage = 0;
    		return;
    	}
    	
    	entity.getNavigator().clearPath();
    	
    	//Block block = entity.world.getBlock(posCurMining.posX, posCurMining.posY, posCurMining.posZ);
    	//double blockStrength = block.getBlockHardness(entity.world, posCurMining.posX, posCurMining.posY, posCurMining.posZ);
    	//Block block = state.getBlock();

    	
    	double blockStrength = state.getBlockHardness(entity.world, posCurMining);
    	
    	if (blockStrength == -1) {
			setMiningBlock(null, null);
    		return;
    	}


		if (entity.world.getTotalWorldTime() % 10 == 0) {
			//entity.swingItem();
			entity.swingArm(EnumHand.MAIN_HAND);
			//System.out.println("swing!");

			entity.world.playSound(null, new BlockPos(posCurMining.getX(), posCurMining.getY(), posCurMining.getZ()), block.getSoundType(state, entity.world, posCurMining, entity).getBreakSound(), SoundCategory.HOSTILE, 0.5F, 1F);
			//entity.world.playSoundEffect(posCurMining.getX(), posCurMining.getY(), posCurMining.getZ(), block.stepSound.getBreakSound(), 0.5F, 1F);
		}
    	
    	curBlockDamage += digSpeed / blockStrength;
    	
    	if (curBlockDamage > 1D) {
    		//entity.world.destroyBlockInWorldPartially(entity.getEntityId(), posCurMining.posX, posCurMining.posY, posCurMining.posZ, 0);
    		entity.world.sendBlockBreakProgress(entity.getEntityId(), posCurMining, 0);
    		//entity.world.setBlock(posCurMining.posX, posCurMining.posY, posCurMining.posZ, Blocks.AIR);
    		//entity.world.setBlockToAir(posCurMining);
			Block.spawnAsEntity(entity.world, posCurMining, new ItemStack(state.getBlock(), 1));
			entity.world.setBlockToAir(posCurMining);
			curBlockDamage = 0;
			if (breakBlockSound != null) {
                entity.world.playSound(null, new BlockPos(posCurMining.getX(), posCurMining.getY(), posCurMining.getZ()), breakBlockSound, SoundCategory.HOSTILE, 0.5F, 1F);
            }

			setMiningBlock(null, null);
    		
    	} else {
    		//entity.world.destroyBlockInWorldPartially(entity.getEntityId(), posCurMining.posX, posCurMining.posY, posCurMining.posZ, (int)(curBlockDamage * 10D));
    		entity.world.sendBlockBreakProgress(entity.getEntityId(), posCurMining, (int)(curBlockDamage * 10D));
    	}
    }

	public boolean canMineBlock(World world, BlockPos pos, Block block) {

		IBlockState state = world.getBlockState(pos);

		//dont mine tile entities
		/*if (world.getTileEntity(pos) != null) {
			return false;
		}*/
		if (block.isAir(state, world, pos)) {
			return false;
		}
		if (state.getMaterial().isLiquid()) {
			return false;
		}
		if (state.getBlockHardness(world, pos) > maxHardnessDiggable) {
			return false;
		}

		return true;
	}
}
