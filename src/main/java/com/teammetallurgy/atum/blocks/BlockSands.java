package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockSands extends Block {

	public BlockSands() {
		super(Material.sand);
		this.setBlockName("sand");
		this.setStepSound(Block.soundTypeSand);
		this.setHardness(0.5F);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.blockIcon = par1IIconRegister.registerIcon("atum:AtumSand");
	}

}
