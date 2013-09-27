package rebelkeithy.mods.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.AtumConfig;

public class BlockAtumBrick extends Block {

	Icon[] iconArray;

	public BlockAtumBrick(int par1, Material par2Material) {
		super(par1, par2Material);
	}

	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) == 1 && AtumConfig.protectBlocksInCreative ? false : super.removeBlockByPlayer(world, player, x, y, z);
	}

	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		return world.getBlockMetadata(x, y, z) == 1 ? 80.0F : super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
	}

	public float getBlockHardness(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4) == 1 ? -1.0F : super.blockHardness;
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		if (this.blockID == AtumConfig.largeBrickID) {
			return this.iconArray[0];
		} else if (this.blockID == AtumConfig.smallBrickID) {
			return this.iconArray[1];
		} else {
			return this.iconArray[2];
		}
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		iconArray = new Icon[3];
		this.iconArray[0] = par1IconRegister.registerIcon(Atum.modID + ":AtumBrickLarge");
		this.iconArray[1] = par1IconRegister.registerIcon(Atum.modID + ":AtumBrickSmall");
		this.iconArray[2] = par1IconRegister.registerIcon(Atum.modID + ":AtumBrickCarved");
	}
}
