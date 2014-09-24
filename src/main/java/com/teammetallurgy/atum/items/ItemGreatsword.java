package com.teammetallurgy.atum.items;

import com.teammetallurgy.atum.entity.EntityPharaoh;
import com.teammetallurgy.atum.entity.EntityStoneSoldier;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MathHelper;

public class ItemGreatsword extends ItemSword {

    public ItemGreatsword(ToolMaterial par2ToolMaterial) {
        super(par2ToolMaterial);
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase mob, EntityLivingBase player) {
        if (!(mob instanceof EntityStoneSoldier) && !(mob instanceof EntityPharaoh)) {
            float j = 1.2F;
            mob.addVelocity((double) (-MathHelper.sin(player.rotationYaw * 3.1415927F / 180.0F) * j * 0.5F), 0.1D, (double) (MathHelper.cos(player.rotationYaw * 3.1415927F / 180.0F) * j * 0.5F));
        }

        return super.hitEntity(par1ItemStack, mob, player);
    }
}
