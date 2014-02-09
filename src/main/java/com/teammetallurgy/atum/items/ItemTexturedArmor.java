package com.teammetallurgy.atum.items;

import javax.swing.Icon;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemTexturedArmor extends ItemArmor {

	private String textureFile;
	private Item repairItem;
	private Icon[] armour;

	public ItemTexturedArmor(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par2EnumArmorMaterial, par3, par4);
	}

	public ItemTexturedArmor setRepairItem(Item item) {
		this.repairItem = item;
		return this;
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == this.repairItem;
	}

	public ItemTexturedArmor setTextureFile(String filename) {
		this.textureFile = filename;
		return this;
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, int layer) {
		return "atum:textures/armor/" + this.textureFile + ".png";
	}

}
