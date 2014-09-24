package com.teammetallurgy.atum.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;

@SideOnly(Side.CLIENT)
public class RenderBandit extends RenderBiped {

    public RenderBandit(ModelBiped par1ModelBiped, float par2) {
        super(par1ModelBiped, par2);
    }

}
