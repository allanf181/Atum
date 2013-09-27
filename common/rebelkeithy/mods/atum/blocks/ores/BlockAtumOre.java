package rebelkeithy.mods.atum.blocks.ores;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.AtumBlocks;

public class BlockAtumOre extends Block {

	public BlockAtumOre(int par1) {
		super(par1, Material.rock);
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return super.blockID == AtumBlocks.coalOre.blockID ? Item.coal.itemID : (super.blockID == AtumBlocks.diamondOre.blockID ? Item.diamond.itemID : (super.blockID == AtumBlocks.lapisOre.blockID ? Item.dyePowder.itemID : super.blockID));
	}

	public int quantityDropped(Random par1Random) {
		return super.blockID == AtumBlocks.lapisOre.blockID ? 4 + par1Random.nextInt(5) : 1;
	}

	public int quantityDroppedWithBonus(int par1, Random par2Random) {
		if (par1 > 0 && super.blockID != this.idDropped(0, par2Random, par1)) {
			int j = par2Random.nextInt(par1 + 2) - 1;
			if (j < 0) {
				j = 0;
			}

			return this.quantityDropped(par2Random) * (j + 1);
		} else {
			return this.quantityDropped(par2Random);
		}
	}

	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
		if (this.idDropped(par5, par1World.rand, par7) != super.blockID) {
			int j1 = 0;
			if (super.blockID == AtumBlocks.coalOre.blockID) {
				j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 0, 2);
			} else if (super.blockID == AtumBlocks.diamondOre.blockID) {
				j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 3, 7);
			} else if (super.blockID == AtumBlocks.lapisOre.blockID) {
				j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);
			}

			this.dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
		}

	}

	public int damageDropped(int par1) {
		return super.blockID == AtumBlocks.lapisOre.blockID ? 4 : 0;
	}

}
