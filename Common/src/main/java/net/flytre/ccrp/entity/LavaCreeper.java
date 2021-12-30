package net.flytre.ccrp.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class LavaCreeper extends CreeperEntity {
    public LavaCreeper(EntityType<? extends LavaCreeper> entityType, World world) {
        super(entityType, world);
    }


    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void explode() {
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                WaterCreeper.trySetBlock(this, Blocks.LAVA.getDefaultState(), i, 0, j);
            }
        for(Entity x : world.getEntitiesByClass(LivingEntity.class,this.getBoundingBox().expand(12),i -> true)) {
            drawLineTo(this,x, ParticleTypes.FLAME);
            world.playSound(x.getX(),x.getY(),x.getZ(), SoundEvents.ENTITY_BLAZE_BURN, SoundCategory.HOSTILE,1.0f,1.0f,true);
            x.setFireTicks(x.getFireTicks() + 40);
        }
        super.explode();
    }

    public static void drawLineTo(Entity a, Entity b, ParticleEffect particle) {
        for (double i = 0; i < 1; i += 0.01) {
            double x = a.getX() + (b.getX() - a.getX()) * i;
            double y = a.getY() + 1 + (b.getY() - a.getY()) * i;
            double z = a.getZ() + 1 + (b.getZ() - a.getZ()) * i;
            a.world.addParticle(particle, x, y, z, 0, 0, 0);
        }
    }

}
