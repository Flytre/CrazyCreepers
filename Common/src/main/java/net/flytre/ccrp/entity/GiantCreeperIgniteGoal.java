package net.flytre.ccrp.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.predicate.entity.EntityPredicates;

import java.util.EnumSet;

public class GiantCreeperIgniteGoal extends Goal {
    private final CreeperEntity creeper;
    private LivingEntity target;
    private final double squareDis;

    public GiantCreeperIgniteGoal(CreeperEntity creeper, double squareDis) {
        this.creeper = creeper;
        this.setControls(EnumSet.of(Control.MOVE));
        this.squareDis = squareDis;
    }

    public boolean canStart() {
        LivingEntity livingEntity = this.creeper.getTarget();

        if (!EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(livingEntity)) {
            return false;
        }

            return this.creeper.getFuseSpeed() > 0 || livingEntity != null && this.creeper.squaredDistanceTo(livingEntity) < squareDis;
    }

    public void start() {
        this.creeper.getNavigation().stop();
        this.target = this.creeper.getTarget();
    }

    public void stop() {
        this.target = null;
    }

    public void tick() {
        if (this.target == null) {
            this.creeper.setFuseSpeed(-1);
        } else if (this.creeper.squaredDistanceTo(this.target) > 49.0D) {
            this.creeper.setFuseSpeed(-1);
        } else if (!this.creeper.getVisibilityCache().canSee(this.target)) {
            this.creeper.setFuseSpeed(-1);
        } else {
            this.creeper.setFuseSpeed(1);
        }
    }
}
