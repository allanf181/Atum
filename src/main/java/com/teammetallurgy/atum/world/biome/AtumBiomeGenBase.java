package com.teammetallurgy.atum.world.biome;

import java.util.Random;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.entity.EntityBanditArcher;
import com.teammetallurgy.atum.entity.EntityBanditWarrior;
import com.teammetallurgy.atum.entity.EntityBarbarian;
import com.teammetallurgy.atum.entity.EntityBonestorm;
import com.teammetallurgy.atum.entity.EntityDesertWolf;
import com.teammetallurgy.atum.entity.EntityDustySkeleton;
import com.teammetallurgy.atum.entity.EntityGhost;
import com.teammetallurgy.atum.entity.EntityMummy;
import com.teammetallurgy.atum.entity.EntityStoneSoldier;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.world.decorators.WorldGenPalm;
import com.teammetallurgy.atum.world.decorators.WorldGenPyramid;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public class AtumBiomeGenBase extends BiomeGenBase {

	protected int weight = AtumConfig.DEFAULT_BIOME_WEIGHT;
	protected AtumConfig.BiomeConfig config;
	
	protected int palmRarity = 5;
	protected int pyramidRarity = 240;
	
	public AtumBiomeGenBase(int biomeID) {
		super(biomeID);
		
        super.spawnableMonsterList.clear();
        super.spawnableCreatureList.clear();
        super.spawnableWaterCreatureList.clear();
        super.spawnableCaveCreatureList.clear();
        
        this.setDisableRain();
        
        this.topBlock = AtumBlocks.BLOCK_SAND;
        this.fillerBlock = AtumBlocks.BLOCK_STONE;

        this.setColor(16421912);
        
        this.setTemperatureRainfall(2.0F, 0.0F);
        this.setHeight(height_Default);	// same as plains
	}
	
	public AtumBiomeGenBase(AtumConfig.BiomeConfig config) {
		this(config.getID());
		this.config = config;
		
        this.setBiomeName(config.toString());
        this.weight = config.getWeight();
        config.setGen(this);
	}
	
	public int getWeight() {
		return weight;
	}
	
	protected void addDefaultSpawns() {
        super.spawnableMonsterList.add(new SpawnListEntry(EntityMummy.class, 6, 4, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityBanditWarrior.class, 6, 2, 2));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityBarbarian.class, 2, 4, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityBanditArcher.class, 6, 4, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityDustySkeleton.class, 6, 4, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityGhost.class, 6, 4, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityDesertWolf.class, 4, 1, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityStoneSoldier.class, 6, 4, 4));
        super.spawnableMonsterList.add(new SpawnListEntry(EntityBonestorm.class, 6, 4, 4));
	}

    @Override
    public BiomeDecorator createBiomeDecorator() {
        final BiomeDecorator dec = new BiomeDecoratorAtum();
        // dec.treesPerChunk = 1; // defaults to 0
        dec.deadBushPerChunk = 5;
        dec.reedsPerChunk = 0;
        dec.cactiPerChunk = 0;
        
        return dec;
    }
    
    @Override
    public void decorate(World world, Random rng, int chunkx, int chunkz) {
        super.decorate(world, rng, chunkx, chunkz);
        
        int xx, zz, height;
        
        if (palmRarity > 0 && rng.nextInt(palmRarity) == 0) {
            xx = chunkx + rng.nextInt(16) + 8;
            zz = chunkz + rng.nextInt(16) + 8;
            height = rng.nextInt(4) + 5;
           	(new WorldGenPalm(true, height, 0, 0)).generate(world, rng, xx, world.getHeightValue(xx, zz), zz);
        } else
        if (pyramidRarity > 0 && rng.nextInt(pyramidRarity) == 0) {
            xx = chunkx + rng.nextInt(16) + 8;
            zz = chunkz + rng.nextInt(16) + 8;
            (new WorldGenPyramid()).generate(world, rng, xx, world.getHeightValue(xx, zz), zz);
		}

    }
    
    @Override
    public void genTerrainBlocks(World world, Random rng, Block[] blocks, byte[] bytes, int x, int z, double elevation)
    {
        boolean flag = true;
        Block block = this.topBlock;
        byte b0 = (byte) (this.field_150604_aj & 255);
        Block block1 = this.fillerBlock;
        int k = -1;
        int l = (int) (elevation / 3.0D + 3.0D + rng.nextDouble() * 0.25D);
        int i1 = x & 15;
        int j1 = z & 15;
        int k1 = blocks.length / 256;

        for (int l1 = 255; l1 >= 0; --l1) {
            int i2 = (j1 * 16 + i1) * k1 + l1;

            if (l1 <= 0 + rng.nextInt(5)) {
                blocks[i2] = Blocks.bedrock;
            } else {
                Block block2 = blocks[i2];

                if (block2 != null && block2.getMaterial() != Material.air) {
                    if (block2 == AtumBlocks.BLOCK_STONE) {
                        if (k == -1) {
                            if (l <= 0) {
                                block = null;
                                b0 = 0;
                                block1 = AtumBlocks.BLOCK_STONE;
                            } else if (l1 >= 59 && l1 <= 64) {
                                block = this.topBlock;
                                b0 = (byte) (this.field_150604_aj & 255);
                                block1 = this.fillerBlock;
                            }

                            /*
                            if (l1 < 63 && (block == null || block.getMaterial() == Material.air)) {
                                if (this.getFloatTemperature(x, l1, z) < 0.15F) {
                                    block = Blocks.ice;
                                    b0 = 0;
                                } else {
                                    block = Blocks.water;
                                    b0 = 0;
                                }
                            }
                            */

                            k = l;

                            if (l1 >= 62) {
                                blocks[i2] = block;
                                bytes[i2] = b0;
                            } else if (l1 < 56 - l) {
                                block = null;
                                block1 = Blocks.stone;
                                blocks[i2] = Blocks.gravel;
                            } else {
                                blocks[i2] = block1;
                            }
                        } else if (k > 0) {
                            --k;
                            blocks[i2] = block1;

                            if (k == 0 && block1 == Blocks.sand) {
                                k = rng.nextInt(4) + Math.max(0, l1 - 63);
                                block1 = Blocks.sandstone;
                            }
                        }
                    }
                } else {
                    k = -1;
                }
            }
        }
    }


}
