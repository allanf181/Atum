package com.teammetallurgy.atum.blocks.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

import com.teammetallurgy.atum.blocks.BlockLimeStoneFurnace;

public class TileEntityLimestoneFurnace extends TileEntityFurnace {

	private ItemStack[] furnaceItemStacks = new ItemStack[3];

	@Override
	public void updateEntity() {
		boolean flag = this.furnaceBurnTime > 0;
		boolean flag1 = false;
		if(this.furnaceBurnTime > 0) {
			--this.furnaceBurnTime;
		}

		if(!super.worldObj.isRemote) {
			if(this.furnaceBurnTime == 0 && this.canSmelt()) {
				this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
				if(this.furnaceBurnTime > 0) {
					flag1 = true;
					if(this.furnaceItemStacks[1] != null) {
						--this.furnaceItemStacks[1].stackSize;
						if(this.furnaceItemStacks[1].stackSize == 0) {
							this.furnaceItemStacks[1] = this.furnaceItemStacks[1].getItem().getContainerItemStack(this.furnaceItemStacks[1]);
						}
					}
				}
			}

			if(this.isBurning() && this.canSmelt()) {
				++this.furnaceCookTime;
				if(this.furnaceCookTime == 200) {
					this.furnaceCookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			} else {
				this.furnaceCookTime = 0;
			}

			if(flag != this.furnaceBurnTime > 0) {
				flag1 = true;
				BlockLimeStoneFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, super.worldObj, super.xCoord, super.yCoord, super.zCoord);
			}
		}

		if(flag1) {
			this.onInventoryChanged();
		}

	}

	private boolean canSmelt() {
		if(this.furnaceItemStacks[0] == null) {
			return false;
		} else {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
			if(itemstack == null) {
				return false;
			} else if(this.furnaceItemStacks[2] == null) {
				return true;
			} else if(!this.furnaceItemStacks[2].isItemEqual(itemstack)) {
				return false;
			} else {
				int result = this.furnaceItemStacks[2].stackSize + itemstack.stackSize;
				return result <= this.getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
			}
		}
	}
}
