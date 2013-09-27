package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.artifacts.arrow.EntityArrowPoison;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHedetetsVenom extends ItemBow {

	public static final String[] bowPullIconNameArray = new String[] { "HedetetsVenom_pull_0", "HedetetsVenom_pull_1", "HedetetsVenom_pull_2" };
	Icon[] iconArray;

	public ItemHedetetsVenom(int par1) {
		super(par1);
		this.setMaxDamage(650);
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
		MinecraftForge.EVENT_BUS.post(event);
		if (!event.isCanceled()) {
			j = event.charge;
			boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
			if (flag || par3EntityPlayer.inventory.hasItem(Item.arrow.itemID)) {
				float f = (float) j / 20.0F;
				f = (f * f + f * 2.0F) / 3.0F;
				if ((double) f < 0.1D) {
					return;
				}

				if (f > 1.0F) {
					f = 1.0F;
				}

				EntityArrowPoison entityarrow = new EntityArrowPoison(par2World, par3EntityPlayer, f * 3.0F);
				entityarrow.setDamage(entityarrow.getDamage() * 1.5D);
				if (f == 1.0F) {
					entityarrow.setIsCritical(true);
				}

				int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
				if (k > 0) {
					entityarrow.setDamage(entityarrow.getDamage() + (double) k * 0.5D + 0.5D);
				}

				int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);
				if (l > 0) {
					entityarrow.setKnockbackStrength(l);
				}

				if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0) {
					entityarrow.setFire(100);
				}

				par1ItemStack.damageItem(1, par3EntityPlayer);
				par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (Item.itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
				if (flag) {
					entityarrow.canBePickedUp = 2;
				} else {
					par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.itemID);
				}

				if (!par2World.isRemote) {
					par2World.spawnEntityInWorld(entityarrow);
				}
			}

		}
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Poison Arrow I: Fires an");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "arrow that poisons foes");
		} else {
			par3List.add("Poison Arrow I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		this.iconArray = new Icon[bowPullIconNameArray.length];
		this.itemIcon = par1IconRegister.registerIcon(Atum.modID + ":HedetetsVenom");
		for (int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = par1IconRegister.registerIcon(Atum.modID + ":" + bowPullIconNameArray[i]);
		}

	}

	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		if (usingItem != null) {
			int j = this.getMaxItemUseDuration(stack) - useRemaining;
			if (j >= 18) {
				return this.getItemIconForUseDuration(2);
			}

			if (j > 13) {
				return this.getItemIconForUseDuration(1);
			}

			if (j > 0) {
				return this.getItemIconForUseDuration(0);
			}
		}

		return this.getIcon(stack, renderPass);
	}

	@SideOnly(Side.CLIENT)
	public Icon getItemIconForUseDuration(int par1) {
		return this.iconArray[par1];
	}

}
