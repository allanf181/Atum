package com.teammetallurgy.atum.entity;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.entity.arrow.*;
import cpw.mods.fml.common.registry.EntityRegistry;

public class AtumEntities {

    public void register() {
        int entityID;

        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityMummy.class, "atum.mummy", entityID, 0x515838, 0x868F6B);
        EntityRegistry.registerModEntity(EntityMummy.class, "atum.mummy", entityID, Atum.instance, 64, 1, true);

        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityBanditWarrior.class, "atum.banditWarrior", entityID, 0xC2C2C2, 0x040F85);
        EntityRegistry.registerModEntity(EntityBanditWarrior.class, "atum.banditWarrior", entityID, Atum.instance, 64, 1, true);
        

        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityBanditArcher.class, "atum.banditArcher", entityID, 0xC2C2C2, 0x7E0C0C);
        EntityRegistry.registerModEntity(EntityBanditArcher.class, "atum.banditArcher", entityID, Atum.instance, 64, 1, true);
        

        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityPharaoh.class, "atum.pharaoh", entityID, 0xD4BC37, 0x3A4BE0);
        EntityRegistry.registerModEntity(EntityPharaoh.class, "atum.pharaoh", entityID, Atum.instance, 64, 1, true);
        

        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityDustySkeleton.class, "atum.dustySkeleton", entityID, 0xB59C7D, 0x6F5C43);
        EntityRegistry.registerModEntity(EntityDustySkeleton.class, "atum.dustySkeleton", entityID, Atum.instance, 64, 1, true);
        

        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityGhost.class, "atum.desertGhost", entityID, 0xE7DBC8, 0xAD9467);
        EntityRegistry.registerModEntity(EntityGhost.class, "atum.desertGhost", entityID, Atum.instance, 64, 1, true);
        

        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityStoneSoldier.class, "atum.stoneSoldier", entityID, 0x918354, 0x695D37);
        EntityRegistry.registerModEntity(EntityStoneSoldier.class, "atum.stoneSoldier", entityID, Atum.instance, 64, 1, true);
        

        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityDesertWolf.class, "atum.desertWolf", entityID, 0x918354, 0x695D37);
        EntityRegistry.registerModEntity(EntityDesertWolf.class, "atum.desertWolf", entityID, Atum.instance, 64, 1, true);
        

        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityBanditWarlord.class, "atum.banditWarlord", entityID, 0x918354, 0x695D37);
        EntityRegistry.registerModEntity(EntityBanditWarlord.class, "atum.banditWarlord", entityID, Atum.instance, 64, 1, true);
        

        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityBarbarian.class, "atum.barbarian", entityID, 0x918354, 0x695D37);
        EntityRegistry.registerModEntity(EntityBarbarian.class, "atum.barbarian", entityID, Atum.instance, 64, 1, true);
        

        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(EntityArrowVelocity.class, "atum.ArrowVeloctiy", entityID, Atum.instance, 64, 1, true);
        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(EntityArrowExplosive.class, "atum.ArrowExplosive", entityID, Atum.instance, 64, 1, true);
        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(EntityArrowPoison.class, "atum.ArrowPoison", entityID, Atum.instance, 64, 1, true);
        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(EntityArrowFire.class, "atum.ArrowFire", entityID, Atum.instance, 64, 1, true);
        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(EntityArrowDoubleShot.class, "atum.ArrowFire", entityID, Atum.instance, 64, 1, true);
        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(EntityArrowQuickdraw.class, "atum.ArrowFire", entityID, Atum.instance, 64, 1, true);
        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(EntityNutsCall.class, "atum.EntityNutsCall", entityID, Atum.instance, 64, 1, true);
        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerModEntity(EntityAtumFishHook.class, "atum.EntityAtumFishHook", entityID, Atum.instance, 64, 1, false);

    }

}
