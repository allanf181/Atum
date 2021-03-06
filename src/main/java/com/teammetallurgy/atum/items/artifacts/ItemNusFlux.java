package com.teammetallurgy.atum.items.artifacts;

import com.teammetallurgy.atum.entity.EntityStoneSoldier;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemNusFlux extends ItemSword {

    public ItemNusFlux(ToolMaterial par2ToolMaterial) {
        super(par2ToolMaterial);

    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase entity, EntityLivingBase player) {
        if (!player.worldObj.isRemote && Math.random() > 0.75D && !(entity instanceof EntityStoneSoldier)) {
            double dx = entity.posX - player.posX;
            double dz = entity.posZ - player.posZ;
            double magnitude = Math.sqrt(dx * dx + dz * dz);
            dx /= magnitude;
            dz /= magnitude;
            entity.isAirBorne = true;
            entity.addVelocity(dx / 2.0D, 1.5D, dz / 2.0D);
            if (entity.motionY > 1.0D) {
                entity.motionY = 1.0D;
            }

            // entity.attackEntityFrom(DamageSource.generic,
            // this.getDamageVsEntity(entity, par1ItemStack));
            if (player.worldObj.isRemote) {
                this.spawnParticle(player.worldObj, entity);
            }
        }

        return super.hitEntity(par1ItemStack, entity, player);
    }

    @SideOnly(Side.CLIENT)
    public void spawnParticle(World world, Entity entity) {
        Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCrit2FX(world, entity));
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
        this.itemIcon = par1IIconRegister.registerIcon("atum:NusFlux");
    }
}
