package com.teammetallurgy.atum.items;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.AtumIDS;
import com.teammetallurgy.atum.LocalizationHelper;
import com.teammetallurgy.atum.blocks.Blocks;
import com.teammetallurgy.atum.items.artifacts.IsisEmbrace;
import com.teammetallurgy.atum.items.artifacts.ItemAkersToil;
import com.teammetallurgy.atum.items.artifacts.ItemAmunetsHomecoming;
import com.teammetallurgy.atum.items.artifacts.ItemAnhursMight;
import com.teammetallurgy.atum.items.artifacts.ItemAnubisMercy;
import com.teammetallurgy.atum.items.artifacts.ItemAnuketsBounty;
import com.teammetallurgy.atum.items.artifacts.ItemAtensFury;
import com.teammetallurgy.atum.items.artifacts.ItemGebsBlessing;
import com.teammetallurgy.atum.items.artifacts.ItemGebsSolidarity;
import com.teammetallurgy.atum.items.artifacts.ItemHedetetsSting;
import com.teammetallurgy.atum.items.artifacts.ItemHedetetsVenom;
import com.teammetallurgy.atum.items.artifacts.ItemHorusFlight;
import com.teammetallurgy.atum.items.artifacts.ItemHorusSoaring;
import com.teammetallurgy.atum.items.artifacts.ItemIsisHealing;
import com.teammetallurgy.atum.items.artifacts.ItemMaatsBalance;
import com.teammetallurgy.atum.items.artifacts.ItemMafdetsQuickness;
import com.teammetallurgy.atum.items.artifacts.ItemMnevisHorns;
import com.teammetallurgy.atum.items.artifacts.ItemMonthusBlast;
import com.teammetallurgy.atum.items.artifacts.ItemMonthusStrike;
import com.teammetallurgy.atum.items.artifacts.ItemNeithsAudacity;
import com.teammetallurgy.atum.items.artifacts.ItemNusFlux;
import com.teammetallurgy.atum.items.artifacts.ItemNutsAgility;
import com.teammetallurgy.atum.items.artifacts.ItemNutsCall;
import com.teammetallurgy.atum.items.artifacts.ItemOsirisWill;
import com.teammetallurgy.atum.items.artifacts.ItemPtahsDecadence;
import com.teammetallurgy.atum.items.artifacts.ItemPtahsDestruction;
import com.teammetallurgy.atum.items.artifacts.ItemRasGlory;
import com.teammetallurgy.atum.items.artifacts.ItemSekhmetsWrath;
import com.teammetallurgy.atum.items.artifacts.ItemShusBreath;
import com.teammetallurgy.atum.items.artifacts.ItemSobeksRage;
import com.teammetallurgy.atum.items.artifacts.ItemSpear;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public enum Items {
	INSTANCE;

	public static final Item ITEM_SCARAB = new ItemScarab(AtumIDS.ITEM_SCARAB_ID).setUnlocalizedName("atum:scarab").setCreativeTab(Atum.creativeTab);
	public static final Item ITEM_LOOT = new ItemLoot(AtumIDS.ITEM_LOOT_ID).setUnlocalizedName("atum:loot").setCreativeTab(Atum.creativeTab);
	public static final Item ITEM_DATE = new ItemFood(AtumIDS.ITEM_DATE_ID, 5, 1.5F, false).setTextureName("atum:date").setUnlocalizedName("atum:date").setCreativeTab(Atum.creativeTab);
	public static final Item ITEM_SCIMITAR = new ItemScimitar(AtumIDS.ITEM_SCIMITAR_ID, EnumToolMaterial.IRON).setUnlocalizedName("atum:scimitar").setCreativeTab(Atum.creativeTab);
	public static final Item ITEM_GREATSWORD = new ItemGreatsword(AtumIDS.ITEM_GREATSWORD_ID, EnumToolMaterial.IRON).setUnlocalizedName("atum:greatsword").setTextureName("atum:Greatsword").setCreativeTab(Atum.creativeTab);
	public static final Item ITEM_BOW = new ItemAtumBow(AtumIDS.ITEM_BOW_ID).setUnlocalizedName("atum:bow").setCreativeTab(Atum.creativeTab);
	public static final Item ITEM_STONESOLDIER_SWORD = new ItemStoneSoldierSword(AtumIDS.ITEM_STONESOLDIERSWORD_ID, EnumToolMaterial.IRON).setUnlocalizedName("atum:stoneSoldierSword").setCreativeTab(Atum.creativeTab);
	public static final Item ITEM_SCEPTER = new ItemScepter(AtumIDS.ITEM_SCEPTER_ID, EnumToolMaterial.GOLD).setUnlocalizedName("atum:scepter").setCreativeTab(Atum.creativeTab);
	public static final Item ptahsPick = new ItemPtahsDecadence(AtumIDS.ITEM_PTAHSPICK_ID, EnumToolMaterial.EMERALD).setUnlocalizedName("atum:ptahsDecadence").setCreativeTab(Atum.creativeTab);
	public static final Item sobeksRage = new ItemSobeksRage(AtumIDS.ITEM_SOBEKSRAGE_ID, EnumToolMaterial.EMERALD).setUnlocalizedName("atum:soteksRage").setCreativeTab(Atum.creativeTab);
	public static final Item osirisWill = new ItemOsirisWill(AtumIDS.ITEM_OSIRISWILL_ID, EnumToolMaterial.EMERALD).setUnlocalizedName("atum:osirisWill").setCreativeTab(Atum.creativeTab);
	public static final Item akersToil = new ItemAkersToil(AtumIDS.ITEM_AKERSTOIL_ID, EnumToolMaterial.EMERALD).setUnlocalizedName("atum:akersToil").setCreativeTab(Atum.creativeTab);
	public static final Item gebsBlessing = new ItemGebsBlessing(AtumIDS.ITEM_GEBSBLESSING_ID, EnumToolMaterial.EMERALD).setUnlocalizedName("atum:gebsBlessing").setCreativeTab(Atum.creativeTab);
	public static final Item atensFury = new ItemAtensFury(AtumIDS.ITEM_ATENSFURY_ID).setUnlocalizedName("atum:atensFury").setCreativeTab(Atum.creativeTab);
	public static final Item rasGlory = new ItemRasGlory(AtumIDS.ITEM_RASGLORY_ID, EnumArmorMaterial.DIAMOND, 0, 0).setTextureFile("EgyptianArmor_1").setUnlocalizedName("atum:rasGlory").setCreativeTab(Atum.creativeTab);
	public static final Item sekhmetsWrath = new ItemSekhmetsWrath(AtumIDS.ITEM_SEKHMETSWRATH_ID, 1, 1).setTextureFile("EgyptianArmor_1").setUnlocalizedName("atum:sekhmetsWrath").setCreativeTab(Atum.creativeTab);
	public static final Item nutsAgility = new ItemNutsAgility(AtumIDS.ITEM_NUTSAGILITY_ID, EnumArmorMaterial.DIAMOND, 2, 2).setTextureFile("EgyptianArmor_2").setUnlocalizedName("atum:nutsAgility").setCreativeTab(Atum.creativeTab);
	public static final Item horusFlight = new ItemHorusFlight(AtumIDS.ITEM_HORUSFLIGHT_ID, EnumArmorMaterial.DIAMOND, 3, 3).setTextureFile("EgyptianArmor_1").setUnlocalizedName("atum:horusFlight").setCreativeTab(Atum.creativeTab);
	public static final Item monthusStrike = new ItemMonthusStrike(AtumIDS.ITEM_MONTHUSSTRIKE_ID, EnumToolMaterial.EMERALD).setUnlocalizedName("atum:monthusStrike").setCreativeTab(Atum.creativeTab);
	public static final Item anhursMight = new ItemAnhursMight(AtumIDS.ITEM_ANHURSMIGHT_ID, EnumToolMaterial.EMERALD).setUnlocalizedName("atum:anhursMight").setCreativeTab(Atum.creativeTab);
	public static final Item hedetetsSting = new ItemHedetetsSting(AtumIDS.ITEM_HEDETETSSTING_ID, EnumToolMaterial.EMERALD).setUnlocalizedName("atum:hedetetsSting").setCreativeTab(Atum.creativeTab);
	public static final Item horusSoaring = new ItemHorusSoaring(AtumIDS.ITEM_HORUSFLIGHT_ID).setUnlocalizedName("atum:horusSoaring").setCreativeTab(Atum.creativeTab);
	public static final Item shusBreath = new ItemShusBreath(AtumIDS.ITEM_SHUSBREATH_ID).setUnlocalizedName("atum:shusBreath").setCreativeTab(Atum.creativeTab);
	public static final Item ptahsDestruction = new ItemPtahsDestruction(AtumIDS.ITEM_PTAHSDESTRUCTION_ID, EnumToolMaterial.EMERALD).setUnlocalizedName("atum:ptahsDestruction").setCreativeTab(Atum.creativeTab);
	public static final Item monthusBlast = new ItemMonthusBlast(AtumIDS.ITEM_MONTHUSBLAST_ID).setUnlocalizedName("atum:monthusBlast").setCreativeTab(Atum.creativeTab);
	public static final Item nusFlux = new ItemNusFlux(AtumIDS.ITEM_NUSFLUX_ID, EnumToolMaterial.EMERALD).setUnlocalizedName("atum:nusFlux").setCreativeTab(Atum.creativeTab);
	public static final Item mnevisHorns = new ItemMnevisHorns(AtumIDS.ITEM_MNEVISHORNS_ID, EnumArmorMaterial.DIAMOND, 0, 0).setTextureFile("RubyArtifactArmor_1").setUnlocalizedName("atum:mnevisHorns").setCreativeTab(Atum.creativeTab);
	public static final Item isisEmbrace = new IsisEmbrace(AtumIDS.ITEM_ISISEMBRACE_ID, EnumArmorMaterial.DIAMOND, 1, 1).setTextureFile("RubyArtifactArmor_1").setUnlocalizedName("atum:isisEmbrace").setCreativeTab(Atum.creativeTab);
	public static final Item maatsBalance = new ItemMaatsBalance(AtumIDS.ITEM_MAATSBALANCE_ID, EnumArmorMaterial.DIAMOND, 2, 2).setTextureFile("RubyArtifactArmor_2").setUnlocalizedName("atum:maatsBalance").setCreativeTab(Atum.creativeTab);
	public static final Item hedetetsVenom = new ItemHedetetsVenom(AtumIDS.ITEM_HEDETETSVENOM_ID).setUnlocalizedName("atum:hedetetsVenom").setCreativeTab(Atum.creativeTab);
	public static final Item gebsSolidarity = new ItemGebsSolidarity(AtumIDS.ITEM_GEBSSOLIDARITY_ID, EnumArmorMaterial.DIAMOND, 3, 3).setTextureFile("RubyArtifactArmor_1").setUnlocalizedName("atum:gebsSolidarity").setCreativeTab(Atum.creativeTab);
	public static final Item nutsCall = new ItemNutsCall(AtumIDS.ITEM_NUTSCALL_ID).setUnlocalizedName("atum:nutsCall").setCreativeTab(Atum.creativeTab);
	public static final Item anuketsBounty = new ItemAnuketsBounty(AtumIDS.ITEM_ANUKETSBOUNTY_ID).setUnlocalizedName("atum:anuketsBounty").setCreativeTab(Atum.creativeTab);
	public static final Item mafdetsQuickness = new ItemMafdetsQuickness(AtumIDS.ITEM_MAFDETSQUICKNESS_ID).setUnlocalizedName("atum:mafdetsQuickness").setCreativeTab(Atum.creativeTab);
	public static final Item isisHealing = new ItemIsisHealing(AtumIDS.ITEM_ISISHEALING_ID).setUnlocalizedName("atum:isisHealing").setCreativeTab(Atum.creativeTab);
	public static final Item amunetsHomecoming = new ItemAmunetsHomecoming(AtumIDS.ITEM_AMUNETSHOMECOMING_ID).setUnlocalizedName("atum:amunetsHomecoming").setCreativeTab(Atum.creativeTab);
	public static final Item anubisMercy = new ItemAnubisMercy(AtumIDS.ITEM_ANUBISMERCY_ID).setUnlocalizedName("atum:anubisMercy").setCreativeTab(Atum.creativeTab);
	public static final Item limestoneShovel = new LimestoneShovel(AtumIDS.ITEM_LIMESTONESHOVEL_ID, EnumToolMaterial.STONE).setUnlocalizedName("atum:limestoneShovel").setCreativeTab(Atum.creativeTab);
	public static final Item limestonePickaxe = new LimestonePickaxe(AtumIDS.ITEM_LIMESTONEPICKAXE_ID, EnumToolMaterial.STONE).setUnlocalizedName("atum:limestonePickaxe").setCreativeTab(Atum.creativeTab);
	public static final Item limestoneAxe = new LimestoneAxe(AtumIDS.ITEM_LIMESTONEAXE_ID, EnumToolMaterial.STONE).setUnlocalizedName("atum:limestoneAxe").setCreativeTab(Atum.creativeTab);
	public static final Item limestoneSword = new LimestoneSword(AtumIDS.ITEM_LIMESTONESWORD_ID, EnumToolMaterial.STONE).setUnlocalizedName("atum:limestoneSword").setCreativeTab(Atum.creativeTab);
	public static final Item limestoneHoe = new LimestoneHoe(AtumIDS.ITEM_LIMESTONEHOE_ID, EnumToolMaterial.STONE).setUnlocalizedName("atum:limestoneHoe").setCreativeTab(Atum.creativeTab);
	private static EnumArmorMaterial mummyEnum = EnumHelper.addArmorMaterial("Mummy", 5, new int[]{1, 3, 2, 1}, 15);
	public static final Item mummyHelmet = new ItemTexturedArmor(AtumIDS.ITEM_MUMMYHELMET_ID, mummyEnum, 0, 0).setRepairItem(AtumIDS.ITEM_SCRAP_ID).setTextureFile("MummyArmor_1").setUnlocalizedName("atum:mummyHelmet").setTextureName("atum:MummyHelmet").setCreativeTab(Atum.creativeTab);
	public static final Item mummyChest = new ItemTexturedArmor(AtumIDS.ITEM_MUMMYCHEST_ID, mummyEnum, 0, 1).setRepairItem(AtumIDS.ITEM_SCRAP_ID).setTextureFile("MummyArmor_1").setUnlocalizedName("atum:mummyChest").setTextureName("atum:MummyChest").setCreativeTab(Atum.creativeTab);
	public static final Item mummyLegs = new ItemTexturedArmor(AtumIDS.ITEM_MUMMYLEGS_ID, mummyEnum, 0, 2).setRepairItem(AtumIDS.ITEM_SCRAP_ID).setTextureFile("MummyArmor_2").setUnlocalizedName("atum:mummyLegs").setTextureName("atum:MummyLegs").setCreativeTab(Atum.creativeTab);
	public static final Item mummyBoots = new ItemTexturedArmor(AtumIDS.ITEM_MUMMYBOOTS_ID, mummyEnum, 0, 3).setRepairItem(AtumIDS.ITEM_SCRAP_ID).setTextureFile("MummyArmor_1").setUnlocalizedName("atum:mummyBoots").setTextureName("atum:MummyBoots").setCreativeTab(Atum.creativeTab);
	private static EnumArmorMaterial wandererEnum = EnumHelper.addArmorMaterial("Wanderer", 10, new int[]{2, 3, 3, 2}, 15);
	public static final Item wandererHelmet = new ItemTexturedArmor(AtumIDS.ITEM_WANDERERHELMET_ID, wandererEnum, 0, 0).setRepairItem(AtumIDS.ITEM_LINEN_ID).setTextureFile("WandererArmor_1").setUnlocalizedName("atum:wandererHelmet").setTextureName("atum:WandererHelmet").setCreativeTab(Atum.creativeTab);
	public static final Item wandererChest = new ItemTexturedArmor(AtumIDS.ITEM_WANDERERCHEST_ID, wandererEnum, 0, 1).setRepairItem(AtumIDS.ITEM_LINEN_ID).setTextureFile("WandererArmor_1").setUnlocalizedName("atum:wandererChest").setTextureName("atum:WandererChest").setCreativeTab(Atum.creativeTab);
	public static final Item wandererLegs = new ItemTexturedArmor(AtumIDS.ITEM_WANDERERLEGS_ID, wandererEnum, 0, 2).setRepairItem(AtumIDS.ITEM_LINEN_ID).setTextureFile("WandererArmor_2").setUnlocalizedName("atum:wandererLegs").setTextureName("atum:WandererLegs").setCreativeTab(Atum.creativeTab);
	public static final Item wandererBoots = new ItemTexturedArmor(AtumIDS.ITEM_WANDERERBOOTS_ID, wandererEnum, 0, 3).setRepairItem(AtumIDS.ITEM_LINEN_ID).setTextureFile("WandererArmor_1").setUnlocalizedName("atum:wandererBoots").setTextureName("atum:WandererBoots").setCreativeTab(Atum.creativeTab);
	private static EnumArmorMaterial desertEnum = EnumHelper.addArmorMaterial("Desert", 20, new int[]{3, 6, 5, 3}, 15);
	public static final Item desertHelmet = new ItemTexturedArmor(AtumIDS.ITEM_DESERTHELMET_ID, desertEnum, 0, 0).setRepairItem(Item.ingotIron.itemID).setTextureFile("DesertArmor_1").setUnlocalizedName("atum:desertHelmet").setTextureName("atum:DesertHelmet").setCreativeTab(Atum.creativeTab);
	public static final Item desertChest = new ItemTexturedArmor(AtumIDS.ITEM_DESERTCHEST_ID, desertEnum, 0, 1).setRepairItem(Item.ingotIron.itemID).setTextureFile("DesertArmor_1").setUnlocalizedName("atum:desertChest").setTextureName("atum:DesertChest").setCreativeTab(Atum.creativeTab);
	public static final Item desertLegs = new ItemTexturedArmor(AtumIDS.ITEM_DESERTLEGS_ID, desertEnum, 0, 2).setRepairItem(Item.ingotIron.itemID).setTextureFile("DesertArmor_2").setUnlocalizedName("atum:desertLegs").setTextureName("atum:DesertLegs").setCreativeTab(Atum.creativeTab);
	public static final Item desertBoots = new ItemTexturedArmor(AtumIDS.ITEM_DESERTBOOTS_ID, desertEnum, 0, 3).setRepairItem(Item.ingotIron.itemID).setTextureFile("DesertArmor_1").setUnlocalizedName("atum:desertBoots").setTextureName("atum:DesertBoots").setCreativeTab(Atum.creativeTab);
	public static final Item papyrusPlant = new ItemPapyrusPlant(AtumIDS.ITEM_PAPYRUSPLANT_ID, Blocks.BLOCK_PAPYRUS).setUnlocalizedName("atum:papyrusPlantItem").setCreativeTab(Atum.creativeTab);
	public static final Item ectoplasm = new Item(AtumIDS.ITEM_ECTOPLASM_ID).setUnlocalizedName("atum:ectoplasm").setTextureName("atum:Ectoplasm").setCreativeTab(Atum.creativeTab);
	public static final Item stoneChunk = new Item(AtumIDS.ITEM_STONECHUNK_ID).setUnlocalizedName("atum:stoneChunk").setTextureName("atum:StoneChunk").setCreativeTab(Atum.creativeTab);
	public static final Item scrap = new Item(AtumIDS.ITEM_SCRAP_ID).setUnlocalizedName("atum:clothScrap").setTextureName("atum:ClothScrap").setCreativeTab(Atum.creativeTab);
	public static final Item scroll = new Item(AtumIDS.ITEM_SCROLL_ID).setUnlocalizedName("atum:scroll").setTextureName("atum:Scroll").setCreativeTab(Atum.creativeTab);
	public static final Item pelt = new Item(AtumIDS.ITEM_PELT_ID).setUnlocalizedName("atum:wolfPelt").setTextureName("atum:WolfPelt").setCreativeTab(Atum.creativeTab);
	public static final Item linen = new Item(AtumIDS.ITEM_LINEN_ID).setUnlocalizedName("atum:linen").setTextureName("atum:Linen").setCreativeTab(Atum.creativeTab);
	public static final Item flax = new Item(AtumIDS.ITEM_FLAX_ID).setUnlocalizedName("atum:flaxItem").setTextureName("atum:FlaxItem").setCreativeTab(Atum.creativeTab);
	public static final Item flaxSeeds = new ItemSeeds(AtumIDS.ITEM_FLAXSEEDS_ID, Blocks.BLOCK_FLAX.blockID, Block.tilledField.blockID).setUnlocalizedName("atum:flaxSeeds").setTextureName("atum:FlaxSeeds").setCreativeTab(Atum.creativeTab);
	public static final Item fish = new ItemFish(AtumIDS.ITEM_FISH_ID).setUnlocalizedName("atum:fish").setCreativeTab(Atum.creativeTab);
	public static final Item neithsAudacity = new ItemNeithsAudacity(AtumIDS.ITEM_NEITHSAUDACITY_ID).setUnlocalizedName("atum:neithsAudacity").setCreativeTab(Atum.creativeTab);
	public static final Item spear = new ItemSpear(AtumIDS.ITEM_SPEAR_ID).setUnlocalizedName("atum:spear").setCreativeTab(Atum.creativeTab);

	public void registerItems() {
		this.register(ITEM_SCARAB);
		this.register(ITEM_LOOT);
		this.register(ITEM_DATE);
		this.register(ITEM_SCIMITAR);
		this.register(ITEM_GREATSWORD);
		this.register(ITEM_SCEPTER);
		this.register(ITEM_STONESOLDIER_SWORD);
		this.register(ITEM_BOW);
		this.register(ptahsPick);
		this.register(sobeksRage);
		this.register(osirisWill);
		this.register(akersToil);
		this.register(gebsBlessing);
		this.register(atensFury);
		this.register(rasGlory);
		this.register(sekhmetsWrath);
		this.register(nutsAgility);
		this.register(horusFlight);
		this.register(monthusStrike);
		this.register(anhursMight);
		this.register(hedetetsSting);
		this.register(horusSoaring);
		this.register(shusBreath);
		this.register(ptahsDestruction);
		this.register(monthusBlast);
		this.register(nusFlux);
		this.register(mnevisHorns);
		this.register(isisEmbrace);
		this.register(maatsBalance);
		this.register(hedetetsVenom);
		this.register(gebsSolidarity);
		this.register(nutsCall);
		this.register(anuketsBounty);
		this.register(mafdetsQuickness);
		this.register(isisHealing);
		this.register(amunetsHomecoming);
		this.register(anubisMercy);
		this.register(limestoneShovel);
		this.register(limestonePickaxe);
		this.register(limestoneAxe);
		this.register(limestoneSword);
		this.register(limestoneHoe);
		this.register(mummyHelmet);
		this.register(mummyChest);
		this.register(mummyLegs);
		this.register(mummyBoots);
		this.register(wandererHelmet);
		this.register(wandererChest);
		this.register(wandererLegs);
		this.register(wandererBoots);
		this.register(desertHelmet);
		this.register(desertChest);
		this.register(desertLegs);
		this.register(desertBoots);
		this.register(papyrusPlant);
		this.register(ectoplasm);
		this.register(stoneChunk);
		this.register(scrap);
		this.register(scroll);
		this.register(pelt);
		this.register(linen);
		this.register(flax);
		this.register(flaxSeeds);
		this.register(fish);
		this.register(neithsAudacity);
		this.register(spear);

		this.addLanguages();
	}


	private void register(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName());
		this.name(item, item.getUnlocalizedName().split(":")[1]);
	}

	private void name(Item item, String tag) {
		LanguageRegistry.addName(item, LocalizationHelper.localize("item." + tag + ".name"));
	}

	private void name(ItemStack item, String tag) {
		LanguageRegistry.addName(item, LocalizationHelper.localize("item." + tag + ".name"));
	}

	private void addLanguages() {
		name(new ItemStack(fish, 1, 0), "fish1");
		name(new ItemStack(fish, 1, 1), "fish2");
		name(new ItemStack(fish, 1, 2), "fish3");
		name(new ItemStack(fish, 1, 3), "fish4");
	}

}
