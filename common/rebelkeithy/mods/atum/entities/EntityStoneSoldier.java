package rebelkeithy.mods.atum.entities;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.AtumItems;
import rebelkeithy.mods.atum.entities.EntityStone;
import rebelkeithy.mods.atum.entities.IAtumDayMob;
import rebelkeithy.mods.atum.entities.IAtumNightMob;

public class EntityStoneSoldier extends EntityStone implements IAtumNightMob, IAtumDayMob {

	public EntityStoneSoldier(World par1World) {
		super(par1World);
		super.isImmuneToFire = true;
		super.experienceValue = 8;
		this.setHealth(80);
	}

	public void initCreature() {
		this.setCurrentItemOrArmor(0, new ItemStack(AtumItems.stoneSoldierSword));

		for (int i = 0; i < super.equipmentDropChances.length; ++i) {
			super.equipmentDropChances[i] = 0.0F;
		}

	}

	public float getSpeedModifier() {
		return this.getSpeedModifier() * 0.5F;
	}

	public boolean getCanSpawnHere() {
		return super.worldObj.checkNoEntityCollision(super.boundingBox) && super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if (super.attackEntityFrom(par1DamageSource, par2)) {
			if (par1DamageSource.getEntity() != null) {
				Entity par1Entity = par1DamageSource.getEntity();
				byte j = 0;
				if (par1Entity instanceof EntityLiving) {
					int j1 = j + EnchantmentHelper.getKnockbackModifier((EntityLiving) par1Entity, this);
					if (j1 > 0) {
						super.motionX /= 0.6D;
						super.motionZ /= 0.6D;
						this.addVelocity((double) (MathHelper.sin(par1Entity.rotationYaw * 3.1415927F / 180.0F) * (float) j1 * 0.5F), -0.1D, (double) (-MathHelper.cos(par1Entity.rotationYaw * 3.1415927F / 180.0F) * (float) j1 * 0.5F));
					}
				}
			}

			return true;
		} else {
			return false;
		}
	}

	public void knockBack(Entity par1Entity, int par2, double par3, double par5) {
		super.isAirBorne = true;
		float f = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
		float f1 = 0.2F;
		super.motionX /= 2.0D;
		super.motionY /= 2.0D;
		super.motionZ /= 2.0D;
		super.motionX -= par3 / (double) f * (double) f1;
		super.motionZ -= par5 / (double) f * (double) f1;
		if (super.motionY > 0.4000000059604645D) {
			super.motionY = 0.4000000059604645D;
		}

	}

	protected void dropFewItems(boolean par1, int par2) {
		if (super.rand.nextInt(4) == 0) {
			int amount = super.rand.nextInt(2) + 1;
			this.dropItem(AtumItems.stoneChunk.itemID, amount);
		}

	}

	public int getAttackStrength(Entity par1Entity) {
		return 4;
	}
}
