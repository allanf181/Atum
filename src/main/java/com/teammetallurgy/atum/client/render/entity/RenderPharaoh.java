package com.teammetallurgy.atum.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderPharaoh extends RenderBiped {

    public RenderPharaoh(ModelBiped par1ModelBiped, float par2) {
        super(par1ModelBiped, par2);
    }

    @Override
    public void doRender(EntityLiving par1Entity, double par2, double par4, double par6, float par8, float par9) {
        BossStatus.setBossStatus((net.minecraft.entity.boss.IBossDisplayData) par1Entity, true);
        super.doRender(par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return new ResourceLocation("atum", "textures/entities/PharaohBlue.png");
    }
}
