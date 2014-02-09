package com.teammetallurgy.atum.items.artifacts;

import java.util.List;

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

public class ItemMafdetsQuickness extends Item {

	public ItemMafdetsQuickness(int par1) {
		super(par1);
		this.setMaxDamage(24000);
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if(par3Entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) par3Entity;
			if(player.onGround && player.inventory.mainInventory[player.inventory.currentItem] != null && player.inventory.mainInventory[player.inventory.currentItem].itemID == super.itemID) {
				this.doEffect(player, par1ItemStack);
			}
		}

	}

	public void doEffect(EntityPlayer player, ItemStack item) {
		player.capabilities.setPlayerWalkSpeed((float) ((double) player.capabilities.getWalkSpeed() * 1.4D));
		if(player.motionX * player.motionX + player.motionZ * player.motionZ > 0.02D && !player.capabilities.isCreativeMode) {
			if(item.getItemDamage() == 1) {
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
		if(Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Swiftness I: You run");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "faster when held");
		} else {
			par3List.add("Swiftness I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

		par3List.add((double) ((par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()) / 12) / 100.0D + " Minutes Remaining");
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:MafdetsQuickness");
	}
}
