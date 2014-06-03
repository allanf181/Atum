package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class BlockStones extends BlockStone {

	public BlockStones() {
		super();
		this.setBlockName("stone");
		this.setStepSound(Block.soundTypeSand);
		this.setHardness(0.5F);
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(AtumBlocks.BLOCK_LIMESTONECOBBLE);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.blockIcon = par1IIconRegister.registerIcon("atum:AtumStone");
	}

}
