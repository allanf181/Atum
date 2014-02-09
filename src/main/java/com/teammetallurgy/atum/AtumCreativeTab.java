package com.teammetallurgy.atum;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.teammetallurgy.atum.items.AtumItems;

public class AtumCreativeTab extends CreativeTabs {

	public AtumCreativeTab() {
		super("Atum");
	}

	@Override
	public String getTranslatedTabLabel() {
		return LocalizationHelper.localize("itemGroup.Atum");
	}

	@Override
	public String getBackgroundImageName() {
		return super.getBackgroundImageName().replace("s.png", "_search.png");
	}

	@Override
	public boolean hasSearchBar() {
		return true;
	}
	
	@Override
	public Item getTabIconItem() {
		return AtumItems.ITEM_SCARAB;
	}
}
