package com.teammetallurgy.atum.lib.proxy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderFish;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.teammetallurgy.atum.blocks.BlockDate;
import com.teammetallurgy.atum.blocks.BlockPapyrus;
import com.teammetallurgy.atum.blocks.Blocks;
import com.teammetallurgy.atum.client.model.entity.ModelDesertWolf;
import com.teammetallurgy.atum.client.model.entity.ModelDustySkeleton;
import com.teammetallurgy.atum.client.render.entity.RenderBandit;
import com.teammetallurgy.atum.client.render.entity.RenderDesertWolf;
import com.teammetallurgy.atum.client.render.entity.RenderFireSpear;
import com.teammetallurgy.atum.client.render.entity.RenderGhost;
import com.teammetallurgy.atum.client.render.entity.RenderPharaoh;
import com.teammetallurgy.atum.client.render.entity.arrow.RenderCustomArrow;
import com.teammetallurgy.atum.client.render.entity.arrow.RenderNutsCall;
import com.teammetallurgy.atum.client.render.item.RendererItemBow;
import com.teammetallurgy.atum.client.render.tileentity.RenderDate;
import com.teammetallurgy.atum.client.render.tileentity.RenderPapyrus;
import com.teammetallurgy.atum.entity.EntityBanditArcher;
import com.teammetallurgy.atum.entity.EntityBanditWarlord;
import com.teammetallurgy.atum.entity.EntityBanditWarrior;
import com.teammetallurgy.atum.entity.EntityBarbarian;
import com.teammetallurgy.atum.entity.EntityDesertWolf;
import com.teammetallurgy.atum.entity.EntityDustySkeleton;
import com.teammetallurgy.atum.entity.EntityGhost;
import com.teammetallurgy.atum.entity.EntityMummy;
import com.teammetallurgy.atum.entity.EntityPharaoh;
import com.teammetallurgy.atum.entity.EntityStoneSoldier;
import com.teammetallurgy.atum.entity.arrow.CustomArrow;
import com.teammetallurgy.atum.entity.arrow.EntityAtumFishHook;
import com.teammetallurgy.atum.entity.arrow.EntityNutsCall;
import com.teammetallurgy.atum.entity.projectiles.EntityFireSpearCombined;
import com.teammetallurgy.atum.items.Items;
import com.teammetallurgy.atum.lib.tickhandler.ClientTickHandler;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void init() {
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		
		MinecraftForge.EVENT_BUS.register(Items.sekhmetsWrath);
		MinecraftForge.EVENT_BUS.register(Items.mnevisHorns);
		MinecraftForge.EVENT_BUS.register(Items.maatsBalance);
		MinecraftForge.EVENT_BUS.register(Items.anubisMercy);
	}
	
	@Override
	public void initRenders() {
		MinecraftForgeClient.registerItemRenderer(Items.ITEM_BOW.itemID, new RendererItemBow());
		MinecraftForgeClient.registerItemRenderer(Items.atensFury.itemID, new RendererItemBow());
		MinecraftForgeClient.registerItemRenderer(Items.horusSoaring.itemID, new RendererItemBow());
		MinecraftForgeClient.registerItemRenderer(Items.neithsAudacity.itemID, new RendererItemBow());
		MinecraftForgeClient.registerItemRenderer(Items.shusBreath.itemID, new RendererItemBow());
		MinecraftForgeClient.registerItemRenderer(Items.hedetetsVenom.itemID, new RendererItemBow());
		MinecraftForgeClient.registerItemRenderer(Items.monthusBlast.itemID, new RendererItemBow());
		RenderingRegistry.registerBlockHandler(((BlockPapyrus) Blocks.BLOCK_PAPYRUS).renderID, new RenderPapyrus());
		RenderingRegistry.registerBlockHandler(((BlockDate) Blocks.BLOCK_DATEBLOCK).renderID, new RenderDate());

		RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new RenderLiving(new ModelZombie(), 0.5F) {

			@Override
			protected ResourceLocation getEntityTexture(Entity entity) {
				return new ResourceLocation("atum", "textures/entities/Mummy.png");
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityBanditWarrior.class, new RenderBiped(new ModelBiped(), 0.5F) {
			@Override
			protected ResourceLocation getEntityTexture(Entity par1Entity) {
				return new ResourceLocation("atum", "textures/entities/BanditWarrior.png");
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityBarbarian.class, new RenderBiped(new ModelBiped(), 0.5F) {
			@Override
			protected ResourceLocation getEntityTexture(Entity par1Entity) {
				return new ResourceLocation("atum", "textures/entities/Barbarian.png");
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityBanditArcher.class, new RenderBandit(new ModelBiped(), 0.5F) {
			@Override
			protected ResourceLocation getEntityTexture(Entity par1Entity) {
				return new ResourceLocation("atum", "textures/entities/BanditArcher.png");
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityBanditWarlord.class, new RenderBiped(new ModelBiped(), 0.5F) {
			@Override
			protected ResourceLocation getEntityTexture(Entity par1Entity) {
				return new ResourceLocation("atum", "textures/entities/BanditWarlord.png");
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityPharaoh.class, new RenderPharaoh(new ModelBiped(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntityDustySkeleton.class, new RenderBiped(new ModelDustySkeleton(), 0.5F) {
			@Override
			protected ResourceLocation getEntityTexture(Entity par1Entity) {
				return new ResourceLocation("atum", "textures/entities/DustySkeleton.png");
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityGhost.class, new RenderGhost(new ModelZombie(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityStoneSoldier.class, new RenderBiped(new ModelBiped(), 0.5F) {
			@Override
			protected ResourceLocation getEntityTexture(Entity par1Entity) {
				return new ResourceLocation("atum", "textures/entities/StoneSoldier.png");
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityDesertWolf.class, new RenderDesertWolf(new ModelDesertWolf(), new ModelDesertWolf(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntityFireSpearCombined.class, new RenderFireSpear());
		RenderingRegistry.registerEntityRenderingHandler(CustomArrow.class, new RenderCustomArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityNutsCall.class, new RenderNutsCall());
		RenderingRegistry.registerEntityRenderingHandler(EntityAtumFishHook.class, new RenderFish());
	}
}
