package com.teammetallurgy.atum.world;

import net.minecraft.util.ChunkCoordinates;

public class AtumPortalPosition implements Comparable {


    public int posX;
    /**
     * the y coordinate
     */
    public int posY;
    /**
     * the z coordinate
     */
    public int posZ;
    public long lastUpdateTime;

    public AtumPortalPosition(int par2, int par3, int par4, long updateTime) {
        this(par2, par3, par4);
        this.lastUpdateTime = updateTime;
    }


    private AtumPortalPosition(int x, int y, int z) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
    }

    private AtumPortalPosition(ChunkCoordinates chunkCoords) {
        this.posX = chunkCoords.posX;
        this.posY = chunkCoords.posY;
        this.posZ = chunkCoords.posZ;
    }

    public boolean equals(Object p_equals_1_) {
        if (!(p_equals_1_ instanceof ChunkCoordinates)) {
            return false;
        } else {
            ChunkCoordinates chunkcoordinates = (ChunkCoordinates) p_equals_1_;
            return this.posX == chunkcoordinates.posX && this.posY == chunkcoordinates.posY && this.posZ == chunkcoordinates.posZ;
        }
    }

    public int hashCode() {
        return this.posX + this.posZ << 8 + this.posY << 16;
    }

    public int compareTo(ChunkCoordinates p_compareTo_1_) {
        return this.posY == p_compareTo_1_.posY ? (this.posZ == p_compareTo_1_.posZ ? this.posX - p_compareTo_1_.posX : this.posZ - p_compareTo_1_.posZ) : this.posY - p_compareTo_1_.posY;
    }

    public void set(int x, int y, int z) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
    }

    /**
     * Returns the squared distance between this coordinates and the coordinates given as argument.
     */
    public float getDistanceSquared(int p_71569_1_, int p_71569_2_, int p_71569_3_) {
        float f = (float) (this.posX - p_71569_1_);
        float f1 = (float) (this.posY - p_71569_2_);
        float f2 = (float) (this.posZ - p_71569_3_);
        return f * f + f1 * f1 + f2 * f2;
    }

    /**
     * Return the squared distance between this coordinates and the ChunkCoordinates given as argument.
     */
    public float getDistanceSquaredToChunkCoordinates(ChunkCoordinates chunkCoords) {
        return this.getDistanceSquared(chunkCoords.posX, chunkCoords.posY, chunkCoords.posZ);
    }

    public String toString() {
        return "Pos{x=" + this.posX + ", y=" + this.posY + ", z=" + this.posZ + '}';
    }

    public int compareTo(Object p_compareTo_1_) {
        return this.compareTo((ChunkCoordinates) p_compareTo_1_);
    }

}
