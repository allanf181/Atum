package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockAtum extends Block {

	public BlockAtum(String unlocalisedName) {
		super(Material.rock);
		this.setBlockName(unlocalisedName);
		this.setHardness(2.0F);
	}

}
