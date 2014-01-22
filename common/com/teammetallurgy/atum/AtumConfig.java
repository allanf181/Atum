package com.teammetallurgy.atum;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class AtumConfig {

	private final Configuration CONFIG;

	public AtumConfig(File file) {
		this.CONFIG = new Configuration(file);
	}

	public void load() {
		this.CONFIG.load();
		AtumIDS.BLOCK_PORTAL_ID = getBlock("Portal Block", 1024);
		AtumIDS.BLOCK_CURSEDCHEST_ID = getBlock("CursedChest", 1025);
		AtumIDS.BLOCK_SAND_ID = getBlock("Strange Sand", 1026);
		AtumIDS.BLOCK_STONE_ID = getBlock("Limestone", 1027);
		AtumIDS.BLOCK_COBBLE_ID = getBlock("Cracked Limestone", 1028);
		AtumIDS.BLOCK_LARGEBRICK_ID = getBlock("Large Brick", 1029);
		AtumIDS.BLOCK_SMALLBRICK_ID = getBlock("Small Brick", 1030);
		AtumIDS.BLOCK_CARVEDBRICK_ID = getBlock("Carved Limestone", 1031);
		AtumIDS.BLOCK_SLABS_ID = getBlock("Limestone Slabs", 1032);
		AtumIDS.BLOCK_DOUBLE_SLAB_ID = getBlock("Double Limestone Slabs", 1033);
		AtumIDS.BLOCK_SMOOTHSTAIRS_ID = getBlock("Limestone Stairs", 1034);
		AtumIDS.BLOCK_COBBLESTAIRS_ID = getBlock("Cracked Stairs", 1035);
		AtumIDS.BLOCK_LARGESTONESTAIRS_ID = getBlock("Large Brick Stairs", 1036);
		AtumIDS.BLOCK_SMALLSTONESTAIRS_ID = getBlock("Small Brick Stairs", 1037);
		AtumIDS.BLOCK_SANDLAYERED_ID = getBlock("Sand Layer", 1038);
		AtumIDS.BLOCK_CRACKEDLARGEBRICK_ID = getBlock("Cracked Large Brick", 1039);
		AtumIDS.BLOCK_WALL_ID = getBlock("Limestone Wall", 1040);
		AtumIDS.BLOCK_CRYSTALGLASS_ID = getBlock("Crystal Glass", 1041);
		AtumIDS.BLOCK_FRAMEDGLASS_ID = getBlock("Framed Glass", 1042);
		AtumIDS.BLOCK_PALMSAPLING_ID = getBlock("Palm Sapling", 1043);
		AtumIDS.BLOCK_DATEBLOCK_ID = getBlock("Date Block", 1044);
		AtumIDS.BLOCK_SHRUB_ID = getBlock("Shrub", 1045);
		AtumIDS.BLOCK_WEED_ID = getBlock("Weed", 1046);
		AtumIDS.BLOCK_PAPYRUS_ID = getBlock("Papyrus", 1047);
		AtumIDS.BLOCK_FLAX_ID = getBlock("Flax", 1048);
		AtumIDS.BLOCK_FERTILESOIL_ID = getBlock("Fertile Soil", 1049);
		AtumIDS.BLOCK_FERTILESOILTILLED_ID = getBlock("Fertile Soil Tilled", 1050);
		AtumIDS.BLOCK_LOG_ID = getBlock("Palm Log", 1051);
		AtumIDS.BLOCK_LEAVES_ID = getBlock("Palm Leaves", 1052);
		AtumIDS.BLOCK_PLANKS_ID = getBlock("Palm Planks", 1053);
		AtumIDS.BLOCK_THINCRYSTALGLASS_ID = getBlock("Thin Crystal Glass", 1054);
		AtumIDS.BLOCK_THINFRAMEDGLASS_ID = getBlock("Thin Framed Glass", 1055);
		AtumIDS.BLOCK_TRAPARROW_ID = getBlock("Trap Arrows", 1056);
		AtumIDS.BLOCK_PHARAOHCHEST_ID = getBlock("Pharaoh Chest", 1057);
		AtumIDS.BLOCK_REDSTONEORE_ID = getBlock("Atum Redstone Ore", 1058);
		AtumIDS.BLOCK_COALORE_ID = getBlock("Atum Coal Ore", 1059);
		AtumIDS.BLOCK_IRONORE_ID = getBlock("Atum Iron Ore", 1060);
		AtumIDS.BLOCK_GOLDORE_ID = getBlock("Atum Gold Ore", 1061);
		AtumIDS.BLOCK_LAPISORE_ID = getBlock("Atum Lapis Ore", 1062);
		AtumIDS.BLOCK_DIAMONDORE_ID = getBlock("Atum Diamond Ore", 1063);
		AtumIDS.BLOCK_FURNACEIDLE_ID = getBlock("Atum Furnace Idle", 1064);
		AtumIDS.BLOCK_FURNACEBURNING_ID = getBlock("Atum Furnace Burning", 1065);
		
		AtumIDS.ITEM_SCARAB_ID = getItem("Scarab", 5000);
		AtumIDS.ITEM_SCIMITAR_ID = getItem("Scimitar", 5001);
		AtumIDS.ITEM_GREATSWORD_ID = getItem("Great Sword", 5002);
		AtumIDS.ITEM_SCEPTER_ID = getItem("Scepter", 5003);
		AtumIDS.ITEM_STONESOLDIERSWORD_ID = getItem("Stone Soldier Sword", 5004);
		AtumIDS.ITEM_BOW_ID = getItem("Bow", 5005);
		AtumIDS.ITEM_LOOT_ID = getItem("Loot", 5006);
		AtumIDS.ITEM_PTAHSPICK_ID = getItem("Ptahs Pickaxe", 5007);
		AtumIDS.ITEM_SOBEKSRAGE_ID = getItem("Sobeks Rage", 5008);
		AtumIDS.ITEM_OSIRISWILL_ID = getItem("Osiris Will", 5009);
		AtumIDS.ITEM_AKERSTOIL_ID = getItem("AkerStoil", 5010);
		AtumIDS.ITEM_GEBSBLESSING_ID = getItem("Gebs Blessing", 5010);
		AtumIDS.ITEM_ATENSFURY_ID = getItem("Atens Fury", 5011);
		AtumIDS.ITEM_RASGLORY_ID = getItem("Ras Glory", 5012);
		AtumIDS.ITEM_SEKHMETSWRATH_ID = getItem("Sekhmets Wrath", 5013);
		AtumIDS.ITEM_NUTSAGILITY_ID = getItem("Nuts Agility", 5014);
		AtumIDS.ITEM_HORUSFLIGHT_ID = getItem("Horus Flight", 5015);
		AtumIDS.ITEM_MONTHUSSTRIKE_ID = getItem("Monthus Strike", 5016);
		AtumIDS.ITEM_ANHURSMIGHT_ID = getItem("Anhurs Might", 5017);
		AtumIDS.ITEM_HEDETETSSTING_ID = getItem("Hedetet Sting", 5018);
		AtumIDS.ITEM_HORUSSOARING_ID = getItem("Horus Soaring", 5019);
		AtumIDS.ITEM_SHUSBREATH_ID = getItem("Shus Breath", 5020);
		AtumIDS.ITEM_PTAHSDESTRUCTION_ID = getItem("Ptahs Destruction", 5021);
		AtumIDS.ITEM_MONTHUSBLAST_ID = getItem("Monthus Blast", 5022);
		AtumIDS.ITEM_NUSFLUX_ID = getItem("Nus Flux", 5023);
		AtumIDS.ITEM_MNEVISHORNS_ID = getItem("Mnevis Horns", 5024);
		AtumIDS.ITEM_ISISEMBRACE_ID = getItem("Isis Brace", 5025);
		AtumIDS.ITEM_MAATSBALANCE_ID = getItem("Maats Balance", 5026);
		AtumIDS.ITEM_HEDETETSVENOM_ID = getItem("Hedetets Venom", 5027);
		AtumIDS.ITEM_GEBSSOLIDARITY_ID = getItem("Gevs Solidarity", 5028);
		AtumIDS.ITEM_NUTSCALL_ID = getItem("Nuts Call", 5029);
		AtumIDS.ITEM_ANUKETSBOUNTY_ID = getItem("Anukets Bounty", 5030);
		AtumIDS.ITEM_MAFDETSQUICKNESS_ID = getItem("Mafdets Quickness", 5031);
		AtumIDS.ITEM_ISISHEALING_ID = getItem("Isis Healing", 5032);
		AtumIDS.ITEM_AMUNETSHOMECOMING_ID = getItem("Amunets Home Coming", 5033);
		AtumIDS.ITEM_ANUBISMERCY_ID = getItem("Anubis Mercy", 5034);
		AtumIDS.ITEM_LIMESTONESHOVEL_ID = getItem("Limestone Shovel", 5035);
		AtumIDS.ITEM_LIMESTONEPICKAXE_ID = getItem("Limestone Pickaxe", 5036);
		AtumIDS.ITEM_LIMESTONEAXE_ID = getItem("Limestone Axe", 5037);
		AtumIDS.ITEM_LIMESTONESWORD_ID = getItem("Limestone Sword", 5038);
		AtumIDS.ITEM_LIMESTONEHOE_ID = getItem("Limestone Hoe", 5039);
		AtumIDS.ITEM_MUMMYHELMET_ID = getItem("Mummy Helmet", 5040);
		AtumIDS.ITEM_MUMMYCHEST_ID = getItem("Mummy Chestplate", 5041);
		AtumIDS.ITEM_MUMMYLEGS_ID = getItem("Mummy Leggings", 5042);
		AtumIDS.ITEM_MUMMYBOOTS_ID = getItem("Mummy Boots", 5043);
		AtumIDS.ITEM_WANDERERHELMET_ID = getItem("Wanderer Helmet", 5044);
		AtumIDS.ITEM_WANDERERCHEST_ID = getItem("Wanderer Chestplate", 5045);
		AtumIDS.ITEM_WANDERERLEGS_ID = getItem("Wanderer Leggings", 5046);
		AtumIDS.ITEM_WANDERERBOOTS_ID = getItem("Wanderer Boots", 5047);
		AtumIDS.ITEM_DESERTHELMET_ID = getItem("Desert Helmet", 5048);
		AtumIDS.ITEM_DESERTCHEST_ID = getItem("Desert Chestplate", 5049);
		AtumIDS.ITEM_DESERTLEGS_ID = getItem("Desert Leggings", 5050);
		AtumIDS.ITEM_DESERTBOOTS_ID = getItem("Desert Boots", 5051);
		AtumIDS.ITEM_PAPYRUSPLANT_ID = getItem("Papyris Plant", 5052);
		AtumIDS.ITEM_ECTOPLASM_ID = getItem("Ectoplasm", 5053);
		AtumIDS.ITEM_STONECHUNK_ID = getItem("Stone Chunk", 5054);
		AtumIDS.ITEM_SCRAP_ID = getItem("Scrap", 5055);
		AtumIDS.ITEM_SCROLL_ID = getItem("Scroll", 5056);
		AtumIDS.ITEM_PELT_ID = getItem("Pelt", 5057);
		AtumIDS.ITEM_DATE_ID = getItem("Date", 5058);
		AtumIDS.ITEM_LINEN_ID = getItem("Linen", 5059);
		AtumIDS.ITEM_FLAX_ID = getItem("Flax", 5060);
		AtumIDS.ITEM_FLAXSEEDS_ID = getItem("Flax Seed", 5061);
		AtumIDS.ITEM_FISH_ID = getItem("Fish", 5062);
		AtumIDS.ITEM_NEITHSAUDACITY_ID = getItem("Neiths Audacity", 5063);
		AtumIDS.ITEM_SPEAR_ID = getItem("Spear", 5064);
		this.CONFIG.save();
	}

	private int getItem(String name, int defaultId) {
		return this.CONFIG.getItem(name, defaultId).getInt();
	}

	private int getBlock(String name, int defaultId) {
		return this.CONFIG.getBlock(name, defaultId).getInt();
	}

}
