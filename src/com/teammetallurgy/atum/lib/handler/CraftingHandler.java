package com.teammetallurgy.atum.lib.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.teammetallurgy.atum.blocks.Blocks;
import com.teammetallurgy.atum.items.Items;

import cpw.mods.fml.common.registry.GameRegistry;

public enum CraftingHandler {
	INSTANCE;

	public void register() {
		addRecipes();
		addSmeltingRecipes();
		addShapelessRecipes();
	}

	private void addRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.BLOCK_LARGEBRICK, 4), "XX", "XX", 'X', Blocks.BLOCK_STONE));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.BLOCK_SMALLBRICK, 4), "XX", "XX", 'X', Blocks.BLOCK_LIMESTONECOBBLE));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.BLOCK_CARVEDBRICK, 1), Blocks.BLOCK_STONE));
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_SMOOTHSTAIRS, 6), "X  ", "XX ", "XXX", 'X', Blocks.BLOCK_STONE);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_COBBLESTAIRS, 6), "X  ", "XX ", "XXX", 'X', Blocks.BLOCK_LIMESTONECOBBLE);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_LARGESTONESTAIRS, 6), "X  ", "XX ", "XXX", 'X', Blocks.BLOCK_LARGEBRICK);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_SMALLSTONESTAIRS, 6), "X  ", "XX ", "XXX", 'X', Blocks.BLOCK_SMALLBRICK);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_SLABS, 6, 0), "XXX", 'X', Blocks.BLOCK_STONE);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_SLABS, 6, 1), "XXX", 'X', Blocks.BLOCK_LIMESTONECOBBLE);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_SLABS, 6, 2), "XXX", 'X', Blocks.BLOCK_LARGEBRICK);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_SLABS, 6, 3), "XXX", 'X', Blocks.BLOCK_SMALLBRICK);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_WALL, 6, 0), "XXX", "XXX", 'X', Blocks.BLOCK_STONE);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_WALL, 6, 1), "XXX", "XXX", 'X', Blocks.BLOCK_LIMESTONECOBBLE);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_WALL, 6, 2), "XXX", "XXX", 'X', Blocks.BLOCK_LARGEBRICK);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_WALL, 6, 3), "XXX", "XXX", 'X', Blocks.BLOCK_SMALLBRICK);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_FRAMEDGLASS), " X ", "XSX", " X ", 'X', Item.stick, 'S', Blocks.BLOCK_CRYSTALGLASS);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_CRACKEDLARGEBRICK, 4), "XX", "XX", 'X', Items.stoneChunk);
		GameRegistry.addRecipe(new ItemStack(Item.expBottle), " X ", "XBX", " X ", 'X', Items.ectoplasm, 'B', Item.potion);
		GameRegistry.addRecipe(new ItemStack(Items.limestoneSword), "L", "L", "S", 'L', Blocks.BLOCK_LIMESTONECOBBLE, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(Items.limestoneShovel), "L", "S", "S", 'L', Blocks.BLOCK_LIMESTONECOBBLE, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(Items.limestonePickaxe), "LLL", " S ", " S ", 'L', Blocks.BLOCK_LIMESTONECOBBLE, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(Items.limestoneAxe), "LL", "LS", " S", 'L', Blocks.BLOCK_LIMESTONECOBBLE, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(Items.limestoneHoe), "LL", " S", " S", 'L', Blocks.BLOCK_LIMESTONECOBBLE, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(Items.mummyHelmet), "XXX", "X X", 'X', Items.scrap);
		GameRegistry.addRecipe(new ItemStack(Items.mummyChest), "X X", "XXX", "XXX", 'X', Items.scrap);
		GameRegistry.addRecipe(new ItemStack(Items.mummyLegs), "XXX", "X X", "X X", 'X', Items.scrap);
		GameRegistry.addRecipe(new ItemStack(Items.mummyBoots), "X X", "X X", 'X', Items.scrap);
		GameRegistry.addRecipe(new ItemStack(Items.wandererHelmet), "XXX", "X X", 'X', Items.linen);
		GameRegistry.addRecipe(new ItemStack(Items.wandererChest), "X X", "XXX", "XXX", 'X', Items.linen);
		GameRegistry.addRecipe(new ItemStack(Items.wandererLegs), "XXX", "X X", "X X", 'X', Items.linen);
		GameRegistry.addRecipe(new ItemStack(Items.wandererBoots), "X X", "X X", 'X', Items.linen);
		GameRegistry.addRecipe(new ItemStack(Items.linen), "XXX", 'X', Items.flax);
		GameRegistry.addRecipe(new ItemStack(Item.glassBottle, 3), "X X", " X ", 'X', Blocks.BLOCK_CRYSTALGLASS);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_THINCRYSTALGLASS, 16), "XXX", "XXX", 'X', Blocks.BLOCK_CRYSTALGLASS);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_THINFRAMEDGLASS, 16), "XXX", "XXX", 'X', Blocks.BLOCK_FRAMEDGLASS);
		GameRegistry.addRecipe(new ItemStack(Items.scroll), "XXX", "SXS", "XXX", 'X', Items.papyrusPlant, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(Items.ITEM_SCARAB), " G ", "GDG", " G ", 'G', Item.ingotGold, 'D', Item.diamond);
		GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_FURNACEIDLE), "XXX", "X X", "XXX", 'X', Blocks.BLOCK_LIMESTONECOBBLE);
	}

	private void addSmeltingRecipes() {
		FurnaceRecipes.smelting().addSmelting(Blocks.BLOCK_IRONORE.blockID, 0, new ItemStack(Item.ingotIron), 0.7F);
		FurnaceRecipes.smelting().addSmelting(Blocks.BLOCK_COALORE.blockID, new ItemStack(Item.coal), 0.1F);
		FurnaceRecipes.smelting().addSmelting(Blocks.BLOCK_REDSTONEORE.blockID, new ItemStack(Item.redstone), 0.7F);
		FurnaceRecipes.smelting().addSmelting(Blocks.BLOCK_LAPISORE.blockID, new ItemStack(Item.dyePowder, 1, 4), 0.2F);
		FurnaceRecipes.smelting().addSmelting(Blocks.BLOCK_GOLDORE.blockID, new ItemStack(Item.ingotGold), 1.0F);
		FurnaceRecipes.smelting().addSmelting(Blocks.BLOCK_DIAMONDORE.blockID, new ItemStack(Item.diamond), 1.0F);
		FurnaceRecipes.smelting().addSmelting(Blocks.BLOCK_LOG.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		FurnaceRecipes.smelting().addSmelting(Blocks.BLOCK_LIMESTONECOBBLE.blockID, new ItemStack(Blocks.BLOCK_STONE), 0.1F);
		FurnaceRecipes.smelting().addSmelting(Blocks.BLOCK_SAND.blockID, new ItemStack(Blocks.BLOCK_CRYSTALGLASS), 0.1F);
	}

	private void addShapelessRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.BLOCK_PLANKS, 4), Blocks.BLOCK_LOG);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.desertHelmet), Items.wandererHelmet, Item.helmetIron);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.desertChest), Items.wandererChest, Item.plateIron);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.desertLegs), Items.wandererLegs, Item.legsIron);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.desertBoots), Items.wandererBoots, Item.bootsIron);
		GameRegistry.addShapelessRecipe(new ItemStack(Block.sand), Blocks.BLOCK_SAND);
		GameRegistry.addShapelessRecipe(new ItemStack(Item.silk, 3), Items.flax);
	}

}
