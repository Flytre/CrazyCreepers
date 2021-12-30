package net.flytre.ccrp.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

public class TNTCreeper extends CreeperEntity {
    private int exploding;

    public TNTCreeper(EntityType<? extends TNTCreeper> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        if (exploding > 0) {
            this.currentFuseTime = 1;
            this.setInvulnerable(true);
            double theta = (24 - exploding) * 15;
            double radius = (theta/360.0)*0.4 + 0.8;
            double x = Math.cos(theta) * radius;
            double y = 0.5 + radius/0.3;
            double z = Math.sin(theta) * radius;
            TntEntity tntEntity = new TntEntity(world,getX(),getY(),getZ(),this);
            tntEntity.setVelocity(x,y,z);
            tntEntity.setFuse(180);
            world.spawnEntity(tntEntity);
            --exploding;

            if(this.exploding == 1)
                super.explode();
        }

        super.tick();
    }


    @Override
    public void explode() {
        this.exploding = 24;
        this.setInvulnerable(true);
    }
}
