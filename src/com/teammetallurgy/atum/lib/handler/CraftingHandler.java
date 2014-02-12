package com.teammetallurgy.atum.lib.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.items.AtumItems;

import cpw.mods.fml.common.registry.GameRegistry;

public enum CraftingHandler {
	INSTANCE;

	public void register() {
		addRecipes();
		addSmeltingRecipes();
		addShapelessRecipes();
	}

	private void addRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.BLOCK_LARGEBRICK, 4), "XX", "XX", 'X', AtumBlocks.BLOCK_STONE));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.BLOCK_SMALLBRICK, 4), "XX", "XX", 'X', AtumBlocks.BLOCK_LIMESTONECOBBLE));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(AtumBlocks.BLOCK_CARVEDBRICK, 1), AtumBlocks.BLOCK_STONE));
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_SMOOTHSTAIRS, 6), "X  ", "XX ", "XXX", 'X', AtumBlocks.BLOCK_STONE);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_COBBLESTAIRS, 6), "X  ", "XX ", "XXX", 'X', AtumBlocks.BLOCK_LIMESTONECOBBLE);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_LARGESTONESTAIRS, 6), "X  ", "XX ", "XXX", 'X', AtumBlocks.BLOCK_LARGEBRICK);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_SMALLSTONESTAIRS, 6), "X  ", "XX ", "XXX", 'X', AtumBlocks.BLOCK_SMALLBRICK);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_SLABS, 6, 0), "XXX", 'X', AtumBlocks.BLOCK_STONE);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_SLABS, 6, 1), "XXX", 'X', AtumBlocks.BLOCK_LIMESTONECOBBLE);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_SLABS, 6, 2), "XXX", 'X', AtumBlocks.BLOCK_LARGEBRICK);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_SLABS, 6, 3), "XXX", 'X', AtumBlocks.BLOCK_SMALLBRICK);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_WALL, 6, 0), "XXX", "XXX", 'X', AtumBlocks.BLOCK_STONE);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_WALL, 6, 1), "XXX", "XXX", 'X', AtumBlocks.BLOCK_LIMESTONECOBBLE);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_WALL, 6, 2), "XXX", "XXX", 'X', AtumBlocks.BLOCK_LARGEBRICK);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_WALL, 6, 3), "XXX", "XXX", 'X', AtumBlocks.BLOCK_SMALLBRICK);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_FRAMEDGLASS), " X ", "XSX", " X ", 'X', Item.stick, 'S', AtumBlocks.BLOCK_CRYSTALGLASS);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_CRACKEDLARGEBRICK, 4), "XX", "XX", 'X', AtumItems.stoneChunk);
		GameRegistry.addRecipe(new ItemStack(Item.expBottle), " X ", "XBX", " X ", 'X', AtumItems.ectoplasm, 'B', Item.potion);
		GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneSword), "L", "L", "S", 'L', AtumBlocks.BLOCK_LIMESTONECOBBLE, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneShovel), "L", "S", "S", 'L', AtumBlocks.BLOCK_LIMESTONECOBBLE, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AtumItems.limestonePickaxe), "LLL", " S ", " S ", 'L', AtumBlocks.BLOCK_LIMESTONECOBBLE, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneAxe), "LL", "LS", " S", 'L', AtumBlocks.BLOCK_LIMESTONECOBBLE, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneHoe), "LL", " S", " S", 'L', AtumBlocks.BLOCK_LIMESTONECOBBLE, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AtumItems.mummyHelmet), "XXX", "X X", 'X', AtumItems.scrap);
		GameRegistry.addRecipe(new ItemStack(AtumItems.mummyChest), "X X", "XXX", "XXX", 'X', AtumItems.scrap);
		GameRegistry.addRecipe(new ItemStack(AtumItems.mummyLegs), "XXX", "X X", "X X", 'X', AtumItems.scrap);
		GameRegistry.addRecipe(new ItemStack(AtumItems.mummyBoots), "X X", "X X", 'X', AtumItems.scrap);
		GameRegistry.addRecipe(new ItemStack(AtumItems.wandererHelmet), "XXX", "X X", 'X', AtumItems.linen);
		GameRegistry.addRecipe(new ItemStack(AtumItems.wandererChest), "X X", "XXX", "XXX", 'X', AtumItems.linen);
		GameRegistry.addRecipe(new ItemStack(AtumItems.wandererLegs), "XXX", "X X", "X X", 'X', AtumItems.linen);
		GameRegistry.addRecipe(new ItemStack(AtumItems.wandererBoots), "X X", "X X", 'X', AtumItems.linen);
		GameRegistry.addRecipe(new ItemStack(AtumItems.linen), "XXX", 'X', AtumItems.flax);
		GameRegistry.addRecipe(new ItemStack(Item.glassBottle, 3), "X X", " X ", 'X', AtumBlocks.BLOCK_CRYSTALGLASS);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_THINCRYSTALGLASS, 16), "XXX", "XXX", 'X', AtumBlocks.BLOCK_CRYSTALGLASS);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_THINFRAMEDGLASS, 16), "XXX", "XXX", 'X', AtumBlocks.BLOCK_FRAMEDGLASS);
		GameRegistry.addRecipe(new ItemStack(AtumItems.scroll), "XXX", "SXS", "XXX", 'X', AtumItems.papyrusPlant, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AtumItems.ITEM_SCARAB), " G ", "GDG", " G ", 'G', Item.ingotGold, 'D', Item.diamond);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.BLOCK_FURNACEIDLE), "XXX", "X X", "XXX", 'X', AtumBlocks.BLOCK_LIMESTONECOBBLE);
	}

	private void addSmeltingRecipes() {
		FurnaceRecipes.smelting().addSmelting(AtumBlocks.BLOCK_IRONORE.blockID, 0, new ItemStack(Item.ingotIron), 0.7F);
		FurnaceRecipes.smelting().addSmelting(AtumBlocks.BLOCK_COALORE.blockID, new ItemStack(Item.coal), 0.1F);
		FurnaceRecipes.smelting().addSmelting(AtumBlocks.BLOCK_REDSTONEORE.blockID, new ItemStack(Item.redstone), 0.7F);
		FurnaceRecipes.smelting().addSmelting(AtumBlocks.BLOCK_LAPISORE.blockID, new ItemStack(Item.dyePowder, 1, 4), 0.2F);
		FurnaceRecipes.smelting().addSmelting(AtumBlocks.BLOCK_GOLDORE.blockID, new ItemStack(Item.ingotGold), 1.0F);
		FurnaceRecipes.smelting().addSmelting(AtumBlocks.BLOCK_DIAMONDORE.blockID, new ItemStack(Item.diamond), 1.0F);
		FurnaceRecipes.smelting().addSmelting(AtumBlocks.BLOCK_LOG.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
		FurnaceRecipes.smelting().addSmelting(AtumBlocks.BLOCK_LIMESTONECOBBLE.blockID, new ItemStack(AtumBlocks.BLOCK_STONE), 0.1F);
		FurnaceRecipes.smelting().addSmelting(AtumBlocks.BLOCK_SAND.blockID, new ItemStack(AtumBlocks.BLOCK_CRYSTALGLASS), 0.1F);
	}

	private void addShapelessRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.BLOCK_PLANKS, 4), AtumBlocks.BLOCK_LOG);
		GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertHelmet), AtumItems.wandererHelmet, Item.helmetIron);
		GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertChest), AtumItems.wandererChest, Item.plateIron);
		GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertLegs), AtumItems.wandererLegs, Item.legsIron);
		GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertBoots), AtumItems.wandererBoots, Item.bootsIron);
		GameRegistry.addShapelessRecipe(new ItemStack(Block.sand), AtumBlocks.BLOCK_SAND);
		GameRegistry.addShapelessRecipe(new ItemStack(Item.silk, 3), AtumItems.flax);
	}

}
