package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.BlockStone;

public class BlockStones extends BlockStone {

	public BlockStones(int par1) {
		super(par1);
		this.setUnlocalizedName("atum:stone");
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return AtumBlocks.BLOCK_LIMESTONECOBBLE;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("atum:AtumStone");
	}

}
