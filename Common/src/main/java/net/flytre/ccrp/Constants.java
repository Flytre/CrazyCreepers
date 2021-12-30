package net.flytre.ccrp;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.flytre.ccrp.config.Config;
import net.flytre.flytre_lib.api.config.ConfigHandler;
import net.flytre.flytre_lib.api.config.GsonHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class Constants {
    public static final String MOD_ID = "ccrp";
    public static final String MOD_NAME = "Crazy Creepers";
    public static final Logger LOG = LogManager.getLogger(MOD_NAME);
    public static final ConfigHandler<Config> CONFIG;

    static {
        GsonBuilder builder = GsonHelper.GSON_BUILDER;
        builder.enableComplexMapKeySerialization();
        builder.registerTypeAdapter(new TypeToken<HashMap<EntityType<?>, Config.CreeperConfig>>() {
                }.getType(),
                new GsonHelper.MapDeserializer<EntityType<?>, Config.CreeperConfig>(Registry.ENTITY_TYPE::get));
        CONFIG = new ConfigHandler<>(new Config(), "crazy_creepers", builder.create());
    }

    public static void main(String[] args) {
    }
}