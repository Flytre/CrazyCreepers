package net.flytre.ccrp.mixin;

import net.flytre.ccrp.Constants;
import net.flytre.ccrp.Registry;
import net.flytre.ccrp.config.Config;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {

    private static final Set<EntityType<?>> CREEPERS;

    static {
        CREEPERS = Set.of(
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
    }

    @Inject(method = "addMonsters", at = @At(value = "HEAD"))
    private static void addCreepers(SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight, boolean drowned, CallbackInfo ci) {


        for (EntityType<?> type : CREEPERS) {
            Config.CreeperConfig config = getConfig(type);
            if (config.shouldSpawnNaturally) {
                builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(type, config.spawnWeight, config.minGroupSize, config.maxGroupSize));
            }
        }
    }


    private static boolean spawns(EntityType<?> type) {
        return getConfig(type).shouldSpawnNaturally;
    }

    private static Config.CreeperConfig getConfig(EntityType<?> type) {

        if (Constants.CONFIG.getConfig() == null)
            Constants.CONFIG.handle();

        return Constants.CONFIG.getConfig().spawnDataMap.get(type);
    }
}
