package com.teammetallurgy.atum.lib.tickhandler;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.teammetallurgy.atum.items.Items;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler {

	private boolean raining;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if(type.equals(EnumSet.of(TickType.PLAYER))) {
			EntityPlayer player = (EntityPlayer) tickData[0];

			if(player.worldObj.getTotalWorldTime() % 60L == 0L) {

				if(player.getCurrentArmor(2) != null) {
					if(player.getCurrentArmor(2).itemID == Items.isisEmbrace.itemID) {
						player.heal(1);
					}
				}
			}

			if(player.worldObj.getTotalWorldTime() % 10L == 0L) {
				if(player.getCurrentArmor(3) != null) {
					if(player.getCurrentArmor(3).itemID == Items.rasGlory.itemID) {
						player.addPotionEffect(new PotionEffect(16, 340, 0, true));
					} else {
						if(player.isPotionActive(16)) {
							player.clearActivePotions();
						}
					}
				}

				if(player.getCurrentArmor(2) != null) {
					if(player.getCurrentArmor(2).itemID == Items.sekhmetsWrath.itemID) {
						// player.addPotionEffect(new PotionEffect(12, 240, 0, true));
					}
				}

				if(player.getCurrentArmor(1) != null) {
					if(player.getCurrentArmor(1).itemID == Items.nutsAgility.itemID) {
						// player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 240, 1, true));
						player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 240, 1, true));
					}
				}

				if(player.getCurrentArmor(0) != null) {
					if(player.getCurrentArmor(0).itemID == Items.horusFlight.itemID) {
						// player.addPotionEffect(new PotionEffect(8, 240, 1, true));
					}
				}
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return "AtumServerHandler";
	}

}