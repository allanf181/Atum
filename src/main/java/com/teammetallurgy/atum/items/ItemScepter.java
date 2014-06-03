package com.teammetallurgy.atum.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSword;

public class ItemScepter extends ItemSword {

	public ItemScepter(ToolMaterial par2ToolMaterial) {
		super(par2ToolMaterial);
	}

	@Override
	public void registerIcons(IIconRegister par1IIconRegister) {
		this.itemIcon = par1IIconRegister.registerIcon("atum:Scepter");
	}
}
