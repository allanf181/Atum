package com.teammetallurgy.atum.items.artifacts;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

import com.teammetallurgy.atum.items.AtumItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemOsirisWill extends ItemSword {

	public ItemOsirisWill(ToolMaterial par2ToolMaterial) {
		super(par2ToolMaterial);
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	// @Override
	// public float getDamageVsEntity(Entity par1Entity, ItemStack stack) {
	// int damage = 0;
	//
	// for (int i = 0; i < 4; ++i) {
	// damage = (int) ((double) damage + Math.random() * 4.0D + 1.0D);
	// }
	// return super.getDamageVsEntity(par1Entity, stack) + damage;
	// }

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase mob, EntityLivingBase player) {
		if (!mob.isEntityAlive() && Math.random() < 0.5D && mob.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
			mob.dropItem(AtumItems.ITEM_ECTOPLASM, 1);
		}

		return super.hitEntity(par1ItemStack, mob, player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal(this.getUnlocalizedName() + ".line1"));
			par3List.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal(this.getUnlocalizedName() + ".line2"));
		} else {
			par3List.add(StatCollector.translateToLocal(this.getUnlocalizedName() + ".line3") + " " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == Items.diamond;
	}

	@Override
	public void registerIcons(IIconRegister par1IIconRegister) {
		this.itemIcon = par1IIconRegister.registerIcon("atum:OsirisWill");
	}
}
