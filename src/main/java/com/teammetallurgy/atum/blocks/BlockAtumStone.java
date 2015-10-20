package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockAtumStone extends BlockStone {

    public BlockAtumStone() {
        super();
        this.setBlockName("stone");
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_) {
        return Item.getItemFromBlock(AtumBlocks.BLOCK_LIMESTONECOBBLE);
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = par1IIconRegister.registerIcon("atum:AtumStone");
    }

}
