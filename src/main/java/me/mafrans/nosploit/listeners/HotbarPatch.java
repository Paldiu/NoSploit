package me.mafrans.nosploit.listeners;

import me.mafrans.nosploit.Data;
import me.mafrans.nosploit.Main;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

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
 * @author Mafrans
 */
public class HotbarPatch implements Listener {

    private final Main plugin = Main.plugin;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void safeHotbarPatch(PlayerItemHeldEvent e) {

        Player player = e.getPlayer();
        ItemStack inHand = player.getInventory().getItem(e.getNewSlot());
        if (inHand == null) {
            return;
        }
        if (inHand.getType() == Material.AIR) {
            return;
        }
        
        if (!Data.itemDataTable.containsKey(inHand.getTypeId())) {
            return;
        }

        if(inHand instanceof Attributable) {
            for (Attribute attr : Data.attributes.keySet()) {
                if (((Attributable) inHand).getAttribute(attr).getBaseValue() < Data.attributes.get(attr)[0]) {
                    ((Attributable) inHand).getAttribute(attr).setBaseValue(Data.attributes.get(attr)[0]);
                } else if (((Attributable) inHand).getAttribute(attr).getBaseValue() > Data.attributes.get(attr)[1]) {
                    ((Attributable) inHand).getAttribute(attr).setBaseValue(Data.attributes.get(attr)[1]);
                }
            }
        }

        if (inHand.getDurability() > Data.itemDataTable.get(inHand.getTypeId())) {
            inHand.setDurability((short) (Data.itemDataTable.get(inHand.getTypeId())));
        }
    }

}
