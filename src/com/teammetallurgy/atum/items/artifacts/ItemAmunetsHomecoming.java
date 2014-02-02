package com.teammetallurgy.atum.items.artifacts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import org.lwjgl.input.Keyboard;

public class ItemAmunetsHomecoming extends Item {

	public ItemAmunetsHomecoming(int par1) {
		super(par1);
		this.setMaxDamage(20);
	}

	@Override
	// TODO FIX
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par3World, EntityPlayer par2EntityPlayer) {
		ChunkCoordinates spawn = par2EntityPlayer.getBedLocation();
		if(spawn == null) {
			spawn = par3World.getSpawnPoint();
		}

		if(spawn == null) {
			spawn = par3World.getSpawnPoint();
		}

		spawn = verifyRespawnCoordinates(par3World, spawn, false);
		if(spawn == null) {
			spawn = par3World.getSpawnPoint();
		}

		par2EntityPlayer.rotationPitch = 0.0F;
		par2EntityPlayer.rotationYaw = 0.0F;
		par2EntityPlayer.setPositionAndUpdate((double) spawn.posX + 0.5D, (double) spawn.posY + 0.1D, (double) spawn.posZ);

		while(!par3World.getCollidingBoundingBoxes(par2EntityPlayer, par2EntityPlayer.boundingBox).isEmpty()) {
			par2EntityPlayer.setPosition(par2EntityPlayer.posX, par2EntityPlayer.posY + 1.0D, par2EntityPlayer.posZ);
		}

		par1ItemStack.damageItem(1, par2EntityPlayer);
		return par1ItemStack;
	}

	public static ChunkCoordinates verifyRespawnCoordinates(World par0World, ChunkCoordinates par1ChunkCoordinates, boolean par2) {
		if(!par0World.isRemote) {
			IChunkProvider c = par0World.getChunkProvider();
			c.loadChunk(par1ChunkCoordinates.posX - 3 >> 4, par1ChunkCoordinates.posZ - 3 >> 4);
			c.loadChunk(par1ChunkCoordinates.posX + 3 >> 4, par1ChunkCoordinates.posZ - 3 >> 4);
			c.loadChunk(par1ChunkCoordinates.posX - 3 >> 4, par1ChunkCoordinates.posZ + 3 >> 4);
			c.loadChunk(par1ChunkCoordinates.posX + 3 >> 4, par1ChunkCoordinates.posZ + 3 >> 4);
		}

		Block block = Block.blocksList[par0World.getBlockId(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ)];
		if(block != null && block.isBed(par0World, par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ, (EntityLiving) null)) {
			ChunkCoordinates material1 = block.getBedSpawnPosition(par0World, par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ, (EntityPlayer) null);
			return material1;
		} else {
			Material material = par0World.getBlockMaterial(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ);
			Material material1 = par0World.getBlockMaterial(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY + 1, par1ChunkCoordinates.posZ);
			boolean flag1 = !material.isSolid() && !material.isLiquid();
			boolean flag2 = !material1.isSolid() && !material1.isLiquid();
			return par2 && flag1 && flag2 ? par1ChunkCoordinates : null;
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
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Return I: Teleports you back");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "to your spawn point");
		} else {
			par3List.add("Return I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

		par3List.add(par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage() + " Uses Remaining");
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:AmunetsHomecoming");
	}
}
