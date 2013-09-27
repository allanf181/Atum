package rebelkeithy.mods.atum.artifacts.arrow;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import rebelkeithy.mods.particleregistry.ParticleRegistry;
import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityArrowPoison extends CustomArrow implements IProjectile, IThrowableEntity {

   private int xTile = -1;
   private int yTile = -1;
   private int zTile = -1;
   private int inTile = 0;
   private int inData = 0;
   private boolean inGround = false;
   public int canBePickedUp = 0;
   public int arrowShake = 0;
   public Entity shootingEntity;
   private int ticksInGround;
   private int ticksInAir = 0;
   private double damage = 2.0D;
   private int knockbackStrength;


   public EntityArrowPoison(World par1World) {
      super(par1World);
      super.renderDistanceWeight = 10.0D;
      this.setSize(0.5F, 0.5F);
   }

   public EntityArrowPoison(World par1World, double par2, double par4, double par6) {
      super(par1World);
      super.renderDistanceWeight = 10.0D;
      this.setSize(0.5F, 0.5F);
      this.setPosition(par2, par4, par6);
      super.yOffset = 0.0F;
   }

   public EntityArrowPoison(World par1World, EntityLivingBase par2EntityLiving, EntityLiving par3EntityLiving, float par4, float par5) {
      super(par1World);
      super.renderDistanceWeight = 10.0D;
      this.shootingEntity = par2EntityLiving;
      if(par2EntityLiving instanceof EntityPlayer) {
         this.canBePickedUp = 1;
      }

      super.posY = par2EntityLiving.posY + (double)par2EntityLiving.getEyeHeight() - 0.10000000149011612D;
      double d0 = par3EntityLiving.posX - par2EntityLiving.posX;
      double d1 = par3EntityLiving.boundingBox.minY + (double)(par3EntityLiving.height / 3.0F) - super.posY;
      double d2 = par3EntityLiving.posZ - par2EntityLiving.posZ;
      double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);
      if(d3 >= 1.0E-7D) {
         float f2 = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
         float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D));
         double d4 = d0 / d3;
         double d5 = d2 / d3;
         this.setLocationAndAngles(par2EntityLiving.posX + d4, super.posY, par2EntityLiving.posZ + d5, f2, f3);
         super.yOffset = 0.0F;
         float f4 = (float)d3 * 0.2F;
         this.setThrowableHeading(d0, d1 + (double)f4, d2, par4, par5);
      }

   }

   public EntityArrowPoison(World par1World, EntityLivingBase par2EntityLiving, float par3) {
      super(par1World);
      super.renderDistanceWeight = 10.0D;
      this.shootingEntity = par2EntityLiving;
      if(par2EntityLiving instanceof EntityPlayer) {
         this.canBePickedUp = 1;
      }

      this.setSize(0.5F, 0.5F);
      this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY + (double)par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
      super.posX -= (double)(MathHelper.cos(super.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
      super.posY -= 0.10000000149011612D;
      super.posZ -= (double)(MathHelper.sin(super.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
      this.setPosition(super.posX, super.posY, super.posZ);
      super.yOffset = 0.0F;
      super.motionX = (double)(-MathHelper.sin(super.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(super.rotationPitch / 180.0F * 3.1415927F));
      super.motionZ = (double)(MathHelper.cos(super.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(super.rotationPitch / 180.0F * 3.1415927F));
      super.motionY = (double)(-MathHelper.sin(super.rotationPitch / 180.0F * 3.1415927F));
      this.setThrowableHeading(super.motionX, super.motionY, super.motionZ, par3 * 1.5F, 1.0F);
   }

   protected void entityInit() {
      super.dataWatcher.addObject(16, Byte.valueOf((byte)0));
   }

   public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8) {
      float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
      par1 /= (double)f2;
      par3 /= (double)f2;
      par5 /= (double)f2;
      par1 += super.rand.nextGaussian() * (double)(super.rand.nextBoolean()?-1:1) * 0.007499999832361937D * (double)par8;
      par3 += super.rand.nextGaussian() * (double)(super.rand.nextBoolean()?-1:1) * 0.007499999832361937D * (double)par8;
      par5 += super.rand.nextGaussian() * (double)(super.rand.nextBoolean()?-1:1) * 0.007499999832361937D * (double)par8;
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
      this.setPosition(par1, par3, par5);
      this.setRotation(par7, par8);
   }

   @SideOnly(Side.CLIENT)
   public void setVelocity(double par1, double par3, double par5) {
      super.motionX = par1;
      super.motionY = par3;
      super.motionZ = par5;
      if(super.prevRotationPitch == 0.0F && super.prevRotationYaw == 0.0F) {
         float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
         super.prevRotationYaw = super.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / 3.141592653589793D);
         super.prevRotationPitch = super.rotationPitch = (float)(Math.atan2(par3, (double)f) * 180.0D / 3.141592653589793D);
         super.prevRotationPitch = super.rotationPitch;
         super.prevRotationYaw = super.rotationYaw;
         this.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
         this.ticksInGround = 0;
      }

   }

   public void onUpdate() {
      super.onUpdate();
      if(super.prevRotationPitch == 0.0F && super.prevRotationYaw == 0.0F) {
         float i = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
         super.prevRotationYaw = super.rotationYaw = (float)(Math.atan2(super.motionX, super.motionZ) * 180.0D / 3.141592653589793D);
         super.prevRotationPitch = super.rotationPitch = (float)(Math.atan2(super.motionY, (double)i) * 180.0D / 3.141592653589793D);
      }

      int var16 = super.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
      if(var16 > 0) {
         Block.blocksList[var16].setBlockBoundsBasedOnState(super.worldObj, this.xTile, this.yTile, this.zTile);
         AxisAlignedBB vec3 = Block.blocksList[var16].getCollisionBoundingBoxFromPool(super.worldObj, this.xTile, this.yTile, this.zTile);
         if(vec3 != null && vec3.isVecInside(super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX, super.posY, super.posZ))) {
            this.inGround = true;
         }
      }

      if(this.arrowShake > 0) {
         --this.arrowShake;
      }

      if(this.inGround) {
         int var17 = super.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
         int vec31 = super.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
         if(var17 == this.inTile && vec31 == this.inData) {
            ++this.ticksInGround;
            if(this.ticksInGround == 1200) {
               this.setDead();
            }
         } else {
            this.inGround = false;
            super.motionX *= (double)(super.rand.nextFloat() * 0.2F);
            super.motionY *= (double)(super.rand.nextFloat() * 0.2F);
            super.motionZ *= (double)(super.rand.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
         }
      } else {
         ++this.ticksInAir;
         Vec3 var18 = super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX, super.posY, super.posZ);
         Vec3 var19 = super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ);
         MovingObjectPosition movingobjectposition = super.worldObj.rayTraceBlocks_do_do(var18, var19, false, true);
         var18 = super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX, super.posY, super.posZ);
         var19 = super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ);
         if(movingobjectposition != null) {
            var19 = super.worldObj.getWorldVec3Pool().getVecFromPool(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
         }

         Entity entity = null;
         List list = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.addCoord(super.motionX, super.motionY, super.motionZ).expand(1.0D, 1.0D, 1.0D));
         double d0 = 0.0D;

         int l;
         float f1;
         for(l = 0; l < list.size(); ++l) {
            Entity f2 = (Entity)list.get(l);
            if(f2.canBeCollidedWith() && (f2 != this.shootingEntity || this.ticksInAir >= 5)) {
               f1 = 0.3F;
               AxisAlignedBB f3 = f2.boundingBox.expand((double)f1, (double)f1, (double)f1);
               MovingObjectPosition f4 = f3.calculateIntercept(var18, var19);
               if(f4 != null) {
                  double j1 = var18.distanceTo(f4.hitVec);
                  if(j1 < d0 || d0 == 0.0D) {
                     entity = f2;
                     d0 = j1;
                  }
               }
            }
         }

         if(entity != null) {
            movingobjectposition = new MovingObjectPosition(entity);
         }

         if(movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer) {
            EntityPlayer var20 = (EntityPlayer)movingobjectposition.entityHit;
            if(var20.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(var20)) {
               movingobjectposition = null;
            }
         }

         float var21;
         float var22;
         if(movingobjectposition != null) {
            if(movingobjectposition.entityHit != null) {
               var22 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionY * super.motionY + super.motionZ * super.motionZ);
               int var23 = MathHelper.ceiling_double_int((double)var22 * this.damage);
               if(this.getIsCritical()) {
                  var23 += super.rand.nextInt(var23 / 2 + 2);
               }

               DamageSource var25 = null;
               if(this.shootingEntity == null) {
                  var25 = DamageSource.causeThrownDamage(this, this);
               } else {
                  var25 = DamageSource.causeThrownDamage(this, this.shootingEntity);
               }

               if(this.isBurning() && !(movingobjectposition.entityHit instanceof EntityEnderman)) {
                  movingobjectposition.entityHit.setFire(5);
               }

               if(movingobjectposition.entityHit.attackEntityFrom(var25, var23)) {
                  if(movingobjectposition.entityHit instanceof EntityLiving) {
                     EntityLiving entityliving = (EntityLiving)movingobjectposition.entityHit;
                     entityliving.addPotionEffect(new PotionEffect(Potion.poison.id, 140, 2, false));
                     if(!super.worldObj.isRemote) {
                        entityliving.setArrowCountInEntity(entityliving.getArrowCountInEntity() + 1);
                     }

                     if(this.knockbackStrength > 0) {
                        var21 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
                        if(var21 > 0.0F) {
                           movingobjectposition.entityHit.addVelocity(super.motionX * (double)this.knockbackStrength * 0.6000000238418579D / (double)var21, 0.1D, super.motionZ * (double)this.knockbackStrength * 0.6000000238418579D / (double)var21);
                        }
                     }

                     if(this.shootingEntity != null) {
                        EnchantmentThorns.func_92096_a(this.shootingEntity, entityliving, super.rand);
                     }

                     if(this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(6, 0));
                     }
                  }

                  this.playSound("random.bowhit", 1.0F, 1.2F / (super.rand.nextFloat() * 0.2F + 0.9F));
                  if(!(movingobjectposition.entityHit instanceof EntityEnderman)) {
                     this.setDead();
                  }
               } else {
                  super.motionX *= -0.10000000149011612D;
                  super.motionY *= -0.10000000149011612D;
                  super.motionZ *= -0.10000000149011612D;
                  super.rotationYaw += 180.0F;
                  super.prevRotationYaw += 180.0F;
                  this.ticksInAir = 0;
               }
            } else {
               this.xTile = movingobjectposition.blockX;
               this.yTile = movingobjectposition.blockY;
               this.zTile = movingobjectposition.blockZ;
               this.inTile = super.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
               this.inData = super.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
               super.motionX = (double)((float)(movingobjectposition.hitVec.xCoord - super.posX));
               super.motionY = (double)((float)(movingobjectposition.hitVec.yCoord - super.posY));
               super.motionZ = (double)((float)(movingobjectposition.hitVec.zCoord - super.posZ));
               var22 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionY * super.motionY + super.motionZ * super.motionZ);
               super.posX -= super.motionX / (double)var22 * 0.05000000074505806D;
               super.posY -= super.motionY / (double)var22 * 0.05000000074505806D;
               super.posZ -= super.motionZ / (double)var22 * 0.05000000074505806D;
               this.playSound("random.bowhit", 1.0F, 1.2F / (super.rand.nextFloat() * 0.2F + 0.9F));
               this.inGround = true;
               this.arrowShake = 7;
               this.setIsCritical(false);
               if(this.inTile != 0) {
                  Block.blocksList[this.inTile].onEntityCollidedWithBlock(super.worldObj, this.xTile, this.yTile, this.zTile, this);
               }
            }
         }

         if(this.getIsCritical() && super.worldObj.isRemote) {
            for(l = 0; l < 4; ++l) {
               ParticleRegistry.spawnParticle("coloredcrit", super.worldObj, super.posX + super.motionX * (double)l / 4.0D, super.posY + super.motionY * (double)l / 4.0D, super.posZ + super.motionZ * (double)l / 4.0D, -super.motionX, -super.motionY + 0.2D, -super.motionZ, 0.5D, 0.9D, 0.5D);
            }
         }

         super.posX += super.motionX;
         super.posY += super.motionY;
         super.posZ += super.motionZ;
         var22 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
         super.rotationYaw = (float)(Math.atan2(super.motionX, super.motionZ) * 180.0D / 3.141592653589793D);

         for(super.rotationPitch = (float)(Math.atan2(super.motionY, (double)var22) * 180.0D / 3.141592653589793D); super.rotationPitch - super.prevRotationPitch < -180.0F; super.prevRotationPitch -= 360.0F) {
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
         float var24 = 0.99F;
         f1 = 0.05F;
         if(this.isInWater()) {
            for(int var26 = 0; var26 < 4; ++var26) {
               var21 = 0.25F;
               super.worldObj.spawnParticle("bubble", super.posX - super.motionX * (double)var21, super.posY - super.motionY * (double)var21, super.posZ - super.motionZ * (double)var21, super.motionX, super.motionY, super.motionZ);
            }

            var24 = 0.45F;
         }

         super.motionX *= (double)var24;
         super.motionY *= (double)var24;
         super.motionZ *= (double)var24;
         super.motionY -= (double)f1;
         this.setPosition(super.posX, super.posY, super.posZ);
         this.doBlockCollisions();
      }

   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      par1NBTTagCompound.setShort("xTile", (short)this.xTile);
      par1NBTTagCompound.setShort("yTile", (short)this.yTile);
      par1NBTTagCompound.setShort("zTile", (short)this.zTile);
      par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
      par1NBTTagCompound.setByte("inData", (byte)this.inData);
      par1NBTTagCompound.setByte("shake", (byte)this.arrowShake);
      par1NBTTagCompound.setByte("inGround", (byte)(this.inGround?1:0));
      par1NBTTagCompound.setByte("pickup", (byte)this.canBePickedUp);
      par1NBTTagCompound.setDouble("damage", this.damage);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      this.xTile = par1NBTTagCompound.getShort("xTile");
      this.yTile = par1NBTTagCompound.getShort("yTile");
      this.zTile = par1NBTTagCompound.getShort("zTile");
      this.inTile = par1NBTTagCompound.getByte("inTile") & 255;
      this.inData = par1NBTTagCompound.getByte("inData") & 255;
      this.arrowShake = par1NBTTagCompound.getByte("shake") & 255;
      this.inGround = par1NBTTagCompound.getByte("inGround") == 1;
      if(par1NBTTagCompound.hasKey("damage")) {
         this.damage = par1NBTTagCompound.getDouble("damage");
      }

      if(par1NBTTagCompound.hasKey("pickup")) {
         this.canBePickedUp = par1NBTTagCompound.getByte("pickup");
      } else if(par1NBTTagCompound.hasKey("player")) {
         this.canBePickedUp = par1NBTTagCompound.getBoolean("player")?1:0;
      }

   }

   public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
      if(!super.worldObj.isRemote && this.inGround && this.arrowShake <= 0) {
         boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && par1EntityPlayer.capabilities.isCreativeMode;
         if(this.canBePickedUp == 1 && !par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.arrow, 1))) {
            flag = false;
         }

         if(flag) {
            this.playSound("random.pop", 0.2F, ((super.rand.nextFloat() - super.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            par1EntityPlayer.onItemPickup(this, 1);
            this.setDead();
         }
      }

   }

   protected boolean canTriggerWalking() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public float getShadowSize() {
      return 0.0F;
   }

   public void setDamage(double par1) {
      this.damage = par1;
   }

   public double getDamage() {
      return this.damage;
   }

   public void setKnockbackStrength(int par1) {
      this.knockbackStrength = par1;
   }

   public boolean canAttackWithItem() {
      return false;
   }

   public void setIsCritical(boolean par1) {
      byte b0 = super.dataWatcher.getWatchableObjectByte(16);
      if(par1) {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 1)));
      } else {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -2)));
      }

   }

   public boolean getIsCritical() {
      byte b0 = super.dataWatcher.getWatchableObjectByte(16);
      return (b0 & 1) != 0;
   }

   public Entity getThrower() {
      return this.shootingEntity;
   }

   public void setThrower(Entity entity) {
      this.shootingEntity = entity;
   }

   public String getTexture() {
      return "/mods/Atum/textures/projectiles/arrows_poison.png";
   }
}
