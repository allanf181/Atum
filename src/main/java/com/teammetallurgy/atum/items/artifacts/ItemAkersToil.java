package com.teammetallurgy.atum.items.artifacts;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAkersToil extends ItemSpade {

	public ItemAkersToil(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		for(int i = 0; i < ItemSpade.blocksEffectiveAgainst.length; ++i) {
			if(ItemSpade.blocksEffectiveAgainst[i] == par2Block) {
				return super.efficiencyOnProperMaterial * 3.0F;
			}
		}

		return 1.0F;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLivingBase par7EntityLiving) {
		if(par7EntityLiving instanceof EntityPlayer) {
			((EntityPlayer) par7EntityLiving).getFoodStats().addExhaustion(-0.025F);
		}

		return false;
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
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Performance I: Faster, does");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "not consume fatique");
		} else {
			par3List.add("Performance I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:AkersToil");
	}
}
