package com.teammetallurgy.atum.entity;

import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.LocalizationHelper;
import com.teammetallurgy.atum.entity.arrow.EntityArrowDoubleShot;
import com.teammetallurgy.atum.entity.arrow.EntityArrowExplosive;
import com.teammetallurgy.atum.entity.arrow.EntityArrowFire;
import com.teammetallurgy.atum.entity.arrow.EntityArrowPoison;
import com.teammetallurgy.atum.entity.arrow.EntityArrowQuickdraw;
import com.teammetallurgy.atum.entity.arrow.EntityArrowVelocity;
import com.teammetallurgy.atum.entity.arrow.EntityAtumFishHook;
import com.teammetallurgy.atum.entity.arrow.EntityNutsCall;
import com.teammetallurgy.atum.entity.projectiles.EntityFireSpearCombined;
import com.teammetallurgy.atum.entity.projectiles.EntityFireSpearSeperated;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public enum Entity {
	INSTANCE;

	public void register() {
		int entityID;

		entityID = EntityRegistry.findGlobalUniqueEntityId();

		EntityRegistry.registerModEntity(EntityMummy.class, "AtumMummy", 20, Atum.instance, 64, 1, true);
		EntityList.addMapping(EntityMummy.class, "AtumMummy", 20, 0x515838, 0x868F6B);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityBanditWarrior.class, "AtumBanditWarrior", 21, Atum.instance, 64, 1, true);
		EntityList.addMapping(EntityBanditWarrior.class, "AtumBanditWarrior", 21, 0xC2C2C2, 0x040F85);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityBanditArcher.class, "AtumBanditArcher", 22, Atum.instance, 64, 1, true);
		EntityList.addMapping(EntityBanditArcher.class, "AtumBanditArcher", 22, 0xC2C2C2, 0x7E0C0C);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityPharaoh.class, "AtumPharaoh", 23, Atum.instance, 64, 1, true);
		EntityList.addMapping(EntityPharaoh.class, "AtumPharaoh", 23, 0xD4BC37, 0x3A4BE0);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityDustySkeleton.class, "AtumDustySkeleton", 24, Atum.instance, 64, 1, true);
		EntityList.addMapping(EntityDustySkeleton.class, "AtumDustySkeleton", 24, 0xB59C7D, 0x6F5C43);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityGhost.class, "AtumDesertGhost", 25, Atum.instance, 64, 1, true);
		EntityList.addMapping(EntityGhost.class, "AtumDesertGhost", 25, 0xE7DBC8, 0xAD9467);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityStoneSoldier.class, "AtumStoneSoldier", 26, Atum.instance, 64, 1, true);
		EntityList.addMapping(EntityStoneSoldier.class, "AtumStoneSoldier", 26, 0x918354, 0x695D37);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityDesertWolf.class, "AtumDesertWolf", 27, Atum.instance, 64, 1, true);
		EntityList.addMapping(EntityDesertWolf.class, "AtumDesertWolf", 27, 0x918354, 0x695D37);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityBanditWarlord.class, "AtumBanditWarlord", 28, Atum.instance, 64, 1, true);
		EntityList.addMapping(EntityBanditWarlord.class, "AtumBanditWarlord", 28, 0x918354, 0x695D37);

		EntityRegistry.registerModEntity(EntityBarbarian.class, "AtumBarbarian", 29, Atum.instance, 64, 1, true);
		EntityList.addMapping(EntityBarbarian.class, "AtumBarbarian", 29, 0x918354, 0x695D37);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityFireSpearCombined.class, "FireSpearCombined", entityID, Atum.instance, 64, 1, true);
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityFireSpearSeperated.class, "FireSpearSeperated", entityID, Atum.instance, 64, 1, true);

		EntityRegistry.registerModEntity(EntityArrowVelocity.class, "ArrowVeloctiy", 0, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowExplosive.class, "ArrowExplosive", 1, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowPoison.class, "ArrowPoison", 2, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowFire.class, "ArrowFire", 3, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowDoubleShot.class, "ArrowFire", 4, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowQuickdraw.class, "ArrowFire", 5, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityNutsCall.class, "EntityNutsCall", 6, Atum.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityAtumFishHook.class, "EntityAtumFishHook", 7, Atum.instance, 64, 1, false);

		addNames();
	}

	private void addNames() {
		
	}
	
	private void name(Entity entity, String tag) {
		LanguageRegistry.addName(entity, LocalizationHelper.localize("block." + tag + ".name"));
	}

}
