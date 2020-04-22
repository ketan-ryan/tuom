package library.ai;

import library.entities.mobs.entities.LibEntityOcelot;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LibEntityAIOcelotSit extends EntityAIMoveToBlock {
    private final LibEntityOcelot ocelot;

    public LibEntityAIOcelotSit(LibEntityOcelot ocelotIn, double p_i45315_2_) {
        super(ocelotIn, p_i45315_2_, 8);
        this.ocelot = ocelotIn;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        return this.ocelot.isTamed() && !this.ocelot.isSitting() && super.shouldExecute();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        super.startExecuting();
        this.ocelot.getAISit().setSitting(false);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    @Override
    public void resetTask() {
        super.resetTask();
        this.ocelot.setSitting(false);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void updateTask() {
        super.updateTask();
        this.ocelot.getAISit().setSitting(false);

        if (!this.getIsAboveDestination()) {
            this.ocelot.setSitting(false);
        } else if (!this.ocelot.isSitting()) {
            this.ocelot.setSitting(true);
        }
    }

    /**
     * Return true to set given position as destination
     */
    @Override
    protected boolean shouldMoveTo(World worldIn, BlockPos pos) {
        if (!worldIn.isAirBlock(pos.up())) {
            return false;
        } else {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (block == Blocks.CHEST) {
                TileEntity tileentity = worldIn.getTileEntity(pos);

                if (tileentity instanceof TileEntityChest && ((TileEntityChest) tileentity).numPlayersUsing < 1) {
                    return true;
                }
            } else {
                if (block == Blocks.LIT_FURNACE) {
                    return true;
                }

                if (block == Blocks.BED && iblockstate.getValue(BlockBed.PART) != BlockBed.EnumPartType.HEAD) {
                    return true;
                }
            }

            return false;
        }
    }
}