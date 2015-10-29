package com.teammetallurgy.atum.world.biome;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.decorators.WorldGenShrub;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.Post;
import net.minecraftforge.event.terraingen.OreGenEvent.Pre;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

public class BiomeDecoratorAtum extends BiomeDecorator {

    private float shrubChance;

    public BiomeDecoratorAtum() {
        super();
        this.dirtGen = new WorldGenMinable(AtumBlocks.BLOCK_SAND, 32, AtumBlocks.BLOCK_STONE);
        this.gravelGen = new WorldGenMinable(AtumBlocks.BLOCK_LIMESTONEGRAVEL, 32, AtumBlocks.BLOCK_STONE);
        this.clayGen = new WorldGenMinable(Blocks.clay, 16, AtumBlocks.BLOCK_STONE);
        if (AtumConfig.COAL_ENABLED) { this.coalGen = new WorldGenMinable(AtumBlocks.BLOCK_COALORE, AtumConfig.COAL_VEIN, AtumBlocks.BLOCK_STONE); }
        if (AtumConfig.IRON_ENABLED) { this.ironGen = new WorldGenMinable(AtumBlocks.BLOCK_IRONORE, AtumConfig.IRON_VEIN, AtumBlocks.BLOCK_STONE); }
        if (AtumConfig.GOLD_ENABLED) { this.goldGen = new WorldGenMinable(AtumBlocks.BLOCK_GOLDORE, AtumConfig.GOLD_VEIN, AtumBlocks.BLOCK_STONE); }
        if (AtumConfig.REDSTONE_ENABLED) { this.redstoneGen = new WorldGenMinable(AtumBlocks.BLOCK_REDSTONEORE, AtumConfig.REDSTONE_VEIN, AtumBlocks.BLOCK_STONE); }
        if (AtumConfig.DIAMOND_ENABLED) { this.diamondGen = new WorldGenMinable(AtumBlocks.BLOCK_DIAMONDORE, AtumConfig.DIAMOND_VEIN, AtumBlocks.BLOCK_STONE); }
        if (AtumConfig.LAPIS_ENABLED) { this.lapisGen = new WorldGenMinable(AtumBlocks.BLOCK_LAPISORE, AtumConfig.LAPIS_VEIN); }

        this.treesPerChunk = 0;
        this.shrubChance = 0.3F;
        this.generateLakes = false;
    }

    @Override
    public void decorateChunk(World world, Random random, BiomeGenBase biomeGenBase, int x, int z) {
        if (this.currentWorld != null) {
            throw new RuntimeException("Already decorating!!");
        } else {
            this.currentWorld = world;
            this.randomGenerator = random;
            this.chunk_X = x;
            this.chunk_Z = z;
            this.genDecorations(biomeGenBase);
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }

    @Override
    protected void genDecorations(BiomeGenBase biome) {
        MinecraftForge.EVENT_BUS.post(new Pre(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
        this.generateOres();
        boolean doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, EventType.SAND_PASS2);

        int i;
        int j;
        int k;
        long time;
        for (i = 0; doGen && i < this.sandPerChunk; ++i) {
            j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            time = System.nanoTime();
            this.sandGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
        }

        i = this.treesPerChunk;
        if (this.randomGenerator.nextInt(10) == 0) {
            ++i;
        }

        time = System.nanoTime();
        doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, EventType.GRASS);

        int l;
        int i1;
        for (j = 0; doGen && j < this.grassPerChunk; ++j) {
            k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            l = this.randomGenerator.nextInt(128);
            i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            WorldGenerator worldgenerator1 = biome.getRandomWorldGenForGrass(this.randomGenerator);
            worldgenerator1.generate(this.currentWorld, this.randomGenerator, k, l, i1);
        }

        if (this.randomGenerator.nextFloat() < this.shrubChance) {
            k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            l = this.randomGenerator.nextInt(128);
            i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            time = System.nanoTime();
            (new WorldGenShrub(AtumBlocks.BLOCK_SHRUB, 8)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
        }

        if (this.randomGenerator.nextFloat() < this.shrubChance) {
            k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            l = this.randomGenerator.nextInt(128);
            i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            time = System.nanoTime();
            (new WorldGenShrub(AtumBlocks.BLOCK_WEED, 8)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
        }

        MinecraftForge.EVENT_BUS.post(new Post(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
    }

    @Override
    protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4) {
        for (int l = 0; l < par1; ++l) {
            int i1 = this.chunk_X + this.randomGenerator.nextInt(16);
            int j1 = this.randomGenerator.nextInt(par4 - par3) + par3;
            int k1 = this.chunk_Z + this.randomGenerator.nextInt(16);
            par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, i1, j1, k1);
        }

    }

    @Override
    protected void genStandardOre2(int par1, WorldGenerator par2WorldGenerator, int par3, int par4) {
        for (int l = 0; l < par1; ++l) {
            int i1 = this.chunk_X + this.randomGenerator.nextInt(16);
            int j1 = this.randomGenerator.nextInt(par4) + this.randomGenerator.nextInt(par4) + (par3 - par4);
            int k1 = this.chunk_Z + this.randomGenerator.nextInt(16);
            par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, i1, j1, k1);
        }

    }

    @Override
    protected void generateOres() {
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.coalGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.COAL)) {
            this.genStandardOre1(20, this.coalGen, 0, 128);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.ironGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.IRON)) {
            this.genStandardOre1(20, this.ironGen, 0, 64);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.goldGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.GOLD)) {
            this.genStandardOre1(2, this.goldGen, 0, 32);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.redstoneGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.REDSTONE)) {
            this.genStandardOre1(8, this.redstoneGen, 0, 16);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.diamondGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.DIAMOND)) {
            this.genStandardOre1(1, this.diamondGen, 0, 16);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.lapisGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.LAPIS)) {
            this.genStandardOre2(1, this.lapisGen, 16, 16);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.dirtGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.DIRT)) {
            this.genStandardOre1(20, this.dirtGen, 0, 256);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.gravelGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.GRAVEL)) {
            this.genStandardOre1(10, this.gravelGen, 0, 256);
        }

        if (TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.clayGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
            this.genStandardOre1(8, this.clayGen, 0, 64);
        }

        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
    }
}
