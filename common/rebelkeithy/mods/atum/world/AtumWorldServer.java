package rebelkeithy.mods.atum.world;

import net.minecraft.logging.ILogAgent;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveHandler;
import rebelkeithy.mods.atum.AtumTeleporter;

public class AtumWorldServer extends WorldServer {

   public AtumWorldServer(MinecraftServer par1MinecraftServer, ISaveHandler par2iSaveHandler, String par3Str, int par4, WorldSettings par5WorldSettings, Profiler par6Profiler, ILogAgent par7iLogAgent) {
      super(par1MinecraftServer, par2iSaveHandler, par3Str, par4, par5WorldSettings, par6Profiler, par7iLogAgent);
   }

   public Teleporter getDefaultTeleporter() {
      return new AtumTeleporter(this);
   }
}
