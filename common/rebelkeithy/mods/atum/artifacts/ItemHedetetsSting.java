package rebelkeithy.mods.atum.artifacts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.Atum;

public class ItemHedetetsSting extends ItemSword {

   public ItemHedetetsSting(int par1, EnumToolMaterial par2EnumToolMaterial) {
      super(par1, par2EnumToolMaterial);
      
   }

   public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
      if(super.hitEntity(par1ItemStack, par2EntityLiving, par3EntityLiving)) {
         par2EntityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, 140, 2, false));
         return true;
      } else {
         return false;
      }
   }

   @SideOnly(Side.CLIENT)
   public EnumRarity getRarity(ItemStack par1ItemStack) {
      return EnumRarity.rare;
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
      if(Keyboard.isKeyDown(42)) {
         par3List.add(EnumChatFormatting.DARK_PURPLE + "Poison II: Chance");
         par3List.add(EnumChatFormatting.DARK_PURPLE + "to poison foes");
      } else {
         par3List.add("Poison II " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
      }

   }

   public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      return par2ItemStack.itemID == Item.diamond.itemID;
   }
   
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(Atum.modID + ":HedetetsSting");
	}
}
