package rebelkeithy.mods.atum.artifacts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.artifacts.arrow.EntityAtumFishHook;

public class ItemAnuketsBounty extends Item {

	@SideOnly(Side.CLIENT)
	private Icon theIcon;

	public ItemAnuketsBounty(int par1) {
		super(par1);
		this.setMaxDamage(64);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (par3EntityPlayer.fishEntity != null) {
			int i = par3EntityPlayer.fishEntity.catchFish();
			par1ItemStack.damageItem(i, par3EntityPlayer);
			par3EntityPlayer.swingItem();
		} else {
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (Item.itemRand.nextFloat() * 0.4F + 0.8F));
			if (!par2World.isRemote) {
				par2World.spawnEntityInWorld(new EntityAtumFishHook(par2World, par3EntityPlayer));
			}

			par3EntityPlayer.swingItem();
		}

		return par1ItemStack;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		this.itemIcon = par1IconRegister.registerIcon(Atum.modID + ":AnuketsBounty");
		this.theIcon = par1IconRegister.registerIcon("fishing_rod_cast");
	}

	@SideOnly(Side.CLIENT)
	public Icon func_94597_g() {
		return this.theIcon;
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Alluring I: Chance to catch");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "special fish in Atum");
		} else {
			par3List.add("Return I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

		par3List.add(par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage() + " Uses Remaining");
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}
}
