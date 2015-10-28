package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.blocks.AtumBlocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AtumLoot {

    public static List<ItemStack> artifacts;
    public static AtumWeightedLootSet goodLoot;
    public static AtumWeightedLootSet junkLoot;

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
        if (inventory == null) {
            return;
        }

        Random rand = new Random();
        for (int i = 0; i < multiplier; i++) {
            int slot = rand.nextInt(inventory.getSizeInventory());
            float roll = rand.nextFloat();
            ItemStack stack = null;
            if (rand.nextFloat() < quality) {
                if (roll > 0.20) {
                    stack = goodLoot.getRandomLoot();
                } else if (roll > 0.005) {
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

    public void register() {
        artifacts = new ArrayList<ItemStack>();
        goodLoot = new AtumWeightedLootSet();
        junkLoot = new AtumWeightedLootSet();

        ItemStack stack = new ItemStack(AtumItems.ITEM_PTAHSPICK);
        artifacts.add(stack);

        artifacts.add(new ItemStack(AtumItems.sobeksRage));
        artifacts.add(new ItemStack(AtumItems.osirisWill));
        artifacts.add(new ItemStack(AtumItems.akersToil));
        artifacts.add(new ItemStack(AtumItems.gebsBlessing));
        artifacts.add(new ItemStack(AtumItems.atensFury));
        artifacts.add(new ItemStack(AtumItems.rasGlory));
        artifacts.add(new ItemStack(AtumItems.sekhmetsWrath));
        artifacts.add(new ItemStack(AtumItems.nutsAgility));
        artifacts.add(new ItemStack(AtumItems.horusFlight));
        artifacts.add(new ItemStack(AtumItems.monthusStrike));
        artifacts.add(new ItemStack(AtumItems.neithsAudacity));
        artifacts.add(new ItemStack(AtumItems.hedetetsSting));
        artifacts.add(new ItemStack(AtumItems.nusFlux));
        artifacts.add(new ItemStack(AtumItems.anhursMight));
        artifacts.add(new ItemStack(AtumItems.horusSoaring));
        artifacts.add(new ItemStack(AtumItems.shusBreath));
        artifacts.add(new ItemStack(AtumItems.hedetetsVenom));
        artifacts.add(new ItemStack(AtumItems.monthusBlast));
        artifacts.add(new ItemStack(AtumItems.mnevisHorns));
        artifacts.add(new ItemStack(AtumItems.isisEmbrace));
        artifacts.add(new ItemStack(AtumItems.maatsBalance));
        artifacts.add(new ItemStack(AtumItems.nutsCall));
        artifacts.add(new ItemStack(AtumItems.ptahsDestruction));
        artifacts.add(new ItemStack(AtumItems.anuketsBounty));
        artifacts.add(new ItemStack(AtumItems.anubisMercy));
        artifacts.add(new ItemStack(AtumItems.amunetsHomecoming));
        artifacts.add(new ItemStack(AtumItems.isisHealing));
        artifacts.add(new ItemStack(AtumItems.mafdetsQuickness));

        // Junk Loot Stuff
        junkLoot.addLoot(new ItemStack(AtumItems.ITEM_FLAXSEED), 5, 1, 2);
        junkLoot.addLoot(new ItemStack(Items.stick), 5, 1, 5);
        junkLoot.addLoot(new ItemStack(AtumItems.ITEM_DATE), 5, 1, 2);
        junkLoot.addLoot(new ItemStack(Items.bone), 10, 1, 3);
        junkLoot.addLoot(new ItemStack(AtumBlocks.BLOCK_SAND), 12, 1, 64);
        junkLoot.addLoot(new ItemStack(AtumItems.ITEM_SCIMITAR), 5, 1, 1);
        junkLoot.addLoot(new ItemStack(Items.leather), 5, 1, 5);

        // Good Loot
        goodLoot.addLoot(new ItemStack(Items.iron_ingot), 5, 1, 3);
        goodLoot.addLoot(new ItemStack(Items.gold_ingot), 4, 1, 3);
        goodLoot.addLoot(new ItemStack(Items.diamond), 2, 1, 2);
        goodLoot.addLoot(new ItemStack(Items.enchanted_book, 1, 1), 5, 1, 1);
        ItemStack stick = new ItemStack(Items.stick);
        stick.setStackDisplayName("Amazing Stick");
        goodLoot.addLoot(stick, 1, 1, 1);
    }
}
