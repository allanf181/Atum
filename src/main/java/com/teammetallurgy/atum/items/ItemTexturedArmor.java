package com.teammetallurgy.atum.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemTexturedArmor extends ItemArmor {

	private String textureFile;
	private Item repairItem = null;
	private IIcon[] armour;

	public ItemTexturedArmor(ArmorMaterial par2ArmorMaterial, int par3, int par4) {
		super(par2ArmorMaterial, par3, par4);
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
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "atum:textures/armor/" + this.textureFile + ".png";
	}

}
