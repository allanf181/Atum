package com.teammetallurgy.atum.client.render.entity;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import com.teammetallurgy.atum.entity.EntityPharaoh;
import com.teammetallurgy.atum.items.AtumItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPharaoh extends RenderBiped {

	public RenderPharaoh(ModelBiped par1ModelBiped, float par2) {
		super(par1ModelBiped, par2);
	}

	@Override
	public void doRender(EntityLivingBase par1Entity, double par2, double par4, double par6, float par8, float par9) {
        BossStatus.setBossStatus((net.minecraft.entity.boss.IBossDisplayData) par1Entity, true);
		super.doRender(par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return new ResourceLocation("atum", "textures/entities/PharaohBlue.png");
	}
}
