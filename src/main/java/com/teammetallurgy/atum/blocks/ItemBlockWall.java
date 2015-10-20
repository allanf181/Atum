package com.teammetallurgy.atum.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockWall extends ItemBlock {
	public static final String[] types = { "smooth", "cracked", "largeBrick", "smallBrick" };

	public ItemBlockWall(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0;i<types.length;i++){
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return "tile." + types[par1ItemStack.getItemDamage()] + "Wall";
	}

}
