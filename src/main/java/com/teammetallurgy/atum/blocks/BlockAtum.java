package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockAtum extends Block {
	boolean isBreak;

	public BlockAtum(int par1, String unlocalisedName) {
		super(Material.rock);
		this.setUnlocalizedName(unlocalisedName);
	}

	public Block setNonBreak() {
		this.isBreak = true;
		return this;
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x,
			int y, int z) {
		return isBreak ? false : super.removedByPlayer(world, player, x, y, z);
	}
}
