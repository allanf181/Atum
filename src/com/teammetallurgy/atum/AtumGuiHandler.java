package com.teammetallurgy.atum;

import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

import com.teammetallurgy.atum.blocks.tileentity.TileEntityLimestoneFurnace;

import cpw.mods.fml.common.network.IGuiHandler;

public class AtumGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntityFurnace te = (TileEntityFurnace) world.getBlockTileEntity(x, y, z);

		if(te != null) {
			if(te instanceof TileEntityLimestoneFurnace) {
				return new ContainerFurnace(player.inventory, te);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntityFurnace te = (TileEntityFurnace) world.getBlockTileEntity(x, y, z);

		if(te != null) {
			if(te instanceof TileEntityLimestoneFurnace) {
				return new GuiFurnace(player.inventory, te);
			}
		}
		return null;
	}

}
