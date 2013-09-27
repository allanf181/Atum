package rebelkeithy.mods.atum;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityList;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import rebelkeithy.mods.atum.artifacts.arrow.EntityArrowDoubleShot;
import rebelkeithy.mods.atum.artifacts.arrow.EntityArrowExplosive;
import rebelkeithy.mods.atum.artifacts.arrow.EntityArrowFire;
import rebelkeithy.mods.atum.artifacts.arrow.EntityArrowPoison;
import rebelkeithy.mods.atum.artifacts.arrow.EntityArrowQuickdraw;
import rebelkeithy.mods.atum.artifacts.arrow.EntityArrowVelocity;
import rebelkeithy.mods.atum.artifacts.arrow.EntityAtumFishHook;
import rebelkeithy.mods.atum.artifacts.arrow.EntityNutsCall;
import rebelkeithy.mods.atum.entities.EntityBanditArcher;
import rebelkeithy.mods.atum.entities.EntityBanditWarlord;
import rebelkeithy.mods.atum.entities.EntityBanditWarrior;
import rebelkeithy.mods.atum.entities.EntityBarbarian;
import rebelkeithy.mods.atum.entities.EntityDesertWolf;
import rebelkeithy.mods.atum.entities.EntityDustySkeleton;
import rebelkeithy.mods.atum.entities.EntityGhost;
import rebelkeithy.mods.atum.entities.EntityMummy;
import rebelkeithy.mods.atum.entities.EntityPharaoh;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;
import rebelkeithy.mods.atum.entities.projectiles.EntityFireSpearCombined;
import rebelkeithy.mods.atum.entities.projectiles.EntityFireSpearSeperated;
import rebelkeithy.mods.atum.world.AtumWorldProvider;
import rebelkeithy.mods.atum.world.biome.BiomeGenAtumDesert;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Atum.modID, name = "Atum", version = "0.4.4B")
@NetworkMod(channels = { "Atum" }, clientSideRequired = true, serverSideRequired = false)
public class Atum {

	public static final String modID = "Atum";
	@Instance("Atum")
	public static Atum instance;
	@SidedProxy(clientSide = "rebelkeithy.mods.atum.ClientProxy", serverSide = "rebelkeithy.mods.atum.CommonProxy")
	public static CommonProxy proxy;
	public static AtumTab tabs = new AtumTab("Atum");
	public static BiomeGenBase atumDesert;
	public static Potion stun;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		AtumConfig.initConfig();
		AtumBlocks.init();
		AtumBlocks.registerBlocks();
		AtumItems.init();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ArrayList biomeList = new ArrayList();
		int entityID;
		for (entityID = 0; entityID < BiomeGenBase.biomeList.length; ++entityID) {
			if (BiomeGenBase.biomeList[entityID] != null && BiomeGenBase.biomeList[entityID].biomeID != AtumConfig.biomeAtumDesertID) {
				biomeList.add(BiomeGenBase.biomeList[entityID]);
			}
		}

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityMummy.class, "AtumMummy", 20, this, 64, 1, true);
		EntityList.addMapping(EntityMummy.class, "AtumMummy", 20, 5331000, 8818539);
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityBanditWarrior.class, "AtumBanditWarrior", 21, this, 64, 1, true);
		EntityList.addMapping(EntityBanditWarrior.class, "AtumBanditWarrior", 21, 12763842, 266117);
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityBanditArcher.class, "AtumBanditArcher", 22, this, 64, 1, true);
		EntityList.addMapping(EntityBanditArcher.class, "AtumBanditArcher", 22, 12763842, 8260620);
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityPharaoh.class, "AtumPharaoh", 23, this, 64, 1, true);
		EntityList.addMapping(EntityPharaoh.class, "AtumPharaoh", 23, 13941815, 3820512);
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityDustySkeleton.class, "AtumDustySkeleton", 24, this, 64, 1, true);
		EntityList.addMapping(EntityDustySkeleton.class, "AtumDustySkeleton", 24, 11902077, 7298115);
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityGhost.class, "AtumDesertGhost", 25, this, 64, 1, true);
		EntityList.addMapping(EntityGhost.class, "AtumDesertGhost", 25, 15195080, 11375719);
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityStoneSoldier.class, "AtumStoneSoldier", 26, this, 64, 1, true);
		EntityList.addMapping(EntityStoneSoldier.class, "AtumStoneSoldier", 26, 9536340, 6905143);
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityDesertWolf.class, "AtumDesertWolf", 27, this, 64, 1, true);
		EntityList.addMapping(EntityDesertWolf.class, "AtumDesertWolf", 27, 9536340, 6905143);
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityBanditWarlord.class, "AtumBanditWarlord", 28, this, 64, 1, true);
		EntityList.addMapping(EntityBanditWarlord.class, "AtumBanditWarlord", 28, 9536340, 6905143);
		EntityRegistry.registerModEntity(EntityBarbarian.class, "AtumBarbarian", 29, this, 64, 1, true);
		EntityList.addMapping(EntityBarbarian.class, "AtumBarbarian", 29, 9536340, 6905143);
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityFireSpearCombined.class, "FireSpearCombined", entityID, this, 64, 1, true);
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityFireSpearSeperated.class, "FireSpearSeperated", entityID, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowVelocity.class, "ArrowVeloctiy", 0, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowExplosive.class, "ArrowExplosive", 1, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowPoison.class, "ArrowPoison", 2, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowFire.class, "ArrowFire", 3, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowDoubleShot.class, "ArrowFire", 4, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowQuickdraw.class, "ArrowFire", 5, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityNutsCall.class, "EntityNutsCall", 6, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityAtumFishHook.class, "EntityAtumFishHook", 7, this, 64, 1, false);
		atumDesert = (new BiomeGenAtumDesert(AtumConfig.biomeAtumDesertID)).setColor(16421912).setBiomeName("AtumDesert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
		proxy.registerModelRenderers();
		proxy.registerTickHandlers();
		proxy.preloadImages();
		proxy.registerParticles();
		proxy.registerMusic();
		MinecraftForge.EVENT_BUS.register(new AtumEventListener());
		NetworkRegistry.instance().registerGuiHandler(this, new AtumGuiHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		DimensionManager.registerProviderType(AtumConfig.dimensionID, AtumWorldProvider.class, true);
		DimensionManager.registerDimension(AtumConfig.dimensionID, AtumConfig.dimensionID);
		stun = (new PotionStun(21, true, 8171462)).setPotionName("potion.stun").setIconIndex(0, 0);
		this.addNames();
		addOreDictionaryEntries();
		AtumRecipes.addRecipes();
		AtumRecipes.addShapelessRecipes();
		AtumRecipes.addSmeltingRecipes();
	}

	public static void addOreDictionaryEntries() {
		OreDictionary.registerOre("logWood", AtumBlocks.log);
		OreDictionary.registerOre("plankWood", AtumBlocks.planks);
	}

	public void addNames() {
		AtumBlocks.addNames();
		AtumItems.addNames();
		LanguageRegistry.instance().addStringLocalization("itemGroup.Atum", "Atum");
		LanguageRegistry.instance().addStringLocalization("entity.AtumMummy.name", "Mummy");
		LanguageRegistry.instance().addStringLocalization("entity.AtumBanditWarrior.name", "Brigand");
		LanguageRegistry.instance().addStringLocalization("entity.AtumBanditArcher.name", "Nomad");
		LanguageRegistry.instance().addStringLocalization("entity.AtumPharaoh.name", "Pharaoh");
		LanguageRegistry.instance().addStringLocalization("entity.AtumDustySkeleton.name", "Forsaken");
		LanguageRegistry.instance().addStringLocalization("entity.AtumDesertGhost.name", "Wraith");
		LanguageRegistry.instance().addStringLocalization("entity.AtumStoneSoldier.name", "Tombguard");
		LanguageRegistry.instance().addStringLocalization("entity.AtumDesertWolf.name", "Desert Wolf");
		LanguageRegistry.instance().addStringLocalization("entity.AtumBanditWarlord.name", "Warlord");
	}

}
