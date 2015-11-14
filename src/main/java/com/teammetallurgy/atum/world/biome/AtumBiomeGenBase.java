package com.teammetallurgy.atum.world.biome;

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

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class AtumBiomeGenBase extends BiomeGenBase {

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
		
        this.setBiomeName(config.toString());
        config.setGen(this);
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

}
