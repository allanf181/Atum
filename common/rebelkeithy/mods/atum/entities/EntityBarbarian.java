package rebelkeithy.mods.atum.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.AtumConfig;
import rebelkeithy.mods.atum.AtumItems;
import rebelkeithy.mods.atum.entities.EntityPharaoh;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;
import rebelkeithy.mods.atum.entities.IAtumDayMob;

public class EntityBarbarian extends EntityMob implements IAtumDayMob {

   public EntityBarbarian(World par1World) {
      super(par1World);
      super.experienceValue = 9;
      this.setHealth(30);
   }


   protected void addRandomArmor() {}

   public float getSpeedModifier() {
      return this.getSpeedModifier() * 1.5F;
   }

   public boolean getCanSpawnHere() {
      return super.worldObj.checkNoEntityCollision(super.boundingBox) && super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox);
   }

   protected boolean isValidLightLevel() {
      return true;
   }

   public void initCreature() {
      ItemStack weapon = new ItemStack(AtumItems.greatsword);
      this.setCurrentItemOrArmor(0, weapon);
      this.enchantEquipment();

      for(int i = 0; i < super.equipmentDropChances.length; ++i) {
         super.equipmentDropChances[i] = 0.0F;
      }

   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEFINED;
   }

   public int getAttackStrength(Entity par1Entity) {
      return 4;
   }

   protected void dropFewItems(boolean par1, int par2) {
      int amount;
      if(super.rand.nextInt(20) == 0) {
         amount = (int)((double)AtumItems.greatsword.getMaxDamage() - (double)super.rand.nextInt(AtumItems.greatsword.getMaxDamage()) * 0.5D + 20.0D);
         this.entityDropItem(new ItemStack(AtumConfig.greatswordID, 1, amount), 0.0F);
      }

      if(super.rand.nextInt(4) == 0) {
         amount = super.rand.nextInt(2) + 1;
         this.dropItem(Item.goldNugget.itemID, amount);
      }

   }

   protected void attackEntity(Entity mob, float par2) {
      if(!(mob instanceof EntityStoneSoldier) && !(mob instanceof EntityPharaoh)) {
         float j = 1.2F;
         mob.addVelocity((double)(-MathHelper.sin(super.rotationYaw * 3.1415927F / 180.0F) * j * 0.5F), 0.1D, (double)(MathHelper.cos(super.rotationYaw * 3.1415927F / 180.0F) * j * 0.5F));
      }

      super.attackEntity(mob, par2);
   }
}
