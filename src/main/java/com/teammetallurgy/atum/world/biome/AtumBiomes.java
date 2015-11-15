package com.teammetallurgy.atum.world.biome;

import java.util.List;

import com.google.common.collect.Lists;
import com.teammetallurgy.atum.handler.AtumConfig;

public class AtumBiomes {
	
	public static List<AtumBiomeGenBase> biomes = Lists.newArrayList();
	
	public void register() {
		biomes.add(new BiomeGenSandPlains(AtumConfig.BiomeConfig.SAND_PLAINS));
		biomes.add(new BiomeGenSandDunes(AtumConfig.BiomeConfig.SAND_DUNES));
		biomes.add(new BiomeGenSandHills(AtumConfig.BiomeConfig.SAND_HILLS));
		biomes.add(new BiomeGenLimestoneCrags(AtumConfig.BiomeConfig.LIMESTONE_CRAGS));
		biomes.add(new BiomeGenLimestoneMountains(AtumConfig.BiomeConfig.LIMESTONE_MOUNTAINS));
		biomes.add(new BiomeGenOasis(AtumConfig.BiomeConfig.OASIS));
	}

}
