package rebelkeithy.mods.atum.furnace;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLimestoneFurnace extends GuiContainer {

	private TileEntityLimestoneFurnace furnaceInventory;

	public GuiLimestoneFurnace(InventoryPlayer par1InventoryPlayer, TileEntity par2TileEntityLimestoneFurnace) {
		super(new ContainerLimestoneFurnace(par1InventoryPlayer, par2TileEntityLimestoneFurnace));
		this.furnaceInventory = (TileEntityLimestoneFurnace) par2TileEntityLimestoneFurnace;
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = this.furnaceInventory.isInvNameLocalized() ? this.furnaceInventory.getInvName() : StatCollector.translateToLocal(this.furnaceInventory.getInvName());
		super.fontRenderer.drawString(s, super.xSize / 2 - super.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		super.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, super.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		super.mc.renderEngine.bindTexture(new ResourceLocation("/gui/furnace.png"));
		int k = (super.width - super.xSize) / 2;
		int l = (super.height - super.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, super.xSize, super.ySize);
		int i1;
		if (this.furnaceInventory.isBurning()) {
			i1 = this.furnaceInventory.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
		}

		i1 = this.furnaceInventory.getCookProgressScaled(24);
		this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}
}
