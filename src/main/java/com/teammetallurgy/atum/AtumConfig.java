package com.teammetallurgy.atum;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class AtumConfig {

	private final Configuration CONFIG;

	public AtumConfig(File file) {
		this.CONFIG = new Configuration(file);
	}

	public void load() {
		this.CONFIG.load();

		AtumIDS.DIMENSION_ID = get("Dimension", "Atum", 17);

		AtumIDS.BIOME_DESERT_ID = get("Biome", "Desert", 200);
		this.CONFIG.save();
	}


	private int get(String category, String name, int defaultId) {
		return this.CONFIG.get(category, name, defaultId).getInt();
	}

}
