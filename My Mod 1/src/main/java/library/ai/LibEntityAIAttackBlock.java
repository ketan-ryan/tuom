package library.ai;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

/**
 * Based off of TaskDigTowardsTarget from CoroUtil
 */
public class LibEntityAIAttackBlock extends EntityAIBase
{
    private EntityCreature entity = null;
    private IBlockState state = null;
    private int scanRange;

    private BlockPos pos = null;

    public LibEntityAIAttackBlock(EntityCreature creatureIn, IBlockState block, int scanRange)
    {
        this.entity = creatureIn;
        this.state = block;
        this.scanRange = scanRange;
		this.setMutexBits(3);
    }

	public BlockPos findBlock() {
    	double distCl = 999;
    	BlockPos posCl = null;
		for (int x = -scanRange; x < scanRange; x++) {
			for (int y = -scanRange; y < scanRange; y++) {
				for (int z = -scanRange; z < scanRange; z++) {
					BlockPos pos = new BlockPos(entity.getPosition().add(x, y, z));
					IBlockState state = entity.world.getBlockState(pos);
					if (state.getBlock() == this.state.getBlock()) {
						//return pos;
						double dist = Math.sqrt(entity.getDistanceSq(pos));
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
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {

    	if (entity.world.getTotalWorldTime() % 10 == 0) {
			pos = findBlock();
			if (pos != null) {
				return true;
			}
		}
		return false;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
    	return pos != null && this.entity.world.getBlockState(pos) == state;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
    	//System.out.println("start!");
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
    	pos = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
    	double dist = Math.sqrt(entity.getDistanceSq(pos));
    	if (dist > 3) {
    		if (!entity.hasPath()) {
				entity.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1D);
			}
		} else {
    		if (state != null && pos != null) {
				entity.world.setBlockToAir(pos);
				entity.swingArm(EnumHand.MAIN_HAND);
				entity.world.playSound(null, new BlockPos(pos.getX(), pos.getY(), pos.getZ()), state.getBlock().getSoundType(state, entity.world, pos, entity).getBreakSound(), SoundCategory.HOSTILE, 0.5F, 1F);
				pos = null;
				entity.getNavigator().clearPath();
			}
		}
    }
}
