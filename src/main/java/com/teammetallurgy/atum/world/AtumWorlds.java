package com.teammetallurgy.atum.world;

import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.biome.BiomeGenAtumDesert;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

public class AtumWorlds {
    public void register() {
        int atumID = AtumConfig.DIMENSION_ID;

        DimensionManager.registerProviderType(atumID, AtumWorldProvider.class, true);
        DimensionManager.registerDimension(atumID, atumID);
    }
}
