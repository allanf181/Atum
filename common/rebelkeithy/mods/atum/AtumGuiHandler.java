package rebelkeithy.mods.atum;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.furnace.ContainerLimestoneFurnace;
import rebelkeithy.mods.atum.furnace.GuiLimestoneFurnace;
import rebelkeithy.mods.atum.furnace.TileEntityLimestoneFurnace;

public class AtumGuiHandler implements IGuiHandler {

   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      System.out.println("GUI?");
      TileEntity te = world.getBlockTileEntity(x, y, z);
      return te != null && te instanceof TileEntityLimestoneFurnace?new ContainerLimestoneFurnace(player.inventory, te):null;
   }

   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      System.out.println("GUI?");
      TileEntity te = world.getBlockTileEntity(x, y, z);
      return te != null && te instanceof TileEntityLimestoneFurnace?new GuiLimestoneFurnace(player.inventory, te):null;
   }
}
