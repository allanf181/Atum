package com.teammetallurgy.atum.client.render.entity;

import com.teammetallurgy.atum.entity.EntityDesertWolf;
import com.teammetallurgy.atum.utils.Constants;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderDesertWolf extends RenderLiving {
    private static final ResourceLocation tamedDesertWolfTextures = new ResourceLocation(Constants.MODID + ":" + "textures/entities/DesertWolf_tame.png");
    private static final ResourceLocation angryDesertWolfTextures = new ResourceLocation(Constants.MODID + ":" + "textures/entities/DesertWolf_angry.png");
    private static final ResourceLocation wolfDesertCollarTextures = new ResourceLocation("textures/entity/wolf/wolf_collar.png");

    public RenderDesertWolf(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
        super(par1ModelBase, par3);
        this.setRenderPassModel(par2ModelBase);
    }

    protected float getTailRotation(EntityDesertWolf entityDesertWolf, float rotation) {
        return entityDesertWolf.getTailRotation();
    }

    protected int shouldRenderPass(EntityDesertWolf entityDesertWolf, int par2, float par3) {
        if (par2 == 0 && entityDesertWolf.getWolfShaking()) {
            float f1 = entityDesertWolf.getBrightness(par3) * entityDesertWolf.getShadingWhileShaking(par3);
            this.bindTexture(angryDesertWolfTextures);
            GL11.glColor3f(f1, f1, f1);
            return 1;
        } else if (par2 == 1 && entityDesertWolf.isTamed()) {
            this.bindTexture(wolfDesertCollarTextures);
            int j = entityDesertWolf.getCollarColor();
            GL11.glColor3f(EntitySheep.fleeceColorTable[j][0], EntitySheep.fleeceColorTable[j][1], EntitySheep.fleeceColorTable[j][2]);
            return 1;
        } else {
            return -1;
        }
    }

    protected ResourceLocation getEntityTexture(EntityDesertWolf entityDesertWolf) {
        return entityDesertWolf.isTamed() ? tamedDesertWolfTextures : angryDesertWolfTextures;
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    @Override
    protected int shouldRenderPass(EntityLivingBase livingBase, int par2, float par3) {
        return this.shouldRenderPass((EntityDesertWolf) livingBase, par2, par3);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    @Override
    protected float handleRotationFloat(EntityLivingBase livingBase, float rotation) {
        return this.getTailRotation((EntityDesertWolf) livingBase, rotation);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getEntityTexture((EntityDesertWolf) entity);
    }
}