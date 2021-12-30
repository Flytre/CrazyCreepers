package net.flytre.ccrp;

import net.flytre.ccrp.entity.*;
import net.flytre.flytre_lib.api.base.registry.EntityAttributeRegistry;
import net.flytre.flytre_lib.api.config.ConfigRegistry;
import net.flytre.flytre_lib.impl.loader.LoaderPropertyInitializer;
import net.flytre.flytre_lib.loader.LoaderProperties;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;

public class Registry {

    public static final EntityType<LavaCreeper> LAVA_CREEPER = register("lava_creeper", LavaCreeper::new);
    public static final EntityType<DiamondCreeper> DIAMOND_CREEPER = register("diamond_creeper", DiamondCreeper::new);
    public static final EntityType<GoldenCreeper> GOLDEN_CREEPER = register("golden_creeper", GoldenCreeper::new);
    public static final EntityType<IronCreeper> IRON_CREEPER = register("iron_creeper", IronCreeper::new);
    public static final EntityType<LightningCreeper> LIGHTNING_CREEPER = register("lightning_creeper", LightningCreeper::new);
    public static final EntityType<TeleportCreeper> TELEPORT_CREEPER = register("teleport_creeper", TeleportCreeper::new);
    public static final EntityType<GiantCreeper> GIANT_CREEPER = register("giant_creeper", GiantCreeper::new, 64, 32.0f);
    public static final EntityType<WaterCreeper> WATER_CREEPER = register("water_creeper", WaterCreeper::new);
    public static final EntityType<TNTCreeper> TNT_CREEPER = register("tnt_creeper", TNTCreeper::new);
    public static final EntityType<EnchantingCreeper> ENCHANTING_CREEPER = register("enchanting_creeper", EnchantingCreeper::new);


    public static final SpawnEggItem LAVA_CREEPER_SPAWN_EGG = register(new SpawnEggItem(LAVA_CREEPER, 445920, 0, new Item.Settings().group(ItemGroup.MISC)), "lava_creeper_spawn_egg");
    public static final SpawnEggItem DIAMOND_CREEPER_SPAWN_EGG = register(new SpawnEggItem(DIAMOND_CREEPER, 445920, 0, new Item.Settings().group(ItemGroup.MISC)), "diamond_creeper_spawn_egg");
    public static final SpawnEggItem GOLDEN_CREEPER_SPAWN_EGG = register(new SpawnEggItem(GOLDEN_CREEPER, 10394112, 0, new Item.Settings().group(ItemGroup.MISC)), "golden_creeper_spawn_egg");
    public static final SpawnEggItem IRON_CREEPER_SPAWN_EGG = register(new SpawnEggItem(IRON_CREEPER, 12895428, 0, new Item.Settings().group(ItemGroup.MISC)), "iron_creeper_spawn_egg");
    public static final SpawnEggItem LIGHTNING_CREEPER_SPAWN_EGG = register(new SpawnEggItem(LIGHTNING_CREEPER, 16449300, 0, new Item.Settings().group(ItemGroup.MISC)), "lightning_creeper_spawn_egg");
    public static final SpawnEggItem TELEPORT_CREEPER_SPAWN_EGG = register(new SpawnEggItem(TELEPORT_CREEPER, 13178111, 0, new Item.Settings().group(ItemGroup.MISC)), "teleport_creeper_spawn_egg");
    public static final SpawnEggItem WATER_CREEPER_SPAWN_EGG = register(new SpawnEggItem(WATER_CREEPER, 655819, 0, new Item.Settings().group(ItemGroup.MISC)), "water_creeper_spawn_egg");
    public static final SpawnEggItem GIANT_CREEPER_SPAWN_EGG = register(new SpawnEggItem(GIANT_CREEPER, 60223, 0, new Item.Settings().group(ItemGroup.MISC)), "giant_creeper_spawn_egg");
    public static final SpawnEggItem TNT_CREEPER_SPAWN_EGG = register(new SpawnEggItem(TNT_CREEPER, 13107200, 0, new Item.Settings().group(ItemGroup.MISC)), "tnt_creeper_spawn_egg");
    public static final SpawnEggItem ENCHANTING_CREEPER_SPAWN_EGG = register(new SpawnEggItem(ENCHANTING_CREEPER, 15728818, 0, new Item.Settings().group(ItemGroup.MISC)), "enchanting_creeper_spawn_egg");


    static {
        LoaderProperties.register(LAVA_CREEPER, CreeperEntity::createCreeperAttributes);
        LoaderProperties.register(DIAMOND_CREEPER, CreeperEntity::createCreeperAttributes);
        LoaderProperties.register(GOLDEN_CREEPER, CreeperEntity::createCreeperAttributes);
        LoaderProperties.register(IRON_CREEPER, CreeperEntity::createCreeperAttributes);
        LoaderProperties.register(LIGHTNING_CREEPER, CreeperEntity::createCreeperAttributes);
        LoaderProperties.register(TELEPORT_CREEPER, CreeperEntity::createCreeperAttributes);
        LoaderProperties.register(WATER_CREEPER, CreeperEntity::createCreeperAttributes);
        LoaderProperties.register(GIANT_CREEPER, GiantCreeper::createCreeperAttributes);
        LoaderProperties.register(TNT_CREEPER, CreeperEntity::createCreeperAttributes);
        LoaderProperties.register(ENCHANTING_CREEPER, CreeperEntity::createCreeperAttributes);
    }

    private static <T extends Item> T register(T item, String id) {
        return LoaderProperties.register(item, "ccrp", id);
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType.EntityFactory<T> factory) {
        return register(id, factory, 8, 1);
    }

    public static <E extends Entity> EntityType<E> register(String id, EntityType.EntityFactory<E> factory, int trackingRange, float scale) {
        return LoaderProperties.register(
                EntityType.Builder.create(factory, SpawnGroup.MONSTER)
                        .setDimensions(0.6F * scale, 1.7F * scale)
                        .maxTrackingRange(trackingRange)
                        .build(id), "ccrp", id);
    }

    public static void init() {
        ConfigRegistry.registerServerConfig(Constants.CONFIG);
    }


}
