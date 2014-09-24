package com.teammetallurgy.atum.world.decorators;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenPalm extends WorldGenerator {

    private final int minTreeHeight;
    private final int metaWood;
    private final int metaLeaves;

    public WorldGenPalm(boolean par1) {
        this(par1, 5, 0, 0);
    }

    public WorldGenPalm(boolean par1, int par2, int par3, int par4) {
        super(par1);
        this.minTreeHeight = par2;
        this.metaWood = par3;
        this.metaLeaves = par4;
    }

    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
        int l = par2Random.nextInt(3) + this.minTreeHeight;
        boolean flag = true;
        Block blocks = par1World.getBlock(par3, par4 - 1, par5);
        if ((blocks == AtumBlocks.BLOCK_SAND || blocks == AtumBlocks.BLOCK_FERTILESOIL || blocks == Blocks.dirt) && par4 >= 1 && par4 + l + 1 <= 256) {
            int i1;
            int j1;
            Block k1;
            for (i1 = par4; i1 <= par4 + 1 + l; ++i1) {
                byte b0 = 1;
                if (i1 == par4) {
                    b0 = 0;
                }

                if (i1 >= par4 + 1 + l - 2) {
                    b0 = 2;
                }

                for (int soil = par3 - b0; soil <= par3 + b0 && flag; ++soil) {
                    for (j1 = par5 - b0; j1 <= par5 + b0 && flag; ++j1) {
                        if (i1 >= 0 && i1 < 256) {
                            k1 = par1World.getBlock(soil, i1, j1);
                            if (k1 != null && !k1.isLeaves(par1World, soil, i1, j1) && k1 != AtumBlocks.BLOCK_FERTILESOIL && k1 != Blocks.dirt && !k1.isWood(par1World, soil, i1, j1)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                Block var21 = par1World.getBlock(par3, par4 - 1, par5);
                if (par4 >= 256 - l - 1) {
                    return false;
                } else {
                    if (var21 != null) {
                        var21.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
                    }

                    boolean var20 = true;
                    boolean var22 = false;
                    this.spawnLeaf(par1World, par3, par4 + l + 1, par5);

                    for (int block = -1; block <= 1; ++block) {
                        for (int z = -1; z <= 1; ++z) {
                            if (block != 0 || z != 0) {
                                this.spawnLeaf(par1World, par3 + block, par4 + l, par5 + z);
                            }
                        }
                    }

                    this.spawnLeaf(par1World, par3 + 2, par4 + l, par5);
                    this.spawnLeaf(par1World, par3 - 2, par4 + l, par5);
                    this.spawnLeaf(par1World, par3, par4 + l, par5 + 2);
                    this.spawnLeaf(par1World, par3, par4 + l, par5 - 2);
                    this.spawnLeaf(par1World, par3, par4 + l - 1, par5 - 2);
                    this.spawnLeaf(par1World, par3, par4 + l - 1, par5 + 2);
                    this.spawnLeaf(par1World, par3 + 2, par4 + l - 1, par5);
                    this.spawnLeaf(par1World, par3 - 2, par4 + l - 1, par5);
                    this.spawnLeaf(par1World, par3, par4 + l - 1, par5 - 3);
                    this.spawnLeaf(par1World, par3, par4 + l - 1, par5 + 3);
                    this.spawnLeaf(par1World, par3 + 3, par4 + l - 1, par5);
                    this.spawnLeaf(par1World, par3 - 3, par4 + l - 1, par5);
                    if (par2Random.nextInt(100) < 15) {
                        par1World.setBlock(par3 + 1, par4 + l - 1, par5, AtumBlocks.BLOCK_DATEBLOCK, 0, 2);
                    }

                    if (par2Random.nextInt(100) < 15) {
                        par1World.setBlock(par3 - 1, par4 + l - 1, par5, AtumBlocks.BLOCK_DATEBLOCK, 0, 2);
                    }

                    if (par2Random.nextInt(100) < 15) {
                        par1World.setBlock(par3, par4 + l - 1, par5 + 1, AtumBlocks.BLOCK_DATEBLOCK, 0, 2);
                    }

                    if (par2Random.nextInt(100) < 15) {
                        par1World.setBlock(par3, par4 + l - 1, par5 - 1, AtumBlocks.BLOCK_DATEBLOCK, 0, 2);
                    }

                    for (j1 = 0; j1 <= l; ++j1) {
                        Block var23 = par1World.getBlock(par3, par4 + j1, par5);
                        if (var23 == null || var23.isLeaves(par1World, par3, par4 + j1, par5)) {
                            this.setBlockAndNotifyAdequately(par1World, par3, par4 + j1, par5, AtumBlocks.BLOCK_LOG, this.metaWood);
                        }
                    }

                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public void spawnLeaf(World par1World, int x, int y, int z) {
        Block block = par1World.getBlock(x, y, z);
        if (block == null || block.canBeReplacedByLeaves(par1World, x, y, z)) {
            this.setBlockAndNotifyAdequately(par1World, x, y, z, AtumBlocks.BLOCK_LEAVES, this.metaLeaves);
        }

    }
}
