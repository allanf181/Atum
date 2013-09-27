package rebelkeithy.mods.atum.world.biome;

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
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Post;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Pre;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import rebelkeithy.mods.atum.AtumBlocks;
import rebelkeithy.mods.atum.world.decorators.WorldGenShrub;

public class BiomeDecoratorAtum extends BiomeDecorator {

   private float shrubChance;


   public BiomeDecoratorAtum(BiomeGenBase par1BiomeGenBase) {
      super(par1BiomeGenBase);
      super.sandGen = new WorldGenSand(7, AtumBlocks.sand.blockID);
      super.gravelAsSandGen = new WorldGenSand(6, Block.gravel.blockID);
      super.dirtGen = new WorldGenMinable(Block.dirt.blockID, 32);
      super.gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
      super.coalGen = new WorldGenMinable(AtumBlocks.coalOre.blockID, 16, AtumBlocks.stone.blockID);
      super.ironGen = new WorldGenMinable(AtumBlocks.ironOre.blockID, 8, AtumBlocks.stone.blockID);
      super.goldGen = new WorldGenMinable(AtumBlocks.goldOre.blockID, 8, AtumBlocks.stone.blockID);
      super.redstoneGen = new WorldGenMinable(AtumBlocks.redstoneOre.blockID, 7, AtumBlocks.stone.blockID);
      super.diamondGen = new WorldGenMinable(AtumBlocks.diamondOre.blockID, 7, AtumBlocks.stone.blockID);
      super.lapisGen = new WorldGenMinable(AtumBlocks.lapisOre.blockID, 6);
      super.plantYellowGen = new WorldGenFlowers(Block.plantYellow.blockID);
      super.plantRedGen = new WorldGenFlowers(Block.plantRed.blockID);
      super.mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
      super.mushroomRedGen = new WorldGenFlowers(Block.mushroomRed.blockID);
      super.bigMushroomGen = new WorldGenBigMushroom();
      super.reedGen = new WorldGenReed();
      super.cactusGen = new WorldGenCactus();
      super.waterlilyGen = new WorldGenWaterlily();
      super.waterlilyPerChunk = 0;
      super.treesPerChunk = 0;
      super.flowersPerChunk = 2;
      super.grassPerChunk = 1;
      super.deadBushPerChunk = 1;
      this.shrubChance = 0.3F;
      super.mushroomsPerChunk = 0;
      super.reedsPerChunk = 0;
      super.cactiPerChunk = 0;
      super.sandPerChunk = 1;
      super.sandPerChunk2 = 3;
      super.clayPerChunk = 1;
      super.bigMushroomsPerChunk = 0;
      super.generateLakes = true;
      super.biome = par1BiomeGenBase;
   }

   public void decorate(World par1World, Random par2Random, int par3, int par4) {
      if(super.currentWorld != null) {
         throw new RuntimeException("Already decorating!!");
      } else {
         super.currentWorld = par1World;
         super.randomGenerator = par2Random;
         super.chunk_X = par3;
         super.chunk_Z = par4;
         this.decorate();
         super.currentWorld = null;
         super.randomGenerator = null;
      }
   }

   protected void decorate() {
      MinecraftForge.EVENT_BUS.post(new Pre(super.currentWorld, super.randomGenerator, super.chunk_X, super.chunk_Z));
      this.generateOres();
      boolean doGen = TerrainGen.decorate(super.currentWorld, super.randomGenerator, super.chunk_X, super.chunk_Z, EventType.SAND_PASS2);

      int i;
      int j;
      int k;
      long time;
      for(i = 0; doGen && i < super.sandPerChunk; ++i) {
         j = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         k = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         time = System.nanoTime();
         super.sandGen.generate(super.currentWorld, super.randomGenerator, j, super.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
      }

      i = super.treesPerChunk;
      if(super.randomGenerator.nextInt(10) == 0) {
         ++i;
      }

      time = System.nanoTime();
      doGen = TerrainGen.decorate(super.currentWorld, super.randomGenerator, super.chunk_X, super.chunk_Z, EventType.GRASS);

      int l;
      int i1;
      for(j = 0; doGen && j < super.grassPerChunk; ++j) {
         k = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         l = super.randomGenerator.nextInt(128);
         i1 = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         WorldGenerator worldgenerator1 = super.biome.getRandomWorldGenForGrass(super.randomGenerator);
         worldgenerator1.generate(super.currentWorld, super.randomGenerator, k, l, i1);
      }

      if(super.randomGenerator.nextFloat() < this.shrubChance) {
         k = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         l = super.randomGenerator.nextInt(128);
         i1 = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         time = System.nanoTime();
         (new WorldGenShrub(AtumBlocks.shrub.blockID, 8)).generate(super.currentWorld, super.randomGenerator, k, l, i1);
      }

      if(super.randomGenerator.nextFloat() < this.shrubChance) {
         k = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         l = super.randomGenerator.nextInt(128);
         i1 = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         time = System.nanoTime();
         (new WorldGenShrub(AtumBlocks.weed.blockID, 8)).generate(super.currentWorld, super.randomGenerator, k, l, i1);
      }

      doGen = TerrainGen.decorate(super.currentWorld, super.randomGenerator, super.chunk_X, super.chunk_Z, EventType.LAKE);
      if(doGen && super.generateLakes) {
         for(j = 0; j < 20; ++j) {
            k = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
            l = super.randomGenerator.nextInt(super.randomGenerator.nextInt(super.randomGenerator.nextInt(112) + 8) + 8);
            i1 = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
            time = System.nanoTime();
            (new WorldGenLiquids(Block.lavaMoving.blockID)).generate(super.currentWorld, super.randomGenerator, k, l, i1);
         }
      }

      MinecraftForge.EVENT_BUS.post(new Post(super.currentWorld, super.randomGenerator, super.chunk_X, super.chunk_Z));
   }

   protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4) {
      for(int l = 0; l < par1; ++l) {
         int i1 = super.chunk_X + super.randomGenerator.nextInt(16);
         int j1 = super.randomGenerator.nextInt(par4 - par3) + par3;
         int k1 = super.chunk_Z + super.randomGenerator.nextInt(16);
         par2WorldGenerator.generate(super.currentWorld, super.randomGenerator, i1, j1, k1);
      }

   }

   protected void genStandardOre2(int par1, WorldGenerator par2WorldGenerator, int par3, int par4) {
      for(int l = 0; l < par1; ++l) {
         int i1 = super.chunk_X + super.randomGenerator.nextInt(16);
         int j1 = super.randomGenerator.nextInt(par4) + super.randomGenerator.nextInt(par4) + (par3 - par4);
         int k1 = super.chunk_Z + super.randomGenerator.nextInt(16);
         par2WorldGenerator.generate(super.currentWorld, super.randomGenerator, i1, j1, k1);
      }

   }

   protected void generateOres() {
      MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(super.currentWorld, super.randomGenerator, super.chunk_X, super.chunk_Z));
      if(TerrainGen.generateOre(super.currentWorld, super.randomGenerator, super.coalGen, super.chunk_X, super.chunk_Z, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.COAL)) {
         this.genStandardOre1(20, super.coalGen, 0, 128);
      }

      if(TerrainGen.generateOre(super.currentWorld, super.randomGenerator, super.ironGen, super.chunk_X, super.chunk_Z, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.IRON)) {
         this.genStandardOre1(20, super.ironGen, 0, 64);
      }

      if(TerrainGen.generateOre(super.currentWorld, super.randomGenerator, super.goldGen, super.chunk_X, super.chunk_Z, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD)) {
         this.genStandardOre1(2, super.goldGen, 0, 32);
      }

      if(TerrainGen.generateOre(super.currentWorld, super.randomGenerator, super.redstoneGen, super.chunk_X, super.chunk_Z, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.REDSTONE)) {
         this.genStandardOre1(8, super.redstoneGen, 0, 16);
      }

      if(TerrainGen.generateOre(super.currentWorld, super.randomGenerator, super.diamondGen, super.chunk_X, super.chunk_Z, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND)) {
         this.genStandardOre1(1, super.diamondGen, 0, 16);
      }

      if(TerrainGen.generateOre(super.currentWorld, super.randomGenerator, super.lapisGen, super.chunk_X, super.chunk_Z, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS)) {
         this.genStandardOre2(1, super.lapisGen, 16, 16);
      }

      MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(super.currentWorld, super.randomGenerator, super.chunk_X, super.chunk_Z));
   }
}
