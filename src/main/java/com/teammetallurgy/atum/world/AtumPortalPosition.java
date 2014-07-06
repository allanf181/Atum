package com.teammetallurgy.atum.world;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;

public class AtumPortalPosition extends ChunkCoordinates {
    public long lastUpdateTime;

	public AtumPortalPosition(int par2, int par3, int par4, long par5) {
		super(par2, par3, par4);
        this.lastUpdateTime = par5;
	}
}
