package net.flytre.ccrp.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GiantCreeper extends CreeperEntity {
    private final float scale;

    public GiantCreeper(EntityType<? extends GiantCreeper> entityType, World world) {
        super(entityType, world);
        this.scale = 32;
        this.explosionRadius = (int) (scale * 5.0);
        this.fuseTime = 60;
    }

    public static DefaultAttributeContainer.Builder createCreeperAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 45.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE,24.0D);
    }

    @Override
    protected int computeFallDamage(float fallDistance, float damageMultiplier) {
        StatusEffectInstance statusEffectInstance = this.getStatusEffect(StatusEffects.JUMP_BOOST);
        float f = statusEffectInstance == null ? 0.0F : (float)(statusEffectInstance.getAmplifier() + 1);
        return MathHelper.ceil((fallDistance - 24.0F - f) * damageMultiplier);
    }

    @Override
    public int getSafeFallDistance() {
        return this.getTarget() == null ? 24 : 24 + (int)(this.getHealth() - 1.0F);
    }


    @Override
    public void explode() {
        super.explode();
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new GiantCreeperIgniteGoal(this,(this.scale / 2) * (this.scale / 2)));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, OcelotEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 18.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this));
    }
}
