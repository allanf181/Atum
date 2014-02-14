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

public class EntityBarbarian extends EntityMob implements IAtumDayMob {

	public EntityBarbarian(World par1World) {
		super(par1World);
		this.experienceValue = 9;
		
		this.setCurrentItemOrArmor(0, new ItemStack(AtumItems.ITEM_GREATSWORD));
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
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(10.0D);
	}

	@Override
	protected void addRandomArmor() {
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
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param par2 - Level of Looting used to kill this mob.
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2) {
		if(rand.nextInt(20) == 0) {
			int damage = (int) (AtumItems.ITEM_GREATSWORD.getMaxDamage() - rand.nextInt(AtumItems.ITEM_GREATSWORD.getMaxDamage()) * 0.5 + 20);
			this.entityDropItem(new ItemStack(AtumItems.ITEM_GREATSWORD.itemID, 1, damage), 0.0F);
		}

		if(rand.nextInt(4) == 0) {
			int amount = rand.nextInt(2) + 1;
			this.dropItem(Item.goldNugget.itemID, amount);
		}
	}

	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
	 */
	@Override
	protected void attackEntity(Entity mob, float par2) {

		if(!(mob instanceof EntityStoneSoldier || mob instanceof EntityPharaoh)) {
			float j = 1.2f;
			mob.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * (float) j * 0.5F), 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * (float) j * 0.5F));
		}
		super.attackEntity(mob, par2);
	}
}
