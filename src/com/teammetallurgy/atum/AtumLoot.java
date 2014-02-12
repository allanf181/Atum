package com.teammetallurgy.atum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.teammetallurgy.atum.blocks.Blocks;
import com.teammetallurgy.atum.items.ItemLoot;
import com.teammetallurgy.atum.items.Items;

public enum AtumLoot {
	INSTANCE;

	public static List<ItemStack> artifacts;
	public static AtumWeightedLootSet goodLoot;
	public static AtumWeightedLootSet junkLoot;

	public void register() {
		artifacts = new ArrayList<ItemStack>();
		goodLoot = new AtumWeightedLootSet();
		junkLoot = new AtumWeightedLootSet();

		ItemStack stack = new ItemStack(Items.ptahsPick);
		artifacts.add(stack);

		artifacts.add(new ItemStack(Items.sobeksRage));
		artifacts.add(new ItemStack(Items.osirisWill));
		artifacts.add(new ItemStack(Items.akersToil));
		artifacts.add(new ItemStack(Items.gebsBlessing));
		artifacts.add(new ItemStack(Items.atensFury));
		artifacts.add(new ItemStack(Items.rasGlory));
		artifacts.add(new ItemStack(Items.sekhmetsWrath));
		artifacts.add(new ItemStack(Items.nutsAgility));
		artifacts.add(new ItemStack(Items.horusFlight));
		artifacts.add(new ItemStack(Items.monthusStrike));
		artifacts.add(new ItemStack(Items.neithsAudacity));
		artifacts.add(new ItemStack(Items.hedetetsSting));
		artifacts.add(new ItemStack(Items.nusFlux));
		artifacts.add(new ItemStack(Items.anhursMight));
		artifacts.add(new ItemStack(Items.horusSoaring));
		artifacts.add(new ItemStack(Items.shusBreath));
		artifacts.add(new ItemStack(Items.hedetetsVenom));
		artifacts.add(new ItemStack(Items.monthusBlast));
		artifacts.add(new ItemStack(Items.mnevisHorns));
		artifacts.add(new ItemStack(Items.isisEmbrace));
		artifacts.add(new ItemStack(Items.maatsBalance));
		artifacts.add(new ItemStack(Items.nutsCall));
		artifacts.add(new ItemStack(Items.ptahsDestruction));
		artifacts.add(new ItemStack(Items.anuketsBounty));
		artifacts.add(new ItemStack(Items.anubisMercy));
		artifacts.add(new ItemStack(Items.amunetsHomecoming));
		artifacts.add(new ItemStack(Items.isisHealing));
		artifacts.add(new ItemStack(Items.mafdetsQuickness));

		// Junk Loot Stuff
		junkLoot.addLoot(new ItemStack(Items.flaxSeeds), 5, 1, 2);
		junkLoot.addLoot(new ItemStack(Item.stick), 5, 1, 5);
		junkLoot.addLoot(new ItemStack(Items.ITEM_DATE), 5, 1, 2);
		junkLoot.addLoot(new ItemStack(Item.bone), 10, 1, 3);
		junkLoot.addLoot(new ItemStack(Item.bread), 10, 1, 4);
		junkLoot.addLoot(new ItemStack(Blocks.BLOCK_SAND), 20, 1, 64);
		junkLoot.addLoot(new ItemStack(Items.ITEM_SCIMITAR), 5, 1, 1);
		junkLoot.addLoot(new ItemStack(Item.seeds), 5, 1, 3);
		junkLoot.addLoot(new ItemStack(Item.leather), 5, 1, 5);
		junkLoot.addLoot(new ItemStack(Item.dyePowder, 1, 3), 5, 1, 3);
		junkLoot.addLoot(new ItemStack(Item.saddle), 5, 1, 1);

		// Good Loot
		goodLoot.addLoot(new ItemStack(Item.ingotIron), 38, 1, 3);
		goodLoot.addLoot(new ItemStack(Item.ingotGold), 20, 1, 3);
		goodLoot.addLoot(new ItemStack(Item.diamond), 4, 1, 2);
		goodLoot.addLoot(new ItemStack(Item.enchantedBook, 1, 1), 5, 1, 1);
		ItemStack stick = new ItemStack(Item.stick);
		stick.setItemName("Amazing Stick");
		goodLoot.addLoot(stick, 1, 1, 1);
	}

	public static void addArtifact(ItemStack stack) {
		artifacts.add(stack);
	}

	public static ItemStack getRandomLoot() {
		return artifacts.get(0);
	}

	public static ItemStack getRandomArtifact() {

		int i = (new Random()).nextInt(artifacts.size());
		return artifacts.get(i).copy();
	}

	public static void fillChest(IInventory inventory, int multiplier, float quality) {
		if(inventory == null) {
			System.out.println("Error trying to fill empty chest");
			return;
		}

		Random rand = new Random();
		for(int i = 0; i < multiplier; i++) {
			int slot = rand.nextInt(inventory.getSizeInventory());
			float roll = rand.nextFloat();
			ItemStack stack = new ItemStack(0, 0, 0);
			if(rand.nextFloat() < quality) {
				if(roll > 0.20) {
					stack = goodLoot.getRandomLoot();
				} else if(roll > 0.005) {
					stack = ItemLoot.getRandomLoot(rand, true);
				} else {
					int randomArtifactID = rand.nextInt(artifacts.size());
					stack = artifacts.get(randomArtifactID).copy();
				}
			} else {
				stack = junkLoot.getRandomLoot();
			}
			inventory.setInventorySlotContents(slot, stack);
		}
	}
}
