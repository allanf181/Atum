package com.teammetallurgy.atum.items;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

import com.teammetallurgy.atum.blocks.AtumBlocks;

public class LimestoneAxe extends ItemAxe {

	public LimestoneAxe(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == AtumBlocks.BLOCK_LIMESTONECOBBLE;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:LimestoneAxe");
	}
}
