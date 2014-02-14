package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockAtum extends Block {
	boolean isBreak = false;

	public BlockAtum(int par1, String unlocalisedName) {
		super(par1, Material.rock);
		this.setUnlocalizedName(unlocalisedName);
		this.setHardness(2.0F);
	}

	public Block setNonBreak() {
		this.isBreak = true;
		return this;
	}

	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z) {
		return isBreak ? false : super.removeBlockByPlayer(world, player, x, y, z);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return this.blockID;
	}

}
