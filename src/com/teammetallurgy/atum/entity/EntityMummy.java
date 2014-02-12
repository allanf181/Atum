package com.teammetallurgy.atum.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.teammetallurgy.atum.items.Items;

public class EntityMummy extends EntityMob implements IAtumNightMob {

	public EntityMummy(World par1World) {
		super(par1World);
		this.experienceValue = 8;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.53000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(2.0D);
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
	 * Get this Entity's EnumCreatureAttribute
	 */
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if(par1DamageSource.isFireDamage()) {
			par2 += 1;
		}
		if(this.isBurning()) {
			par2 = (int) (par2 * 1.5);
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		boolean flag = super.attackEntityAsMob(par1Entity);

		if(flag && this.isBurning() && this.rand.nextFloat() < (float) this.worldObj.difficultySetting * 0.4F) {
			par1Entity.setFire(2 * this.worldObj.difficultySetting);
		}

		return flag;
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param par2 - Level of Looting used to kill this mob.
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2) {
		if(rand.nextInt(4) == 0) {
			this.dropItem(Item.rottenFlesh.itemID, 1);
		}
		if(rand.nextInt(4) == 0) {
			int amount = rand.nextInt(2) + 1;
			this.dropItem(Items.scrap.itemID, amount);
		}
	}
}
