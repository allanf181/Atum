package rebelkeithy.mods.atum.entities;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.AtumConfig;
import rebelkeithy.mods.atum.AtumItems;
import rebelkeithy.mods.atum.entities.IAtumDayMob;

public class EntityBanditWarlord extends EntityMob implements IAtumDayMob {

	public EntityBanditWarlord(World par1World) {
		super(par1World);
		super.experienceValue = 16;
		this.setHealth(80);
	}

	protected void addRandomArmor() {
	}

	public float getSpeedModifier() {
		return this.getSpeedModifier();
	}

	public boolean getCanSpawnHere() {
		return super.worldObj.checkNoEntityCollision(super.boundingBox) && super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox);
	}

	public int getMaxSpawnedInChunk() {
		return 1;
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public void initCreature() {
		this.setCurrentItemOrArmor(0, new ItemStack(AtumItems.scimitar));
		EnchantmentHelper.addRandomEnchantment(super.rand, this.getHeldItem(), 5 + super.worldObj.difficultySetting * super.rand.nextInt(6));

		for (int i = 0; i < super.equipmentDropChances.length; ++i) {
			super.equipmentDropChances[i] = 0.05F;
		}

	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	public int getAttackStrength(Entity par1Entity) {
		return 4;
	}

	protected void dropFewItems(boolean par1, int par2) {
		int choice;
		if (super.rand.nextInt(20) == 0) {
			choice = (int) ((double) AtumItems.scimitar.getMaxDamage() - (double) super.rand.nextInt(AtumItems.scimitar.getMaxDamage()) * 0.5D + 20.0D);
			this.entityDropItem(new ItemStack(AtumConfig.scimitarID, 1, choice), 0.0F);
		}

		if (super.rand.nextInt(4) == 0) {
			choice = super.rand.nextInt(3) + 3;
			this.dropItem(Item.goldNugget.itemID, choice);
		}

		if (super.rand.nextInt(4) == 0) {
			choice = super.rand.nextInt(4);
			if (choice == 0) {
				this.dropItem(AtumItems.wandererHelmet.itemID, 1);
			} else if (choice == 1) {
				this.dropItem(AtumItems.wandererChest.itemID, 1);
			} else if (choice == 2) {
				this.dropItem(AtumItems.wandererLegs.itemID, 1);
			} else if (choice == 3) {
				this.dropItem(AtumItems.wandererBoots.itemID, 1);
			}
		}

	}
}
