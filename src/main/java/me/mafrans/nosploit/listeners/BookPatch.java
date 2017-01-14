/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.mafrans.nosploit.listeners;

import me.mafrans.nosploit.Data;
import me.mafrans.nosploit.Main;
import me.mafrans.nosploit.NLogger;
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
import org.bukkit.inventory.meta.BookMeta;

/**
 *
 * @author malmar03
 */
public class BookPatch implements Listener {

    private final Main plugin = Main.plugin;

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

}
