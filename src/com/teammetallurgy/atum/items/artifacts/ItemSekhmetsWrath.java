package com.teammetallurgy.atum.items.artifacts;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSekhmetsWrath extends ItemArmor {

	String texture;

	@SideOnly(Side.CLIENT)
	public ItemSekhmetsWrath(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	@ForgeSubscribe
	public void onLivingAttack(LivingAttackEvent event) {
		if(event.entityLiving.getCurrentItemOrArmor(2) != null && event.entityLiving.getCurrentItemOrArmor(2).itemID == super.itemID && event.source instanceof EntityDamageSource) {
			EntityDamageSource source = (EntityDamageSource) event.source;
			if(source.getEntity() != null && Math.random() > 0.5D) {
				source.getEntity().setFire(10);
			}
		}

	}

	@ForgeSubscribe
	public void onLivingAttack(LivingHurtEvent event) {
		if(event.entityLiving.getCurrentItemOrArmor(2) != null && event.entityLiving.getCurrentItemOrArmor(2).itemID == super.itemID && event.source.isFireDamage()) {
			event.ammount /= 2;
			if(event.ammount == 0 && Math.random() > 0.5D) {
				event.ammount = 1;
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
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Immolation I: Protection from fire,");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "chance to ignite attackers");
		} else {
			par3List.add("Immolation I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}

	public Item setTextureFile(String string) {
		this.texture = string;
		return this;
	}

	public String getArmorTextureFile(ItemStack itemstack) {
		return "/armor/" + this.texture + ".png";
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:SekhmetsWrath");
	}
}
