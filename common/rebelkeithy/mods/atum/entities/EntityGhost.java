package rebelkeithy.mods.atum.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import rebelkeithy.mods.atum.AtumItems;
import rebelkeithy.mods.atum.entities.IAtumNightMob;

public class EntityGhost extends EntityMob implements IAtumNightMob {

	private int cycleHeight = 0;
	private int cycleTime = 100;

	public EntityGhost(World par1World) {
		super(par1World);
		super.experienceValue = 6;
		this.cycleTime = (int) (Math.random() * 40.0D + 80.0D);
		this.cycleHeight = (int) (Math.random() * (double) this.cycleTime);
		this.setHealth(10);
	}

	public boolean isAIEnabled() {
		return false;
	}

	public float getSpeedModifier() {
		return super.entityToAttack == null ? this.getSpeedModifier() * 1.5F : this.getSpeedModifier() * 2.0F;
	}

	public void onLivingUpdate() {
		this.cycleHeight = (this.cycleHeight + 1) % this.cycleTime;
		super.onLivingUpdate();
	}

	public boolean getCanSpawnHere() {
		return super.worldObj.checkNoEntityCollision(super.boundingBox) && super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	protected void jump() {
		super.motionY = 0.5699999868869782D;
		if (this.isPotionActive(Potion.jump)) {
			super.motionY += (double) ((float) (this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
		}

		if (this.isSprinting()) {
			float f = super.rotationYaw * 0.017453292F;
			super.motionX -= (double) (MathHelper.sin(f) * 0.2F);
			super.motionZ += (double) (MathHelper.cos(f) * 0.2F);
		}

		super.isAirBorne = true;
		ForgeHooks.onLivingJump(this);
	}

	protected void playStepSound(int par1, int par2, int par3, int par4) {
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	protected void attackEntity(Entity par1Entity, float par2) {
		if (super.attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > super.boundingBox.minY && par1Entity.boundingBox.minY < super.boundingBox.maxY) {
			super.attackTime = 20;
			this.attackEntityAsMob(par1Entity);
			if (Math.random() > 0.75D && par1Entity instanceof EntityLiving) {
				EntityLiving e = (EntityLiving) par1Entity;
				e.addPotionEffect(new PotionEffect(2, 100, 2));
			}
		}

	}

	public int getAttackStrength(Entity par1Entity) {
		return 2;
	}

	protected void dropFewItems(boolean par1, int par2) {
		if (super.rand.nextInt(4) == 0) {
			int amount = super.rand.nextInt(3) + 1;
			this.dropItem(AtumItems.ectoplasm.itemID, amount);
		}

	}

	public double getFloatingHeight() {
		return Math.cos(6.283185307179586D * ((double) this.cycleHeight / (double) this.cycleTime)) / 3.0D;
	}
}
