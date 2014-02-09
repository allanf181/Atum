package com.teammetallurgy.atum.lib.proxy;

import com.teammetallurgy.atum.lib.tickhandler.ServerTickHandler;

import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

	public void init() {
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
	}

	public void initRenders() {
	}

	public void initTiles() {
	}

}
