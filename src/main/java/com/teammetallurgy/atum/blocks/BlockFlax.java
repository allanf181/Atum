package com.teammetallurgy.atum.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

import com.teammetallurgy.atum.items.AtumItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFlax extends BlockBush {
	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	protected BlockFlax() {
		super();
		this.setBlockName("flax");
		this.setTickRandomly(true);
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		this.setCreativeTab((CreativeTabs) null);
		this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeGrass);
		this.disableStats();
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
		if (par1World.getBlockMetadata(par2, par3, par4) >> 3 == 1) {
			return par1World.getBlock(par2, par3 - 1, par4) == AtumBlocks.BLOCK_FERTILESOIL;
		} else {
			Block soil = par1World.getBlock(par2, par3 - 1, par4);
			return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) && soil != null && soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this);
		}
	}

	@Override
	protected boolean canPlaceBlockOn(Block par1) {
		return par1 == Blocks.farmland || par1 == AtumBlocks.BLOCK_FERTILESOILTILLED;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		super.updateTick(par1World, par2, par3, par4, par5Random);
		if ((par1World.getBlockLightValue(par2, par3 + 1, par4) & 273) >= 9) {
			int l = par1World.getBlockMetadata(par2, par3, par4);
			if ((l & 7) < 5) {
				float f = this.getGrowthRate(par1World, par2, par3, par4);
				if (par5Random.nextInt((int) (25.0F / f) + 1) == 0) {
					++l;
					par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
				}
			}
		}

	}

	public void fertilize(World par1World, int par2, int par3, int par4) {
		int l = par1World.getBlockMetadata(par2, par3, par4) + MathHelper.getRandomIntegerInRange(par1World.rand, 2, 3);
		if ((l & 7) > 5) {
			l -= (l & 7) - 5;
		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
	}

	private float getGrowthRate(World par1World, int par2, int par3, int par4) {
		float f = 1.0F;
		Block l = par1World.getBlock(par2, par3, par4 - 1);
		Block i1 = par1World.getBlock(par2, par3, par4 + 1);
		Block j1 = par1World.getBlock(par2 - 1, par3, par4);
		Block k1 = par1World.getBlock(par2 + 1, par3, par4);
		Block l1 = par1World.getBlock(par2 - 1, par3, par4 - 1);
		Block i2 = par1World.getBlock(par2 + 1, par3, par4 - 1);
		Block j2 = par1World.getBlock(par2 + 1, par3, par4 + 1);
		Block k2 = par1World.getBlock(par2 - 1, par3, par4 + 1);
		boolean flag = j1 == this || k1 == this;
		boolean flag1 = l == this || i1 == this;
		boolean flag2 = l1 == this || i2 == this || j2 == this || k2 == this;

		for (int l2 = par2 - 1; l2 <= par2 + 1; ++l2) {
			for (int i3 = par4 - 1; i3 <= par4 + 1; ++i3) {
				Block j3 = par1World.getBlock(l2, par3 - 1, i3);
				float f1 = 0.0F;
				if (j3 != null && j3.canSustainPlant(par1World, l2, par3 - 1, i3, ForgeDirection.UP, this)) {
					f1 = 1.0F;
					if (j3.isFertile(par1World, l2, par3 - 1, i3)) {
						f1 = 3.0F;
					}
				}

				if (l2 != par2 || i3 != par4) {
					f1 /= 4.0F;
				}

				f += f1;
			}
		}

		if (flag2 || flag && flag1) {
			f /= 2.0F;
		}

		return f;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int meta = par2 & 7;
		if (meta < 0 || meta > 5) {
			meta = 5;
		}

		return this.iconArray[meta];
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	protected Item getSeedItem() {
		return AtumItems.flaxSeeds;
	}

	protected Item getCropItem() {
		return AtumItems.flax;
	}

	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
	}

	@Override
	public ArrayList getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList ret = super.getDrops(world, x, y, z, metadata, fortune);
		if ((metadata & 7) >= 5) {
			for (int n = 0; n < 3 + fortune; ++n) {
				if (world.rand.nextInt(15) <= (metadata & 7)) {
					ret.add(new ItemStack(this.getSeedItem(), 1, 0));
				}
			}
		}

		return ret;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return p_149650_1_ == 5 ? this.getCropItem() : this.getSeedItem();
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(this.getSeedItem());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.iconArray = new IIcon[6];

		for (int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = par1IIconRegister.registerIcon("atum:Flax_" + i);
		}

	}
}