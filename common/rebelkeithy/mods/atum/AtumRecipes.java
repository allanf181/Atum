package rebelkeithy.mods.atum;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import rebelkeithy.mods.atum.AtumBlocks;
import rebelkeithy.mods.atum.AtumItems;

public class AtumRecipes {

   public static void addRecipes() {
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.largeBrick, 4), new Object[]{"XX", "XX", Character.valueOf('X'), AtumBlocks.stone}));
      GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.smallBrick, 4), new Object[]{"XX", "XX", Character.valueOf('X'), AtumBlocks.cobble}));
      GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(AtumBlocks.carvedBrick, 1), new Object[]{AtumBlocks.stone}));
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.smoothStairs, 6), new Object[]{"X  ", "XX ", "XXX", Character.valueOf('X'), AtumBlocks.stone});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.cobbleStairs, 6), new Object[]{"X  ", "XX ", "XXX", Character.valueOf('X'), AtumBlocks.cobble});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.largeStoneStairs, 6), new Object[]{"X  ", "XX ", "XXX", Character.valueOf('X'), AtumBlocks.largeBrick});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.smallStoneStairs, 6), new Object[]{"X  ", "XX ", "XXX", Character.valueOf('X'), AtumBlocks.smallBrick});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.slabs, 6, 0), new Object[]{"XXX", Character.valueOf('X'), AtumBlocks.stone});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.slabs, 6, 1), new Object[]{"XXX", Character.valueOf('X'), AtumBlocks.cobble});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.slabs, 6, 2), new Object[]{"XXX", Character.valueOf('X'), AtumBlocks.largeBrick});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.slabs, 6, 3), new Object[]{"XXX", Character.valueOf('X'), AtumBlocks.smallBrick});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.wall, 6, 0), new Object[]{"XXX", "XXX", Character.valueOf('X'), AtumBlocks.stone});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.wall, 6, 1), new Object[]{"XXX", "XXX", Character.valueOf('X'), AtumBlocks.cobble});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.wall, 6, 2), new Object[]{"XXX", "XXX", Character.valueOf('X'), AtumBlocks.largeBrick});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.wall, 6, 3), new Object[]{"XXX", "XXX", Character.valueOf('X'), AtumBlocks.smallBrick});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.framedGlass), new Object[]{" X ", "XSX", " X ", Character.valueOf('X'), Item.stick, Character.valueOf('S'), AtumBlocks.crystalGlass});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.crackedLargeBrick, 4), new Object[]{"XX", "XX", Character.valueOf('X'), AtumItems.stoneChunk});
      GameRegistry.addRecipe(new ItemStack(Item.expBottle), new Object[]{" X ", "XBX", " X ", Character.valueOf('X'), AtumItems.ectoplasm, Character.valueOf('B'), Item.potion});
      GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneSword), new Object[]{"L", "L", "S", Character.valueOf('L'), AtumBlocks.cobble, Character.valueOf('S'), Item.stick});
      GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneShovel), new Object[]{"L", "S", "S", Character.valueOf('L'), AtumBlocks.cobble, Character.valueOf('S'), Item.stick});
      GameRegistry.addRecipe(new ItemStack(AtumItems.limestonePickaxe), new Object[]{"LLL", " S ", " S ", Character.valueOf('L'), AtumBlocks.cobble, Character.valueOf('S'), Item.stick});
      GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneAxe), new Object[]{"LL", "LS", " S", Character.valueOf('L'), AtumBlocks.cobble, Character.valueOf('S'), Item.stick});
      GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneHoe), new Object[]{"LL", " S", " S", Character.valueOf('L'), AtumBlocks.cobble, Character.valueOf('S'), Item.stick});
      GameRegistry.addRecipe(new ItemStack(AtumItems.mummyHelmet), new Object[]{"XXX", "X X", Character.valueOf('X'), AtumItems.scrap});
      GameRegistry.addRecipe(new ItemStack(AtumItems.mummyChest), new Object[]{"X X", "XXX", "XXX", Character.valueOf('X'), AtumItems.scrap});
      GameRegistry.addRecipe(new ItemStack(AtumItems.mummyLegs), new Object[]{"XXX", "X X", "X X", Character.valueOf('X'), AtumItems.scrap});
      GameRegistry.addRecipe(new ItemStack(AtumItems.mummyBoots), new Object[]{"X X", "X X", Character.valueOf('X'), AtumItems.scrap});
      GameRegistry.addRecipe(new ItemStack(AtumItems.wandererHelmet), new Object[]{"XXX", "X X", Character.valueOf('X'), AtumItems.linen});
      GameRegistry.addRecipe(new ItemStack(AtumItems.wandererChest), new Object[]{"X X", "XXX", "XXX", Character.valueOf('X'), AtumItems.linen});
      GameRegistry.addRecipe(new ItemStack(AtumItems.wandererLegs), new Object[]{"XXX", "X X", "X X", Character.valueOf('X'), AtumItems.linen});
      GameRegistry.addRecipe(new ItemStack(AtumItems.wandererBoots), new Object[]{"X X", "X X", Character.valueOf('X'), AtumItems.linen});
      GameRegistry.addRecipe(new ItemStack(AtumItems.linen), new Object[]{"XXX", Character.valueOf('X'), AtumItems.flax});
      GameRegistry.addRecipe(new ItemStack(Item.glassBottle, 3), new Object[]{"X X", " X ", Character.valueOf('X'), AtumBlocks.crystalGlass});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.thinCrystalGlass, 16), new Object[]{"XXX", "XXX", Character.valueOf('X'), AtumBlocks.crystalGlass});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.thinFramedGlass, 16), new Object[]{"XXX", "XXX", Character.valueOf('X'), AtumBlocks.framedGlass});
      GameRegistry.addRecipe(new ItemStack(AtumItems.scroll), new Object[]{"XXX", "SXS", "XXX", Character.valueOf('X'), AtumItems.papyrusPlant, Character.valueOf('S'), Item.stick});
      GameRegistry.addRecipe(new ItemStack(AtumItems.scarab), new Object[]{" G ", "GDG", " G ", Character.valueOf('G'), Item.ingotGold, Character.valueOf('D'), Item.diamond});
      GameRegistry.addRecipe(new ItemStack(AtumBlocks.furnaceIdle), new Object[]{"XXX", "X X", "XXX", Character.valueOf('X'), AtumBlocks.cobble});
   }

   public static void addSmeltingRecipes() {
      FurnaceRecipes.smelting().addSmelting(AtumBlocks.ironOre.blockID, 0, new ItemStack(Item.ingotIron), 0.7F);
      FurnaceRecipes.smelting().addSmelting(AtumBlocks.coalOre.blockID, new ItemStack(Item.coal), 0.1F);
      FurnaceRecipes.smelting().addSmelting(AtumBlocks.redstoneOre.blockID, new ItemStack(Item.redstone), 0.7F);
      FurnaceRecipes.smelting().addSmelting(AtumBlocks.lapisOre.blockID, new ItemStack(Item.dyePowder, 1, 4), 0.2F);
      FurnaceRecipes.smelting().addSmelting(AtumBlocks.goldOre.blockID, new ItemStack(Item.ingotGold), 1.0F);
      FurnaceRecipes.smelting().addSmelting(AtumBlocks.diamondOre.blockID, new ItemStack(Item.diamond), 1.0F);
      FurnaceRecipes.smelting().addSmelting(AtumBlocks.log.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
      FurnaceRecipes.smelting().addSmelting(AtumBlocks.cobble.blockID, new ItemStack(AtumBlocks.stone), 0.1F);
      FurnaceRecipes.smelting().addSmelting(AtumBlocks.sand.blockID, new ItemStack(AtumBlocks.crystalGlass), 0.1F);
   }

   public static void addShapelessRecipes() {
      GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.planks, 4), new Object[]{AtumBlocks.log});
      GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertHelmet), new Object[]{AtumItems.wandererHelmet, Item.helmetIron});
      GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertChest), new Object[]{AtumItems.wandererChest, Item.plateIron});
      GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertLegs), new Object[]{AtumItems.wandererLegs, Item.legsIron});
      GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertBoots), new Object[]{AtumItems.wandererBoots, Item.bootsIron});
      GameRegistry.addShapelessRecipe(new ItemStack(Block.sand), new Object[]{AtumBlocks.sand});
      GameRegistry.addShapelessRecipe(new ItemStack(Item.silk, 3), new Object[]{AtumItems.flax});
   }
}
