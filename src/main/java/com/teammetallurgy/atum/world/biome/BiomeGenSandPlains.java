package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.handler.AtumConfig;

public class BiomeGenSandPlains extends AtumBiomeGenBase {

    public BiomeGenSandPlains(AtumConfig.BiomeConfig config) {
        super(config);
        
        super.setHeight(height_LowPlains);
        
        super.addDefaultSpawns();
    }

}
