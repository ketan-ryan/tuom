package library.ai;

import library.entities.mobs.entities.LibEntityCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class LibEntityAICreeperSwell extends EntityAIBase {
    /**
     * The creeper that is swelling.
     */
    LibEntityCreeper swellingCreeper;
    /**
     * The creeper's attack target. This is used for the changing of the creeper's state.
     */
    EntityLivingBase creeperAttackTarget;

    public LibEntityAICreeperSwell(LibEntityCreeper entitycreeperIn) {
        this.swellingCreeper = entitycreeperIn;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.swellingCreeper.getAttackTarget();
        return swellingCreeper.getCreeperState() > 0 || entitylivingbase != null && this.swellingCreeper.getDistanceSq(entitylivingbase) < 9.0D;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.swellingCreeper.getNavigator().clearPath();
        this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.creeperAttackTarget = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask() {
        if (this.creeperAttackTarget == null) {
            swellingCreeper.setCreeperState(-1);
        } else if (this.swellingCreeper.getDistanceSq(this.creeperAttackTarget) > 49.0D) {
            swellingCreeper.setCreeperState(-1);
        } else if (!this.swellingCreeper.getEntitySenses().canSee(this.creeperAttackTarget)) {
            swellingCreeper.setCreeperState(-1);
        } else {
            swellingCreeper.setCreeperState(1);
        }
    }
}