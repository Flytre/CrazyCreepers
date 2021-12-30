package net.flytre.ccrp;

import net.flytre.ccrp.config.Config;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Set;

public class ForgeEventBus {


    public ForgeEventBus() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private static Config.CreeperConfig getConfig(EntityType<?> type) {

        if (Constants.CONFIG.getConfig() == null)
            Constants.CONFIG.handle();

        return Constants.CONFIG.getConfig().spawnDataMap.get(type);
    }

    @SubscribeEvent
    public void registerMobs(BiomeLoadingEvent event) {
        final Set<EntityType<?>> creepers = Set.of(
                Registry.ENCHANTING_CREEPER,
                Registry.LAVA_CREEPER,
                Registry.TNT_CREEPER,
                Registry.GIANT_CREEPER,
                Registry.DIAMOND_CREEPER,
                Registry.GOLDEN_CREEPER,
                Registry.IRON_CREEPER,
                Registry.LIGHTNING_CREEPER,
                Registry.TELEPORT_CREEPER,
                Registry.WATER_CREEPER
        );
        final Set<Biome.Category> categories = Set.of(
                Biome.Category.NONE,
                Biome.Category.TAIGA,
                Biome.Category.EXTREME_HILLS,
                Biome.Category.JUNGLE,
                Biome.Category.MESA,
                Biome.Category.PLAINS,
                Biome.Category.SAVANNA,
                Biome.Category.ICY,
                Biome.Category.BEACH,
                Biome.Category.FOREST,
                Biome.Category.DESERT,
                Biome.Category.SWAMP,
                Biome.Category.MUSHROOM,
                Biome.Category.UNDERGROUND,
                Biome.Category.MOUNTAIN
        );

        if (categories.contains(event.getCategory()))
            for (EntityType<?> type : creepers) {
                Config.CreeperConfig config = getConfig(type);
                if (config.shouldSpawnNaturally) {
                    event.getSpawns().spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(type, config.spawnWeight, config.minGroupSize, config.maxGroupSize));
                }
            }

    }

}
