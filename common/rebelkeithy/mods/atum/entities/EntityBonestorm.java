package rebelkeithy.mods.atum.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBonestorm extends EntityMob {

   private float heightOffset = 0.5F;
   private int heightOffsetUpdateTime;
   private int field_70846_g;


   public EntityBonestorm(World par1World) {
      super(par1World);
      super.isImmuneToFire = true;
      super.experienceValue = 10;
      this.setHealth(20f);
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, new Byte((byte)0));
   }

   protected String getLivingSound() {
      return "mob.blaze.breathe";
   }

   protected String getHurtSound() {
      return "mob.blaze.hit";
   }

   protected String getDeathSound() {
      return "mob.blaze.death";
   }

   @SideOnly(Side.CLIENT)
   public int getBrightnessForRender(float par1) {
      return 15728880;
   }

   public float getBrightness(float par1) {
      return 1.0F;
   }

   public void onLivingUpdate() {
      if(!super.worldObj.isRemote) {
         if(this.isWet()) {
            this.attackEntityFrom(DamageSource.drown, 1);
         }

         --this.heightOffsetUpdateTime;
         if(this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5F + (float)super.rand.nextGaussian() * 3.0F;
         }

         if(this.getEntityToAttack() != null && this.getEntityToAttack().posY + (double)this.getEntityToAttack().getEyeHeight() > super.posY + (double)this.getEyeHeight() + (double)this.heightOffset) {
            super.motionY += (0.30000001192092896D - super.motionY) * 0.30000001192092896D;
         }
      }

      if(super.rand.nextInt(24) == 0) {
         super.worldObj.playSoundEffect(super.posX + 0.5D, super.posY + 0.5D, super.posZ + 0.5D, "fire.fire", 1.0F + super.rand.nextFloat(), super.rand.nextFloat() * 0.7F + 0.3F);
      }

      if(!super.onGround && super.motionY < 0.0D) {
         super.motionY *= 0.6D;
      }

      for(int i = 0; i < 2; ++i) {
         super.worldObj.spawnParticle("largesmoke", super.posX + (super.rand.nextDouble() - 0.5D) * (double)super.width, super.posY + super.rand.nextDouble() * (double)super.height, super.posZ + (super.rand.nextDouble() - 0.5D) * (double)super.width, 0.0D, 0.0D, 0.0D);
      }

      super.onLivingUpdate();
   }

   protected void attackEntity(Entity par1Entity, float par2) {
      if(super.attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > super.boundingBox.minY && par1Entity.boundingBox.minY < super.boundingBox.maxY) {
         super.attackTime = 20;
         this.attackEntityAsMob(par1Entity);
      } else if(par2 < 30.0F) {
         double d0 = par1Entity.posX - super.posX;
         double d1 = par1Entity.boundingBox.minY + (double)(par1Entity.height / 2.0F) - (super.posY + (double)(super.height / 2.0F));
         double d2 = par1Entity.posZ - super.posZ;
         if(super.attackTime == 0) {
            ++this.field_70846_g;
            if(this.field_70846_g == 1) {
               super.attackTime = 60;
               this.func_70844_e(true);
            } else if(this.field_70846_g <= 4) {
               super.attackTime = 6;
            } else {
               super.attackTime = 100;
               this.field_70846_g = 0;
               this.func_70844_e(false);
            }

            if(this.field_70846_g > 1) {
               float f1 = MathHelper.sqrt_float(par2) * 0.5F;
               super.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)super.posX, (int)super.posY, (int)super.posZ, 0);

               for(int i = 0; i < 1; ++i) {
                  EntitySmallFireball entitysmallfireball = new EntitySmallFireball(super.worldObj, this, d0 + super.rand.nextGaussian() * (double)f1, d1, d2 + super.rand.nextGaussian() * (double)f1);
                  entitysmallfireball.posY = super.posY + (double)(super.height / 2.0F) + 0.5D;
                  super.worldObj.spawnEntityInWorld(entitysmallfireball);
               }
            }
         }

         super.rotationYaw = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
         super.hasAttacked = true;
      }

   }

   protected void fall(float par1) {}

   protected int getDropItemId() {
      return Item.blazeRod.itemID;
   }

   public boolean isBurning() {
      return this.func_70845_n();
   }

   protected void dropFewItems(boolean par1, int par2) {
      if(par1) {
         int j = super.rand.nextInt(2 + par2);

         for(int k = 0; k < j; ++k) {
            this.dropItem(Item.blazeRod.itemID, 1);
         }
      }

   }

   public boolean func_70845_n() {
      return (super.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   public void func_70844_e(boolean par1) {
      byte b0 = super.dataWatcher.getWatchableObjectByte(16);
      if(par1) {
         b0 = (byte)(b0 | 1);
      } else {
         b0 &= -2;
      }

      super.dataWatcher.updateObject(16, Byte.valueOf(b0));
   }

   protected boolean isValidLightLevel() {
      return true;
   }

   public int getAttackStrength(Entity par1Entity) {
      return 6;
   }
}
