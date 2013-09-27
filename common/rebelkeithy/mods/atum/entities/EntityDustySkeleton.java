package rebelkeithy.mods.atum.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.entities.IAtumNightMob;

public class EntityDustySkeleton extends EntityMob implements IAtumNightMob {

	public EntityDustySkeleton(World par1World) {
		super(par1World);
		super.isImmuneToFire = true;
		super.experienceValue = 6;
		this.setHealth(20);
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean getCanSpawnHere() {
		return super.worldObj.checkNoEntityCollision(super.boundingBox) && super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	public int getAttackStrength(Entity par1Entity) {
		ItemStack itemstack = this.getHeldItem();
		float f = (float) (this.getMaxHealth() - this.getHealth()) / (float) this.getMaxHealth();
		int i = 3 + MathHelper.floor_float(f * 4.0F);
		if (itemstack != null) {
			// i += itemstack.getDamageVsEntity(this, itemstack);
		}

		return i;
	}

	public float getSpeedModifier() {
		return this.getSpeedModifier() * 1.5F;
	}

	protected void dropFewItems(boolean par1, int par2) {
		switch (super.rand.nextInt(4)) {
		case 0:
			int amount = super.rand.nextInt(2) + 1;
			this.dropItem(Item.bone.itemID, amount);
		default:
		}
	}
}
