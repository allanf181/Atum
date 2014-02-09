package com.teammetallurgy.atum.items.artifacts;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAnhursMight extends ItemSword {

	public ItemAnhursMight(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
		if(Math.random() > 0.5D) {
			par2EntityLiving.addPotionEffect(new PotionEffect(21, 80, 1, false));
			par2EntityLiving.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 80, 6, false));
		}

		return super.hitEntity(par1ItemStack, par2EntityLiving, par3EntityLiving);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == super.itemID) {
				double magnitude = Math.sqrt(player.motionX * player.motionX + player.motionZ * player.motionZ);
				// Fix
				// player.capabilities.setPlayerWalkSpeed((float) ((double) player.capabilities.getWalkSpeed() * 0.75D));
			}
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Mighty I: Slows player,");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Chance to stun foes");
		} else {
			par3List.add("Mighty I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:AnhursMight");
	}
}
