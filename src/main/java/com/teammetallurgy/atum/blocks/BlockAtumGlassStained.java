package com.teammetallurgy.atum.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.utils.Constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAtumGlassStained extends BlockBreakable {

    private IIcon[] icons = new IIcon[16];
    private String[] colours = Constants.COLOURS;

    public BlockAtumGlassStained(String textureName) {
        super(textureName, Material.glass, false);
        this.setBlockTextureName(textureName);
        this.setStepSound(soundTypeGlass);
        this.setHardness(0.3F);
        this.setCreativeTab(Atum.creativeTab);
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int meta = 0; meta < colours.length; meta++) {
            list.add(new ItemStack(this, 1, meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        for (int meta = 0; meta < colours.length; meta++){
            icons[meta] = register.registerIcon(getTextureName() + colours[meta]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        
        if (meta < icons.length) {
            return icons[meta];
        }
        
        return icons[0];
    }

}
