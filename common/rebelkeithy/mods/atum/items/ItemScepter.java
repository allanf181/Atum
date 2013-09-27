package rebelkeithy.mods.atum.items;

import rebelkeithy.mods.atum.Atum;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

public class ItemScepter extends ItemSword {

	public ItemScepter(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(Atum.modID + ":Scepter");
	}
}
