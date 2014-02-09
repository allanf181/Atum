package com.teammetallurgy.atum.items;

import java.util.List;

import javax.swing.Icon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFish extends Item {

	Icon[] icons;

	public ItemFish(int par1) {
		super(par1);
		this.setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return this.icons[par1];
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int i = 0; i < 4; ++i) {
			par3List.add(new ItemStack(super.itemID, 1, i));
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.icons = new Icon[4];

		for(int i = 0; i < 4; ++i) {
			this.icons[i] = par1IconRegister.registerIcon("atum:Fish" + i);
		}
	}
}
