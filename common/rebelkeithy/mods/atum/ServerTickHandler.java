package rebelkeithy.mods.atum;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import java.util.EnumSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import rebelkeithy.mods.atum.AtumItems;

public class ServerTickHandler implements ITickHandler {

   private boolean raining;


   public void tickStart(EnumSet type, Object ... tickData) {
      if(type.equals(EnumSet.of(TickType.PLAYER))) {
         EntityPlayer player = (EntityPlayer)tickData[0];
         if(player.worldObj.getTotalWorldTime() % 60L == 0L && player.getCurrentArmor(2) != null && player.getCurrentArmor(2).itemID == AtumItems.isisEmbrace.itemID) {
            player.heal(1);
         }

         if(player.worldObj.getTotalWorldTime() % 10L == 0L) {
            if(player.getCurrentArmor(3) != null) {
               if(player.getCurrentArmor(3).itemID == AtumItems.rasGlory.itemID) {
                  player.addPotionEffect(new PotionEffect(16, 340, 0, true));
               } else if(player.isPotionActive(16)) {
                  player.clearActivePotions();
               }
            }

            if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).itemID == AtumItems.sekhmetsWrath.itemID) {
               ;
            }

            if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).itemID == AtumItems.nutsAgility.itemID) {
               player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 240, 1, true));
            }

            if(player.getCurrentArmor(0) != null && player.getCurrentArmor(0).itemID == AtumItems.horusFlight.itemID) {
               ;
            }
         }
      }

   }

   public void tickEnd(EnumSet type, Object ... tickData) {}

   public EnumSet ticks() {
      return EnumSet.of(TickType.PLAYER);
   }

   public String getLabel() {
      return "Atum.TickHandler.Player";
   }
}
