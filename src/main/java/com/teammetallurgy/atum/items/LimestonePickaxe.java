package com.teammetallurgy.atum.items;

import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

import com.teammetallurgy.atum.blocks.AtumBlocks;

public class LimestonePickaxe extends ItemPickaxe {

	public LimestonePickaxe(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == AtumBlocks.BLOCK_LIMESTONECOBBLE;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:LimestonePickaxe");
	}
}
