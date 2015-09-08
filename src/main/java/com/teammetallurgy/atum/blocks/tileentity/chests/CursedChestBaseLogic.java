package com.teammetallurgy.atum.blocks.tileentity.chests;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.*;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CursedChestBaseLogic extends MobSpawnerBaseLogic {
    /**
     * The delay to spawn.
     */
    public int spawnDelay = 20;
    public double field_98287_c;
    public double field_98284_d;
    public int minSpawnDelay = 200;
    public int maxSpawnDelay = 800;
    /**
     * A counter for spawn tries.
     */
    public int spawnCount = 4;
    /**
     * The range coefficient for spawning entities around.
     */
    public int spawnRange = 4;
    private String entityTypeName = "Pig";
    /**
     * List of minecart to spawn.
     */
    private List minecartToSpawn;
    private WeightedRandomMinecart randomMinecart;
    private Entity field_98291_j;
    private int maxNearbyEntities = 6;
    /**
     * The distance from which a player activates the spawner.
     */
    private int activatingRangeFromPlayer = 16;

    /**
     * Gets the entity name that should be spawned.
     */
    @Override
    public String getEntityNameToSpawn() {
        if (this.getRandomEntity() == null) {
            if (this.entityTypeName.equals("Minecart")) {
                this.entityTypeName = "MinecartRideable";
            }

            return this.entityTypeName;
        } else {
            return this.getRandomEntity().entityTypeName;
        }
    }

    @Override
    public void setEntityName(String par1Str) {
        this.entityTypeName = par1Str;
    }

    @Override
    public boolean isActivated() {
        return this.getSpawnerWorld().getClosestPlayer((double) this.getSpawnerX() + 0.5D, (double) this.getSpawnerY() + 0.5D, (double) this.getSpawnerZ() + 0.5D, (double) this.activatingRangeFromPlayer) != null;
    }

    @Override
    public void updateSpawner() {
        if (this.isActivated()) {
            double d0;

            if (this.getSpawnerWorld().isRemote) {
                double d1 = (double) ((float) this.getSpawnerX() + this.getSpawnerWorld().rand.nextFloat());
                double d2 = (double) ((float) this.getSpawnerY() + this.getSpawnerWorld().rand.nextFloat());
                d0 = (double) ((float) this.getSpawnerZ() + this.getSpawnerWorld().rand.nextFloat());
                this.getSpawnerWorld().spawnParticle("smoke", d1, d2, d0, 0.0D, 0.0D, 0.0D);
                this.getSpawnerWorld().spawnParticle("flame", d1, d2, d0, 0.0D, 0.0D, 0.0D);

                if (this.spawnDelay > 0) {
                    --this.spawnDelay;
                }

                this.field_98284_d = this.field_98287_c;
                this.field_98287_c = (this.field_98287_c + (double) (1000.0F / ((float) this.spawnDelay + 200.0F))) % 360.0D;
            } else {
                if (this.spawnDelay == -1) {
                    this.func_98273_j();
                }

                if (this.spawnDelay > 0) {
                    --this.spawnDelay;
                    return;
                }

                boolean flag = false;

                for (int i = 0; i < this.spawnCount; ++i) {
                    if (this.getSpawnerWorld().difficultySetting.getDifficultyId() == 0) {
                        return;
                    }

                    Entity entity = EntityList.createEntityByName(this.getEntityNameToSpawn(), this.getSpawnerWorld());

                    if (entity == null) {
                        return;
                    }

                    int j = this.getSpawnerWorld().getEntitiesWithinAABB(entity.getClass(), AxisAlignedBB.getBoundingBox((double) this.getSpawnerX(), (double) this.getSpawnerY(), (double) this.getSpawnerZ(), (double) (this.getSpawnerX() + 1), (double) (this.getSpawnerY() + 1), (double) (this.getSpawnerZ() + 1)).expand((double) (this.spawnRange * 2), 4.0D, (double) (this.spawnRange * 2))).size();

                    if (j >= this.maxNearbyEntities) {
                        this.func_98273_j();
                        return;
                    }

                    d0 = (double) this.getSpawnerX() + (this.getSpawnerWorld().rand.nextDouble() - this.getSpawnerWorld().rand.nextDouble()) * (double) this.spawnRange;
                    double d3 = (double) (this.getSpawnerY() + this.getSpawnerWorld().rand.nextInt(3) - 1);
                    double d4 = (double) this.getSpawnerZ() + (this.getSpawnerWorld().rand.nextDouble() - this.getSpawnerWorld().rand.nextDouble()) * (double) this.spawnRange;
                    EntityLiving entityliving = entity instanceof EntityLiving ? (EntityLiving) entity : null;
                    entity.setLocationAndAngles(d0, d3, d4, this.getSpawnerWorld().rand.nextFloat() * 360.0F, 0.0F);

                    if (entityliving == null || entityliving.getCanSpawnHere()) {
                        this.func_98265_a(entity);
                        this.getSpawnerWorld().playAuxSFX(2004, this.getSpawnerX(), this.getSpawnerY(), this.getSpawnerZ(), 0);

                        if (entityliving != null) {
                            entityliving.spawnExplosionParticle();
                        }

                        flag = true;
                    }
                }

                if (flag) {
                    this.func_98273_j();
                }
            }
        }
    }

    @Override
    public Entity func_98265_a(Entity par1Entity) {
        if (this.getRandomEntity() != null) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            par1Entity.writeToNBTOptional(nbttagcompound);
            Iterator iterator = this.getRandomEntity().field_98222_b.func_150296_c().iterator();

            while (iterator.hasNext()) {
                NBTBase nbtbase = (NBTBase) iterator.next();
                nbttagcompound.setTag(nbtbase.toString(), nbtbase.copy());
            }

            par1Entity.readFromNBT(nbttagcompound);

            if (par1Entity.worldObj != null) {
                par1Entity.worldObj.spawnEntityInWorld(par1Entity);
            }

            NBTTagCompound nbttagcompound1;

            for (Entity entity1 = par1Entity; nbttagcompound.hasKey("Riding"); nbttagcompound = nbttagcompound1) {
                nbttagcompound1 = nbttagcompound.getCompoundTag("Riding");
                Entity entity2 = EntityList.createEntityByName(nbttagcompound1.getString("id"), par1Entity.worldObj);

                if (entity2 != null) {
                    NBTTagCompound nbttagcompound2 = new NBTTagCompound();
                    entity2.writeToNBTOptional(nbttagcompound2);
                    Iterator iterator1 = nbttagcompound1.func_150296_c().iterator();

                    while (iterator1.hasNext()) {
                        NBTBase nbtbase1 = (NBTBase) iterator1.next();
                        nbttagcompound2.setTag(nbtbase1.toString(), nbtbase1.copy());
                    }

                    entity2.readFromNBT(nbttagcompound2);
                    entity2.setLocationAndAngles(entity1.posX, entity1.posY, entity1.posZ, entity1.rotationYaw, entity1.rotationPitch);

                    if (par1Entity.worldObj != null) {
                        par1Entity.worldObj.spawnEntityInWorld(entity2);
                    }

                    entity1.mountEntity(entity2);
                }

                entity1 = entity2;
            }
        } else if (par1Entity instanceof EntityLivingBase && par1Entity.worldObj != null) {
            ((EntityLiving) par1Entity).onSpawnWithEgg((IEntityLivingData) null);
            this.getSpawnerWorld().spawnEntityInWorld(par1Entity);
        }

        return par1Entity;
    }

    private void func_98273_j() {
        if (this.maxSpawnDelay <= this.minSpawnDelay) {
            this.spawnDelay = this.minSpawnDelay;
        } else {
            int i = this.maxSpawnDelay - this.minSpawnDelay;
            this.spawnDelay = this.minSpawnDelay + this.getSpawnerWorld().rand.nextInt(i);
        }

        if (this.minecartToSpawn != null && this.minecartToSpawn.size() > 0) {
            this.setRandomEntity((WeightedRandomMinecart) WeightedRandom.getRandomItem(this.getSpawnerWorld().rand, this.minecartToSpawn));
        }

        this.func_98267_a(1);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        this.entityTypeName = par1NBTTagCompound.getString("EntityId");
        this.spawnDelay = par1NBTTagCompound.getShort("Delay");

        if (par1NBTTagCompound.hasKey("SpawnPotentials")) {
            this.minecartToSpawn = new ArrayList();
            NBTTagList nbttaglist = par1NBTTagCompound.getTagList("SpawnPotentials", 10);

            for (int i = 0; i < nbttaglist.tagCount(); ++i) {
                this.minecartToSpawn.add(new WeightedRandomMinecart((NBTTagCompound) nbttaglist.getCompoundTagAt(i)));
            }
        } else {
            this.minecartToSpawn = null;
        }

        if (par1NBTTagCompound.hasKey("SpawnData")) {
            this.setRandomEntity(new WeightedRandomMinecart(par1NBTTagCompound.getCompoundTag("SpawnData"), this.entityTypeName));
        } else {
            this.setRandomEntity((WeightedRandomMinecart) null);
        }

        if (par1NBTTagCompound.hasKey("MinSpawnDelay")) {
            this.minSpawnDelay = par1NBTTagCompound.getShort("MinSpawnDelay");
            this.maxSpawnDelay = par1NBTTagCompound.getShort("MaxSpawnDelay");
            this.spawnCount = par1NBTTagCompound.getShort("SpawnCount");
        }

        if (par1NBTTagCompound.hasKey("MaxNearbyEntities")) {
            this.maxNearbyEntities = par1NBTTagCompound.getShort("MaxNearbyEntities");
            this.activatingRangeFromPlayer = par1NBTTagCompound.getShort("RequiredPlayerRange");
        }

        if (par1NBTTagCompound.hasKey("SpawnRange")) {
            this.spawnRange = par1NBTTagCompound.getShort("SpawnRange");
        }

        if (this.getSpawnerWorld() != null && this.getSpawnerWorld().isRemote) {
            this.field_98291_j = null;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setString("EntityId", this.getEntityNameToSpawn());
        par1NBTTagCompound.setShort("Delay", (short) this.spawnDelay);
        par1NBTTagCompound.setShort("MinSpawnDelay", (short) this.minSpawnDelay);
        par1NBTTagCompound.setShort("MaxSpawnDelay", (short) this.maxSpawnDelay);
        par1NBTTagCompound.setShort("SpawnCount", (short) this.spawnCount);
        par1NBTTagCompound.setShort("MaxNearbyEntities", (short) this.maxNearbyEntities);
        par1NBTTagCompound.setShort("RequiredPlayerRange", (short) this.activatingRangeFromPlayer);
        par1NBTTagCompound.setShort("SpawnRange", (short) this.spawnRange);

        if (this.getRandomEntity() != null) {
            par1NBTTagCompound.setTag("SpawnData", (NBTTagCompound) this.getRandomEntity().field_98222_b.copy());
        }

        if (this.getRandomEntity() != null || this.minecartToSpawn != null && this.minecartToSpawn.size() > 0) {
            NBTTagList nbttaglist = new NBTTagList();

            if (this.minecartToSpawn != null && this.minecartToSpawn.size() > 0) {
                Iterator iterator = this.minecartToSpawn.iterator();

                while (iterator.hasNext()) {
                    WeightedRandomMinecart weightedrandomminecart = (WeightedRandomMinecart) iterator.next();
                    nbttaglist.appendTag(weightedrandomminecart.func_98220_a());
                }
            } else {
                nbttaglist.appendTag(this.getRandomEntity().func_98220_a());
            }

            par1NBTTagCompound.setTag("SpawnPotentials", nbttaglist);
        }
    }

    /**
     * Sets the delay to minDelay if parameter given is 1, else return false.
     */
    @Override
    public boolean setDelayToMin(int par1) {
        if (par1 == 1 && this.getSpawnerWorld().isRemote) {
            this.spawnDelay = this.minSpawnDelay;
            return true;
        } else {
            return false;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Entity func_98281_h() {
        if (this.field_98291_j == null) {
            Entity entity = EntityList.createEntityByName(this.getEntityNameToSpawn(), (World) null);
            entity = this.func_98265_a(entity);
            this.field_98291_j = entity;
        }

        return this.field_98291_j;
    }

    @Override
    public WeightedRandomMinecart getRandomEntity() {
        return this.randomMinecart;
    }

    @Override
    public void setRandomEntity(WeightedRandomMinecart par1WeightedRandomMinecart) {
        this.randomMinecart = par1WeightedRandomMinecart;
    }

    @Override
    public abstract void func_98267_a(int i);

    public abstract World getSpawnerWorld();

    public abstract int getSpawnerX();

    public abstract int getSpawnerY();

    public abstract int getSpawnerZ();
}
