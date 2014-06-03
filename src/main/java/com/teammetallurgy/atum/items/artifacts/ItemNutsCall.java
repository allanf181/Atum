package com.teammetallurgy.atum.items.artifacts;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.teammetallurgy.atum.entity.arrow.EntityNutsCall;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNutsCall extends Item {

	public ItemNutsCall() {
		super();
		this.setMaxDamage(650);
		this.setMaxStackSize(1);

	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 7200;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldRotateAroundWhenRendering() {
		return false;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World world, EntityPlayer player, int par4) {
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
		System.out.println("charge strength: " + j);
		if (j > 21) {
			j = 21;
		}

		EntityNutsCall spear = new EntityNutsCall(world, player, (float) j / 25.0F + 0.25F);
		spear.setDamage(spear.getDamage() * 2.0D);
		spear.setStack(par1ItemStack);
		if (!world.isRemote) {
			world.spawnEntityInWorld(spear);
			world.updateEntity(spear);
		}

		par1ItemStack.damageItem(4, player);
		player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
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
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Wrath I: Chance to strike");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "foe with lightning");
		} else {
			par3List.add("Wrath I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}

	@Override
	public void registerIcons(IIconRegister par1IIconRegister) {
		this.itemIcon = par1IIconRegister.registerIcon("atum:NutsCall");
	}
}
