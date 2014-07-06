package com.teammetallurgy.atum.lib.proxy;

import com.teammetallurgy.atum.lib.tickhandler.ServerEvents;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy {

	public void init() {
		ServerEvents ticker = new ServerEvents();
		MinecraftForge.EVENT_BUS.register(ticker);
		FMLCommonHandler.instance().bus().register(ticker);
	}

	public void initRenders() {
	}

	public void initTiles() {
	}

}
