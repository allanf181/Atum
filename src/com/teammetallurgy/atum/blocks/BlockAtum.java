package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockAtum extends Block {

	public BlockAtum(int par1, String unlocalisedName) {
		super(par1, Material.rock);
		this.setUnlocalizedName(unlocalisedName);
		this.setHardness(2.0F);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return this.blockID;
	}

}
