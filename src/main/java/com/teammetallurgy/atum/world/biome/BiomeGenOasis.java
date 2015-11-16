package com.teammetallurgy.atum.world.biome;

import java.util.Random;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeGenOasis extends AtumBiomeGenBase {

	protected final int lakeRarity = 3;
	
    public BiomeGenOasis(AtumConfig.BiomeConfig config) {
        super(config);
        
        //super.topBlock = Blocks.grass;
        super.topBlock = AtumBlocks.BLOCK_FERTILESOIL;
        
        super.setHeight(height_PartiallySubmerged);
        super.setTemperatureRainfall(0.8F, 0.9F);
        super.enableRain = true;
        
        // no hostile spawns here
        
        super.theBiomeDecorator.reedsPerChunk = 10;
        super.theBiomeDecorator.clayPerChunk = 1;
        super.theBiomeDecorator.waterlilyPerChunk = 2;
        
        super.palmRarity = 3;
        super.pyramidRarity = -1;
    }
    
    public void decorate(World world, Random rng, int chunkx, int chunkz) {
        int xx = chunkx + rng.nextInt(16) + 8;
        int yy = rng.nextInt(rng.nextInt(248) + 8);
        int zz = chunkz + rng.nextInt(16) + 8;

        if (yy < 65 || rng.nextInt(lakeRarity) == 0)
        {
            (new WorldGenLakes(Blocks.water)).generate(world, rng, xx, yy, zz);
        }
        
        super.decorate(world, rng, chunkx, chunkz);
    }
    
}
