package com.teammetallurgy.atum.lib.proxy;

import net.minecraftforge.common.MinecraftForge;

import com.teammetallurgy.atum.lib.tickhandler.ServerTickHandler;

import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy {

	public void init() {
		ServerTickHandler ticker = new ServerTickHandler();
		MinecraftForge.EVENT_BUS.register(ticker);
		FMLCommonHandler.instance().bus().register(ticker);
	}

	public void initRenders() {
	}

	public void initTiles() {
	}

}
