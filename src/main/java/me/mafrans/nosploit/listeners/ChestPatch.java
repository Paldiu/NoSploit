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

package me.mafrans.nosploit.listeners;

import java.util.Arrays;
import java.util.List;
import me.mafrans.nosploit.Data;
import me.mafrans.nosploit.Main;
import me.mafrans.nosploit.listeners.*;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Dropper;
import org.bukkit.block.Furnace;
import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChestPatch implements Listener {

    private final Main plugin = Main.plugin;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void chestContentPatch(InventoryOpenEvent e) {
        Inventory inventory = e.getInventory();
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null) {
                ItemStack stack = inventory.getItem(i);
                if (!Data.itemDataTable.containsKey(stack.getTypeId())) {
                    return;
                }
                if (stack.getDurability() != 0 && stack.getDurability() > Data.itemDataTable.get(stack.getTypeId())) {
                    stack.setDurability(Data.itemDataTable.get(stack.getTypeId()));
                }

                if (stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && stack.getItemMeta().getDisplayName().length() > 100) {
                    stack.setType(Material.AIR);
                }
            }
        }
    }

    @SuppressWarnings("null")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void chestDropPatch(BlockBreakEvent e) {
        List<Material> containers = Arrays.asList(Material.CHEST, Material.HOPPER, Material.FURNACE, Material.DISPENSER, Material.DROPPER);
        if (!containers.contains(e.getBlock().getType())) {
            return;
        }
        Inventory inventory = null;

        switch (e.getBlock().getType()) {
            case CHEST:
                inventory = ((Chest) e.getBlock().getState()).getBlockInventory();
                break;
            case HOPPER:
                inventory = ((Hopper) e.getBlock().getState()).getInventory();
                break;
            case FURNACE:
                inventory = ((Furnace) e.getBlock().getState()).getInventory();
                break;
            case DISPENSER:
                inventory = ((Dispenser) e.getBlock().getState()).getInventory();
                break;
            case DROPPER:
                inventory = ((Dropper) e.getBlock().getState()).getInventory();
                break;
        }

        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null) {
                ItemStack stack = inventory.getItem(i);
                if (!Data.itemDataTable.containsKey(stack.getTypeId())) {
                    return;
                }
                if (stack.getDurability() != 0 && stack.getDurability() > Data.itemDataTable.get(stack.getTypeId())) {
                    stack.setDurability(Data.itemDataTable.get(stack.getTypeId()));
                }

                if (stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && stack.getItemMeta().getDisplayName().length() > 100) {
                    stack.setType(Material.AIR);
                }
            }
        }
    }
}
