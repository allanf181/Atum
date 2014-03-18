package com.teammetallurgy.atum.worldgen.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.entity.EntityBanditArcher;
import com.teammetallurgy.atum.entity.EntityBanditWarlord;
import com.teammetallurgy.atum.entity.EntityBanditWarrior;
import com.teammetallurgy.atum.entity.EntityBarbarian;
import com.teammetallurgy.atum.entity.EntityDesertWolf;
import com.teammetallurgy.atum.entity.EntityDustySkeleton;
import com.teammetallurgy.atum.entity.EntityGhost;
import com.teammetallurgy.atum.entity.EntityMummy;
import com.teammetallurgy.atum.entity.EntityStoneSoldier;
import com.teammetallurgy.atum.worldgen.decorators.WorldGenAtumTrees;
import com.teammetallurgy.atum.worldgen.decorators.WorldGenPalm;
import com.teammetallurgy.atum.worldgen.decorators.WorldGenRuins;

public class BiomeGenAtumDesert extends BiomeGenBase {

	public WorldGenerator treeGenerator;
	public WorldGenerator palmGenerator;
	public WorldGenerator ruinsGenerator;

	public BiomeGenAtumDesert(int par1) {
		super(par1);
		super.spawnableCreatureList.clear();
		this.topBlock = (byte) AtumBlocks.BLOCK_SAND.blockID;
		this.fillerBlock = (byte) AtumBlocks.BLOCK_STONE.blockID;

		super.theBiomeDecorator.treesPerChunk = 1;
		super.theBiomeDecorator.deadBushPerChunk = 5;
		super.theBiomeDecorator.reedsPerChunk = 0;
		super.theBiomeDecorator.cactiPerChunk = 0;
		this.treeGenerator = new WorldGenAtumTrees(true);
		this.palmGenerator = new WorldGenPalm(true);
		this.ruinsGenerator = new WorldGenRuins();
		super.spawnableMonsterList.clear();
		super.spawnableCreatureList.clear();
		super.spawnableWaterCreatureList.clear();
		super.spawnableCaveCreatureList.clear();
		this.setColor(16421912);
		this.setBiomeName("biome.desert.name");
		this.setDisableRain();
		this.setTemperatureRainfall(2.0F, 0.0F);
		this.setMinMaxHeight(0.1F, 0.2F);
		super.spawnableMonsterList.add(new SpawnListEntry(EntityMummy.class, 6, 4, 4));
		super.spawnableMonsterList.add(new SpawnListEntry(EntityBanditWarlord.class, 1, 1, 1));
		super.spawnableMonsterList.add(new SpawnListEntry(EntityBanditWarrior.class, 6, 2, 2));
		super.spawnableMonsterList.add(new SpawnListEntry(EntityBarbarian.class, 2, 4, 4));
		super.spawnableMonsterList.add(new SpawnListEntry(EntityBanditArcher.class, 6, 4, 4));
		super.spawnableMonsterList.add(new SpawnListEntry(EntityDustySkeleton.class, 6, 4, 4));
		super.spawnableMonsterList.add(new SpawnListEntry(EntityGhost.class, 6, 4, 4));
		super.spawnableMonsterList.add(new SpawnListEntry(EntityDesertWolf.class, 6, 4, 4));
		super.spawnableMonsterList.add(new SpawnListEntry(EntityStoneSoldier.class, 6, 4, 4));
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new BiomeDecoratorAtum(this);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return this.treeGenerator;
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		super.decorate(par1World, par2Random, par3, par4);
//		long time;
//		int k;
//		int l;
//		if(par2Random.nextInt(100) == 0) {
//			k = par3 + par2Random.nextInt(16) + 8;
//			l = par4 + par2Random.nextInt(16) + 8;
//			time = System.nanoTime();
//			(new WorldGenOasis(false)).generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
//		}
//
//		if(par2Random.nextInt(20) == 0) {
//			k = par3 + par2Random.nextInt(16) + 8;
//			l = par4 + par2Random.nextInt(16) + 8;
//			time = System.nanoTime();
//			this.ruinsGenerator.generate(par1World, par2Random, k, par1World.getHeightValue(k, l) + 1, l);
//		}
//
//		int height;
//		if(par2Random.nextInt(5) == 0) {
//			k = par3 + par2Random.nextInt(16) + 8;
//			l = par4 + par2Random.nextInt(16) + 8;
//			height = par2Random.nextInt(4) + 5;
//			time = System.nanoTime();
//			(new WorldGenPalm(true, height, 0, 0)).generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
//		}
//
//		if(par2Random.nextInt(7) == 0) {
//			k = par3 + par2Random.nextInt(16) + 8;
//			l = par4 + par2Random.nextInt(16) + 8;
//			height = par2Random.nextInt(4) + 4;
//			time = System.nanoTime();
//			(new WorldGenAtumTrees(true)).generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
//		}

//		if(par2Random.nextInt(120) == 0) {
//			k = par3 + par2Random.nextInt(16) + 8;
//			l = par4 + par2Random.nextInt(16) + 8;
//			time = System.nanoTime();
//			(new WorldGenPyramid()).generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
//		}

//		if(par2Random.nextInt(160) == 0) {
//			k = par3 + par2Random.nextInt(16) + 8;
//			l = par4 + par2Random.nextInt(16) + 8;
//			height = par2Random.nextInt(10);
//			if(height <= 20) {
//				height += 30;
//			}
//
//			(new WorldGenPalace()).generate(par1World, par2Random, k, height, l);
//		}

	}
}
