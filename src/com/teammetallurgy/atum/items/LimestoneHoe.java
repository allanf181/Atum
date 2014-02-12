package com.teammetallurgy.atum.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

import com.teammetallurgy.atum.blocks.Blocks;

public class LimestoneHoe extends ItemHoe {

	public LimestoneHoe(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Blocks.BLOCK_LIMESTONECOBBLE.blockID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:LimestoneHoe");
	}
}
