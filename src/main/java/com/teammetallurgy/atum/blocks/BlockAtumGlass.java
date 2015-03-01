package com.teammetallurgy.atum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;

import java.util.Random;

public class BlockAtumGlass extends BlockBreakable {

    public BlockAtumGlass(String texture) {
        super(texture, Material.glass, false);
        this.setStepSound(Block.soundTypeGlass);
        this.setHardness(0.3F);
    }

    @Override
    public int quantityDropped(Random rand) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 0;
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
    protected boolean canSilkHarvest() {
        return true;
    }

}
