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
package me.mafrans.nosploit.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author malmar03
 */
public class Command_getnbt implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command may only be executed in game.");
            return true;
        }
        if (args.length != 0) {
            return false;
        }
        Player psender = (Player) sender;
        if (psender.getInventory().getItemInMainHand() == null
            || psender.getInventory().getItemInMainHand().getType() == Material.AIR) {
            sender.sendMessage(ChatColor.RED + "You need to hold an item to see its NBT data!");
            return true;
        }

        ItemStack item = psender.getInventory().getItemInMainHand();

        sender.sendMessage(ChatColor.GREEN + "NBT Data of item " + item.getType().toString() + ":");
        sender.sendMessage(ChatColor.GRAY + item.serialize().toString().replace("§", "&"));

        return true;
    }
}
