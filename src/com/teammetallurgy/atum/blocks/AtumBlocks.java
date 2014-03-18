package com.teammetallurgy.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.AtumIDS;
import com.teammetallurgy.atum.blocks.tileentity.TileEntityBurningTrap;
import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityChestSpawner;
import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityPharaohChest;
import com.teammetallurgy.atum.blocks.tileentity.furnace.TileEntityLimestoneFurnace;

import cpw.mods.fml.common.registry.GameRegistry;

public enum AtumBlocks {
	INSTANCE;

	public static final BlockPortal BLOCK_PORTAL = new BlockPortal(AtumIDS.BLOCK_PORTAL_ID);
	public static final Block BLOCK_CURSEDCHEST = new BlockChestSpawner(AtumIDS.BLOCK_CURSEDCHEST_ID);
	public static final Block BLOCK_SAND = new BlockSands(AtumIDS.BLOCK_SAND_ID);
	public static final Block BLOCK_STONE = new BlockStones(AtumIDS.BLOCK_STONE_ID);
	public static final Block BLOCK_LIMESTONECOBBLE = new BlockAtum(AtumIDS.BLOCK_COBBLE_ID, "cobble").setTextureName("atum:AtumCobble");
	public static final Block BLOCK_LARGEBRICK = new BlockAtum(AtumIDS.BLOCK_LARGEBRICK_ID, "largeBrick").setTextureName("atum:AtumBrickLarge");
	public static final Block BLOCK_SMALLBRICK = new BlockAtum(AtumIDS.BLOCK_SMALLBRICK_ID, "smallBrick").setTextureName("atum:AtumBrickSmall");
	public static final Block BLOCK_CARVEDBRICK = new BlockAtum(AtumIDS.BLOCK_CARVEDBRICK_ID, "carvedBrick").setTextureName("atum:AtumBrickCarved");
	public static final Block BLOCK_SLABS = new BlockSlab(AtumIDS.BLOCK_SLABS_ID, false).setUnlocalizedName("slab");
	public static final Block BLOCK_DOUBLESLAB = new BlockSlab(AtumIDS.BLOCK_DOUBLE_SLAB_ID, true).setUnlocalizedName("doubleSlab");
	public static final Block BLOCK_SMOOTHSTAIRS = new BlockStair(AtumIDS.BLOCK_SMOOTHSTAIRS_ID, BLOCK_STONE, 0).setUnlocalizedName("smoothStairs");
	public static final Block BLOCK_COBBLESTAIRS = new BlockStair(AtumIDS.BLOCK_COBBLESTAIRS_ID, BLOCK_LIMESTONECOBBLE, 0).setUnlocalizedName("cobbleStairs");
	public static final Block BLOCK_LARGESTONESTAIRS = new BlockStair(AtumIDS.BLOCK_LARGESTONESTAIRS_ID, BLOCK_LARGEBRICK, 0).setUnlocalizedName("largeStairs");
	public static final Block BLOCK_SMALLSTONESTAIRS = new BlockStair(AtumIDS.BLOCK_SMALLSTONESTAIRS_ID, BLOCK_SMALLBRICK, 0).setUnlocalizedName("smallStairs");
	public static final Block BLOCK_SANDLAYERED = new BlockSandLayered(AtumIDS.BLOCK_SANDLAYERED_ID);
	public static final Block BLOCK_CRACKEDLARGEBRICK = new BlockAtum(AtumIDS.BLOCK_CRACKEDLARGEBRICK_ID, "crackedLargeBrick").setTextureName("atum:AtumCrackedLargeBrick");
	public static final Block BLOCK_WALL = new BlockWalls(AtumIDS.BLOCK_WALL_ID, BLOCK_STONE);
	public static final Block BLOCK_CRYSTALGLASS = new BlockAtumGlass(AtumIDS.BLOCK_CRYSTALGLASS_ID, "atum:AtumCrystalGlass").setUnlocalizedName("crystalGlass");
	public static final Block BLOCK_FRAMEDGLASS = new BlockAtumGlass(AtumIDS.BLOCK_FRAMEDGLASS_ID, "atum:AtumFramedGlass").setUnlocalizedName("framedGlass");
	public static final Block BLOCK_PALMSAPLING = new BlockPalmSapling(AtumIDS.BLOCK_PALMSAPLING_ID);
	public static final Block BLOCK_DATEBLOCK = new BlockDate(AtumIDS.BLOCK_DATEBLOCK_ID);
	public static final Block BLOCK_SHRUB = new BlockShrub(AtumIDS.BLOCK_SHRUB_ID).setUnlocalizedName("shrub").setTextureName("atum:shrub");
	public static final Block BLOCK_WEED = new BlockShrub(AtumIDS.BLOCK_WEED_ID).setUnlocalizedName("weed").setTextureName("atum:DeadBush");
	public static final Block BLOCK_PAPYRUS = new BlockPapyrus(AtumIDS.BLOCK_PAPYRUS_ID);
	public static final Block BLOCK_FLAX = new BlockFlax(AtumIDS.BLOCK_FLAX_ID);
	public static final Block BLOCK_FERTILESOIL = new BlockFertileSoil(AtumIDS.BLOCK_FERTILESOIL_ID);
	public static final Block BLOCK_FERTILESOILTILLED = new BlockFertileSoilTilled(AtumIDS.BLOCK_FERTILESOILTILLED_ID);
	public static final Block BLOCK_LOG = new BlockPalmLog(AtumIDS.BLOCK_LOG_ID);
	public static final Block BLOCK_LEAVES = new BlockLeave(AtumIDS.BLOCK_LEAVES_ID);
	public static final Block BLOCK_PLANKS = new Block(AtumIDS.BLOCK_PLANKS_ID, Material.wood).setUnlocalizedName("palmPlanks").setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setTextureName("atum:Planks");
	public static final Block BLOCK_THINCRYSTALGLASS = new BlockAtumPane(AtumIDS.BLOCK_THINCRYSTALGLASS_ID, "atum:AtumCrystalGlass", "atum:thinglass_top").setUnlocalizedName("thinCrystalGlass");
	public static final Block BLOCK_THINFRAMEDGLASS = new BlockAtumPane(AtumIDS.BLOCK_THINFRAMEDGLASS_ID, "atum:AtumFramedGlass", "atum:thinglass_top").setUnlocalizedName("thinFramedGlass");
	public static final Block BLOCK_TRAPARROW = new BlockBurningTrap(AtumIDS.BLOCK_TRAPARROW_ID);
	public static final Block BLOCK_PHARAOHCHEST = new BlockPharaohChest(AtumIDS.BLOCK_PHARAOHCHEST_ID);
	public static final Block BLOCK_REDSTONEORE = new BlockAtumRedstone(AtumIDS.BLOCK_REDSTONEORE_ID);
	public static final Block BLOCK_COALORE = new BlockAtumOres(AtumIDS.BLOCK_COALORE_ID).setUnlocalizedName("coalOre").setTextureName("atum:AtumCoal");
	public static final Block BLOCK_IRONORE = new BlockAtumOres(AtumIDS.BLOCK_IRONORE_ID).setUnlocalizedName("ironOre").setTextureName("atum:AtumIron");
	public static final Block BLOCK_GOLDORE = new BlockAtumOres(AtumIDS.BLOCK_GOLDORE_ID).setUnlocalizedName("goldOre").setTextureName("atum:AtumGold");
	public static final Block BLOCK_LAPISORE = new BlockAtumOres(AtumIDS.BLOCK_LAPISORE_ID).setUnlocalizedName("lapisOre").setTextureName("atum:AtumLapis");
	public static final Block BLOCK_DIAMONDORE = new BlockAtumOres(AtumIDS.BLOCK_DIAMONDORE_ID).setUnlocalizedName("diamondOre").setTextureName("atum:AtumDiamond");
	public static final Block BLOCK_FURNACEIDLE = new BlockLimeStoneFurnace(AtumIDS.BLOCK_FURNACEIDLE_ID, false).setUnlocalizedName("furnaceIdle");
	public static final Block BLOCK_FURNACEBURNING = new BlockLimeStoneFurnace(AtumIDS.BLOCK_FURNACEBURNING_ID, true).setUnlocalizedName("furnaceBurning");

	public void registerBlocks() {
		this.register(BLOCK_PORTAL);
		this.register(BLOCK_CURSEDCHEST);
		this.register(BLOCK_SAND);
		this.register(BLOCK_STONE);
		this.register(BLOCK_LIMESTONECOBBLE);
		this.register(BLOCK_LARGEBRICK);
		this.register(BLOCK_SMALLBRICK);
		this.register(BLOCK_CARVEDBRICK);
		this.register(BLOCK_SMOOTHSTAIRS);
		this.register(BLOCK_COBBLESTAIRS);
		this.register(BLOCK_LARGESTONESTAIRS);
		this.register(BLOCK_SMALLSTONESTAIRS);
		this.register(BLOCK_SANDLAYERED);
		this.register(BLOCK_CRACKEDLARGEBRICK);
		this.register(BLOCK_CRYSTALGLASS);
		this.register(BLOCK_FRAMEDGLASS);
		this.register(BLOCK_PALMSAPLING);
		this.register(BLOCK_DATEBLOCK);
		this.register(BLOCK_SHRUB);
		this.register(BLOCK_WEED);
		this.register(BLOCK_PAPYRUS);
		this.register(BLOCK_FLAX);
		this.register(BLOCK_FERTILESOIL);
		this.register(BLOCK_FERTILESOILTILLED);
		this.register(BLOCK_LOG);
		this.register(BLOCK_LEAVES);
		this.register(BLOCK_PLANKS);
		this.register(BLOCK_THINCRYSTALGLASS);
		this.register(BLOCK_THINFRAMEDGLASS);
		this.register(BLOCK_TRAPARROW);
		this.register(BLOCK_PHARAOHCHEST);
		this.register(BLOCK_REDSTONEORE);
		this.register(BLOCK_COALORE);
		this.register(BLOCK_IRONORE);
		this.register(BLOCK_GOLDORE);
		this.register(BLOCK_LAPISORE);
		this.register(BLOCK_DIAMONDORE);
		this.register(BLOCK_FURNACEIDLE);
		this.register(BLOCK_FURNACEBURNING);

		ForgeHooks.canToolHarvestBlock(BLOCK_SAND, 0, new ItemStack(Item.shovelIron));
		MinecraftForge.setBlockHarvestLevel(BLOCK_SAND, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(BLOCK_COALORE, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(BLOCK_IRONORE, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BLOCK_GOLDORE, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(BLOCK_LAPISORE, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BLOCK_DIAMONDORE, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(BLOCK_REDSTONEORE, "pickaxe", 2);
		Block.setBurnProperties(BLOCK_PLANKS.blockID, 5, 20);
		Block.setBurnProperties(BLOCK_LEAVES.blockID, 30, 60);

		GameRegistry.registerTileEntity(TileEntityChestSpawner.class, "CursedChest");
		GameRegistry.registerTileEntity(TileEntityPharaohChest.class, "PharaohChest");
		GameRegistry.registerTileEntity(TileEntityBurningTrap.class, "BurningTrap");
		GameRegistry.registerTileEntity(TileEntityLimestoneFurnace.class, "LimestoneFurnace");

		OreDictionary.registerOre("logWood", BLOCK_LOG);
		OreDictionary.registerOre("plankWood", BLOCK_PLANKS);

		GameRegistry.registerBlock(BLOCK_SLABS, ItemBlockSlab.class, BLOCK_SLABS.getUnlocalizedName());
		GameRegistry.registerBlock(BLOCK_DOUBLESLAB, ItemBlockSlab.class, BLOCK_DOUBLESLAB.getUnlocalizedName());

		GameRegistry.registerBlock(BLOCK_WALL, ItemBlockWall.class, BLOCK_WALL.getUnlocalizedName());
	}

	private void register(Block b) {
		if(!(b instanceof BlockDate)) {
			b.setCreativeTab(Atum.creativeTab);
		}
		GameRegistry.registerBlock(b, b.getUnlocalizedName());
	}
}
