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

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.Atum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRasGlory extends ItemArmor {

	String texture;

	public ItemRasGlory(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Divine Vision I: Night vision, increased");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "range of vision in Atum");
		} else {
			par3List.add("Divine Vision I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
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
		this.itemIcon = par1IconRegister.registerIcon(Atum.modID + ":RasGlory");
	}
}
