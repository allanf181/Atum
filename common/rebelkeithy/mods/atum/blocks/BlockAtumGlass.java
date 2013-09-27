package rebelkeithy.mods.atum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;

public class BlockAtumGlass extends BlockBreakable {

   public BlockAtumGlass(int id, String name, Material material, boolean bool) {
      super(id, name, material, bool);
   }

   public int quantityDropped(Random rand) {
      return 0;
   }

   @SideOnly(Side.CLIENT)
   public int getRenderBlockPass() {
      return 0;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   protected boolean canSilkHarvest() {
      return true;
   }
}
