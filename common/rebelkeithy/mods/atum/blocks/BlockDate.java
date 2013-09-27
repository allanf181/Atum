package rebelkeithy.mods.atum.blocks;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.AtumBlocks;
import rebelkeithy.mods.atum.AtumItems;

public class BlockDate extends Block {

   public int renderID = RenderingRegistry.getNextAvailableRenderId();


   public BlockDate(int id, Material material) {
      super(id, material);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return this.renderID;
   }

   public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
      if(world.getBlockId(x, y + 1, z) != AtumBlocks.leaves.blockID && !world.isRemote) {
         EntityItem entityItem = new EntityItem(world, (double)x, (double)y, (double)z, new ItemStack(AtumItems.date.itemID, 0, this.quantityDropped(new Random())));
         entityItem.dropItem(AtumItems.date.itemID, this.quantityDropped(new Random()));
         world.setBlockToAir(x, y, z);
      }

   }

   public int idDropped(int par1, Random rand, int par3) {
      return AtumItems.date.itemID;
   }

   public int quantityDropped(Random rand) {
      return rand.nextInt(3) + 1;
   }

   @SideOnly(Side.CLIENT)
   public int idPicked(World par1World, int par2, int par3, int par4) {
      return AtumItems.date.itemID;
   }
}
