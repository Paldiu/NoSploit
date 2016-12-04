/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class Command_getnbt implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "This command may only be executed in game.");
            return true;
        }
        if(args.length != 0)
        {
            return false;
        }
        Player psender = (Player)sender;
        if(psender.getInventory().getItemInMainHand() == null
        || psender.getInventory().getItemInMainHand().getType() == Material.AIR)
        {
            sender.sendMessage(ChatColor.RED + "You need to hold an item to see its NBT data!");
            return true;
        }
        
        ItemStack item = psender.getInventory().getItemInMainHand();
        
        sender.sendMessage(ChatColor.GREEN + "NBT Data of item " + item.getType().toString() + ":");
        sender.sendMessage(ChatColor.GRAY + item.serialize().toString().replace("§","&"));
        
        return true;
    }
}
