package com.teammetallurgy.atum.entity;

import net.minecraft.entity.EntityList;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.entity.arrow.EntityArrowDoubleShot;
import com.teammetallurgy.atum.entity.arrow.EntityArrowExplosive;
import com.teammetallurgy.atum.entity.arrow.EntityArrowFire;
import com.teammetallurgy.atum.entity.arrow.EntityArrowPoison;
import com.teammetallurgy.atum.entity.arrow.EntityArrowQuickdraw;
import com.teammetallurgy.atum.entity.arrow.EntityArrowVelocity;
import com.teammetallurgy.atum.entity.arrow.EntityAtumFishHook;
import com.teammetallurgy.atum.entity.arrow.EntityNutsCall;

import cpw.mods.fml.common.registry.EntityRegistry;

public enum AtumEntities {
	INSTANCE;

	public void register() {
		int entityID;

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityMummy.class, "mummy", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityMummy.class, "mummy", entityID, 0x515838, 0x868F6B);

		entityID++;
		EntityRegistry.registerModEntity(EntityBanditWarrior.class, "banditWarrior", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityBanditWarrior.class, "banditWarrior", entityID, 0xC2C2C2, 0x040F85);

		entityID++;
		EntityRegistry.registerModEntity(EntityBanditArcher.class, "banditArcher", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityBanditArcher.class, "banditArcher", entityID, 0xC2C2C2, 0x7E0C0C);

		entityID++;
		EntityRegistry.registerModEntity(EntityPharaoh.class, "pharaoh", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityPharaoh.class, "pharaoh", entityID, 0xD4BC37, 0x3A4BE0);

		entityID++;
		EntityRegistry.registerModEntity(EntityDustySkeleton.class, "dustySkeleton", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityDustySkeleton.class, "dustySkeleton", entityID, 0xB59C7D, 0x6F5C43);

		entityID++;
		EntityRegistry.registerModEntity(EntityGhost.class, "desertGhost", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityGhost.class, "desertGhost", entityID, 0xE7DBC8, 0xAD9467);

		entityID++;
		EntityRegistry.registerModEntity(EntityStoneSoldier.class, "stoneSoldier", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityStoneSoldier.class, "stoneSoldier", entityID, 0x918354, 0x695D37);

		entityID++;
		EntityRegistry.registerModEntity(EntityDesertWolf.class, "desertWolf", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityDesertWolf.class, "desertWolf", entityID, 0x918354, 0x695D37);

		entityID++;
		EntityRegistry.registerModEntity(EntityBanditWarlord.class, "banditWarlord", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityBanditWarlord.class, "banditWarlord", entityID, 0x918354, 0x695D37);
		
		entityID++;
		EntityRegistry.registerModEntity(EntityBarbarian.class, "barbarian", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityBarbarian.class, "barbarian", entityID, 0x918354, 0x695D37);

		EntityRegistry.registerModEntity(EntityArrowVelocity.class, "ArrowVeloctiy", 0, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowExplosive.class, "ArrowExplosive", 1, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowPoison.class, "ArrowPoison", 2, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowFire.class, "ArrowFire", 3, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowDoubleShot.class, "ArrowFire", 4, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowQuickdraw.class, "ArrowFire", 5, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityNutsCall.class, "EntityNutsCall", 6, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityAtumFishHook.class, "EntityAtumFishHook", 7, Atum.instance, 64, 1, false);

	}

}
