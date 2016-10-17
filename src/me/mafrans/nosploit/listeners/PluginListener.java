package me.mafrans.nosploit.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.mafrans.nosploit.Data;
import me.mafrans.nosploit.Main;
import net.minecraft.server.v1_10_R1.EntityInsentient;
import net.minecraft.server.v1_10_R1.GenericAttributes;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class PluginListener implements Listener
{
    private final Main plugin = Main.plugin;
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e)
    {
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack inHand = player.getItemInHand();
        Block block = null; 
        if(action.equals(Action.RIGHT_CLICK_BLOCK)||action.equals(Action.LEFT_CLICK_BLOCK)) 
        {
            block = e.getClickedBlock();
        }
        
        // Block crash mobs
        if(inHand != null && inHand.hasItemMeta() && inHand.getItemMeta().hasDisplayName() && inHand.getItemMeta().getDisplayName().length() > 100)
        {
            if(!isActivated("mobnames"))
            {
                return;
            }
            ItemMeta meta = inHand.getItemMeta();
            meta.setDisplayName(meta.getDisplayName().substring(0, 100));
            inHand.setItemMeta(meta);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract2(PlayerInteractEvent e)
    {
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack inHand = player.getItemInHand();
        Block block = null; 
        if(action.equals(Action.RIGHT_CLICK_BLOCK)||action.equals(Action.LEFT_CLICK_BLOCK)) 
        {
            block = e.getClickedBlock();
        }
        
        // Psuedo-Block crash blocks
        if(action.equals(Action.RIGHT_CLICK_BLOCK) && inHand != null && inHand.getType() != Material.AIR)
        {
            if(!inHand.getType().isBlock()) return;
            if(!isActivated("crashblocks"))
            {
                return;
            }
            if(!Data.itemDataTable.containsKey(inHand.getTypeId()))
            {
                return;
            }
            if(inHand.getDurability() > Data.itemDataTable.get(inHand.getTypeId()))
            {
                inHand.setDurability(Data.itemDataTable.get(inHand.getTypeId()));
                return;
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract3(PlayerInteractEvent e)
    {
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack inHand = player.getItemInHand();
        Block block = null; 
        if(action.equals(Action.RIGHT_CLICK_BLOCK)||action.equals(Action.LEFT_CLICK_BLOCK)) 
        {
            block = e.getClickedBlock();
        }
        
        // Block sign exploits
        if(action.equals(Action.RIGHT_CLICK_BLOCK) && block != null)
        {
            if(!isActivated("signs"))
            {
                return;
            }
            if(block.getType() == Material.SIGN_POST||block.getType() == Material.WALL_SIGN)
            {
                e.setCancelled(true);
                return;
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract4(PlayerInteractEvent e)
    {
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack inHand = player.getItemInHand();
        Block block = null; 
        if(action.equals(Action.RIGHT_CLICK_BLOCK)||action.equals(Action.LEFT_CLICK_BLOCK)) 
        {
            block = e.getClickedBlock();
        }
        
        // Block lever spamming
        if(action.equals(Action.RIGHT_CLICK_BLOCK) && block != null)
        {
            if(!isActivated("levers"))
            {
                return;
            }
            if(block.getType() == Material.LEVER)
            {
                if(plugin.leverPlayers.contains(player))
                {
                    e.setCancelled(true);
                    return;
                }
                else
                {
                    plugin.leverPlayers.add(player);

                    (new BukkitRunnable() 
                    {
                        @Override
                        public void run() 
                        {
                            plugin.leverPlayers.remove(player);
                        }
                    }).runTaskLater(plugin, 3L);
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract5(PlayerInteractEvent e)
    {
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack inHand = player.getItemInHand();
        Block block = null; 
        if(action.equals(Action.RIGHT_CLICK_BLOCK)||action.equals(Action.LEFT_CLICK_BLOCK)) 
        {
            block = e.getClickedBlock();
        }
        
        // Block book exploits
        if(action.equals(Action.RIGHT_CLICK_AIR)||action.equals(Action.RIGHT_CLICK_BLOCK))
        {
            if(!isActivated("stripBookJSON"))
            {
                return;
            }
            if(inHand.getType() == Material.WRITTEN_BOOK)
            {
                BookMeta meta = (BookMeta)inHand.getItemMeta();
                if(meta.hasLore())
                {
                    for(String lore : meta.getLore())
                    {
                        if(lore.contains("verified"))
                        {
                            inHand.setItemMeta(meta);
                            return;
                        }
                    }
                }
                
                meta.setPages(meta.getPages());
                System.out.println(player.getName() + " opened book: " + meta.getPages().toString());
                player.sendMessage(ChatColor.GRAY + "Book JSON has been removed, book is now safe.");
                player.sendMessage(ChatColor.GRAY + "Please open the book once again.");
                meta.setLore(Arrays.asList(ChatColor.GREEN + "verified"));
                inHand.setItemMeta(meta);
                e.setCancelled(true);
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract6(PlayerInteractEvent e)
    {
        List<Integer> discs = Arrays.asList(
        2256,
        2257,
        2258,
        2259,
        2260,
        2261,
        2262,
        2263,
        2264,
        2265,
        2266,
        2267
        );
        
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack inHand = player.getItemInHand();
        Block block = null; 
        if(action.equals(Action.RIGHT_CLICK_BLOCK)||action.equals(Action.LEFT_CLICK_BLOCK)) 
        {
            block = e.getClickedBlock();
        }
        
        
        // Block jukebox exploits
        if(action.equals(Action.RIGHT_CLICK_BLOCK) && block != null)
        {
            if(!isActivated("jukeboxes"))
            {
                return;
            }
            if(block.getType() == Material.JUKEBOX)
            {
                if(discs.contains(inHand.getTypeId()))
                {
                    if(plugin.jukeboxPlayers.contains(player))
                    {
                        player.sendMessage(ChatColor.GRAY + "Jukeboxes can only be used once every second in order to prevent the sound engine from crashing.");
                        e.setCancelled(true);
                        return;
                    }
                    else
                    {
                        plugin.jukeboxPlayers.add(player);
                        
                        (new BukkitRunnable() 
                        {
                            @Override
                            public void run() 
                            {
                                plugin.jukeboxPlayers.remove(player);
                            }
                        }).runTaskLater(plugin, 20*1L);
                    }
                }
            }
        }
    }
    
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntitySpawned(EntitySpawnEvent e)
    {
        Entity entity = e.getEntity();
        
        if(Data.nonLivingEntities.contains(entity.getType()))
        {
            return;
        }
        EntityInsentient nmsEntity = (EntityInsentient) ((CraftLivingEntity) entity).getHandle();
        if(isActivated("mobrange"))
        {
            if(nmsEntity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).getValue() > 32.0)
            {
                nmsEntity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(32.0);
                entity = (Entity) nmsEntity.getBukkitEntity();
                e.getLocation().getWorld().spawn(e.getLocation(), entity.getClass());
                e.setCancelled(true);
            }
        }
    }
    
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryOpen(InventoryOpenEvent e)
    {
        if(!isActivated("chestcontents"))
        {
            return;
        }
        Inventory inventory = e.getInventory();
        for(int i = 0; i < inventory.getSize(); i++)
        {
            if(inventory.getItem(i) != null)
            {
                ItemStack stack = inventory.getItem(i);
                if(stack.getDurability() != 0 && stack.getDurability() > Data.itemDataTable.get(stack.getTypeId()))
                {
                    stack.setDurability(Data.itemDataTable.get(stack.getTypeId()));
                }

                if(stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && stack.getItemMeta().getDisplayName().length() > 100)
                {
                    stack.setType(Material.AIR);
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHotbarSwap(PlayerItemHeldEvent e)
    {
        if(!isActivated("safehotbar"))
        {
            return;
        }
        
        Player player = e.getPlayer();
        ItemStack inHand = player.getInventory().getItem(e.getNewSlot());
        if(inHand == null) return;
        if(inHand.getType() == Material.AIR) return;
        
        if(!Data.itemDataTable.containsKey(inHand.getTypeId()))
        {
            return;
        }
        if(inHand.getDurability() > Data.itemDataTable.get(inHand.getTypeId()))
        {
            inHand.setDurability((short) (Data.itemDataTable.get(inHand.getTypeId())));
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent e)
    {
        if(!isActivated("chestdrops"))
        {
            return;
        }
        if(e.getBlock().getType() != Material.CHEST)
        {
            return;
        }
        Chest chest = (Chest) e.getBlock().getState();
        
        Inventory inventory = chest.getBlockInventory();
        for(int i = 0; i < inventory.getSize(); i++)
        {
            if(inventory.getItem(i) != null)
            {
                ItemStack stack = inventory.getItem(i);
                if(stack.getDurability() != 0 && stack.getDurability() > Data.itemDataTable.get(stack.getTypeId()))
                {
                    stack.setDurability(Data.itemDataTable.get(stack.getTypeId()));
                }

                if(stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && stack.getItemMeta().getDisplayName().length() > 100)
                {
                    stack.setType(Material.AIR);
                }
            }
        }
    }
    
    public static List<Entity> getNearbyEntities(Location where, int range) 
    {
        List<Entity> found = new ArrayList();

        for (Entity entity : where.getWorld().getEntities()) 
        {
            if (isInBorder(where, entity.getLocation(), range)) 
            {
                found.add(entity);
            }
        }
        return found;
    }
    
    public static boolean isInBorder(Location center, Location notCenter, int range) 
    {
        int x = center.getBlockX(), z = center.getBlockZ();
        int x1 = notCenter.getBlockX(), z1 = notCenter.getBlockZ();

        if (x1 >= (x + range) || z1 >= (z + range) || x1 <= (x - range) || z1 <= (z - range)) 
        {
            return false;
        }
    return true;
    }
    
    
    public boolean isActivated(String path)
    {
        return plugin.config.getStringList("modules").contains(path);
    }
}
