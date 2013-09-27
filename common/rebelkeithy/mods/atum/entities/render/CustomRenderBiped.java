package rebelkeithy.mods.atum.entities.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class CustomRenderBiped extends RenderBiped {
	String textureName;

	public CustomRenderBiped(ModelBiped par1ModelBiped, float par2, String textureName) {
		super(par1ModelBiped, par2);
		this.textureName = textureName;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("atum:textures/entities/" + textureName);
	}
}
