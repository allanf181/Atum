package rebelkeithy.mods.atum.tools;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import rebelkeithy.mods.atum.AtumBlocks;

public class LimestoneShovel extends ItemSpade {

   public LimestoneShovel(int par1, EnumToolMaterial par2EnumToolMaterial) {
      super(par1, par2EnumToolMaterial);
   }

   public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      return par2ItemStack.itemID == AtumBlocks.cobble.blockID;
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.itemIcon = par1IconRegister.registerIcon("atum:LimestoneShovel");
   }
}
