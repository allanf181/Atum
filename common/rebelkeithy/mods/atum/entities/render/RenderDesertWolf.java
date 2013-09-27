package rebelkeithy.mods.atum.entities.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.entities.EntityDesertWolf;

@SideOnly(Side.CLIENT)
public class RenderDesertWolf extends RenderLiving {

	public RenderDesertWolf(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		this.setRenderPassModel(par1ModelBase);
	}

	protected int func_82447_a(EntityDesertWolf par1EntityDesertWolf, int par2, float par3) {
		float f1;
		if (par2 == 0 && par1EntityDesertWolf.getWolfShaking()) {
			f1 = par1EntityDesertWolf.getBrightness(par3) * par1EntityDesertWolf.getShadingWhileShaking(par3);
			GL11.glColor3f(f1, f1, f1);
			return 1;
		} else if (par2 == 1 && par1EntityDesertWolf.isTamed()) {
			f1 = 1.0F;
			int j = par1EntityDesertWolf.getCollarColor();
			GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
			return 1;
		} else {
			return -1;
		}
	}

	protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3) {
		return this.func_82447_a((EntityDesertWolf) par1EntityLiving, par2, par3);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(((EntityDesertWolf) entity).getTexture());
	}
}
