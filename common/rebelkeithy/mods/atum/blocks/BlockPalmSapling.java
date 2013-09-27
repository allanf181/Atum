package rebelkeithy.mods.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.AtumBlocks;

public class BlockPalmSapling extends BlockFlower {

	public BlockPalmSapling(int par1) {
		super(par1);
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			super.updateTick(world, x, y, z, rand);
			if (world.getBlockLightValue(x, y + 1, z) > 9 && rand.nextInt(20) == 0) {
				this.growTree(world, x, y, z, rand);
			}
		}

	}

	public void growTree(World world, int x, int y, int z, Random rand) {
		int height = rand.nextInt(4) + 5;

		int i;
		for (i = 0; i < height; ++i) {
			world.setBlock(x, y + i, z, AtumBlocks.log.blockID, 0, 2);
		}

		world.setBlock(x, y + height, z, AtumBlocks.leaves.blockID, 0, 2);

		for (i = -1; i < 2; ++i) {
			for (int j = -1; j < 2; ++j) {
				if (i != 0 || j != 0) {
					world.setBlock(x + i, y + height - 1, z + j, AtumBlocks.leaves.blockID, 0, 2);
				}
			}
		}

		world.setBlock(x + 2, y + height - 1, z, AtumBlocks.leaves.blockID, 0, 2);
		world.setBlock(x + 2, y + height - 2, z, AtumBlocks.leaves.blockID, 0, 2);
		world.setBlock(x + 3, y + height - 2, z, AtumBlocks.leaves.blockID, 0, 2);
		if (rand.nextInt(100) < 15) {
			world.setBlock(x + 1, y + height - 2, z, AtumBlocks.dateBlock.blockID, 0, 2);
		}

		world.setBlock(x - 2, y + height - 1, z, AtumBlocks.leaves.blockID, 0, 2);
		world.setBlock(x - 2, y + height - 2, z, AtumBlocks.leaves.blockID, 0, 2);
		world.setBlock(x - 3, y + height - 2, z, AtumBlocks.leaves.blockID, 0, 2);
		if (rand.nextInt(100) < 15) {
			world.setBlock(x - 1, y + height - 2, z, AtumBlocks.dateBlock.blockID, 0, 2);
		}

		world.setBlock(x, y + height - 1, z + 2, AtumBlocks.leaves.blockID, 0, 2);
		world.setBlock(x, y + height - 2, z + 2, AtumBlocks.leaves.blockID, 0, 2);
		world.setBlock(x, y + height - 2, z + 3, AtumBlocks.leaves.blockID, 0, 2);
		if (rand.nextInt(100) < 15) {
			world.setBlock(x, y + height - 2, z + 1, AtumBlocks.dateBlock.blockID, 0, 2);
		}

		world.setBlock(x, y + height - 1, z - 2, AtumBlocks.leaves.blockID, 0, 2);
		world.setBlock(x, y + height - 2, z - 2, AtumBlocks.leaves.blockID, 0, 2);
		world.setBlock(x, y + height - 2, z - 3, AtumBlocks.leaves.blockID, 0, 2);
		if (rand.nextInt(100) < 15) {
			world.setBlock(x, y + height - 2, z - 1, AtumBlocks.dateBlock.blockID, 0, 2);
		}

	}

	protected boolean canThisPlantGrowOnThisBlockID(int id) {
		return id == AtumBlocks.sand.blockID || id == Block.grass.blockID || id == Block.dirt.blockID;
	}

	public boolean canBlockStay(World world, int x, int y, int z) {
		return world.getBlockId(x, y - 1, z) == AtumBlocks.sand.blockID || world.getBlockId(x, y - 1, z) == Block.grass.blockID || world.getBlockId(x, y - 1, z) == Block.dirt.blockID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Atum.modID + ":AtumPalmSapling");
	}
}
