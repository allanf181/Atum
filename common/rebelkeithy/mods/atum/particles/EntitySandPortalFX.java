package rebelkeithy.mods.atum.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntitySandPortalFX extends EntityFX {

   private float portalParticleScale;
   private double portalPosX;
   private double portalPosY;
   private double portalPosZ;


   public EntitySandPortalFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
      super(par1World, par2, par4, par6, par8, par10, par12);
      super.motionX = par8;
      super.motionY = par10;
      super.motionZ = par12;
      this.portalPosX = super.posX = par2;
      this.portalPosY = super.posY = par4;
      this.portalPosZ = super.posZ = par6;
      float f = super.rand.nextFloat() * 0.6F + 0.4F;
      this.portalParticleScale = super.particleScale = super.rand.nextFloat() * 0.2F + 0.5F;
      super.particleRed = super.particleGreen = super.particleBlue = 1.0F * f;
      super.particleGreen *= 0.3F;
      super.particleRed *= 0.5F;
      super.particleBlue *= 0.1F;
      super.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
      super.noClip = true;
      this.setParticleTextureIndex((int)(Math.random() * 8.0D));
   }

   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
      float f6 = ((float)super.particleAge + par2) / (float)super.particleMaxAge;
      f6 = 1.0F - f6;
      f6 *= f6;
      f6 = 1.0F - f6;
      super.particleScale = this.portalParticleScale * f6;
      super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
   }

   public int getBrightnessForRender(float par1) {
      int i = super.getBrightnessForRender(par1);
      float f1 = (float)super.particleAge / (float)super.particleMaxAge;
      f1 *= f1;
      f1 *= f1;
      int j = i & 255;
      int k = i >> 16 & 255;
      k += (int)(f1 * 15.0F * 16.0F);
      if(k > 240) {
         k = 240;
      }

      return j | k << 16;
   }

   public float getBrightness(float par1) {
      float f1 = super.getBrightness(par1);
      float f2 = (float)super.particleAge / (float)super.particleMaxAge;
      f2 = f2 * f2 * f2 * f2;
      return f1 * (1.0F - f2) + f2;
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      float f = (float)super.particleAge / (float)super.particleMaxAge;
      float f1 = f;
      f = -f + f * f * 2.0F;
      f = 1.0F - f;
      super.posX = this.portalPosX + super.motionX * (double)f;
      super.posY = this.portalPosY + super.motionY * (double)f + (double)(1.0F - f1);
      super.posZ = this.portalPosZ + super.motionZ * (double)f;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

   }
}
