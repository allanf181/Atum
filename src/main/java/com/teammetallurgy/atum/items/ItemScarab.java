package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.handler.AtumConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
    public boolean hasEffect(ItemStack stack, int pass) {
        return true;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (AtumConfig.ALLOW_CREATION || player.capabilities.isCreativeMode) {
            Block block = world.getBlock(x, y, z);
            Block temp = null;
            if (block == Blocks.sandstone || block == AtumBlocks.BLOCK_LARGEBRICK) {
                temp = block;
            }
            if (temp != null) {
                for (int x1 = -1; x1 < 1; x1++) {
                    for (int z1 = -1; z1 < 1; z1++) {
                        if (world.getBlock(x1 + x, y + 1, z1 + z).getMaterial() == Material.water && world.getBlockMetadata(x1 + x, y + 1, z1 + z) == 0) {
                            if (AtumBlocks.BLOCK_PORTAL.tryToCreatePortal(world, x1 + x, y, z1 + z, temp)) {
                                --player.getCurrentEquippedItem().stackSize;
                                return true;
                            }
                        }
                    }
                }

                if (player.capabilities.isCreativeMode) {
                    for (int x1 = -2; x1 < 3; x1++) {
                        for (int z1 = -2; z1 < 3; z1++) {
                            for (int y1 = 0; y1 < 2; y1++) {
                                world.setBlock(x + x1, y + y1, z + z1, temp);
                            }
                        }
                    }

                    for (int x1 = -1; x1 < 2; x1++) {
                        for (int z1 = -1; z1 < 2; z1++) {
                            world.setBlockToAir(x + x1, y + 1, z + z1);
                        }
                    }

                    for (int y1 = 2; y1 < 4; y1++) {
                        world.setBlock(x - 2, y + y1, z - 2, temp);
                        world.setBlock(x + 2, y + y1, z - 2, temp);
                        world.setBlock(x - 2, y + y1, z + 2, temp);
                        world.setBlock(x + 2, y + y1, z + 2, temp);
                    }
                    AtumBlocks.BLOCK_PORTAL.tryToCreatePortal(world, x, y, z, temp);
                }
            }
        } else {
            player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("chat.atum.disabled")));
        }

        return true;
    }

    @Override
    public void registerIcons(IIconRegister IIconRegister) {
        this.itemIcon = IIconRegister.registerIcon("atum:Scarab");
    }
}