package com.teammetallurgy.atum.entity.arrow;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CustomArrow extends Entity {

	public float arrowShake = 0;

	public CustomArrow(World par1World) {
		super(par1World);
	}

	public String getTexture() {
		return "minecraft:textures/item/arrows.png";
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
	}

	@Override
	protected void entityInit() {
	}

}
