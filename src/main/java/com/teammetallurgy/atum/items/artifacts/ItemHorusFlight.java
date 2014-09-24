package com.teammetallurgy.atum.items.artifacts;

import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.items.ItemTexturedArmor;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemHorusFlight extends ItemTexturedArmor {

    public ItemHorusFlight(ArmorMaterial par2ArmorMaterial, int par3, int par4) {
        super(par2ArmorMaterial, par3, par4);
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    @SubscribeEvent
    public void onJump(LivingJumpEvent event) {
        if (event.entityLiving.getEquipmentInSlot(0) != null && event.entityLiving.getEquipmentInSlot(0).getItem() == this) {
            event.entityLiving.motionY += 0.2D;
            event.entityLiving.motionX *= 1.2D;
            event.entityLiving.motionZ *= 1.2D;
        }

    }

    @SubscribeEvent
    public void onFallDamage(LivingFallEvent event) {
        if (event.entityLiving.getEquipmentInSlot(0) != null && event.entityLiving.getEquipmentInSlot(0).getItem() == AtumItems.horusFlight) {
            event.distance = 0.0F;
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
    }

    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.getItem() == Items.diamond;
    }

    @Override
    public void registerIcons(IIconRegister par1IIconRegister) {
        this.itemIcon = par1IIconRegister.registerIcon("atum:HorusFlight");
    }
}
