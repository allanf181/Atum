package com.teammetallurgy.atum.handler;

import com.teammetallurgy.atum.utils.Constants;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

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

        if (event.modID.equals(Constants.MODID))
            syncConfigData();
    }

    private void syncConfigData() {
        List<String> propOrder = new ArrayList<String>();
        Property prop;

        prop = config.get(CATEGORY_GENERAL, "Atum Portal", true);
        prop.comment = "Can a non-creative user create a portal using the scarab?";
        prop.setLanguageKey("atum.configGui.portalCreation");
        ALLOW_CREATION = prop.getBoolean(true);
        propOrder.add(prop.getName());

        prop = config.get(CATEGORY_GENERAL, "Atum Fog", true);
        prop.comment = "Should clientside fog be rendered?";
        prop.setLanguageKey("atum.configGui.fog");
        FOG_ENABLED = prop.getBoolean(true);
        propOrder.add(prop.getName());

        prop = config.get(CATEGORY_GENERAL, "Atum Dimension ID", 17);
        prop.comment = "The ID of the Atum Dimension";
        prop.setLanguageKey("atum.configGui.dimensionID").setRequiresMcRestart(true);
        DIMENSION_ID = prop.getInt();
        propOrder.add(prop.getName());

        prop = config.get(CATEGORY_GENERAL, "Atum Desert Biome ID", 200);
        prop.comment = "The ID of the Atum Dimension biome Desert";
        prop.setLanguageKey("atum.configGui.biomeID").setRequiresMcRestart(true);
        BIOME_DESERT_ID = prop.getInt();
        propOrder.add(prop.getName());

        config.setCategoryPropertyOrder(CATEGORY_GENERAL, propOrder);

        if (config.hasChanged()) {
            config.save();
        }
    }

}
