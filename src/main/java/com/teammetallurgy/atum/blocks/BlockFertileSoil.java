package com.teammetallurgy.atum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class BlockFertileSoil extends BlockFarmland {
    @SideOnly(Side.CLIENT)
    private IIcon iconGrassTop;

    @SideOnly(Side.CLIENT)
    private IIcon iconGrassSideOverlay;

    @SideOnly(Side.CLIENT)
    private IIcon iconDirt;

    public BlockFertileSoil() {
        super();
        this.setBlockName("fertileSoil");
        this.setHardness(0.5F);
        this.setStepSound(Block.soundTypeGrass);
        setTickRandomly(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        if (par2 == 1) {
            return this.iconDirt;
        }
        return par1 == 0 ? Blocks.dirt.getBlockTextureFromSide(par1) : par1 == 1 ? this.iconGrassTop : this.blockIcon;
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!par1World.isRemote) {
            if ((par1World.getBlockLightValue(par2, par3 + 1, par4) < 4) && (par1World.getBlockLightOpacity(par2, par3 + 1, par4) > 2)) {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
            } else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9) {
                for (int l = 0; l < 4; l++) {
                    int i1 = par2 + par5Random.nextInt(3) - 1;
                    int j1 = par3 + par5Random.nextInt(5) - 3;
                    int k1 = par4 + par5Random.nextInt(3) - 1;
                    Block l1 = par1World.getBlock(i1, j1 + 1, k1);

                    if ((par1World.getBlock(i1, j1, k1) == this) && (par1World.getBlockMetadata(i1, j1, k1) == 1) && (par1World.getBlockLightValue(i1, j1 + 1, k1) >= 4) && (par1World.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)) {
                        par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
                    }
                }
            }
        }
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
        EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);

        if ((plant instanceof BlockFlower)) {
            return true;
        }

        if (plantType == EnumPlantType.Beach) {
                boolean hasWater = (world.getBlock(x - 1, y, z).getMaterial() == Material.water) || (world.getBlock(x + 1, y, z).getMaterial() == Material.water) || (world.getBlock(x, y, z - 1).getMaterial() == Material.water) || (world.getBlock(x, y, z + 1).getMaterial() == Material.water);

                return hasWater;
        }

        return false;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(AtumBlocks.BLOCK_SAND);
    }

    @Override
    public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        if (par1IBlockAccess.getBlockMetadata(par2, par3, par4) == 1) {
            return this.iconDirt;
        }

        if (par5 == 1) {
            return this.iconGrassTop;
        }
        if (par5 == 0) {
            return AtumBlocks.BLOCK_SAND.getBlockTextureFromSide(par5);
        }

        return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = par1IIconRegister.registerIcon("atum:FertileSoilSide");
        this.iconDirt = par1IIconRegister.registerIcon("atum:FertileSoil");
        this.iconGrassTop = par1IIconRegister.registerIcon("atum:FertileSoilTop");
        this.iconGrassSideOverlay = par1IIconRegister.registerIcon("grass_side_overlay");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconSideOverlay() {
        return this.iconGrassSideOverlay;
    }
}