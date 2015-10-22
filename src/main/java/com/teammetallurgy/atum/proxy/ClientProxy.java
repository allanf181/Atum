package com.teammetallurgy.atum.proxy;

import com.teammetallurgy.atum.client.render.entity.*;
import com.teammetallurgy.atum.client.render.entity.arrow.RenderBone;
import com.teammetallurgy.atum.entity.projectile.EntitySmallBone;
import com.teammetallurgy.atum.handler.AtumConfig;
import com.teammetallurgy.atum.handler.event.AtumFogEventListener;
import com.teammetallurgy.atum.blocks.AtumBlocks;
import com.teammetallurgy.atum.blocks.BlockDate;
import com.teammetallurgy.atum.blocks.BlockPapyrus;
import com.teammetallurgy.atum.client.model.entity.ModelDesertWolf;
import com.teammetallurgy.atum.client.model.entity.ModelDustySkeleton;
import com.teammetallurgy.atum.client.render.entity.arrow.RenderCustomArrow;
import com.teammetallurgy.atum.client.render.entity.arrow.RenderNutsCall;
import com.teammetallurgy.atum.client.render.item.RendererItemBow;
import com.teammetallurgy.atum.client.render.tileentity.RenderDate;
import com.teammetallurgy.atum.client.render.tileentity.RenderPapyrus;
import com.teammetallurgy.atum.entity.*;
import com.teammetallurgy.atum.entity.arrow.CustomArrow;
import com.teammetallurgy.atum.entity.arrow.EntityAtumFishHook;
import com.teammetallurgy.atum.entity.arrow.EntityNutsCall;
import com.teammetallurgy.atum.items.AtumItems;
import com.teammetallurgy.atum.handler.event.ClientEvents;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderFish;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        ClientEvents ticker = new ClientEvents();
        FMLCommonHandler.instance().bus().register(ticker);

        MinecraftForge.EVENT_BUS.register(AtumItems.anubisMercy);
        MinecraftForge.EVENT_BUS.register(AtumItems.gebsSolidarity);
        MinecraftForge.EVENT_BUS.register(AtumItems.horusFlight);
        MinecraftForge.EVENT_BUS.register(AtumItems.maatsBalance);
        MinecraftForge.EVENT_BUS.register(AtumItems.mnevisHorns);
        MinecraftForge.EVENT_BUS.register(AtumItems.sekhmetsWrath);

        if (AtumConfig.FOG_ENABLED) {
            MinecraftForge.EVENT_BUS.register(new AtumFogEventListener());
        }
    }

    @Override
    public void initRenders() {
        MinecraftForgeClient.registerItemRenderer(AtumItems.ITEM_BOW, new RendererItemBow());
        MinecraftForgeClient.registerItemRenderer(AtumItems.atensFury, new RendererItemBow());
        MinecraftForgeClient.registerItemRenderer(AtumItems.horusSoaring, new RendererItemBow());
        MinecraftForgeClient.registerItemRenderer(AtumItems.neithsAudacity, new RendererItemBow());
        MinecraftForgeClient.registerItemRenderer(AtumItems.shusBreath, new RendererItemBow());
        MinecraftForgeClient.registerItemRenderer(AtumItems.hedetetsVenom, new RendererItemBow());
        MinecraftForgeClient.registerItemRenderer(AtumItems.monthusBlast, new RendererItemBow());
        RenderingRegistry.registerBlockHandler(((BlockPapyrus) AtumBlocks.BLOCK_PAPYRUS).renderID, new RenderPapyrus());
        RenderingRegistry.registerBlockHandler(((BlockDate) AtumBlocks.BLOCK_DATEBLOCK).renderID, new RenderDate());

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
        RenderingRegistry.registerEntityRenderingHandler(EntityBonestorm.class, new RenderBonestorm());

        RenderingRegistry.registerEntityRenderingHandler(CustomArrow.class, new RenderCustomArrow());
        RenderingRegistry.registerEntityRenderingHandler(EntitySmallBone.class, new RenderBone(0.35F));
        RenderingRegistry.registerEntityRenderingHandler(EntityNutsCall.class, new RenderNutsCall());
        RenderingRegistry.registerEntityRenderingHandler(EntityAtumFishHook.class, new RenderFish());
    }
}
