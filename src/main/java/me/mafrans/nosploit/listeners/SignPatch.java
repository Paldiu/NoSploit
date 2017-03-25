package me.mafrans.nosploit.listeners;

import me.mafrans.nosploit.Main;
import me.mafrans.nosploit.listeners.*;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

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
public class SignPatch implements Listener {

    private final Main plugin = Main.plugin;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void signPatch(PlayerInteractEvent e) {
        Action action = e.getAction();
        Block block = null;
        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            block = e.getClickedBlock();
        }

        // Block sign exploits
        if (action.equals(Action.RIGHT_CLICK_BLOCK) && block != null) {
            if (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
                e.setCancelled(true);
            }
        }
    }

}
