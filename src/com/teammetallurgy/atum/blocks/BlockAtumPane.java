package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;

public class BlockAtumPane extends BlockPane {

	protected BlockAtumPane(int par1, String par2Str, String par3Str) {
		super(par1, par2Str, par3Str, Material.glass, false);
		this.setHardness(0.3F);
		this.setStepSound(Block.soundGlassFootstep);
	}

}
