package com.teammetallurgy.atum.items.artifacts;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemIsisHealing extends Item {

	public ItemIsisHealing(int par1) {
		super(par1);
		this.setMaxDamage(400);
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if(par3Entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) par3Entity;
			if(player.onGround && player.inventory.mainInventory[player.inventory.currentItem] != null && player.inventory.mainInventory[player.inventory.currentItem].itemID == super.itemID) {
				this.doEffect(player, par1ItemStack);
			}
		}

	}

	public void doEffect(EntityPlayer player, ItemStack item) {
		if(Math.random() <= 0.05D) {
			if(player.getHealth() < player.getMaxHealth()) {
				player.heal(1);
				if(!player.capabilities.isCreativeMode) {
					if(item.getItemDamage() == 1) {
						item.damageItem(1, player);
					} else {
						item.setItemDamage(item.getItemDamage() + 1);
					}
				}
			}

		}
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Regeneration I: Regenerates");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "health slowly while held");
		} else {
			par3List.add("Regeneration I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

		par3List.add(par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage() + " Hearts Remaining");
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:IsisHealing");
	}
}
