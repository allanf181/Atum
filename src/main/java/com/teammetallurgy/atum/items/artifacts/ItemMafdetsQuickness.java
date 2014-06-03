package com.teammetallurgy.atum.items.artifacts;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMafdetsQuickness extends Item {

	public ItemMafdetsQuickness() {
		super();
		this.setMaxDamage(24000);
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if (par3Entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) par3Entity;
			if (player.inventory.armorInventory[1] != null && player.inventory.armorInventory[1].getItem() == this) {
				doEffect(player, player.inventory.armorInventory[1]);
			}
		}

	}

	public void doEffect(EntityPlayer player, ItemStack item) {
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2, 0, false));
		if (!player.capabilities.isCreativeMode) {
			if (item.getItemDamage() == 1) {
				item.damageItem(1, player);
			} else {
				item.setItemDamage(item.getItemDamage() + 1);
			}
		}

	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Swiftness I: You run");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "faster when held");
		} else {
			par3List.add("Swiftness I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

		par3List.add((double) ((par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()) / 12) / 100.0D + " Minutes Remaining");
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == Items.diamond;
	}

	@Override
	public void registerIcons(IIconRegister par1IIconRegister) {
		this.itemIcon = par1IIconRegister.registerIcon("atum:MafdetsQuickness");
	}
}
