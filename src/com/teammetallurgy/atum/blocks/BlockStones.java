package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockStones extends BlockStone {

	public BlockStones(int par1) {
		super(par1);
		this.setUnlocalizedName("stone");
		this.setStepSound(Block.soundSandFootstep);
		this.setHardness(0.5F);
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return AtumBlocks.BLOCK_LIMESTONECOBBLE.blockID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("atum:AtumStone");
	}

}
