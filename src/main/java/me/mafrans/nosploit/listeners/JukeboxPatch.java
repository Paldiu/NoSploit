package me.mafrans.nosploit.listeners;

import java.util.Arrays;
import java.util.List;
import me.mafrans.nosploit.Main;
import me.mafrans.nosploit.listeners.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

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
public class JukeboxPatch implements Listener {

    private final Main plugin = Main.plugin;

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

}
