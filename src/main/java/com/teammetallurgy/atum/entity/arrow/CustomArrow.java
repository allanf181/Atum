package com.teammetallurgy.atum.entity.arrow;

import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CustomArrow extends EntityArrow {

    public float arrowShake = 0;

    public CustomArrow(World par1World) {
        super(par1World);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
    }

    @Override
    protected void entityInit() {
    }

    public String getTexture() {
        return "minecraft:arrow";
    }
}
