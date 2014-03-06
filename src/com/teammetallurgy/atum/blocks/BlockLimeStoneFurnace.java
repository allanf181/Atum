package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.tileentity.TileEntityLimestoneFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLimeStoneFurnace extends BlockFurnace {

	private static boolean keepFurnaceInventory;

	boolean isActive;
	@SideOnly(Side.CLIENT)
	private Icon furnaceFront;

	protected BlockLimeStoneFurnace(int par1, boolean par2) {
		super(par1, par2);
		this.isActive = par2;
		this.setHardness(3.5F);
		this.setStepSound(Block.soundStoneFootstep);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if(par1World.isRemote) {
			return true;
		} else {
			TileEntityLimestoneFurnace TileEntityLimestoneFurnace = (TileEntityLimestoneFurnace) par1World.getBlockTileEntity(par2, par3, par4);

			if(TileEntityLimestoneFurnace != null) {
				par5EntityPlayer.openGui(Atum.instance, 0, par1World, par2, par3, par4);
			}

			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		if(par2 == 0) {
			par2 = 3;
		}

		return par1 == 1 ? this.blockIcon : (par1 == 0 ? this.blockIcon : (par1 != par2 ? this.blockIcon : this.furnaceFront));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("atum:FurnaceTop");
		this.furnaceFront = par1IconRegister.registerIcon(this.isActive ? "atum:FurnaceBurning" : "atum:FurnaceFront");
	}

	@Override
	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityLimestoneFurnace();
	}

	public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4) {
		int l = par1World.getBlockMetadata(par2, par3, par4);
		TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
		keepFurnaceInventory = true;
		if(par0) {
			par1World.setBlock(par2, par3, par4, AtumBlocks.BLOCK_FURNACEBURNING.blockID);
		} else {
			par1World.setBlock(par2, par3, par4, AtumBlocks.BLOCK_FURNACEIDLE.blockID);
		}

		keepFurnaceInventory = false;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
		if(tileentity != null) {
			tileentity.validate();
			par1World.setBlockTileEntity(par2, par3, par4, tileentity);
		}

	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return AtumBlocks.BLOCK_FURNACEIDLE.blockID;
	}
}
