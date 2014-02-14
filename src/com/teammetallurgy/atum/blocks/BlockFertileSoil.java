package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFertileSoil extends BlockFarmland {
	@SideOnly(Side.CLIENT)
	private Icon iconGrassTop;

	@SideOnly(Side.CLIENT)
	private Icon iconGrassSideOverlay;

	@SideOnly(Side.CLIENT)
	private Icon iconDirt;

	public BlockFertileSoil(int par1) {
		super(par1);
		this.setUnlocalizedName("atum:fertileSoil");
		this.setHardness(0.5F);
		this.setStepSound(Block.soundGrassFootstep);
		setTickRandomly(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		if(par2 == 1) {
			return this.iconDirt;
		}
		return par1 == 0 ? Block.dirt.getBlockTextureFromSide(par1) : par1 == 1 ? this.iconGrassTop : this.blockIcon;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(!par1World.isRemote) {
			if((par1World.getBlockLightValue(par2, par3 + 1, par4) < 4) && (par1World.getBlockLightOpacity(par2, par3 + 1, par4) > 2)) {
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
			} else if(par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9) {
				for(int l = 0; l < 4; l++) {
					int i1 = par2 + par5Random.nextInt(3) - 1;
					int j1 = par3 + par5Random.nextInt(5) - 3;
					int k1 = par4 + par5Random.nextInt(3) - 1;
					int l1 = par1World.getBlockId(i1, j1 + 1, k1);

					if((par1World.getBlockId(i1, j1, k1) == this.blockID) && (par1World.getBlockMetadata(i1, j1, k1) == 1) && (par1World.getBlockLightValue(i1, j1 + 1, k1) >= 4) && (par1World.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)) {
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
					}
				}
			}
		}
	}

	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
		EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);

		if((plant instanceof BlockFlower)) {
			return true;
		}

		switch(plantType.ordinal()) {
		case 1:
			return false;
		case 2:
			return false;
		case 3:
			return false;
		case 4:
			return isBlockSolidOnSide(world, x, y, z, ForgeDirection.UP);
		case 5:
			return true;
		case 6:
			return false;
		case 7:
			boolean hasWater = (world.getBlockMaterial(x - 1, y, z) == Material.water) || (world.getBlockMaterial(x + 1, y, z) == Material.water) || (world.getBlockMaterial(x, y, z - 1) == Material.water) || (world.getBlockMaterial(x, y, z + 1) == Material.water);

			return hasWater;
		}

		return false;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return AtumBlocks.BLOCK_SAND.blockID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		if(par1IBlockAccess.getBlockMetadata(par2, par3, par4) == 1) {
			return this.iconDirt;
		}

		if(par5 == 1) {
			return this.iconGrassTop;
		}
		if(par5 == 0) {
			return AtumBlocks.BLOCK_SAND.getBlockTextureFromSide(par5);
		}

		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("atum:FertileSoilSide");
		this.iconDirt = par1IconRegister.registerIcon("atum:FertileSoil");
		this.iconGrassTop = par1IconRegister.registerIcon("atum:FertileSoilTop");
		this.iconGrassSideOverlay = par1IconRegister.registerIcon("grass_side_overlay");
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconSideOverlay() {
		return this.iconGrassSideOverlay;
	}
}