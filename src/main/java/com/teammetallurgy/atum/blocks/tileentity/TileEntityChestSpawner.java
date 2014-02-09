package com.teammetallurgy.atum.blocks.tileentity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityChestSpawner extends TileEntityChest implements IInventory {

	private final CursedChestSpawnerLogic chestSpawner = new CursedChestSpawnerLogic(this);
	private ItemStack[] chestContents = new ItemStack[36];
	public float f;
	public float g;
	public int h;
	private int ticksSinceSync;
	private int field_94046_i = -1;
	private String field_94045_s;

	public TileEntityChestSpawner() {
		int entityID = (int) (Math.random() * 6.0D);
		if(entityID == 0) {
			this.chestSpawner.setEntityName("AtumMummy");
		}

		if(entityID == 1) {
			this.chestSpawner.setEntityName("AtumBanditWarrior");
		}

		if(entityID == 2) {
			this.chestSpawner.setEntityName("AtumBanditArcher");
		}

		if(entityID == 3) {
			this.chestSpawner.setEntityName("AtumDustySkeleton");
		}

		if(entityID == 4) {
			this.chestSpawner.setEntityName("AtumDesertGhost");
		}

		if(entityID == 5) {
			this.chestSpawner.setEntityName("AtumStoneSoldier");
		}

		if(entityID == 6) {
			this.chestSpawner.setEntityName("AtumDesertWolf");
		}

		this.chestSpawner.minSpawnDelay = 0;
	}

	public void setSpawnerEntity(String name) {
		this.chestSpawner.setEntityName(name);
	}

	public void setMaxEntities(int max) {
		this.chestSpawner.spawnCount = max;
	}

	public void setDelay(int min, int max) {
		this.chestSpawner.minSpawnDelay = min;
		this.chestSpawner.maxSpawnDelay = max;
	}

	public void setRange(int range) {
		this.chestSpawner.spawnRange = range;
	}

	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int par1) {
		return this.chestContents[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if(this.chestContents[par1] != null) {
			ItemStack itemstack;
			if(this.chestContents[par1].stackSize <= par2) {
				itemstack = this.chestContents[par1];
				this.chestContents[par1] = null;
				this.onInventoryChanged();
				return itemstack;
			} else {
				itemstack = this.chestContents[par1].splitStack(par2);
				if(this.chestContents[par1].stackSize == 0) {
					this.chestContents[par1] = null;
				}

				this.onInventoryChanged();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if(this.chestContents[par1] != null) {
			ItemStack itemstack = this.chestContents[par1];
			this.chestContents[par1] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		this.chestContents[par1] = par2ItemStack;
		if(par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}

		this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return this.isInvNameLocalized() ? this.field_94045_s : "container.chest";
	}

	@Override
	public boolean isInvNameLocalized() {
		return this.field_94045_s != null && this.field_94045_s.length() > 0;
	}

	@Override
	public void setChestGuiName(String par1Str) {
		this.field_94045_s = par1Str;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		this.chestContents = new ItemStack[this.getSizeInventory()];
		if(par1NBTTagCompound.hasKey("CustomName")) {
			this.field_94045_s = par1NBTTagCompound.getString("CustomName");
		}

		for(int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;
			if(j >= 0 && j < this.chestContents.length) {
				this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.chestSpawner.readFromNBT(par1NBTTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = new NBTTagList();

		for(int i = 0; i < this.chestContents.length; ++i) {
			if(this.chestContents[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.chestContents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);
		if(this.isInvNameLocalized()) {
			par1NBTTagCompound.setString("CustomName", this.field_94045_s);
		}

		this.chestSpawner.writeToNBT(par1NBTTagCompound);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		double d0 = 4.0D;
		double d1 = 3.0D;
		List list = super.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getAABBPool().getAABB((double) super.xCoord - d0, (double) super.yCoord - d1, (double) super.zCoord - d0, (double) super.xCoord + d0, (double) super.yCoord + d1, (double) super.zCoord + d0));
		if(!list.isEmpty()) {
			if(!super.worldObj.isRemote) {
				par1EntityPlayer.addChatMessage("There are too many enemies nearby to search this chest");
			}

			return false;
		} else {
			return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double) super.xCoord + 0.5D, (double) super.yCoord + 0.5D, (double) super.zCoord + 0.5D) <= 64.0D;
		}
	}

	private boolean func_94044_a(int par1, int par2, int par3) {
		Block block = Block.blocksList[super.worldObj.getBlockId(par1, par2, par3)];
		return block != null && block instanceof BlockChest ? ((BlockChest) block).chestType == this.getChestType() : false;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		this.chestSpawner.updateSpawner();
		++this.ticksSinceSync;
		float f;
		if(!super.worldObj.isRemote && super.numUsingPlayers != 0 && (this.ticksSinceSync + super.xCoord + super.yCoord + super.zCoord) % 200 == 0) {
			super.numUsingPlayers = 0;
			f = 5.0F;
			List d0 = super.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB((double) ((float) super.xCoord - f), (double) ((float) super.yCoord - f), (double) ((float) super.zCoord - f), (double) ((float) (super.xCoord + 1) + f), (double) ((float) (super.yCoord + 1) + f), (double) ((float) (super.zCoord + 1) + f)));
			Iterator iterator = d0.iterator();

			while(iterator.hasNext()) {
				EntityPlayer f1 = (EntityPlayer) iterator.next();
				if(f1.openContainer instanceof ContainerChest) {
					IInventory f2 = ((ContainerChest) f1.openContainer).getLowerChestInventory();
					if(f2 == this || f2 instanceof InventoryLargeChest && ((InventoryLargeChest) f2).isPartOfLargeChest(this)) {
						++super.numUsingPlayers;
					}
				}
			}
		}

		super.prevLidAngle = super.lidAngle;
		f = 0.1F;
		double var8;
		if(super.numUsingPlayers > 0 && super.lidAngle == 0.0F) {
			double var9 = (double) super.xCoord + 0.5D;
			var8 = (double) super.zCoord + 0.5D;
			super.worldObj.playSoundEffect(var9, (double) super.yCoord + 0.5D, var8, "random.chestopen", 0.5F, super.worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}

		if(super.numUsingPlayers == 0 && super.lidAngle > 0.0F || super.numUsingPlayers > 0 && super.lidAngle < 1.0F) {
			float var10 = super.lidAngle;
			if(super.numUsingPlayers > 0) {
				super.lidAngle += f;
			} else {
				super.lidAngle -= f;
			}

			if(super.lidAngle > 1.0F) {
				super.lidAngle = 1.0F;
			}

			float var11 = 0.5F;
			if(super.lidAngle < var11 && var10 >= var11) {
				var8 = (double) super.xCoord + 0.5D;
				double d2 = (double) super.zCoord + 0.5D;
				super.worldObj.playSoundEffect(var8, (double) super.yCoord + 0.5D, d2, "random.chestclosed", 0.5F, super.worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}

			if(super.lidAngle < 0.0F) {
				super.lidAngle = 0.0F;
			}
		}

	}

	@Override
	public boolean receiveClientEvent(int par1, int par2) {
		if(par1 == 1) {
			super.numUsingPlayers = par2;
			return true;
		} else {
			return super.receiveClientEvent(par1, par2);
		}
	}

	@Override
	public void openChest() {
		if(super.numUsingPlayers < 0) {
			super.numUsingPlayers = 0;
		}

		++super.numUsingPlayers;
		super.worldObj.addBlockEvent(super.xCoord, super.yCoord, super.zCoord, this.getBlockType(), 1, super.numUsingPlayers);
		super.worldObj.notifyBlocksOfNeighborChange(super.xCoord, super.yCoord, super.zCoord, this.getBlockType());
		super.worldObj.notifyBlocksOfNeighborChange(super.xCoord, super.yCoord - 1, super.zCoord, this.getBlockType());
	}

	@Override
	public void closeChest() {
		if(this.getBlockType() != null && this.getBlockType() instanceof BlockChest) {
			--super.numUsingPlayers;
			super.worldObj.addBlockEvent(super.xCoord, super.yCoord, super.zCoord, this.getBlockType(), 1, super.numUsingPlayers);
			super.worldObj.notifyBlocksOfNeighborChange(super.xCoord, super.yCoord, super.zCoord, this.getBlockType());
			super.worldObj.notifyBlocksOfNeighborChange(super.xCoord, super.yCoord - 1, super.zCoord, this.getBlockType());
		}

	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	@Override
	public int getChestType() {
		if(this.field_94046_i == -1) {
			if(super.worldObj == null || !(this.getBlockType() instanceof BlockChest)) {
				return 0;
			}

			this.field_94046_i = ((BlockChest) this.getBlockType()).chestType;
		}

		return this.field_94046_i;
	}

}
