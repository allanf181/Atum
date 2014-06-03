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
		EntityRegistry.registerModEntity(EntityMummy.class, "AtumMummy", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityMummy.class, "AtumMummy", entityID, 0x515838, 0x868F6B);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityBanditWarrior.class, "AtumBanditWarrior", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityBanditWarrior.class, "AtumBanditWarrior", entityID, 0xC2C2C2, 0x040F85);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityBanditArcher.class, "AtumBanditArcher", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityBanditArcher.class, "AtumBanditArcher", entityID, 0xC2C2C2, 0x7E0C0C);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityPharaoh.class, "AtumPharaoh", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityPharaoh.class, "AtumPharaoh", entityID, 0xD4BC37, 0x3A4BE0);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityDustySkeleton.class, "AtumDustySkeleton", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityDustySkeleton.class, "AtumDustySkeleton", entityID, 0xB59C7D, 0x6F5C43);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityGhost.class, "AtumDesertGhost", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityGhost.class, "AtumDesertGhost", entityID, 0xE7DBC8, 0xAD9467);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityStoneSoldier.class, "AtumStoneSoldier", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityStoneSoldier.class, "AtumStoneSoldier", entityID, 0x918354, 0x695D37);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityDesertWolf.class, "AtumDesertWolf", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityDesertWolf.class, "AtumDesertWolf", entityID, 0x918354, 0x695D37);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityBanditWarlord.class, "AtumBanditWarlord", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityBanditWarlord.class, "AtumBanditWarlord", entityID, 0x918354, 0x695D37);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityBarbarian.class, "AtumBarbarian", entityID, Atum.instance, 64, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityBarbarian.class, "AtumBarbarian", entityID, 0x918354, 0x695D37);

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
