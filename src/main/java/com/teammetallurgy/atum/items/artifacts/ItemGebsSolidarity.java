package com.teammetallurgy.atum.items.artifacts;

import com.teammetallurgy.atum.items.ItemTexturedArmor;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemGebsSolidarity extends ItemTexturedArmor {

    public ItemGebsSolidarity(ArmorMaterial par2ArmorMaterial, int par3, int par4) {
        super(par2ArmorMaterial, par3, par4);
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    @SubscribeEvent
    public void onLivingAttack(LivingHurtEvent event) {
        DamageSource par1DamageSource = event.source;
        if (par1DamageSource.getEntity() != null) {
            Entity par1Entity = par1DamageSource.getEntity();
            byte j = 0;
            if (par1Entity instanceof EntityLiving) {
                int j1 = j + EnchantmentHelper.getKnockbackModifier((EntityLiving) par1Entity, event.entityLiving);
                System.out.println("undo knockback " + j1);
                if (j1 >= 0) {
                    event.entityLiving.motionX /= 0.6D;
                    event.entityLiving.motionZ /= 0.6D;
                    event.entityLiving.addVelocity((double) (MathHelper.sin(par1Entity.rotationYaw * 3.1415927F / 180.0F) * (float) j1 * 0.5F), -0.1D, (double) (-MathHelper.cos(par1Entity.rotationYaw * 3.1415927F / 180.0F) * (float) j1 * 0.5F));
                }
            }

            EntityLivingBase player = event.entityLiving;
            double d0 = par1DamageSource.getEntity().posX - player.posX;

            double d1;
            for (d1 = par1DamageSource.getEntity().posZ - player.posZ; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                d0 = (Math.random() - Math.random()) * 0.01D;
            }

            player.isAirBorne = true;
            float f = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
            float f1 = 0.2F;
            player.motionX += d0 / (double) f * (double) f1;
            player.motionZ += d1 / (double) f * (double) f1;
            player.motionX *= 2.0D;
            player.motionY *= 2.0D;
            player.motionZ *= 2.0D;
            if (player.motionY > 0.4000000059604645D) {
                player.motionY = 0.4000000059604645D;
            }
        }

    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        double magnitude = Math.sqrt(player.motionX * player.motionX + player.motionZ * player.motionZ);
        player.capabilities.setPlayerWalkSpeed((float) ((double) player.capabilities.getWalkSpeed() * 0.5D));
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
        this.itemIcon = par1IIconRegister.registerIcon("atum:GebsSolidarity");
    }
}
