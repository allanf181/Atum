package rebelkeithy.mods.atum;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rebelkeithy.mods.atum.AtumItems;
import rebelkeithy.mods.atum.AtumWeightedLootSet;

public class AtumFish {

   private static AtumWeightedLootSet fish = new AtumWeightedLootSet();


   public static void addFish(ItemStack fishStack, int probability) {
      fish.addLoot(fishStack, probability, 1, 1);
   }

   public static ItemStack getRandomFish() {
      return fish.getRandomLoot();
   }

   static {
      addFish(new ItemStack(Item.fishRaw, 1, 0), 100);
      addFish(new ItemStack(AtumItems.fish, 1, 0), 30);
      addFish(new ItemStack(AtumItems.fish, 1, 1), 5);
      addFish(new ItemStack(AtumItems.fish, 1, 2), 50);
      addFish(new ItemStack(AtumItems.fish, 1, 3), 50);
   }
}
