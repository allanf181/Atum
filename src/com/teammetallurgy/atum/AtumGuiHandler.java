package com.teammetallurgy.atum;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.teammetallurgy.atum.blocks.tileentity.furnace.ContainerLimestoneFurnace;
import com.teammetallurgy.atum.blocks.tileentity.furnace.GuiLimestoneFurnace;
import com.teammetallurgy.atum.blocks.tileentity.furnace.TileEntityLimestoneFurnace;

import cpw.mods.fml.common.network.IGuiHandler;

public class AtumGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntityLimestoneFurnace te = (TileEntityLimestoneFurnace) world.getBlockTileEntity(x, y, z);

		if(te != null) {
			if(te instanceof TileEntityLimestoneFurnace) {
				return new ContainerLimestoneFurnace(player.inventory, te);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntityLimestoneFurnace te = (TileEntityLimestoneFurnace) world.getBlockTileEntity(x, y, z);

		if(te != null) {
			if(te instanceof TileEntityLimestoneFurnace) {
				return new GuiLimestoneFurnace(player.inventory, te);
			}
		}
		return null;
	}

}
