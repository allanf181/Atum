package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAtumPlank extends Block {

    protected BlockAtumPlank() {
        super(Material.wood);
        this.setBlockName("palmPlanks");
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setStepSound(Block.soundTypeWood);
        this.setBlockTextureName("atum:Planks");
    }

}
