package rebelkeithy.mods.atum.entities.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderGhost extends RenderLiving {

	public static final ResourceLocation texture = new ResourceLocation("atum:textures/entities/DesertGhost.png");

	public RenderGhost(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.renderLivingLabel((EntityLiving) par1Entity, "Wrath", par2, par4, par6, 64);
		this.doRenderLiving((EntityLiving) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
