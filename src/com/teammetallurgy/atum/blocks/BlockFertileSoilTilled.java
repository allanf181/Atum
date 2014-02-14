package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFertileSoilTilled extends Block {
	@SideOnly(Side.CLIENT)
	private Icon farmlandWet;
	@SideOnly(Side.CLIENT)
	private Icon farmlandDry;

	public BlockFertileSoilTilled(int par1) {
		super(par1, Material.ground);
		this.setHardness(0.5F);
		this.setUnlocalizedName("atum:fertileSoilTilled");
		this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
		this.setLightOpacity(255);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int enchanted = (par1World.getBlockMetadata(par2, par3, par4) & 4 & 4) >> 2;
		if(enchanted == 1 && par5Random.nextDouble() > 0.6D) {
			double d0 = par5Random.nextGaussian() * 0.02D;
			double d1 = par5Random.nextGaussian() * 0.02D;
			double d2 = par5Random.nextGaussian() * 0.02D;
			par1World.spawnParticle("happyVillager", (double) ((float) par2 + par5Random.nextFloat()), (double) par3 + (double) par5Random.nextFloat() * this.getBlockBoundsMaxY() * 0.4D + 1.0D, (double) ((float) par4 + par5Random.nextFloat()), d0, d1, d2);
		}

	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return AxisAlignedBB.getAABBPool().getAABB((double) (par2 + 0), (double) (par3 + 0), (double) (par4 + 0), (double) (par2 + 1), (double) (par3 + 1), (double) (par4 + 1));
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return par2 >> 3 == 0 ? (par1 == 1 ? (par2 > 0 ? this.farmlandWet : this.farmlandDry) : AtumBlocks.BLOCK_FERTILESOIL.getIcon(par1, 1)) : Block.tilledField.getIcon(par1, par2);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		if(!this.isWaterNearby(par1World, par2, par3, par4) && !par1World.canLightningStrikeAt(par2, par3 + 1, par4)) {
			if((meta & 3) > 0 && Math.random() > 0.5D) {
				par1World.setBlockMetadataWithNotify(par2, par3, par4, meta - 1, 2);
			} else if(!this.isCropsNearby(par1World, par2, par3, par4)) {
				this.revertToDirt(par1World, par2, par3, par4);
			}
		} else {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta | 3, 2);
		}

		int cropID = par1World.getBlockId(par2, par3 + 1, par4);
		if(cropID != 0) {
			for(int i = 0; i < 2; ++i) {
				Block.blocksList[cropID].updateTick(par1World, par2, par3 + 1, par4, par5Random);
			}
		}

	}

	@Override
	public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6) {
		if(!par1World.isRemote && par1World.rand.nextFloat() < par6 - 0.5F) {
			if(!(par5Entity instanceof EntityPlayer) && !par1World.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
				return;
			}

			this.revertToDirt(par1World, par2, par3, par4);
		}

	}

	private boolean isCropsNearby(World par1World, int par2, int par3, int par4) {
		byte b0 = 0;

		for(int l = par2 - b0; l <= par2 + b0; ++l) {
			for(int i1 = par4 - b0; i1 <= par4 + b0; ++i1) {
				int j1 = par1World.getBlockId(l, par3 + 1, i1);
				Block plant = Block.blocksList[j1];
				if(plant instanceof IPlantable && this.canSustainPlant(par1World, par2, par3, par4, ForgeDirection.UP, (IPlantable) plant)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isWaterNearby(World par1World, int par2, int par3, int par4) {
		for(int l = par2 - 4; l <= par2 + 4; ++l) {
			for(int i1 = par3; i1 <= par3 + 1; ++i1) {
				for(int j1 = par4 - 4; j1 <= par4 + 4; ++j1) {
					if(par1World.getBlockMaterial(l, i1, j1) == Material.water) {
						return true;
					}
				}
			}
		}

		return false;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
		Material material = par1World.getBlockMaterial(par2, par3 + 1, par4);
		if(material.isSolid()) {
			this.revertToDirt(par1World, par2, par3, par4);
		}

	}

	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
		EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);
		return plantType == EnumPlantType.Crop;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return par1 >> 3 == 0 ? AtumBlocks.BLOCK_SAND.blockID : Block.dirt.blockID;
	}

	public void revertToDirt(World world, int x, int y, int z) {
		int type = world.getBlockMetadata(x, y, z) >> 3;
		if(type == 0) {
			world.setBlock(x, y, z, AtumBlocks.BLOCK_FERTILESOIL.blockID);
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		} else {
			world.setBlock(x, y, z, Block.dirt.blockID);
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		int type = par1World.getBlockMetadata(par2, par3, par4) >> 3;
		return type == 0 ? AtumBlocks.BLOCK_SAND.blockID : Block.dirt.blockID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.farmlandWet = par1IconRegister.registerIcon("farmland_wet");
		this.farmlandDry = par1IconRegister.registerIcon("farmland_dry");
	}
}