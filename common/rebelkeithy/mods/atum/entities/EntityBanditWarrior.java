package rebelkeithy.mods.atum.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.AtumConfig;
import rebelkeithy.mods.atum.AtumItems;
import rebelkeithy.mods.atum.entities.IAtumDayMob;

public class EntityBanditWarrior extends EntityMob implements IAtumDayMob {

	public EntityBanditWarrior(World par1World) {
		super(par1World);
		super.experienceValue = 8;
		this.setHealth(30);
	}

	public float getSpeedModifier() {
		return this.getSpeedModifier() * 1.25F;
	}

	public boolean getCanSpawnHere() {
		return super.worldObj.checkNoEntityCollision(super.boundingBox) && super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
		par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
		this.setCurrentItemOrArmor(0, new ItemStack(AtumItems.scimitar));
		for (int i = 0; i < super.equipmentDropChances.length; ++i) {
			super.equipmentDropChances[i] = 0.0F;
		}
		return par1EntityLivingData;
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	public int getAttackStrength(Entity par1Entity) {
		ItemStack itemstack = this.getHeldItem();
		float f = (float) (this.getMaxHealth() - this.getHealth()) / (float) this.getMaxHealth();
		int i = 3 + MathHelper.floor_float(f * 4.0F);
		return i;
	}

	protected void dropFewItems(boolean par1, int par2) {
		int amount;
		if (super.rand.nextInt(20) == 0) {
			amount = (int) ((double) AtumItems.scimitar.getMaxDamage() - (double) super.rand.nextInt(AtumItems.scimitar.getMaxDamage()) * 0.5D + 20.0D);
			this.entityDropItem(new ItemStack(AtumConfig.scimitarID, 1, amount), 0.0F);
		}

		if (super.rand.nextInt(10) == 0) {
			amount = super.rand.nextInt(2) + 1;
			this.dropItem(Item.goldNugget.itemID, amount);
		}

	}
}
