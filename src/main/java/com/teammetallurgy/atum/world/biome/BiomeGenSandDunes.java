package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.entity.*;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.decorators.*;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeGenSandDunes extends AtumBiomeGenBase {

    public BiomeGenSandDunes(AtumConfig.BiomeConfig config) {
        super(config);

        super.rootHeight = height_LowPlains.rootHeight + 0.1F;
        super.heightVariation = height_LowPlains.variation + 0.2F;
        
        super.palmRarity *= 2;
        // TODO: dead trees
        
        super.addDefaultSpawns();
    }

}
