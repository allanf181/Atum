package com.teammetallurgy.atum.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySmallBone extends EntityBone {

    public EntitySmallBone(World world) {
        super(world);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntitySmallBone(World world, EntityLivingBase livingBase, double p_i1771_3_, double p_i1771_5_, double p_i1771_7_) {
        super(world, livingBase, p_i1771_3_, p_i1771_5_, p_i1771_7_);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntitySmallBone(World world, double p_i1772_2_, double p_i1772_4_, double p_i1772_6_, double p_i1772_8_, double p_i1772_10_, double p_i1772_12_) {
        super(world, p_i1772_2_, p_i1772_4_, p_i1772_6_, p_i1772_8_, p_i1772_10_, p_i1772_12_);
        this.setSize(0.3125F, 0.3125F);
    }

    @Override
    protected void onImpact(MovingObjectPosition position) {
        if (!this.worldObj.isRemote) {
            if (position.entityHit != null) {
                position.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 2.0F);
            } else {
                int i = position.blockX;
                int j = position.blockY;
                int k = position.blockZ;

                switch (position.sideHit) {
                    case 0:
                        --j;
                        break;
                    case 1:
                        ++j;
                        break;
                    case 2:
                        --k;
                        break;
                    case 3:
                        ++k;
                        break;
                    case 4:
                        --i;
                        break;
                    case 5:
                        ++i;
                }
            }
            this.setDead();
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float from) {
        return false;
    }
}