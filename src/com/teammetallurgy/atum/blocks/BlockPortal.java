package com.teammetallurgy.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import com.teammetallurgy.atum.AtumIDS;
import com.teammetallurgy.atum.lib.tickhandler.ClientTickHandler;
import com.teammetallurgy.atum.world.AtumTeleporter;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPortal extends BlockBreakable {

	public BlockPortal(int par1) {
		super(par1, "atum:portal", Material.portal, true);
		this.setTickRandomly(true);
		this.setHardness(-1.0F);
		this.setUnlocalizedName("atum:portal");
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		super.updateTick(par1World, par2, par3, par4, par5Random);
		if(par1World.provider.isSurfaceWorld() && par5Random.nextInt(2000) < par1World.difficultySetting) {
			;
		}

	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		for(int x = -1; x < 2; x++) {
			for(int z = -1; z < 2; z++) {
				for(int y = -1; y < 1; y++) {
					if(par1World.getBlockId(par2 + x, par3 + y, par4 + z) != Block.sandStone.blockID && par1World.getBlockId(par2 + x, par3 + y, par4 + z) != this.blockID && par1World.getBlockId(par2 + x, par3 + y, par4 + z) != AtumBlocks.BLOCK_LARGEBRICK.blockID) {
						par1World.setBlockToAir(par2, par3, par4);
					}
				}
			}
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean tryToCreatePortal(World par1World, int x, int y, int z, Block block) {
		for(int x1 = -2; x1 < 3; x1++) {
			for(int z1 = -2; z1 < 3; z1++) {
				int id = par1World.getBlockId(x + x1, y, z + z1);
				if(id != block.blockID) {
					return false;
				}
			}
		}
		for(int x1 = -2; x1 < 3; x1++) {
			for(int z1 = -2; z1 < 3; z1++) {
				if(x1 + x == x + 2 || z1 + z == z + 2 || x1 + x == x - 2 || z1 + z == z - 2) {
					int id = par1World.getBlockId(x + x1, y + 1, z + z1);
					if(id != block.blockID) {
						return false;
					}
				}
			}
		}
		for(int y1 = 2; y1 < 4; y1++) {
			for(int x1 = -2; x1 < 3; x1++) {
				for(int z1 = -2; z1 < 3; z1++) {
					if((x1 + x == x + 2 && z1 + z == z + 2) || (x1 + x == x - 2 && z1 + z == z + 2) || (x1 + x == x + 2 && z1 + z == z - 2) || (x1 + x == x - 2 && z1 + z == z - 2)) {
						int id = par1World.getBlockId(x + x1, y + y1, z + z1);
						if(id != block.blockID) {
							return false;
						}
					}
				}
			}
		}
		for(int x1 = -1; x1 < 2; x1++) {
			for(int z1 = -1; z1 < 2; z1++) {
				par1World.setBlock(x + x1, y + 1, z + z1, AtumBlocks.BLOCK_PORTAL.blockID, 0, 2);
			}
		}
		return true;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if(par5Entity.ridingEntity == null && par5Entity.riddenByEntity == null && par5Entity instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) par5Entity;
			if(par5Entity.timeUntilPortal == 0 && par5Entity instanceof EntityPlayerMP) {
				par5Entity.timeUntilPortal = 100;
				MinecraftServer minecraftserver = MinecraftServer.getServer();
				int dimID = par5Entity.dimension;
				WorldServer worldserver = minecraftserver.worldServerForDimension(0);
				WorldServer worldserver1 = minecraftserver.worldServerForDimension(AtumIDS.DIMENSION_ID);
				if(dimID == AtumIDS.DIMENSION_ID) {
					minecraftserver.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) par5Entity, 0, new AtumTeleporter(worldserver));
					if(par1World.isRemote) {
						Minecraft.getMinecraft().gameSettings.renderDistance = ClientTickHandler.defaultFog;
					}
				} else {
					minecraftserver.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) par5Entity, AtumIDS.DIMENSION_ID, new AtumTeleporter(worldserver1));
				}

				try {
					EntityPlayerMP e = (EntityPlayerMP) player;
					ObfuscationReflectionHelper.setPrivateValue(EntityPlayerMP.class, e, Integer.valueOf(-1), new String[]{"lastExperience", "cp", "field_71144_ck"});
					ObfuscationReflectionHelper.setPrivateValue(EntityPlayerMP.class, e, Integer.valueOf(-1), new String[]{"lastHealth", "cm", "field_71149_ch"});
					ObfuscationReflectionHelper.setPrivateValue(EntityPlayerMP.class, e, Integer.valueOf(-1), new String[]{"lastFoodLevel", "cn", "field_71146_ci"});
				} catch(Exception var12) {
					var12.printStackTrace();
				}
			}
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(par5Random.nextInt(100) == 0) {
			par1World.playSound((double) par2 + 0.5D, (double) par3 + 0.5D, (double) par4 + 0.5D, "portal.portal", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F, false);
		}

		for(int l = 0; l < 8; ++l) {
			double d0 = (double) ((float) par2 + par5Random.nextFloat());
			double d1 = (double) ((float) par3 + par5Random.nextFloat());
			double d2 = (double) ((float) par4 + par5Random.nextFloat());
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = par5Random.nextInt(2) * 2 - 1;
			d3 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
			d4 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
			d5 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
			if(par5Random.nextDouble() > 0.5D) {
				d0 = (double) par2 + 0.5D + 0.25D * (double) i1;
				d3 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
			} else {
				d2 = (double) par4 + 0.5D + 0.25D * (double) i1;
				d5 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
			}

			par1World.spawnParticle("sandportal", d0, d1, d2, d3, d4, d5);
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return 0;
	}

}
