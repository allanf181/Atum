package com.teammetallurgy.atum.world;

import com.teammetallurgy.atum.AtumIDS;
import com.teammetallurgy.atum.world.biome.BiomeGenAtumDesert;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

public enum AtumWorlds {
    INSTANCE;

    public static final BiomeGenBase BIOME_DESERT = new BiomeGenAtumDesert(AtumIDS.BIOME_DESERT_ID);

    public void register() {
        DimensionManager.registerProviderType(AtumIDS.DIMENSION_ID, AtumWorldProvider.class, true);
        DimensionManager.registerDimension(AtumIDS.DIMENSION_ID, AtumIDS.DIMENSION_ID);
    }
}
