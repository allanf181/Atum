package com.teammetallurgy.atum;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;

import com.teammetallurgy.atum.blocks.BlockFlax;
import com.teammetallurgy.atum.blocks.BlockPalmSapling;
import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.entity.EntityDustySkeleton;
import com.teammetallurgy.atum.entity.EntityGhost;
import com.teammetallurgy.atum.entity.EntityMummy;
import com.teammetallurgy.atum.entity.EntityPharaoh;
import com.teammetallurgy.atum.entity.EntityStoneSoldier;
import com.teammetallurgy.atum.items.AtumItems;

public class AtumEventListener {
	@ForgeSubscribe
	public void onFallDamage(LivingFallEvent event) {
		if(event.entity instanceof EntityGhost || event.entity instanceof EntityPharaoh) {
			event.distance = 0.0F;
		}

	}

	@ForgeSubscribe
	public void onLivingHurt(LivingHurtEvent event) {
		if(event.source.getDamageType().equals("drown") && (event.entity instanceof EntityPharaoh || event.entity instanceof EntityGhost || event.entity instanceof EntityMummy || event.entity instanceof EntityDustySkeleton || event.entity instanceof EntityStoneSoldier)) {
			event.setCanceled(true);
		}

	}

	@ForgeSubscribe
	public boolean onBonemeal(BonemealEvent event) {
		if(event.world.isRemote) {
			return true;
		} else {
			int id = event.world.getBlockId(event.X, event.Y, event.Z);
			if(id == AtumBlocks.BLOCK_PALMSAPLING.blockID) {
				((BlockPalmSapling) ((BlockPalmSapling) AtumBlocks.BLOCK_PALMSAPLING)).growTree(event.world, event.X, event.Y, event.Z, new Random());
				event.setResult(Result.ALLOW);
			}

			if(id == AtumBlocks.BLOCK_FLAX.blockID && event.world.getBlockMetadata(event.X, event.Y, event.Z) < 5) {
				((BlockFlax) ((BlockFlax) AtumBlocks.BLOCK_FLAX)).fertilize(event.world, event.X, event.Y, event.Z);
				event.setResult(Result.ALLOW);
			}

			return false;
		}
	}

	@ForgeSubscribe
	public boolean onHoeEvent(UseHoeEvent event) {
		int id = event.world.getBlockId(event.x, event.y, event.z);
		if(id == AtumBlocks.BLOCK_FERTILESOIL.blockID) {
			byte block2 = 0;
			if(event.current.itemID == AtumItems.gebsBlessing.itemID) {
				block2 = 4;
			}

			event.world.setBlock(event.x, event.y, event.z, AtumBlocks.BLOCK_FERTILESOILTILLED.blockID);
			event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, block2, 2);
			event.setResult(Result.ALLOW);
			Block block1 = Block.blocksList[id];
			event.world.playSoundEffect((double) event.x, (double) event.y, (double) event.z, block1.stepSound.getStepSound(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);
			return true;
		} else if((id == Block.dirt.blockID || id == Block.grass.blockID) && event.current.itemID == AtumItems.gebsBlessing.itemID) {
			event.world.setBlock(event.x, event.y, event.z, AtumBlocks.BLOCK_FERTILESOILTILLED.blockID);
			event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 12, 2);
			event.setResult(Result.ALLOW);
			Block block = Block.blocksList[id];
			event.world.playSoundEffect((double) event.x, (double) event.y, (double) event.z, block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
			return true;
		} else {
			return false;
		}
	}
}