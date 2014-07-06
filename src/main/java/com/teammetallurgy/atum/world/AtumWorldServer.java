package com.teammetallurgy.atum.world;

import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveHandler;

import com.teammetallurgy.atum.world.AtumTeleporter;

public class AtumWorldServer extends WorldServer {

	public AtumWorldServer(MinecraftServer par1MinecraftServer, ISaveHandler par2iSaveHandler, String par3Str, int par4, WorldSettings par5WorldSettings, Profiler par6Profiler) {
		super(par1MinecraftServer, par2iSaveHandler, par3Str, par4, par5WorldSettings, par6Profiler);
	}

	@Override
	public Teleporter getDefaultTeleporter() {
		return new AtumTeleporter(this);
	}
}
