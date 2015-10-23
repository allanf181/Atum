package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockSlab extends ItemSlab {
    public static final String[] types = {"smooth", "cracked", "largeBrick", "smallBrick"};

    public ItemBlockSlab(Block baseBlock) {
        super(baseBlock, AtumBlocks.BLOCK_SLABS, AtumBlocks.BLOCK_DOUBLESLAB, false);
        this.setHasSubtypes(true);
    }
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
    	for(int i = 0;i< types.length;i++){
    		list.add(new ItemStack(this, 1, i));
    	}
    }

    @Override
    public int getMetadata(int meta) {
    	return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return "tile." + types[par1ItemStack.getItemDamage()] + "Slab";
    }
}