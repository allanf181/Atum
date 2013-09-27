package rebelkeithy.mods.atum.items;

import rebelkeithy.mods.atum.Atum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemAtumBow extends ItemBow {

	public static final String[] bowPullIconNameArray = new String[] { "bow_pull_0", "bow_pull_1", "bow_pull_2" };
	Icon[] iconArray;

	public ItemAtumBow(int par1) {
		super(par1);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.iconArray = new Icon[bowPullIconNameArray.length];

		this.itemIcon = par1IconRegister.registerIcon("atum:Bow");
		for (int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = par1IconRegister.registerIcon("atum:" + bowPullIconNameArray[i]);
		}

	}

	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		if (usingItem != null) {
			int j = this.getMaxItemUseDuration(stack) - useRemaining;
			if (j >= 18) {
				return this.getItemIconForUseDuration(2);
			}

			if (j > 13) {
				return this.getItemIconForUseDuration(1);
			}

			if (j > 0) {
				return this.getItemIconForUseDuration(0);
			}
		}

		return this.getIcon(stack, renderPass);
	}

	@SideOnly(Side.CLIENT)
	public Icon getItemIconForUseDuration(int par1) {
		return this.iconArray[par1];
	}

}
