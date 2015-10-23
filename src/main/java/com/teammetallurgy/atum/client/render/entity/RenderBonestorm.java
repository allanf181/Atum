package com.teammetallurgy.atum.client.render.entity;

import com.teammetallurgy.atum.client.model.entity.ModelBonestorm;
import com.teammetallurgy.atum.entity.EntityBonestorm;
import com.teammetallurgy.atum.utils.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderBonestorm extends RenderLiving {
    private static final ResourceLocation bonestormTextures = new ResourceLocation(Constants.MODID + ":" + "textures/entities/Bonestorm.png");
    private int field_77068_a;

    public RenderBonestorm() {
        super(new ModelBonestorm(), 0.5F);
        this.field_77068_a = ((ModelBonestorm)this.mainModel).func_78104_a();
    }

    public void doRender(EntityBonestorm entityBonestorm, double d, double d1, double d2, float f, float f1) {
        int i = ((ModelBonestorm)this.mainModel).func_78104_a();

        if (i != this.field_77068_a) {
            this.field_77068_a = i;
            this.mainModel = new ModelBonestorm();
        }

        super.doRender((EntityLiving)entityBonestorm, d, d1, d2, f, f1);
    }

    protected ResourceLocation getEntityTexture(EntityBonestorm entityBonestorm)
    {
        return bonestormTextures;
    }

    @Override
    public void doRender(EntityLiving entityLiving, double d, double d1, double d2, float f, float f1) {
        this.doRender((EntityBonestorm)entityLiving, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(EntityLivingBase livingBase, double d, double d1, double d2, float f, float f1) {
        this.doRender((EntityBonestorm)livingBase, d, d1, d2, f, f1);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getEntityTexture((EntityBonestorm)entity);
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        this.doRender((EntityBonestorm)entity, d, d1, d2, f, f1);
    }
}