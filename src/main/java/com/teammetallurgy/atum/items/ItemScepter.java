package com.teammetallurgy.atum.items;

import net.minecraft.item.ItemSword;

public class ItemScepter extends ItemSword {

	public ItemScepter(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:Scepter");
	}
}
