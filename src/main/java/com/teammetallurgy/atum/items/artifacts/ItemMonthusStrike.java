package com.teammetallurgy.atum.items.artifacts;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.teammetallurgy.atum.entity.EntityPharaoh;
import com.teammetallurgy.atum.entity.EntityStoneSoldier;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMonthusStrike extends ItemAxe {

	public ItemMonthusStrike(ToolMaterial par2ToolMaterial) {
		super(par2ToolMaterial);

	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass) {
		return true;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 7200;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5) {
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World world, EntityPlayer player, int par4) {
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
		if (j > 21) {
			AxisAlignedBB bb = player.boundingBox.copy();
			bb = bb.expand(3.0D, 3.0D, 3.0D);
			List list = world.getEntitiesWithinAABB(EntityLiving.class, bb);
			Iterator i = list.iterator();

			while (i.hasNext()) {
				Entity entity = (Entity) i.next();
				if (entity != player && !(entity instanceof EntityStoneSoldier) && !(entity instanceof EntityPharaoh)) {
					double dx = entity.posX - player.posX;
					double dz = entity.posZ - player.posZ;
					double magnitude = Math.sqrt(dx * dx + dz * dz);
					dx /= magnitude;
					dz /= magnitude;
					entity.isAirBorne = true;
					entity.addVelocity(dx * 2.5D, 0.3D, dz * 2.5D);
					if (entity.motionY > 0.4000000059604645D) {
						entity.motionY = 0.4000000059604645D;
					}

					// ((EntityLiving)
					// entity).attackEntityFrom(DamageSource.generic,
					// this.getDamageVsEntity(entity, par1ItemStack));
					if (world.isRemote) {
						this.spawnParticle(world, entity);
					}
				}
			}

			par1ItemStack.damageItem(4, player);
		}
	}

	@SideOnly(Side.CLIENT)
	public void spawnParticle(World world, Entity entity) {
		Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCrit2FX(world, entity));
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (Keyboard.isKeyDown(42)) {
			par3List.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal(this.getUnlocalizedName() + ".line1"));
			par3List.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal(this.getUnlocalizedName() + ".line2"));
		} else {
			par3List.add(StatCollector.translateToLocal(this.getUnlocalizedName() + ".line3") + " " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
		}
	}

	// @Override
	// public float getDamageVsEntity(Entity par1Entity, ItemStack stack) {
	// return 4 + super.toolMaterial.getDamageVsEntity();
	// }

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return par2ItemStack.getItem() == Items.diamond;
	}

	@Override
	public void registerIcons(IIconRegister par1IIconRegister) {
		this.itemIcon = par1IIconRegister.registerIcon("atum:MonthusStrike");
	}
}
