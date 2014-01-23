package com.teammetallurgy.atum.proxy;

import net.minecraftforge.client.MinecraftForgeClient;

import com.teammetallurgy.atum.blocks.BlockDate;
import com.teammetallurgy.atum.blocks.BlockPapyrus;
import com.teammetallurgy.atum.blocks.Blocks;
import com.teammetallurgy.atum.client.render.item.RendererItemBow;
import com.teammetallurgy.atum.client.render.tileentity.RenderDate;
import com.teammetallurgy.atum.client.render.tileentity.RenderPapyrus;
import com.teammetallurgy.atum.items.Items;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
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
	}
}
