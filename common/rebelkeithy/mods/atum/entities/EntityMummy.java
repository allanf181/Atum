package rebelkeithy.mods.atum.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.AtumItems;
import rebelkeithy.mods.atum.entities.IAtumNightMob;

public class EntityMummy extends EntityMob implements IAtumNightMob {

	public EntityMummy(World par1World) {
		super(par1World);
		super.experienceValue = 8;
		this.setHealth(40);
	}
	@Override
	public void entityInit(){
		super.entityInit();
	}
	public float getSpeedModifier() {
		return this.isBurning() ? this.getSpeedModifier() * 1.4F : this.getSpeedModifier();
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

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if (par1DamageSource.isFireDamage()) {
			++par2;
		}

		if (this.isBurning()) {
			par2 = (int) ((double) par2 * 1.5D);
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		boolean flag = super.attackEntityAsMob(par1Entity);
		if (flag && this.isBurning() && super.rand.nextFloat() < (float) super.worldObj.difficultySetting * 0.4F) {
			par1Entity.setFire(2 * super.worldObj.difficultySetting);
		}

		return flag;
	}

	public int getAttackStrength(Entity par1Entity) {
		return 2;
	}

	protected void dropFewItems(boolean par1, int par2) {
		if (super.rand.nextInt(4) == 0) {
			this.dropItem(Item.rottenFlesh.itemID, 1);
		}

		if (super.rand.nextInt(4) == 0) {
			int amount = super.rand.nextInt(2) + 1;
			this.dropItem(AtumItems.scrap.itemID, amount);
		}

	}
}
