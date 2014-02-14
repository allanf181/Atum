package com.teammetallurgy.atum.items.artifacts;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPtahsDecadence extends ItemPickaxe {

	public ItemPtahsDecadence(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int blockID, int x, int y, int z, EntityLiving par7EntityLiving) {
		int dropID = Block.blocksList[blockID].idDropped(par2World.getBlockMetadata(x, y, z), new Random(), 0);
		if(dropID == Item.diamond.itemID) {
			Block.oreDiamond.dropBlockAsItem(par2World, x, y, z, 0, 0);
		}

		return super.onBlockDestroyed(par1ItemStack, par2World, blockID, x, y, z, par7EntityLiving);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Wealth I: Gain an extra");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "diamond from each ore");
		} else {
			par3List.add("Wealth I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:PtahsDecadence");
	}
}
