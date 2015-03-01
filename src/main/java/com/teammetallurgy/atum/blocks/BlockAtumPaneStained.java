package com.teammetallurgy.atum.blocks;

import java.util.List;

import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.utils.Constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAtumPaneStained extends BlockStainedGlassPane {

    private IIcon[] faceIcons = new IIcon[16];
    private IIcon[] edgeIcons = new IIcon[16];
    private String[] colours = Constants.COLOURS;
    
    public BlockAtumPaneStained(String faceTexture, String edgeTexture) {
        super();
        this.setBlockTextureName(faceTexture);
        this.setHardness(0.3F);
        this.setStepSound(soundTypeGlass);
        this.setCreativeTab(Atum.creativeTab);
    }
    
    @Override
    public int damageDropped(int meta) {
        return meta;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int meta = 0; meta < colours.length; meta++){
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
            faceIcons[meta] = register.registerIcon(getTextureName() + colours[meta]);
            edgeIcons[meta] = register.registerIcon("atum:thinglass_top");
        }
        
        blockIcon = register.registerIcon (getTextureName());
    }
    
    // Retrieves face icon 
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon func_149735_b(int side, int meta) {
        if ( meta < colours.length) {
            return faceIcons[meta];
        }
        return faceIcons[0];
    }
    
    // gets edge icon
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon func_150104_b(int meta) {
        if ( meta < colours.length) {
            return edgeIcons[meta];
        }
        return edgeIcons[0];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.func_149735_b(side, meta);
    }
    

}
