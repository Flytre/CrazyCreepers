package net.flytre.ccrp.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class IronCreeper extends CreeperEntity {
    public IronCreeper(EntityType<? extends IronCreeper> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void explode() {
        super.explode();
        for(int i = 0; i < 10; i++)
            DiamondCreeper.spawn(world,this.getX(),this.getY(),this.getZ(), new ItemStack(Items.IRON_INGOT,1));
    }
}
