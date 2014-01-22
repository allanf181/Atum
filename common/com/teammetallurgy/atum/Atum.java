package com.teammetallurgy.atum;

import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;

import com.teammetallurgy.atum.blocks.Blocks;
import com.teammetallurgy.atum.items.Items;
import com.teammetallurgy.atum.proxy.CommonProxy;
import com.teammetallurgy.atum.worldgen.World;

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
	public static final String VERSION = "0.0.1";

	@Instance(Atum.MODID)
	public static Atum instance;

	@SidedProxy(clientSide = "com.teammetallurgy.atum.proxy.ClientProxy", serverSide = "com.teammetallurgy.atum.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final Logger LOGGER = Logger.getLogger(Atum.class.getSimpleName());

	public static AtumConfig config;
	public static CreativeTabs creativeTab = new CreativeTabs("Atum");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LOGGER.info("Loading Configuration");
		config = new AtumConfig(event.getSuggestedConfigurationFile());
		config.load();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		LOGGER.info("Block Init");
		Blocks.INSTANCE.registerBlocks();
		LOGGER.info("Item Init");
		Items.INSTANCE.registerItems();
		LOGGER.info("World Init");
		World.load();
		
		LOGGER.info("Proxy Init");
		proxy.init();
		proxy.initRenders();
		proxy.initTiles();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
