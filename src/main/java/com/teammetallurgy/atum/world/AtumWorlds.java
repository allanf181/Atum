package com.teammetallurgy.atum.world;

import com.teammetallurgy.atum.AtumConfig;
import com.teammetallurgy.atum.world.biome.BiomeGenAtumDesert;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

public enum AtumWorlds {
    INSTANCE;

    public static final BiomeGenBase BIOME_DESERT = new BiomeGenAtumDesert(AtumConfig.BIOME_DESERT_ID);

    public void register() {
        int atumID = AtumConfig.DIMENSION_ID;

        DimensionManager.registerProviderType(atumID, AtumWorldProvider.class, true);
        DimensionManager.registerDimension(atumID, atumID);
    }
}
