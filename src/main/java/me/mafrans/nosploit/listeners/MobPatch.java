/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.mafrans.nosploit.listeners;

import me.mafrans.nosploit.Data;
import me.mafrans.nosploit.Main;
import me.mafrans.nosploit.NLogger;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author malmar03
 */
public class MobPatch implements Listener {

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
            ItemMeta meta = inHand.getItemMeta();
            meta.setDisplayName(meta.getDisplayName().substring(0, 100));
            inHand.setItemMeta(meta);
        }
        //Check other hand!
        if (offHand != null && offHand.hasItemMeta() && offHand.getItemMeta().hasDisplayName() && offHand.getItemMeta().getDisplayName().length() > 100) {
            ItemMeta meta = offHand.getItemMeta();
            meta.setDisplayName(meta.getDisplayName().substring(0, 100));
            offHand.setItemMeta(meta);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void mobrangePatch(EntitySpawnEvent e) {
        if (Data.nonLivingEntities.contains(e.getEntity().getType())) {
            return;
        }

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
