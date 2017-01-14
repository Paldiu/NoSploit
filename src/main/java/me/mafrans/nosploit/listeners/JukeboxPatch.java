/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author malmar03
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
