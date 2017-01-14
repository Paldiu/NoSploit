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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author malmar03
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
        if (inHand.getDurability() > Data.itemDataTable.get(inHand.getTypeId())) {
            inHand.setDurability((short) (Data.itemDataTable.get(inHand.getTypeId())));
        }
    }

}
