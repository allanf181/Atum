package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockAtumStairs extends BlockStairs {

    protected BlockAtumStairs(Block par2Block, int par3) {
        super(par2Block, par3);
        this.useNeighborBrightness = true;
    }

}
