package com.teammetallurgy.atum.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.teammetallurgy.atum.items.AtumItems;

public class EntityStoneSoldier extends EntityStone implements IAtumNightMob, IAtumDayMob {

	public EntityStoneSoldier(World par1World) {
		super(par1World);
		this.isImmuneToFire = true;
		this.experienceValue = 8;

		this.setCurrentItemOrArmor(0, new ItemStack(AtumItems.ITEM_STONESOLDIER_SWORD));

		for(int i = 0; i < this.equipmentDropChances.length; ++i) {
			this.equipmentDropChances[i] = 0F;
		}
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.53000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(10.0D);
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
	 * Get this Entity's EnumCreatureAttribute
	 */
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if(super.attackEntityFrom(par1DamageSource, par2)) {
			if(par1DamageSource.getEntity() != null) {
				Entity par1Entity = par1DamageSource.getEntity();
				int j = 0;
				if(par1Entity instanceof EntityLiving) {
					j += EnchantmentHelper.getKnockbackModifier((EntityLiving) par1Entity, this);

					if(j > 0) {
						this.motionX /= 0.6D;
						this.motionZ /= 0.6D;
						this.addVelocity((double) (MathHelper.sin(par1Entity.rotationYaw * (float) Math.PI / 180.0F) * (float) j * 0.5F), -0.1D, (double) (-MathHelper.cos(par1Entity.rotationYaw * (float) Math.PI / 180.0F) * (float) j * 0.5F));
					}
				}

			}
			return true;
		}

		return false;
	}

	/**
	 * knocks back this entity
	 */
	@Override
	public void knockBack(Entity par1Entity, float par2, double par3, double par5) {
		this.isAirBorne = true;
		float f = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
		float f1 = 0.2F;
		this.motionX /= 2.0D;
		this.motionY /= 2.0D;
		this.motionZ /= 2.0D;
		this.motionX -= par3 / (double) f * (double) f1;
		this.motionZ -= par5 / (double) f * (double) f1;

		if(this.motionY > 0.4000000059604645D) {
			this.motionY = 0.4000000059604645D;
		}
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param par2 - Level of Looting used to kill this mob.
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2) {
		if(this.rand.nextInt(4) == 0) {
			int amount = rand.nextInt(2) + 1;
			this.dropItem(AtumItems.ITEM_STONECHUNK, amount);
		}
	}

}
