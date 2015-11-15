package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.entity.*;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.decorators.*;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeGenSandHills extends AtumBiomeGenBase {

    public BiomeGenSandHills(AtumConfig.BiomeConfig config) {
        super(config);
        
        super.setHeight(height_LowHills);
        //super.topBlock = Blocks.stone;
        
        super.palmRarity *= 4;
        // TODO: dead trees
        
        super.addDefaultSpawns();
    }
    
}
