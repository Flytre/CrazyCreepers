package net.flytre.ccrp.config;


import com.google.gson.annotations.SerializedName;
import net.flytre.ccrp.Constants;
import net.flytre.ccrp.Registry;
import net.flytre.flytre_lib.api.config.ConfigEventAcceptor;
import net.flytre.flytre_lib.api.config.annotation.Description;
import net.minecraft.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class Config implements ConfigEventAcceptor {

    @Description("Edit this to tweak how and if each creeper tweaks. Requires game restart.")
    @SerializedName("spawn_data_map")
    public Map<EntityType<?>, CreeperConfig> spawnDataMap;

    public Config() {
        spawnDataMap = new HashMap<>();
    }

    @Override
    public void onReload() {
        spawnDataMap.remove(null);

        if(spawnDataMap.isEmpty()) {
            spawnDataMap.put(Registry.ENCHANTING_CREEPER, new CreeperConfig(true, 15, 2, 2));
            spawnDataMap.put(Registry.LAVA_CREEPER, new CreeperConfig(true, 15, 2, 2));
            spawnDataMap.put(Registry.TNT_CREEPER, new CreeperConfig(true, 10, 1, 2));
            spawnDataMap.put(Registry.GIANT_CREEPER, new CreeperConfig(true, 4, 1, 2));
            spawnDataMap.put(Registry.DIAMOND_CREEPER, new CreeperConfig(true, 15, 2, 2));
            spawnDataMap.put(Registry.GOLDEN_CREEPER, new CreeperConfig(true, 15, 2, 2));
            spawnDataMap.put(Registry.IRON_CREEPER, new CreeperConfig(true, 15, 2, 2));
            spawnDataMap.put(Registry.LIGHTNING_CREEPER, new CreeperConfig(true, 15, 2, 2));
            spawnDataMap.put(Registry.TELEPORT_CREEPER, new CreeperConfig(true, 15, 2, 2));
            spawnDataMap.put(Registry.WATER_CREEPER, new CreeperConfig(true, 15, 2, 2));
            Constants.CONFIG.save(this);
        }
    }

    public static class CreeperConfig {

        @SerializedName("spawns_naturally")
        public boolean shouldSpawnNaturally;

        @SerializedName("spawn_weight")
        public int spawnWeight;

        @SerializedName("min_spawn_group_size")
        public int minGroupSize;

        @SerializedName("max_spawn_group_size")
        public int maxGroupSize;

        public CreeperConfig(boolean shouldSpawnNaturally, int spawnWeight, int minGroupSize, int maxGroupSize) {
            this.shouldSpawnNaturally = shouldSpawnNaturally;
            this.spawnWeight = spawnWeight;
            this.minGroupSize = minGroupSize;
            this.maxGroupSize = maxGroupSize;
        }
    }

}
