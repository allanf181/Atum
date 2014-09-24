package com.teammetallurgy.atum.lib.tickhandler;

import com.teammetallurgy.atum.AtumIDS;
import com.teammetallurgy.atum.items.AtumItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class ClientEvents {
    private boolean raining;

    @SubscribeEvent
    public void onRender(TickEvent.RenderTickEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        if (player != null && player.getCurrentArmor(3) != null) {
            if (player.getCurrentArmor(3).getItem() == AtumItems.mummyHelmet) {
                ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
                int par1 = scaledresolution.getScaledWidth();
                int par2 = scaledresolution.getScaledHeight();

                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("atum", "textures/hud/mummyblur"));
                Tessellator tessellator = Tessellator.instance;
                tessellator.startDrawingQuads();
                tessellator.addVertexWithUV(0.0D, par2, -100, 0.0D, 1.0D);
                tessellator.addVertexWithUV(par1, par2, -100, 1.0D, 1.0D);
                tessellator.addVertexWithUV(par1, 0.0D, -100, 1.0D, 0.0D);
                tessellator.addVertexWithUV(0.0D, 0.0D, -100, 0.0D, 0.0D);
                tessellator.draw();
            }
        }
    }

    @SubscribeEvent
    public void onUpdate(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;

        if (player.dimension == AtumIDS.DIMENSION_ID) {
            if (player.worldObj.isRaining()) {
                raining = true;

                Random random = new Random();
                int particlesPerTick = (3 - Minecraft.getMinecraft().gameSettings.particleSetting) * 6;
                for (int i = 0; i < particlesPerTick; i++) {
                    float x = random.nextInt(4) - 2;
                    float z = random.nextInt(4) - 2;
                    float y = (random.nextFloat() - 0.7F) * 2F;

                    float vx = 0.1F + random.nextFloat() * 0.1F;
                    float vz = 0.1F + random.nextFloat() * 0.1F;

                    player.worldObj.spawnParticle("sand", player.posX + x, player.posY + y, player.posZ + z, vx + player.motionX, 0.0D, vz + player.motionZ);
                }
            }
        }
    }
}
