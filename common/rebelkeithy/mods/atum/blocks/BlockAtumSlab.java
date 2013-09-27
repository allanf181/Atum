package rebelkeithy.mods.atum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import rebelkeithy.mods.atum.AtumBlocks;

public class BlockAtumSlab extends BlockHalfSlab {
	public static final String[] slabType = { "smooth", "cracked", "largeBrick", "smallBrick" };

	public BlockAtumSlab(int par1, boolean par2, Material mat) {
		super(par1, par2, mat);
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		par2 %= 4;
		if (par2 == 0)
			return AtumBlocks.stone.getIcon(par1, par2 & 0x7);
		if (par2 == 1)
			return AtumBlocks.cobble.getIcon(par1, par2 & 0x7);
		if (par2 == 2) {
			return AtumBlocks.largeBrick.getIcon(par1, par2 & 0x7);
		}
		return AtumBlocks.smallBrick.getIcon(par1, par2 & 0x7);
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return AtumBlocks.slabs.blockID;
	}

	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(AtumBlocks.slabs.blockID, 2, par1 & 0x7);
	}

	public String getFullSlabName(int par1) {
		if ((par1 < 0) || (par1 >= slabType.length)) {
			par1 = 0;
		}

		return super.getUnlocalizedName() + "." + slabType[par1];
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < 4; i++)
			subItems.add(new ItemStack(this, 1, i));
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
	}
}