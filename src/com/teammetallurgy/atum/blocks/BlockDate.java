package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.teammetallurgy.atum.items.AtumItems;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDate extends Block {

	public int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockDate(int par1) {
		super(par1, Material.plants);
		this.setUnlocalizedName("date");
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
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		if(world.getBlockId(x, y + 1, z) != AtumBlocks.BLOCK_LEAVES.blockID && !world.isRemote) {
			EntityItem entityItem = new EntityItem(world, (double) x, (double) y, (double) z, new ItemStack(AtumItems.ITEM_DATE.itemID, 0, this.quantityDropped(new Random())));
			entityItem.dropItem(AtumItems.ITEM_DATE.itemID, this.quantityDropped(new Random()));
			world.setBlockToAir(x, y, z);
		}

	}

	@Override
	public int idDropped(int par1, Random rand, int par3) {
		return AtumItems.ITEM_DATE.itemID;
	}

	@Override
	public int quantityDropped(Random rand) {
		return rand.nextInt(3) + 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return AtumItems.ITEM_DATE.itemID;
	}

}
