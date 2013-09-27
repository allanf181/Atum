package rebelkeithy.mods.atum.world.decorators;

import java.util.Random;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import rebelkeithy.mods.atum.AtumBlocks;
import rebelkeithy.mods.atum.AtumLoot;

public class WorldGenRuins extends WorldGenerator {

   public boolean generate(World world, Random random, int i, int j, int k) {
      int width = random.nextInt(4) + 6;
      int depth = random.nextInt(2) + 5;
      int height = world.getHeightValue(i, k);
      int x2;
      int z2;
      if(world.getHeightValue(i + width, k + depth) >= height) {
         x2 = i + width;
         z2 = k + depth;
      } else if(world.getHeightValue(i - width, k + depth) >= height) {
         x2 = i - width;
         z2 = k + depth;
      } else if(world.getHeightValue(i + width, k - depth) >= height) {
         x2 = i + width;
         z2 = k - depth;
      } else {
         if(world.getHeightValue(i - width, k - depth) < height) {
            return false;
         }

         x2 = i - width;
         z2 = k - depth;
      }

      int chestX;
      int chestZ;
      int chestY;
      for(chestX = Math.min(x2, i); chestX <= Math.max(x2, i); ++chestX) {
         for(chestZ = Math.min(z2, k); chestZ <= Math.max(z2, k); ++chestZ) {
            int meta = random.nextInt(4);

            for(chestY = -1; chestY < 15; ++chestY) {
               if(chestX != x2 && chestZ != z2 && chestX != i && chestZ != k && chestY != -1) {
                  world.setBlockToAir(chestX, chestY + height, chestZ);
               } else if(chestY < meta) {
                  if((double)random.nextFloat() > 0.1D) {
                     world.setBlock(chestX, chestY + height, chestZ, AtumBlocks.largeBrick.blockID);
                  } else {
                     world.setBlock(chestX, chestY + height, chestZ, AtumBlocks.smallBrick.blockID);
                  }
               } else if(chestY == meta && (double)random.nextFloat() > 0.7D) {
                  if((double)random.nextFloat() > 0.1D) {
                     world.setBlock(chestX, chestY + height, chestZ, AtumBlocks.slabs.blockID, 2, 0);
                  } else {
                     world.setBlock(chestX, chestY + height, chestZ, AtumBlocks.slabs.blockID, 3, 0);
                  }
               }
            }
         }
      }

      chestX = width / 2 + i;
      chestZ = Math.max(z2, k) - 1;
      boolean var16 = false;
      if((double)random.nextFloat() > 0.5D) {
         chestX = random.nextInt(width - 1) + 1 + Math.min(i, x2);
         if((double)random.nextFloat() > 0.5D) {
            chestZ = Math.max(z2, k) - 1;
            var16 = true;
         } else {
            chestZ = Math.min(z2, k) + 1;
            var16 = true;
         }
      } else {
         chestZ = random.nextInt(depth - 1) + 1 + Math.min(k, z2);
         if((double)random.nextFloat() > 0.5D) {
            chestX = Math.max(x2, i) - 1;
            var16 = true;
         } else {
            chestX = Math.min(x2, i) + 1;
            var16 = true;
         }
      }

      chestY = world.getHeightValue(chestX, chestZ);
      world.setBlock(chestX, chestY, chestZ, AtumBlocks.cursedChest.blockID, 0, 2);
      IInventory chest = (IInventory)world.getBlockTileEntity(chestX, chestY, chestZ);
      AtumLoot.fillChest(chest, 5, 0.5F);
      return false;
   }
}
