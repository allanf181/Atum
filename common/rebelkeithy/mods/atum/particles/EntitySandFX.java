package rebelkeithy.mods.atum.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import rebelkeithy.mods.atum.Atum;

@SideOnly(Side.CLIENT)
public class EntitySandFX extends EntityFX {

   float smokeParticleScale;


   public EntitySandFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
      this(par1World, par2, par4, par6, par8, par10, par12, 1.0F);
   }

   public EntitySandFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14) {
      super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
      super.motionX *= 0.10000000149011612D;
      super.motionY *= 0.10000000149011612D;
      super.motionZ *= 0.10000000149011612D;
      super.motionX += par8;
      super.motionY += par10;
      super.motionZ += par12;
      super.particleRed = super.particleGreen = super.particleBlue = 1.0F;
      super.particleScale *= 0.75F;
      super.particleScale *= par14;
      this.smokeParticleScale = super.particleScale;
      super.particleMaxAge = (int)(4.0D / (Math.random() * 0.8D + 0.2D));
      super.particleMaxAge = (int)((float)super.particleMaxAge * par14);
      super.noClip = false;
   }

   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      Tessellator tessellator1 = new Tessellator();
      tessellator1.startDrawingQuads();
      tessellator1.setBrightness(this.getBrightnessForRender(f));
      float f6 = ((float)super.particleAge + f) / (float)super.particleMaxAge * 32.0F;
      if(f6 < 0.0F) {
         f6 = 0.0F;
      }

      if(f6 > 1.0F) {
         f6 = 1.0F;
      }

      float var8 = ((float)super.particleAge + f) / (float)super.particleMaxAge;
      super.particleScale = 1.0F - var8 * var8 * 0.5F;
      int index = 3 * super.particleAge / super.particleMaxAge;
      GL11.glBindTexture(3553, Minecraft.getMinecraft().renderEngine.getTexture(new ResourceLocation(Atum.modID + "/textures/particles/Sand.png")).getGlTextureId());
      float f0 = (float)index / 16.0F;
      float f7 = f0 + 0.0625F;
      float f8 = 1.0F;
      float f9 = f8 + 0.0625F;
      float f10 = 0.1F * super.particleScale;
      float f11 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)f - EntityFX.interpPosX);
      float f12 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)f - EntityFX.interpPosY);
      float f13 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)f - EntityFX.interpPosZ);
      float f14 = 1.0F;
      float brightness = super.rand.nextFloat() * 0.2F + 0.5F;
      tessellator1.setColorRGBA_F(0.7F * brightness, 0.55F * brightness, 0.35F * brightness, 0.6F);
      tessellator1.addVertexWithUV((double)(f11 - f1 * f10 - f4 * f10), (double)(f12 - f2 * f10), (double)(f13 - f3 * f10 - f5 * f10), (double)f7, (double)f9);
      tessellator1.addVertexWithUV((double)(f11 - f1 * f10 + f4 * f10), (double)(f12 + f2 * f10), (double)(f13 - f3 * f10 + f5 * f10), (double)f7, (double)f8);
      tessellator1.addVertexWithUV((double)(f11 + f1 * f10 + f4 * f10), (double)(f12 + f2 * f10), (double)(f13 + f3 * f10 + f5 * f10), (double)f0, (double)f8);
      tessellator1.addVertexWithUV((double)(f11 + f1 * f10 - f4 * f10), (double)(f12 - f2 * f10), (double)(f13 + f3 * f10 - f5 * f10), (double)f0, (double)f9);
      tessellator1.draw();
      GL11.glBindTexture(3553, Minecraft.getMinecraft().renderEngine.getTexture(new ResourceLocation("/particles.png")).getGlTextureId());
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

      this.setParticleTextureIndex(7 - super.particleAge * 8 / super.particleMaxAge);
      super.motionY += 0.004D;
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      if(super.posY == super.prevPosY) {
         super.motionX *= 1.1D;
         super.motionZ *= 1.1D;
      }

      super.motionX *= 0.9599999785423279D;
      super.motionY *= 0.9599999785423279D;
      super.motionZ *= 0.9599999785423279D;
      if(super.onGround) {
         super.motionX *= 0.699999988079071D;
         super.motionZ *= 0.699999988079071D;
      }

   }
}
