/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.mafrans.nosploit.listeners;

import me.mafrans.nosploit.Data;
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
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author malmar03
 */
public class CrashblockPatch implements Listener {

    private final Main plugin = Main.plugin;

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

}
