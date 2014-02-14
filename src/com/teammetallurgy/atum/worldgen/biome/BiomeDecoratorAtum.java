package com.teammetallurgy.atum.worldgen.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.Post;
import net.minecraftforge.event.terraingen.OreGenEvent.Pre;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.worldgen.decorators.WorldGenShrub;

public class BiomeDecoratorAtum extends BiomeDecorator {

	private float shrubChance;

	public BiomeDecoratorAtum(BiomeGenBase par1BiomeGenBase) {
		super(par1BiomeGenBase);
		this.sandGen = new WorldGenSand(7, AtumBlocks.BLOCK_SAND.blockID);
		this.gravelAsSandGen = new WorldGenSand(6, Block.gravel.blockID);
		this.dirtGen = new WorldGenMinable(Block.dirt.blockID, 32);
		this.gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
		this.coalGen = new WorldGenMinable(AtumBlocks.BLOCK_COALORE.blockID, 16, AtumBlocks.BLOCK_STONE.blockID);
		this.ironGen = new WorldGenMinable(AtumBlocks.BLOCK_IRONORE.blockID, 8, AtumBlocks.BLOCK_STONE.blockID);
		this.goldGen = new WorldGenMinable(AtumBlocks.BLOCK_GOLDORE.blockID, 8, AtumBlocks.BLOCK_STONE.blockID);
		this.redstoneGen = new WorldGenMinable(AtumBlocks.BLOCK_REDSTONEORE.blockID, 7, AtumBlocks.BLOCK_STONE.blockID);
		this.diamondGen = new WorldGenMinable(AtumBlocks.BLOCK_DIAMONDORE.blockID, 7, AtumBlocks.BLOCK_STONE.blockID);
		this.lapisGen = new WorldGenMinable(AtumBlocks.BLOCK_LAPISORE.blockID, 6);
		
		this.plantYellowGen = new WorldGenFlowers(Block.plantYellow.blockID);
		this.plantRedGen = new WorldGenFlowers(Block.plantRed.blockID);
		this.mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
		this.mushroomRedGen = new WorldGenFlowers(Block.mushroomRed.blockID);
		this.bigMushroomGen = new WorldGenBigMushroom();
		this.reedGen = new WorldGenReed();
		this.cactusGen = new WorldGenCactus();
		this.waterlilyGen = new WorldGenWaterlily();
		this.waterlilyPerChunk = 0;
		this.treesPerChunk = 0;
		this.flowersPerChunk = 2;
		this.grassPerChunk = 1;
		this.deadBushPerChunk = 1;
		this.shrubChance = 0.3F;
		this.mushroomsPerChunk = 0;
		this.reedsPerChunk = 0;
		this.cactiPerChunk = 0;
		this.sandPerChunk = 1;
		this.sandPerChunk2 = 3;
		this.clayPerChunk = 1;
		this.bigMushroomsPerChunk = 0;
		this.generateLakes = true;
		this.biome = par1BiomeGenBase;
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		if(this.currentWorld != null) {
			throw new RuntimeException("Already decorating!!");
		} else {
			this.currentWorld = par1World;
			this.randomGenerator = par2Random;
			this.chunk_X = par3;
			this.chunk_Z = par4;
			this.decorate();
			this.currentWorld = null;
			this.randomGenerator = null;
		}
	}

	@Override
	protected void decorate() {
		MinecraftForge.EVENT_BUS.post(new Pre(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
		this.generateOres();
		boolean doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, EventType.SAND_PASS2);

		int i;
		int j;
		int k;
		long time;
		for(i = 0; doGen && i < this.sandPerChunk; ++i) {
			j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			time = System.nanoTime();
			this.sandGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
		}

		i = this.treesPerChunk;
		if(this.randomGenerator.nextInt(10) == 0) {
			++i;
		}

		time = System.nanoTime();
		doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, EventType.GRASS);

		int l;
		int i1;
		for(j = 0; doGen && j < this.grassPerChunk; ++j) {
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.randomGenerator.nextInt(128);
			i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			WorldGenerator worldgenerator1 = this.biome.getRandomWorldGenForGrass(this.randomGenerator);
			worldgenerator1.generate(this.currentWorld, this.randomGenerator, k, l, i1);
		}

		if(this.randomGenerator.nextFloat() < this.shrubChance) {
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.randomGenerator.nextInt(128);
			i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			time = System.nanoTime();
			(new WorldGenShrub(AtumBlocks.BLOCK_SHRUB.blockID, 8)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
		}

		if(this.randomGenerator.nextFloat() < this.shrubChance) {
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.randomGenerator.nextInt(128);
			i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			time = System.nanoTime();
			(new WorldGenShrub(AtumBlocks.BLOCK_WEED.blockID, 8)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
		}

		doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, EventType.LAKE);
		if(doGen && this.generateLakes) {
			for(j = 0; j < 20; ++j) {
				k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				l = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
				i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				time = System.nanoTime();
				(new WorldGenLiquids(Block.lavaMoving.blockID)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
			}
		}

		MinecraftForge.EVENT_BUS.post(new Post(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
	}

	@Override
	protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4) {
		for(int l = 0; l < par1; ++l) {
			int i1 = this.chunk_X + this.randomGenerator.nextInt(16);
			int j1 = this.randomGenerator.nextInt(par4 - par3) + par3;
			int k1 = this.chunk_Z + this.randomGenerator.nextInt(16);
			par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, i1, j1, k1);
		}

	}

	@Override
	protected void genStandardOre2(int par1, WorldGenerator par2WorldGenerator, int par3, int par4) {
		for(int l = 0; l < par1; ++l) {
			int i1 = this.chunk_X + this.randomGenerator.nextInt(16);
			int j1 = this.randomGenerator.nextInt(par4) + this.randomGenerator.nextInt(par4) + (par3 - par4);
			int k1 = this.chunk_Z + this.randomGenerator.nextInt(16);
			par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, i1, j1, k1);
		}

	}

	@Override
	protected void generateOres() {
		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
		if(TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.coalGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.COAL)) {
			this.genStandardOre1(20, this.coalGen, 0, 128);
		}

		if(TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.ironGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.IRON)) {
			this.genStandardOre1(20, this.ironGen, 0, 64);
		}

		if(TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.goldGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.GOLD)) {
			this.genStandardOre1(2, this.goldGen, 0, 32);
		}

		if(TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.redstoneGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.REDSTONE)) {
			this.genStandardOre1(8, this.redstoneGen, 0, 16);
		}

		if(TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.diamondGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.DIAMOND)) {
			this.genStandardOre1(1, this.diamondGen, 0, 16);
		}

		if(TerrainGen.generateOre(this.currentWorld, this.randomGenerator, this.lapisGen, this.chunk_X, this.chunk_Z, OreGenEvent.GenerateMinable.EventType.LAPIS)) {
			this.genStandardOre2(1, this.lapisGen, 16, 16);
		}

		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
	}
}
