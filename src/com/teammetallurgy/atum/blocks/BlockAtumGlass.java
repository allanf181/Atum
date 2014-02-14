package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAtumGlass extends BlockBreakable {

	public BlockAtumGlass(int par1, String texture) {
		super(par1, texture, Material.glass, false);
		this.setStepSound(Block.soundGlassFootstep);
		this.setHardness(0.3F);
	}

	public int quantityDropped(Random rand) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 0;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	protected boolean canSilkHarvest() {
		return true;
	}

}
