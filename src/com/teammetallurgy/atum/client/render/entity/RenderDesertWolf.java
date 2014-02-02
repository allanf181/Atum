package com.teammetallurgy.atum.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.teammetallurgy.atum.entity.EntityDesertWolf;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDesertWolf extends RenderLiving {
	public RenderDesertWolf(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
		super(par1ModelBase, par3);
		this.setRenderPassModel(par2ModelBase);
	}

	protected float getTailRotation(EntityDesertWolf par1EntityDesertWolf, float par2) {
		return par1EntityDesertWolf.getTailRotation();
	}

	protected int func_82447_a(EntityDesertWolf par1EntityDesertWolf, int par2, float par3) {
		float f1;
		this.bindEntityTexture(par1EntityDesertWolf);
		if(par2 == 0 && par1EntityDesertWolf.getWolfShaking()) {
			f1 = par1EntityDesertWolf.getBrightness(par3) * par1EntityDesertWolf.getShadingWhileShaking(par3);
			GL11.glColor3f(f1, f1, f1);
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * Queries whether should render the specified pass or not.
	 */
	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3) {
		return this.func_82447_a((EntityDesertWolf) par1EntityLiving, par2, par3);
	}

	/**
	 * Defines what float the third param in setRotationAngles of ModelBase is
	 */
	@Override
	protected float handleRotationFloat(EntityLivingBase par1EntityLiving, float par2) {
		return this.getTailRotation((EntityDesertWolf) par1EntityLiving, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("atum", "textures/entities/DesertWolf.png");
	}
}
