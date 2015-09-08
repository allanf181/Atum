package com.teammetallurgy.atum.items.artifacts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemMafdetsQuickness extends Item {

    public ItemMafdetsQuickness() {
        super();
        this.setMaxDamage(24000);
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (par3Entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) par3Entity;
            if (par5 && player.onGround && player.getHeldItem() != null && player.getHeldItem().getItem() == this) {
                doEffect(player, par1ItemStack);
            }
        }

    }

    public void doEffect(EntityPlayer player, ItemStack item) {
        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 40, 0, false));
        if (!player.capabilities.isCreativeMode) {
            if (item.getItemDamage() == 1) {
                item.damageItem(1, player);
            } else {
                item.setItemDamage(item.getItemDamage() + 1);
            }
        }

    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (Keyboard.isKeyDown(42)) {
            par3List.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal(this.getUnlocalizedName() + ".line1"));
            par3List.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal(this.getUnlocalizedName() + ".line2"));
        } else {
            par3List.add(StatCollector.translateToLocal(this.getUnlocalizedName() + ".line3") + " " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
        }

        double remaining = ((par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()) / 12) / 100.0D;
        String localizedRemaining = StatCollector.translateToLocalFormatted("tooltip.atum.minutesRemaining", remaining);
        par3List.add(localizedRemaining);
    }

    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.getItem() == Items.diamond;
    }

    @Override
    public void registerIcons(IIconRegister par1IIconRegister) {
        this.itemIcon = par1IIconRegister.registerIcon("atum:MafdetsQuickness");
    }
}
