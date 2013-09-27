package rebelkeithy.mods.atum.artifacts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.Atum;

public class ItemMaatsBalance extends ItemArmor {

	public String texture;

	public ItemMaatsBalance(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		MinecraftForge.EVENT_BUS.register(this);
		
	}

	@ForgeSubscribe
	public void onLivingAttack(LivingHurtEvent event) {
		if (event.entityLiving.getCurrentItemOrArmor(3) != null && event.entityLiving.getCurrentItemOrArmor(3).itemID == super.itemID) {
			event.ammount = (int) ((float) (event.ammount + 1) / 1.5F) - 1;
		}

		if (event.source instanceof EntityDamageSource) {
			EntityDamageSource source = (EntityDamageSource) event.source;
			if (source.getEntity() != null && source.getEntity() instanceof EntityLiving) {
				EntityLiving entity = (EntityLiving) source.getEntity();
				if (entity.getCurrentItemOrArmor(3) != null && entity.getCurrentItemOrArmor(3).itemID == super.itemID) {
					event.ammount = (int) ((float) (event.ammount + 1) / 1.5F) - 1;
				}
			}
		}

	}

	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
	}

	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + "Balance I: Decreases damage");
			par3List.add(EnumChatFormatting.DARK_PURPLE + "dealt, Decreases damage taken");
		} else {
			par3List.add("Balance I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}

	}

	public Item setTextureFile(String string) {
		this.texture = string;
		return this;
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return "/armor/" + this.texture + ".png";
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == Item.diamond.itemID;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(Atum.modID + ":MaatsBalance");
	}
}
