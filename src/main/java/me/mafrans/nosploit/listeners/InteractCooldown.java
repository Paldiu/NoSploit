package me.mafrans.nosploit.listeners;

import java.util.Arrays;
import java.util.List;
import me.mafrans.nosploit.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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
public class InteractCooldown implements Listener {

    private final Main plugin = Main.plugin;
    private final long cooldown = 5L;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void cooldownPatch(PlayerInteractEvent e) {
        
        List<Material> blocks = Arrays.asList(Material.LEVER);
        
        final Player player = e.getPlayer();
        Action action = e.getAction();
        Block block = null;
        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            block = e.getClickedBlock();
        }

        // Block lever spamming
        if (action.equals(Action.RIGHT_CLICK_BLOCK) && block != null) {
            if (blocks.contains(block.getType())) {
                if (plugin.cooldownPlayers.contains(player)) {
                    e.setCancelled(true);
                }
                else {
                    plugin.cooldownPlayers.add(player);

                    (new BukkitRunnable() {
                        @Override
                        public void run() {
                            plugin.cooldownPlayers.remove(player);
                        }
                    }).runTaskLater(plugin, cooldown);
                }
            }
        }
    }
}
