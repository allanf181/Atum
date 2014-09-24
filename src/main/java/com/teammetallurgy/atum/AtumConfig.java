package com.teammetallurgy.atum;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class AtumConfig {

    private final Configuration CONFIG;

    public AtumConfig(File file) {
        System.out.println(file);
        this.CONFIG = new Configuration(file);
    }

    public void load() {
        this.CONFIG.load();

        AtumIDS.DIMENSION_ID = getInt("Dimension", "Atum", 17);

        AtumIDS.BIOME_DESERT_ID = getInt("Biome", "Desert", 200);
        AtumIDS.ALLOW_CREATION = getBoolean("Scarab", "Create Portal", true);
        this.CONFIG.save();
    }


    private int getInt(String category, String name, int defaultId) {
        return this.CONFIG.get(category, name, defaultId).getInt();
    }

    private boolean getBoolean(String category, String name, boolean defaultId) {
        return this.CONFIG.get(category, name, defaultId).getBoolean(true);
    }
}
