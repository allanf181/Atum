package com.teammetallurgy.atum;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.entity.AtumEntities;
import com.teammetallurgy.atum.gui.AtumGuiHandler;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.handler.AtumCreativeTab;
import com.teammetallurgy.atum.handler.event.AtumEventListener;
import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.handler.CraftingHandler;
import com.teammetallurgy.atum.items.AtumLoot;
import com.teammetallurgy.atum.proxy.CommonProxy;
import com.teammetallurgy.atum.utils.Constants;
import com.teammetallurgy.atum.world.AtumWorlds;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Constants.MODID, name = Constants.MOD_NAME, version = Constants.VERSION, dependencies = Constants.DEPENDENCIES,guiFactory = Constants.FACTORY)
public class Atum {

    @Instance(Constants.MODID)
    public static Atum instance;

    @SidedProxy(clientSide = Constants.CLIENT, serverSide = Constants.SERVER)
    public static CommonProxy proxy;

    public static CreativeTabs creativeTab = new AtumCreativeTab();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Constants.LOG = event.getModLog();
        Constants.LOG.trace("Loading Configuration");
        new AtumConfig(event.getSuggestedConfigurationFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Constants.LOG.trace("Register Blocks");
        new AtumBlocks();

        Constants.LOG.trace("Register Item");
        new AtumItems();

        Constants.LOG.trace("Register Crafting Recipes");
        new CraftingHandler().register();

        Constants.LOG.trace("Register World");
        new AtumWorlds().register();

        Constants.LOG.trace("Register Entity");
        new AtumEntities().register();

        Constants.LOG.trace("Register Loot");
        new AtumLoot().register();

        Constants.LOG.trace("Proxy Init");
        proxy.init();
        proxy.initRenders();
        proxy.initTiles();

        MinecraftForge.EVENT_BUS.register(new AtumEventListener());

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new AtumGuiHandler());
    }
}
