package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.AtumItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHorusFlight extends ItemArmor {

	String texture;

	public ItemHorusFlight(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		
	}

	@ForgeSubscribe
	public void onJump(LivingJumpEvent event) {
		if (event.entityLiving.getCurrentItemOrArmor(0) != null && event.entityLiving.getCurrentItemOrArmor(0).itemID == super.itemID) {
			event.entityLiving.motionY += 0.2D;
			event.entityLiving.motionX *= 1.2D;
			event.entityLiving.motionZ *= 1.2D;
		}

	}

	@ForgeSubscribe
	public void onFallDamage(LivingFallEvent event) {
		if (event.entityLiving.getCurrentItemOrArmor(0) != null && event.entityLiving.getCurrentItemOrArmor(0).itemID == AtumItems.horusFlight.itemID) {
			event.distance = 0.0F;
		}

	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Nimbleness I: Increased jump height,");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "protection from fall damage");
		} else {
			par3List.add("Nimbleness I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}

	public Item setTextureFile(String string) {
		this.texture = string;
		return this;
	}

	public String getArmorTextureFile(ItemStack itemstack) {
		return "/armor/" + this.texture + ".png";
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(Atum.modID + ":HorusFlight");
	}
}
