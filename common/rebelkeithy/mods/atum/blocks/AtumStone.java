package rebelkeithy.mods.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.AtumBlocks;

public class AtumStone extends Block {

	public AtumStone(int par1) {
		super(par1, Material.rock);
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return AtumBlocks.cobble.blockID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Atum.modID + ":AtumStone");
	}
}
