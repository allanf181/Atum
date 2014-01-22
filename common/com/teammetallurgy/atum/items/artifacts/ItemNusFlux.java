package com.teammetallurgy.atum.items.artifacts;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNusFlux extends ItemSword {

	public ItemNusFlux(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);

	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase entity, EntityLivingBase player) {
		// if(!player.worldObj.isRemote && Math.random() > 0.75D && !(entity instanceof EntityStoneSoldier)) {
		double dx = entity.posX - player.posX;
		double dz = entity.posZ - player.posZ;
		double magnitude = Math.sqrt(dx * dx + dz * dz);
		dx /= magnitude;
		dz /= magnitude;
		entity.isAirBorne = true;
		entity.addVelocity(dx / 2.0D, 1.5D, dz / 2.0D);
		if(entity.motionY > 1.0D) {
			entity.motionY = 1.0D;
		}

		entity.attackEntityFrom(DamageSource.generic, this.getDamageVsEntity(entity, par1ItemStack));
		if(player.worldObj.isRemote) {
			this.spawnParticle(player.worldObj, entity);
			// }
		}

		return super.hitEntity(par1ItemStack, entity, player);
	}

	@SideOnly(Side.CLIENT)
	public void spawnParticle(World world, Entity entity) {
		Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCrit2FX(world, entity));
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
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Sweep I: Chance to launch");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "foes into the air");
		} else {
			par3List.add("Sweep I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:NusFlux");
	}
}
