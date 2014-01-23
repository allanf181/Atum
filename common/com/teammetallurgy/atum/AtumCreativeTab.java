package com.teammetallurgy.atum;

import net.minecraft.creativetab.CreativeTabs;

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
}
