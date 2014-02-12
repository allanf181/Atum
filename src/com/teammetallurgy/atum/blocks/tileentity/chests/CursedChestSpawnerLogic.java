package com.teammetallurgy.atum.blocks.tileentity.chests;

import net.minecraft.block.Block;
import net.minecraft.tileentity.WeightedRandomMinecart;
import net.minecraft.world.World;

class CursedChestSpawnerLogic extends CursedChestBaseLogic {

	final TileEntityChestSpawner field_98295_a;

	CursedChestSpawnerLogic(TileEntityChestSpawner tileEntityChestSpawner) {
		this.field_98295_a = tileEntityChestSpawner;
	}

	@Override
	public void func_98267_a(int par1) {
		this.field_98295_a.worldObj.addBlockEvent(this.field_98295_a.xCoord, this.field_98295_a.yCoord, this.field_98295_a.zCoord, Block.mobSpawner.blockID, par1, 0);
	}

	@Override
	public World getSpawnerWorld() {
		return this.field_98295_a.worldObj;
	}

	@Override
	public int getSpawnerX() {
		return this.field_98295_a.xCoord;
	}

	@Override
	public int getSpawnerY() {
		return this.field_98295_a.yCoord;
	}

	@Override
	public int getSpawnerZ() {
		return this.field_98295_a.zCoord;
	}

	@Override
	public void setRandomMinecart(WeightedRandomMinecart par1WeightedRandomMinecart) {
		super.setRandomMinecart(par1WeightedRandomMinecart);
		if(this.getSpawnerWorld() != null) {
			this.getSpawnerWorld().markBlockForUpdate(this.field_98295_a.xCoord, this.field_98295_a.yCoord, this.field_98295_a.zCoord);
		}

	}
}
