/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.mafrans.nosploit.listeners;

import me.mafrans.nosploit.Main;
import me.mafrans.nosploit.listeners.*;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author malmar03
 */
public class LeverPatch implements Listener {

    private final Main plugin = Main.plugin;

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
}
