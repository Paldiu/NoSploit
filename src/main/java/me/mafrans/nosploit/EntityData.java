/*
 * Copyright 2016 NoSploit
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 *
 * @author Paldiu
 */
package me.mafrans.nosploit;

import org.bukkit.entity.*;
import org.bukkit.attribute.*;
import java.util.ArrayList;
import java.util.List;

public class EntityData {

    public static List<EntityType> entities = new ArrayList<>();
    public static List<Class<?>> allOtherEntities = new ArrayList<>();
    private static final Class<?> ATT = Attributable.class;

    public static void registerData() {
        //register all entity types
        {
            entities.add(EntityType.ARMOR_STAND);
            entities.add(EntityType.BAT);
            entities.add(EntityType.BLAZE);
            entities.add(EntityType.CAVE_SPIDER);
            entities.add(EntityType.CHICKEN);
            entities.add(EntityType.COW);
            entities.add(EntityType.CREEPER);
            //entities.add(EntityType.DONKEY);    instance of horse
            entities.add(EntityType.ENDERMAN);
            entities.add(EntityType.ENDERMITE);
            entities.add(EntityType.ENDER_DRAGON);
            entities.add(EntityType.GHAST);
            entities.add(EntityType.GIANT);
            entities.add(EntityType.GUARDIAN);
            entities.add(EntityType.HORSE);
            entities.add(EntityType.HUSK);
            entities.add(EntityType.IRON_GOLEM);
            entities.add(EntityType.MAGMA_CUBE);
            //entities.add(EntityType.MULE);    instance of horse
            entities.add(EntityType.MUSHROOM_COW);
            entities.add(EntityType.OCELOT);
            entities.add(EntityType.PIG);
            entities.add(EntityType.PIG_ZOMBIE);
            entities.add(EntityType.PLAYER);
            entities.add(EntityType.POLAR_BEAR);
            entities.add(EntityType.RABBIT);
            entities.add(EntityType.SHEEP);
            entities.add(EntityType.SHULKER);
            entities.add(EntityType.SILVERFISH);
            entities.add(EntityType.SKELETON);
            //entities.add(EntityType.SKELETON_HORSE);    instance of horse
            entities.add(EntityType.SLIME);
            entities.add(EntityType.SNOWMAN);
            entities.add(EntityType.SPIDER);
            entities.add(EntityType.SQUID);
            entities.add(EntityType.STRAY);
            entities.add(EntityType.VILLAGER);
            entities.add(EntityType.WITCH);
            entities.add(EntityType.WITHER);
            //entities.add(EntityType.WITHER_SKELETON);    instance of skeleton
            entities.add(EntityType.WOLF);
            entities.add(EntityType.ZOMBIE);
            //entities.add(EntityType.ZOMBIE_HORSE);    instance of horse
            //entities.add(EntityType.ZOMBIE_VILLAGER);    instance of zombie
            entities.add(EntityType.EVOKER);
            entities.add(EntityType.VEX);
            entities.add(EntityType.LLAMA);
            entities.add(EntityType.VINDICATOR);
            entities.add(EntityType.PARROT);
        }

        //Other attributable entities as classes
        {
            allOtherEntities.add(AbstractHorse.class.asSubclass(ATT));
            allOtherEntities.add(Ageable.class.asSubclass(ATT));
            allOtherEntities.add(Ambient.class.asSubclass(ATT));
            allOtherEntities.add(Animals.class.asSubclass(ATT));
            allOtherEntities.add(ChestedHorse.class.asSubclass(ATT));
            allOtherEntities.add(ComplexLivingEntity.class.asSubclass(ATT));
            allOtherEntities.add(Creature.class.asSubclass(ATT));
            allOtherEntities.add(Flying.class.asSubclass(ATT));
            allOtherEntities.add(Golem.class.asSubclass(ATT));
            allOtherEntities.add(HumanEntity.class.asSubclass(ATT));
            allOtherEntities.add(LivingEntity.class.asSubclass(ATT));
            allOtherEntities.add(Monster.class.asSubclass(ATT));
            allOtherEntities.add(NPC.class.asSubclass(ATT));
            allOtherEntities.add(WaterMob.class.asSubclass(ATT));
        }
    }
}
