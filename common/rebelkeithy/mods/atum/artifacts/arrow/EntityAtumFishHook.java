package rebelkeithy.mods.atum.artifacts.arrow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.AtumFish;
import rebelkeithy.mods.atum.AtumItems;

public class EntityAtumFishHook extends EntityFishHook {

   private int xTile;
   private int yTile;
   private int zTile;
   private int inTile;
   private boolean inGround;
   public int a;
   public EntityPlayer b;
   private int ticksInGround;
   private int ticksInAir;
   private int ticksCatchable;
   public Entity c;
   private int fishPosRotationIncrements;
   private double fishX;
   private double fishY;
   private double fishZ;
   private double fishYaw;
   private double fishPitch;
   @SideOnly(Side.CLIENT)
   private double velocityX;
   @SideOnly(Side.CLIENT)
   private double velocityY;
   @SideOnly(Side.CLIENT)
   private double velocityZ;


   public EntityAtumFishHook(World par1World) {
      super(par1World);
      this.xTile = -1;
      this.yTile = -1;
      this.zTile = -1;
      this.inTile = 0;
      this.inGround = false;
      super.shake = 0;
      this.ticksInAir = 0;
      this.ticksCatchable = 0;
      super.bobber = null;
      this.setSize(0.25F, 0.25F);
      super.ignoreFrustumCheck = true;
   }

   @SideOnly(Side.CLIENT)
   public EntityAtumFishHook(World par1World, double par2, double par4, double par6, EntityPlayer par8EntityPlayer) {
      this(par1World);
      this.setPosition(par2, par4, par6);
      super.ignoreFrustumCheck = true;
      super.angler = par8EntityPlayer;
      par8EntityPlayer.fishEntity = this;
   }

   public EntityAtumFishHook(World par1World, EntityPlayer par2EntityPlayer) {
      super(par1World);
      this.xTile = -1;
      this.yTile = -1;
      this.zTile = -1;
      this.inTile = 0;
      this.inGround = false;
      super.shake = 0;
      this.ticksInAir = 0;
      this.ticksCatchable = 0;
      super.bobber = null;
      super.ignoreFrustumCheck = true;
      super.angler = par2EntityPlayer;
      super.angler.fishEntity = this;
      this.setSize(0.25F, 0.25F);
      this.setLocationAndAngles(par2EntityPlayer.posX, par2EntityPlayer.posY + 1.62D - (double)par2EntityPlayer.yOffset, par2EntityPlayer.posZ, par2EntityPlayer.rotationYaw, par2EntityPlayer.rotationPitch);
      super.posX -= (double)(MathHelper.cos(super.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
      super.posY -= 0.10000000149011612D;
      super.posZ -= (double)(MathHelper.sin(super.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
      this.setPosition(super.posX, super.posY, super.posZ);
      super.yOffset = 0.0F;
      float f = 0.4F;
      super.motionX = (double)(-MathHelper.sin(super.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(super.rotationPitch / 180.0F * 3.1415927F) * f);
      super.motionZ = (double)(MathHelper.cos(super.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(super.rotationPitch / 180.0F * 3.1415927F) * f);
      super.motionY = (double)(-MathHelper.sin(super.rotationPitch / 180.0F * 3.1415927F) * f);
      this.calculateVelocity(super.motionX, super.motionY, super.motionZ, 1.5F, 1.0F);
   }

   protected void entityInit() {}

   @SideOnly(Side.CLIENT)
   public boolean isInRangeToRenderDist(double par1) {
      double d1 = super.boundingBox.getAverageEdgeLength() * 4.0D;
      d1 *= 64.0D;
      return par1 < d1 * d1;
   }

   public void calculateVelocity(double par1, double par3, double par5, float par7, float par8) {
      float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
      par1 /= (double)f2;
      par3 /= (double)f2;
      par5 /= (double)f2;
      par1 += super.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
      par3 += super.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
      par5 += super.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
      par1 *= (double)par7;
      par3 *= (double)par7;
      par5 *= (double)par7;
      super.motionX = par1;
      super.motionY = par3;
      super.motionZ = par5;
      float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
      super.prevRotationYaw = super.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / 3.141592653589793D);
      super.prevRotationPitch = super.rotationPitch = (float)(Math.atan2(par3, (double)f3) * 180.0D / 3.141592653589793D);
      this.ticksInGround = 0;
   }

   @SideOnly(Side.CLIENT)
   public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
      this.fishX = par1;
      this.fishY = par3;
      this.fishZ = par5;
      this.fishYaw = (double)par7;
      this.fishPitch = (double)par8;
      this.fishPosRotationIncrements = par9;
      super.motionX = this.velocityX;
      super.motionY = this.velocityY;
      super.motionZ = this.velocityZ;
   }

   @SideOnly(Side.CLIENT)
   public void setVelocity(double par1, double par3, double par5) {
      this.velocityX = super.motionX = par1;
      this.velocityY = super.motionY = par3;
      this.velocityZ = super.motionZ = par5;
   }

   public void onUpdate() {
      this.onEntityUpdate();
      if(this.fishPosRotationIncrements > 0) {
         double vec3 = super.posX + (this.fishX - super.posX) / (double)this.fishPosRotationIncrements;
         double movingobjectposition = super.posY + (this.fishY - super.posY) / (double)this.fishPosRotationIncrements;
         double list = super.posZ + (this.fishZ - super.posZ) / (double)this.fishPosRotationIncrements;
         double d3 = MathHelper.wrapAngleTo180_double(this.fishYaw - (double)super.rotationYaw);
         super.rotationYaw = (float)((double)super.rotationYaw + d3 / (double)this.fishPosRotationIncrements);
         super.rotationPitch = (float)((double)super.rotationPitch + (this.fishPitch - (double)super.rotationPitch) / (double)this.fishPosRotationIncrements);
         --this.fishPosRotationIncrements;
         this.setPosition(vec3, movingobjectposition, list);
         this.setRotation(super.rotationYaw, super.rotationPitch);
      } else {
         if(!super.worldObj.isRemote) {
            ItemStack var21 = super.angler.getCurrentEquippedItem();
            if(super.angler.isDead || !super.angler.isEntityAlive() || var21 == null || var21.getItem() != AtumItems.anuketsBounty || this.getDistanceSqToEntity(super.angler) > 1024.0D) {
               this.setDead();
               super.angler.fishEntity = null;
               return;
            }

            if(super.bobber != null) {
               if(!super.bobber.isDead) {
                  super.posX = super.bobber.posX;
                  super.posY = super.bobber.boundingBox.minY + (double)super.bobber.height * 0.8D;
                  super.posZ = super.bobber.posZ;
                  return;
               }

               super.bobber = null;
            }
         }

         if(super.shake > 0) {
            --super.shake;
         }

         if(this.inGround) {
            int var22 = super.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
            if(var22 == this.inTile) {
               ++this.ticksInGround;
               if(this.ticksInGround == 1200) {
                  this.setDead();
               }

               return;
            }

            this.inGround = false;
            super.motionX *= (double)(super.rand.nextFloat() * 0.2F);
            super.motionY *= (double)(super.rand.nextFloat() * 0.2F);
            super.motionZ *= (double)(super.rand.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
         } else {
            ++this.ticksInAir;
         }

         Vec3 var23 = super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX, super.posY, super.posZ);
         Vec3 vec31 = super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ);
         MovingObjectPosition var24 = this.worldObj.rayTraceBlocks_do_do(var23, vec31, false, false);
         var23 = super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX, super.posY, super.posZ);
         vec31 = super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ);
         if(var24 != null) {
            vec31 = super.worldObj.getWorldVec3Pool().getVecFromPool(var24.hitVec.xCoord, var24.hitVec.yCoord, var24.hitVec.zCoord);
         }

         Entity entity = null;
         List var25 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.addCoord(super.motionX, super.motionY, super.motionZ).expand(1.0D, 1.0D, 1.0D));
         double d4 = 0.0D;

         double d5;
         for(int f1 = 0; f1 < var25.size(); ++f1) {
            Entity f2 = (Entity)var25.get(f1);
            if(f2.canBeCollidedWith() && (f2 != super.angler || this.ticksInAir >= 5)) {
               float b0 = 0.3F;
               AxisAlignedBB d6 = f2.boundingBox.expand((double)b0, (double)b0, (double)b0);
               MovingObjectPosition movingobjectposition1 = d6.calculateIntercept(var23, vec31);
               if(movingobjectposition1 != null) {
                  d5 = var23.distanceTo(movingobjectposition1.hitVec);
                  if(d5 < d4 || d4 == 0.0D) {
                     entity = f2;
                     d4 = d5;
                  }
               }
            }
         }

         if(entity != null) {
            var24 = new MovingObjectPosition(entity);
         }

         if(var24 != null) {
            if(var24.entityHit != null) {
               if(var24.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, super.angler), 0)) {
                  super.bobber = var24.entityHit;
               }
            } else {
               this.inGround = true;
            }
         }

         if(!this.inGround) {
            this.moveEntity(super.motionX, super.motionY, super.motionZ);
            float var27 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
            super.rotationYaw = (float)(Math.atan2(super.motionX, super.motionZ) * 180.0D / 3.141592653589793D);

            for(super.rotationPitch = (float)(Math.atan2(super.motionY, (double)var27) * 180.0D / 3.141592653589793D); super.rotationPitch - super.prevRotationPitch < -180.0F; super.prevRotationPitch -= 360.0F) {
               ;
            }

            while(super.rotationPitch - super.prevRotationPitch >= 180.0F) {
               super.prevRotationPitch += 360.0F;
            }

            while(super.rotationYaw - super.prevRotationYaw < -180.0F) {
               super.prevRotationYaw -= 360.0F;
            }

            while(super.rotationYaw - super.prevRotationYaw >= 180.0F) {
               super.prevRotationYaw += 360.0F;
            }

            super.rotationPitch = super.prevRotationPitch + (super.rotationPitch - super.prevRotationPitch) * 0.2F;
            super.rotationYaw = super.prevRotationYaw + (super.rotationYaw - super.prevRotationYaw) * 0.2F;
            float var26 = 0.92F;
            if(super.onGround || super.isCollidedHorizontally) {
               var26 = 0.5F;
            }

            byte var28 = 5;
            double var29 = 0.0D;

            for(int short1 = 0; short1 < var28; ++short1) {
               double f3 = super.boundingBox.minY + (super.boundingBox.maxY - super.boundingBox.minY) * (double)(short1 + 0) / (double)var28 - 0.125D + 0.125D;
               double f4 = super.boundingBox.minY + (super.boundingBox.maxY - super.boundingBox.minY) * (double)(short1 + 1) / (double)var28 - 0.125D + 0.125D;
               AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getAABBPool().getAABB(super.boundingBox.minX, f3, super.boundingBox.minZ, super.boundingBox.maxX, f4, super.boundingBox.maxZ);
               if(super.worldObj.isAABBInMaterial(axisalignedbb1, Material.water)) {
                  var29 += 1.0D / (double)var28;
               }
            }

            if(var29 > 0.0D) {
               if(this.ticksCatchable > 0) {
                  --this.ticksCatchable;
               } else {
                  short var31 = 500;
                  if(super.worldObj.canLightningStrikeAt(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY) + 1, MathHelper.floor_double(super.posZ))) {
                     var31 = 300;
                  }

                  if(super.rand.nextInt(var31) == 0) {
                     this.ticksCatchable = super.rand.nextInt(30) + 10;
                     super.motionY -= 0.20000000298023224D;
                     this.playSound("random.splash", 0.25F, 1.0F + (super.rand.nextFloat() - super.rand.nextFloat()) * 0.4F);
                     float var30 = (float)MathHelper.floor_double(super.boundingBox.minY);

                     int l;
                     float f5;
                     float var32;
                     for(l = 0; (float)l < 1.0F + super.width * 20.0F; ++l) {
                        f5 = (super.rand.nextFloat() * 2.0F - 1.0F) * super.width;
                        var32 = (super.rand.nextFloat() * 2.0F - 1.0F) * super.width;
                        super.worldObj.spawnParticle("bubble", super.posX + (double)f5, (double)(var30 + 1.0F), super.posZ + (double)var32, super.motionX, super.motionY - (double)(super.rand.nextFloat() * 0.2F), super.motionZ);
                     }

                     for(l = 0; (float)l < 1.0F + super.width * 20.0F; ++l) {
                        f5 = (super.rand.nextFloat() * 2.0F - 1.0F) * super.width;
                        var32 = (super.rand.nextFloat() * 2.0F - 1.0F) * super.width;
                        super.worldObj.spawnParticle("splash", super.posX + (double)f5, (double)(var30 + 1.0F), super.posZ + (double)var32, super.motionX, super.motionY, super.motionZ);
                     }
                  }
               }
            }

            if(this.ticksCatchable > 0) {
               super.motionY -= (double)(super.rand.nextFloat() * super.rand.nextFloat() * super.rand.nextFloat()) * 0.2D;
            }

            d5 = var29 * 2.0D - 1.0D;
            super.motionY += 0.03999999910593033D * d5;
            if(var29 > 0.0D) {
               var26 = (float)((double)var26 * 0.9D);
               super.motionY *= 0.8D;
            }

            super.motionX *= (double)var26;
            super.motionY *= (double)var26;
            super.motionZ *= (double)var26;
            this.setPosition(super.posX, super.posY, super.posZ);
         }
      }

   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      par1NBTTagCompound.setShort("xTile", (short)this.xTile);
      par1NBTTagCompound.setShort("yTile", (short)this.yTile);
      par1NBTTagCompound.setShort("zTile", (short)this.zTile);
      par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
      par1NBTTagCompound.setByte("shake", (byte)super.shake);
      par1NBTTagCompound.setByte("inGround", (byte)(this.inGround?1:0));
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      this.xTile = par1NBTTagCompound.getShort("xTile");
      this.yTile = par1NBTTagCompound.getShort("yTile");
      this.zTile = par1NBTTagCompound.getShort("zTile");
      this.inTile = par1NBTTagCompound.getByte("inTile") & 255;
      super.shake = par1NBTTagCompound.getByte("shake") & 255;
      this.inGround = par1NBTTagCompound.getByte("inGround") == 1;
   }

   @SideOnly(Side.CLIENT)
   public float getShadowSize() {
      return 0.0F;
   }

   public int catchFish() {
      if(super.worldObj.isRemote) {
         return 0;
      } else {
         byte b0 = 0;
         if(super.bobber != null) {
            double entityitem = super.angler.posX - super.posX;
            double d1 = super.angler.posY - super.posY;
            double d2 = super.angler.posZ - super.posZ;
            double d3 = (double)MathHelper.sqrt_double(entityitem * entityitem + d1 * d1 + d2 * d2);
            double d4 = 0.1D;
            super.bobber.motionX += entityitem * d4;
            super.bobber.motionY += d1 * d4 + (double)MathHelper.sqrt_double(d3) * 0.08D;
            super.bobber.motionZ += d2 * d4;
            b0 = 3;
         } else if(this.ticksCatchable > 0) {
            EntityItem entityitem1 = new EntityItem(super.worldObj, super.posX, super.posY, super.posZ, new ItemStack(Item.fishRaw));
            entityitem1.setEntityItemStack(AtumFish.getRandomFish());
            double d5 = super.angler.posX - super.posX;
            double d6 = super.angler.posY - super.posY;
            double d7 = super.angler.posZ - super.posZ;
            double d8 = (double)MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);
            double d9 = 0.1D;
            entityitem1.motionX = d5 * d9;
            entityitem1.motionY = d6 * d9 + (double)MathHelper.sqrt_double(d8) * 0.08D;
            entityitem1.motionZ = d7 * d9;
            super.worldObj.spawnEntityInWorld(entityitem1);
            super.angler.addStat(StatList.fishCaughtStat, 1);
            super.angler.worldObj.spawnEntityInWorld(new EntityXPOrb(super.angler.worldObj, super.angler.posX, super.angler.posY + 0.5D, super.angler.posZ + 0.5D, super.rand.nextInt(6) + 1));
            b0 = 1;
         }

         if(this.inGround) {
            b0 = 2;
         }

         this.setDead();
         super.angler.fishEntity = null;
         return b0;
      }
   }

   public void setDead() {
      super.setDead();
      if(super.angler != null) {
         super.angler.fishEntity = null;
      }

   }
}
