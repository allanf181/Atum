package com.teammetallurgy.atum.lib.tickhandler;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.teammetallurgy.atum.items.AtumItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ServerEvents {

	private boolean raining;

	@SubscribeEvent
	public void onServerTick(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;

		if (player.worldObj.getTotalWorldTime() % 60L == 0L) {

			if (player.getCurrentArmor(2) != null) {
				if (player.getCurrentArmor(2).getItem() == AtumItems.isisEmbrace) {
					player.heal(1);
				}
			}
		}

		if (player.worldObj.getTotalWorldTime() % 10L == 0L) {
			if (player.getCurrentArmor(3) != null) {
				if (player.getCurrentArmor(3).getItem() == AtumItems.rasGlory) {
					player.addPotionEffect(new PotionEffect(16, 340, 0, true));
				} else {
					if (player.isPotionActive(16)) {
						player.clearActivePotions();
					}
				}
			}

			if (player.getCurrentArmor(2) != null) {
				if (player.getCurrentArmor(2).getItem() == AtumItems.sekhmetsWrath) {
					player.addPotionEffect(new PotionEffect(12, 240, 0, true));
				}
			}

			if (player.getCurrentArmor(1) != null) {
				if (player.getCurrentArmor(1).getItem() == AtumItems.nutsAgility) {
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 240, 1, true));
					player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 240, 1, true));
				}
			}

			if (player.getCurrentArmor(0) != null) {
				if (player.getCurrentArmor(0).getItem() == AtumItems.horusFlight) {
					player.addPotionEffect(new PotionEffect(8, 240, 1, true));
				}
			}
		}
	}

}