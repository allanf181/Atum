package rebelkeithy.mods.atum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraftforge.client.event.sound.PlayBackgroundMusicEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class AtumMusicListener {

	
	@ForgeSubscribe
	public void onSoundLoadEvent(SoundLoadEvent event) {
		event.manager.addSound("Atum/ALongJourney.ogg");
		event.manager.addSound("Atum/Hostiles.ogg");
		event.manager.addSound("Atum/ScorchingSand.ogg");
		event.manager.addSound("Atum/TempleTales.ogg");
		event.manager.addSound("Atum/TheWanderer.ogg");
		event.manager.addSound("Atum/pharaohspawn.ogg");
	}

	@ForgeSubscribe
	public void onBackgroundMusic(PlayBackgroundMusicEvent event) {
		if (Minecraft.getMinecraft().thePlayer.worldObj.provider.dimensionId == AtumConfig.dimensionID) {
		}

	}

	
}
enum Music {
	ALongJourney("atum:sound/ALongJourney.ogg"),
	HOSTILES("atum:sound/Hostiles.ogg"),
	ScorchingSand("atum:sound/ScorchingSand.ogg"),
	TempleTales("atum:sound/TempleTales.ogg"),
	TheWanderer("atum:sound/TheWanderer.ogg"),
	Pharaohspawn("atum:sound/pharaohspawn.ogg");
	
	private static final List<Music> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();
	
	public String soundLocation;
	public String soundTag;
	
	Music(String music) {
		this.soundLocation = music;
		this.soundTag = this.soundLocation.substring(0, this.soundLocation.length() - ".ogg".length()).replace("/", ".");
	}

	public static Music getRandomTrack() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}