package rebelkeithy.mods.atum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.AtumBlocks;

public class BlockSandLayered extends Block {

   public BlockSandLayered(int par1) {
      super(par1, Material.sand);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      this.setTickRandomly(true);
      this.func_96478_d(0);
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister par1IconRegister) {
      super.blockIcon = par1IconRegister.registerIcon("atum:AtumSand");
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
      return null;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public void setBlockBoundsForItemRender() {
      this.func_96478_d(0);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      this.func_96478_d(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
   }

   protected void func_96478_d(int par1) {
      int j = par1 & 7;
      float f = (float)(2 * (1 + j)) / 16.0F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
   }

   public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
      int l = par1World.getBlockId(par2, par3 - 1, par4);
      Block block = Block.blocksList[l];
      return block == null?false:(block.blockID == AtumBlocks.sandLayered.blockID?true:(block == this && (par1World.getBlockMetadata(par2, par3 - 1, par4) & 7) == 7?true:(!block.isLeaves(par1World, par2, par3 - 1, par4) && !Block.blocksList[l].isOpaqueCube()?false:par1World.getBlockMaterial(par2, par3 - 1, par4).blocksMovement())));
   }

   public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
      this.canSnowStay(par1World, par2, par3, par4);
   }

   private boolean canSnowStay(World par1World, int par2, int par3, int par4) {
      if(!this.canPlaceBlockAt(par1World, par2, par3, par4)) {
         par1World.setBlockToAir(par2, par3, par4);
         return false;
      } else {
         return false;
      }
   }

   public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
      super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
      par1World.setBlockToAir(par3, par4, par5);
   }

   public int idDropped(int par1, Random par2Random, int par3) {
      return 0;
   }

   public int quantityDropped(Random par1Random) {
      return 0;
   }

   public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
      if(par1World.getSavedLightValue(EnumSkyBlock.Block, par2, par3, par4) > 11) {
         par1World.setBlockToAir(par2, par3, par4);
      }

   }

   @SideOnly(Side.CLIENT)
   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
      return par5 == 1?true:super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
   }

   public int quantityDropped(int meta, int fortune, Random random) {
      return (meta & 7) + 1;
   }
}
