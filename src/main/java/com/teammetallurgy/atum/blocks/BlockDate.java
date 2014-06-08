package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.teammetallurgy.atum.items.AtumItems;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDate extends Block {

	public int renderID = RenderingRegistry.getNextAvailableRenderId();

	public BlockDate() {
		super(Material.plants);
		this.setBlockName("date");
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
	public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_) {
		if (world.getBlock(x, y + 1, z) != AtumBlocks.BLOCK_LEAVES && !world.isRemote) {
			EntityItem entityItem = new EntityItem(world, (double) x, (double) y, (double) z, new ItemStack(AtumItems.ITEM_DATE, 0, this.quantityDropped(new Random())));
			entityItem.dropItem(AtumItems.ITEM_DATE, this.quantityDropped(new Random()));
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return AtumItems.ITEM_DATE;
	}

	@Override
	public int quantityDropped(Random rand) {
		return rand.nextInt(3) + 1;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(AtumItems.ITEM_DATE);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
	}
}
