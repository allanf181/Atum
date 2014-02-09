package com.teammetallurgy.atum.items.artifacts;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import org.lwjgl.input.Keyboard;

import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.items.ItemTexturedArmor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHorusFlight extends ItemTexturedArmor {

	public ItemHorusFlight(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);

	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	@ForgeSubscribe
	public void onJump(LivingJumpEvent event) {
		if(event.entityLiving.getCurrentItemOrArmor(0) != null && event.entityLiving.getCurrentItemOrArmor(0).itemID == super.itemID) {
			event.entityLiving.motionY += 0.2D;
			event.entityLiving.motionX *= 1.2D;
			event.entityLiving.motionZ *= 1.2D;
		}

	}

	@ForgeSubscribe
	public void onFallDamage(LivingFallEvent event) {
		if(event.entityLiving.getCurrentItemOrArmor(0) != null && event.entityLiving.getCurrentItemOrArmor(0).itemID == AtumItems.horusFlight.itemID) {
			event.distance = 0.0F;
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
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Nimbleness I: Increased jump height,");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "protection from fall damage");
		} else {
			par3List.add("Nimbleness I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:HorusFlight");
	}
}
