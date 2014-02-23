package com.teammetallurgy.atum;

import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.entity.AtumEntities;
import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.lib.handler.CraftingHandler;
import com.teammetallurgy.atum.lib.proxy.CommonProxy;
import com.teammetallurgy.atum.world.AtumWorlds;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Atum.MODID, name = Atum.NAME, version = Atum.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Atum {
	public static final String MODID = "Atum";
	public static final String NAME = "Atum";
	public static final String VERSION = "0.5.9B";

	@Instance(Atum.MODID)
	public static Atum instance;

	@SidedProxy(clientSide = "com.teammetallurgy.atum.lib.proxy.ClientProxy", serverSide = "com.teammetallurgy.atum.lib.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final Logger LOGGER = Logger.getLogger(Atum.class.getSimpleName());

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
		LOGGER.info("Register Blocks");
		AtumBlocks.INSTANCE.registerBlocks();

		LOGGER.info("Register Item");
		AtumItems.INSTANCE.registerItems();

		LOGGER.info("Register Crafting Recipes");
		CraftingHandler.INSTANCE.register();

		LOGGER.info("Register World");
		AtumWorlds.INSTANCE.register();

		LOGGER.info("Register Entity");
		AtumEntities.INSTANCE.register();

		LOGGER.info("Register Loot");
		AtumLoot.INSTANCE.register();

		LOGGER.info("Proxy Init");
		proxy.init();
		proxy.initRenders();
		proxy.initTiles();

		MinecraftForge.EVENT_BUS.register(new AtumEventListener());

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
