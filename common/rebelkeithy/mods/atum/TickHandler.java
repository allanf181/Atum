package rebelkeithy.mods.atum;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import rebelkeithy.mods.atum.AtumConfig;
import rebelkeithy.mods.atum.AtumItems;
import rebelkeithy.mods.particleregistry.ParticleRegistry;

public class TickHandler implements ITickHandler {

   private boolean raining;
   private boolean overlay;
   public static int defaultFog;


   public void tickStart(EnumSet type, Object ... tickData) {
      if(type.equals(EnumSet.of(TickType.RENDER))) {
         ;
      }

      if(type.equals(EnumSet.of(TickType.PLAYER))) {
         if(Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().theWorld.loadedEntityList.size() > 0) {
            List player = Minecraft.getMinecraft().theWorld.playerEntities;
            Iterator nightvision = player.iterator();

            while(nightvision.hasNext()) {
               EntityPlayer random = (EntityPlayer)nightvision.next();
//               if(random != null && random.getEntityName() == "RebelKeithy" || random.getEntityName() == "Shadowclaimer") {
//                  String particlesPerTick = "http://images.mccapes.com/capes/standardmc/RebelKeithy.png";
//                  if(random.cloakUrl != particlesPerTick) {
//                     random.cloakUrl = particlesPerTick;
//                  }
//
//                  Minecraft.getMinecraft().renderEngine.obtainImageData(random.cloakUrl, new ImageBufferDownload());
//               }
            }
         }

         EntityPlayer var13 = (EntityPlayer)tickData[0];
         boolean var14 = false;
         if(var13.getCurrentArmor(3) != null && var13.getCurrentArmor(3).itemID == AtumItems.rasGlory.itemID) {
            var14 = true;
         }

         if(var13.dimension == AtumConfig.dimensionID) {
            if(Minecraft.getMinecraft().gameSettings.renderDistance < (var14?1:2)) {
               defaultFog = Minecraft.getMinecraft().gameSettings.renderDistance;
               Minecraft.getMinecraft().gameSettings.renderDistance = var14?1:2;
            }

            if(var13.worldObj.isRaining()) {
               this.raining = true;
               if(Minecraft.getMinecraft().gameSettings.renderDistance < (var14?2:3)) {
                  Minecraft.getMinecraft().gameSettings.renderDistance = var14?2:3;
               }

               Random var15 = new Random();
               int var16 = (3 - Minecraft.getMinecraft().gameSettings.particleSetting) * 6;

               for(int i = 0; i < var16; ++i) {
                  float x = (float)(var15.nextInt(4) - 2);
                  float z = (float)(var15.nextInt(4) - 2);
                  float y = (var15.nextFloat() - 0.7F) * 2.0F;
                  float vx = 0.1F + var15.nextFloat() * 0.1F;
                  float vz = 0.1F + var15.nextFloat() * 0.1F;
                  ParticleRegistry.spawnParticle("sand", var13.worldObj, var13.posX + (double)x, var13.posY + (double)y, var13.posZ + (double)z, (double)vx + var13.motionX, 0.0D, (double)vz + var13.motionZ);
               }
            } else if(this.raining && defaultFog < (var14?1:2)) {
               this.raining = false;
               Minecraft.getMinecraft().gameSettings.renderDistance = var14?1:2;
            }
         }
      }

   }

   public void tickEnd(EnumSet type, Object ... tickData) {
      Minecraft minecraft = FMLClientHandler.instance().getClient();
      EntityClientPlayerMP player = minecraft.thePlayer;
      Object currentItemStack = null;
      if(type.contains(TickType.RENDER) && player != null && player.getCurrentArmor(3) != null && player.getCurrentArmor(3).itemID == AtumItems.mummyHelmet.itemID) {
         ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
         int par1 = scaledresolution.getScaledWidth();
         int par2 = scaledresolution.getScaledHeight();
         Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Atum.modID + ":textures/hud/mummyblur.png"));
         Tessellator tessellator = Tessellator.instance;
         tessellator.startDrawingQuads();
         tessellator.addVertexWithUV(0.0D, (double)par2, -100.0D, 0.0D, 1.0D);
         tessellator.addVertexWithUV((double)par1, (double)par2, -100.0D, 1.0D, 1.0D);
         tessellator.addVertexWithUV((double)par1, 0.0D, -100.0D, 1.0D, 0.0D);
         tessellator.addVertexWithUV(0.0D, 0.0D, -100.0D, 0.0D, 0.0D);
         tessellator.draw();
      }

   }

   public EnumSet ticks() {
      return EnumSet.of(TickType.PLAYER, TickType.RENDER);
   }

   public String getLabel() {
      return "Atum.TickHandler.Player";
   }
}
