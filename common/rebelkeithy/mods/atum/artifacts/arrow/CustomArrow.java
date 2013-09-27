package rebelkeithy.mods.atum.artifacts.arrow;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CustomArrow extends Entity {

   float arrowShake = 0.0F;


   public CustomArrow(World par1World) {
      super(par1World);
   }

   protected void entityInit() {}

   public String getTexture() {
      return "/item/arrows.png";
   }

   protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
      super.readFromNBT(nbttagcompound);
   }

   protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
      super.writeToNBT(nbttagcompound);
   }
}
