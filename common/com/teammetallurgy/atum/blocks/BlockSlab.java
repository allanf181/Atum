package com.teammetallurgy.atum.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSlab extends BlockHalfSlab {
	public static final String[] slabType = {"smooth", "cracked", "largeBrick", "smallBrick"};

	public BlockSlab(int par1, boolean par2) {
		super(par1, par2, Material.rock);
		this.setHardness(2.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		par2 %= 4;
		if(par2 == 0)
			return Blocks.BLOCK_STONE.getIcon(par1, par2 & 0x7);
		if(par2 == 1)
			return Blocks.BLOCK_LIMESTONECOBBLE.getIcon(par1, par2 & 0x7);
		if(par2 == 2) {
			return Blocks.BLOCK_LARGEBRICK.getIcon(par1, par2 & 0x7);
		}
		return Blocks.BLOCK_SMALLBRICK.getIcon(par1, par2 & 0x7);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return this.blockID;
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this.blockID, 2, par1 & 0x7);
	}

	@Override
	public String getFullSlabName(int par1) {
		if((par1 < 0) || (par1 >= slabType.length)) {
			par1 = 0;
		}

		return super.getUnlocalizedName() + "." + slabType[par1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for(int i = 0; i < 4; i++)
			subItems.add(new ItemStack(this, 1, i));
	}

}
