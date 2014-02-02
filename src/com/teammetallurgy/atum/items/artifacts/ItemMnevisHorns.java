package com.teammetallurgy.atum.items.artifacts;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMnevisHorns extends ItemArmor {

	public String texture;

	public ItemMnevisHorns(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		MinecraftForge.EVENT_BUS.register(this);

	}

	@ForgeSubscribe
	public void onLivingAttack(LivingAttackEvent event) {
		if(event.entityLiving.getCurrentItemOrArmor(3) != null && event.entityLiving.getCurrentItemOrArmor(3).itemID == super.itemID && event.source instanceof EntityDamageSource) {
			EntityDamageSource source = (EntityDamageSource) event.source;
			if(source.getEntity() != null) {
				source.getEntity().attackEntityFrom(DamageSource.generic, (int) ((double) event.ammount / 2.0D));
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
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Melee Reflection I: Deals");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "damage to attackers");
		} else {
			par3List.add("Melee Reflection I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}

	public Item setTextureFile(String string) {
		this.texture = string;
		return this;
	}

	public String getArmorTextureFile(ItemStack itemstack) {
		return "/armor/" + this.texture + ".png";
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:MnevisHorns");
	}
}
