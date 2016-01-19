package com.teammetallurgy.atum.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;

public class BlockDeadwoodLog extends BlockPalmLog {
    
    protected BlockDeadwoodLog() {
        super();
        this.setBlockName("deadwoodLog");
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }
    
    @Override
    public void registerBlockIcons(IIconRegister register) {
        this.tree_top = register.registerIcon("atum:deadwood_top");
        this.tree_side = register.registerIcon("atum:deadwood_side");
    }
}
