package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IRegistry;
import net.minecraft.dispenser.RegistryDefaulted;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.tileentity.TileEntityBurningTrap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBurningTrap extends BlockContainer {

	public static final IRegistry dispenseBehaviorRegistry = new RegistryDefaulted(new BehaviorDefaultDispenseItem());
	protected Random random = new Random();
	@SideOnly(Side.CLIENT)
	protected Icon fireTrap;

	public BlockBurningTrap(int par1) {
		super(par1, Material.rock);
		this.setUnlocalizedName("burningTrap");
		this.setCreativeTab(CreativeTabs.tabRedstone);
		this.setHardness(-1.0F);
	}

	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockId(par2, par3 + 1, par4) == AtumBlocks.BLOCK_LARGEBRICK.blockID && par1World.getBlockMetadata(par2, par3 + 1, par4) == 1 ? -1.0F : super.blockHardness;
	}

	@Override
	public int tickRate(World par1World) {
		return 4;
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDispenserDefaultDirection(par1World, par2, par3, par4);
	}

	private void setDispenserDefaultDirection(World par1World, int par2, int par3, int par4) {
		if(!par1World.isRemote) {
			int l = par1World.getBlockId(par2, par3, par4 - 1);
			int i1 = par1World.getBlockId(par2, par3, par4 + 1);
			int j1 = par1World.getBlockId(par2 - 1, par3, par4);
			int k1 = par1World.getBlockId(par2 + 1, par3, par4);
			byte b0 = 3;
			if(Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1]) {
				b0 = 3;
			}

			if(Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l]) {
				b0 = 2;
			}

			if(Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1]) {
				b0 = 5;
			}

			if(Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1]) {
				b0 = 4;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}

	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2) {
		if(par2 == 0) {
			par2 = 3;
		}

		int k = par2 & 7;
		return par1 == k ? (k != 1 && k != 0 ? this.fireTrap : this.fireTrap) : (k != 1 && k != 0 ? (par1 != 1 && par1 != 0 ? super.blockIcon : this.blockIcon) : this.blockIcon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("atum:TrapSide");
		this.fireTrap = par1IconRegister.registerIcon("atum:TrapFire");
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if(par1World.isRemote) {
			return true;
		} else {
			TileEntityBurningTrap tileEntityBurningTrap = (TileEntityBurningTrap) par1World.getBlockTileEntity(par2, par3, par4);
			if(tileEntityBurningTrap != null) {
				;
			}

			return true;
		}
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		boolean flag = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) || par1World.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4);
		int i1 = par1World.getBlockMetadata(par2, par3, par4);
		boolean flag1 = (i1 & 8) != 0;
		if(flag && !flag1) {
			par1World.scheduleBlockUpdate(par2, par3, par4, super.blockID, this.tickRate(par1World));
			par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 | 8, 4);
		} else if(!flag && flag1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 & -9, 4);
		}

	}

	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityBurningTrap();
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
		int l = BlockPistonBase.determineOrientation(par1World, par2, par3, par4, par5EntityLiving);
		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
	}

	public static EnumFacing getFacing(int par0) {
		return EnumFacing.getFront(par0 & 7);
	}

}
