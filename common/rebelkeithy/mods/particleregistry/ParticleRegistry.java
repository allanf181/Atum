package rebelkeithy.mods.particleregistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class ParticleRegistry {

   private static Map particleList = new HashMap();


   public static void registerParticle(String name, Class particle) {
      particleList.put(name, particle);
   }

   @SideOnly(Side.CLIENT)
   public static void spawnParticle(String string, World par1World, double x, double y, double z, double dx, double dy, double dz, double r, double g, double b) {
      Class entityClass = (Class)particleList.get(string);
      if(entityClass != null) {
         try {
            Constructor entityConstructor = entityClass.getConstructor(new Class[]{World.class, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE});
            if(entityConstructor != null) {
               EntityFX e = (EntityFX)entityConstructor.newInstance(new Object[]{par1World, Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), Double.valueOf(dx), Double.valueOf(dy), Double.valueOf(dz), Double.valueOf(r), Double.valueOf(g), Double.valueOf(b)});
               Minecraft.getMinecraft().effectRenderer.addEffect(e);
            }
         } catch (Exception var23) {
            var23.printStackTrace();
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public static void spawnParticle(String string, World par1World, double x, double y, double z, double r, double g, double b) {
      Class entityClass = (Class)particleList.get(string);
      if(entityClass != null) {
         try {
            Constructor entityConstructor = entityClass.getConstructor(new Class[]{World.class, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE});
            if(entityConstructor != null) {
               EntityFX e = (EntityFX)entityConstructor.newInstance(new Object[]{par1World, Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), Double.valueOf(r), Double.valueOf(g), Double.valueOf(b)});
               Minecraft.getMinecraft().effectRenderer.addEffect(e);
            }
         } catch (Exception var17) {
            var17.printStackTrace();
         }
      }

   }

}
