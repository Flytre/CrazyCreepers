package net.flytre.ccrp.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.Random;

public class DiamondCreeper extends CreeperEntity {
    private static final Random RANDOM = new Random();
    private int exploding;


    public DiamondCreeper(EntityType<? extends DiamondCreeper> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        if (exploding > 0) {
            this.currentFuseTime = 1;
            spawn(world,this.getX(),this.getY(),this.getZ(), new ItemStack(Items.DIAMOND,1));
            world.playSound(getX(),getY(),getZ(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.HOSTILE,1.0f,1.0f,true);
            if (this.exploding == 1)
                super.explode();
            this.exploding -= 1;
        }

        super.tick();
    }

    @Override
    public void explode() {
        this.exploding = 17;
        this.setInvulnerable(true);
    }

    static void spawn(World world, double x, double y, double z, ItemStack item) {
        double d = EntityType.ITEM.getWidth();
        double e = 1.0D - d;
        double f = d / 2.0D;
        double g = Math.floor(x) + RANDOM.nextDouble() * e + f;
        double h = Math.floor(y) + RANDOM.nextDouble() * e;
        double i = Math.floor(z) + RANDOM.nextDouble() * e + f;

        while(!item.isEmpty()) {
            ItemEntity itemEntity = new ItemEntity(world, g, h, i, item.split(RANDOM.nextInt(21) + 10));
            itemEntity.setVelocity(RANDOM.nextGaussian() * 0.4000000074505806D, RANDOM.nextGaussian() * 0.4000000074505806D + 0.80000000298023224D, RANDOM.nextGaussian() * 0.4000000074505806D);
            world.spawnEntity(itemEntity);
        }

    }
}
