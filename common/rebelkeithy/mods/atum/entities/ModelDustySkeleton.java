package rebelkeithy.mods.atum.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelZombie;

@SideOnly(Side.CLIENT)
public class ModelDustySkeleton extends ModelZombie {

   public ModelDustySkeleton() {
      this(0.0F);
   }

   public ModelDustySkeleton(float par1) {
      super(par1, 0.0F, 64, 32);
      super.bipedRightArm = new ModelRenderer(this, 40, 16);
      super.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, par1);
      super.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
      super.bipedLeftArm = new ModelRenderer(this, 40, 16);
      super.bipedLeftArm.mirror = true;
      super.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, par1);
      super.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
      super.bipedRightLeg = new ModelRenderer(this, 0, 16);
      super.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, par1);
      super.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
      super.bipedLeftLeg = new ModelRenderer(this, 0, 16);
      super.bipedLeftLeg.mirror = true;
      super.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, par1);
      super.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
   }
}
