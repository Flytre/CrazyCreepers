package net.flytre.ccrp;

import net.flytre.ccrp.entity.CustomCreeperRenderer;
import net.flytre.ccrp.entity.GiantCreeperRenderer;
import net.flytre.flytre_lib.loader.LoaderProperties;
import net.minecraft.util.Identifier;

public class ClientRegistry {

    public static void init() {
        LoaderProperties.register(Registry.LAVA_CREEPER,
                (ctx) -> new CustomCreeperRenderer(ctx, new Identifier("ccrp:textures/lava_creeper.png")));
        LoaderProperties.register(Registry.DIAMOND_CREEPER,
                (ctx) -> new CustomCreeperRenderer(ctx, new Identifier("ccrp:textures/diamond_creeper.png")));
        LoaderProperties.register(Registry.GOLDEN_CREEPER,
                (ctx) -> new CustomCreeperRenderer(ctx, new Identifier("ccrp:textures/golden_creeper.png")));
        LoaderProperties.register(Registry.IRON_CREEPER,
                (ctx) -> new CustomCreeperRenderer(ctx, new Identifier("ccrp:textures/iron_creeper.png")));
        LoaderProperties.register(Registry.LIGHTNING_CREEPER,
                (ctx) -> new CustomCreeperRenderer(ctx, new Identifier("ccrp:textures/lightning_creeper.png")));
        LoaderProperties.register(Registry.TELEPORT_CREEPER,
                (ctx) -> new CustomCreeperRenderer(ctx, new Identifier("ccrp:textures/teleport_creeper.png")));
        LoaderProperties.register(Registry.WATER_CREEPER,
                (ctx) -> new CustomCreeperRenderer(ctx, new Identifier("ccrp:textures/water_creeper.png")));
        LoaderProperties.register(Registry.GIANT_CREEPER,
                (ctx) -> new GiantCreeperRenderer(ctx,32.0f));
        LoaderProperties.register(Registry.TNT_CREEPER,
                (ctx) -> new CustomCreeperRenderer(ctx, new Identifier("ccrp:textures/tnt_creeper.png")));
        LoaderProperties.register(Registry.ENCHANTING_CREEPER,
                (ctx) -> new CustomCreeperRenderer(ctx, new Identifier("ccrp:textures/enchanting_creeper.png")));
    }
}
