package com.teammetallurgy.atum.blocks;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityChestSpawner;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChestSpawner extends BlockContainer {

	protected BlockChestSpawner(int par1) {
		super(par1, Material.wood);
		this.setUnlocalizedName("atum:chestSpawner");
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Block.chest.blockID;
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
		return 22;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
		int l = par1World.getBlockId(par2, par3, par4 - 1);
		int i1 = par1World.getBlockId(par2, par3, par4 + 1);
		int j1 = par1World.getBlockId(par2 - 1, par3, par4);
		int k1 = par1World.getBlockId(par2 + 1, par3, par4);
		byte b0 = 0;
		int l1 = MathHelper.floor_double((double) (par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		if(l1 == 0) {
			b0 = 2;
		}

		if(l1 == 1) {
			b0 = 5;
		}

		if(l1 == 2) {
			b0 = 3;
		}

		if(l1 == 3) {
			b0 = 4;
		}

		if(l != blockID && i1 != blockID && j1 != blockID && k1 != blockID) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
		} else {
			if((l == blockID || i1 == blockID) && (b0 == 4 || b0 == 5)) {
				if(l == blockID) {
					par1World.setBlockMetadataWithNotify(par2, par3, par4 - 1, b0, 3);
				} else {
					par1World.setBlockMetadataWithNotify(par2, par3, par4 + 1, b0, 3);
				}

				par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
			}

			if((j1 == blockID || k1 == blockID) && (b0 == 2 || b0 == 3)) {
				if(j1 == blockID) {
					par1World.setBlockMetadataWithNotify(par2 - 1, par3, par4, b0, 3);
				} else {
					par1World.setBlockMetadataWithNotify(par2 + 1, par3, par4, b0, 3);
				}

				par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
			}
		}

		if(par6ItemStack.hasDisplayName()) {
			((TileEntityChestSpawner) par1World.getBlockTileEntity(par2, par3, par4)).setChestGuiName(par6ItemStack.getDisplayName());
		}

	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
		par1World.func_96440_m(par2, par3, par4, par5);
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if(par1World.isRemote) {
			return true;
		} else {
			IInventory iinventory = this.getInventory(par1World, par2, par3, par4);
			if(iinventory != null) {
				par5EntityPlayer.displayGUIChest(iinventory);
			}

			return true;
		}
	}

	public IInventory getInventory(World par1World, int par2, int par3, int par4) {
		TileEntityChestSpawner object = (TileEntityChestSpawner) par1World.getBlockTileEntity(par2, par3, par4);
		return object == null ? null : (par1World.isBlockSolidOnSide(par2, par3 + 1, par4, ForgeDirection.DOWN) ? null : (isOcelotBlockingChest(par1World, par2, par3, par4) ? null : (IInventory) object));
	}

	@Override
	public TileEntity createNewTileEntity(World par1World) {
		TileEntityChestSpawner TileEntityChestSpawner = new TileEntityChestSpawner();
		return TileEntityChestSpawner;
	}

	@Override
	public boolean canProvidePower() {
		return true;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		if(!this.canProvidePower()) {
			return 0;
		} else {
			int i1 = ((TileEntityChestSpawner) par1IBlockAccess.getBlockTileEntity(par2, par3, par4)).numUsingPlayers;
			return MathHelper.clamp_int(i1, 0, 15);
		}
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return par5 == 1 ? this.isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5) : 0;
	}

	public static boolean isOcelotBlockingChest(World par0World, int par1, int par2, int par3) {
		Iterator iterator = par0World.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().getAABB((double) par1, (double) (par2 + 1), (double) par3, (double) (par1 + 1), (double) (par2 + 2), (double) (par3 + 1))).iterator();

		while(iterator.hasNext()) {
			EntityOcelot entityocelot1 = (EntityOcelot) iterator.next();
			if(entityocelot1.isSitting()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
		return Container.calcRedstoneFromInventory(this.getInventory(par1World, par2, par3, par4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("wood");
	}
}