package rebelkeithy.mods.atum;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import rebelkeithy.mods.atum.AtumItems;

public class AtumTab extends CreativeTabs {

   public AtumTab(String label) {
      super(label);
   }

   public Item getTabIconItem() {
      return AtumItems.scarab;
   }
}
