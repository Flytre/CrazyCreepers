package net.flytre.ccrp.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.List;

public class GoldenCreeper extends CreeperEntity {
    public GoldenCreeper(EntityType<? extends GoldenCreeper> entityType, World world) {
        super(entityType, world);
    }


    @Override
    public void explode() {
        drawParticles(this,ParticleTypes.HAPPY_VILLAGER);
        if (!this.world.isClient) {
            List<PlayerEntity> entities = this.world.getEntitiesByClass(PlayerEntity.class, this.getBoundingBox().expand(5), entity -> true);
            for(PlayerEntity p : entities) {
                p.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,600));
            }
            this.dead = true;
            this.discard();
            this.spawnEffectsCloud();
        }
        if (this.world.isClient) {
            this.world.playSound(getX(), getY(), getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F, false);
        }
    }

    static void drawParticles(Entity e, ParticleEffect p) {
        for(int i = 0; i < 8; i++) {
            double startingTheta = 360.0/i;
            for(double j = startingTheta; j < startingTheta + 180; j += 5) {
                double percent = j/180.0;
                double radius = percent * 5;
                e.world.addParticle(p,e.getX() + Math.sin(j) * radius,
                        e.getY() + 1, e.getZ() + Math.cos(j) * radius,0,0,0);
            }
        }
    }
}
