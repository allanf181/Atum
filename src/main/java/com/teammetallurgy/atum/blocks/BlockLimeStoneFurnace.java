package com.teammetallurgy.atum.blocks;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLimeStoneFurnace extends BlockFurnace {

	boolean isActive;
	@SideOnly(Side.CLIENT)
	private IIcon furnaceFront;

	protected BlockLimeStoneFurnace(int par1, boolean par2) {
		super(par2);
		this.isActive = par2;
		this.setHardness(3.5F);
		this.setStepSound(Block.soundTypeStone);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		if (par2 == 0) {
			par2 = 3;
		}

		return par1 == 1 ? this.blockIcon : (par1 == 0 ? this.blockIcon : (par1 != par2 ? this.blockIcon : this.furnaceFront));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("atum:FurnaceTop");
		this.furnaceFront = par1IconRegister.registerIcon(this.isActive ? "atum:FurnaceBurning" : "atum:FurnaceFront");
	}
}
