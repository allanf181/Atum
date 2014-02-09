package com.teammetallurgy.atum.blocks;

import java.util.Random;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import com.teammetallurgy.atum.items.AtumItems;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPapyrus extends Block implements IPlantable {
	Icon iconPapyrus;
	Icon iconPapyrusTop;
	public int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockPapyrus(int par1) {
		super(par1, Material.plants);
		this.setUnlocalizedName("atum:papyrus");
		this.setHardness(0.0F);
		this.setStepSound(Block.soundGrassFootstep);
		float f = 0.375F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		this.setTickRandomly(true);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if(par5EntityPlayer.capabilities.isCreativeMode) {
			this.updateTick(par1World, par2, par3, par4, new Random());
		}

		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if((double) par5Random.nextFloat() <= 0.75D) {
			if(par1World.isAirBlock(par2, par3 + 1, par4)) {
				int l;
				for(l = 1; par1World.getBlockId(par2, par3 - l, par4) == super; ++l) {
					;
				}

				if(l < 5) {
					int i1 = par1World.getBlockMetadata(par2, par3, par4);
					byte reqHeight = 0;
					if(l == 1) {
						reqHeight = 2;
					} else if(l == 2) {
						reqHeight = 4;
					} else if(l == 3) {
						reqHeight = 8;
					} else if(l == 4) {
						reqHeight = 15;
					}

					if(i1 >= reqHeight) {
						par1World.setBlock(par2, par3 + 1, par4, super);
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 4);
					} else {
						par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 + 1, 4);
					}
				}
			}

		}
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return this.iconPapyrus;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		boolean top = par1IBlockAccess.getBlockId(par2, par3 + 1, par4) != super;
		return top ? this.iconPapyrusTop : this.iconPapyrus;
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		Block block = Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
		return block != null && block.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this);
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		this.checkBlockCoordValid(par1World, par2, par3, par4);
	}

	protected final void checkBlockCoordValid(World par1World, int par2, int par3, int par4) {
		if(!this.canBlockStay(par1World, par2, par3, par4)) {
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockToAir(par2, par3, par4);
		}

	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
		return this.canPlaceBlockAt(par1World, par2, par3, par4);
	}

	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
		int plantID = plant.getPlantID(world, x, y + 1, z);
		EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);
		if(plantID == this) {
			return true;
		} else if(plantType != EnumPlantType.Beach) {
			return false;
		} else {
			boolean isBeach = super == Block.grass || super == Block.dirt || super == Block.sand;
			boolean hasWater = world.getBlockMaterial(x - 1, y, z) == Material.water || world.getBlockMaterial(x + 1, y, z) == Material.water || world.getBlockMaterial(x, y, z - 1) == Material.water || world.getBlockMaterial(x, y, z + 1) == Material.water;
			return isBeach && hasWater;
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return AtumItems.papyrusPlant.itemID;
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
	public int getRenderType() {
		return renderID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return AtumItems.papyrusPlant.itemID;
	}

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Beach;
	}

	@Override
	public int getPlantID(World world, int x, int y, int z) {
		return this;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.iconPapyrus = par1IconRegister.registerIcon("atum:AtumPapyrus");
		this.iconPapyrusTop = par1IconRegister.registerIcon("atum:AtumPapyrusTop");
	}
}