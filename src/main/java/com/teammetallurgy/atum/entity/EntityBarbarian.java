package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.items.AtumItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBarbarian extends EntityMob {

    public EntityBarbarian(World par1World) {
        super(par1World);
        this.experienceValue = 9;

        this.setCurrentItemOrArmor(0, new ItemStack(AtumItems.ITEM_GREATSWORD));
        this.enchantEquipment();

        for (int i = 0; i < this.equipmentDropChances.length; ++i) {
            this.equipmentDropChances[i] = 0F;
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.53000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(10.0D);
    }

    @Override
    protected void addRandomArmor() {
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        if (rand.nextInt(20) == 0) {
            int damage = (int) (AtumItems.ITEM_GREATSWORD.getMaxDamage() - rand.nextInt(AtumItems.ITEM_GREATSWORD.getMaxDamage()) * 0.5 + 20);
            this.entityDropItem(new ItemStack(AtumItems.ITEM_GREATSWORD, 1, damage), 0.0F);
        }

        if (rand.nextInt(4) == 0) {
            int amount = rand.nextInt(2) + 1;
            this.dropItem(Items.gold_nugget, amount);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (this.getEquipmentInSlot(0).getItem() == AtumItems.ITEM_GREATSWORD) {
            float f = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
            float i = 1.2F;

            if (entity instanceof EntityLivingBase) {
                f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) entity);
                i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase) entity);
            }

            boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);
            if (flag) {
                if (i > 0) {
                    entity.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * i * 0.5F), 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * i * 0.5F));
                    this.motionX *= 0.6D;
                    this.motionZ *= 0.6D;
                }

                int j = EnchantmentHelper.getFireAspectModifier(this);
                if (j > 0) {
                    entity.setFire(j * 4);
                }

                if (entity instanceof EntityLivingBase) {
                    EnchantmentHelper.func_151384_a((EntityLivingBase) entity, this);
                }
                EnchantmentHelper.func_151385_b(this, entity);
            }
            return flag;
        }
        return super.attackEntityAsMob(entity);
    }
}
