package rebelkeithy.mods.atum.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityCritFX extends EntityFX {

   float field_70561_a;


   public EntityCritFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
      this(par1World, par2, par4, par6, par8, par10, par12, 1.0F);
   }

   public EntityCritFX(World par1World, double x, double y, double z, double dx, double dy, double dz, double r, double g, double b) {
      this(par1World, x, y, z, dx, dy, dz, 1.0F);
      float colorOffset = (float)((Math.random() - 0.5D) * 0.20000000298023224D);
      super.particleRed = (float)r + colorOffset;
      super.particleBlue = (float)b + colorOffset;
      super.particleGreen = (float)g + colorOffset;
   }

   public EntityCritFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14) {
      super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
      super.motionX *= 0.10000000149011612D;
      super.motionY *= 0.10000000149011612D;
      super.motionZ *= 0.10000000149011612D;
      super.motionX += par8 * 0.4D;
      super.motionY += par10 * 0.4D;
      super.motionZ += par12 * 0.4D;
      super.particleRed = super.particleGreen = super.particleBlue = (float)(Math.random() * 0.30000001192092896D + 0.6000000238418579D);
      super.particleScale *= 0.75F;
      super.particleScale *= par14;
      this.field_70561_a = super.particleScale;
      super.particleMaxAge = (int)(6.0D / (Math.random() * 0.8D + 0.6D));
      super.particleMaxAge = (int)((float)super.particleMaxAge * par14);
      super.noClip = false;
      this.setParticleTextureIndex(65);
      this.onUpdate();
   }

   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
      float f6 = ((float)super.particleAge + par2) / (float)super.particleMaxAge * 32.0F;
      if(f6 < 0.0F) {
         f6 = 0.0F;
      }

      if(f6 > 1.0F) {
         f6 = 1.0F;
      }

      super.particleScale = this.field_70561_a * f6;
      super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      super.motionX *= 0.699999988079071D;
      super.motionY *= 0.699999988079071D;
      super.motionZ *= 0.699999988079071D;
      super.motionY -= 0.019999999552965164D;
      if(super.onGround) {
         super.motionX *= 0.699999988079071D;
         super.motionZ *= 0.699999988079071D;
      }

   }
}
