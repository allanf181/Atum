package com.teammetallurgy.atum.entity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.teammetallurgy.atum.items.Items;

public class EntityBanditArcher extends EntityMob implements IRangedAttackMob, IAtumDayMob {
	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 0.25F, 20, 60, 15.0F);
	private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.31F, false);

	public EntityBanditArcher(World par1World) {
		super(par1World);
		this.setCurrentItemOrArmor(0, new ItemStack(Items.ITEM_BOW));
		this.enchantEquipment();

		this.setCanPickUpLoot(this.rand.nextFloat() < equipmentDropChances[this.worldObj.difficultySetting]);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIRestrictSun(this));
		this.tasks.addTask(3, new EntityAIWander(this, 0.8));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.tasks.addTask(6, this.aiArrowAttack);
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16, true));

		if(par1World != null && !par1World.isRemote) {
			this.setCombatTask();
		}
		this.experienceValue = 6;
		
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.53000000417232513D); 
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(10.0D);
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
	}

	/**
	 * Checks to make sure the light is not too bright where the mob is spawning
	 */
	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		if(super.attackEntityAsMob(par1Entity)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	/**
	 * Makes entity wear random armor based on difficulty
	 */
	@Override
	protected void addRandomArmor() {
	}

	/**
	 * sets this entity's combat AI.
	 */
	public void setCombatTask() {
		this.tasks.removeTask(this.aiAttackOnCollide);
		this.tasks.removeTask(this.aiArrowAttack);
		ItemStack itemstack = this.getHeldItem();

		if(itemstack != null && itemstack.itemID == Items.ITEM_BOW.itemID) {
			this.tasks.addTask(4, this.aiArrowAttack);
		} else {
			this.tasks.addTask(4, this.aiAttackOnCollide);
		}
	}

	/**
	 * Attack the specified entity using a ranged attack.
	 */
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLiving, float par2) {
		EntityArrow entityarrow = new EntityArrow(this.worldObj, this, par1EntityLiving, 1.6F, (float) (14 - this.worldObj.difficultySetting * 4));
		int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
		int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
		entityarrow.setDamage((double) (par2 * 2.0F) + this.rand.nextGaussian() * 0.25D + (double) ((float) this.worldObj.difficultySetting * 0.11F));

		if(i > 0) {
			entityarrow.setDamage(entityarrow.getDamage() + (double) i * 0.5D + 0.5D);
		}

		if(j > 0) {
			entityarrow.setKnockbackStrength(j);
		}

		if(EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0) {
			entityarrow.setFire(100);
		}

		this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.worldObj.spawnEntityInWorld(entityarrow);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);

		this.setCombatTask();
	}
	
	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param par2 - Level of Looting used to kill this mob.
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2) {
		if(rand.nextInt(20) == 0) {
			int damage = (int) (Items.ITEM_BOW.getMaxDamage() - rand.nextInt(Items.ITEM_BOW.getMaxDamage()) * 0.5 + 20);
			this.entityDropItem(new ItemStack(Items.ITEM_BOW.itemID, 1, damage), 0.0F);
		}

		if(rand.nextInt(10) == 0) {
			int amount = rand.nextInt(2) + 1;
			this.dropItem(Item.goldNugget.itemID, amount);
		}

		if(rand.nextInt(4) == 0) {
			int amount = rand.nextInt(3) + 1;
			this.dropItem(Item.arrow.itemID, amount);
		}
	}
}