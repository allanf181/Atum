package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.entity.*;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.decorators.*;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeGenSandPlains extends AtumBiomeGenBase {

    public BiomeGenSandPlains(AtumConfig.BiomeConfig config) {
        super(config);
        
        // plains should be twice as common as other biomes
        this.weight = super.DEFAULT_WEIGHT * 2;
        
        super.addDefaultSpawns();
    }

}
