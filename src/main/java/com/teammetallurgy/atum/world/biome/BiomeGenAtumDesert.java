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

@Deprecated
public class BiomeGenAtumDesert extends AtumBiomeGenBase {

    public WorldGenerator treeGenerator;
    public WorldGenerator palmGenerator;
    public WorldGenerator ruinsGenerator;

    public BiomeGenAtumDesert(AtumConfig.BiomeConfig config) {
        super(config);

        this.treeGenerator = new WorldGenAtumTrees(true);
        this.palmGenerator = new WorldGenPalm(true);
        this.ruinsGenerator = new WorldGenRuins();
        
        super.addDefaultSpawns();
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4) {
        super.decorate(par1World, par2Random, par3, par4);
        int k;
        int l;
        if (AtumConfig.OASIS_ENABLED && par2Random.nextInt(100) == 0) {
            k = par3 + par2Random.nextInt(16) + 8;
            l = par4 + par2Random.nextInt(16) + 8;
            (new WorldGenOasis(false)).generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
        }

        if (par2Random.nextInt(100) == 0) {
            k = par3 + par2Random.nextInt(16) + 8;
            l = par4 + par2Random.nextInt(16) + 8;
            this.ruinsGenerator.generate(par1World, par2Random, k, par1World.getHeightValue(k, l) + 1, l);
        }

        int height;
        if (par2Random.nextInt(5) == 0) {
            k = par3 + par2Random.nextInt(16) + 8;
            l = par4 + par2Random.nextInt(16) + 8;
            height = par2Random.nextInt(4) + 5;
            (new WorldGenPalm(true, height, 0, 0)).generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
        }

        if (par2Random.nextInt(7) == 0) {
            k = par3 + par2Random.nextInt(16) + 8;
            l = par4 + par2Random.nextInt(16) + 8;
            height = par2Random.nextInt(4) + 4;
            this.treeGenerator.generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
        }

        if (par2Random.nextInt(240) == 0) {
            k = par3 + par2Random.nextInt(16) + 8;
            l = par4 + par2Random.nextInt(16) + 8;
            (new WorldGenPyramid()).generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
        }

		if (par2Random.nextInt(1000) == 0) {
			k = par3 + par2Random.nextInt(16) + 8;
			l = par4 + par2Random.nextInt(16) + 8;
			height = par2Random.nextInt(10);
			if (height <= 20) {
				height += 30;
			}
			(new WorldGenPalace()).generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
		}

    }
}
