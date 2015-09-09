package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

import com.teammetallurgy.atum.items.AtumItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFlax extends BlockCrops {
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    protected BlockFlax() {
        super();
        this.setBlockName("flax");
        this.setTickRandomly(true);
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab((CreativeTabs) null);
        this.setHardness(0.0F);
        this.setStepSound(Block.soundTypeGrass);
        this.disableStats();
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Crop;
    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
        if (par1World.getBlockMetadata(par2, par3, par4) >> 3 == 1) {
            return par1World.getBlock(par2, par3 - 1, par4) == AtumBlocks.BLOCK_FERTILESOIL;
        } else {
            Block soil = par1World.getBlock(par2, par3 - 1, par4);
            return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) && soil != null && soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this);
        }
    }

    @Override
    protected boolean canPlaceBlockOn(Block par1) {
        return par1 == Blocks.farmland || par1 == AtumBlocks.BLOCK_FERTILESOILTILLED;
    }


    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        int iconIndex = par2;
        
        if (iconIndex >= 5)
            iconIndex-- ;
        if (iconIndex >= 2)
            iconIndex-- ;
        
        if (iconIndex < 0 || iconIndex > 5) {
            iconIndex = 5;
        }

        return this.iconArray[iconIndex];
    }

    @Override
    public int getRenderType() {
        return 1;
    }
    
    // getSeedItem
    @Override
    protected Item func_149866_i() {
        return AtumItems.ITEM_FLAXSEED;
    }
    
    // getCropItem
    @Override
    protected Item func_149865_P() {
        return AtumItems.ITEM_FLAX;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IIconRegister) {
        this.iconArray = new IIcon[6];

        for (int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = par1IIconRegister.registerIcon("atum:Flax_" + i);
        }

    }
}