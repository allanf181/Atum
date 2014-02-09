package com.teammetallurgy.atum.items;

import java.util.List;
import java.util.Random;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import com.teammetallurgy.atum.LocalizationHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLoot extends Item {

	private static String[] typeArray = new String[]{"Idol", "Necklace", "Ring", "Broach", "Scepter"};
	private static String[] qualityArray = new String[]{"Dirty", "Silver", "Gold", "Sapphire", "Ruby", "Emerald", "Diamond"};
	Icon[] iconArray;

	public ItemLoot(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	public static ItemStack getRandomLoot(Random rand, boolean isDirty) {
		int type = rand.nextInt(typeArray.length);
		int quality = rand.nextInt(qualityArray.length - 1) + 1;
		return new ItemStack(AtumItems.ITEM_LOOT.itemID, 1, type << 5 | quality << 1 | (isDirty ? 1 : 0));
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		int dirty = par1ItemStack.getItemDamage() & 1;
		int quality = par1ItemStack.getItemDamage() >> 1 & 15;
		int type = par1ItemStack.getItemDamage() >> 5 & 15;
		return dirty == 1 ? "Dirty " + typeArray[type] : qualityArray[quality] + " " + typeArray[type];
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int dirty = par1ItemStack.getItemDamage() & 1;
		int quality = par1ItemStack.getItemDamage() >> 1 & 15;
		int type = par1ItemStack.getItemDamage() >> 5 & 15;
		return dirty == 1 ? LocalizationHelper.localize(this.getUnlocalizedName() + ".dirty" + typeArray[type]) : LocalizationHelper.localize(this.getUnlocalizedName() + "." + qualityArray[quality] + typeArray[type]);
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		int id = entityItem.worldObj.getBlockId(MathHelper.floor_double(entityItem.posX), MathHelper.floor_double(entityItem.posY), MathHelper.floor_double(entityItem.posZ));
		if(id == Block.waterStill || id == Block.waterMoving) {
			ItemStack item = entityItem.getEntityItem();
			int damage = item.getItemDamage() >> 1;
			int quality = damage & 15;
			if(quality == 0) {
				damage |= (int) (Math.random() * 6.0D) + 1;
			}

			item.setItemDamage(damage << 1);
			entityItem.setEntityItemStack(item);
		}

		return super.onEntityItemUpdate(entityItem);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for(int type = 0; type < typeArray.length; ++type) {
			par3List.add(new ItemStack(par1, 1, type << 5 | 1));

			for(int quality = 1; quality < qualityArray.length; ++quality) {
				par3List.add(new ItemStack(par1, 1, type << 5 | quality << 1));
			}
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		int dirty = par1 & 1;
		int quality = par1 >> 1 & 15;
		int type = par1 >> 5 & 15;
		return dirty == 1 ? this.iconArray[type * 7] : this.iconArray[type * 7 + quality];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.iconArray = new Icon[typeArray.length * (qualityArray.length + 1)];

		for(int type = 0; type < 5; ++type) {
			this.iconArray[type * 7] = par1IconRegister.registerIcon("atum:Dirty" + typeArray[type]);

			for(int quality = 1; quality < 7; ++quality) {
				this.iconArray[type * 7 + quality] = par1IconRegister.registerIcon("atum:" + qualityArray[quality] + typeArray[type]);
			}
		}

	}

}
