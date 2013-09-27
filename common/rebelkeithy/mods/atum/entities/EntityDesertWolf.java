package rebelkeithy.mods.atum.entities;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.AtumItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityDesertWolf extends EntityTameable implements IAtumDayMob {

   private float field_70926_e;
   private float field_70924_f;
   private boolean isShaking;
   private boolean field_70928_h;
   private float timeWolfIsShaking;
   private float prevTimeWolfIsShaking;


   public EntityDesertWolf(World par1World) {
      super(par1World);
      this.setSize(0.6F, 0.8F);
      this.getNavigator().setAvoidsWater(true);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, this.aiSit);
      this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
      this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
      this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
      this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
      this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(9, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
      this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
      this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntitySheep.class, 200, false));
   }

   public boolean isAIEnabled() {
      return true;
   }

   protected Entity findPlayerToAttack() {
      EntityPlayer entityplayer = super.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
      return entityplayer != null && this.canEntityBeSeen(entityplayer)?entityplayer:null;
   }

   @Override
   public void setAttackTarget(EntityLivingBase par1EntityLiving) {
      super.setAttackTarget(par1EntityLiving);
      if(par1EntityLiving instanceof EntityPlayer) {
         this.setAngry(true);
      }

   }

   protected void updateAITick() {
      super.dataWatcher.updateObject(18, this.getHealth());
   }

 

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(18, this.getHealth());
      super.dataWatcher.addObject(19, new Byte((byte)0));
      super.dataWatcher.addObject(20, new Byte((byte)BlockColored.getBlockFromDye(1)));
      this.setAngry(true);
      super.experienceValue = 6;
   }

   public boolean getCanSpawnHere() {
      return super.worldObj.checkNoEntityCollision(super.boundingBox) && super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox);
   }

   protected void playStepSound(int par1, int par2, int par3, int par4) {
      this.playSound("mob.wolf.step", 0.15F, 1.0F);
   }

   @SideOnly(Side.CLIENT)
   public String getTexture() {
      return this.isTamed()?"atum:textures/entities/DesertWolf_tame.png":(this.isAngry()?"atum:textures/entities/DesertWolf_angry.png":"atum:textures/entities/DesertWolf");
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setBoolean("Angry", this.isAngry());
      par1NBTTagCompound.setByte("CollarColor", (byte)this.getCollarColor());
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound);
      this.setAngry(par1NBTTagCompound.getBoolean("Angry"));
      if(par1NBTTagCompound.hasKey("CollarColor")) {
         this.setCollarColor(par1NBTTagCompound.getByte("CollarColor"));
      }

   }

   protected boolean canDespawn() {
      return this.isAngry();
   }

   protected String getLivingSound() {
      return this.isAngry()?"mob.wolf.growl":(super.rand.nextInt(3) == 0?(this.isTamed() && super.dataWatcher.getWatchableObjectInt(18) < 10?"mob.wolf.whine":"mob.wolf.panting"):"mob.wolf.bark");
   }

   protected String getHurtSound() {
      return "mob.wolf.hurt";
   }

   protected String getDeathSound() {
      return "mob.wolf.death";
   }

   protected float getSoundVolume() {
      return 0.4F;
   }

   protected void dropFewItems(boolean par1, int par2) {
      if(super.rand.nextInt(10) == 0) {
         this.dropItem(Item.bone.itemID, 1);
      }

      if(super.rand.nextInt(4) == 0) {
         int amount = super.rand.nextInt(2) + 1;
         this.dropItem(AtumItems.pelt.itemID, amount);
      }

   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if(!super.worldObj.isRemote && this.isShaking && !this.field_70928_h && !this.hasPath() && super.onGround) {
         this.field_70928_h = true;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
         super.worldObj.setEntityState(this, (byte)8);
      }

   }

   public void onUpdate() {
      super.onUpdate();
      if(!super.worldObj.isRemote && super.worldObj.difficultySetting == 0) {
         this.setDead();
      } else {
         this.field_70924_f = this.field_70926_e;
         if(this.func_70922_bv()) {
            this.field_70926_e += (1.0F - this.field_70926_e) * 0.4F;
         } else {
            this.field_70926_e += (0.0F - this.field_70926_e) * 0.4F;
         }

         if(this.func_70922_bv()) {
            super.numTicksToChaseTarget = 10;
         }

         if(this.isWet()) {
            this.isShaking = true;
            this.field_70928_h = false;
            this.timeWolfIsShaking = 0.0F;
            this.prevTimeWolfIsShaking = 0.0F;
         } else if((this.isShaking || this.field_70928_h) && this.field_70928_h) {
            if(this.timeWolfIsShaking == 0.0F) {
               this.playSound("mob.wolf.shake", this.getSoundVolume(), (super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
            this.timeWolfIsShaking += 0.05F;
            if(this.prevTimeWolfIsShaking >= 2.0F) {
               this.isShaking = false;
               this.field_70928_h = false;
               this.prevTimeWolfIsShaking = 0.0F;
               this.timeWolfIsShaking = 0.0F;
            }

            if(this.timeWolfIsShaking > 0.4F) {
               float f = (float)super.boundingBox.minY;
               int i = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * 3.1415927F) * 7.0F);

               for(int j = 0; j < i; ++j) {
                  float f1 = (super.rand.nextFloat() * 2.0F - 1.0F) * super.width * 0.5F;
                  float f2 = (super.rand.nextFloat() * 2.0F - 1.0F) * super.width * 0.5F;
                  super.worldObj.spawnParticle("splash", super.posX + (double)f1, (double)(f + 0.8F), super.posZ + (double)f2, super.motionX, super.motionY, super.motionZ);
               }
            }
         }

      }
   }

   @SideOnly(Side.CLIENT)
   public boolean getWolfShaking() {
      return this.isShaking;
   }

   @SideOnly(Side.CLIENT)
   public float getShadingWhileShaking(float par1) {
      return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1) / 2.0F * 0.25F;
   }

   @SideOnly(Side.CLIENT)
   public float getShakeAngle(float par1, float par2) {
      float f2 = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1 + par2) / 1.8F;
      if(f2 < 0.0F) {
         f2 = 0.0F;
      } else if(f2 > 1.0F) {
         f2 = 1.0F;
      }

      return MathHelper.sin(f2 * 3.1415927F) * MathHelper.sin(f2 * 3.1415927F * 11.0F) * 0.15F * 3.1415927F;
   }

   @SideOnly(Side.CLIENT)
   public float getInterestedAngle(float par1) {
      return (this.field_70924_f + (this.field_70926_e - this.field_70924_f) * par1) * 0.15F * 3.1415927F;
   }

   public float getEyeHeight() {
      return super.height * 0.8F;
   }

   public int getVerticalFaceSpeed() {
      return this.isSitting()?20:super.getVerticalFaceSpeed();
   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else {
         Entity entity = par1DamageSource.getEntity();
         super.aiSit.setSitting(false);
         if(entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
            par2 = (par2 + 1) / 2;
         }

         return super.attackEntityFrom(par1DamageSource, par2);
      }
   }

   public boolean attackEntityAsMob(Entity par1Entity) {
      int i = this.isTamed()?4:2;
      return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
   }

   public boolean interact(EntityPlayer par1EntityPlayer) {
      ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
      if(this.isTamed()) {
         if(itemstack != null) {
            if(Item.itemsList[itemstack.itemID] instanceof ItemFood) {
               ItemFood i = (ItemFood)Item.itemsList[itemstack.itemID];
               if(i.isWolfsFavoriteMeat() && super.dataWatcher.getWatchableObjectInt(18) < 20) {
                  if(!par1EntityPlayer.capabilities.isCreativeMode) {
                     --itemstack.stackSize;
                  }

                  this.heal(i.getHealAmount());
                  if(itemstack.stackSize <= 0) {
                     par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                  }

                  return true;
               }
            } else if(itemstack.itemID == Item.dyePowder.itemID) {
               int var4 = BlockColored.getBlockFromDye(itemstack.getItemDamage());
               if(var4 != this.getCollarColor()) {
                  this.setCollarColor(var4);
                  if(!par1EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
                     par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                  }

                  return true;
               }
            }
         }

         if(par1EntityPlayer.username.equalsIgnoreCase(this.getOwnerName()) && !super.worldObj.isRemote && !this.isBreedingItem(itemstack)) {
            super.aiSit.setSitting(!this.isSitting());
            super.isJumping = false;
            this.setPathToEntity((PathEntity)null);
         }
      } else if(itemstack != null && itemstack.itemID == Item.bone.itemID && !this.isAngry()) {
         if(!par1EntityPlayer.capabilities.isCreativeMode) {
            --itemstack.stackSize;
         }

         if(itemstack.stackSize <= 0) {
            par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
         }

         if(!super.worldObj.isRemote) {
            if(super.rand.nextInt(3) == 0) {
               this.setTamed(true);
               this.setPathToEntity((PathEntity)null);
               this.setAttackTarget((EntityLiving)null);
               super.aiSit.setSitting(true);
               this.setHealth(20);
               this.setOwner(par1EntityPlayer.username);
               this.playTameEffect(true);
               super.worldObj.setEntityState(this, (byte)7);
            } else {
               this.playTameEffect(false);
               super.worldObj.setEntityState(this, (byte)6);
            }
         }

         return true;
      }

      return super.interact(par1EntityPlayer);
   }

   @SideOnly(Side.CLIENT)
   public void handleHealthUpdate(byte par1) {
      if(par1 == 8) {
         this.field_70928_h = true;
         this.timeWolfIsShaking = 0.0F;
         this.prevTimeWolfIsShaking = 0.0F;
      } else {
         super.handleHealthUpdate(par1);
      }

   }

   @SideOnly(Side.CLIENT)
   public float getTailRotation() {
      return this.isAngry()?1.5393804F:(this.isTamed()?(0.55F - (float)(20 - super.dataWatcher.getWatchableObjectInt(18)) * 0.02F) * 3.1415927F:0.62831855F);
   }

   public boolean isBreedingItem(ItemStack par1ItemStack) {
      return par1ItemStack == null?false:(!(Item.itemsList[par1ItemStack.itemID] instanceof ItemFood)?false:((ItemFood)Item.itemsList[par1ItemStack.itemID]).isWolfsFavoriteMeat());
   }

   public int getMaxSpawnedInChunk() {
      return 8;
   }

   public boolean isAngry() {
      return (super.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
   }

   public void setAngry(boolean par1) {
      byte b0 = super.dataWatcher.getWatchableObjectByte(16);
      if(par1) {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 2)));
      } else {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -3)));
      }

   }

   public int getCollarColor() {
      return super.dataWatcher.getWatchableObjectByte(20) & 15;
   }

   public void setCollarColor(int par1) {
      super.dataWatcher.updateObject(20, Byte.valueOf((byte)(par1 & 15)));
   }

   public EntityDesertWolf spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
      EntityDesertWolf entitywolf = new EntityDesertWolf(super.worldObj);
      String s = this.getOwnerName();
      if(s != null && s.trim().length() > 0) {
         entitywolf.setOwner(s);
         entitywolf.setTamed(true);
      }

      return entitywolf;
   }

   public void func_70918_i(boolean par1) {
      byte b0 = super.dataWatcher.getWatchableObjectByte(19);
      if(par1) {
         super.dataWatcher.updateObject(19, Byte.valueOf((byte)1));
      } else {
         super.dataWatcher.updateObject(19, Byte.valueOf((byte)0));
      }

   }

   public boolean canMateWith(EntityAnimal par1EntityAnimal) {
      if(par1EntityAnimal == this) {
         return false;
      } else if(!this.isTamed()) {
         return false;
      } else if(!(par1EntityAnimal instanceof EntityDesertWolf)) {
         return false;
      } else {
         EntityDesertWolf entitywolf = (EntityDesertWolf)par1EntityAnimal;
         return !entitywolf.isTamed()?false:(entitywolf.isSitting()?false:this.isInLove() && entitywolf.isInLove());
      }
   }

   public boolean func_70922_bv() {
      return super.dataWatcher.getWatchableObjectByte(19) == 1;
   }

   public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
      return this.spawnBabyAnimal(par1EntityAgeable);
   }
}
