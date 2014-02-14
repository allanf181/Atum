package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;

import com.teammetallurgy.atum.world.AtumWorlds;

public class BlockAtumOres extends BlockOre {

	public BlockAtumOres(int par1) {
		super(par1);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundStoneFootstep);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return this.blockID == AtumBlocks.BLOCK_COALORE.blockID ? Item.coal.itemID : (this.blockID == AtumBlocks.BLOCK_DIAMONDORE.blockID ? Item.diamond.itemID : (this.blockID == AtumBlocks.BLOCK_LAPISORE.blockID ? Item.dyePowder.itemID : this.blockID));
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return this.blockID == AtumBlocks.BLOCK_LAPISORE.blockID ? 4 + par1Random.nextInt(5) : 1;
	}

	@Override
	public int quantityDroppedWithBonus(int par1, Random par2Random) {
		if(par1 > 0 && this.blockID != this.idDropped(0, par2Random, par1)) {
			int j = par2Random.nextInt(par1 + 2) - 1;
			if(j < 0) {
				j = 0;
			}

			return this.quantityDropped(par2Random) * (j + 1);
		} else {
			return this.quantityDropped(par2Random);
		}
	}

	@Override
	public void dropBlockAsItemWithChance(net.minecraft.world.World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

		if(this.idDropped(par5, par1World.rand, par7) != super.blockID) {
			int j1 = 0;
			if(this.blockID == AtumBlocks.BLOCK_COALORE.blockID) {
				j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 0, 2);
			} else if(this.blockID == AtumBlocks.BLOCK_DIAMONDORE.blockID) {
				j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 3, 7);
			} else if(this.blockID == AtumBlocks.BLOCK_LAPISORE.blockID) {
				j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);
			}

			this.dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
		}

	}

	@Override
	public int damageDropped(int par1) {
		return this.blockID == AtumBlocks.BLOCK_LAPISORE.blockID ? 4 : 0;
	}
}
