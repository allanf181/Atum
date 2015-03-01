package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;

public class BlockAtumPane extends BlockPane {

    protected BlockAtumPane(String par2Str, String par3Str) {
        super(par2Str, par3Str, Material.glass, false);
        this.setHardness(0.3F);
        this.setStepSound(Block.soundTypeGlass);
    }

}
