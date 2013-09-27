package rebelkeithy.mods.atum.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.AtumConfig;
import rebelkeithy.mods.atum.AtumItems;
import rebelkeithy.mods.atum.entities.IAtumDayMob;

public class EntityBanditArcher extends EntityMob implements IRangedAttackMob, IAtumDayMob {

	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 0.25F, 20, 60, 15.0F);
	private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.31F, false);

	public EntityBanditArcher(World par1World) {
		super(par1World);
		this.setAIMoveSpeed(0.25F);
		this.setHealth(20F);
		super.tasks.addTask(1, new EntityAISwimming(this));
		super.tasks.addTask(2, new EntityAIRestrictSun(this));
		super.tasks.addTask(3, this.aiArrowAttack);
		super.tasks.addTask(4, new EntityAIWander(this, this.getAIMoveSpeed()));
		super.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		super.tasks.addTask(6, new EntityAILookIdle(this));
		super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16, true));
		if (par1World != null && !par1World.isRemote) {
			this.setCombatTask();
		}

		super.experienceValue = 6;
	}

	public boolean getCanSpawnHere() {
		return super.worldObj.checkNoEntityCollision(super.boundingBox) && super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox);
	}

	protected boolean isValidLightLevel() {
		return true;
	}

	public boolean isAIEnabled() {
		return true;
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		return super.attackEntityAsMob(par1Entity);
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	protected void addRandomArmor() {
		this.setCurrentItemOrArmor(0, new ItemStack(AtumItems.bow));

		for (int i = 0; i < super.equipmentDropChances.length; ++i) {
			super.equipmentDropChances[i] = 0.0F;
		}

	}
	@Override
	public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
		par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
		this.addRandomArmor();
		this.enchantEquipment();
		return par1EntityLivingData;
	}
	@Override
	public void entityInit() {
		super.entityInit();
		this.setCanPickUpLoot(super.rand.nextFloat() < this.worldObj.getLocationTensionFactor(this.posX, this.posY, this.posZ));
	}

	public void setCombatTask() {
		super.tasks.removeTask(this.aiAttackOnCollide);
		super.tasks.removeTask(this.aiArrowAttack);
		ItemStack itemstack = this.getHeldItem();
		if (itemstack != null && itemstack.itemID == AtumItems.bow.itemID) {
			super.tasks.addTask(4, this.aiArrowAttack);
		} else {
			super.tasks.addTask(4, this.aiAttackOnCollide);
		}

	}

	public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLiving, float par2) {
		EntityArrow entityarrow = new EntityArrow(super.worldObj, this, par1EntityLiving, 1.6F, (float) (14 - super.worldObj.difficultySetting * 4));
		int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
		int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
		entityarrow.setDamage((double) (par2 * 2.0F) + super.rand.nextGaussian() * 0.25D + (double) ((float) super.worldObj.difficultySetting * 0.11F));
		if (i > 0) {
			entityarrow.setDamage(entityarrow.getDamage() + (double) i * 0.5D + 0.5D);
		}

		if (j > 0) {
			entityarrow.setKnockbackStrength(j);
		}

		if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0) {
			entityarrow.setFire(100);
		}

		this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		super.worldObj.spawnEntityInWorld(entityarrow);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setCombatTask();
	}

	public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
		super.setCurrentItemOrArmor(par1, par2ItemStack);
		if (!super.worldObj.isRemote && par1 == 0) {
			this.setCombatTask();
		}

	}

	protected void dropFewItems(boolean par1, int par2) {
		int amount;
		if (super.rand.nextInt(20) == 0) {
			amount = (int) ((double) AtumItems.bow.getMaxDamage() - (double) super.rand.nextInt(AtumItems.bow.getMaxDamage()) * 0.5D + 20.0D);
			this.entityDropItem(new ItemStack(AtumConfig.bowID, 1, amount), 0.0F);
		}

		if (super.rand.nextInt(10) == 0) {
			amount = super.rand.nextInt(2) + 1;
			this.dropItem(Item.goldNugget.itemID, amount);
		}

		if (super.rand.nextInt(4) == 0) {
			amount = super.rand.nextInt(3) + 1;
			this.dropItem(Item.arrow.itemID, amount);
		}

	}

}
