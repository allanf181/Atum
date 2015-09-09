package com.teammetallurgy.atum.entity;

import net.minecraft.entity.EntityList;

import com.teammetallurgy.atum.Atum;
import com.teammetallurgy.atum.entity.arrow.*;

import cpw.mods.fml.common.registry.EntityRegistry;

public class AtumEntities {

    public void register() {
        int entityID = 0;

        // Mobs
        
        EntityRegistry.registerModEntity(EntityMummy.class, "mummy", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityBanditWarrior.class, "banditWarrior", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityBanditArcher.class, "banditArcher", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityPharaoh.class, "pharaoh", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityDustySkeleton.class, "dustySkeleton", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityGhost.class, "desertGhost", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityStoneSoldier.class, "stoneSoldier", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityDesertWolf.class, "desertWolf", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityBanditWarlord.class, "banditWarlord", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityBarbarian.class, "barbarian", entityID++, Atum.instance, 64, 1, true);
        
        // Projectiles

        EntityRegistry.registerModEntity(EntityArrowVelocity.class, "ArrowVeloctiy", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityArrowExplosive.class, "ArrowExplosive", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityArrowPoison.class, "ArrowPoison", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityArrowFire.class, "ArrowFire", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityArrowDoubleShot.class, "ArrowDoubleShot", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityArrowQuickdraw.class, "ArrowQuickDraw", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityNutsCall.class, "EntityNutsCall", entityID++, Atum.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityAtumFishHook.class, "EntityAtumFishHook", entityID++, Atum.instance, 64, 1, false);

    }

}
