package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockWall extends ItemBlock {
	public static final String[] types = {"smooth", "cracked", "largeBrick", "smallBrick"};

	public ItemBlockWall(int par1) {
		super(par1);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return "tile." + types[par1ItemStack.getItemDamage()] + "Wall";
	}

}
