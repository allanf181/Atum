package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAtum extends Block {

	public BlockAtum(int par1, String unlocalisedName) {
		super(par1, Material.rock);
		this.setUnlocalizedName(unlocalisedName);
	}
}
