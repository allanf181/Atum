package com.teammetallurgy.atum.items.artifacts;

import java.util.List;

import javax.swing.Icon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import org.lwjgl.input.Keyboard;

import com.teammetallurgy.atum.entity.arrow.EntityArrowDoubleShot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNeithsAudacity extends ItemBow {

	public static final String[] bowPullIconNameArray = new String[]{"neiths_pull_0", "neiths_pull_1", "neiths_pull_2"};
	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;

	public ItemNeithsAudacity(int par1) {
		super(par1);
		super.maxStackSize = 1;
		this.setMaxDamage(384);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Double Shot I: Fires ");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "two arrows");
		} else {
			par3List.add("Slam I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}

	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
		MinecraftForge.EVENT_BUS.post(event);
		if(!event.isCanceled()) {
			j = event.charge;
			boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
			if(flag || par3EntityPlayer.inventory.hasItemStack(new ItemStack(Item.arrow, 2))) {
				float f = (float) j / 20.0F;
				f = (f * f + f * 2.0F) / 3.0F;
				if((double) f < 0.1D) {
					return;
				}

				if(f > 1.0F) {
					f = 1.0F;
				}

				EntityArrowDoubleShot entityarrow = new EntityArrowDoubleShot(par2World, par3EntityPlayer, f * 2.0F);
				EntityArrowDoubleShot entityarrow1 = new EntityArrowDoubleShot(par2World, par3EntityPlayer, f * 2.0F);
				entityarrow.motionX += Math.random() * 0.4D - 0.2D;
				entityarrow.motionY += Math.random() * 0.4D - 0.2D;
				entityarrow.motionZ += Math.random() * 0.4D - 0.2D;
				entityarrow1.motionX += Math.random() * 0.4D - 0.2D;
				entityarrow1.motionY += Math.random() * 0.4D - 0.2D;
				entityarrow1.motionZ += Math.random() * 0.4D - 0.2D;
				entityarrow.setDamage(entityarrow.getDamage() + 0.5D);
				entityarrow1.setDamage(entityarrow.getDamage() + 0.5D);
				if(f == 1.0F) {
					entityarrow.setIsCritical(true);
					entityarrow1.setIsCritical(true);
				}

				int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
				if(k > 0) {
					entityarrow.setDamage(entityarrow.getDamage() + (double) k * 0.5D + 0.5D);
					entityarrow1.setDamage(entityarrow.getDamage() + (double) k * 0.5D + 0.5D);
				}

				int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);
				if(l > 0) {
					entityarrow.setKnockbackStrength(l);
					entityarrow1.setKnockbackStrength(l);
				}

				if(EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0) {
					entityarrow.setFire(100);
					entityarrow1.setFire(100);
				}

				par1ItemStack.damageItem(1, par3EntityPlayer);
				par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (Item.itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
				if(flag) {
					entityarrow.canBePickedUp = 2;
					entityarrow1.canBePickedUp = 2;
				} else {
					par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.itemID);
					par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.itemID);
				}

				if(!par2World.isRemote) {
					par2World.spawnEntityInWorld(entityarrow);
					par2World.spawnEntityInWorld(entityarrow1);
				}
			}

		}
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
		MinecraftForge.EVENT_BUS.post(event);
		if(event.isCanceled()) {
			return event.result;
		} else {
			if(par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Item.arrow.itemID)) {
				par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
			}

			return par1ItemStack;
		}
	}

	@Override
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		if(usingItem != null) {
			int j = this.getMaxItemUseDuration(stack) - useRemaining;
			if(j >= 18) {
				return this.getItemIconForUseDuration(2);
			}

			if(j > 13) {
				return this.getItemIconForUseDuration(1);
			}

			if(j > 0) {
				return this.getItemIconForUseDuration(0);
			}
		}

		return this.getIcon(stack, renderPass);
	}

	@Override
	public int getItemEnchantability() {
		return 1;
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.iconArray = new Icon[bowPullIconNameArray.length];
		this.itemIcon = par1IconRegister.registerIcon("atum:NeithsAudacity");

		for(int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = par1IconRegister.registerIcon("atum:" + bowPullIconNameArray[i]);
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getItemIconForUseDuration(int par1) {
		return this.iconArray[par1];
	}

}
