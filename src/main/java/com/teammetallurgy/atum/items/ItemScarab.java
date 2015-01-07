package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemScarab extends Item {

    public ItemScarab() {
        super();
        super.maxStackSize = 1;
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (AtumConfig.ALLOW_CREATION || par2EntityPlayer.capabilities.isCreativeMode) {
            Block block = par3World.getBlock(x, y, z);
            Block temp = null;
            if (block == Blocks.sandstone || block == AtumBlocks.BLOCK_LARGEBRICK) {
                temp = block;
            }
            if (temp != null) {
                for (int x1 = -1; x1 < 1; x1++) {
                    for (int z1 = -1; z1 < 1; z1++) {
                        if (AtumBlocks.BLOCK_PORTAL.tryToCreatePortal(par3World, x1 + x, y, z1 + z, temp)) {
                            --par2EntityPlayer.getCurrentEquippedItem().stackSize;
                            return true;
                        }
                    }
                }

                if (par2EntityPlayer.capabilities.isCreativeMode) {
                    for (int x1 = -2; x1 < 3; x1++) {
                        for (int z1 = -2; z1 < 3; z1++) {
                            for (int y1 = 0; y1 < 2; y1++) {
                                par3World.setBlock(x + x1, y + y1, z + z1, temp);
                            }
                        }
                    }

                    for (int x1 = -1; x1 < 2; x1++) {
                        for (int z1 = -1; z1 < 2; z1++) {
                            par3World.setBlockToAir(x + x1, y + 1, z + z1);
                        }
                    }

                    for (int y1 = 2; y1 < 4; y1++) {
                        par3World.setBlock(x - 2, y + y1, z - 2, temp);
                        par3World.setBlock(x + 2, y + y1, z - 2, temp);
                        par3World.setBlock(x - 2, y + y1, z + 2, temp);
                        par3World.setBlock(x + 2, y + y1, z + 2, temp);
                    }
                    AtumBlocks.BLOCK_PORTAL.tryToCreatePortal(par3World, x, y, z, temp);
                }
            }
        } else {
            par2EntityPlayer.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("chat.atum.disabled")));
        }

        return true;
    }

    @Override
    public void registerIcons(IIconRegister par1IIconRegister) {
        this.itemIcon = par1IIconRegister.registerIcon("atum:Scarab");
    }
}
