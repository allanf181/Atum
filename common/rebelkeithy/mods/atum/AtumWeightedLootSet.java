package rebelkeithy.mods.atum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class AtumWeightedLootSet {
	public Map loot;
	public Map lootMin;
	public Map lootMax;
	public int totalWeight;

	public AtumWeightedLootSet() {
		this.loot = new HashMap();
		this.lootMin = new HashMap();
		this.lootMax = new HashMap();
		this.totalWeight = 0;
	}

	public void addLoot(ItemStack stack, int weight, int min, int max) {
		if ((weight <= 0) || (stack == null)) {
			return;
		}
		this.loot.put(Integer.valueOf(this.totalWeight + weight), stack);
		this.lootMin.put(Integer.valueOf(this.totalWeight + weight), Integer.valueOf(min));
		this.lootMax.put(Integer.valueOf(this.totalWeight + weight), Integer.valueOf(max));
		this.totalWeight += weight;
	}

	public ItemStack getRandomLoot() {
		Random rand = new Random();
		int weight = rand.nextInt(this.totalWeight);

		ItemStack stack = null;

		Set keySet = this.loot.keySet();
		Integer[] keys = (Integer[]) keySet.toArray(new Integer[keySet.size()]);
		Arrays.sort(keys);

		for (Integer key : keys) {
			if (key.intValue() >= weight) {
				stack = ((ItemStack) this.loot.get(key)).copy();
				int min = ((Integer) this.lootMin.get(key)).intValue();
				int max = ((Integer) this.lootMax.get(key)).intValue();
				int amount = rand.nextInt(max - min + 1) + min;
				stack.stackSize = amount;
				if (stack.itemID != Item.enchantedBook.itemID)
					break;
				Enchantment enchantment = Enchantment.enchantmentsBookList[rand.nextInt(Enchantment.enchantmentsBookList.length)];
				int l = MathHelper.getRandomIntegerInRange(rand, enchantment.getMinLevel(), enchantment.getMaxLevel());
				((ItemEnchantedBook) stack.getItem()).addEnchantment(stack, new EnchantmentData(enchantment, l));
				break;
			}

		}

		return stack;
	}
}