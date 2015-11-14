package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.handler.AtumConfig;

public class AtumBiomes {
	
	public void register() {
		new BiomeGenSandPlains(AtumConfig.BiomeConfig.SAND_PLAINS);
		new BiomeGenSandDunes(AtumConfig.BiomeConfig.SAND_DUNES);
		new BiomeGenSandHills(AtumConfig.BiomeConfig.SAND_HILLS);
	}

}
