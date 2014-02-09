package com.teammetallurgy.atum.blocks.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

class CursedChestSpawnerLogic extends CursedChestBaseLogic {

	final TileEntityChestSpawner field_98295_a;

	CursedChestSpawnerLogic(TileEntityChestSpawner tileEntityChestSpawner) {
		this.field_98295_a = tileEntityChestSpawner;
	}

	@Override
	public void func_98267_a(int par1) {
		this.field_98295_a.getWorldObj().addBlockEvent(this.field_98295_a.xCoord, this.field_98295_a.yCoord, this.field_98295_a.zCoord, Blocks.mob_spawner, par1, 0);
	}

	@Override
	public World getSpawnerWorld() {
		return this.field_98295_a.getWorldObj();
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
	public void setRandomEntity(WeightedRandomMinecart par1WeightedRandomMinecart) {
		super.setRandomEntity(par1WeightedRandomMinecart);
		if (this.getSpawnerWorld() != null) {
			this.getSpawnerWorld().markBlockForUpdate(this.field_98295_a.xCoord, this.field_98295_a.yCoord, this.field_98295_a.zCoord);
		}

	}
}
