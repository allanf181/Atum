package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.blocks.AtumBlocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class LimestoneShovel extends ItemSpade {

	public LimestoneShovel(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == AtumBlocks.BLOCK_LIMESTONECOBBLE.blockID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:LimestoneShovel");
	}
}
