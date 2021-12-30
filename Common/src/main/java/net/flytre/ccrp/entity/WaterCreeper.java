package net.flytre.ccrp.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WaterCreeper extends CreeperEntity {
    public WaterCreeper(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void explode() {
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                trySetBlock(this, Blocks.WATER.getDefaultState(), i, 0, j);
            }
        for(Entity x : world.getEntitiesByClass(LivingEntity.class,this.getBoundingBox().expand(12),i -> true)) {
            LavaCreeper.drawLineTo(this,x, ParticleTypes.DRIPPING_WATER);
            world.playSound(x.getX(),x.getY(),x.getZ(), SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.HOSTILE,1.0f,1.0f,true);
            x.setFireTicks(0);
        }
        super.explode();
    }

    public static void trySetBlock(LivingEntity entity, BlockState state, int dx, int dy, int dz) {
        BlockPos pos = entity.getBlockPos();
        BlockPos blockPos = new BlockPos(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
        if(entity.world.canSetBlock(blockPos)) {
            entity.world.setBlockState(blockPos, state);
        }
    }
}
