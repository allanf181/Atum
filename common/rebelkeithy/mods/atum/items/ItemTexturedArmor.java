package rebelkeithy.mods.atum.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemTexturedArmor extends ItemArmor {

	private String textureFile;
	private int repairItemID = 0;
	private Icon[] armour;

	public ItemTexturedArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
	}

	public ItemTexturedArmor setRepairItem(int id) {
		this.repairItemID = id;
		return this;
	}

	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.itemID == this.repairItemID;
	}

	public ItemTexturedArmor setTextureFile(String filename) {
		this.textureFile = filename;
		return this;
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, int layer) {
		return "atum:textures/armor/" + this.textureFile + ".png";
	}

}
