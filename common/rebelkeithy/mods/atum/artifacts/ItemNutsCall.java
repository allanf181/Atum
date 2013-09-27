package rebelkeithy.mods.atum.artifacts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.artifacts.arrow.EntityNutsCall;

public class ItemNutsCall extends Item {

	public ItemNutsCall(int par1) {
		super(par1);
		this.setMaxDamage(650);
		this.setMaxStackSize(1);
		
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 7200;
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldRotateAroundWhenRendering() {
		return false;
	}

	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World world, EntityPlayer player, int par4) {
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
		System.out.println("charge strength: " + j);
		if (j > 21) {
			j = 21;
		}

		EntityNutsCall spear = new EntityNutsCall(world, player, (float) j / 37.0F + 0.25F);
		spear.setDamage(spear.getDamage() * 2.0D);
		spear.setStack(par1ItemStack);
		if (!world.isRemote) {
			System.out.println("firing2");
			world.spawnEntityInWorld(spear);
			world.updateEntity(spear);
		}

		par1ItemStack.damageItem(4, player);
		player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		System.out.println("check");
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Wrath I: Chance to strike");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "foe with lightning");
		} else {
			par3List.add("Wrath I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}
	
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(Atum.modID + ":NutsCall");
	}
}
