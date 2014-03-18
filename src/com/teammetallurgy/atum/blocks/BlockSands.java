package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockSands extends BlockSand {

	public BlockSands(int par1) {
		super(par1);
		this.setUnlocalizedName("sand");
		this.setStepSound(Block.soundSandFootstep);
		this.setHardness(0.5F);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("atum:AtumSand");
	}

}
