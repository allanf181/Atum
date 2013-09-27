package rebelkeithy.mods.atum.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import rebelkeithy.mods.atum.Atum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAtumWorld extends Block {

	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;

	public BlockAtumWorld(int par1) {
		super(par1, Material.rock);
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return this.iconArray[par2 % this.iconArray.length];
	}

	public int damageDropped(int par1) {
		return par1 == 0 ? 1 : par1;
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int j = 0; j < 13; ++j) {
			par3List.add(new ItemStack(par1, 1, j));
		}

	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.iconArray = new Icon[16];

		for (int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = par1IconRegister.registerIcon("cloth_" + i);
		}

		this.iconArray[0] = par1IconRegister.registerIcon(Atum.modID + ":AtumStone");
		this.iconArray[0] = par1IconRegister.registerIcon(Atum.modID + ":AtumCobble");
		this.iconArray[0] = par1IconRegister.registerIcon(Atum.modID + ":AtumBrickLarge");
		this.iconArray[0] = par1IconRegister.registerIcon(Atum.modID + ":AtumBrickSmall");
		this.iconArray[0] = par1IconRegister.registerIcon(Atum.modID + ":AtumBrickCarved");
		this.iconArray[0] = par1IconRegister.registerIcon(Atum.modID + ":AtumStone");
		this.iconArray[0] = par1IconRegister.registerIcon(Atum.modID + ":AtumStone");
		this.iconArray[0] = par1IconRegister.registerIcon(Atum.modID + ":AtumCobble");
		this.iconArray[0] = par1IconRegister.registerIcon(Atum.modID + ":AtumCobble");
	}
}
