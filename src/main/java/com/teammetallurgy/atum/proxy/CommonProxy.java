package com.teammetallurgy.atum.proxy;

import com.teammetallurgy.atum.handler.event.ServerEvents;

import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy {

    public void init() {
        ServerEvents ticker = new ServerEvents();
        FMLCommonHandler.instance().bus().register(ticker);
    }

    public void initRenders() {
    }

    public void initTiles() {
    }

}
