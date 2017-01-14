/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.mafrans.nosploit.listeners;


import java.util.List;
import java.util.ArrayList;
import me.mafrans.nosploit.EntityData;
import me.mafrans.nosploit.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author malmar03
 */
public class SpawnerPatch implements Listener {

    private final Main plugin = Main.plugin;
    private List<String> entities;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void invalidEntityPatch(PlayerInteractEvent e) {
        final Player player = e.getPlayer();
        Action action = e.getAction();
        Block block = null;
        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            block = e.getClickedBlock();
        }
        
         entities = new ArrayList();
         
        
        // Block spawner spamming
        if (action.equals(Action.RIGHT_CLICK_BLOCK) && block != null) {
            if (block.getType() == Material.MOB_SPAWNER) {
                player.sendMessage(ChatColor.GRAY + "For security purposes you cannot interact with spawners.");
                player.sendMessage(ChatColor.GRAY + "this will be removed once the server updates to 1.11");
                e.setCancelled(true);
                return;
            }
        }
    }
}
