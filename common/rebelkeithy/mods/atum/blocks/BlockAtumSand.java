package rebelkeithy.mods.atum.blocks;

import rebelkeithy.mods.atum.Atum;
import net.minecraft.block.BlockSand;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockAtumSand extends BlockSand {

	public BlockAtumSand(int par1) {
		super(par1);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Atum.modID + ":AtumSand");
	}
}
