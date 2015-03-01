package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.utils.Constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockStainedGlass extends ItemBlock{

    public ItemBlockStainedGlass(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
    
    @Override
    public int getMetadata(int meta) {
        return meta;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        if (itemStack.getItemDamage() < Constants.COLOURS.length)
            return field_150939_a.getUnlocalizedName() + Constants.COLOURS[itemStack.getItemDamage()];
        
        return field_150939_a.getUnlocalizedName(); 
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        return this.field_150939_a.func_149735_b(2, meta);
    }

}
