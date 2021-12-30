package net.flytre.ccrp.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

public class LightningCreeper extends CreeperEntity {
    public LightningCreeper(EntityType<? extends LightningCreeper> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void explode() {
        super.explode();

        for(int i = 0; i < Math.random()*4 + 2; i++) {
            LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT,world);
            double off = Math.random()*14 - 7;
            double off2 = Math.random()*14 - 7;
            double off3 = Math.random()*14 - 7;
            lightningEntity.setPos(getX() + off, getY() + off2, getZ() + off3);
            world.spawnEntity(lightningEntity);
        }
    }
}
