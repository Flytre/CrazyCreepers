package net.flytre.ccrp.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class TeleportCreeper extends CreeperEntity {
    public TeleportCreeper(EntityType<? extends TeleportCreeper> entityType, World world) {
        super(entityType, world);
    }


    protected boolean teleportRandomly() {
        if (!this.world.isClient() && this.isAlive()) {
            double d = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
            double e = this.getY() + (double) (this.random.nextInt(64) - 32);
            double f = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
            return this.teleportTo(d, e, f);
        } else {
            return false;
        }
    }

    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (source instanceof ProjectileDamageSource) {
            for (int i = 0; i < 64; ++i) {
                if (this.teleportRandomly()) {
                    return true;
                }
            }
        }
        return super.damage(source, amount);
    }

    protected void initGoals() {
        this.targetSelector.add(1, new TeleportTowardsPlayerGoal(this, this::shouldAngerAt));
        super.initGoals();
    }

    public boolean shouldAngerAt(LivingEntity entity) {
        if (!EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(entity)) {
            return false;
        } else {
            return entity.getType() == EntityType.PLAYER && !((PlayerEntity) entity).getAbilities().invulnerable;
        }
    }

    private boolean teleportTo(Entity entity) {
        Vec3d vec3d = new Vec3d(this.getX() - entity.getX(), this.getBodyY(0.5D) - entity.getEyeY(), this.getZ() - entity.getZ());
        vec3d = vec3d.normalize();
        double d = 16.0D;
        double e = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
        double f = this.getY() + (double) (this.random.nextInt(16) - 8) - vec3d.y * 16.0D;
        double g = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
        return this.teleportTo(e, f, g);
    }

    private boolean teleportTo(double x, double y, double z) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);

        while (mutable.getY() > 0 && !this.world.getBlockState(mutable).getMaterial().blocksMovement()) {
            mutable.move(Direction.DOWN);
        }

        BlockState blockState = this.world.getBlockState(mutable);
        boolean bl = blockState.getMaterial().blocksMovement();
        boolean bl2 = blockState.getFluidState().isIn(FluidTags.WATER);
        if (bl && !bl2) {
            boolean bl3 = this.teleport(x, y, z, true);
            if (bl3 && !this.isSilent()) {
                this.world.playSound((PlayerEntity) null, this.prevX, this.prevY, this.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
                this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }

            return bl3;
        } else {
            return false;
        }
    }


    static class TeleportTowardsPlayerGoal extends ActiveTargetGoal<PlayerEntity> {
        private final TeleportCreeper teleportCreeper;
        private final TargetPredicate validTargetPredicate = TargetPredicate.createAttackable();
        private PlayerEntity targetPlayer;
        private double cooldown;

        public TeleportTowardsPlayerGoal(TeleportCreeper enderman, @Nullable Predicate<LivingEntity> predicate) {
            super(enderman, PlayerEntity.class, 10, false, false, predicate);
            this.teleportCreeper = enderman;
            this.cooldown = 20;
        }

        public boolean canStart() {
            this.targetPlayer = teleportCreeper.world.getClosestPlayer(teleportCreeper.getX(), teleportCreeper.getY(), teleportCreeper.getZ(), 16.0D, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR);
            return this.targetPlayer != null && validTargetPredicate.test(teleportCreeper, targetPlayer) && this.targetPlayer.squaredDistanceTo(this.teleportCreeper) > 35.0D;
        }

        public void start() {
        }

        public void stop() {
            this.targetPlayer = null;
            super.stop();
        }

        public boolean shouldContinue() {
            if (this.targetPlayer != null) {
                return true;
            } else {
                return this.targetEntity != null && this.validTargetPredicate.test(this.teleportCreeper, this.targetEntity) || super.shouldContinue();
            }
        }

        public void tick() {
            this.cooldown--;
            if (this.targetEntity instanceof PlayerEntity)
                this.targetPlayer = (PlayerEntity) this.targetEntity;

            if (this.targetPlayer != null && this.targetPlayer.squaredDistanceTo(this.teleportCreeper) > 35.0D && this.cooldown <= 0) {
                this.teleportCreeper.teleportTo(targetPlayer);
                this.stop();
            }

        }

    }
}

