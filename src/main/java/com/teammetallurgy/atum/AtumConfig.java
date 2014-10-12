package com.teammetallurgy.atum;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class AtumConfig {

    public static Configuration config;

    public static String general = "general settings";
    public static boolean ALLOW_CREATION;
    public static boolean FOG_ENABLED;

    public static int DIMENSION_ID;
    public static int BIOME_DESERT_ID;

    public AtumConfig(File file) {
        this.config = new Configuration(file);

        FMLCommonHandler.instance().bus().register(this);
        syncConfigData();
    }

    @SubscribeEvent
    public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent event) {

        if (event.modID.equals(Atum.MODID))
            syncConfigData();
    }

    private void syncConfigData() {

        ALLOW_CREATION = config.get(general, "Can a non-creative player create a portal?", true).getBoolean(true);
        FOG_ENABLED = config.get(general, "Should the client show fog?", true).getBoolean(true);

        DIMENSION_ID = config.get("ID", "The dimension id of Atum", 17).getInt();
        BIOME_DESERT_ID = config.get("ID", "The biomeID of Desert for Atum", 200).getInt();

        this.config.save();
    }

}
