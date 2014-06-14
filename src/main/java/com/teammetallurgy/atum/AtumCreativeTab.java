package com.teammetallurgy.atum;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.teammetallurgy.atum.items.AtumItems;

public class AtumCreativeTab extends CreativeTabs {

	public AtumCreativeTab() {
		super("atum");
	}

	@Override
	public Item getTabIconItem() {
		return AtumItems.ITEM_SCARAB;
	}
}
