package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.handler.AtumConfig;

public class AtumBiomes {
	
	public void register() {
		new BiomeGenAtumDesert(AtumConfig.BiomeConfig.SAND_PLAINS);
	}

}
