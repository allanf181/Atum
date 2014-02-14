package com.teammetallurgy.atum.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.teammetallurgy.atum.blocks.BlockPortal;
import com.teammetallurgy.atum.blocks.AtumBlocks;

public class ItemScarab extends Item {

	public ItemScarab(int id) {
		super(id);
		super.maxStackSize = 1;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10) {
		int blockID = par3World.getBlockId(x, y, z);
		if(blockID == Block.sandStone.blockID) {
			if(!((BlockPortal) AtumBlocks.BLOCK_PORTAL).tryToCreatePortal(par3World, x, y, z)) {
				if(par2EntityPlayer.capabilities.isCreativeMode) {
					for(int x1 = -2; x1 < 3; x1++) {
						for(int z1 = -2; z1 < 3; z1++) {
							par3World.setBlock(x + x1, y, z + z1, Block.sandStone.blockID);
						}
					}
					for(int x1 = -2; x1 < 3; x1++) {
						for(int z1 = -2; z1 < 3; z1++) {
							if(x1 + x == x + 2 || z1 + z == z + 2 || x1 + x == x - 2 || z1 + z == z - 2) {
								par3World.setBlock(x + x1, y + 1, z + z1, Block.sandStone.blockID);
							}
						}
					}
					for(int y1 = 2; y1 < 4; y1++) {
						for(int x1 = -2; x1 < 3; x1++) {
							for(int z1 = -2; z1 < 3; z1++) {
								if((x1 + x == x + 2 && z1 + z == z + 2) || (x1 + x == x - 2 && z1 + z == z + 2) || (x1 + x == x + 2 && z1 + z == z - 2) || (x1 + x == x - 2 && z1 + z == z - 2)) {
									par3World.setBlock(x + x1, y + y1, z + z1, Block.sandStone.blockID);
								}
							}
						}
					}
				}
			} else {
				--par2EntityPlayer.getCurrentEquippedItem().stackSize;
			}
		}

		return true;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:Scarab");
	}
}
