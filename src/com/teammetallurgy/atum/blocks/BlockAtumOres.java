package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;

public class BlockAtumOres extends BlockOre {

	public BlockAtumOres(int par1) {
		super(par1);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundStoneFootstep);
	}

}
