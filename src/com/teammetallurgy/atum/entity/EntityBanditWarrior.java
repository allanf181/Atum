package com.teammetallurgy.atum.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.teammetallurgy.atum.items.AtumItems;

public class EntityBanditWarrior extends EntityMob implements IAtumDayMob {

	public EntityBanditWarrior(World par1World) {
		super(par1World);
		this.experienceValue = 8;

		super.setCurrentItemOrArmor(0, new ItemStack(AtumItems.ITEM_SCIMITAR));
		this.enchantEquipment();

		for(int i = 0; i < this.equipmentDropChances.length; ++i) {
			this.equipmentDropChances[i] = 0F;
		}
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.53000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(10.0D);
	}

	public String getTexture() {
		return "atum:textures/entities/BanditWarrior.png";
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

	/**
	 * Returns the amount of damage a mob should deal.
	 */
	public int getAttackStrength(Entity par1Entity) {
		ItemStack itemstack = this.getHeldItem();
		float f = (float) (this.getMaxHealth() - this.getHealth()) / (float) this.getMaxHealth();
		int i = 3 + MathHelper.floor_float(f * 4.0F);

		return i;
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param par2 - Level of Looting used to kill this mob.
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2) {
		if(rand.nextInt(20) == 0) {
			int damage = (int) (AtumItems.ITEM_SCIMITAR.getMaxDamage() - rand.nextInt(AtumItems.ITEM_SCIMITAR.getMaxDamage()) * 0.5 + 20);
			this.entityDropItem(new ItemStack(AtumItems.ITEM_SCIMITAR.itemID, 1, damage), 0.0F);
		}

		if(rand.nextInt(10) == 0) {
			int amount = rand.nextInt(2) + 1;
			this.dropItem(Item.goldNugget.itemID, amount);
		}
	}
}
