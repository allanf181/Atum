package rebelkeithy.mods.atum.blocks;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import rebelkeithy.mods.atum.AtumBlocks;
import rebelkeithy.mods.atum.AtumConfig;
import rebelkeithy.mods.atum.AtumTeleporter;
import rebelkeithy.mods.atum.TickHandler;
import rebelkeithy.mods.particleregistry.ParticleRegistry;

public class BlockAtumPortal extends BlockBreakable {

   public BlockAtumPortal(int par1) {
      super(par1, "atum:portal", Material.portal, false);
      this.setTickRandomly(true);
      this.setHardness(-1.0F);
   }

   public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
      super.updateTick(par1World, par2, par3, par4, par5Random);
      if(par1World.provider.isSurfaceWorld() && par5Random.nextInt(2000) < par1World.difficultySetting) {
         ;
      }

   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
      return null;
   }

   public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {}

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return true;
   }

   public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4) {
      par1World.getBlockId(par2, par3 - 1, par4);
      int id1 = par1World.getBlockId(par2 - 2, par3 + 0, par4);
      int id2 = par1World.getBlockId(par2 - 2, par3 + 1, par4);
      int id3 = par1World.getBlockId(par2, par3 + 0, par4 - 2);
      int id4 = par1World.getBlockId(par2, par3 + 1, par4 - 2);
      int id5 = par1World.getBlockId(par2, par3 + 0, par4 + 2);
      int id6 = par1World.getBlockId(par2, par3 + 1, par4 + 2);
      int id7 = par1World.getBlockId(par2 + 2, par3 + 0, par4);
      int id8 = par1World.getBlockId(par2 + 2, par3 + 1, par4);
      int id9 = par1World.getBlockId(par2 - 1, par3 + 2, par4);
      int id10 = par1World.getBlockId(par2, par3 + 2, par4 - 1);
      int id11 = par1World.getBlockId(par2, par3 + 2, par4 + 1);
      int id12 = par1World.getBlockId(par2 + 1, par3 + 2, par4);
      if(id1 == id2 && id2 == id3 && id3 == id4 && id4 == id5 && id6 == id7 && id7 == id8 && id8 == id9 && id9 == id10 && id10 == id11 && id11 == id12 && id12 == Block.sandStone.blockID) {
         par1World.setBlock(par2, par3, par4, AtumBlocks.portal.blockID, 0, 2);
         par1World.setBlock(par2, par3 + 1, par4, AtumBlocks.portal.blockID, 0, 2);
         par1World.setBlock(par2, par3 + 2, par4, AtumBlocks.portal.blockID, 0, 2);
         return true;
      } else {
         return false;
      }
   }

   public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
      byte b0 = 0;
      byte b1 = 1;
      if(par1World.getBlockId(par2 - 1, par3, par4) == super.blockID || par1World.getBlockId(par2 + 1, par3, par4) == super.blockID) {
         b0 = 1;
         b1 = 0;
      }

      int i1;
      for(i1 = par3; par1World.getBlockId(par2, i1 - 1, par4) == super.blockID; --i1) {
         ;
      }

      if(par1World.getBlockId(par2, i1 - 1, par4) != Block.sandStone.blockID) {
         par1World.setBlockToAir(par2, par3, par4);
      } else {
         int j1;
         for(j1 = 1; j1 < 4 && par1World.getBlockId(par2, i1 + j1, par4) == super.blockID; ++j1) {
            ;
         }

         if(j1 == 3 && par1World.getBlockId(par2, i1 + j1, par4) == Block.sandStone.blockID) {
            boolean flag = par1World.getBlockId(par2 - 1, par3, par4) == super.blockID || par1World.getBlockId(par2 + 1, par3, par4) == super.blockID;
            boolean flag1 = par1World.getBlockId(par2, par3, par4 - 1) == super.blockID || par1World.getBlockId(par2, par3, par4 + 1) == super.blockID;
            if(flag && flag1) {
               par1World.setBlockToAir(par2, par3, par4);
            } else if((par1World.getBlockId(par2 + b0, par3, par4 + b1) != Block.sandStone.blockID || par1World.getBlockId(par2 - b0, par3, par4 - b1) != super.blockID) && (par1World.getBlockId(par2 - b0, par3, par4 - b1) != Block.sandStone.blockID || par1World.getBlockId(par2 + b0, par3, par4 + b1) != super.blockID)) {
               par1World.setBlockToAir(par2, par3, par4);
            }
         } else {
            par1World.setBlockToAir(par2, par3, par4);
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
      if(par1IBlockAccess.getBlockId(par2, par3, par4) == super.blockID) {
         return false;
      } else {
         boolean flag = par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == super.blockID && par1IBlockAccess.getBlockId(par2 - 2, par3, par4) != super.blockID;
         boolean flag1 = par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == super.blockID && par1IBlockAccess.getBlockId(par2 + 2, par3, par4) != super.blockID;
         boolean flag2 = par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == super.blockID && par1IBlockAccess.getBlockId(par2, par3, par4 - 2) != super.blockID;
         boolean flag3 = par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == super.blockID && par1IBlockAccess.getBlockId(par2, par3, par4 + 2) != super.blockID;
         boolean flag4 = flag || flag1;
         boolean flag5 = flag2 || flag3;
         return flag4 && par5 == 4?true:(flag4 && par5 == 5?true:(flag5 && par5 == 2?true:flag5 && par5 == 3));
      }
   }

   public int quantityDropped(Random par1Random) {
      return 0;
   }

   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
      if(par5Entity.ridingEntity == null && par5Entity.riddenByEntity == null && par5Entity instanceof EntityPlayerMP) {
         EntityPlayerMP player = (EntityPlayerMP)par5Entity;
         if(par5Entity.timeUntilPortal == 0 && par5Entity instanceof EntityPlayerMP) {
            par5Entity.timeUntilPortal = 100;
            MinecraftServer minecraftserver = MinecraftServer.getServer();
            int dimID = par5Entity.dimension;
            WorldServer worldserver = minecraftserver.worldServerForDimension(0);
            WorldServer worldserver1 = minecraftserver.worldServerForDimension(AtumConfig.dimensionID);
            if(dimID == AtumConfig.dimensionID) {
               minecraftserver.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par5Entity, 0, new AtumTeleporter(worldserver));
               if(par1World.isRemote) {
                  Minecraft.getMinecraft().gameSettings.renderDistance = TickHandler.defaultFog;
               }
            } else {
               minecraftserver.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par5Entity, AtumConfig.dimensionID, new AtumTeleporter(worldserver1));
            }

            try {
               EntityPlayerMP e = (EntityPlayerMP)player;
               ObfuscationReflectionHelper.setPrivateValue(EntityPlayerMP.class, e, Integer.valueOf(-1), new String[]{"lastExperience", "cp", "field_71144_ck"});
               ObfuscationReflectionHelper.setPrivateValue(EntityPlayerMP.class, e, Integer.valueOf(-1), new String[]{"lastHealth", "cm", "field_71149_ch"});
               ObfuscationReflectionHelper.setPrivateValue(EntityPlayerMP.class, e, Integer.valueOf(-1), new String[]{"lastFoodLevel", "cn", "field_71146_ci"});
            } catch (Exception var12) {
               var12.printStackTrace();
            }
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public int getRenderBlockPass() {
      return 1;
   }

   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
      if(par5Random.nextInt(100) == 0) {
         par1World.playSound((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "portal.portal", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F, false);
      }

      for(int l = 0; l < 8; ++l) {
         double d0 = (double)((float)par2 + par5Random.nextFloat());
         double d1 = (double)((float)par3 + par5Random.nextFloat());
         double d2 = (double)((float)par4 + par5Random.nextFloat());
         double d3 = 0.0D;
         double d4 = 0.0D;
         double d5 = 0.0D;
         int i1 = par5Random.nextInt(2) * 2 - 1;
         d3 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
         d4 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
         d5 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
         if(par5Random.nextDouble() > 0.5D) {
            d0 = (double)par2 + 0.5D + 0.25D * (double)i1;
            d3 = (double)(par5Random.nextFloat() * 2.0F * (float)i1);
         } else {
            d2 = (double)par4 + 0.5D + 0.25D * (double)i1;
            d5 = (double)(par5Random.nextFloat() * 2.0F * (float)i1);
         }

         ParticleRegistry.spawnParticle("sandportal", par1World, d0, d1, d2, d3, d4, d5);
      }

   }

   @SideOnly(Side.CLIENT)
   public int idPicked(World par1World, int par2, int par3, int par4) {
      return 0;
   }
}
