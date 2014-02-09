package com.teammetallurgy.atum;

import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.entity.Entity;
import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.lib.handler.CraftingHandler;
import com.teammetallurgy.atum.lib.proxy.CommonProxy;
import com.teammetallurgy.atum.world.World;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Atum.MODID, name = Atum.NAME, version = Atum.VERSION)
public class Atum {
	public static final String MODID = "Atum";
	public static final String NAME = "Atum";
	public static final String VERSION = "0.5.9B";

	@Instance(Atum.MODID)
	public static Atum instance;

	@SidedProxy(clientSide = "com.teammetallurgy.atum.lib.proxy.ClientProxy", serverSide = "com.teammetallurgy.atum.lib.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final Logger LOGGER = Logger.getLogger(Atum.class
			.getSimpleName());

	public static AtumConfig config;
	public static CreativeTabs creativeTab = new AtumCreativeTab();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LOGGER.info("Loading Configuration");
		config = new AtumConfig(event.getSuggestedConfigurationFile());
		config.load();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		LOGGER.info("Block Init");
		AtumBlocks.INSTANCE.registerBlocks();

		LOGGER.info("Item Init");
		AtumItems.INSTANCE.registerItems();

		LOGGER.info("Register Crafting Recipes");
		CraftingHandler.INSTANCE.register();

		LOGGER.info("World Init");
		World.INSTANCE.register();

		LOGGER.info("Entity Init");
		Entity.INSTANCE.register();

		LOGGER.info("Loot Init");
		AtumLoot.INSTANCE.register();

		LOGGER.info("Proxy Init");
		proxy.init();
		proxy.initRenders();
		proxy.initTiles();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
