package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class BlockShrub extends BlockDeadBush {

	protected BlockShrub() {
		super();
		this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeGrass);
	}
	
	@Override
	public boolean canPlaceBlockOn(Block par1) {
		return par1 == AtumBlocks.BLOCK_SAND;
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return null;
	}

	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
		if(!par1World.isRemote && par2EntityPlayer.getCurrentEquippedItem() != null && par2EntityPlayer.getCurrentEquippedItem().getItem() == Items.shears) {
			//par2EntityPlayer.addStat(StatList.mineBlockStatArray[this], 1);
			this.dropBlockAsItem(par1World, par3, par4, par5, new ItemStack(AtumBlocks.BLOCK_SHRUB, 1, par6));
		} else {
			super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
		}

	}

}
