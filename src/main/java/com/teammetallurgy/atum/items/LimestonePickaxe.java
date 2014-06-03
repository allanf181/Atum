package com.teammetallurgy.atum.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

import com.teammetallurgy.atum.blocks.AtumBlocks;

public class LimestonePickaxe extends ItemPickaxe {

	public LimestonePickaxe(ToolMaterial par2ToolMaterial) {
		super( par2ToolMaterial);
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return Block.getBlockFromItem(par2ItemStack.getItem()) == AtumBlocks.BLOCK_LIMESTONECOBBLE;
	}

	@Override
	public void registerIcons(IIconRegister par1IIconRegister) {
		this.itemIcon = par1IIconRegister.registerIcon("atum:LimestonePickaxe");
	}
}
