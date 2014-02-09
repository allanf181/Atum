package com.teammetallurgy.atum.blocks;

import net.minecraft.block.BlockSand;

public class BlockSands extends BlockSand {

	public BlockSands(int par1) {
		super(par1);
		this.setUnlocalizedName("atum:sand");
	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("atum:AtumSand");
	}

}
