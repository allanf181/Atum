package com.teammetallurgy.atum.client.render.entity.arrow;

import com.teammetallurgy.atum.entity.projectile.EntityBone;
import com.teammetallurgy.atum.items.AtumItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class RenderBone extends Render {
    private float size;

    public RenderBone(float size) {
        this.size = size;
    }

    public void doRender(EntityBone entityBone, double d, double d1, double d2, float f, float f1) {
        GL11.glPushMatrix();
        this.bindEntityTexture(entityBone);
        GL11.glTranslatef((float) d, (float) d1, (float) d2);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f2 = this.size;
        GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
        IIcon iicon = AtumItems.ITEM_DUSTYBONE.getIconFromDamage(0);
        Tessellator tessellator = Tessellator.instance;
        float f3 = iicon.getMinU();
        float f4 = iicon.getMaxU();
        float f5 = iicon.getMinV();
        float f6 = iicon.getMaxV();
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV((double) (0.0F - f8), (double) (0.0F - f9), 0.0D, (double) f3, (double) f6);
        tessellator.addVertexWithUV((double) (f7 - f8), (double) (0.0F - f9), 0.0D, (double) f4, (double) f6);
        tessellator.addVertexWithUV((double) (f7 - f8), (double) (1.0F - f9), 0.0D, (double) f4, (double) f5);
        tessellator.addVertexWithUV((double) (0.0F - f8), (double) (1.0F - f9), 0.0D, (double) f3, (double) f5);
        tessellator.draw();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(EntityBone entityBone) {
        return TextureMap.locationItemsTexture;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getEntityTexture((EntityBone) entity);
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        this.doRender((EntityBone) entity, d, d1, d2, f, f1);
    }
}