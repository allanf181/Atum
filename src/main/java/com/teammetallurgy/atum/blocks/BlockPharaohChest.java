package com.teammetallurgy.atum.blocks;

import com.teammetallurgy.atum.AtumLoot;
import com.teammetallurgy.atum.blocks.tileentity.chests.TileEntityPharaohChest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Iterator;
import java.util.Random;

public class BlockPharaohChest extends BlockContainer {

    protected BlockPharaohChest() {
        super(Material.wood);
        this.setBlockName("pharaohChest");
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        this.setHardness(4.0F);
    }

    public static boolean isOcelotBlockingChest(World par0World, int par1, int par2, int par3) {
        Iterator iterator = par0World.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox((double) par1, (double) (par2 + 1), (double) par3, (double) (par1 + 1), (double) (par2 + 2), (double) (par3 + 1))).iterator();

        while (iterator.hasNext()) {
            EntityOcelot entityocelot1 = (EntityOcelot) iterator.next();
            if (entityocelot1.isSitting()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(Blocks.chest);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 22;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }

    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        Block l = par1World.getBlock(par2, par3, par4 - 1);
        Block i1 = par1World.getBlock(par2, par3, par4 + 1);
        Block j1 = par1World.getBlock(par2 - 1, par3, par4);
        Block k1 = par1World.getBlock(par2 + 1, par3, par4);
        byte b0 = 0;
        int l1 = MathHelper.floor_double((double) (par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if (l1 == 0) {
            b0 = 2;
        }

        if (l1 == 1) {
            b0 = 5;
        }

        if (l1 == 2) {
            b0 = 3;
        }

        if (l1 == 3) {
            b0 = 4;
        }

        if (l != this && i1 != this && j1 != this && k1 != this) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
        } else {
            if ((l == this || i1 == this) && (b0 == 4 || b0 == 5)) {
                if (l == this) {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4 - 1, b0, 3);
                } else {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4 + 1, b0, 3);
                }

                par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
            }

            if ((j1 == this || k1 == this) && (b0 == 2 || b0 == 3)) {
                if (j1 == this) {
                    par1World.setBlockMetadataWithNotify(par2 - 1, par3, par4, b0, 3);
                } else {
                    par1World.setBlockMetadataWithNotify(par2 + 1, par3, par4, b0, 3);
                }

                par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
            }
        }

        if (par6ItemStack.hasDisplayName()) {
            ((TileEntityPharaohChest) par1World.getTileEntity(par2, par3, par4)).func_94043_a(par6ItemStack.getDisplayName());
        }

        AtumLoot.fillChest((TileEntityPharaohChest) par1World.getTileEntity(par2, par3, par4), 15, 0.9F);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
        TileEntityPharaohChest tileEntityPharaohChest = (TileEntityPharaohChest) world.getTileEntity(x, y, z);
        if (tileEntityPharaohChest != null) {
            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, p_149749_6_);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity te = par1World.getTileEntity(par2, par3, par4);
        if (te instanceof TileEntityPharaohChest) {
            TileEntityPharaohChest iinventory = (TileEntityPharaohChest) te;
            if (!iinventory.hasSpawned()) {
                iinventory.spawn(par5EntityPlayer);
            }
        }

        if (par1World.isRemote) {
            return true;
        } else {
            IInventory iinventory1 = this.getInventory(par1World, par2, par3, par4);
            if (iinventory1 != null) {
                par5EntityPlayer.displayGUIChest(iinventory1);
            }

            return true;
        }
    }

    public IInventory getInventory(World par1World, int par2, int par3, int par4) {
        TileEntityPharaohChest object = (TileEntityPharaohChest) par1World.getTileEntity(par2, par3, par4);
        return object == null ? null : (par1World.isSideSolid(par2, par3 + 1, par4, ForgeDirection.DOWN) ? null : (isOcelotBlockingChest(par1World, par2, par3, par4) ? null : (IInventory) object));
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        if (!this.canProvidePower()) {
            return 0;
        } else {
            int i1 = ((TileEntityPharaohChest) par1IBlockAccess.getTileEntity(par2, par3, par4)).numPlayersUsing;
            return MathHelper.clamp_int(i1, 0, 15);
        }
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return par5 == 1 ? this.isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5) : 0;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
        return Container.calcRedstoneFromInventory(this.getInventory(par1World, par2, par3, par4));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IIconRegister) {
        this.blockIcon = par1IIconRegister.registerIcon("log_oak");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityPharaohChest();
    }
}
