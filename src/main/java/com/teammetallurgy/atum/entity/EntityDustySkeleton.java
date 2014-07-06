package com.teammetallurgy.atum.entity;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDustySkeleton extends EntityMob {

    boolean onFire = false;

    public EntityDustySkeleton(World par1World) {
        super(par1World);
        this.isImmuneToFire = true;
        this.experienceValue = 6;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        double speed = 0.53000000417232513D;
        if (this.onFire) {
            speed = 0.9D;
        }
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(speed);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(10.0D);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.isBurning()) {
            this.onFire = true;
            this.applyEntityAttributes();
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        return super.attackEntityFrom(par1DamageSource, par2);
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
    protected void dropFewItems(boolean par1, int par2) {
        switch (this.rand.nextInt(4)) {
            case 0:
                int amount = rand.nextInt(2) + 1;
                this.dropItem(Items.bone, amount);
                break;
        }
    }
}
