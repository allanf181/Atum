package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.items.AtumItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMummy extends EntityMob {

    public EntityMummy(World par1World) {
        super(par1World);
        this.experienceValue = 8;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.53000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(10.0D);
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
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.isFireDamage()) {
            par2 += 1;
        }
        if (this.isBurning()) {
            par2 = (int) (par2 * 1.5);
        }

        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        boolean flag = super.attackEntityAsMob(par1Entity);

        if(flag){
        	if (this.isBurning() && this.rand.nextFloat() < (float) this.worldObj.difficultySetting.getDifficultyId() * 0.4F) {
        		par1Entity.setFire(2 * this.worldObj.difficultySetting.getDifficultyId());
        	}
        	if(par1Entity instanceof EntityLivingBase){
        		EntityLivingBase base = (EntityLivingBase)par1Entity;
        		base.addPotionEffect(new PotionEffect(Potion.wither.id, 40, 1));
        	}
        }

        return flag;
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
        if (rand.nextInt(4) == 0) {
            this.dropItem(Items.rotten_flesh, 1);
        }
        if (rand.nextInt(4) == 0) {
            int amount = rand.nextInt(2) + 1;
            this.dropItem(AtumItems.ITEM_SCRAP, amount);
        }
    }
}
