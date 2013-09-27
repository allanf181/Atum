package rebelkeithy.mods.atum.artifacts.arrow;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rebelkeithy.mods.atum.Atum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderNutsCall extends Render {

	public void renderArrow(CustomArrow par1EntityFireSpearCombined, double par2, double par4, double par6, float par8, float par9) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par2, (float) par4, (float) par6);
		GL11.glRotatef(par1EntityFireSpearCombined.prevRotationYaw + (par1EntityFireSpearCombined.rotationYaw - par1EntityFireSpearCombined.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(par1EntityFireSpearCombined.prevRotationPitch + (par1EntityFireSpearCombined.rotationPitch - par1EntityFireSpearCombined.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
		Tessellator tessellator = Tessellator.instance;
		byte b0 = 0;
		float f2 = 0.0F;
		float f3 = 0.5F;
		float f4 = (float) (0 + b0 * 10) / 32.0F;
		float f5 = (float) (5 + b0 * 10) / 32.0F;
		float f6 = 0.0F;
		float f7 = 0.15625F;
		float f8 = (float) (5 + b0 * 10) / 32.0F;
		float f9 = (float) (10 + b0 * 10) / 32.0F;
		float f10 = 0.15625F;
		GL11.glEnable('\u803a');
		float f11 = par1EntityFireSpearCombined.arrowShake - par9;
		if (f11 > 0.0F) {
			float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
			GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
		}

		GL11.glScalef(2.0F, 1.5F, 1.5F);
		GL11.glTranslatef(-0.85F, 0.0F, 0.0F);
		GL11.glNormal3f(f10, 0.0F, 0.0F);
		ItemRenderer.renderItemIn2D(tessellator, -1.0F, -0.3125F, -0.15625F, 0.375F, 32, 1024, 0.0625F);
		GL11.glDisable('\u803a');
		GL11.glPopMatrix();
	}

	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.renderArrow((CustomArrow) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("atum:textures/projectiles/nutscall.png");
	}
}
