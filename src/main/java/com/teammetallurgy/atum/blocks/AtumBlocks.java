package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.blocks.tileentity.TileEntityBurningTrap;
import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityChestSpawner;
import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityPharaohChest;
import com.teammetallurgy.atum.blocks.tileentity.furnace.TileEntityLimestoneFurnace;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;

public class AtumBlocks {
	public static BlockPortal BLOCK_PORTAL = new BlockPortal();
	public static Block BLOCK_CURSEDCHEST = new BlockChestSpawner();
	public static Block BLOCK_SAND = new BlockSands();
	public static Block BLOCK_STONE = new BlockStones();
	public static Block BLOCK_WALL = new BlockWalls(BLOCK_STONE);
	public static Block BLOCK_LIMESTONECOBBLE = new BlockAtum("cobble").setBlockTextureName("atum:AtumCobble");
	public static Block BLOCK_LARGEBRICK = new BlockAtum("largeBrick").setBlockTextureName("atum:AtumBrickLarge");
	public static Block BLOCK_SMALLBRICK = new BlockAtum("smallBrick").setBlockTextureName("atum:AtumBrickSmall");
	public static Block BLOCK_CARVEDBRICK = new BlockAtum("carvedBrick").setBlockTextureName("atum:AtumBrickCarved");
	public static Block BLOCK_SLABS = new BlockAtumSlab(false).setBlockName("slab");
	public static Block BLOCK_DOUBLESLAB = new BlockAtumSlab(true).setBlockName("doubleSlab");
	public static Block BLOCK_SMOOTHSTAIRS = new BlockAtumStairs(BLOCK_STONE, 0).setBlockName("smoothStairs");
	public static Block BLOCK_COBBLESTAIRS = new BlockAtumStairs(BLOCK_LIMESTONECOBBLE, 0).setBlockName("cobbleStairs");
	public static Block BLOCK_LARGESTONESTAIRS = new BlockAtumStairs(BLOCK_LARGEBRICK, 0).setBlockName("largeStairs");
	public static Block BLOCK_SMALLSTONESTAIRS = new BlockAtumStairs(BLOCK_SMALLBRICK, 0).setBlockName("smallStairs");
	public static Block BLOCK_SANDLAYERED = new BlockSandLayered();
	public static Block BLOCK_CRACKEDLARGEBRICK = new BlockAtum("crackedLargeBrick").setBlockTextureName("atum:AtumCrackedLargeBrick");
	public static Block BLOCK_CRYSTALGLASS = new BlockAtumGlass("atum:AtumCrystalGlass").setBlockName("crystalGlass");
	public static Block BLOCK_FRAMEDGLASS = new BlockAtumGlass("atum:AtumFramedGlass").setBlockName("framedGlass");
	public static Block BLOCK_PALMSAPLING = new BlockPalmSapling();
	public static Block BLOCK_DATEBLOCK = new BlockDate();
	public static Block BLOCK_SHRUB = new BlockShrub().setBlockName("shrub").setBlockTextureName("atum:Shrub");
	public static Block BLOCK_WEED = new BlockShrub().setBlockName("weed").setBlockTextureName("atum:DeadBush");
	public static Block BLOCK_PAPYRUS = new BlockPapyrus();
	public static Block BLOCK_FLAX = new BlockFlax();
	public static Block BLOCK_FERTILESOIL = new BlockFertileSoil();
	public static Block BLOCK_FERTILESOILTILLED = new BlockFertileSoilTilled();
	public static Block BLOCK_LOG = new BlockPalmLog();
	public static Block BLOCK_LEAVES = new BlockLeave();
	public static Block BLOCK_PLANKS = new BlockAtumPlank();
	public static Block BLOCK_THINCRYSTALGLASS = new BlockAtumPane("atum:AtumCrystalGlass", "atum:thinglass_top").setBlockName("thinCrystalGlass");
	public static Block BLOCK_THINFRAMEDGLASS = new BlockAtumPane("atum:AtumFramedGlass", "atum:thinglass_top").setBlockName("thinFramedGlass");
	public static Block BLOCK_TRAPARROW = new BlockBurningTrap();
	public static Block BLOCK_PHARAOHCHEST = new BlockPharaohChest();
	public static Block BLOCK_REDSTONEORE = new BlockAtumRedstone();
	public static Block BLOCK_COALORE = new BlockAtumOres().setBlockName("coalOre").setBlockTextureName("atum:AtumCoal");
	public static Block BLOCK_IRONORE = new BlockAtumOres().setBlockName("ironOre").setBlockTextureName("atum:AtumIron");
	public static Block BLOCK_GOLDORE = new BlockAtumOres().setBlockName("goldOre").setBlockTextureName("atum:AtumGold");
	public static Block BLOCK_LAPISORE = new BlockAtumOres().setBlockName("lapisOre").setBlockTextureName("atum:AtumLapis");
	public static Block BLOCK_DIAMONDORE = new BlockAtumOres().setBlockName("diamondOre").setBlockTextureName("atum:AtumDiamond");
	public static Block BLOCK_FURNACEIDLE = new BlockLimeStoneFurnace(false).setBlockName("furnaceIdle");
	public static Block BLOCK_FURNACEBURNING = new BlockLimeStoneFurnace(true).setBlockName("furnaceBurning");

	public AtumBlocks(){
		registerBlocks();
	}
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

		ForgeHooks.canToolHarvestBlock(BLOCK_SAND, 0, new ItemStack(Items.iron_shovel));
		BLOCK_SAND.setHarvestLevel("shovel", 0);
		BLOCK_COALORE.setHarvestLevel("pickaxe", 0);
		BLOCK_IRONORE.setHarvestLevel("pickaxe", 1);
		BLOCK_GOLDORE.setHarvestLevel("pickaxe", 2);
		BLOCK_LAPISORE.setHarvestLevel("pickaxe", 1);
		BLOCK_DIAMONDORE.setHarvestLevel("pickaxe", 2);
		BLOCK_REDSTONEORE.setHarvestLevel("pickaxe", 2);

		Blocks.fire.setFireInfo(BLOCK_PLANKS, 5, 20);
		Blocks.fire.setFireInfo(BLOCK_LEAVES, 30, 60);
		Blocks.fire.setFireInfo(BLOCK_LOG, 5, 5);

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
		if (!(b instanceof BlockDate)) {
			b.setCreativeTab(Atum.creativeTab);
		}
		GameRegistry.registerBlock(b, b.getUnlocalizedName());
	}
}
