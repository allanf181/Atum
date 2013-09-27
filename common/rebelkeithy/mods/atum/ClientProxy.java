package rebelkeithy.mods.atum;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderFish;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.client.MinecraftForgeClient;
import rebelkeithy.mods.atum.artifacts.arrow.CustomArrow;
import rebelkeithy.mods.atum.artifacts.arrow.EntityAtumFishHook;
import rebelkeithy.mods.atum.artifacts.arrow.EntityNutsCall;
import rebelkeithy.mods.atum.artifacts.arrow.RenderCustomArrow;
import rebelkeithy.mods.atum.artifacts.arrow.RenderNutsCall;
import rebelkeithy.mods.atum.blocks.BlockDate;
import rebelkeithy.mods.atum.blocks.BlockPapyrus;
import rebelkeithy.mods.atum.blocks.renderers.DateBlockRenderer;
import rebelkeithy.mods.atum.blocks.renderers.PapyrusBlockRenderer;
import rebelkeithy.mods.atum.entities.EntityBanditArcher;
import rebelkeithy.mods.atum.entities.EntityBanditWarlord;
import rebelkeithy.mods.atum.entities.EntityBanditWarrior;
import rebelkeithy.mods.atum.entities.EntityBarbarian;
import rebelkeithy.mods.atum.entities.EntityDesertWolf;
import rebelkeithy.mods.atum.entities.EntityDustySkeleton;
import rebelkeithy.mods.atum.entities.EntityGhost;
import rebelkeithy.mods.atum.entities.EntityMummy;
import rebelkeithy.mods.atum.entities.EntityPharaoh;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;
import rebelkeithy.mods.atum.entities.ModelDesertWolf;
import rebelkeithy.mods.atum.entities.ModelDustySkeleton;
import rebelkeithy.mods.atum.entities.projectiles.EntityFireSpearCombined;
import rebelkeithy.mods.atum.entities.projectiles.EntityFireSpearSeperated;
import rebelkeithy.mods.atum.entities.projectiles.RenderFireSpear;
import rebelkeithy.mods.atum.entities.projectiles.RenderFireSpearSeperated;
import rebelkeithy.mods.atum.entities.render.CustomRenderBiped;
import rebelkeithy.mods.atum.entities.render.RenderBandit;
import rebelkeithy.mods.atum.entities.render.RenderDesertWolf;
import rebelkeithy.mods.atum.entities.render.RenderGhost;
import rebelkeithy.mods.atum.entities.render.RenderPharaoh;
import rebelkeithy.mods.atum.items.RendererItemBow;
import rebelkeithy.mods.atum.particles.EntityCritFX;
import rebelkeithy.mods.atum.particles.EntitySandFX;
import rebelkeithy.mods.atum.particles.EntitySandPortalFX;
import rebelkeithy.mods.particleregistry.ParticleRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

	public void registerParticles() {
		ParticleRegistry.registerParticle("sand", EntitySandFX.class);
		ParticleRegistry.registerParticle("sandportal", EntitySandPortalFX.class);
		ParticleRegistry.registerParticle("coloredcrit", EntityCritFX.class);
	}

	public File getMinecraftDir() {
		return Minecraft.getMinecraft().mcDataDir;
	}

	public void registerTickHandlers() {
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
		TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);
	}

	public void registerModelRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new CustomRenderBiped(new ModelZombie(), 0.5F, "Mummy.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityBanditWarrior.class, new RenderBandit(new ModelBiped(), 0.5F, "BanditWarrior.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityBarbarian.class, new CustomRenderBiped(new ModelBiped(), 0.5F, "Barbarian.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityBanditArcher.class, new RenderBandit(new ModelBiped(), 0.5F, "BanditArcher.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityBanditWarlord.class, new RenderBandit(new ModelBiped(), 0.5F, "BanditWarlord.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityPharaoh.class, new RenderPharaoh(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDustySkeleton.class, new CustomRenderBiped(new ModelDustySkeleton(), 0.5F, "DustySkeleton.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityGhost.class, new RenderGhost(new ModelZombie(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityStoneSoldier.class, new CustomRenderBiped(new ModelBiped(), 0.5F, "StoneSoldier.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityDesertWolf.class, new RenderDesertWolf(new ModelDesertWolf(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntityFireSpearCombined.class, new RenderFireSpear());
		RenderingRegistry.registerEntityRenderingHandler(CustomArrow.class, new RenderCustomArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityNutsCall.class, new RenderNutsCall());
		RenderingRegistry.registerEntityRenderingHandler(EntityAtumFishHook.class, new RenderFish());
		RenderManager.instance.entityRenderMap.put(EntityFireSpearCombined.class, new RenderFireSpear());
		RenderManager.instance.entityRenderMap.put(EntityFireSpearSeperated.class, new RenderFireSpearSeperated());
		MinecraftForgeClient.registerItemRenderer(AtumItems.bow.itemID, new RendererItemBow());
		MinecraftForgeClient.registerItemRenderer(AtumItems.atensFury.itemID, new RendererItemBow());
		MinecraftForgeClient.registerItemRenderer(AtumItems.horusSoaring.itemID, new RendererItemBow());
		MinecraftForgeClient.registerItemRenderer(AtumItems.neithsAudacity.itemID, new RendererItemBow());
		MinecraftForgeClient.registerItemRenderer(AtumItems.shusBreath.itemID, new RendererItemBow());
		MinecraftForgeClient.registerItemRenderer(AtumItems.hedetetsVenom.itemID, new RendererItemBow());
		MinecraftForgeClient.registerItemRenderer(AtumItems.monthusBlast.itemID, new RendererItemBow());
		RenderingRegistry.registerBlockHandler(((BlockPapyrus) AtumBlocks.papyrus).renderID, new PapyrusBlockRenderer());
		RenderingRegistry.registerBlockHandler(((BlockDate) ((BlockDate) AtumBlocks.dateBlock)).renderID, new DateBlockRenderer());
	}

	public void registerMusic() {
		// MinecraftForge.EVENT_BUS.register(new AtumMusicListener());
	}
}
