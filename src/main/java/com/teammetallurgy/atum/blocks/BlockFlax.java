package com.teammetallurgy.atum.blocks;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

import com.teammetallurgy.atum.items.AtumItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFlax extends BlockFlower {
	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;

	protected BlockFlax(int par1) {
		super(par1);
		this.setUnlocalizedName("atum:flax");
		this.setTickRandomly(true);
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		this.setCreativeTab((CreativeTabs) null);
		this.setHardness(0.0F);
		this.setStepSound(Block.soundGrassFootstep);
		this.disableStats();
	}

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
		if(par1World.getBlockMetadata(par2, par3, par4) >> 3 == 1) {
			return par1World.getBlockId(par2, par3 - 1, par4) == AtumBlocks.BLOCK_FERTILESOIL;
		} else {
			Block soil = Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
			return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) && soil != null && soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this);
		}
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int par1) {
		return par1 == Block.tilledField || par1 == AtumBlocks.BLOCK_FERTILESOILTILLED;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		super.updateTick(par1World, par2, par3, par4, par5Random);
		if((par1World.getBlockLightValue(par2, par3 + 1, par4) & 273) >= 9) {
			int l = par1World.getBlockMetadata(par2, par3, par4);
			if((l & 7) < 5) {
				float f = this.getGrowthRate(par1World, par2, par3, par4);
				if(par5Random.nextInt((int) (25.0F / f) + 1) == 0) {
					++l;
					par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
				}
			}
		}

	}

	public void fertilize(World par1World, int par2, int par3, int par4) {
		int l = par1World.getBlockMetadata(par2, par3, par4) + MathHelper.getRandomIntegerInRange(par1World.rand, 2, 3);
		if((l & 7) > 5) {
			l -= (l & 7) - 5;
		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
	}

	private float getGrowthRate(World par1World, int par2, int par3, int par4) {
		float f = 1.0F;
		int l = par1World.getBlockId(par2, par3, par4 - 1);
		int i1 = par1World.getBlockId(par2, par3, par4 + 1);
		int j1 = par1World.getBlockId(par2 - 1, par3, par4);
		int k1 = par1World.getBlockId(par2 + 1, par3, par4);
		int l1 = par1World.getBlockId(par2 - 1, par3, par4 - 1);
		int i2 = par1World.getBlockId(par2 + 1, par3, par4 - 1);
		int j2 = par1World.getBlockId(par2 + 1, par3, par4 + 1);
		int k2 = par1World.getBlockId(par2 - 1, par3, par4 + 1);
		boolean flag = j1 == super || k1 == super;
		boolean flag1 = l == super || i1 == super;
		boolean flag2 = l1 == super || i2 == super || j2 == super || k2 == super;

		for(int l2 = par2 - 1; l2 <= par2 + 1; ++l2) {
			for(int i3 = par4 - 1; i3 <= par4 + 1; ++i3) {
				int j3 = par1World.getBlockId(l2, par3 - 1, i3);
				float f1 = 0.0F;
				if(Block.blocksList[j3] != null && Block.blocksList[j3].canSustainPlant(par1World, l2, par3 - 1, i3, ForgeDirection.UP, this)) {
					f1 = 1.0F;
					if(Block.blocksList[j3].isFertile(par1World, l2, par3 - 1, i3)) {
						f1 = 3.0F;
					}
				}

				if(l2 != par2 || i3 != par4) {
					f1 /= 4.0F;
				}

				f += f1;
			}
		}

		if(flag2 || flag && flag1) {
			f /= 2.0F;
		}

		return f;
	}

	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		int meta = par2 & 7;
		if(meta < 0 || meta > 5) {
			meta = 5;
		}

		return this.iconArray[meta];
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	protected int getSeedItem() {
		return AtumItems.flaxSeeds.itemID;
	}

	protected int getCropItem() {
		return AtumItems.flax.itemID;
	}

	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
	}

	@Override
	public ArrayList getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList ret = super.getBlockDropped(world, x, y, z, metadata, fortune);
		if((metadata & 7) >= 5) {
			for(int n = 0; n < 3 + fortune; ++n) {
				if(world.rand.nextInt(15) <= (metadata & 7)) {
					ret.add(new ItemStack(this.getSeedItem(), 1, 0));
				}
			}
		}

		return ret;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return par1 == 5 ? this.getCropItem() : this.getSeedItem();
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return this.getSeedItem();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.iconArray = new Icon[6];

		for(int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = par1IconRegister.registerIcon("atum:Flax_" + i);
		}

	}
}