package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;

public class BlockPalmSapling extends BlockFlower {

	protected BlockPalmSapling(int par1) {
		super(par1);
		this.setUnlocalizedName("atum:palmSapling");
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if(!world.isRemote) {
			super.updateTick(world, x, y, z, rand);
			if(world.getBlockLightValue(x, y + 1, z) > 9 && rand.nextInt(20) == 0) {
				this.growTree(world, x, y, z, rand);
			}
		}

	}

	public void growTree(World world, int x, int y, int z, Random rand) {
		int height = rand.nextInt(4) + 5;

		int i;
		for(i = 0; i < height; ++i) {
			world.setBlock(x, y + i, z, Blocks.BLOCK_LOG.blockID, 0, 2);
		}

		world.setBlock(x, y + height, z, Blocks.BLOCK_LEAVES.blockID, 0, 2);

		for(i = -1; i < 2; ++i) {
			for(int j = -1; j < 2; ++j) {
				if(i != 0 || j != 0) {
					world.setBlock(x + i, y + height - 1, z + j, Blocks.BLOCK_LEAVES.blockID, 0, 2);
				}
			}
		}

		world.setBlock(x + 2, y + height - 1, z, Blocks.BLOCK_LEAVES.blockID, 0, 2);
		world.setBlock(x + 2, y + height - 2, z, Blocks.BLOCK_LEAVES.blockID, 0, 2);
		world.setBlock(x + 3, y + height - 2, z, Blocks.BLOCK_LEAVES.blockID, 0, 2);
		if(rand.nextInt(100) < 15) {
			world.setBlock(x + 1, y + height - 2, z, Blocks.BLOCK_DATEBLOCK.blockID, 0, 2);
		}

		world.setBlock(x - 2, y + height - 1, z, Blocks.BLOCK_LEAVES.blockID, 0, 2);
		world.setBlock(x - 2, y + height - 2, z, Blocks.BLOCK_LEAVES.blockID, 0, 2);
		world.setBlock(x - 3, y + height - 2, z, Blocks.BLOCK_LEAVES.blockID, 0, 2);
		if(rand.nextInt(100) < 15) {
			world.setBlock(x - 1, y + height - 2, z, Blocks.BLOCK_DATEBLOCK.blockID, 0, 2);
		}

		world.setBlock(x, y + height - 1, z + 2, Blocks.BLOCK_LEAVES.blockID, 0, 2);
		world.setBlock(x, y + height - 2, z + 2, Blocks.BLOCK_LEAVES.blockID, 0, 2);
		world.setBlock(x, y + height - 2, z + 3, Blocks.BLOCK_LEAVES.blockID, 0, 2);
		if(rand.nextInt(100) < 15) {
			world.setBlock(x, y + height - 2, z + 1, Blocks.BLOCK_DATEBLOCK.blockID, 0, 2);
		}

		world.setBlock(x, y + height - 1, z - 2, Blocks.BLOCK_LEAVES.blockID, 0, 2);
		world.setBlock(x, y + height - 2, z - 2, Blocks.BLOCK_LEAVES.blockID, 0, 2);
		world.setBlock(x, y + height - 2, z - 3, Blocks.BLOCK_LEAVES.blockID, 0, 2);
		if(rand.nextInt(100) < 15) {
			world.setBlock(x, y + height - 2, z - 1, Blocks.BLOCK_DATEBLOCK.blockID, 0, 2);
		}

	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id) {
		return id == Blocks.BLOCK_SAND.blockID || id == Block.grass.blockID || id == Block.dirt.blockID;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return world.getBlockId(x, y - 1, z) == Blocks.BLOCK_SAND.blockID || world.getBlockId(x, y - 1, z) == Block.grass.blockID || world.getBlockId(x, y - 1, z) == Block.dirt.blockID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("atum:AtumPalmSapling");
	}
}
