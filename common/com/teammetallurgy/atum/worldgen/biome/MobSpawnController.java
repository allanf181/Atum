package com.teammetallurgy.atum.worldgen.biome;

import java.util.List;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent.PotentialSpawns;

public class MobSpawnController {

	@ForgeSubscribe
	public void getPotentialSpawns(PotentialSpawns event) {
		List list = event.list;
		int time = (int) (event.world.getWorldTime() % 48000L);
		System.out.println(time);

		for(int i = 0; i < list.size(); ++i) {
			System.out.println(time);
			//if((!IAtumDayMob.class.isAssignableFrom(((SpawnListEntry) list.get(i)).entityClass) || time <= 0 || time >= 24000) && (!IAtumNightMob.class.isAssignableFrom(((SpawnListEntry) list.get(i)).entityClass) || time <= 24000 || time >= '\ubb80')) {
				list.remove(i);
				--i;
			//}
		}

	}
}
