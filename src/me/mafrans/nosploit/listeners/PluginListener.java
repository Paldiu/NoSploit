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
import me.mafrans.nosploit.NLogger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Dropper;
import org.bukkit.block.Furnace;
import org.bukkit.block.Hopper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class PluginListener implements Listener {

    private final Main plugin = Main.plugin;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void mobNamePatch(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack inHand = player.getInventory().getItemInMainHand();
        ItemStack offHand = player.getInventory().getItemInOffHand();
        Block block = null;
        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            block = e.getClickedBlock();
        }

        // Block crash mobs
        if (inHand != null && inHand.hasItemMeta() && inHand.getItemMeta().hasDisplayName() && inHand.getItemMeta().getDisplayName().length() > 100) {
            if (!isActivated("mobnames")) {
                return;
            }
            ItemMeta meta = inHand.getItemMeta();
            meta.setDisplayName(meta.getDisplayName().substring(0, 100));
            inHand.setItemMeta(meta);
        }
        //Check other hand!
        if (offHand != null && offHand.hasItemMeta() && offHand.getItemMeta().hasDisplayName() && offHand.getItemMeta().getDisplayName().length() > 100) {
            if (!isActivated("mobnames")) {
                return;
            }
            ItemMeta meta = offHand.getItemMeta();
            meta.setDisplayName(meta.getDisplayName().substring(0, 100));
            offHand.setItemMeta(meta);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void cbPatch(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack inHand = player.getInventory().getItemInMainHand();
        ItemStack offHand = player.getInventory().getItemInOffHand();
        Block block = null;
        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            block = e.getClickedBlock();
        }

        // Psuedo-Block crash blocks
        if (action.equals(Action.RIGHT_CLICK_BLOCK) && inHand != null && inHand.getType() != Material.AIR) {
            if (!inHand.getType().isBlock()) {
                return;
            }
            if (!offHand.getType().isBlock()) {
                return;
            }
            if (!isActivated("crashblocks")) {
                return;
            }
            if (!Data.itemDataTable.containsKey(inHand.getTypeId())) {
                return;
            }
            if (!Data.itemDataTable.containsKey(offHand.getTypeId())) {
                return;
            }
            if (inHand.getDurability() > Data.itemDataTable.get(inHand.getTypeId())) {
                inHand.setDurability(Data.itemDataTable.get(inHand.getTypeId()));
            }
            if (offHand.getDurability() > Data.itemDataTable.get(offHand.getTypeId())) {
                offHand.setDurability(Data.itemDataTable.get(offHand.getTypeId()));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void signPatch(PlayerInteractEvent e) {
        Action action = e.getAction();
        Block block = null;
        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            block = e.getClickedBlock();
        }

        // Block sign exploits
        if (action.equals(Action.RIGHT_CLICK_BLOCK) && block != null) {
            if (!isActivated("signs")) {
                return;
            }
            if (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void leverPatch(PlayerInteractEvent e) {
        final Player player = e.getPlayer();
        Action action = e.getAction();
        Block block = null;
        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            block = e.getClickedBlock();
        }

        // Block lever spamming
        if (action.equals(Action.RIGHT_CLICK_BLOCK) && block != null) {
            if (!isActivated("levers")) {
                return;
            }
            if (block.getType() == Material.LEVER) {
                if (plugin.leverPlayers.contains(player)) {
                    e.setCancelled(true);
                }
                else {
                    plugin.leverPlayers.add(player);

                    (new BukkitRunnable() {
                        @Override
                        public void run() {
                            plugin.leverPlayers.remove(player);
                        }
                    }).runTaskLater(plugin, 3L);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void bookPatch(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack inHand = player.getInventory().getItemInMainHand();
        ItemStack offHand = player.getInventory().getItemInOffHand();
        Block block = null;
        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            block = e.getClickedBlock();
        }

        // Block book exploits
        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (!isActivated("stripBookJSON")) {
                return;
            }
            if (inHand.getType() == Material.WRITTEN_BOOK) {
                if (Data.books.contains(inHand)) {
                    return;
                }

                BookMeta meta = (BookMeta) inHand.getItemMeta();
                meta.setPages(meta.getPages());
                if (plugin.config.getBoolean("debug")) {
                    NLogger.info(player.getName() + " opened book: " + inHand.serialize().toString());
                }
                player.sendMessage(ChatColor.GRAY + "Book JSON has been removed, this book is now safe.");
                player.sendMessage(ChatColor.GRAY + "Please open the book once again.");
                inHand.setItemMeta(meta);

                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                book.setItemMeta(meta);

                inHand.setType(Material.AIR);
                player.getInventory().setItemInMainHand(book);
                Data.books.add(book);

                e.setCancelled(true);
            }
            if (offHand.getType() == Material.WRITTEN_BOOK) {
                if (Data.books.contains(offHand)) {
                    return;
                }

                BookMeta meta = (BookMeta) offHand.getItemMeta();
                meta.setPages(meta.getPages());
                if (plugin.config.getBoolean("debug")) {
                    NLogger.info(player.getName() + " opened book: " + offHand.serialize().toString());
                }
                player.sendMessage(ChatColor.GRAY + "Book JSON has been removed, this book is now safe.");
                player.sendMessage(ChatColor.GRAY + "Please open the book once again.");
                offHand.setItemMeta(meta);

                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                book.setItemMeta(meta);

                offHand.setType(Material.AIR);
                player.getInventory().setItemInOffHand(book);
                Data.books.add(book);

                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void jukeboxPatch(PlayerInteractEvent e) {
        List<Integer> discs = Arrays.asList(
                2256,
                2257,
                2258,
                2259,
                2260,
                2261,
                2262,
                2263,
                2264,
                2265,
                2266,
                2267
        );

        final Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack inHand = player.getInventory().getItemInMainHand();
        ItemStack offHand = player.getInventory().getItemInOffHand();
        Block block = null;
        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            block = e.getClickedBlock();
        }

        // Block jukebox exploits
        if (action.equals(Action.RIGHT_CLICK_BLOCK) && block != null) {
            if (!isActivated("jukeboxes")) {
                return;
            }
            if (block.getType() == Material.JUKEBOX) {
                if (discs.contains(inHand.getTypeId())) {
                    if (plugin.jukeboxPlayers.contains(player)) {
                        player.sendMessage(ChatColor.GRAY + "Jukeboxes can only be used once every second in order to prevent the sound engine from crashing.");
                        e.setCancelled(true);
                    }
                    else {
                        plugin.jukeboxPlayers.add(player);

                        (new BukkitRunnable() {
                            @Override
                            public void run() {
                                plugin.jukeboxPlayers.remove(player);
                            }
                        }).runTaskLater(plugin, 20L);
                    }
                }
                if (discs.contains(offHand.getTypeId())) {
                    if (plugin.jukeboxPlayers.contains(player)) {
                        player.sendMessage(ChatColor.GRAY + "Jukeboxes can only be used once every second in order to prevent the sound engine from crashing.");
                        e.setCancelled(true);
                    }
                    else {
                        plugin.jukeboxPlayers.add(player);

                        (new BukkitRunnable() {
                            @Override
                            public void run() {
                                plugin.jukeboxPlayers.remove(player);
                            }
                        }).runTaskLater(plugin, 20L);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void mobrangePatch(EntitySpawnEvent e)
    {
        if (Data.nonLivingEntities.contains(e.getEntity().getType())) {
            return;
        }

        if (isActivated("mobrange")) {
            Entity entity = e.getEntity();
            if (entity instanceof Attributable) {
                if (((Attributable) entity).getAttribute(Attribute.GENERIC_FOLLOW_RANGE).getBaseValue() > 255.0) {
                    if (plugin.config.getBoolean("debug")) {
                        NLogger.warning("An attempt was done to spawn a potentially harmful " + e.getEntity().getType().toString() + "!");
                    }
                    ((Attributable) entity).getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(255.0);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void chestContentPatch(InventoryOpenEvent e) {
        if (!isActivated("chestcontents")) {
            return;
        }
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void safeHotbarPatch(PlayerItemHeldEvent e) {
        if (!isActivated("safehotbar")) {
            return;
        }

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
        if (inHand.getDurability() > Data.itemDataTable.get(inHand.getTypeId())) {
            inHand.setDurability((short) (Data.itemDataTable.get(inHand.getTypeId())));
        }
    }

    
    @SuppressWarnings("null")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void chestDropPatch(BlockBreakEvent e) {
        List<Material> containers = Arrays.asList(Material.CHEST, Material.HOPPER, Material.FURNACE, Material.DISPENSER, Material.DROPPER);
        if (!isActivated("chestdrops")) {
            return;
        }
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

    public boolean isActivated(String path) {
        return plugin.config.getStringList("modules").contains(path);
    }
}
