package rebelkeithy.mods.atum.artifacts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;

public class ItemPtahsDestruction extends ItemPickaxe {

	public ItemPtahsDestruction(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
		super.efficiencyOnProperMaterial = 11.0F;
		
	}

	@Override
	public float getDamageVsEntity(Entity entity, ItemStack stack) {
		float damage = 4 + super.toolMaterial.getDamageVsEntity();
		return entity instanceof EntityStoneSoldier ? (int) ((double) damage * (2.0D + Math.random())) : damage;
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Stonecutter I: Incresed damage");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "to stone enemies");
		} else {
			par3List.add("Stonecutter I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(Atum.modID + ":PtahsDestruction");
	}
}
