package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
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
	IIcon iconPapyrus;
	IIcon iconPapyrusTop;
	public int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockPapyrus() {
		super(Material.plants);
		this.setBlockName("papyrus");
		this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeGrass);
		float f = 0.375F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		this.setTickRandomly(true);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (par5EntityPlayer.capabilities.isCreativeMode) {
			this.updateTick(par1World, par2, par3, par4, new Random());
		}

		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if ((double) par5Random.nextFloat() <= 0.75D) {
			if (par1World.isAirBlock(par2, par3 + 1, par4)) {
				int l;
				for (l = 1; par1World.getBlock(par2, par3 - l, par4) == this; ++l) {
					;
				}

				if (l < 5) {
					int i1 = par1World.getBlockMetadata(par2, par3, par4);
					byte reqHeight = 0;
					if (l == 1) {
						reqHeight = 2;
					} else if (l == 2) {
						reqHeight = 4;
					} else if (l == 3) {
						reqHeight = 8;
					} else if (l == 4) {
						reqHeight = 15;
					}

					if (i1 >= reqHeight) {
						par1World.setBlock(par2, par3 + 1, par4, this);
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 4);
					} else {
						par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 + 1, 4);
					}
				}
			}

		}
	}

	@Override
	public IIcon getIcon(int par1, int par2) {
		return this.iconPapyrus;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		boolean top = par1IBlockAccess.getBlock(par2, par3 + 1, par4) != this;
		return top ? this.iconPapyrusTop : this.iconPapyrus;
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		Block block = par1World.getBlock(par2, par3 - 1, par4);
		return block != null && block.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this);
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
		this.checkBlockCoordValid(par1World, par2, par3, par4);
	}

	protected final void checkBlockCoordValid(World par1World, int par2, int par3, int par4) {
		if (!this.canBlockStay(par1World, par2, par3, par4)) {
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockToAir(par2, par3, par4);
		}

	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
		return this.canPlaceBlockAt(par1World, par2, par3, par4);
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
		EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);
		if (plant.getPlant(world, x, y + 1, z) == this) {
			return true;
		} else if (plantType != EnumPlantType.Beach) {
			return false;
		} else {
			boolean isBeach = this == Blocks.dirt || (Block)this == Blocks.sand;
			boolean hasWater = world.getBlock(x - 1, y, z).getMaterial() == Material.water || world.getBlock(x + 1, y, z).getMaterial() == Material.water || world.getBlock(x, y, z - 1).getMaterial() == Material.water || world.getBlock(x, y, z + 1).getMaterial() == Material.water;
			return isBeach && hasWater;
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
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
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return AtumItems.papyrusPlant;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.iconPapyrus = par1IIconRegister.registerIcon("atum:AtumPapyrus");
		this.iconPapyrusTop = par1IIconRegister.registerIcon("atum:AtumPapyrusTop");
	}

	@Override
	public Block getPlant(IBlockAccess arg0, int arg1, int arg2, int arg3) {
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess arg0, int arg1, int arg2, int arg3) {
		return EnumPlantType.Beach;
	}
}