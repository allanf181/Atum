package com.teammetallurgy.atum.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.teammetallurgy.atum.Atum;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWalls extends BlockWall {
	@SideOnly(Side.CLIENT)
	private IIcon[] icon;

	public BlockWalls(Block par2Block) {
		super(par2Block);
		this.setBlockName("walls");
		this.setCreativeTab(Atum.creativeTab);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return this.icon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IIconRegister) {
		this.icon = new IIcon[4];
		this.icon[0] = IIconRegister.registerIcon("atum:AtumStone");
		this.icon[1] = IIconRegister.registerIcon("atum:AtumCobble");
		this.icon[2] = IIconRegister.registerIcon("atum:AtumBrickLarge");
		this.icon[3] = IIconRegister.registerIcon("atum:AtumBrickSmall");
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 3));
	}

}
