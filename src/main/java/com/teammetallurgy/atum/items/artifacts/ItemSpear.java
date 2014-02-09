package com.teammetallurgy.atum.items.artifacts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.teammetallurgy.atum.entity.projectiles.EntityFireSpearCombined;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpear extends Item {

	public ItemSpear(int par1) {
		super(par1);
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		EntityFireSpearCombined entityarrow = new EntityFireSpearCombined(par2World, par3EntityPlayer, 0.75F);
		entityarrow.setDamage(entityarrow.getDamage() * 1.5D);
		if(!par2World.isRemote) {
			par2World.spawnEntityInWorld(entityarrow);
			par2World.updateEntity(entityarrow);
		}

		return par1ItemStack;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("atum:Arrow");
	}
}
