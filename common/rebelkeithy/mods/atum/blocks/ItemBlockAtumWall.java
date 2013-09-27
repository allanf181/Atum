package rebelkeithy.mods.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBlockAtumWall extends ItemBlock {

   public ItemBlockAtumWall(int par1) {
      super(par1);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public int getMetadata(int meta) {
      return meta;
   }

   public Icon getIconFromDamage(int meta) {
      return Block.blocksList[super.itemID].getIcon(2, meta);
   }

   public String getUnlocalizedName(ItemStack itemstack) {
      switch(itemstack.getItemDamage()) {
      case 0:
         return "atum:AtumStoneWall";
      case 1:
         return "atum:AtumCobble";
      case 2:
         return "atum:AtumBrickLarge";
      case 3:
         return "atum:AtumBrickSmall";
      default:
         return "atum:Default";
      }
   }
}
