package com.teammetallurgy.atum.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.teammetallurgy.atum.Atum;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAtumSlab extends BlockSlab {
	public static final String[] slabType = { "smooth", "cracked", "largeBrick", "smallBrick" };

	public BlockAtumSlab(boolean par2) {
		super(par2, Material.rock);
		this.setHardness(2.0F);
		this.setCreativeTab(Atum.creativeTab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		par2 %= 4;
		if (par2 == 0)
			return AtumBlocks.BLOCK_STONE.getIcon(par1, par2 & 0x7);
		if (par2 == 1)
			return AtumBlocks.BLOCK_LIMESTONECOBBLE.getIcon(par1, par2 & 0x7);
		if (par2 == 2) {
			return AtumBlocks.BLOCK_LARGEBRICK.getIcon(par1, par2 & 0x7);
		}
		return AtumBlocks.BLOCK_SMALLBRICK.getIcon(par1, par2 & 0x7);
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this, 2, par1 & 0x7);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < 4; i++)
			subItems.add(new ItemStack(this, 1, i));
	}

	@Override
	public String func_150002_b(int var1) {
		return this.getUnlocalizedName();
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
	}
}
