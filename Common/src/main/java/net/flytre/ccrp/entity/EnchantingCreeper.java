package net.flytre.ccrp.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnchantingCreeper extends CreeperEntity {

    private static final Random r = new Random();


    public EnchantingCreeper(EntityType<? extends EnchantingCreeper> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void explode() {
        GoldenCreeper.drawParticles(this, ParticleTypes.ENCHANT);
        if (!this.world.isClient) {
            List<PlayerEntity> entities = this.world.getEntitiesByClass(PlayerEntity.class, this.getBoundingBox().expand(5), entity -> true);
            for(PlayerEntity p : entities) {
                ArrayList<ItemStack> items = new ArrayList<>(p.getInventory().armor);
                items.add(p.getInventory().offHand.get(0));
                items.add(p.getInventory().getMainHandStack());
                for(ItemStack stack : items) {
                    if(EnchantmentHelper.get(stack).size() > 0)
                        continue;
                    EnchantmentHelper.enchant(r, stack, 15 + r.nextInt(15), true);
                }
            }
            this.dead = true;
            this.discard();
            this.spawnEffectsCloud();
        }
        if (this.world.isClient) {
            this.world.playSound(getX(), getY(), getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F, false);
        }
    }
}
