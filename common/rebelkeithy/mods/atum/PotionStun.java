package rebelkeithy.mods.atum;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

public class PotionStun extends Potion {

   protected PotionStun(int par1, boolean par2, int par3) {
      super(par1, par2, par3);
      MinecraftForge.EVENT_BUS.register(this);
   }

   @ForgeSubscribe
   public void entityAquireTarge(LivingSetAttackTargetEvent event) {
      if(event.entityLiving != null && event.entityLiving.isPotionActive(this)) {
         ReflectionHelper.setPrivateValue(EntityLivingBase.class, event.entityLiving, (Object)null, new String[]{"attackTarget", "field_70696_bz", "b0"});
      }

   }

   public void performEffect(EntityLiving par1EntityLiving, int par2) {
      ReflectionHelper.setPrivateValue(EntityLiving.class, par1EntityLiving, (Object)null, new String[]{"currentTarget", "field_70776_bF", "bY"});
   }

   public boolean isReady(int par1, int par2) {
      return true;
   }
}
