package com.teammetallurgy.atum.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWalls extends BlockWall {
	@SideOnly(Side.CLIENT)
	private Icon[] icon;

	public BlockWalls(int par1, Block par2Block) {
		super(par1, par2Block);
		this.setUnlocalizedName("atum:walls");
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return this.icon[meta];
	}

	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
		return side == ForgeDirection.UP;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.icon = new Icon[4];
		this.icon[0] = iconRegister.registerIcon("atum:AtumStone");
		this.icon[1] = iconRegister.registerIcon("atum:AtumCobble");
		this.icon[2] = iconRegister.registerIcon("atum:AtumBrickLarge");
		this.icon[3] = iconRegister.registerIcon("atum:AtumBrickSmall");
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 3));
	}
}
