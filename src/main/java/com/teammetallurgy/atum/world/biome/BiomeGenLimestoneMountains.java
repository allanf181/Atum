package com.teammetallurgy.atum.world.biome;

import java.util.Random;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BiomeGenLimestoneMountains extends AtumBiomeGenBase {

    public BiomeGenLimestoneMountains(AtumConfig.BiomeConfig config) {
        super(config);
        
        super.setHeight(height_MidHills);
        
        super.palmRarity = -1;
        // TODO: dead trees
        
        super.addDefaultSpawns();
    }
    
    public void genTerrainBlocks(World world, Random rng, Block[] blocks, byte[] bytes, int x, int z, double elevation)
    {
        super.topBlock = AtumBlocks.BLOCK_SAND;
        super.fillerBlock = AtumBlocks.BLOCK_STONE;

        if (elevation > 1.0D) {
            super.topBlock = AtumBlocks.BLOCK_STONE;
            super.fillerBlock = AtumBlocks.BLOCK_STONE;
        }

        this.genBiomeTerrain(world, rng, blocks, bytes, x, z, elevation);
    }

}
