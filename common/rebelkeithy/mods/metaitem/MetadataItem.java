package rebelkeithy.mods.metaitem;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MetadataItem extends Item {
	private List varNames;
	private List varLengths;
	int totalOffset;

	public MetadataItem(int par1) {
		super(par1);
		this.varNames = new ArrayList();
		this.varLengths = new ArrayList();
		this.totalOffset = 0;
	}

	public void addDamageVariable(String name, int bitlength) {
		int prevMaxDamage = getMaxDamage();

		setMaxDamage(getMaxDamage() << bitlength);
		this.totalOffset += bitlength;

		this.varNames.add(name);
		this.varLengths.add(Integer.valueOf(bitlength));

		if (prevMaxDamage < getMaxDamage()) {
			throw new OutOffDamageValueBitsException("Variable " + name + " of length " + bitlength + " caused max damage value to shift off the left");
		}
	}

	public int getVar(int damage, String name) {
		return getDamageVariable(damage, name);
	}

	public int getVar(ItemStack stack, String name) {
		return getDamageVariable(stack, name);
	}

	public int getDamageVariable(ItemStack stack, String name) {
		int damage = stack.getItemDamage();

		return getDamageVariable(damage, name);
	}

	public int getDamageVariable(int damage, String name) {
		if (!this.varNames.contains(name)) {
			throw new DamageVariableDoesNotExistException("Items does not contain damage variable: " + name);
		}
		for (int i = 0; i < this.varNames.size(); i++) {
			int length = ((Integer) this.varLengths.get(i)).intValue();
			if (((String) this.varNames.get(i)).equals(name)) {
				int mask = 2147483647 << length ^ 0xFFFFFFFF;
				damage &= mask;
				break;
			}
			damage >>= length;
		}

		return damage;
	}

	public Item setMaxDamage(int par1) {
		long shiftedDamageLong = par1 << this.totalOffset;
		int shiftedDamage = (int) shiftedDamageLong;
		if (shiftedDamage != shiftedDamageLong)
			throw new OutOffDamageValueBitsException("Setting damage to " + par1 + " caused an integer overflow. Damage variables use " + this.totalOffset + " bits");
		return super.setMaxDamage(shiftedDamage);
	}

	public int getMaxDamage() {
		return super.getMaxDamage() >> this.totalOffset;
	}

	public void damageItem(ItemStack stack, int amount, EntityLiving entity) {
		stack.damageItem(amount << this.totalOffset, entity);
	}

	public void getSubItems(String string, int amount, int par1, CreativeTabs par2CreativeTabs, List par3List) {
		int offset = 0;
		for (int i = 0; i < this.varNames.size(); i++) {
			if (((String) this.varNames.get(i)).equals(string)) {
				break;
			}
			offset += ((Integer) this.varLengths.get(i)).intValue();
		}

		for (int i = 0; i < amount; i++) {
			par3List.add(new ItemStack(par1, 1, i << offset));
		}
	}

	public class DamageVariableDoesNotExistException extends RuntimeException {
		public DamageVariableDoesNotExistException(String message) {
			super();
		}
	}

	public class OutOffDamageValueBitsException extends RuntimeException {
		public OutOffDamageValueBitsException(String message) {
			super();
		}
	}
}